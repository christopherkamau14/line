package com.high.school.students.model;

import com.high.school.Locale.model.Country;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Table(name="parents_table")
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "parent_id")
    private Long parCode;

    @Column(name = "father_name")
    private String nameFather;

    @Column(name = "mother_name")
    private String nameMother;

    @Column(name = "father_address")
    private String addressFather;

    @Column(name = "mother_address")
    private String addressMother;

    @Column(name = "father_tel_no")
    private String telFather;

    @Column(name = "mother_tel_no")
    private String telMother;

    @Column(name = "father_email")
    private String emailFather;

    @Column(name = "mother_email")
    private String emailMother;

    @Column(name = "parent_guardian")
    private String parGur;

    @Column(name = "parent_id_no")
    private String idNo;

    public String getMailReq() {
        return mailReq;
    }

    public void setMailReq(String mailReq) {
        this.mailReq = mailReq;
    }

    @Transient
    private String mailReq;

    @Column(name = "email_required")
    private boolean emailRequired;

    @Transient
    MultipartFile file;

    @Lob
    @Column(name="org_logo")
    private byte[] photo;

    @ManyToOne
    @JoinColumn(name="father_proffession")
    Proffession fatherProffession;

    @ManyToOne
    @JoinColumn(name="mother_prof")
    Proffession motherProffession;


    public Long getParCode() {
        return parCode;
    }

    public void setParCode(Long parCode) {
        this.parCode = parCode;
    }

    public String getIdNo() {
        return idNo;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getNameFather() {
        return nameFather;
    }

    public void setNameFather(String nameFather) {
        this.nameFather = nameFather;
    }

    public String getNameMother() {
        return nameMother;
    }

    public void setNameMother(String nameMother) {
        this.nameMother = nameMother;
    }

    public String getAddressFather() {
        return addressFather;
    }

    public void setAddressFather(String addressFather) {
        this.addressFather = addressFather;
    }

    public String getAddressMother() {
        return addressMother;
    }

    public void setAddressMother(String addressMother) {
        this.addressMother = addressMother;
    }

    public String getTelFather() {
        return telFather;
    }

    public void setTelFather(String telFather) {
        this.telFather = telFather;
    }

    public String getTelMother() {
        return telMother;
    }

    public void setTelMother(String telMother) {
        this.telMother = telMother;
    }

    public String getEmailFather() {
        return emailFather;
    }

    public void setEmailFather(String emailFather) {
        this.emailFather = emailFather;
    }

    public String getEmailMother() {
        return emailMother;
    }

    public void setEmailMother(String emailMother) {
        this.emailMother = emailMother;
    }

    public String getParGur() {
        return parGur;
    }

    public void setParGur(String parGur) {
        this.parGur = parGur;
    }

    public boolean isEmailRequired() {
        return emailRequired;
    }

    public void setEmailRequired(boolean emailRequired) {
        this.emailRequired = emailRequired;
    }

    public Proffession getFatherProffession() {
        return fatherProffession;
    }

    public void setFatherProffession(Proffession fatherProffession) {
        this.fatherProffession = fatherProffession;
    }

    public Proffession getMotherProffession() {
        return motherProffession;
    }

    public void setMotherProffession(Proffession motherProffession) {
        this.motherProffession = motherProffession;
    }

}
