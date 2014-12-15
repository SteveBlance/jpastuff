package com.codaconsultancy.entities;

import javax.persistence.*;

@Entity
@Table(name = "ancestor")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Ancestor {

    private Long id;
    private String nationality;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
