package com.high.school.library.controller;

import com.high.school.library.model.Stationery;
import com.high.school.library.model.Stores;
import com.high.school.library.service.LibraryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/protected/library")
public class LibraryController {

    @Autowired
    LibraryInterface libraryInterface;

    @GetMapping("/stores")
    public String stores(){
        return "stores";
    }

    @GetMapping("/stationery")
    public String stationery(){
        return "stationery";
    }

    @PostMapping("/saveStore")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveStore(Stores stores){
        libraryInterface.saveStore(stores);
    }
    @GetMapping("/getStoresTbl")
    @ResponseBody
    public DataTablesOutput<Stores> getStoresTbl(DataTablesInput input){
        return libraryInterface.getStoresTbl(input);
    }
    @GetMapping("/deleteStore/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStores(@PathVariable Long code){
        libraryInterface.deleteStores(code);
    }
    @PostMapping("/saveStationery")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveStationery(Stationery stationery){
        libraryInterface.saveStationery(stationery);
    }
    @GetMapping("/getStationeryTbl")
    @ResponseBody
    public DataTablesOutput<Stationery> getStationeryTbl(DataTablesInput input){
        return libraryInterface.getStationeryTbl(input);
    }
    @GetMapping("/deleteStationery/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStationery(@PathVariable Long code){
        libraryInterface.deleteStationery(code);
    }
}
