package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.persistence.entities.State;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.persistence.repositories.RecipientRepository;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.services.exception.BLDataNotFoundException;
import at.fhtw.swen3.services.exception.BLException;
import at.fhtw.swen3.services.exception.BLValidationException;
import at.fhtw.swen3.services.mapper.ParcelMapper;
import at.fhtw.swen3.services.mapper.RecipientMapper;
import at.fhtw.swen3.services.validation.BLValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;

@Slf4j
@Component
public class ParcelLogic {
    private final ParcelRepository parcelRepository;

    @Autowired
    public ParcelLogic(ParcelRepository parcelRepository) {
        this.parcelRepository = parcelRepository;
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

    public NewParcelInfo saveNewParcel(Parcel parcel) throws BLValidationException {
        ParcelEntity parcelEntity = ParcelMapper.INSTANCE.dtoToEntity(parcel);
        log.info("Parcel mapped to ParcelEntity.");
        RecipientEntity recipientEntity = RecipientMapper.INSTANCE.dtoToEntity(parcel.getRecipient());
        log.info("(recipient) Recipient mapped to RecipientEntity.");
        RecipientEntity senderEntity = RecipientMapper.INSTANCE.dtoToEntity(parcel.getSender());
        log.info("(sender) Recipient mapped to RecipientEntity.");

        // create parcelEntity
        String trackingId = randomAlphanumericTrackingId();
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
        NewParcelInfo newParcelInfo = new NewParcelInfo();
        newParcelInfo.setTrackingId(trackingId);
        return newParcelInfo;
    }

    public TrackingInformation findParcelByTrackingId(String trackingId) throws BLDataNotFoundException {
        log.info("In ParcelLogic.findParcelByTrackingId():");
        ParcelEntity parcelEntity = parcelRepository.findByTrackingId(trackingId);
        if (parcelEntity != null) {
            log.info("    Parcel found with tracking id {}.", trackingId);
            TrackingInformation trackingInformation = ParcelMapper.INSTANCE.parcelEntityToTrackingInformationDto(parcelEntity);
            log.info("    ParcelEntity mapped to TrackingInformationDto.");
            log.info("    Parcel state: {}", trackingInformation.toString());
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

        ParcelEntity parcelEntity = ParcelMapper.INSTANCE.dtoToEntity(parcel);
        log.info("Parcel mapped to ParcelEntity.");
        RecipientEntity recipientEntity = RecipientMapper.INSTANCE.dtoToEntity(parcel.getRecipient());
        log.info("(recipient) Recipient mapped to RecipientEntity.");
        RecipientEntity senderEntity = RecipientMapper.INSTANCE.dtoToEntity(parcel.getSender());
        log.info("(sender) Recipient mapped to RecipientEntity.");

        // create parcelEntity
        // reuse existing tracking id
        parcelEntity.setTrackingId(trackingId);
        log.info("Tracking id set to {}.", parcelEntity.getTrackingId());
        // set parcel state to "TRANSFERRED"
        parcelEntity.setState(State.TRANSFERRED);
        log.info("Parcel state set to {}.", parcelEntity.getState().toString());
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
        return new NewParcelInfo().trackingId(trackingId);
    }
}
