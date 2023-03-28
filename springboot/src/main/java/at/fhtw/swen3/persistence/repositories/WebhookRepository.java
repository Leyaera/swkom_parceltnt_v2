package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.WebhookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WebhookRepository extends JpaRepository<WebhookEntity, Long> {
    List<WebhookEntity> findWebhookEntitiesByParcelTrackingId(String trackingId);
    List<WebhookEntity> findWebhookEntitiesByParcelTrackingIdAndSubscriberType(String trackingId, String subscriberType);
    WebhookEntity findWebhookEntityByParcelTrackingIdAndSubscriberId(String trackingId, Long subscriberId);
}
