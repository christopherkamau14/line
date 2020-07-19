package com.high.school.Locale.model;


import javax.persistence.*;

@Entity
@Table(name="sub_location_table")
public class SubLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="sub_location_id")
    private Long subLouCode;

    @Column(name = "sub_location_name")
    private String subLouName;

    @Column(name = "sub_location_desc")
    private String subLouDesc;

    @ManyToOne
    @JoinColumn(name="location")
    private Location location;

    public SubLocation() {
    }

    public SubLocation(Long sublocation) {
        this.subLouCode=sublocation;
    }

    public Long getSubLouCode() {
        return subLouCode;
    }

    public void setSubLouCode(Long subLouCode) {
        this.subLouCode = subLouCode;
    }

    public String getSubLouName() {
        return subLouName;
    }

    public void setSubLouName(String subLouName) {
        this.subLouName = subLouName;
    }

    public String getSubLouDesc() {
        return subLouDesc;
    }

    public void setSubLouDesc(String subLouDesc) {
        this.subLouDesc = subLouDesc;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
