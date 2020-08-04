package com.high.school.academics.repo;

import com.high.school.academics.model.StudSubjects;
import com.high.school.academics.model.TeacherSubjects;
import com.high.school.academics.model.Teachers;
import com.high.school.students.model.Forms;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface TeacherSubjectsRepo extends JpaRepository<TeacherSubjects,Long>, DataTablesRepository<TeacherSubjects, Long> {
    TeacherSubjects findFirstByTeachersAndFormsAndSubjects(Teachers t, Forms forms, StudSubjects s);


    List<TeacherSubjects> findByTeachers(Teachers t);

    TeacherSubjects findByTeacherSubjectCode(Long teacherSubjectCode);

    List<TeacherSubjects> findByTeachersAndForms(Teachers t, Forms forms);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM teacher_subjects WHERE subject_teachers = ?1",
            nativeQuery = true)
    void deleteAssocSubjects(Teachers t);

    Page<TeacherSubjects> findByTeachersAndForms_ClassCode(Teachers teachers, Long classCode, Pageable pageable);

    Page<TeacherSubjects> findByTeachersAndForms_ClassCodeAndSubjects_NameContainingIgnoreCase(Teachers teachers, String term, Long classCode, Pageable pageable);

    TeacherSubjects findByFormsAndSubjects(Forms className, StudSubjects subject);
}
