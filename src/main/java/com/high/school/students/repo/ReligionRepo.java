package com.high.school.students.repo;

import com.high.school.students.model.Religion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ReligionRepo extends JpaRepository<Religion,Long>, PagingAndSortingRepository<Religion,Long>, DataTablesRepository<Religion,Long> {
    Page<Religion> findByRelNameContainingIgnoreCase(String term, Pageable pageable);

    Religion findByRelCode(Long relCode);

    Religion findByRelNameIgnoreCase(String relName);
}
