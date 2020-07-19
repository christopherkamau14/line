package com.high.school.students.model;

import com.high.school.academics.model.Teachers;
import com.high.school.students.repo.FormsRepo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Table(name="current_class")
public class Forms {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "class_id")
    private Long classCode;

    @Column(name = "class_number")
    private String classNo;

    @Column(name = "class_abbreviation")
    private String classDesc;

    @Column(name = "class_active")
    private String active;

    @Column(name = "class_capacity")
    private String capacity;

    @ManyToOne
    @JoinColumn(name="class_block")
    private Block block;

    @ManyToOne
    @JoinColumn(name="teacher_class")
    private Teachers teachers;

    @Column(name="class_abbr")
    private String abbr;


    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public Long getClassCode() {
        return classCode;
    }

    public void setClassCode(Long classCode) {
        this.classCode = classCode;
    }



    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public String getClassDesc() {
        return classDesc;
    }

    public void setClassDesc(String classDesc) {
        this.classDesc = classDesc;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }


    public Teachers getTeachers() {
        return teachers;
    }

    public void setTeachers(Teachers teachers) {
        this.teachers = teachers;
    }
}
