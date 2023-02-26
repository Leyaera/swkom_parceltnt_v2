package at.fhtw.swen3.persistence.entities;

import javax.persistence.*;

@Entity
public class TransferwarehouseEntity extends HopEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column
    private Long id;

    @Column
    private String regionGeoJson;

    @Column
    private String logisticsPartner;

    @Column
    private String logisticsPartnerUrl;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
