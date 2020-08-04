package com.high.school.timetabling.repo;

import com.high.school.timetabling.model.Days;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DaysRepo extends JpaRepository<Days,Long>, DataTablesRepository<Days,Long> {

    Days findByDayCode(Long dayCode);

    Days findByCode(String code);

    Page<Days> findByNameContainingIgnoreCase(String term, Pageable pageable);
}
