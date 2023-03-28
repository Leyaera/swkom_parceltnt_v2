package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.services.dto.GeoCoordinate;
import at.fhtw.swen3.services.dto.Recipient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RecipientMapperTest {
    @Test
    @DisplayName("Test RecipientEntity to RecipientDto.")
    void testEntityToDto()
    {
        RecipientEntity recipientEntity = new RecipientEntity();
        recipientEntity.setName("Recipient Name");
        recipientEntity.setStreet("Recipient Street 123");
        recipientEntity.setCity("Recipient City");
        recipientEntity.setCity("RecipientCountry");
        recipientEntity.setPostalCode("R-12345");

        Recipient recipient = RecipientMapper.INSTANCE.entityToDto(recipientEntity);

        assertEquals(recipientEntity.getName(), recipient.getName());
        assertEquals(recipientEntity.getStreet(), recipient.getStreet());
        assertEquals(recipientEntity.getPostalCode(), recipient.getPostalCode());
        assertEquals(recipientEntity.getCountry(), recipient.getCountry());
        assertEquals(recipientEntity.getCity(), recipient.getCity());
    }

    @Test
    @DisplayName("Test RecipientDto to RecipientEntity.")
    void testDtoToEntity()
    {
        Recipient recipient = new Recipient();
        recipient.setName("Recipient Name");
        recipient.setStreet("Recipient Street 123");
        recipient.setCity("Recipient City");
        recipient.setCity("RecipientCountry");
        recipient.setPostalCode("R-12345");

        RecipientEntity recipientEntity = RecipientMapper.INSTANCE.dtoToEntity(recipient);

        assertEquals(recipientEntity.getName(), recipient.getName());
        assertEquals(recipientEntity.getStreet(), recipient.getStreet());
        assertEquals(recipientEntity.getPostalCode(), recipient.getPostalCode());
        assertEquals(recipientEntity.getCountry(), recipient.getCountry());
        assertEquals(recipientEntity.getCity(), recipient.getCity());
    }
}