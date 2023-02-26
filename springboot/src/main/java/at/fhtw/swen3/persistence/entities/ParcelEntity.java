package at.fhtw.swen3.persistence.entities;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

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

    @OneToOne
    @JoinColumn(name = "fk_recipient")
    @NotNull(message = "Recipient can not be null!")
    private RecipientEntity recipient;

    @OneToOne
    @JoinColumn(name = "fk_sender")
    @NotNull(message = "Sender can not be null!")
    private RecipientEntity sender;

    @Column
    @Pattern(regexp = "^[A-Z0-9]{9}$", message = "tracking id has a length of 9 must only be upper letters and numbers.")
    //@NotNull(message = "Tracking-ID can not be null!")
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

    public ParcelEntity() {
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public RecipientEntity getRecipient() {
        return recipient;
    }

    public void setRecipient(RecipientEntity recipient) {
        this.recipient = recipient;
    }

    public RecipientEntity getSender() {
        return sender;
    }

    public void setSender(RecipientEntity sender) {
        this.sender = sender;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @OneToMany(mappedBy = "visitedHops")
    public List<HopArrivalEntity> getVisitedHops() {
        return visitedHops;
    }

    public void setVisitedHops(List<HopArrivalEntity> visitedHops) {
        this.visitedHops = visitedHops;
    }

    @OneToMany(mappedBy = "futureHops")
    public List<HopArrivalEntity> getFutureHops() {
        return futureHops;
    }

    public void setFutureHops(List<HopArrivalEntity> futureHops) {
        this.futureHops = futureHops;
    }
}
