package com.high.school.academics.repo;

import com.high.school.academics.model.Teachers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepo extends JpaRepository<Teachers,Long>, DataTablesRepository<Teachers, Long> {
    Teachers findByTeacherCode(Long teacherCode);

    Page<Teachers> findByNameContainingIgnoreCase(String term, Pageable pageable);

    List<Teachers> findByInSession(String pending);

    Teachers findByIdNo(String idNo);

    Teachers findByTscNo(String tscNo);

    Teachers findByPhoneNumber(String phoneNumber);

    Teachers findFirstByName(String teacher);
}
