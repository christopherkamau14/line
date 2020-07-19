package com.high.school.examination.model;

import java.math.BigDecimal;

;


public class PositionClass {
    private BigDecimal aggregate;
    private BigDecimal counter;
    private String grade;

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public BigDecimal getCounter() {
        return counter;
    }

    public void setCounter(BigDecimal counter) {
        this.counter = counter;
    }


    public BigDecimal getAggregate() {
        return aggregate;
    }

    public void setAggregate(BigDecimal aggregate) {
        this.aggregate = aggregate;
    }
}
