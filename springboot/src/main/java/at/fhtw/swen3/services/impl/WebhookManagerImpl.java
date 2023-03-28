package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.services.dto.Webhook;
import at.fhtw.swen3.services.exception.BLException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WebhookManagerImpl implements WebhookManager {

    protected final WebhookManagerLogic webhookManagerLogic;

    @Override
    public void registerWebhook(String trackingId) throws BLException {
        webhookManagerLogic.registerWebhook(trackingId);
    }

    @Override
    public List<Webhook> getWebhooksByTrackingId(String trackingId) throws BLException {
        return webhookManagerLogic.getWebhooksByTrackingId(trackingId);
    }

    @Override
    public void deleteWebhookByTrackingId(String trackingId) throws BLException {
        webhookManagerLogic.deleteWebhookByTrackingId(trackingId);
    }
}
