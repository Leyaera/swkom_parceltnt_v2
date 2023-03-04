package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.persistence.entities.State;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.persistence.repositories.RecipientRepository;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.mapper.ParcelMapper;
import at.fhtw.swen3.services.mapper.RecipientMapper;
import at.fhtw.swen3.services.validation.BLValidator;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
//@Validated
public class ParcelServiceImpl extends ParcelService {
    @Autowired
    public ParcelServiceImpl(ParcelLogic parcelLogic) {
        super(parcelLogic);
    }

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

    public NewParcelInfo submitParcel(Parcel parcel) {

        // get random tracking ID
        String trackingId = randomAlphanumericTrackingId();
        parcelLogic.setTrackingId(trackingId);
        return parcelLogic.saveNewParcel(parcel);
    }
}
