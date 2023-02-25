package at.fhtw.swen3.persistence.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.OffsetDateTime;

@Getter @Setter
@Entity
public class HopArrivalEntity {
    //^[A-Z]{4}\\d{1,4}$ - https://stackoverflow.com/questions/14017134/what-is-d-d-in-regex

    @Id
    private Long id;
    @Pattern(regexp = "^[A-Z]{4}[0-9]{1,4}$")
    //@NotNull(message = "Code can not be null!")
    private String code;

    @Pattern(regexp = "[A-Z ][a-zA-Z0-9 \\/\\-]*")
    //@NotNull(message = "Description can not be null!")
    private String description;

    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    //@NotNull(message = "Date/Time can not be null!")
    private OffsetDateTime dateTime;

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
}
