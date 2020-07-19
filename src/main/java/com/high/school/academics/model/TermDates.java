package com.high.school.academics.model;

import com.high.school.students.model.Term;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "term_dates")
public class TermDates {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "term_date_id")
    private Long termDateCode;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "current_term")
    private String currentTerm;

    @ManyToOne
    @JoinColumn(name = "term")
    private Term term;

    public Long getTermDateCode() {
        return termDateCode;
    }

    public void setTermDateCode(Long termDateCode) {
        this.termDateCode = termDateCode;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getCurrentTerm() {
        return currentTerm;
    }

    public void setCurrentTerm(String currentTerm) {
        this.currentTerm = currentTerm;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }
}
