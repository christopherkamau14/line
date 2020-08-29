package com.high.school.library.model;

import com.high.school.academics.model.Staff;
import com.high.school.academics.model.Teachers;
import com.high.school.students.model.Forms;
import com.high.school.students.model.Student;
import com.high.school.users.model.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="stationery_issuing")
public class StationeryIssue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="stat_issue_code")
    private Long statIssueCode;

    @Column(name="stat_issue_date")
    private Date issueDate;

    @Column(name="stat_issue_units")
    private Long units;

    @ManyToOne
    @JoinColumn(name="stat_issue_teacher")
    private Teachers teachers;

    @ManyToOne
    @JoinColumn(name="stat_issue_student")
    private Student student;

    @ManyToOne
    @JoinColumn(name="stat_issue_staff")
    private Staff staff;

    @ManyToOne
    @JoinColumn(name="stat_issue_class")
    private Forms forms;

    @ManyToOne
    @JoinColumn(name="stat_issue_stationery")
    private Stationery stationery;

    @ManyToOne
    @JoinColumn(name="stat_issue_store")
    private Stores stores;

    @ManyToOne
    @JoinColumn(name="stat_issue_by")
    private User registeredBy;

    public Long getStatIssueCode() {
        return statIssueCode;
    }

    public void setStatIssueCode(Long statIssueCode) {
        this.statIssueCode = statIssueCode;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Long getUnits() {
        return units;
    }

    public void setUnits(Long units) {
        this.units = units;
    }

    public Teachers getTeachers() {
        return teachers;
    }

    public void setTeachers(Teachers teachers) {
        this.teachers = teachers;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Forms getForms() {
        return forms;
    }

    public void setForms(Forms forms) {
        this.forms = forms;
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

    public User getRegisteredBy() {
        return registeredBy;
    }

    public void setRegisteredBy(User registeredBy) {
        this.registeredBy = registeredBy;
    }
}