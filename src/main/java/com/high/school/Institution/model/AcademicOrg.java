package com.high.school.Institution.model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="academic_org")
public class AcademicOrg {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "school_id")
    private Long schoolCode;

    @Column(name="physical_address")
    private String physicalAddress;

    @Lob
    @Column(name="school_logo")
    private byte[] schoolLogo;

    @Column(name="school_mobile")
    private String schoolMobile;


    @Column(name="school_name")
    private String schoolName;

    @Column(name="school_phone")
    private String schoolPhone;

    @Column(name="school_website")
    private String schoolWebsite;

    @Lob
    @Column(name="school_motto")
    private String motto;

    @Lob
    @Column(name="school_mission")
    private String mission;

    @Lob
    @Column(name="school_vision")
    private String vision;

    @Transient
    MultipartFile file;


    @Column(name="date_incorp")
    @Temporal(TemporalType.DATE)
    private Date dateIncorp;

    @Column(name="email_address")
    private String emailAddress;

    @Column(name="school_address")
    private String address;

    public Long getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(Long schoolCode) {
        this.schoolCode = schoolCode;
    }

    public String getPhysicalAddress() {
        return physicalAddress;
    }

    public void setPhysicalAddress(String physicalAddress) {
        this.physicalAddress = physicalAddress;
    }

    public byte[] getSchoolLogo() {
        return schoolLogo;
    }

    public void setSchoolLogo(byte[] schoolLogo) {
        this.schoolLogo = schoolLogo;
    }

    public String getSchoolMobile() {
        return schoolMobile;
    }

    public void setSchoolMobile(String schoolMobile) {
        this.schoolMobile = schoolMobile;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolPhone() {
        return schoolPhone;
    }

    public void setSchoolPhone(String schoolPhone) {
        this.schoolPhone = schoolPhone;
    }

    public String getSchoolWebsite() {
        return schoolWebsite;
    }

    public void setSchoolWebsite(String schoolWebsite) {
        this.schoolWebsite = schoolWebsite;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Date getDateIncorp() {
        return dateIncorp;
    }

    public void setDateIncorp(Date dateIncorp) {
        this.dateIncorp = dateIncorp;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public String getVision() {
        return vision;
    }

    public void setVision(String vision) {
        this.vision = vision;
    }
}
