package at.fhtw.swen3.persistence.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "parcel")
public class ParcelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column
    private Long id;

    @Column
    @DecimalMin(value = "1.0",message ="weight must be at least 1.0")
    @NotNull(message = "Weight can not be null!")
    private Float weight;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_recipient")
    @NotNull(message = "Recipient can not be null!")
    private RecipientEntity recipient;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_sender")
    @NotNull(message = "Sender can not be null!")
    private RecipientEntity sender;

    @Column
    @Pattern(regexp = "^[A-Z0-9]{9}$", message = "tracking id has a length of 9 must only be upper letters and numbers.")
    @NotNull(message = "Tracking-ID can not be null!")
    private String trackingId;

    @Column
    @NotNull(message = "State can not be null!")
    private State state;

    @OneToMany(mappedBy = "visitedHops")
    @NotNull
    private List<HopArrivalEntity> visitedHops;

    @OneToMany(mappedBy = "futureHops")
    @NotNull
    private List<HopArrivalEntity> futureHops;

    public ParcelEntity(Float weight, RecipientEntity recipient, RecipientEntity sender, String trackingId, State state, List<HopArrivalEntity> visitedHops, List<HopArrivalEntity> futureHops) {
        this.weight = weight;
        this.recipient = recipient;
        this.sender = sender;
        this.trackingId = trackingId;
        this.state = state;
        this.visitedHops = visitedHops;
        this.futureHops = futureHops;
    }
}
