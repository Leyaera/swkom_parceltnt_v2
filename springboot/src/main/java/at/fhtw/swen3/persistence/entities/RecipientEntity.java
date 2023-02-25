package at.fhtw.swen3.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class RecipientEntity {
    @Id
    private Long id;

    private String name;
    private String street;
    private String postalCode;
    private String city;
    private String country;

    @Id
    public Long getId() {
        return id;
    }
}
