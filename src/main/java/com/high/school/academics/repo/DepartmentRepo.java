package com.high.school.academics.repo;

import com.high.school.academics.model.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department,Long>, DataTablesRepository<Department, Long> {
    Page<Department> findByDepartmentNameContainingIgnoreCase(String term, Pageable pageable);

    Department findByDepartmentCode(Long departmentCode);
}
