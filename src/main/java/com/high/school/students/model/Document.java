package com.high.school.students.model;

import javax.persistence.*;

@Entity
@Table(name="docs_table")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "doc_id")
    private Long docCode;

    @Column(name = "doc_name")
    private String docName;

    @Column(name = "doc_desc")
    private String docDesc;

    @Column(name = "document_required")
    private String docRequired;

    public Long getDocCode() {
        return docCode;
    }

    public void setDocCode(Long docCode) {
        this.docCode = docCode;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocDesc() {
        return docDesc;
    }

    public void setDocDesc(String docDesc) {
        this.docDesc = docDesc;
    }

    public String getDocRequired() {
        return docRequired;
    }

    public void setDocRequired(String docRequired) {
        this.docRequired = docRequired;
    }
}
