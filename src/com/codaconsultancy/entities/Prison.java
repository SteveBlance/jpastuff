package com.codaconsultancy.entities;

import com.codaconsultancy.entities.keys.CompoundKey;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "prison")
public class Prison {

    private CompoundKey key;
    private String city;

    @EmbeddedId
    public CompoundKey getKey() {
        return key;
    }

    public void setKey(CompoundKey key) {
        this.key = key;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
