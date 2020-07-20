package com.high.school.examination.repo;

import com.high.school.examination.model.ExamRegister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRegRepo extends JpaRepository<ExamRegister,Long>, DataTablesRepository<ExamRegister,Long> {
    ExamRegister findByExamCode(Long examCode);

    Page<ExamRegister> findByExamNameContainingIgnoreCase(String term, Pageable pageable);

    ExamRegister findFirstByExamNameIgnoreCase(String exam);
}
