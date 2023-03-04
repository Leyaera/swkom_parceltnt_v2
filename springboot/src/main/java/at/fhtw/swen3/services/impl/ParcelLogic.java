package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.persistence.entities.State;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.persistence.repositories.RecipientRepository;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.mapper.ParcelMapper;
import at.fhtw.swen3.services.mapper.RecipientMapper;
import at.fhtw.swen3.services.validation.BLValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Component
public class ParcelLogic {
    private final ParcelRepository parcelRepository;
    private final RecipientLogic recipientLogic;
    private String trackingId = "";

    @Autowired
    public ParcelLogic(ParcelRepository parcelRepository, RecipientLogic recipientLogic) {
        this.parcelRepository = parcelRepository;
        this.recipientLogic = recipientLogic;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    public NewParcelInfo saveNewParcel(Parcel parcel) {
        ParcelEntity parcelEntity = ParcelMapper.INSTANCE.dtoToEntity(parcel);

        // create parcelEntity
        parcelEntity.setTrackingId(trackingId);
        parcelEntity.setState(State.PICKUP);
        parcelEntity.setRecipient(recipientLogic.saveNewRecipient(parcel.getRecipient()));
        parcelEntity.setSender(recipientLogic.saveNewRecipient(parcel.getSender()));
        // TODO: generate visitedHops and futureHops
        parcelEntity.setFutureHops(new ArrayList<HopArrivalEntity>());
        parcelEntity.setVisitedHops(new ArrayList<HopArrivalEntity>());

        if (BLValidator.INSTANCE.validate(parcelEntity)) {
            // Save to DB
            parcelRepository.save(parcelEntity);

            NewParcelInfo newParcelInfo = new NewParcelInfo();
            newParcelInfo.setTrackingId(trackingId);
            return newParcelInfo;
        } else {
            return null;
        }
    }

}
