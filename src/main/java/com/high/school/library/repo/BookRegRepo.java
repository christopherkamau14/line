package com.high.school.library.repo;

import com.high.school.library.model.BookClass;
import com.high.school.library.model.BookRegister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRegRepo extends JpaRepository<BookRegister,Long>, DataTablesRepository<BookRegister,Long> {
    List<BookRegister> findByBookClassAndTitleAndPublisherAndYearAndAuthor(BookClass bookClass, String title, String publisher, String year, String author);

    BookRegister findByBookRegCode(Long bookRegCode);

    BookRegister findFirstByCallNo(String callNo);

    Page<BookRegister> findByCallNoContainingIgnoreCaseOrTitleContainingIgnoreCase(String term, String term1, Pageable pageable);

    List<BookRegister> findByCallNoAndBookClassAndTitleAndPublisherAndYearAndAuthor(String callNo, BookClass bookClass, String title, String publisher, String year, String author);
}
