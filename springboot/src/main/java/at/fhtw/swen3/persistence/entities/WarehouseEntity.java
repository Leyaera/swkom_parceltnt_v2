package at.fhtw.swen3.persistence.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class WarehouseEntity extends HopEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column
    private Long id;

    @Column
    private Integer level;

    @OneToMany
    @NotNull
    private List<WarehouseNextHopsEntity> nextHops;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @OneToMany
    @JoinColumn(name = "next_hops_id")
    public List<WarehouseNextHopsEntity> getNextHops() {
        return nextHops;
    }

    public void setNextHops(List<WarehouseNextHopsEntity> nextHops) {
        this.nextHops = nextHops;
    }
}
