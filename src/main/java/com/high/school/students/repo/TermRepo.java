package com.high.school.students.repo;

import com.high.school.students.model.Term;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TermRepo extends PagingAndSortingRepository<Term,Long>, JpaRepository<Term,Long>, DataTablesRepository<Term,Long> {
    Page<Term> findByTermNumberContaining(String term, Pageable pageable);

    Term findByTermCode(Long termCode);

    Term findByTermName(int termName);

    Term findFirstByTermNumber(int term);
}
