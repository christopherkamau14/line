package com.high.school.library.model;

import com.high.school.users.model.User;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="book_costs_tbl")
public class BookCost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="cost_code")
    public Long costCode;

    @Column(name="book_title")
    public String title;

    @Column(name="book_cost")
    public BigDecimal cost;

    @Column(name="date_updated")
    private Date issuedDate;

    @ManyToOne
    @JoinColumn(name="user_updated")
    private User user;

    @ManyToOne
    @JoinColumn(name="book_register")
    private BookRegister bookRegister;

    public Long getCostCode() {
        return costCode;
    }

    public void setCostCode(Long costCode) {
        this.costCode = costCode;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Date getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BookRegister getBookRegister() {
        return bookRegister;
    }

    public void setBookRegister(BookRegister bookRegister) {
        this.bookRegister = bookRegister;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
