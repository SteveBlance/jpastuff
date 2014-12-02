package com.codaconsultancy.entities;

import javax.persistence.*;

@Entity
@Table(name = "bar")
@SecondaryTable(name = "foo")
public class FooBar {

    int id;
    String fooName;
    String barCode;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(table = "foo")
    public String getFooName() {
        return fooName;
    }

    public void setFooName(String fooName) {
        this.fooName = fooName;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }
}
