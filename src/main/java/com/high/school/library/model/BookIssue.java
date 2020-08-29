package com.high.school.library.model;

import com.high.school.academics.model.Teachers;
import com.high.school.students.model.Forms;
import com.high.school.students.model.Student;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="book_issuing")
public class BookIssue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="book_issue_code")
    private Long bookIssueCode;

    @Column(name="issue_date")
    private Date issueDate;

    @Column(name="return_date")
    private Date returnDate;

    @Column(name="date_returned")
    private Date dateReturned;

    @Column(name="borrower_cat")
    private String borrowerCat;


    @Column(name="book_callNo")
    private String callNo;

    @Column(name="book_title")
    private String title;

    @ManyToOne
    @JoinColumn(name="book_student")
    private Student student;

    @ManyToOne
    @JoinColumn(name="book_form")
    private Forms forms;

    @ManyToOne
    @JoinColumn(name="book_teachers")
    private Teachers teachers;

    @ManyToOne
    @JoinColumn(name="book_register")
    private BookRegister bookRegister;

    public Long getBookIssueCode() {
        return bookIssueCode;
    }

    public void setBookIssueCode(Long bookIssueCode) {
        this.bookIssueCode = bookIssueCode;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Date getDateReturned() {
        return dateReturned;
    }

    public void setDateReturned(Date dateReturned) {
        this.dateReturned = dateReturned;
    }

    public String getBorrowerCat() {
        return borrowerCat;
    }

    public void setBorrowerCat(String borrowerCat) {
        this.borrowerCat = borrowerCat;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Forms getForms() {
        return forms;
    }

    public void setForms(Forms forms) {
        this.forms = forms;
    }

    public Teachers getTeachers() {
        return teachers;
    }

    public void setTeachers(Teachers teachers) {
        this.teachers = teachers;
    }

    public BookRegister getBookRegister() {
        return bookRegister;
    }

    public void setBookRegister(BookRegister bookRegister) {
        this.bookRegister = bookRegister;
    }

    public String getCallNo() {
        return callNo;
    }

    public void setCallNo(String callNo) {
        this.callNo = callNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
