package com.high.school.students.repo;

import com.high.school.students.model.StudStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StudStatusRepo extends JpaRepository<StudStatus,Long>, PagingAndSortingRepository<StudStatus,Long>, DataTablesRepository<StudStatus,Long> {
    Page<StudStatus> findByStatusNameContainingIgnoreCase(String term, Pageable pageable);

    StudStatus findByStatusCode(Long fCode);

    StudStatus findByStatusNameIgnoreCase(String statusName);
}
