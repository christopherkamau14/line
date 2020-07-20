package com.high.school.academics.model;

import javax.persistence.*;

@Entity
@Table(name="responsibility_table")
public class Responsibility {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "responsibility_id")
    private Long responsibilityCode;

    @Column(name = "responsibility_code")
    private Long code;

    @Column(name = "responsibility_name")
    private String responsibilityName;

    @Column(name = "responsibility_category")
    private String category;


    public Long getResponsibilityCode() {
        return responsibilityCode;
    }

    public void setResponsibilityCode(Long responsibilityCode) {
        this.responsibilityCode = responsibilityCode;
    }

    public String getResponsibilityName() {
        return responsibilityName;
    }

    public void setResponsibilityName(String responsibilityName) {
        this.responsibilityName = responsibilityName;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
