package com.high.school.students.model;

import javax.persistence.*;

@Entity
@Table(name="religion_table")
public class Religion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "religion_id")
    private Long relCode;

    @Column(name = "religion_name")
    private String relName;

    @Column(name = "religion_desc")
    private String relDesc;

    @Column(name = "religion_holy_day")
    private String relDay;

    public Long getRelCode() {
        return relCode;
    }

    public void setRelCode(Long relCode) {
        this.relCode = relCode;
    }

    public String getRelName() {
        return relName;
    }

    public void setRelName(String relName) {
        this.relName = relName;
    }

    public String getRelDesc() {
        return relDesc;
    }

    public void setRelDesc(String relDesc) {
        this.relDesc = relDesc;
    }

    public String getRelDay() {
        return relDay;
    }

    public void setRelDay(String relDay) {
        this.relDay = relDay;
    }
}
