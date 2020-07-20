package com.high.school.academics.model;

import javax.persistence.*;

@Entity
@Table(name="subject_groups")
public class SubjectGroups {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "group_id")
    private Long groupCode;

    @Column(name = "group_number")
    private Integer groupNumber;

    @Column(name = "compulsory_subject")
    private String compulsorySubject;

    @Column(name = "subject_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "subjects")
    private StudSubjects subjects;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(Long groupCode) {
        this.groupCode = groupCode;
    }

    public Integer getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(Integer groupNumber) {
        this.groupNumber = groupNumber;
    }

    public String getCompulsorySubject() {
        return compulsorySubject;
    }

    public void setCompulsorySubject(String compulsorySubject) {
        this.compulsorySubject = compulsorySubject;
    }

    public StudSubjects getSubjects() {
        return subjects;
    }

    public void setSubjects(StudSubjects subjects) {
        this.subjects = subjects;
    }
}
