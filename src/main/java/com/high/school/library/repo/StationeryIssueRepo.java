package com.high.school.library.repo;

import com.high.school.library.model.StationeryIssue;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationeryIssueRepo extends JpaRepository<StationeryIssue,Long>, DataTablesRepository<StationeryIssue,Long> {
    StationeryIssue findByStatIssueCode(Long statIssueCode);
}
