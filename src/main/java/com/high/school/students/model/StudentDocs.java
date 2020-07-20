package com.high.school.students.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name="student_docs")
public class StudentDocs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="stdocs_id")
    private Long stdocCode;

    @Column(name = "std_file_id")
    private String fileId;

    @Column(name = "cd_loc_name")
    private String uploadedFileName;

    @Column(name = "cd_content_type")
    private String contentType;

    @ManyToOne
    @JoinColumn (name = "main_docs")
    private Document document;

    @ManyToOne
    @JoinColumn(name="doc_std_id")
    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Long getStdocCode() {
        return stdocCode;
    }

    public void setStdocCode(Long stdocCode) {
        this.stdocCode = stdocCode;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getUploadedFileName() {
        return uploadedFileName;
    }

    public void setUploadedFileName(String uploadedFileName) {
        this.uploadedFileName = uploadedFileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
