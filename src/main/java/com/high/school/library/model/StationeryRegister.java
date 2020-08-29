package com.high.school.library.model;

import com.high.school.timetabling.model.Days;
import com.high.school.users.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="stationery_register")
public class StationeryRegister {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="stat_reg_id")
    private Long stationeryRegCode;

    @Column(name="stat_reg_units")
    private Long units;

    @Column(name="stat_total_units")
    private Long totalUnits;

    @Column(name="stat_reg_date")
    private Date issueDate;

    @ManyToOne
    @JoinColumn(name="registered_by")
    private User registeredBy;

    @ManyToOne
    @JoinColumn(name="stat_reg_stationery")
    Stationery stationery;

    @ManyToOne
    @JoinColumn(name="stat_reg_stores")
    Stores stores;


    public Long getStationeryRegCode() {
        return stationeryRegCode;
    }

    public void setStationeryRegCode(Long stationeryRegCode) {
        this.stationeryRegCode = stationeryRegCode;
    }

    public Long getUnits() {
        return units;
    }

    public void setUnits(Long units) {
        this.units = units;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public User getRegisteredBy() {
        return registeredBy;
    }

    public void setRegisteredBy(User registeredBy) {
        this.registeredBy = registeredBy;
    }

    public Stationery getStationery() {
        return stationery;
    }

    public void setStationery(Stationery stationery) {
        this.stationery = stationery;
    }

    public Stores getStores() {
        return stores;
    }

    public void setStores(Stores stores) {
        this.stores = stores;
    }

    public Long getTotalUnits() {
        return totalUnits;
    }

    public void setTotalUnits(Long totalUnits) {
        this.totalUnits = totalUnits;
    }
}
