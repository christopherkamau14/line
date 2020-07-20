package com.high.school.academics.model;

import javax.persistence.*;

@Entity
@Table(name="titles_table")
public class Titles {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "title_id")
    private Long titleCode;

    @Column(name = "title_name")
    private String titleName;

    @Column(name = "title_desc")
    private String titleDesc;

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getTitleDesc() {
        return titleDesc;
    }

    public void setTitleDesc(String titleDesc) {
        this.titleDesc = titleDesc;
    }

    public Long getTitleCode() {
        return titleCode;
    }

    public void setTitleCode(Long titleCode) {
        this.titleCode = titleCode;
    }
}
