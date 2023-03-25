package at.fhtw.swen3.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Entity
public class WarehouseEntity extends HopEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column
    private Long id;

    @Column
    private Integer level;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_from_hop")
    @NotNull
    private List<WarehouseNextHopsEntity> nextHops;
}
