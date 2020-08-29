package com.high.school.library.repo;

import com.high.school.library.model.Shelves;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShelfRepo extends JpaRepository<Shelves,Long>, DataTablesRepository<Shelves,Long> {

    Shelves findByShelfId(Long shelfId);

    Page<Shelves> findByShelfNameContainingIgnoreCase(String term, Pageable pageable);
}
