package com.high.school.students.repo;

import com.high.school.students.model.Parent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ParentsRepo extends JpaRepository<Parent,Long>, PagingAndSortingRepository<Parent,Long>,DataTablesRepository<Parent,Long> {
    Parent findByParCode(Long parCode);

    Page<Parent> findByNameFatherContainingIgnoreCase(String term, Pageable pageable);

    Parent findFirstByIdNoAndTelFather(String idNo, String telFather);

    Parent findFirstByIdNoAndTelMother(String idNo, String telMother);

    Parent findFirstByIdNo(String idNo);

    Parent findFirstByTelFather(String telFather);
}
