package com.high.school.examination.model;

import com.high.school.students.model.Term;
import com.high.school.students.model.Year;

public class CombineBean {

    private ExamRegister finalExam;
    private ExamRegister combineExam;
    private Term term;
    private Year year;


    public ExamRegister getFinalExam() {
        return finalExam;
    }

    public void setFinalExam(ExamRegister finalExam) {
        this.finalExam = finalExam;
    }

    public ExamRegister getCombineExam() {
        return combineExam;
    }

    public void setCombineExam(ExamRegister combineExam) {
        this.combineExam = combineExam;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }
}
