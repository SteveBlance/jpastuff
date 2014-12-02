package com.codaconsultancy.entities;

import javax.persistence.*;

@Entity
@Table(name = "thing")
public class Thing {

    private long id;
    private String name;
    private ThingDetail thingDetail;

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Embedded
    public ThingDetail getThingDetail() {
        return thingDetail;
    }

    public void setThingDetail(ThingDetail thingDetail) {
        this.thingDetail = thingDetail;
    }
}
