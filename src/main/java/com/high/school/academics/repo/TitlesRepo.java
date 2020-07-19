package com.high.school.academics.repo;

import com.high.school.academics.model.Titles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TitlesRepo extends JpaRepository<Titles,Long>, DataTablesRepository<Titles, Long> {
    Titles findByTitleCode(Long titleCode);

    Page<Titles> findByTitleNameContainingIgnoreCase(String term, Pageable pageable);
}
