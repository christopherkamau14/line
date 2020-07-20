package com.high.school.academics.repo;

import com.high.school.academics.model.Clearance;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClearanceRepo extends JpaRepository<Clearance,Long>, DataTablesRepository<Clearance, Long> {
    Clearance findByClearanceCode(Long clearanceCode);
}
