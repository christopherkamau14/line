package com.high.school.library.model;

import com.high.school.timetabling.model.Days;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="book_registration")
@SequenceGenerator(name="bookregseq", initialValue=1, allocationSize=1)
public class BookRegister {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="bookregseq")
    @Column(name = "book_reg_id")
    private Long bookRegCode;

    @Column(name = "issue_date")
    private Date issueDate;

    @Column(name = "book_title")
    private String title;

    @Column(name = "book_isbn")
    private String isbnNo;

    @Column(name = "book_publisher")
    private String publisher;

    @Column(name = "publication_year")
    private String year;

    @Column(name = "class_number")
    private Integer classNo;

    @Column(name = "book_author")
    private String author;

    @Column(name = "book_callno")
    private String callNo;

    @Column(name = "book_copies")
    private Long copies;

    @Column(name="book_status")
    private String status;

    @Column(name="book_comments")
    private String statusComments;

    @Column(name = "book_units")
    private Integer units;

    @Column(name = "book_loan")
    private String shortLoan;

    @ManyToOne
    @JoinColumn(name="book_shelves")
    private Shelves shelves;

    @ManyToOne
    @JoinColumn(name="book_stores")
    private Stores stores;

    @ManyToOne
    @JoinColumn(name="book_classification")
    private BookClass bookClass;


    public Long getBookRegCode() {
        return bookRegCode;
    }

    public void setBookRegCode(Long bookRegCode) {
        this.bookRegCode = bookRegCode;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbnNo() {
        return isbnNo;
    }

    public void setIsbnNo(String isbnNo) {
        this.isbnNo = isbnNo;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getClassNo() {
        return classNo;
    }

    public void setClassNo(Integer classNo) {
        this.classNo = classNo;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCallNo() {
        return callNo;
    }

    public void setCallNo(String callNo) {
        this.callNo = callNo;
    }

    public Long getCopies() {
        return copies;
    }

    public void setCopies(Long copies) {
        this.copies = copies;
    }

    public String getShortLoan() {
        return shortLoan;
    }

    public void setShortLoan(String shortLoan) {
        this.shortLoan = shortLoan;
    }

    public Shelves getShelves() {
        return shelves;
    }

    public void setShelves(Shelves shelves) {
        this.shelves = shelves;
    }

    public Stores getStores() {
        return stores;
    }

    public void setStores(Stores stores) {
        this.stores = stores;
    }

    public BookClass getBookClass() {
        return bookClass;
    }

    public void setBookClass(BookClass bookClass) {
        this.bookClass = bookClass;
    }

    public Integer getUnits() {
        return units;
    }

    public void setUnits(Integer units) {
        this.units = units;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusComments() {
        return statusComments;
    }

    public void setStatusComments(String statusComments) {
        this.statusComments = statusComments;
    }
}
