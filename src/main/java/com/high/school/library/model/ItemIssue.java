package com.high.school.library.model;

import com.high.school.academics.model.Teachers;
import com.high.school.students.model.Forms;
import com.high.school.students.model.Student;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="items_issuing")
@SequenceGenerator(name="itemsissueseq", initialValue=1, allocationSize=1)
public class ItemIssue {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="itemsissueseq")
    @Column(name = "item_id")
    private Long itemCode;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "issued_date")
    private Date issueDate;

    @Column(name = "return_date")
    private Date returnDate;

    @Column(name = "returned_date")
    private Date dateReturned;

    @Column(name = "issued_units")
    private BigDecimal units;

    @Column(name = "borrower_category")
    private String borrowerCat;

    @Column(name = "item_description")
    private String description;

    @Column(name = "teacher_name")
    private String borrowerName;

    @Column(name = "issuer_code")
    private Long issuerCode;

    @Column(name = "issuer_name")
    private String issuerName;

    @ManyToOne
    @JoinColumn(name="item_teacher")
    private Teachers teachers;

    @ManyToOne
    @JoinColumn(name="item_students")
    private Student student;

    @ManyToOne
    @JoinColumn(name="item_class")
    private Forms forms;

    public Long getItemCode() {
        return itemCode;
    }

    public void setItemCode(Long itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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

    public BigDecimal getUnits() {
        return units;
    }

    public void setUnits(BigDecimal units) {
        this.units = units;
    }

    public String getBorrowerCat() {
        return borrowerCat;
    }

    public void setBorrowerCat(String borrowerCat) {
        this.borrowerCat = borrowerCat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public Long getIssuerCode() {
        return issuerCode;
    }

    public void setIssuerCode(Long issuerCode) {
        this.issuerCode = issuerCode;
    }

    public String getIssuerName() {
        return issuerName;
    }

    public void setIssuerName(String issuerName) {
        this.issuerName = issuerName;
    }

    public Teachers getTeachers() {
        return teachers;
    }

    public void setTeachers(Teachers teachers) {
        this.teachers = teachers;
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
}
