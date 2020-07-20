package com.high.school.students.model;

import com.high.school.Fee.model.FeeCategory;
import com.high.school.Locale.model.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="students_table")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="stud_id")
    private Long stId;

    @Column(name="stud_admno")
    private String admNo;

    @Column(name="stud_name")
    private String name;

    @Column(name="stud_upi")
    private String upi;

    @Column(name="stud_birthcert")
    private String birthCertNo;

    @Column(name="stud_birthcert_number")
    private String indexNo;

    @Lob
    @Column(name="stud_photo")
    private byte[] photo;

    @Column(name="stud_dob")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(name="stud_doa")
    @Temporal(TemporalType.DATE)
    private Date admDate;

    @Column(name="stud_doc")
    @Temporal(TemporalType.DATE)
    private Date completionDate;

    @Column(name="stud_gender")
    private String gender;

    @Column(name="stud_address")
    private String address;

    @Column(name="stud_grad")
    private String graduate;

    @Column(name="kcpe_marks")
    private String kcpeMarks;

    @Column(name="kcpe_grade")
    private String kcpeGrade;

    @Column(name="primary_school")
    private String primarySchool;

    @Column(name="parent_contact")
    private String parentContact;

    @Column(name="student_contact")
    private String studentContact;

    @Column(name="student_email")
    private String studEmail;

    @ManyToOne
    @JoinColumn(name="country")
    Country country;

    @ManyToOne
    @JoinColumn(name="county")
    County county;

    @ManyToOne
    @JoinColumn(name="sub_county")
    private SubCounty subCounty;

    @ManyToOne
    @JoinColumn(name="location")
    private Location location;

    @ManyToOne
    @JoinColumn(name="sub_location")
    private SubLocation subLocation;

    @ManyToOne
    @JoinColumn(name="village")
    private Village village;

    @ManyToOne
    @JoinColumn(name="student_sources")
    private Sources sources;

    @ManyToOne
    @JoinColumn(name="student_health")
    private Health health;


    @Column(name="in_session")
    private String inSession;

    @Transient
    MultipartFile file;

    public String getInSession() {
        return inSession;
    }

    public void setInSession(String inSession) {
        this.inSession = inSession;
    }

    public String getKcpeMarks() {
        return kcpeMarks;
    }

    public void setKcpeMarks(String kcpeMarks) {
        this.kcpeMarks = kcpeMarks;
    }

    public String getKcpeGrade() {
        return kcpeGrade;
    }

    public void setKcpeGrade(String kcpeGrade) {
        this.kcpeGrade = kcpeGrade;
    }

    public String getPrimarySchool() {
        return primarySchool;
    }

    public void setPrimarySchool(String primarySchool) {
        this.primarySchool = primarySchool;
    }

    public String getParentContact() {
        return parentContact;
    }

    public void setParentContact(String parentContact) {
        this.parentContact = parentContact;
    }

    public String getStudentContact() {
        return studentContact;
    }

    public void setStudentContact(String studentContact) {
        this.studentContact = studentContact;
    }

    public String getStudEmail() {
        return studEmail;
    }

    public void setStudEmail(String studEmail) {
        this.studEmail = studEmail;
    }

    public Sources getSources() {
        return sources;
    }

    public void setSources(Sources sources) {
        this.sources = sources;
    }

    public Health getHealth() {
        return health;
    }

    public void setHealth(Health health) {
        this.health = health;
    }

    public StudStatus getStudStatus() {
        return studStatus;
    }

    public void setStudStatus(StudStatus studStatus) {
        this.studStatus = studStatus;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    @ManyToOne
    @JoinColumn(name="student_status")
    private StudStatus studStatus;

    @ManyToOne
    @JoinColumn(name="parent")
    private Parent parent;

    @ManyToOne
    @JoinColumn(name="fee_category")
    private FeeCategory feeCategory;

    @ManyToOne
    @JoinColumn(name="student_dorm")
    private Dorm dorm;

    @ManyToOne
    @JoinColumn(name="student_class")
    private Forms forms;

    @ManyToOne
    @JoinColumn(name="student_term")
    private Term term;

    @ManyToOne
    @JoinColumn(name="student_religion")
    private Religion religion;

    @ManyToOne
    @JoinColumn(name="student_year")
    private Year year;


    public Religion getReligion() {
        return religion;
    }

    public void setReligion(Religion religion) {
        this.religion = religion;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Dorm getDorm() {
        return dorm;
    }

    public void setDorm(Dorm dorm) {
        this.dorm = dorm;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public County getCounty() {
        return county;
    }

    public void setCounty(County county) {
        this.county = county;
    }
    public String getUpi() {
        return upi;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public void setUpi(String upi) {
        this.upi = upi;
    }
    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public FeeCategory getFeeCategory() {
        return feeCategory;
    }

    public void setFeeCategory(FeeCategory feeCategory) {
        this.feeCategory = feeCategory;
    }

    public SubCounty getSubCounty() {
        return subCounty;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setSubCounty(SubCounty subCounty) {
        this.subCounty = subCounty;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public SubLocation getSubLocation() {
        return subLocation;
    }

    public void setSubLocation(SubLocation subLocation) {
        this.subLocation = subLocation;
    }

    public Village getVillage() {
        return village;
    }

    public void setVillage(Village village) {
        this.village = village;
    }

    public Long getStId() {
        return stId;
    }

    public void setStId(Long stId) {
        this.stId = stId;
    }

    public String getAdmNo() {
        return admNo;
    }

    public void setAdmNo(String admNo) {
        this.admNo = admNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getAdmDate() {
        return admDate;
    }

    public void setAdmDate(Date admDate) {
        this.admDate = admDate;
    }

    public String getGraduate() {
        return graduate;
    }

    public void setGraduate(String graduate) {
        this.graduate = graduate;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getBirthCertNo() {
        return birthCertNo;
    }

    public void setBirthCertNo(String birthCertNo) {
        this.birthCertNo = birthCertNo;
    }

    public String getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(String indexNo) {
        this.indexNo = indexNo;
    }
}
