package com.high.school.Locale.repo;

import com.high.school.Locale.model.Village;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface VillageRepo extends JpaRepository<Village,Long>, PagingAndSortingRepository<Village,Long>, DataTablesRepository<Village,Long> {
    Page<Village> findByvilNameContainingIgnoreCase(String term, Pageable pageable);

    Village findByVilCode(Long vilCode);

    Village findByVilNameIgnoreCase(String vilName);
}
