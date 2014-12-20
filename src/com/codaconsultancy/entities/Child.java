package com.codaconsultancy.entities;

import javax.persistence.Entity;

@Entity
public class Child extends Parent {

    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
