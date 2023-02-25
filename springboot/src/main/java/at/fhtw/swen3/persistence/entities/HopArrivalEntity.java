package at.fhtw.swen3.persistence.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import java.time.OffsetDateTime;

@Entity
public class HopArrivalEntity {
    //^[A-Z]{4}\\d{1,4}$ - https://stackoverflow.com/questions/14017134/what-is-d-d-in-regex
    @Pattern(regexp = "^[A-Z]{4}[0-9]{1,4}$")
    private String code;
    private String description;
    private OffsetDateTime dateTime;
    private Long id;

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
