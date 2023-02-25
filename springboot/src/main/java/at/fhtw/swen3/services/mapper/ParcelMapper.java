package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.TrackingInformation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

public interface ParcelMapper {

    ParcelMapper INSTANCE = Mappers.getMapper(ParcelMapper.class);

    @Mapping(source = "trackingId", target = "trackingId")
    NewParcelInfo parcelEntityToNewParcelInfoDto(ParcelEntity parcel);


    @Mapping(source = "weight", target = "weight")
    @Mapping(source = "recipient", target = "recipient")
    @Mapping(source = "sender", target = "sender")
    TrackingInformation parcelEntityToTrackingInformationDto(ParcelEntity parcel);

    @Mapping(source = "weight", target = "weight")
    @Mapping(source = "recipient", target = "recipient")
    @Mapping(source = "sender", target = "sender")
    Parcel parcelEntityToParcelDto(ParcelEntity parcel);

    @Mapping(source = "weight", target = "weight")
    @Mapping(source = "recipient", target = "recipient")
    @Mapping(source = "sender", target = "sender")
    ParcelEntity parcelDtoToParcelEntity(at.fhtw.swen3.services.dto.Parcel parcel);


}
