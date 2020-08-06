package com.high.school.library.model;

import javax.persistence.*;

@Entity
@Table(name="stationery_table")
@SequenceGenerator(name="statseq", initialValue=1, allocationSize=100)
public class Stationery {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="statseq")
    @Column(name="stationery_id")
    private Long stationeryCode;

    @Column(name = "stationery_name")
    private String stationeryName;

    public Long getStationeryCode() {
        return stationeryCode;
    }

    public void setStationeryCode(Long stationeryCode) {
        this.stationeryCode = stationeryCode;
    }


    public String getStationeryName() {
        return stationeryName;
    }

    public void setStationeryName(String stationeryName) {
        this.stationeryName = stationeryName;
    }
}
