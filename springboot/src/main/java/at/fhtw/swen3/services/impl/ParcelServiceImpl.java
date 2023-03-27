package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.services.exception.BLDataNotFoundException;
import at.fhtw.swen3.services.exception.BLException;
import at.fhtw.swen3.services.exception.BLValidationException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
//@Validated
public class ParcelServiceImpl implements ParcelService {

    protected final ParcelLogic parcelLogic;

    @Override
    public void reportParcelDelivery(String trackingId) throws BLException {
        parcelLogic.reportParcelDelivery(trackingId);
    }
    @Override
    public NewParcelInfo submitParcel(Parcel parcel) throws BLValidationException {
        // get random tracking ID
        return parcelLogic.submitNewParcel(parcel);
    }

    @Override
    public TrackingInformation trackParcel(String trackingId) throws BLDataNotFoundException {
        return parcelLogic.findParcelByTrackingId(trackingId);
    }

    @Override
    public NewParcelInfo transitionParcel(String trackingId, Parcel parcel) throws BLException {
        return parcelLogic.transitionParcel(trackingId, parcel);
    }
}
