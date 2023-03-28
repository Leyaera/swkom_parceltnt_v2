package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.services.dto.Webhook;
import at.fhtw.swen3.services.exception.BLException;

import java.util.List;

public interface WebhookManager {
    void registerWebhook(String trackingId) throws BLException;

    List<Webhook> getWebhooksByTrackingId(String trackingId) throws BLException;

    void deleteWebhookByTrackingId(String trackingId) throws BLException;
}
