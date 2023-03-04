package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.services.dto.Recipient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipientServiceImpl extends RecipientService {
    @Autowired
    public RecipientServiceImpl(RecipientLogic recipientLogic) {
        super(recipientLogic);
    }

    public RecipientEntity saveNewRecipient(Recipient recipient) {
        return recipientLogic.saveNewRecipient(recipient);
    }
}
