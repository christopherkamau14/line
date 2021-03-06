package com.high.school.library.repo;

import com.high.school.library.model.Stores;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepo extends JpaRepository<Stores,Long>, DataTablesRepository<Stores,Long> {
    Stores findByStoreCode(Long storeCode);

    Page<Stores> findByStoreNameContainingIgnoreCase(String term, Pageable pageable);
}
