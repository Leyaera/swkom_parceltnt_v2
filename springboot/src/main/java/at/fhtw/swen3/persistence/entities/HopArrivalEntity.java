package at.fhtw.swen3.persistence.entities;

import javax.persistence.*;

import javax.validation.constraints.Pattern;

import java.time.OffsetDateTime;

@Entity
@Table(name = "hop_arrival_entity")
public class HopArrivalEntity {
    //^[A-Z]{4}\\d{1,4}$ - https://stackoverflow.com/questions/14017134/what-is-d-d-in-regex

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column
    private Long id;

    @Column
    @Pattern(regexp = "^[A-Z]{4}[0-9]{1,4}$")
    //@NotNull(message = "Code can not be null!")
    private String code;

    @Column
    @Pattern(regexp = "[A-Z ][a-zA-Z0-9 \\/\\-]*")
    //@NotNull(message = "Description can not be null!")
    private String description;

    @Column
    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    //@NotNull(message = "Date/Time can not be null!")
    private OffsetDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "fk_visited_hops")
    private ParcelEntity visitedHops;

    @ManyToOne
    @JoinColumn(name = "fk_future_hops")
    private ParcelEntity futureHops;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OffsetDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(OffsetDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @ManyToOne
    @JoinColumn(name = "fk_visited_hops")
    public ParcelEntity getVisitedHops() {
        return visitedHops;
    }

    public void setVisitedHops(ParcelEntity visitedHops) {
        this.visitedHops = visitedHops;
    }

    @ManyToOne
    @JoinColumn(name = "fk_future_hops")
    public ParcelEntity getFutureHops() {
        return futureHops;
    }

    public void setFutureHops(ParcelEntity futureHops) {
        this.futureHops = futureHops;
    }
}
