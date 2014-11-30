package com.codaconsultancy.entities;

import javax.persistence.*;

@Entity
@Table(name = "snafu", schema = "examscam")
public class Snafu {

    long id;
    String situation;

    @Id
    @GeneratedValue
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "situation")
    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }
}
