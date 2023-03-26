package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.services.exception.BLDataNotFoundException;
import at.fhtw.swen3.services.exception.BLValidationException;
import lombok.RequiredArgsConstructor;


public interface ParcelService {

    public NewParcelInfo submitParcel(Parcel parcel) throws BLValidationException;
    public TrackingInformation trackParcel(String trackingId) throws BLDataNotFoundException;

}
