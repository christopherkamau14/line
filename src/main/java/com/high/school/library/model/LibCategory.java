package com.high.school.library.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="library_category")
@SequenceGenerator(name="catseq", initialValue=1, allocationSize=1)
public class LibCategory {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="catseq")
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "duration")
    private Long duration;

    @Column(name = "duration_type")
    private String durationType;

    @Column(name = "overdue_charges")
    private BigDecimal charges;

    @Column(name = "book_number")
    private Long bookNumber;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getDurationType() {
        return durationType;
    }

    public void setDurationType(String durationType) {
        this.durationType = durationType;
    }

    public BigDecimal getCharges() {
        return charges;
    }

    public void setCharges(BigDecimal charges) {
        this.charges = charges;
    }

    public Long getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(Long bookNumber) {
        this.bookNumber = bookNumber;
    }
}
