package com.high.school.students.model;

import javax.persistence.*;

@Entity
@Table(name="dorms_table")
public class Dorm {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dorm_id")
    private Long dormCode;

    @Column(name = "dorm_name")
    private String dormName;

    @Column(name = "dorm_desc")
    private String dormDesc;

    @Column(name = "dorm_capacity")
    private String capacity;

    public Long getDormCode() {
        return dormCode;
    }

    public void setDormCode(Long dormCode) {
        this.dormCode = dormCode;
    }

    public String getDormName() {
        return dormName;
    }

    public void setDormName(String dormName) {
        this.dormName = dormName;
    }

    public String getDormDesc() {
        return dormDesc;
    }

    public void setDormDesc(String dormDesc) {
        this.dormDesc = dormDesc;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }
}
