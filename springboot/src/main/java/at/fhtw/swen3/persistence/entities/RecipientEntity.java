package at.fhtw.swen3.persistence.entities;

import at.fhtw.swen3.services.validation.City;
import at.fhtw.swen3.services.validation.Name;
import at.fhtw.swen3.services.validation.PostalCode;
import at.fhtw.swen3.services.validation.Street;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;


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

    public String getCountry() {
        return this.country;
    }

    @Id
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
