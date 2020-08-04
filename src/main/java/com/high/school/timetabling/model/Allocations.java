package com.high.school.timetabling.model;

import com.high.school.academics.model.StudSubjects;
import com.high.school.academics.model.TeacherSubjects;
import com.high.school.academics.model.Teachers;
import com.high.school.students.model.Block;
import com.high.school.students.model.Forms;
import com.high.school.students.model.Term;
import com.high.school.users.model.User;

import javax.persistence.*;

@Entity
@Table(name="timetable_allocation")
public class Allocations {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="allocation_id")
    private Long allocationCode;

    @Column(name="day_name")
    private String dayName;

    @Column(name="teacher_name")
    private String teacherName;

    @Column(name="subject_name")
    private String subjectName;

    @Column(name="class_name")
    private String className;

    @ManyToOne
    @JoinColumn(name="tt_days")
    private Days days;

    @ManyToOne
    @JoinColumn(name="tt_user")
    private User createdBy;

    @ManyToOne
    @JoinColumn(name="tt_lessons")
    private Lessons lessons;

    @ManyToOne
    @JoinColumn(name="tt_class")
    private Forms forms;

    @ManyToOne
    @JoinColumn(name="tt_term")
    private Term term;

    @ManyToOne
    @JoinColumn(name="tt_subjects")
    private StudSubjects subjects;

    @ManyToOne
    @JoinColumn(name="tt_teachers")
    private Teachers teachers;

    @Column(name = "tt_stroked")
    private String stroked;

    public String getStroked() {
        return stroked;
    }

    public void setStroked(String stroked) {
        this.stroked = stroked;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Long getAllocationCode() {
        return allocationCode;
    }

    public void setAllocationCode(Long allocationCode) {
        this.allocationCode = allocationCode;
    }


    public Days getDays() {
        return days;
    }

    public void setDays(Days days) {
        this.days = days;
    }

    public Lessons getLessons() {
        return lessons;
    }

    public void setLessons(Lessons lessons) {
        this.lessons = lessons;
    }

    public Forms getForms() {
        return forms;
    }

    public void setForms(Forms forms) {
        this.forms = forms;
    }


    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public StudSubjects getSubjects() {
        return subjects;
    }

    public void setSubjects(StudSubjects subjects) {
        this.subjects = subjects;
    }

    public Teachers getTeachers() {
        return teachers;
    }

    public void setTeachers(Teachers teachers) {
        this.teachers = teachers;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
