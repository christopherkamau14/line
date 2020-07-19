package com.high.school.Locale.model;


import javax.persistence.*;

@Entity
@Table(name="sub_county_table")
public class SubCounty {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sub_county_id")
    private Long subCode;

    @Column(name = "sub_county_name")
    private String subName;

    @Column(name = "sub_county_desc")
    private String subDesc;

    public SubCounty() {
    }

    @ManyToOne
    @JoinColumn(name="county")
    County county;

    public SubCounty(Long subCounty) {
        this.subCode=subCounty;
    }

    public County getCounty() {
        return county;
    }

    public void setCounty(County county) {
        this.county = county;
    }

    public Long getSubCode() {
        return subCode;
    }

    public void setSubCode(Long subCode) {
        this.subCode = subCode;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getSubDesc() {
        return subDesc;
    }

    public void setSubDesc(String subDesc) {
        this.subDesc = subDesc;
    }
}
