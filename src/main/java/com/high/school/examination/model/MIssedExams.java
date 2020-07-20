package com.high.school.examination.model;

import com.high.school.academics.model.TeacherSubjects;
import com.high.school.academics.model.Teachers;
import com.high.school.students.model.Forms;
import com.high.school.students.model.Student;

import javax.persistence.*;

@Entity
@Table(name="missed_exams")
public class MIssedExams {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "missed_exam_id")
    private Long missedCode;

    @Column(name = "missed_name")
    private String subjectName;

    @Column(name="missed_class_name")
    private String className;

    @ManyToOne
    @JoinColumn(name = "missed_student")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "exam_subject")
    private TeacherSubjects teacherSubjects;

    @ManyToOne
    @JoinColumn(name = "exam_class")
    private Forms forms;

    @ManyToOne
    @JoinColumn(name = "exam_register")
    private ExamRegister examRegister;

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Long getMissedCode() {
        return missedCode;
    }

    public void setMissedCode(Long missedCode) {
        this.missedCode = missedCode;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public TeacherSubjects getTeacherSubjects() {
        return teacherSubjects;
    }

    public void setTeacherSubjects(TeacherSubjects teacherSubjects) {
        this.teacherSubjects = teacherSubjects;
    }

    public Forms getForms() {
        return forms;
    }

    public void setForms(Forms forms) {
        this.forms = forms;
    }

    public ExamRegister getExamRegister() {
        return examRegister;
    }

    public void setExamRegister(ExamRegister examRegister) {
        this.examRegister = examRegister;
    }

}
