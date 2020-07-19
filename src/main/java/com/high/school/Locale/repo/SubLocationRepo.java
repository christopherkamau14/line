package com.high.school.Locale.repo;

import com.high.school.Locale.model.SubLocation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SubLocationRepo extends JpaRepository<SubLocation,Long>, PagingAndSortingRepository<SubLocation,Long>, DataTablesRepository<SubLocation,Long> {
    Page<SubLocation> findBysubLouNameContainingIgnoreCase(String term, Pageable pageable);

    SubLocation findBySubLouCode(Long subLocation);

    SubLocation findBySubLouNameIgnoreCase(String subLouName);
}
