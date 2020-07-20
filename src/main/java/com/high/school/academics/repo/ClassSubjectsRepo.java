package com.high.school.academics.repo;

import com.high.school.academics.model.ClassSubjects;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassSubjectsRepo extends JpaRepository<ClassSubjects,Long>, DataTablesRepository<ClassSubjects, Long> {
    ClassSubjects findByClassSubjectCode(Long classSubjectCode);
}
