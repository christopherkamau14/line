package com.high.school.students.model;

import javax.persistence.*;

@Entity
@Table(name="sources_table")
public class Sources {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "source_id")
    private Long sourceCode;

    @Column(name = "source_name")
    private String sourceName;

    @Column(name = "source_desc")
    private String sourceDesc;

    public Long getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(Long sourceCode) {
        this.sourceCode = sourceCode;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getSourceDesc() {
        return sourceDesc;
    }

    public void setSourceDesc(String sourceDesc) {
        this.sourceDesc = sourceDesc;
    }
}
