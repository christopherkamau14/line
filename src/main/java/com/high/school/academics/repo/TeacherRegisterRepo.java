package com.high.school.academics.repo;

import com.high.school.academics.model.TeacherRegister;
import com.high.school.academics.model.Teachers;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface TeacherRegisterRepo extends JpaRepository<TeacherRegister,Long>, DataTablesRepository<TeacherRegister, Long> {

    TeacherRegister findByTeachersAndRegisterDate(Teachers teachers, Date date);


    TeacherRegister findByTeacherRegisterCode(Long teacherRegisterCode);

    TeacherRegister findByTeachersAndRegisterDateAndTimeInNotNull(Teachers teachers, Date date);
}
