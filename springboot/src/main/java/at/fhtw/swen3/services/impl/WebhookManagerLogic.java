package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.persistence.entities.WebhookEntity;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.persistence.repositories.RecipientRepository;
import at.fhtw.swen3.persistence.repositories.WebhookRepository;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.services.dto.Webhook;
import at.fhtw.swen3.services.exception.BLDataNotFoundException;
import at.fhtw.swen3.services.exception.BLException;
import at.fhtw.swen3.services.mapper.WebhookMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import java.util.List;

@Slf4j
@Component
public class WebhookManagerLogic {

    private final WebhookRepository webhookRepository;
    private final ParcelRepository parcelRepository;

    private final RecipientRepository recipientRepository;

    @Autowired
    public WebhookManagerLogic(WebhookRepository webhookRepository, ParcelRepository parcelRepository, RecipientRepository recipientRepository) {
        this.webhookRepository = webhookRepository;
        this.parcelRepository = parcelRepository;
        this.recipientRepository = recipientRepository;
    }

    public void registerWebhook(String trackingId) throws BLException {
        // find parcel in db
        ParcelEntity parcelEntity = parcelRepository.findByTrackingId(trackingId);
        /*if(parcelEntity == null) {
            log.error("Parcel does not exist with this tracking ID: " + trackingId);
            throw new BLDataNotFoundException(null, "Parcel does not exist with this tracking ID: " + trackingId);
        }*/

        // add recipient subscriber and save to db
        if(webhookRepository.findWebhookEntityByParcelTrackingIdAndSubscriberId(trackingId, parcelEntity.getRecipient().getId()) == null) {
            WebhookEntity webhookEntityRecipient = new WebhookEntity();
            webhookEntityRecipient.setParcelTrackingId(trackingId);
            webhookEntityRecipient.setSubscriberType("recipient");
            webhookEntityRecipient.setSubscriberId(parcelEntity.getRecipient().getId());
            webhookRepository.save(webhookEntityRecipient);
        }

        // add sender as subscriber and save to db
        if(webhookRepository.findWebhookEntityByParcelTrackingIdAndSubscriberId(trackingId, parcelEntity.getSender().getId()) == null) {
            WebhookEntity webhookEntitySender = new WebhookEntity();
            webhookEntitySender.setParcelTrackingId(trackingId);
            webhookEntitySender.setSubscriberType("recipient");
            webhookEntitySender.setSubscriberId(parcelEntity.getSender().getId());
            webhookRepository.save(webhookEntitySender);
        }

        // TODO: add hops as subscribers
    }

    public List<Webhook> getWebhooksByTrackingId(String trackingId) throws BLException {
        List<WebhookEntity> webhookEntities = webhookRepository.findWebhookEntitiesByParcelTrackingId(trackingId);
        if (webhookEntities.isEmpty()) {
            log.error("Webhook does not exist with this tracking ID: " + trackingId);
            throw new BLDataNotFoundException(null, "Webhook does not exist with this tracking ID: " + trackingId);
        }

        // map WebhookEntities
        List<Webhook> webhooks = WebhookMapper.INSTANCE.dtoListToEntityList(webhookEntities);
        log.info("List of WebhookEntities mapped to list of WebhookDtos.");
        return webhooks;
    }

    public void deleteWebhookByTrackingId(String trackingId) throws BLException {
        List<WebhookEntity> webhookEntities = webhookRepository.findWebhookEntitiesByParcelTrackingId(trackingId);
        if (webhookEntities.isEmpty()) {
            log.error("Webhook does not exist with this tracking ID: " + trackingId);
            throw new BLDataNotFoundException(null, "Webhook does not exist with this tracking ID: " + trackingId);
        }

        // delete every webhook associated with given trackingId
        for (WebhookEntity wE : webhookEntities) {
            webhookRepository.delete(wE);
        }
        log.info("Webhooks associated with trackingId {} successfully deleted.", trackingId );
    }
}
