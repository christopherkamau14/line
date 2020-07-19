package com.high.school.Locale.model;


import javax.persistence.*;
import javax.persistence.Column;
@Entity
@Table(name="village_table")
public class Village {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="village_id")
    private Long vilCode;

    @Column(name = "village_name")
    private String vilName;

    @Column(name = "village_desc")
    private String vilDesc;

    @ManyToOne
    @JoinColumn(name="sub_location")
    private SubLocation subLocation;

    public Long getVilCode() {
        return vilCode;
    }

    public void setVilCode(Long vilCode) {
        this.vilCode = vilCode;
    }

    public String getVilName() {
        return vilName;
    }

    public void setVilName(String vilName) {
        this.vilName = vilName;
    }

    public String getVilDesc() {
        return vilDesc;
    }

    public void setVilDesc(String vilDesc) {
        this.vilDesc = vilDesc;
    }

    public SubLocation getSubLocation() {
        return subLocation;
    }

    public void setSubLocation(SubLocation subLocation) {
        this.subLocation = subLocation;
    }
}
