package com.high.school.academics.model;

import javax.persistence.*;

@Entity
@Table(name="department_table")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "department_id")
    private Long departmentCode;

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "department_code")
    private String code;

    @Column(name = "department_desc")
    private String departmentDesc;

    @Column(name = "department_sequence")
    private String departmentSeq;

    public Long getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(Long departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentDesc() {
        return departmentDesc;
    }

    public void setDepartmentDesc(String departmentDesc) {
        this.departmentDesc = departmentDesc;
    }

    public String getDepartmentSeq() {
        return departmentSeq;
    }

    public void setDepartmentSeq(String departmentSeq) {
        this.departmentSeq = departmentSeq;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
