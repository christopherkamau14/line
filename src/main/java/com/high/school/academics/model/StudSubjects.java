package com.high.school.academics.model;

import javax.persistence.*;

@Entity
@Table(name="subjects_table")
public class StudSubjects {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "subject_id")
    private Long subjectCode;

    @Column(name = "subject_order")
    private Long order;

    @Column(name = "subject_name")
    private String name;

    @Column(name = "subject_desc")
    private String subjectDesc;

    @Column(name = "main_subject")
    private String mainSubject;

    @Column(name = "combined_subject")
    private String combinedSubject;

    @Column(name = "pros_subject")
    private String prosSubject;

    @Column(name = "multiplier_subject")
    private Long multiplier;

    @Column(name = "timetable_name")
    private String timeTableName;

    @Column(name = "subject_assigned")
    private String assigned;

    @ManyToOne
    @JoinColumn(name="subject_dept")
    Department department;

    public String getAssigned() {
        return assigned;
    }

    public void setAssigned(String assigned) {
        this.assigned = assigned;
    }

    public String getSubjectDesc() {
        return subjectDesc;
    }

    public void setSubjectDesc(String subjectDesc) {
        this.subjectDesc = subjectDesc;
    }

    public Long getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(Long subjectCode) {
        this.subjectCode = subjectCode;
    }

    public Long getOrder() {
        return order;
    }

    public void setOrder(Long order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMainSubject() {
        return mainSubject;
    }

    public void setMainSubject(String mainSubject) {
        this.mainSubject = mainSubject;
    }

    public String getCombinedSubject() {
        return combinedSubject;
    }

    public void setCombinedSubject(String combinedSubject) {
        this.combinedSubject = combinedSubject;
    }

    public String getProsSubject() {
        return prosSubject;
    }

    public void setProsSubject(String prosSubject) {
        this.prosSubject = prosSubject;
    }

    public Long getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(Long multiplier) {
        this.multiplier = multiplier;
    }

    public String getTimeTableName() {
        return timeTableName;
    }

    public void setTimeTableName(String timeTableName) {
        this.timeTableName = timeTableName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
