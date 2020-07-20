package com.high.school.academics.model;

import com.high.school.students.model.Forms;

import javax.persistence.*;

@Entity
@Table(name = "class_subjects")
public class ClassSubjects {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "class_subject_id")
    private Long classSubjectCode;

    @Column(name="subject_name")
    private String name;

    @Column(name = "stroked_subject")
    private String strokedSubject;

    @Column(name = "stroked_with")
    private String strokedWith;

    @Column(name = "weekly_lessons")
    private String weekLessons;

    @Column(name = "double_lessons")
    private String doubleLessons;



    @ManyToOne
    @JoinColumn(name = "class_subject")
    private Forms forms;


    @ManyToOne
    @JoinColumn(name = "subjects")
    private StudSubjects subjects;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getClassSubjectCode() {
        return classSubjectCode;
    }

    public void setClassSubjectCode(Long classSubjectCode) {
        this.classSubjectCode = classSubjectCode;
    }

    public String getStrokedSubject() {
        return strokedSubject;
    }

    public void setStrokedSubject(String strokedSubject) {
        this.strokedSubject = strokedSubject;
    }

    public String getStrokedWith() {
        return strokedWith;
    }

    public void setStrokedWith(String strokedWith) {
        this.strokedWith = strokedWith;
    }

    public String getWeekLessons() {
        return weekLessons;
    }

    public void setWeekLessons(String weekLessons) {
        this.weekLessons = weekLessons;
    }

    public String getDoubleLessons() {
        return doubleLessons;
    }

    public void setDoubleLessons(String doubleLessons) {
        this.doubleLessons = doubleLessons;
    }

    public Forms getForms() {
        return forms;
    }

    public void setForms(Forms forms) {
        this.forms = forms;
    }

    public StudSubjects getSubjects() {
        return subjects;
    }

    public void setSubjects(StudSubjects subjects) {
        this.subjects = subjects;
    }
}
