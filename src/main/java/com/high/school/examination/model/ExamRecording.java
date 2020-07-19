package com.high.school.examination.model;

import com.high.school.academics.model.GradingSystem;
import com.high.school.academics.model.StudSubjects;
import com.high.school.academics.model.TeacherSubjects;
import com.high.school.academics.model.Teachers;
import com.high.school.students.model.Forms;
import com.high.school.students.model.Student;
import com.high.school.students.model.Term;
import com.high.school.students.model.Year;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "exam_recording")
public class ExamRecording {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "exam_record_id")
    private Long examRecordCode;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "exam_date")
    private Date examDate;

    @Column(name = "marks")
    private Double mark;

    @Column(name = "out_off")
    private Double outOff;

    @Column(name = "percentage")
    private Double percentage;

    @Column(name = "exam_status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "exam_register")
    private ExamRegister examRegister;

    @ManyToOne
    @JoinColumn(name = "exam_term")
    private Term term;

    @ManyToOne
    @JoinColumn(name = "exam_class")
    private Forms forms;

    @ManyToOne
    @JoinColumn(name = "exam_year")
    private Year year;

    @ManyToOne
    @JoinColumn(name = "exam_subject")
    private TeacherSubjects teacherSubjects;

    @ManyToOne
    @JoinColumn(name = "exam_teacher")
    private Teachers teachers;

    @ManyToOne
    @JoinColumn(name = "exam_student")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "exam_grade")
    private GradingSystem gradingSystem;


    public Long getExamRecordCode() {
        return examRecordCode;
    }

    public void setExamRecordCode(Long examRecordCode) {
        this.examRecordCode = examRecordCode;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }

    public Double getOutOff() {
        return outOff;
    }

    public void setOutOff(Double outOff) {
        this.outOff = outOff;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public ExamRegister getExamRegister() {
        return examRegister;
    }

    public void setExamRegister(ExamRegister examRegister) {
        this.examRegister = examRegister;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public Forms getForms() {
        return forms;
    }

    public void setForms(Forms forms) {
        this.forms = forms;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public TeacherSubjects getTeacherSubjects() {
        return teacherSubjects;
    }

    public void setTeacherSubjects(TeacherSubjects teacherSubjects) {
        this.teacherSubjects = teacherSubjects;
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

    public GradingSystem getGradingSystem() {
        return gradingSystem;
    }

    public void setGradingSystem(GradingSystem gradingSystem) {
        this.gradingSystem = gradingSystem;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}