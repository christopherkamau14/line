package com.high.school.academics.repo;

import com.high.school.academics.model.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepo extends JpaRepository<Staff,Long>, DataTablesRepository<Staff, Long> {
    Staff findByStaffCode(Long tCode);

    Staff findByIdNo(String idNo);

    Staff findByPhoneNumber(String phoneNumber);

    Page<Staff> findByNameContainingIgnoreCase(String term, Pageable pageable);
}
