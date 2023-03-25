package at.fhtw.swen3.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "warehouse_next_hops")
public class WarehouseNextHopsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column
    private long id;

    @Column
    private Integer traveltimeMins;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_to_hop")
    private HopEntity hop;

    /*
    @ManyToOne
    private WarehouseEntity fromHop;

     */
}
