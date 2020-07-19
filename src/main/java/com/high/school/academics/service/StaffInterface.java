package com.high.school.academics.service;

import com.high.school.academics.model.TeacherLeaves;
import com.high.school.academics.model.Teachers;
import com.high.school.exceptions.BadRequestException;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import java.util.Date;

public interface StaffInterface {
    DataTablesOutput<Teachers> findLeaveOuts(DataTablesInput input);

    DataTablesOutput<Teachers> findSchoolTeachers(DataTablesInput input);

    DataTablesOutput<TeacherLeaves> onLeaveTeachers(DataTablesInput input);

    DataTablesOutput<TeacherLeaves> pendingLeaveTeachers(DataTablesInput input);

    DataTablesOutput<TeacherLeaves> returnedLeaveTeachers(DataTablesInput input);

    DataTablesOutput<TeacherLeaves> nameLeaveTeachers(DataTablesInput input, String name);

    void grantLeaveOut(Date left, Date returnDate, String reason) throws BadRequestException;

    void returnLeaveOut(Date returnDate) throws BadRequestException;

    void clearAllPending();

    void recordStaffTimeOut(Long regCode) throws BadRequestException;

    void recordTimeOut(Long regCode) throws BadRequestException;

    void recordTimeIn(Long regCode) throws BadRequestException;

    void recordStaffTimeIn(Long regCode) throws BadRequestException;
}
