package com.codaconsultancy.entities;

import javax.persistence.Entity;

@Entity
public class Parent extends Ancestor {

    private String lastName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
