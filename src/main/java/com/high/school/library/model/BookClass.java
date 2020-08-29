package com.high.school.library.model;

import javax.persistence.*;

@Entity
@Table(name="book_classification")
@SequenceGenerator(name="bookclseq", initialValue=1, allocationSize=1)
public class BookClass {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="bookclseq")
    @Column(name = "classification_id")
    private Long classificationCode;

    @Column(name = "classification_name")
    private String classificationName;

    public Long getClassificationCode() {
        return classificationCode;
    }

    public void setClassificationCode(Long classificationCode) {
        this.classificationCode = classificationCode;
    }

    public String getClassificationName() {
        return classificationName;
    }

    public void setClassificationName(String classificationName) {
        this.classificationName = classificationName;
    }
}
