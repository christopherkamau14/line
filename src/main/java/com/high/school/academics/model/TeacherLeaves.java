package com.high.school.academics.model;

import com.high.school.students.model.Forms;
import com.high.school.students.model.Student;
import com.high.school.users.model.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="teacher_leaves")
public class TeacherLeaves {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "leave_id")
    private Long leaveCode;

    @Column(name="leave_date")
    private Date dateLeft;

    @Column(name="return_date")
    private Date dateReturned;

    @Column(name="expected_date")
    private Date dateExpected;

    @Lob
    @Column(name="leave_reason")
    private String reason;

    @ManyToOne
    @JoinColumn(name="granted_by")
    private User grantedBy;

    @ManyToOne
    @JoinColumn(name="authorised_by")
    private User authorisedBy;


    @ManyToOne
    @JoinColumn(name="teachers")
    private Teachers teachers;

    public Long getLeaveCode() {
        return leaveCode;
    }

    public void setLeaveCode(Long leaveCode) {
        this.leaveCode = leaveCode;
    }

    public Date getDateLeft() {
        return dateLeft;
    }

    public void setDateLeft(Date dateLeft) {
        this.dateLeft = dateLeft;
    }

    public Date getDateReturned() {
        return dateReturned;
    }

    public void setDateReturned(Date dateReturned) {
        this.dateReturned = dateReturned;
    }

    public Date getDateExpected() {
        return dateExpected;
    }

    public void setDateExpected(Date dateExpected) {
        this.dateExpected = dateExpected;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public User getGrantedBy() {
        return grantedBy;
    }

    public void setGrantedBy(User grantedBy) {
        this.grantedBy = grantedBy;
    }

    public User getAuthorisedBy() {
        return authorisedBy;
    }

    public void setAuthorisedBy(User authorisedBy) {
        this.authorisedBy = authorisedBy;
    }

    public Teachers getTeachers() {
        return teachers;
    }

    public void setTeachers(Teachers teachers) {
        this.teachers = teachers;
    }
}
