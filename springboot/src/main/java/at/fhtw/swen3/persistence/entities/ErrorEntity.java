package at.fhtw.swen3.persistence.entities;

import javax.persistence.*;

@Entity
public class ErrorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @Column
    private Long id;

    @Column
    private String errorMessage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
