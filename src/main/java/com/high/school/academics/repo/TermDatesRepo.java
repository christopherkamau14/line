package com.high.school.academics.repo;

import com.high.school.academics.model.TermDates;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermDatesRepo extends JpaRepository<TermDates,Long>, DataTablesRepository<TermDates, Long> {
    TermDates findByTermDateCode(Long termDateCode);
}
