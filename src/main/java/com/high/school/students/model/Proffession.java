package com.high.school.students.model;

import javax.persistence.*;

@Entity
@Table(name="parent_proffession")
public class Proffession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "proffession_id")
    private Long profCode;

    @Column(name = "proffession_name")
    private String profName;

    @Column(name = "proffession_desc")
    private String profDesc;

    public Proffession() {
    }
    public Proffession(Long profCode) {
        this.profCode=profCode;
    }

    public Long getProfCode() {
        return profCode;
    }

    public void setProfCode(Long profCode) {
        this.profCode = profCode;
    }

    public String getProfName() {
        return profName;
    }

    public void setProfName(String profName) {
        this.profName = profName;
    }

    public String getProfDesc() {
        return profDesc;
    }

    public void setProfDesc(String profDesc) {
        this.profDesc = profDesc;
    }
}
