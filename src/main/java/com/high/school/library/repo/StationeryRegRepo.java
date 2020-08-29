package com.high.school.library.repo;

import com.high.school.library.model.Stationery;
import com.high.school.library.model.StationeryRegister;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StationeryRegRepo extends JpaRepository<StationeryRegister,Long>, DataTablesRepository<StationeryRegister,Long> {
    StationeryRegister findByStationeryRegCode(Long stationeryRegCode);

    List<StationeryRegister> findByStationery(Stationery stationery);
}
