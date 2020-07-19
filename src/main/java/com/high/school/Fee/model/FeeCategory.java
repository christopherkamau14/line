package com.high.school.Fee.model;

import javax.persistence.*;

@Entity
@Table(name="fee_category")
public class FeeCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    private Long catCode;

    @Column(name = "category_name")
    private String catName;

    @Column(name = "cat_default")
    private String defaultCat;

    @Column(name = "category_desc")
    private String catDesc;

    @Column(name = "category_successor")
    private String catSuccessor;

    public Long getCatCode() {
        return catCode;
    }

    public void setCatCode(Long catCode) {
        this.catCode = catCode;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatDesc() {
        return catDesc;
    }

    public void setCatDesc(String catDesc) {
        this.catDesc = catDesc;
    }

    public String getCatSuccessor() {
        return catSuccessor;
    }

    public void setCatSuccessor(String catSuccessor) {
        this.catSuccessor = catSuccessor;
    }

    public String getDefaultCat() {
        return defaultCat;
    }

    public void setDefaultCat(String defaultCat) {
        this.defaultCat = defaultCat;
    }
}
