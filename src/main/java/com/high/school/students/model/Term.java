package com.high.school.students.model;

import javax.persistence.*;

@Entity
@Table(name="school_term")
public class Term {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "term_id")
    private Long termCode;

    @Column(name = "term_name")
    private int termName;

    @Column(name = "term_number")
    private int termNumber;

    public Long getTermCode() {
        return termCode;
    }

    public void setTermCode(Long termCode) {
        this.termCode = termCode;
    }

    public int getTermName() {
        return termName;
    }

    public void setTermName(int termName) {
        this.termName = termName;
    }

    public int getTermNumber() {
        return termNumber;
    }

    public void setTermNumber(int termNumber) {
        this.termNumber = termNumber;
    }
}
