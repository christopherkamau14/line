package com.high.school.library.repo;

import com.high.school.library.model.Stationery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationeryRepo extends JpaRepository<Stationery,Long>, DataTablesRepository<Stationery,Long> {
    Stationery findByStationeryCode(Long stationeryCode);

    Page<Stationery> findByStationeryNameContainingIgnoreCase(String term, Pageable pageable);
}
