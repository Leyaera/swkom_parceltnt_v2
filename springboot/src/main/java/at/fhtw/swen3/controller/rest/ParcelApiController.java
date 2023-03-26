package at.fhtw.swen3.controller.rest;

import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.services.exception.BLDataNotFoundException;
import at.fhtw.swen3.services.exception.BLValidationException;
import at.fhtw.swen3.services.impl.ParcelServiceImpl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;

import javax.annotation.Generated;

@Slf4j
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-02-18T16:02:14.309709Z[Etc/UTC]")
@Controller
@RequestMapping("${openapi.parcelLogisticsService.base-path:}")
public class ParcelApiController implements ParcelApi {

    private final NativeWebRequest request;
    private final ParcelServiceImpl parcelServiceImpl;

    @Autowired
    public ParcelApiController(NativeWebRequest request, ParcelServiceImpl parcelServiceImpl) {
        this.request = request;
        this.parcelServiceImpl = parcelServiceImpl;
    }

    @Override
    public ResponseEntity<NewParcelInfo> submitParcel(Parcel parcel) {
        try {
            try {
                NewParcelInfo newParcelInfo = parcelServiceImpl.submitParcel(parcel);
                if(newParcelInfo != null) {
                    log.info("Successfully submitted new parcel.");
                    return new ResponseEntity<NewParcelInfo>(newParcelInfo, HttpStatus.CREATED);
                }
            } catch (BLValidationException e) {
                log.error("BLValidation failed: {}", e.getMessage());
                return new ResponseEntity<NewParcelInfo>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            log.error("Request failed due to an error: {}", e.getMessage());
            return new ResponseEntity<NewParcelInfo>(HttpStatus.NOT_FOUND);
        }
        return null;
    }

    @Override
    public ResponseEntity<TrackingInformation> trackParcel(String trackingId) {
        try {
            try {
                TrackingInformation trackingInformation = parcelServiceImpl.trackParcel(trackingId);
                log.info("Parcel with tracking id {} exists.", trackingId);
                return new ResponseEntity<TrackingInformation>(trackingInformation, HttpStatus.OK);
            } catch (BLDataNotFoundException e) {
                log.error("BLDataNotFoundException: {}", e.getMessage());
                return new ResponseEntity<TrackingInformation>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Request failed due to an error: {}", e.getMessage());
            return new ResponseEntity<TrackingInformation>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }


}
