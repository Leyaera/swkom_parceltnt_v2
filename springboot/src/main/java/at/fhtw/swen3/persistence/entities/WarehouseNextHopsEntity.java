package at.fhtw.swen3.persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "warehouse_next_hops")
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

    @ManyToOne
    private WarehouseEntity nextHops;

    @ManyToOne
    @JoinColumn(name = "fk_next_hops")
    public WarehouseEntity getNextHops() {
        return nextHops;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getTraveltimeMins() {
        return traveltimeMins;
    }

    public void setTraveltimeMins(Integer traveltimeMins) {
        this.traveltimeMins = traveltimeMins;
    }

    public HopEntity getHop() {
        return hop;
    }

    public void setHop(HopEntity hop) {
        this.hop = hop;
    }

    public void setNextHops(WarehouseEntity nextHops) {
        this.nextHops = nextHops;
    }
}
