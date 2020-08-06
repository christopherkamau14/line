package com.high.school.library.service;

import com.high.school.library.model.Stationery;
import com.high.school.library.model.Stores;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

public interface LibraryInterface {
    void saveStore(Stores stores);

    DataTablesOutput<Stores> getStoresTbl(DataTablesInput input);

    void deleteStores(Long code);

    void saveStationery(Stationery stationery);

    DataTablesOutput<Stationery> getStationeryTbl(DataTablesInput input);

    void deleteStationery(Long code);
}
