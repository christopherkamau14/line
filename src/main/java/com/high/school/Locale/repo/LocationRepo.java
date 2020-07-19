package com.high.school.Locale.repo;

import com.high.school.Locale.model.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LocationRepo extends JpaRepository<Location,Long>, PagingAndSortingRepository<Location,Long>, DataTablesRepository<Location,Long> {
    Page<Location> findByLouNameContainingIgnoreCase(String term, Pageable pageable);

    Location findByLouCode(Long location);

    Location findByLouNameIgnoreCase(String louName);
}
