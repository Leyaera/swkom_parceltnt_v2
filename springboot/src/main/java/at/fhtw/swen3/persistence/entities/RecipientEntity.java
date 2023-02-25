package at.fhtw.swen3.persistence.entities;

import at.fhtw.swen3.services.validation.City;
import at.fhtw.swen3.services.validation.Name;
import at.fhtw.swen3.services.validation.PostalCode;
import at.fhtw.swen3.services.validation.Street;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@PostalCode
@City
@Street
@Name
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
