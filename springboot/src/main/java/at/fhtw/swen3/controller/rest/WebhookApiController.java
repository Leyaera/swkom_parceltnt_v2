package at.fhtw.swen3.controller.rest;

import at.fhtw.swen3.persistence.entities.WebhookEntity;
import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.services.dto.Webhook;
import at.fhtw.swen3.services.exception.BLException;
import at.fhtw.swen3.services.impl.ParcelService;
import at.fhtw.swen3.services.impl.WebhookManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("${openapi.parcelLogisticsService.base-path:}")
public class WebhookApiController implements WebhookApi {

    private final WebhookManager webhookManager;

    @Autowired
    public WebhookApiController(WebhookManager webhookManager) {
        this.webhookManager = webhookManager;
    }

    @Override
    public ResponseEntity<Void> registerWebhook(String trackingId) {
        try {
            try {
                webhookManager.registerWebhook(trackingId);
                log.info("Successfully submitted webhook.");
                return new ResponseEntity<Void>(HttpStatus.CREATED);
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
    public ResponseEntity<List<Webhook>> getWebhooks(String trackingId) {
        try {
            try {
                List<Webhook> webhooks = webhookManager.getWebhooksByTrackingId(trackingId);
                log.info("Successfully retrieved webhooks.");
                return new ResponseEntity<List<Webhook>>(webhooks, HttpStatus.OK);
            } catch (BLException e) {
                log.error("Parcel does not exist with this tracking ID: {}", e.getMessage());
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("The operation failed due to an error: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Void> deleteWebhook(String trackingId) {
        try {
            try {
                webhookManager.deleteWebhookByTrackingId(trackingId);
                log.info("Successfully deleted webhook.");
                return new ResponseEntity<Void>(HttpStatus.OK);
            } catch (BLException e) {
                log.error("Parcel does not exist with this tracking ID: {}", e.getMessage());
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("The operation failed due to an error: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
