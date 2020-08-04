package com.high.school.timetabling.repo;

import com.high.school.academics.model.Teachers;
import com.high.school.students.model.Forms;
import com.high.school.timetabling.model.Allocations;
import com.high.school.timetabling.model.Days;
import com.high.school.timetabling.model.Lessons;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AllocationRepo extends JpaRepository<Allocations,Long>, DataTablesRepository<Allocations,Long> {
    Allocations findByFormsAndLessons(Forms forms, Lessons lessons);

    Allocations findByAllocationCode(Long allocationCode);

    List<Allocations> findByForms(Forms forms);

    Allocations findByFormsAndLessonsAndDays(Forms forms, Lessons lessons, Days days);

    List<Allocations> findByTeachers(Teachers teachers);
}
