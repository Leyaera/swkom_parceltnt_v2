package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.persistence.repositories.RecipientRepository;
import at.fhtw.swen3.services.dto.Recipient;
import at.fhtw.swen3.services.mapper.RecipientMapper;
import at.fhtw.swen3.services.validation.BLValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecipientLogic {

    private final RecipientRepository recipientRepository;

    @Autowired
    public RecipientLogic(RecipientRepository recipientRepository) {
        this.recipientRepository = recipientRepository;
    }

    public RecipientEntity saveNewRecipient(Recipient recipient) {
        RecipientEntity recipientEntity = RecipientMapper.INSTANCE.dtoToEntity(recipient);
        if (BLValidator.INSTANCE.validate(recipientEntity)) {
            // Save to DB
            recipientRepository.save(recipientEntity);
            return recipientEntity;
        } else {
            return null;
        }
    }
}
