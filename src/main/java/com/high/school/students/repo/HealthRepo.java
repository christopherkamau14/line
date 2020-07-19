package com.high.school.students.repo;

import com.high.school.students.model.Health;
import com.high.school.students.model.Sources;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface HealthRepo extends JpaRepository<Health,Long>, PagingAndSortingRepository<Health,Long>, DataTablesRepository<Health,Long> {
    Page<Health> findByCondNameContainingIgnoreCase(String term, Pageable pageable);

    Health findByCondCode(Long condCode);

    Health findByCondNameIgnoreCase(String condName);
}
