package at.fhtw.swen3.configuration;

import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.persistence.repositories.RecipientRepository;
import at.fhtw.swen3.services.impl.*;
import at.fhtw.swen3.services.validation.BLValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ParcelService parcelService(ParcelLogic parcelLogic) {
        return new ParcelService(parcelLogic);
    }

    @Bean
    public RecipientService recipientService(RecipientLogic recipientLogic) {
        return new RecipientService(recipientLogic);
    }
}
