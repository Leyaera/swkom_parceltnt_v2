package at.fhtw.swen3.persistence.entities;

import javax.persistence.*;

@Entity
public class TruckEntity extends HopEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column
    private Long id;

    @Column
    private String regionGeoJson;

    @Column
    private String numberPlate;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getRegionGeoJson() {
        return regionGeoJson;
    }

    public void setRegionGeoJson(String regionGeoJson) {
        this.regionGeoJson = regionGeoJson;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }
}
