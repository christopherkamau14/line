package com.high.school.academics.model;

import com.high.school.students.model.Student;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "student_clearance")
public class Clearance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "clearance_id")
    private Long clearanceCode;

    @Column(name = "library_name")
    private String libraryName;

    @Column(name = "library_comment")
    private String libraryComment;

    @Column(name = "library_date")
    private Date libraryDate;

    @Column(name = "labaratory_name")
    private String laboratoryName;

    @Column(name = "laboratory_comment")
    private String laboratoryComment;

    @Column(name = "laboratory_date")
    private Date laboratoryDate;

    @Column(name = "hod_name")
    private String hodName;

    @Column(name = "hod_comment")
    private String hodComment;

    @Column(name = "hod_date")
    private Date hodDate;

    @Column(name = "acct_name")
    private String acctName;

    @Column(name = "acct_comment")
    private String acctComment;

    @Column(name = "acct_date")
    private Date acctDate;

    @Column(name = "exam_name")
    private String examName;

    @Column(name = "exam_comment")
    private String examComment;

    @Column(name = "exam_date")
    private Date examDate;

    @Column(name = "principal_name")
    private String principalName;

    @Column(name = "principal_comment")
    private String principalComment;

    @Column(name = "principal_date")
    private Date principalDate;

    @Column(name = "deputy_name")
    private String deputyName;

    @Column(name = "deputy_comment")
    private String deputyComment;

    @Column(name = "deputy_date")
    private Date deputyDate;

    @Column(name = "save_date")
    private Date saveDate;

    @Column(name = "cord_name")
    private String cordName;

    @Column(name = "cord_comment")
    private String cordComment;

    @Column(name = "cord_date")
    private Date cordDate;

    @Column(name = "cleared_grad")
    private String clearedGrad;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "student")
    private Student student;

    public Date getSaveDate() {
        return saveDate;
    }

    public void setSaveDate(Date saveDate) {
        this.saveDate = saveDate;
    }

    public Long getClearanceCode() {
        return clearanceCode;
    }

    public void setClearanceCode(Long clearanceCode) {
        this.clearanceCode = clearanceCode;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public String getLibraryComment() {
        return libraryComment;
    }

    public void setLibraryComment(String libraryComment) {
        this.libraryComment = libraryComment;
    }

    public Date getLibraryDate() {
        return libraryDate;
    }

    public void setLibraryDate(Date libraryDate) {
        this.libraryDate = libraryDate;
    }

    public String getLaboratoryName() {
        return laboratoryName;
    }

    public void setLaboratoryName(String laboratoryName) {
        this.laboratoryName = laboratoryName;
    }

    public String getLaboratoryComment() {
        return laboratoryComment;
    }

    public void setLaboratoryComment(String laboratoryComment) {
        this.laboratoryComment = laboratoryComment;
    }

    public Date getLaboratoryDate() {
        return laboratoryDate;
    }

    public void setLaboratoryDate(Date laboratoryDate) {
        this.laboratoryDate = laboratoryDate;
    }

    public String getHodName() {
        return hodName;
    }

    public void setHodName(String hodName) {
        this.hodName = hodName;
    }

    public String getHodComment() {
        return hodComment;
    }

    public void setHodComment(String hodComment) {
        this.hodComment = hodComment;
    }

    public Date getHodDate() {
        return hodDate;
    }

    public void setHodDate(Date hodDate) {
        this.hodDate = hodDate;
    }

    public String getAcctName() {
        return acctName;
    }

    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }

    public String getAcctComment() {
        return acctComment;
    }

    public void setAcctComment(String acctComment) {
        this.acctComment = acctComment;
    }

    public Date getAcctDate() {
        return acctDate;
    }

    public void setAcctDate(Date acctDate) {
        this.acctDate = acctDate;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getExamComment() {
        return examComment;
    }

    public void setExamComment(String examComment) {
        this.examComment = examComment;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

    public String getPrincipalComment() {
        return principalComment;
    }

    public void setPrincipalComment(String principalComment) {
        this.principalComment = principalComment;
    }

    public Date getPrincipalDate() {
        return principalDate;
    }

    public void setPrincipalDate(Date principalDate) {
        this.principalDate = principalDate;
    }

    public String getDeputyName() {
        return deputyName;
    }

    public void setDeputyName(String deputyName) {
        this.deputyName = deputyName;
    }

    public String getDeputyComment() {
        return deputyComment;
    }

    public void setDeputyComment(String deputyComment) {
        this.deputyComment = deputyComment;
    }

    public Date getDeputyDate() {
        return deputyDate;
    }

    public void setDeputyDate(Date deputyDate) {
        this.deputyDate = deputyDate;
    }

    public String getCordName() {
        return cordName;
    }

    public void setCordName(String cordName) {
        this.cordName = cordName;
    }

    public String getCordComment() {
        return cordComment;
    }

    public void setCordComment(String cordComment) {
        this.cordComment = cordComment;
    }

    public Date getCordDate() {
        return cordDate;
    }

    public void setCordDate(Date cordDate) {
        this.cordDate = cordDate;
    }

    public String getClearedGrad() {
        return clearedGrad;
    }

    public void setClearedGrad(String clearedGrad) {
        this.clearedGrad = clearedGrad;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
