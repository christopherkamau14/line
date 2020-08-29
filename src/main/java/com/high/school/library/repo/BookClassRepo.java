package com.high.school.library.repo;

import com.high.school.library.model.BookClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookClassRepo extends JpaRepository<BookClass,Long>, DataTablesRepository<BookClass,Long> {
    BookClass findByClassificationCode(Long classificationCode);

    Page<BookClass> findByClassificationNameContainingIgnoreCase(String term, Pageable pageable);
}
