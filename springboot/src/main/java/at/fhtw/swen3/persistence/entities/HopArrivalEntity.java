package at.fhtw.swen3.persistence.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
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
    @NotNull(message = "Code can not be null!")
    private String code;

    @Column
    @Pattern(regexp = "[A-Z ][a-zA-Z0-9 \\/\\-]*")
    @NotNull(message = "Description can not be null!")
    private String description;

    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotNull(message = "Date/Time can not be null!")
    private OffsetDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "fk_visited_hops")
    private ParcelEntity visitedHops;

    @ManyToOne
    @JoinColumn(name = "fk_future_hops")
    private ParcelEntity futureHops;

    public HopArrivalEntity(String code, String description, OffsetDateTime dateTime) {
        this.code = code;
        this.description = description;
        this.dateTime = dateTime;
    }
}
