package at.fhtw.swen3.persistence.entities;

import javax.persistence.*;

@Entity
public class GeoCoordinateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column
    public Long id;

    @Column
    private Double lat;

    @Column
    private Double lon;

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }
}
