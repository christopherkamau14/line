package com.high.school.examination.model;

import com.high.school.students.model.Forms;
import com.high.school.students.model.Student;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="position_table")
public class PositioningBean {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "missed_exam_id")
    private Long positionCode;

    @Column(name = "student_name")
    private String name;

    @Column(name="subject_entered")
    private BigDecimal subjectsEntered;

    @Column(name="student_admNo")
    private String admNo;

    @Column(name = "class_student")
    private String className;

    @Column(name = "pos_marks")
    private BigDecimal marks;

    @Column(name = "class_marks")
    private BigDecimal classMarks;

    @Column(name="class_grade")
    private String classGrade;

    @Column(name = "class_position")
    private Long position;

    @Column(name = "exam_name")
    private String exam;

    @Column(name="total_marks")
    private BigDecimal totalMarks;

    @Column(name = "exam_grade")
    private String grade;

    @ManyToOne
    @JoinColumn(name = "pos_student")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "pos_class")
    private Forms forms;

    @ManyToOne
    @JoinColumn(name = "pos_register")
    private ExamRegister examRegister;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Forms getForms() {
        return forms;
    }

    public void setForms(Forms forms) {
        this.forms = forms;
    }

    public ExamRegister getExamRegister() {
        return examRegister;
    }

    public void setExamRegister(ExamRegister examRegister) {
        this.examRegister = examRegister;
    }

    public Long getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(Long positionCode) {
        this.positionCode = positionCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdmNo() {
        return admNo;
    }

    public void setAdmNo(String admNo) {
        this.admNo = admNo;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public BigDecimal getMarks() {
        return marks;
    }

    public void setMarks(BigDecimal marks) {
        this.marks = marks;
    }

    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
    }

    public String getExam() {
        return exam;
    }

    public void setExam(String exam) {
        this.exam = exam;
    }

    public BigDecimal getClassMarks() {
        return classMarks;
    }

    public void setClassMarks(BigDecimal classMarks) {
        this.classMarks = classMarks;
    }

    public String getClassGrade() {
        return classGrade;
    }

    public void setClassGrade(String classGrade) {
        this.classGrade = classGrade;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public BigDecimal getSubjectsEntered() {
        return subjectsEntered;
    }

    public void setSubjectsEntered(BigDecimal subjectsEntered) {
        this.subjectsEntered = subjectsEntered;
    }

    public BigDecimal getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(BigDecimal totalMarks) {
        this.totalMarks = totalMarks;
    }
}
