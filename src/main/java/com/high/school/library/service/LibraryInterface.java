package com.high.school.library.service;

import com.high.school.academics.model.Staff;
import com.high.school.exceptions.BadRequestException;
import com.high.school.library.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

public interface LibraryInterface {
    void saveStore(Stores stores);

    DataTablesOutput<Stores> getStoresTbl(DataTablesInput input);

    void deleteStores(Long code);

    void saveStationery(Stationery stationery);

    DataTablesOutput<Stationery> getStationeryTbl(DataTablesInput input);

    void deleteStationery(Long code);

    void saveShelves(Shelves shelves);

    DataTablesOutput<Shelves> getShelvesTbl(DataTablesInput input);

    void deleteShelves(Long code);

    void saveCategory(LibCategory libCategory);

    DataTablesOutput<LibCategory> getCategoryTbl(DataTablesInput input);

    void deleteCategory(Long code);

    void saveClassification(BookClass bookClass);

    DataTablesOutput<BookClass> getClassificationTbl(DataTablesInput input);

    void deleteClassification(Long code);

    Page<Stores> pageStores(Pageable pageable, String term);

    Page<Stationery> pageStationery(Pageable pageable, String term);

    void saveStationeryRegister(StationeryRegister stationeryRegister);

    DataTablesOutput<StationeryRegister> getStatRegTbl(DataTablesInput input);

    void deleteStatReg(Long code);

    Page<Shelves> pageShelves(Pageable pageable, String term);

    Page<BookClass> pageClassification(Pageable pageable, String term);

    void saveBookRegister(BookRegister bookRegister) throws BadRequestException;

    DataTablesOutput<BookRegister> getBookRegTbl(DataTablesInput input);

    void deleteBookReg(Long code) throws BadRequestException;

    Page<Staff> pageWorker(Pageable pageable, String term);

    void saveStationeryIssue(StationeryIssue stationeryIssue);

    DataTablesOutput<StationeryIssue> getStatIssueTbl(DataTablesInput input);

    void deleteStationeryIssue(Long code);

    Page<BookRegister> pageBookRegister(Pageable pageable, String term);

    BookRegister getBook(String callNo);

    void saveBookIssue(BookIssue bookIssue);

    DataTablesOutput<BookIssue> getBookIssueTbl(DataTablesInput input);

    void returnBook(Long code);

    void updateBookStatus(String comment, String status, Long code);

    void saveBookCost(BookCost bookCost);

    DataTablesOutput<BookCost> getBookCostTbl(DataTablesInput input);

    void deleteBookCost(Long code);

    Page<ItemsIssuedBean> pageIssuedBean(Pageable pageable, String term);

    void saveItemIssue(ItemIssue itemIssue);

    DataTablesOutput<ItemIssue> getItemIssueTbl(DataTablesInput input);

    void returnItem(Long code);

    void deleteItemIssue(Long code);
}
