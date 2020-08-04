package com.high.school.academics.repo;

import com.high.school.academics.model.ClassSubjects;
import com.high.school.academics.model.StudSubjects;
import com.high.school.students.model.Forms;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassSubjectsRepo extends JpaRepository<ClassSubjects,Long>, DataTablesRepository<ClassSubjects, Long> {
    ClassSubjects findByClassSubjectCode(Long classSubjectCode);

    ClassSubjects findFirstByFormsAndSubjects(Forms forms, StudSubjects subjects);
}
