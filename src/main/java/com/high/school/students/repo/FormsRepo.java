package com.high.school.students.repo;

import com.high.school.students.model.Forms;
import com.high.school.students.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FormsRepo extends JpaRepository<Forms,Long>, PagingAndSortingRepository<Forms,Long>, DataTablesRepository<Forms,Long> {
    Page<Forms> findByClassNoContainingIgnoreCase(String term, Pageable pageable);

    Forms findByClassCode(Long classCode);

    Forms findByClassNo(int classNo);

    Forms findByClassNoAndBlock_BlockCode(String classNo, Long blockCode);


    Forms findFirstByAbbrIgnoreCase(String form);
}
