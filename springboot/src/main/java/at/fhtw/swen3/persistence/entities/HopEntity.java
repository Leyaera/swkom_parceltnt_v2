package at.fhtw.swen3.persistence.entities;

import javax.persistence.*;

@Entity
public class HopEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column
    private Long id;

    @Column
    private String hopType;

    @Column
    private String code;

    @Column
    private String description;

    @Column
    private Integer processingDelayMins;

    @Column
    private String locationName;

    @OneToOne
    //@JoinColumn(name = "fk_lo")
    private GeoCoordinateEntity locationCoordinates;

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) { this.id = id; }

    public String getHopType() {
        return hopType;
    }

    public void setHopType(String hopType) {
        this.hopType = hopType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getProcessingDelayMins() {
        return processingDelayMins;
    }

    public void setProcessingDelayMins(Integer processingDelayMins) {
        this.processingDelayMins = processingDelayMins;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    @OneToOne
    @JoinColumn(name = "geo_coordinates_id")
    public GeoCoordinateEntity getLocationCoordinates() {
        return locationCoordinates;
    }

    public void setLocationCoordinates(GeoCoordinateEntity locationCoordinates) {
        this.locationCoordinates = locationCoordinates;
    }
}
