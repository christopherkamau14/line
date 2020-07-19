package com.high.school.students.repo;

import com.high.school.students.model.LeaveOuts;
import com.high.school.students.model.Student;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LeaveOutsRepo extends PagingAndSortingRepository<LeaveOuts,Long>, JpaRepository<LeaveOuts,Long>, DataTablesRepository<LeaveOuts,Long> {

    LeaveOuts findByStudent_StIdAndDateReturnedIsNull(Long stId);

    LeaveOuts findByStudentAndDateReturnedIsNull(Student s);
}
