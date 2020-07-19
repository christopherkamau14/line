package com.high.school.academics.model;

import com.high.school.students.model.Forms;

import javax.persistence.*;

@Entity
@Table(name="teacher_subjects")
public class TeacherSubjects {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "teacher_subject_id")
    private Long teacherSubjectCode;

    @Transient
    Long subjectCode;

    @Transient
    String subjectName;

    @ManyToOne
    @JoinColumn(name = "class_subject")
    private Forms forms;

    @ManyToOne
    @JoinColumn(name = "subject_teachers")
    private Teachers teachers;

    @ManyToOne
    @JoinColumn(name = "subjects")
    private StudSubjects subjects;

    public Long getTeacherSubjectCode() {
        return teacherSubjectCode;
    }

    public void setTeacherSubjectCode(Long teacherSubjectCode) {
        this.teacherSubjectCode = teacherSubjectCode;
    }

    public Forms getForms() {
        return forms;
    }

    public void setForms(Forms forms) {
        this.forms = forms;
    }

    public Teachers getTeachers() {
        return teachers;
    }

    public void setTeachers(Teachers teachers) {
        this.teachers = teachers;
    }

    public StudSubjects getSubjects() {
        return subjects;
    }

    public void setSubjects(StudSubjects subjects) {
        this.subjects = subjects;
    }

    public Long getSubjectCode() {
        return subjects.getSubjectCode();
    }

    public void setSubjectCode(Long subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectName() {
        return subjects.getName();
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}

