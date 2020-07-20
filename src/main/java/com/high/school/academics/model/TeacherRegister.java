package com.high.school.academics.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="teacher_register")
public class TeacherRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "teacher_register_id")
    private Long teacherRegisterCode;

    @Column(name = "register_date")
    private Date registerDate;

    @Column(name = "time_in")
    private String timeIn;

    @Column(name="time_out")
    private String timeOut;

    @ManyToOne
    @JoinColumn(name = "teacher")
    private Teachers teachers;


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

    public Long getTeacherRegisterCode() {
        return teacherRegisterCode;
    }

    public void setTeacherRegisterCode(Long teacherRegisterCode) {
        this.teacherRegisterCode = teacherRegisterCode;
    }

    public Teachers getTeachers() {
        return teachers;
    }

    public void setTeachers(Teachers teachers) {
        this.teachers = teachers;
    }
}
