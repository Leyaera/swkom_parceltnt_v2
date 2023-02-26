package at.fhtw.swen3.persistence.entities;

import javax.persistence.*;

@Entity
public class WarehouseNextHopsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column
    private long id;

    @Column
    private Integer traveltimeMins;

    @OneToOne
    @JoinColumn(name = "hop_id")
    private HopEntity hop;
}
