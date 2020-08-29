package com.high.school.library.repo;

import com.high.school.library.model.ItemIssue;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemIssueRepo extends JpaRepository<ItemIssue,Long>, DataTablesRepository<ItemIssue,Long> {
    ItemIssue findByItemCode(Long itemCode);
}
