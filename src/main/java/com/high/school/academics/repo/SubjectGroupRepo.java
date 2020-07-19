package com.high.school.academics.repo;

import com.high.school.academics.model.SubjectGroups;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectGroupRepo extends JpaRepository<SubjectGroups,Long>, DataTablesRepository<SubjectGroups, Long> {
    SubjectGroups findByGroupCode(Long groupCode);
}
