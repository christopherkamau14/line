package com.high.school.Locale.repo;

import com.high.school.Locale.model.SubCounty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SubCountyRepo extends JpaRepository<SubCounty,Long>, PagingAndSortingRepository<SubCounty,Long>, DataTablesRepository<SubCounty,Long> {
    Page<SubCounty> findBysubNameContainingIgnoreCase(String term, Pageable pageable);

    SubCounty findBySubCode(Long subCounty);

    SubCounty findBySubNameIgnoreCase(String subName);
}
