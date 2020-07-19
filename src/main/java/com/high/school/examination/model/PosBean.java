package com.high.school.examination.model;

import java.math.BigDecimal;

public class PosBean {

    private String exam;
    private String name;
    private String admNo;
    private String className;
    private BigDecimal aggregate;
    private BigDecimal counter;
    private Long position;

    public String getExam() {
        return exam;
    }

    public void setExam(String exam) {
        this.exam = exam;
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

    public BigDecimal getAggregate() {
        return aggregate;
    }

    public void setAggregate(BigDecimal aggregate) {
        this.aggregate = aggregate;
    }

    public BigDecimal getCounter() {
        return counter;
    }

    public void setCounter(BigDecimal counter) {
        this.counter = counter;
    }

    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
    }
}
