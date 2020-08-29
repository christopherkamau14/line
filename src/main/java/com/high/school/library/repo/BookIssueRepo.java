package com.high.school.library.repo;

import com.high.school.library.model.BookIssue;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookIssueRepo extends JpaRepository<BookIssue,Long>, DataTablesRepository<BookIssue,Long> {
    BookIssue findByBookIssueCode(Long bookIssueCode);
}
