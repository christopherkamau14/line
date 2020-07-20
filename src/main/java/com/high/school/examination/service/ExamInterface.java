package com.high.school.examination.service;

import com.high.school.academics.model.StudSubjects;
import com.high.school.academics.model.TeacherSubjects;
import com.high.school.academics.model.Teachers;
import com.high.school.examination.model.*;
import com.high.school.exceptions.BadRequestException;
import com.high.school.students.model.Forms;
import com.high.school.students.model.Student;
import com.high.school.students.model.Term;
import com.high.school.students.model.Year;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import java.util.List;

public interface ExamInterface {
    void deleteType(Long code);

    void postType(ExamType examType);

    DataTablesOutput<ExamType> getTypeTable(DataTablesInput input);

    ExamType typeEdit(Long fCode);

    Page<Year> pageYear(Pageable pageable, String term);

    Page<Term> pageTerm(Pageable pageable, String term);

    Page<ExamType> pageType(Pageable pageable, String term);

    void postReg(ExamRegister examRegister);

    DataTablesOutput<ExamRegister> getExamRegTable(DataTablesInput input);

    void deleteExamReg(Long code);

    Page<ExamRegister> pageRegister(Pageable pageable, String term);

    Page<Teachers> pageTeachers(Pageable pageable, String term);

    ExamRecording postRecording(ExamRecording examRecording) throws BadRequestException;

    DataTablesOutput<ExamRecording> getExamRecTable(DataTablesInput input, Long code);

    DataTablesOutput<ExamRecording> getExamRecClassTable(DataTablesInput input, Long code, Long classCode);

    DataTablesOutput<ExamRecording> getExamRecTeaTable(DataTablesInput input, Long code, Long classCode, Long teacher);

    DataTablesOutput<ExamRecording> getExamRecSubTable(DataTablesInput input, Long code, Long classCode, Long teacher, Long subject);

    DataTablesOutput<ExamRecording> getExamRecStudTable(DataTablesInput input, Long code, Long classCode, Long teacher, Long subject, Long student);

    Page<TeacherSubjects> pageSubject(Pageable pageable, String term, Long code,Long classCode);

    Page<Student> pageStudent(Pageable pageable, String term, Long code);

    void postMissed(Student student, Forms forms, TeacherSubjects subjects, ExamRegister exam) throws BadRequestException;

    void undoMissExam(Student student, Forms forms, TeacherSubjects subjects, ExamRegister exam) throws BadRequestException;

    ExamRecording checkExcel(String student, String subject, String form, String teacher);

    TeacherSubjects findTeacherSubject(Forms forms, StudSubjects subject, Teachers teachers);

    Student findStudent(String student);

    Forms findForm(String form);

    StudSubjects findSubjects(String subject);
    
    ExamRegister findExam(String exam);

    Term findTerm(String term);

    Year findYear(String year);

    Teachers findTeachers(String teacher);

    void postExcel(ExamRecording examRecording) throws BadRequestException;

    List<ExamBean> drawRecords(Forms code, TeacherSubjects subject, ExamRegister exam);


    void positionExams(ExamRegister exam, Forms code) throws BadRequestException;



    void combineExam(CombineBean combineBean) throws BadRequestException;

    PositioningBean positioningBean(ExamRegister exam, Forms forms, Student student);

    void lockExams(ExamRegister exam) throws BadRequestException;

    void unLockExam(ExamRegister exam);
}
