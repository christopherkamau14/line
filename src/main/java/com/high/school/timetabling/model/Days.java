package com.high.school.timetabling.model;

import javax.persistence.*;

@Entity
@Table(name="days_setups")
public class Days {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "day_id")
    private Long dayCode;

    @Column(name = "day_code")
    private String code;

    @Column(name = "day_name")
    private String name;

    public Long getDayCode() {
        return dayCode;
    }

    public void setDayCode(Long dayCode) {
        this.dayCode = dayCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
