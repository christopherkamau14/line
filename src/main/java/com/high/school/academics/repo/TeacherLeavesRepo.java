package com.high.school.academics.repo;

import com.high.school.academics.model.TeacherLeaves;
import com.high.school.academics.model.Teachers;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherLeavesRepo extends JpaRepository<TeacherLeaves,Long>, DataTablesRepository<TeacherLeaves, Long> {
    TeacherLeaves findByTeachersAndDateReturnedIsNull(Teachers t);
}
