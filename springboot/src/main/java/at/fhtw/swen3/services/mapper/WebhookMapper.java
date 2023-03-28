package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.WebhookEntity;
import at.fhtw.swen3.services.dto.Webhook;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface WebhookMapper extends BasicMapper<WebhookEntity, Webhook> {
    WebhookMapper INSTANCE = Mappers.getMapper(WebhookMapper.class);

    List<Webhook> dtoListToEntityList(List<WebhookEntity> webhookEntities);
}
