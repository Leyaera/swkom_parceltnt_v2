package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.TrackingInformation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ParcelMapper extends BasicMapper<ParcelEntity, Parcel>{

    ParcelMapper INSTANCE = Mappers.getMapper(ParcelMapper.class);

    @Mapping(source = "trackingId", target = "trackingId")
    NewParcelInfo parcelEntityToNewParcelInfoDto(ParcelEntity parcel);

    @Mapping(source = "state", target = "state")
    @Mapping(source = "visitedHops", target = "visitedHops")
    @Mapping(source = "futureHops", target = "futureHops")
    TrackingInformation parcelEntityToTrackingInformationDto(ParcelEntity parcel);
}
