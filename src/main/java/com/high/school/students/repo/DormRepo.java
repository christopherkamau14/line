package com.high.school.students.repo;

import com.high.school.students.model.Dorm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DormRepo extends PagingAndSortingRepository<Dorm,Long>, JpaRepository<Dorm,Long>, DataTablesRepository<Dorm,Long> {
    Page<Dorm> findByDormNameContainingIgnoreCase(String term, Pageable pageable);

    Dorm findByDormCode(Long dormCode);

    Dorm findByDormNameIgnoreCase(String dormName);
}
