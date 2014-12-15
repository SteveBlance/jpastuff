package com.codaconsultancy.entities;

import javax.persistence.*;

@Entity
@Table(name = "exam")
public class Exam {

    private int id;
    private String shortName;
    private ExamDetail detail;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "detail_id")
    public ExamDetail getDetail() {
        return detail;
    }

    public void setDetail(ExamDetail detail) {
        this.detail = detail;
    }
}
