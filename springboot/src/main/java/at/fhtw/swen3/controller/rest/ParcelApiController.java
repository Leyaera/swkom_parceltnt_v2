package at.fhtw.swen3.controller.rest;

import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.services.exception.BLDataNotFoundException;
import at.fhtw.swen3.services.exception.BLException;
import at.fhtw.swen3.services.exception.BLValidationException;
import at.fhtw.swen3.services.impl.ParcelService;
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
    private final ParcelService parcelService;

    @Autowired
    public ParcelApiController(NativeWebRequest request, ParcelService parcelService) {
        this.request = request;
        this.parcelService = parcelService;
    }

    @Override
    public ResponseEntity<Void> reportParcelDelivery(String trackingId) {
        try {
            try {
                parcelService.reportParcelDelivery(trackingId);
                log.info("Successfully reported hop.");
                return new ResponseEntity<Void>(HttpStatus.OK);
            } catch (BLException e) {
                log.error("Parcel does not exist with this tracking ID: {}", e.getMessage());
                return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("The operation failed due to an error: {}", e.getMessage());
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Void> reportParcelHop(String trackingId, String code) {
        try {
            try {
                parcelService.reportParcelHop(trackingId, code);
                log.info("Successfully reported hop.");
                return new ResponseEntity<Void>(HttpStatus.OK);
            } catch (BLException e) {
                log.error("Parcel does not exist with this tracking ID or hop with code not found. : {}", trackingId, e.getMessage());
                return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("The operation failed due to an error: {}", e.getMessage());
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<NewParcelInfo> submitParcel(Parcel parcel) {
        try {
            try {
                NewParcelInfo newParcelInfo = parcelService.submitParcel(parcel);
                if(newParcelInfo != null) {
                    log.info("Successfully submitted new parcel.");
                    return new ResponseEntity<NewParcelInfo>(newParcelInfo, HttpStatus.CREATED);
                }
            } catch (BLValidationException e) {
                log.error("The operation failed due to an error: {}", e.getMessage());
                return new ResponseEntity<NewParcelInfo>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            log.error("The address of sender or receiver was not found: {}", e.getMessage());
            return new ResponseEntity<NewParcelInfo>(HttpStatus.NOT_FOUND);
        }
        return null;
    }

    @Override
    public ResponseEntity<TrackingInformation> trackParcel(String trackingId) {
        try {
            try {
                TrackingInformation trackingInformation = parcelService.trackParcel(trackingId);
                log.info("Successfully transitioned the parcel", trackingId);
                return new ResponseEntity<TrackingInformation>(trackingInformation, HttpStatus.OK);
            } catch (BLDataNotFoundException e) {
                log.error("Parcel does not exist with this tracking ID: {}", e.getMessage());
                return new ResponseEntity<TrackingInformation>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("The operation failed due to an error: {}", e.getMessage());
            return new ResponseEntity<TrackingInformation>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<NewParcelInfo> transitionParcel (String trackingId, Parcel parcel) {
        NewParcelInfo newParcelInfo = null;
        try {
            newParcelInfo = parcelService.transitionParcel(trackingId, parcel);
            return new ResponseEntity<NewParcelInfo>(newParcelInfo, HttpStatus.OK);
        } catch (BLException e) {
            log.error("A parcel with the specified trackingID is already in the system: {}", e.getMessage());
            return new ResponseEntity<NewParcelInfo>(newParcelInfo, HttpStatus.CONFLICT);
        } catch (Exception e) {
            log.error("The operation failed due to an error: {}", e.getMessage());
            return new ResponseEntity<NewParcelInfo>(newParcelInfo, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }


}
