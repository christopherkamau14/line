package com.high.school.academics.model;

import javax.persistence.*;

@Entity
@Table(name="grading_system")
public class GradingSystem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "grading_id")
    private Long gradingCode;

    @Column(name = "grade_from")
    private Integer gradeFrom;

    @Column(name = "grade_to")
    private Integer gradeTo;

    @Column(name = "grade")
    private String grade;

    @Column(name = "apply_for")
    private String applicableFor;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "auto_increment")
    private String autoIncrement;

    @ManyToOne
    @JoinColumn(name = "department")
    private Department department;

    public Long getGradingCode() {
        return gradingCode;
    }

    public void setGradingCode(Long gradingCode) {
        this.gradingCode = gradingCode;
    }

    public Integer getGradeFrom() {
        return gradeFrom;
    }

    public void setGradeFrom(Integer gradeFrom) {
        this.gradeFrom = gradeFrom;
    }

    public Integer getGradeTo() {
        return gradeTo;
    }

    public void setGradeTo(Integer gradeTo) {
        this.gradeTo = gradeTo;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getApplicableFor() {
        return applicableFor;
    }

    public void setApplicableFor(String applicableFor) {
        this.applicableFor = applicableFor;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(String autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
