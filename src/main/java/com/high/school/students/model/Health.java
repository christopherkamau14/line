package com.high.school.students.model;

import javax.persistence.*;

@Entity
@Table(name="health_table")
public class Health {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "condition_id")
    private Long condCode;

    @Column(name = "condition_name")
    private String condName;

    @Column(name = "condition_desc")
    private String condDesc;

    @Column(name = "condition_comments")
    private String condComments;

    public Long getCondCode() {
        return condCode;
    }

    public void setCondCode(Long condCode) {
        this.condCode = condCode;
    }

    public String getCondName() {
        return condName;
    }

    public void setCondName(String condName) {
        this.condName = condName;
    }

    public String getCondDesc() {
        return condDesc;
    }

    public void setCondDesc(String condDesc) {
        this.condDesc = condDesc;
    }

    public String getCondComments() {
        return condComments;
    }

    public void setCondComments(String condComments) {
        this.condComments = condComments;
    }
}
