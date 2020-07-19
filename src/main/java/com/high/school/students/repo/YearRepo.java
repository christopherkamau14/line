package com.high.school.students.repo;

import com.high.school.students.model.Year;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface YearRepo extends JpaRepository<Year,Long>, PagingAndSortingRepository<Year,Long>, DataTablesRepository<Year,Long> {
    Page<Year> findByYearNameContainingIgnoreCase(String term, Pageable pageable);

    Year findByYearCode(Long yearCode);

    Year findByYearNameIgnoreCase(String yearName);

    Year findFirstByYearName(String year);
}
