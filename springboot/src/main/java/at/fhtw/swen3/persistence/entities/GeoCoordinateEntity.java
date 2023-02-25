package at.fhtw.swen3.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class GeoCoordinateEntity {
    @Id
    private Long id;

    private Double lat;

    private Double lon;


    @Id
    public Long getId() {
        return id;
    }
}
