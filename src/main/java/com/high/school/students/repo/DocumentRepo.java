package com.high.school.students.repo;

import com.high.school.students.model.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DocumentRepo extends JpaRepository<Document,Long>, PagingAndSortingRepository<Document,Long>, DataTablesRepository<Document,Long> {
    Page<Document> findByDocNameContainingIgnoreCase(String term, Pageable pageable);

    Document findByDocCode(Long docId);

    Document findByDocNameIgnoreCase(String docName);
}
