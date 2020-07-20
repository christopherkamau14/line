package com.high.school.examination.model;

import com.high.school.students.model.Forms;
import com.high.school.students.model.Term;
import com.high.school.students.model.Year;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="exam_register")
public class ExamRegister {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "exam_id")
    private Long examCode;

    @Column(name = "exam_name")
    private String examName;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "exam_month")
    private String month;

    @Column(name = "exam_grading")
    private String grading;

    @Column(name = "start_date")
    private Date effectiveDate;

    @Column(name = "end_date")
    private Date lockDate;

    @Column(name = "exam_final")
    private String finalExam;

    @Column(name = "exam_status")
    private String status;

    @Column(name = "exam_combined")
    private String combinedExam;

    @Column(name = "exam_authorised")
    private String authorised;

    @ManyToOne
    @JoinColumn(name="exam_year")
    private Year year;

    @ManyToOne
    @JoinColumn(name="exam_term")
    private Term term;

    @ManyToOne
    @JoinColumn(name="exam_class")
    private Forms forms;

    @ManyToOne
    @JoinColumn(name="exam_type")
    private ExamType examType;

    public Long getExamCode() {
        return examCode;
    }

    public void setExamCode(Long examCode) {
        this.examCode = examCode;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getGrading() {
        return grading;
    }

    public void setGrading(String grading) {
        this.grading = grading;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Date getLockDate() {
        return lockDate;
    }

    public void setLockDate(Date lockDate) {
        this.lockDate = lockDate;
    }

    public String getFinalExam() {
        return finalExam;
    }

    public void setFinalExam(String finalExam) {
        this.finalExam = finalExam;
    }

    public String getCombinedExam() {
        return combinedExam;
    }

    public void setCombinedExam(String combinedExam) {
        this.combinedExam = combinedExam;
    }

    public String getAuthorised() {
        return authorised;
    }

    public void setAuthorised(String authorised) {
        this.authorised = authorised;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public Forms getForms() {
        return forms;
    }

    public void setForms(Forms forms) {
        this.forms = forms;
    }

    public ExamType getExamType() {
        return examType;
    }

    public void setExamType(ExamType examType) {
        this.examType = examType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
