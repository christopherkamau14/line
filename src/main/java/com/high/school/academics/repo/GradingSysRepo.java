package com.high.school.academics.repo;

import com.high.school.academics.model.GradingSystem;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradingSysRepo extends JpaRepository<GradingSystem,Long>, DataTablesRepository<GradingSystem, Long> {
    GradingSystem findByGradingCode(Long gradingCode);

    GradingSystem findByGradeIgnoreCase(String e);
}
