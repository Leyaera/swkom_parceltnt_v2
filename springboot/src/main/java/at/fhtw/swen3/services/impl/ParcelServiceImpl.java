package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.State;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.persistence.repositories.RecipientRepository;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.mapper.ParcelMapper;
import at.fhtw.swen3.services.validation.BLValidator;
import lombok.RequiredArgsConstructor;

import java.util.Random;

@RequiredArgsConstructor
public class ParcelServiceImpl implements ParcelService {
    private final BLValidator blValidatior;
    private final ParcelRepository parcelRepository;
    private final RecipientRepository recipientRepository;

    public String randomAlphanumericTrackingId() {
        // generate alphanumeric tracking id that matches the given pattern "^[A-Z0-9]{9}$"
        // https://www.baeldung.com/java-random-string
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 9;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                //.toString()
                .substring(0, 9)
                .toUpperCase();
        System.out.println(generatedString);
        return generatedString;
    }

    @Override
    public NewParcelInfo submitParcel(Parcel parcel) {

        // DTO to Entity
        ParcelEntity parcelEntity = ParcelMapper.INSTANCE.dtoToEntity(parcel);

        // get random tracking ID
        String trackingId = randomAlphanumericTrackingId();
        parcelEntity.setTrackingId(trackingId);
        parcelEntity.setState(State.PICKUP);

        if (blValidatior.validate(parcelEntity)) {
            recipientRepository.save(parcelEntity.getRecipient());
            recipientRepository.save(parcelEntity.getSender());
            parcelRepository.save(parcelEntity);
            NewParcelInfo newParcelInfo = new NewParcelInfo();
            newParcelInfo.setTrackingId(parcelEntity.getTrackingId());
            return newParcelInfo;
        }
        return null;
    }
}
