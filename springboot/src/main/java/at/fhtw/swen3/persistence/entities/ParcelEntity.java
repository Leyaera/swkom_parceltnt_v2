package at.fhtw.swen3.persistence.entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
public class ParcelEntity {

    @Id
    @Pattern(regexp = "^[A-Z0-9]{9}$")
    private String trackingId;

    @Min(0) //@Positive
    private Float weight;
}
