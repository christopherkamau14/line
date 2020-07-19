package com.high.school.Locale.repo;

import com.high.school.Locale.model.Country;
import com.high.school.students.model.Parent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CountryRepo extends JpaRepository<Country,Long>, PagingAndSortingRepository<Country,Long>, DataTablesRepository<Country,Long> {
    Page<Country> findByCouNameLike(String term, Pageable pageable);

    Country findByCouCode(Long country);

    Country findByCouNameIgnoreCase(String couName);
}
