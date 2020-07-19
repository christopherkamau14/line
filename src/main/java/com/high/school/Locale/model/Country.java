package com.high.school.Locale.model;

import javax.persistence.*;

@Entity
@Table(name="country_table")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="country_id")
    private Long couCode;

    @Column(name="country_name")
    private String couName;

    @Column(name="country_desc")
    private String couDesc;

    @Column(name="country_cont")
    private String couContinent;

    public Country(Long country) {
        this.couCode=country;
    }

    public Country() {
    }

    public Long getCouCode() {
        return couCode;
    }

    public void setCouCode(Long couCode) {
        this.couCode = couCode;
    }

    public String getCouName() {
        return couName;
    }

    public void setCouName(String couName) {
        this.couName = couName;
    }

    public String getCouDesc() {
        return couDesc;
    }

    public void setCouDesc(String couDesc) {
        this.couDesc = couDesc;
    }

    public String getCouContinent() {
        return couContinent;
    }

    public void setCouContinent(String couContinent) {
        this.couContinent = couContinent;
    }
}
