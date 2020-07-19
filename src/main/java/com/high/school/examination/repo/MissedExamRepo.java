package com.high.school.examination.repo;

import com.high.school.academics.model.TeacherSubjects;
import com.high.school.examination.model.ExamRegister;
import com.high.school.examination.model.MIssedExams;
import com.high.school.students.model.Forms;
import com.high.school.students.model.Student;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MissedExamRepo extends JpaRepository<MIssedExams,Long>, DataTablesRepository<MIssedExams,Long> {
    MIssedExams findFirstByTeacherSubjectsAndFormsAndExamRegisterAndStudent(TeacherSubjects subjects, Forms forms, ExamRegister exam, Student student);

    MIssedExams findByTeacherSubjectsAndFormsAndExamRegisterAndStudent(TeacherSubjects subjects, Forms forms, ExamRegister exam, Student student);


    List<MIssedExams> findByExamRegisterOrderBySubjectName(ExamRegister e);

    List<MIssedExams> findByExamRegisterAndFormsOrderBySubjectName(ExamRegister examRegister, Forms forms);
}
