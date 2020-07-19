package com.high.school.students.repo;

import com.high.school.students.model.Block;
import com.high.school.students.model.Forms;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BlockRepo extends JpaRepository<Block,Long>, PagingAndSortingRepository<Block,Long>, DataTablesRepository<Block,Long> {
    Page<Block> findByBlockNameContainingIgnoreCase(String term, Pageable pageable);

    Block findByBlockCode(Long blockCode);

    Block findByBlockNameIgnoreCase(String blockName);
}
