package com.high.school.timetabling.model;

import javax.persistence.*;

@Entity
@Table(name="days_setups")
@SequenceGenerator(name="dayseq", initialValue=1, allocationSize=1)
public class Days {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="dayseq")
    @Column(name = "day_id")
    private Long dayCode;

    @Column(name = "day_name")
    private String name;

    public Long getDayCode() {
        return dayCode;
    }

    public void setDayCode(Long dayCode) {
        this.dayCode = dayCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
