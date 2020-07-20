package com.high.school.examination.repo;

import com.high.school.examination.model.ExamType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamTypeRepo extends JpaRepository<ExamType,Long>, DataTablesRepository<ExamType,Long> {
    ExamType findByTypeCode(Long fCode);

    Page<ExamType> findByTypeNameContainingIgnoreCase(String term, Pageable pageable);
}
