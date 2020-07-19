package com.high.school.Fee.repo;

import com.high.school.Fee.model.FeeCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FeeRepo extends JpaRepository<FeeCategory,Long>, PagingAndSortingRepository<FeeCategory,Long>, DataTablesRepository<FeeCategory,Long> {
    Page<FeeCategory> findByCatNameContainingIgnoreCase(String term, Pageable pageable);

    FeeCategory findByCatCode(Long catCode);

    FeeCategory findByCatNameIgnoreCase(String catName);
}
