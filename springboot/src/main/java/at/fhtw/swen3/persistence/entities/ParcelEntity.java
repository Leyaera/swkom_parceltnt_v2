package at.fhtw.swen3.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
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

    public ParcelEntity(String trackingId, Float weight, RecipientEntity recipient, RecipientEntity sender, State state, List<HopArrivalEntity> visitedHops, List<HopArrivalEntity> futureHops) {
        this.trackingId = trackingId;
        this.weight = weight;
        this.recipient = recipient;
        this.sender = sender;
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

    public List<HopArrivalEntity> getVisitedHops() {
        return visitedHops;
    }

    public void setVisitedHops(List<HopArrivalEntity> visitedHops) {
        this.visitedHops = visitedHops;
    }

    public List<HopArrivalEntity> getFutureHops() {
        return futureHops;
    }

    public void setFutureHops(List<HopArrivalEntity> futureHops) {
        this.futureHops = futureHops;
    }
}
