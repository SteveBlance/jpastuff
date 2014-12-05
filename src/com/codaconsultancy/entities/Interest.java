package com.codaconsultancy.entities;

import com.codaconsultancy.entities.keys.CompoundKey;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "interest")
public class Interest {

    private CompoundKey id;
    private double rate;

    @Id
    public CompoundKey getId() {
        return id;
    }

    public void setId(CompoundKey id) {
        this.id = id;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
