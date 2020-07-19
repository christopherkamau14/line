package com.high.school.Locale.model;


import javax.persistence.*;

@Entity
@Table(name="location_table")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="location_id")
    private Long louCode;

    @Column(name = "location_name")
    private String louName;

    @Column(name = "location_desc")
    private String louDesc;

    public Location() {
    }

    @ManyToOne
    @JoinColumn(name="sub_county")
    private SubCounty subCounty;

    public Location(Long location) {
        this.louCode=location;
    }

    public Long getLouCode() {
        return louCode;
    }

    public void setLouCode(Long louCode) {
        this.louCode = louCode;
    }

    public String getLouName() {
        return louName;
    }

    public void setLouName(String louName) {
        this.louName = louName;
    }

    public String getLouDesc() {
        return louDesc;
    }

    public void setLouDesc(String louDesc) {
        this.louDesc = louDesc;
    }

    public SubCounty getSubCounty() {
        return subCounty;
    }

    public void setSubCounty(SubCounty subCounty) {
        this.subCounty = subCounty;
    }
}
