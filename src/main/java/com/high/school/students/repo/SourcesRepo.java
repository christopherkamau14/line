package com.high.school.students.repo;

import com.high.school.students.model.Sources;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SourcesRepo extends JpaRepository<Sources,Long>, PagingAndSortingRepository<Sources,Long>, DataTablesRepository<Sources,Long> {
    Page<Sources> findBySourceNameContainingIgnoreCase(String term, Pageable pageable);

    Sources findBySourceCode(Long sourceCode);

    Sources findBySourceNameIgnoreCase(String sourceName);
}
