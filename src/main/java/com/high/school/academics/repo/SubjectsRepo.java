package com.high.school.academics.repo;

import com.high.school.academics.model.StudSubjects;
import com.high.school.examination.model.ExamRegister;
import com.high.school.students.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectsRepo extends JpaRepository<StudSubjects,Long>, DataTablesRepository<StudSubjects, Long> {
    StudSubjects findBySubjectCode(Long subjectCode);

    List<StudSubjects> findByAssigned(String pending);

    Page<StudSubjects> findByNameContainingIgnoreCase(String term, Pageable pageable);


    StudSubjects findFirstByNameIgnoreCase(String subject);
}
