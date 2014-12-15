package com.codaconsultancy.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "parent")
public class Parent extends Ancestor {

    private String lastName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
