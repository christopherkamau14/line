package com.high.school.examination.repo;

import com.high.school.academics.model.StudSubjects;
import com.high.school.academics.model.TeacherSubjects;
import com.high.school.examination.model.ExamRecording;
import com.high.school.examination.model.ExamRegister;
import com.high.school.students.model.Forms;
import com.high.school.students.model.Student;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExamRecordingRepo extends JpaRepository<ExamRecording,Long>, DataTablesRepository<ExamRecording,Long> {
    ExamRecording findByExamRecordCode(Long examRecordCode);

    ExamRecording findByStudentAndExamRegisterAndTeacherSubjects_Subjects(Student student, ExamRegister examRegister, StudSubjects subjects);

    ExamRecording findFirstByStudent_admNoAndTeacherSubjects_Subjects_nameAndForms_Abbr(String student, String subject, String form);

    ExamRecording findByStudentAndExamRegisterAndTeacherSubjectsAndForms(Student student, ExamRegister examRegister, TeacherSubjects teacherSubjects, Forms forms);

    List<ExamRecording> findByTeacherSubjectsAndFormsAndExamRegister(TeacherSubjects subject, Forms code, ExamRegister exam);


    List<ExamRecording> findByExamRegister(ExamRegister e);


    Long countStudentDistinctByTeacherSubjects_Subjects(StudSubjects subjects);

    Long countStudentDistinctByTeacherSubjects_SubjectsAndGradingSystem_Grade(StudSubjects subjects, String s);

    Long countStudentDistinctByFormsAndTeacherSubjects_SubjectsAndGradingSystem_Grade(Forms forms, StudSubjects subjects, String s);


    Long countStudentDistinctByFormsAndTeacherSubjects_Subjects(Forms forms, StudSubjects subjects);


    Long countByStudent_GenderAndTeacherSubjects_SubjectsAndForms(String gender, StudSubjects subjects, Forms forms);

    Long countByStudent_GenderAndTeacherSubjects_SubjectsAndFormsAndGradingSystem_Grade(String gender, StudSubjects subjects, Forms forms, String e);

    List<ExamRecording> findByExamRegisterOrderByForms(ExamRegister exam);

    List<ExamRecording> findByExamRegisterAndStudent(ExamRegister exam, Student e);

    List<ExamRecording> findByExamRegisterAndForms(ExamRegister exam, Forms code);

    List<ExamRecording> findByExamRegisterAndTeacherSubjects_Subjects(ExamRegister exam, StudSubjects subjects);

    List<ExamRecording> findByExamRegisterAndStudentAndTeacherSubjects_Subjects(ExamRegister exam, Student s, StudSubjects subjects);

    List<ExamRecording> findByFormsAndExamRegister(Long classCode, ExamRegister exam);

    Long countTeacherSubjectsDistinctByStudentAndExamRegister(Student e, ExamRegister exam);

    Long countStudentDistinctByTeacherSubjects_SubjectsAndForms(StudSubjects subjects, Forms forms);

    Long countStudentDistinctByTeacherSubjects_SubjectsAndGradingSystem_GradeAndForms(StudSubjects subjects, String a, Forms forms);

    Long countStudentDistinctByExamRegisterAndFormsAndTeacherSubjects_Subjects(ExamRegister exam, Forms forms, StudSubjects subjects);

    Long countStudentDistinctByExamRegisterAndFormsAndTeacherSubjects_SubjectsAndGradingSystem_Grade(ExamRegister exam, Forms forms, StudSubjects subjects, String a);

    Long countStudentDistinctByExamRegisterAndTeacherSubjects_SubjectsAndForms(ExamRegister exam, StudSubjects subjects, Forms forms);

    Long countStudentDistinctByExamRegisterAndTeacherSubjects_SubjectsAndGradingSystem_GradeAndForms(ExamRegister exam, StudSubjects subjects, String s, Forms forms);

    Long countByExamRegisterAndStudent_GenderAndTeacherSubjects_SubjectsAndFormsAndGradingSystem_Grade(ExamRegister exam, String gender, StudSubjects subjects, Forms forms, String a);

    Long countByExamRegisterAndStudent_GenderAndTeacherSubjects_SubjectsAndForms(ExamRegister exam, String gender, StudSubjects subjects, Forms forms);

    List<ExamRecording> findByExamRegisterAndFormsAndStudent(ExamRegister exam, Forms forms, Student student);
}
