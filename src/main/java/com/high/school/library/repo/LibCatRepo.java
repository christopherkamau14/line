package com.high.school.library.repo;

import com.high.school.library.model.LibCategory;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibCatRepo extends JpaRepository<LibCategory,Long>, DataTablesRepository<LibCategory,Long> {
    LibCategory findByCategoryId(Long categoryId);
}
