package at.fhtw.swen3.controller.rest;

import at.fhtw.swen3.persistence.entities.State;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.HopArrival;

import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.services.impl.ParcelServiceImpl;
import at.fhtw.swen3.services.impl.RecipientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;
import javax.annotation.Generated;
import javax.sound.midi.Track;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-02-18T16:02:14.309709Z[Etc/UTC]")
@Controller
@RequestMapping("${openapi.parcelLogisticsService.base-path:}")
public class ParcelApiController implements ParcelApi {

    private final NativeWebRequest request;
    private final ParcelServiceImpl parcelServiceImpl;
    private final RecipientServiceImpl recipientServiceImpl;


    @Autowired
    public ParcelApiController(NativeWebRequest request, ParcelServiceImpl parcelServiceImpl, RecipientServiceImpl recipientServiceImpl) {
        this.request = request;
        this.parcelServiceImpl = parcelServiceImpl;
        this.recipientServiceImpl = recipientServiceImpl;
    }

    @Override
    public ResponseEntity<NewParcelInfo> submitParcel(Parcel parcel) {
        NewParcelInfo newParcelInfo = parcelServiceImpl.submitParcel(parcel);
        if(newParcelInfo != null) {
            return new ResponseEntity<NewParcelInfo>(newParcelInfo, HttpStatus.CREATED);
        }
        return new ResponseEntity<NewParcelInfo>(newParcelInfo, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<TrackingInformation> trackParcel(String trackingId) {
        TrackingInformation trackingInformation = parcelServiceImpl.trackParcel(trackingId);
        if(trackingInformation != null) {
            return new ResponseEntity<TrackingInformation>(trackingInformation, HttpStatus.CREATED);
        }
        trackingInformation = new TrackingInformation();
        trackingInformation.setState(TrackingInformation.StateEnum.PICKUP);
        return new ResponseEntity<TrackingInformation>(trackingInformation, HttpStatus.NOT_FOUND);
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }


}
