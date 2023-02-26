package at.fhtw.swen3.persistence.entities;

import at.fhtw.swen3.services.validation.City;
import at.fhtw.swen3.services.validation.Name;
import at.fhtw.swen3.services.validation.PostalCode;
import at.fhtw.swen3.services.validation.Street;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@PostalCode
@City
@Street
@Name
@Entity
@Table(name = "recipient")
public class RecipientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column
    private Long id;

    @Column
    //@Pattern(regexp = "[A-Z ][a-zA-Z \\/\\-]*")
    //@NotNull(message = "Name can not be null!")
    private String name;

    @Column
    //@Pattern(regexp = "[A-Z ][a-zA-Zß ]+\\s[0-9a-z\\/\\-]+")
    //@NotNull(message = "Street can not be null!")
    private String street;

    @Column
    //@Pattern(regexp = "A-+[0-9]{4}")
    //@NotNull(message = "Postal code can not be null!")
    private String postalCode;

    @Column
    //@Pattern(regexp = "[A-Z ][a-zA-Z \\/\\-]*")
    //@NotNull(message = "City can not be null!")
    private String city;

    @Column
    //@NotNull(message = "Country can not be null!")
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
