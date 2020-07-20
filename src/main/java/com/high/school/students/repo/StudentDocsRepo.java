package com.high.school.students.repo;

import com.high.school.students.model.StudentDocs;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StudentDocsRepo extends JpaRepository<StudentDocs,Long>, PagingAndSortingRepository<StudentDocs,Long>, DataTablesRepository<StudentDocs,Long> {
    StudentDocs findByStudent_StIdAndStdocCode(Long stdId, Long docId);

    StudentDocs findByStdocCode(Long docId);
}
