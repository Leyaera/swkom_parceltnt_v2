package at.fhtw.swen3.persistence.entities;

import at.fhtw.swen3.services.dto.GeoCoordinate;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Entity
public class HopEntity {

    @Id
    private Long id;
    private String hopType;

    private String code;

    @Pattern(regexp = "^[A-Z]{4}\\d{1,4}$")
    private String description;

    private Integer processingDelayMins;

    private String locationName;

    @NotNull
    @OneToOne
    private GeoCoordinateEntity locationCoordinates;

    @Id
    public Long getId() {
        return id;
    }
}
