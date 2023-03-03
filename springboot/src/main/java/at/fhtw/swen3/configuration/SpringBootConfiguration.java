package at.fhtw.swen3.configuration;

import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.persistence.repositories.RecipientRepository;
import at.fhtw.swen3.services.impl.ParcelServiceImpl;
import at.fhtw.swen3.services.validation.BLValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBootConfiguration {
    @Bean
    public ParcelServiceImpl parcelService(ParcelRepository parcelRepository, BLValidator blValidator, RecipientRepository recipientRepository) {
        return new ParcelServiceImpl(blValidator, parcelRepository, recipientRepository);
    }
}
