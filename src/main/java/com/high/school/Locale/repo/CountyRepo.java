package com.high.school.Locale.repo;

import com.high.school.Locale.model.County;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CountyRepo extends JpaRepository<County,Long>, PagingAndSortingRepository<County,Long>,DataTablesRepository<County,Long> {
    Page<County> findByCountyName(String term, Pageable pageable);

    Page<County> findByCountyNameContainingIgnoreCase(String term, Pageable pageable);

    County findByCountyCode(Long county);

    County findByCountyNameIgnoreCase(String countyName);
}
