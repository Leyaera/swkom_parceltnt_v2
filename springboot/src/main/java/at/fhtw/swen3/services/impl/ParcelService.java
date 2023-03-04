package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.services.dto.TrackingInformation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ParcelService {
    protected final ParcelLogic parcelLogic;

    public TrackingInformation trackParcel(String trackingId) {
        return parcelLogic.findParcelByTrackingId(trackingId);
    }

}
