package at.fhtw.swen3.persistence.entities;

import at.fhtw.swen3.services.validation.City;
import at.fhtw.swen3.services.validation.Name;
import at.fhtw.swen3.services.validation.PostalCode;
import at.fhtw.swen3.services.validation.Street;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
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
    @NotNull(message = "Name can not be null!")
    private String name;

    @Column
    //@Pattern(regexp = "[A-Z ][a-zA-Zß ]+\\s[0-9a-z\\/\\-]+")
    @NotNull(message = "Street can not be null!")
    private String street;

    @Column
    //@Pattern(regexp = "A-+[0-9]{4}")
    @NotNull(message = "Postal code can not be null!")
    private String postalCode;

    @Column
    //@Pattern(regexp = "[A-Z ][a-zA-Z \\/\\-]*")
    @NotNull(message = "City can not be null!")
    private String city;

    @Column
    @NotNull(message = "Country can not be null!")
    private String country;

    public RecipientEntity (String name, String street, String postalCode, String city, String country) {
        this.name = name;
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
    }
}
