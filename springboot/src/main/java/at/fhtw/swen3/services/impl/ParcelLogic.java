package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.*;
import at.fhtw.swen3.persistence.repositories.HopRepository;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.persistence.repositories.TransferwarehouseRepository;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.services.dto.Transferwarehouse;
import at.fhtw.swen3.services.exception.BLDataNotFoundException;
import at.fhtw.swen3.services.exception.BLException;
import at.fhtw.swen3.services.exception.BLValidationException;
import at.fhtw.swen3.services.mapper.HopMapper;
import at.fhtw.swen3.services.mapper.ParcelMapper;
import at.fhtw.swen3.services.mapper.RecipientMapper;
import at.fhtw.swen3.services.validation.BLValidator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.Random;

@Slf4j
@Component
public class ParcelLogic {
    private final ParcelRepository parcelRepository;
    private final HopRepository hopRepository;

    private final TransferwarehouseRepository transferwarehouseRepository;

    @Autowired
    public ParcelLogic(ParcelRepository parcelRepository, HopRepository hopRepository, TransferwarehouseRepository transferwarehouseRepository) {
        this.parcelRepository = parcelRepository;
        this.hopRepository = hopRepository;
        this.transferwarehouseRepository = transferwarehouseRepository;
    }

    public void reportParcelDelivery(String trackingId) throws BLException{
        ParcelEntity parcelEntity = parcelRepository.findByTrackingId(trackingId);
        if (parcelEntity == null) {
            log.error("Parcel does not exist with this tracking ID {}", trackingId);
            throw new BLDataNotFoundException(null, "Parcel does not exist with this tracking ID: " + trackingId);
        }
        log.info("Parcel state:" + parcelEntity.getState());

        parcelEntity.setState(State.DELIVERED);
        log.info("Parcel state after change:" + parcelEntity.getState());

        try {
            // set state to DELIVERED(4) in DB
            parcelRepository.setStateToDifferentState(State.DELIVERED.ordinal(), trackingId);
            log.info("Parcel state after save:" + parcelEntity.getState());
        } catch (Exception e) {
            log.error("The operation failed due to an error: {}", e.getMessage());
            throw new BLException(null, "The operation failed due to an error: " + e.getMessage());
        }
    }

    public void reportParcelHop(String trackingId, String code) throws BLException {
        ParcelEntity parcelEntity = parcelRepository.findByTrackingId(trackingId);
        if (parcelEntity == null) {
            log.error("Parcel does not exist with this tracking ID {}", trackingId);
            throw new BLDataNotFoundException(null, "Parcel does not exist with this tracking ID: " + trackingId);
        }

        String hopType = hopRepository.findHopTypeByCode(code);
        if (hopType == null) {
            log.error("Hop does not exist with this code {}", code);
            throw new BLDataNotFoundException(null, "Hop does not exist with this code: " + code);
        }

        log.info("Parcel state before change:" + parcelEntity.getState());

        // case hop is type warehouse change parcel state to "INTRANSPORT"
        if (hopType.equals("warehouse")) {
            log.info("HopType is warehouse.");
            parcelEntity.setState(State.INTRANSPORT);
            log.info("Parcel state after change:" + parcelEntity.getState());
        }
        // case hop is type truck change parcel state to "INTRUCKDELIVERY"
        if (hopType.equals("truck")){
            log.info("HopType is truck.");
            parcelEntity.setState(State.INTRUCKDELIVERY);
            log.info("Parcel state after change:" + parcelEntity.getState());
        }
        // case hop is type transferwarehouse
        if (hopType.equals("transferwarehouse")){
            log.info("HopType is transferwarehouse.");

            // TODO: call logistics partner api "POST https://<partnerUrl>/parcel/<trackingid>"
            TransferwarehouseEntity transferwarehouseEntity = transferwarehouseRepository.getTransferwarehouseEntityByCode(code);
            log.info("Logistigs partner url is: " + transferwarehouseEntity.getLogisticsPartnerUrl());

            // change parcel state to TRANSFERRED
            parcelEntity.setState(State.TRANSFERRED);
            log.info("Parcel state after change:" + parcelEntity.getState());
        }

        try {
            // save changed parcel state to in DB
            parcelRepository.setStateToDifferentState(parcelEntity.getState().ordinal(), trackingId);
            log.info("Parcel state after save:" + parcelEntity.getState());
        } catch (Exception e) {
            log.error("The operation failed due to an error: {}", e.getMessage());
            throw new BLException(null, "The operation failed due to an error: " + e.getMessage());
        }
    }

    public NewParcelInfo submitNewParcel(Parcel parcel) throws BLValidationException {
        String trackingId = randomAlphanumericTrackingId();

        // check if tracking id already exists in database
        // generate new tracking id if already exists
        while(parcelRepository.findByTrackingId(trackingId) != null) {
            trackingId = randomAlphanumericTrackingId();
        }
        saveParcelToDB(trackingId, parcel);
        return new NewParcelInfo().trackingId(trackingId);
    }

    public TrackingInformation findParcelByTrackingId(String trackingId) throws BLDataNotFoundException {
        ParcelEntity parcelEntity = parcelRepository.findByTrackingId(trackingId);
        if (parcelEntity != null) {
            log.info("Parcel found with tracking id {}.", trackingId);
            TrackingInformation trackingInformation = ParcelMapper.INSTANCE.parcelEntityToTrackingInformationDto(parcelEntity);
            log.info("ParcelEntity mapped to TrackingInformationDto.");
            log.info("Parcel state: {}", trackingInformation.toString());
            return trackingInformation;
        } else {
            log.error("Parcel does not exist with tracking id: {}", trackingId);
            throw new BLDataNotFoundException(null, "Parcel does not exist with tracking id: " + trackingId);
        }
    }

    public NewParcelInfo transitionParcel(String trackingId, Parcel parcel) throws BLException {

        // check if tracking id already exists in database
        if(parcelRepository.findByTrackingId(trackingId) != null) {
            throw new BLException(null, "Tracking id already in use.");
        }
        saveParcelToDB(trackingId, parcel);
        return new NewParcelInfo().trackingId(trackingId);
    }

    public String randomAlphanumericTrackingId() {
        log.info("In ParcelLogic.randomAlphanumericTrackingId():");
        // generate alphanumeric tracking id that matches the given pattern "^[A-Z0-9]{9}$"
        // https://www.baeldung.com/java-random-string
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 9;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                //.toString()
                .substring(0, 9)
                .toUpperCase();

        if (!generatedString.matches("^[A-Z0-9]{9}$")) {
            throw new IllegalArgumentException("The generated trackingId does not match the pattern \"^[A-Z0-9]{9}$\"");
        }

        log.info("    Generated new random alphanumeric tracking id: {}", generatedString);
        return generatedString;
    }

    public void saveParcelToDB(String trackingId, Parcel parcel) throws BLValidationException {
        ParcelEntity parcelEntity = ParcelMapper.INSTANCE.dtoToEntity(parcel);
        log.info("Parcel mapped to ParcelEntity.");
        RecipientEntity recipientEntity = RecipientMapper.INSTANCE.dtoToEntity(parcel.getRecipient());
        log.info("(recipient) Recipient mapped to RecipientEntity.");
        RecipientEntity senderEntity = RecipientMapper.INSTANCE.dtoToEntity(parcel.getSender());
        log.info("(sender) Recipient mapped to RecipientEntity.");

        // create parcelEntity
        parcelEntity.setTrackingId(trackingId);
        log.info("Tracking id set to {}.", parcelEntity.getTrackingId());
        parcelEntity.setState(State.PICKUP);
        log.info("State set to {}.", parcelEntity.getState().toString());
        parcelEntity.setRecipient(recipientEntity);
        log.info("Recipient set to {}.", parcelEntity.getRecipient());
        parcelEntity.setSender(senderEntity);
        log.info("Sender set to {}.", parcelEntity.getSender());

        //TODO: Get GPS coordinates for package sender/recipient (using Geo Encoding Proxy of your choice)
        //TODO: generate visitedHops and futureHops between sender and recipient via GeoEncodingService()
        parcelEntity.setFutureHops(new ArrayList<HopArrivalEntity>());
        parcelEntity.setVisitedHops(new ArrayList<HopArrivalEntity>());

        try {
            BLValidator.INSTANCE.validate(parcelEntity);
            log.info("ParcelEntity is validated.");
        } catch (BLValidationException e) {
            log.error("BLValidation failed due to an error: {}", e.getMessage());
            throw new BLValidationException(e, "Validation of ParcelEntity failed.");
        }

        // Save to DB
        parcelRepository.save(parcelEntity);
        log.info("parcelEntity hase been saved in database");
    }
}
