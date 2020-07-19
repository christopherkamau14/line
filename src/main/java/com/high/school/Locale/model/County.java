package com.high.school.Locale.model;


import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.criteria.Expression;

@Entity
@Table(name="county_table")
public class County {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "county_id")
    private Long countyCode;

    @Column(name = "county_name")
    private String countyName;

    @Column(name = "county_desc")
    private String countyDesc;

    public County() {
    }

    @ManyToOne
    @JoinColumn(name="country")
    Country country;

    public County(Long county) {
        this.countyCode=county;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Long getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(Long countyCode) {
        this.countyCode = countyCode;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getCountyDesc() {
        return countyDesc;
    }

    public void setCountyDesc(String countyDesc) {
        this.countyDesc = countyDesc;
    }
}
