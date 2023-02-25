package at.fhtw.swen3.persistence.entities;

import at.fhtw.swen3.services.dto.HopArrival;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ParcelEntity {
    @Id
    @Pattern(regexp = "^[A-Z0-9]{9}$")
    private String trackingId;

    @Min(0) //@Positive
    private Float weight;

    @OneToOne
    @NotNull
    private RecipientEntity recipient;

    @OneToOne
    @NotNull
    private RecipientEntity sender;

    private State state;

    @OneToMany
    @NotNull
    private List<HopArrivalEntity> visitedHops;

    @OneToMany
    @NotNull
    private List<HopArrivalEntity> futureHops;
}
