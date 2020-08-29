package com.high.school.library.repo;

import com.high.school.library.model.BookCost;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCostRepo extends JpaRepository<BookCost,Long>, DataTablesRepository<BookCost,Long> {
    BookCost findByCostCode(Long costCode);
}
