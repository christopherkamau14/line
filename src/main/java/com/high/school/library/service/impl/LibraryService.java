package com.high.school.library.service.impl;

import com.high.school.library.model.Stationery;
import com.high.school.library.model.Stores;
import com.high.school.library.repo.StationeryRepo;
import com.high.school.library.repo.StoreRepo;
import com.high.school.library.service.LibraryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

@Service
public class LibraryService implements LibraryInterface {

    @Autowired
    StoreRepo storeRepo;

    @Autowired
    StationeryRepo stationeryRepo;

    @Override
    public void saveStore(Stores stores) {
        String s="";
        s=stores.getStationery()==null?"N":"Y";
        if(stores.getStoreCode()==null){
            stores.setStationery(s);
            storeRepo.save(stores);
        }
        else{
            Stores st=storeRepo.findByStoreCode(stores.getStoreCode());
            st.setStoreName(stores.getStoreName());
            st.setStationery(s);
            storeRepo.save(st);
        }
    }

    @Override
    public DataTablesOutput<Stores> getStoresTbl(DataTablesInput input) {
        return storeRepo.findAll(input);
    }

    @Override
    public void deleteStores(Long code) {
        storeRepo.deleteById(code);
    }

    @Override
    public void saveStationery(Stationery stationery) {
        if(stationery.getStationeryCode()==null){
            stationeryRepo.save(stationery);
        }
        else{
            Stationery s=stationeryRepo.findByStationeryCode(stationery.getStationeryCode());
            s.setStationeryName(stationery.getStationeryName());
            stationeryRepo.save(s);
        }
    }

    @Override
    public DataTablesOutput<Stationery> getStationeryTbl(DataTablesInput input) {
        return stationeryRepo.findAll(input);
    }

    @Override
    public void deleteStationery(Long code) {
        stationeryRepo.deleteById(code);
    }
}
