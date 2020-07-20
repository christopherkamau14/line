package com.high.school.students.model;

import javax.persistence.*;

@Entity
@Table(name="calendar_years")
public class Year {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "year_id")
    private Long yearCode;

    @Column(name = "year_name")
    private String yearName;

    @Column(name = "year_number")
    private int yearNumber;

    public Long getYearCode() {
        return yearCode;
    }

    public void setYearCode(Long yearCode) {
        this.yearCode = yearCode;
    }

    public String getYearName() {
        return yearName;
    }

    public void setYearName(String yearName) {
        this.yearName = yearName;
    }

    public int getYearNumber() {
        return yearNumber;
    }

    public void setYearNumber(int yearNumber) {
        this.yearNumber = yearNumber;
    }
}
