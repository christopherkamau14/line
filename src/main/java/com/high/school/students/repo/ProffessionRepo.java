package com.high.school.students.repo;

import com.high.school.students.model.Proffession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProffessionRepo extends JpaRepository<Proffession,Long>, PagingAndSortingRepository<Proffession,Long>, DataTablesRepository<Proffession,Long> {
    Page<Proffession> findByProfNameContainingIgnoreCase(String term, Pageable pageable);

    Proffession findByProfCode(Long profCode);

    Proffession findByProfNameIgnoreCase(String profName);
}
