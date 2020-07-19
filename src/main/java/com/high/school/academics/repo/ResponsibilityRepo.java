package com.high.school.academics.repo;

import com.high.school.academics.model.Responsibility;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponsibilityRepo extends JpaRepository<Responsibility,Long>, DataTablesRepository<Responsibility, Long> {
    Responsibility findByResponsibilityCode(Long responsibilityCode);


    Page<Responsibility> findByCategoryAndResponsibilityNameContainingIgnoreCase(String t, String term, Pageable pageable);

    Page<Responsibility> findByCategory(String t, Pageable pageable);
}
