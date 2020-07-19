package com.high.school.academics.repo;

import com.high.school.academics.model.Staff;
import com.high.school.academics.model.StaffRegister;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface StaffRegisterRepo extends JpaRepository<StaffRegister,Long>, DataTablesRepository<StaffRegister, Long> {
    StaffRegister findByStaffAndRegisterDate(Staff staff, Date date);

    StaffRegister findByStaffAndRegisterDateAndTimeInNotNull(Staff staff, Date date);

    StaffRegister findByStaffRegisterCode(Long staffRegisterCode);
}
