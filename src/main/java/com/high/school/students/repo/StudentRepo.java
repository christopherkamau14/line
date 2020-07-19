package com.high.school.students.repo;

import com.high.school.students.model.Forms;
import com.high.school.students.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface StudentRepo extends JpaRepository<Student,Long>,DataTablesRepository<Student,Long>{
    Student findByStId(Long studCode);

    Page<Student> findByNameContainingIgnoreCase(String term, Pageable pageable);

    Student findFirstByAdmNoAndIndexNo(String admNo, String indexNo);

    Student findFirstByAdmNo(String admNo);

    Student findFirstByIndexNo(String indexNo);

    Student findByBirthCertNo(String birthCertNo);

    @Query(value = "SELECT * FROM students_table u WHERE u.stud_id = ?1",
    nativeQuery = true)
    ArrayList<Student> findByStuedent(Long stdId);

    List<Student> findByForms_ClassCode(Long classId);

    List<Student> findByInSession(String pending);

    Page<Student> findByForms(Forms forms, Pageable pageable);

    Page<Student> findByFormsAndNameContainingIgnoreCase(Forms forms, String term, Pageable pageable);

    List<Student> findAllByOrderByForms();

}
