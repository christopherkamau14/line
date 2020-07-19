package com.high.school.academics.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="staff_register")
public class StaffRegister {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "staff_register_id")
    private Long staffRegisterCode;

    @Column(name = "register_date")
    private Date registerDate;

    @Column(name = "time_in")
    private String timeIn;

    @Column(name="time_out")
    private String timeOut;

    @ManyToOne
    @JoinColumn(name = "worker")
    private Staff staff;

    public Long getStaffRegisterCode() {
        return staffRegisterCode;
    }

    public void setStaffRegisterCode(Long staffRegisterCode) {
        this.staffRegisterCode = staffRegisterCode;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(String timeIn) {
        this.timeIn = timeIn;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }
}
