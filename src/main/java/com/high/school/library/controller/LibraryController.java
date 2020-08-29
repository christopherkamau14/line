package com.high.school.library.controller;

import com.high.school.academics.model.Staff;
import com.high.school.exceptions.BadRequestException;
import com.high.school.library.model.*;
import com.high.school.library.service.LibraryInterface;
import com.high.school.timetabling.model.Days;
import com.high.school.users.model.User;
import com.high.school.users.model.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/protected/library")
public class LibraryController {
    @Autowired
    UserUtils userUtils;

    @Autowired
    LibraryInterface libraryInterface;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping("/regbooks")
    public String regbooks(){
        return "regbooks";
    }

    @GetMapping("/stores")
    public String stores(){
        return "stores";
    }

    @GetMapping("/costs")
    public String costs(){
        return "costs";
    }

    @GetMapping("/regStationery")
    public String regStationery(){
        return "regStationery";
    }

    @GetMapping("/bookIssue")
    public String bookIssue(){
        return "bookIssue";
    }

    @GetMapping("/stationery")
    public String stationery(){
        return "stationery";
    }

    @GetMapping("/shelves")
    public String shelves(){
        return "shelves";
    }

    @GetMapping("/category")
    public String category(){
        return "category";
    }

    @GetMapping("/bookclass")
    public String bookclass(){
        return "bookclass";
    }

    @GetMapping("/statissue")
    public String statissue(){
        return "statissue";
    }

    @GetMapping("/items")
    public String items(){
        return "items";
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
    @PostMapping("/saveShelves")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveShelves(Shelves shelves){
        libraryInterface.saveShelves(shelves);
    }
    @GetMapping("/getShelvesTbl")
    @ResponseBody
    public DataTablesOutput<Shelves> getShelvesTbl(DataTablesInput input){
        return libraryInterface.getShelvesTbl(input);
    }
    @GetMapping("/deleteShelves/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteShelves(@PathVariable Long code){
        libraryInterface.deleteShelves(code);
    }

    @PostMapping("/saveCategory")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCategory(LibCategory libCategory){
        libraryInterface.saveCategory(libCategory);
    }

    @PostMapping("/saveItemIssue")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveItemIssue(ItemIssue itemIssue){
        libraryInterface.saveItemIssue(itemIssue);
    }

    @GetMapping("/getCategoryTbl")
    @ResponseBody
    public DataTablesOutput<LibCategory> getCategoryTbl(DataTablesInput input){
        return libraryInterface.getCategoryTbl(input);
    }
    @GetMapping("/getItemIssueTbl")
    @ResponseBody
    public DataTablesOutput<ItemIssue> getItemIssueTbl(DataTablesInput input){
        return libraryInterface.getItemIssueTbl(input);
    }
    @GetMapping("/deleteCategory/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Long code){
        libraryInterface.deleteCategory(code);
    }

    @PostMapping("/saveClassification")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveClassification(BookClass bookClass){
        libraryInterface.saveClassification(bookClass);
    }
    @GetMapping("/getClassificationTbl")
    @ResponseBody
    public DataTablesOutput<BookClass> getClassificationTbl(DataTablesInput input){
        return libraryInterface.getClassificationTbl(input);
    }
    @GetMapping("/deleteClassification/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClassification(@PathVariable Long code){
        libraryInterface.deleteClassification(code);
    }

    @GetMapping("/getCurrentUser")
    @ResponseBody
    public User getCurrentUser(DataTablesInput input){
        return userUtils.getCurrentUser();
    }
    @GetMapping("/pageStore")
    @ResponseBody
    Page<Stores> pageStores(@RequestParam(required = false) String term, Pageable pageable) {
        return libraryInterface.pageStores(pageable, term);
    }
    @GetMapping("/pageStationery")
    @ResponseBody
    Page<Stationery> pageStationery(@RequestParam(required = false) String term, Pageable pageable) {
        return libraryInterface.pageStationery(pageable, term);
    }
    @GetMapping("/pageBookRegister")
    @ResponseBody
    Page<BookRegister> pageBookRegister(@RequestParam(required = false) String term, Pageable pageable) {
        return libraryInterface.pageBookRegister(pageable, term);
    }
    @GetMapping("/pageShelves")
    @ResponseBody
    Page<Shelves> pageShelves(@RequestParam(required = false) String term, Pageable pageable) {
        return libraryInterface.pageShelves(pageable, term);
    }
    @GetMapping("/pageClassification")
    @ResponseBody
    Page<BookClass> pageClassification(@RequestParam(required = false) String term, Pageable pageable) {
        return libraryInterface.pageClassification(pageable, term);
    }
    @GetMapping("/pageWorker")
    @ResponseBody
    Page<Staff> pageWorker(@RequestParam(required = false) String term, Pageable pageable) {
        return libraryInterface.pageWorker(pageable, term);
    }
    @GetMapping("/pageUsers")
    @ResponseBody
    Page<ItemsIssuedBean> pageIssuedBean(@RequestParam(required = false) String term, Pageable pageable) {
        return libraryInterface.pageIssuedBean(pageable, term);
    }
    @PostMapping("/saveStationeryRegister")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveStationeryRegister(StationeryRegister stationeryRegister){
        libraryInterface.saveStationeryRegister(stationeryRegister);
    }
    @GetMapping("/getStatRegTbl")
    @ResponseBody
    public DataTablesOutput<StationeryRegister> getStatRegTbl(DataTablesInput input){
        return libraryInterface.getStatRegTbl(input);
    }
    @GetMapping("/deleteStatReg/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStatReg(@PathVariable Long code){
        libraryInterface.deleteStatReg(code);
    }

    @PostMapping("/saveBookRegister")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveBookRegister(BookRegister bookRegister) throws BadRequestException {
        libraryInterface.saveBookRegister(bookRegister);
    }
    @GetMapping("/getBookRegTbl")
    @ResponseBody
    public DataTablesOutput<BookRegister> getBookRegTbl(DataTablesInput input){
        return libraryInterface.getBookRegTbl(input);
    }
    @GetMapping("/bookTitle/{callNo}")
    @ResponseBody
    public BookRegister getBookRegTbl(@PathVariable String callNo){
        return libraryInterface.getBook(callNo);
    }
    @GetMapping("/deleteBookReg/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBookReg(@PathVariable Long code) throws BadRequestException {
        libraryInterface.deleteBookReg(code);
    }

    @PostMapping("/saveStationeryIssue")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveStationeryIssue(StationeryIssue stationeryIssue){
        libraryInterface.saveStationeryIssue(stationeryIssue);
    }
    @PostMapping("/saveBookIssue")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveBookIssue(BookIssue bookIssue){
        libraryInterface.saveBookIssue(bookIssue);
    }
    @PostMapping("/saveBookCosts")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveBookCost(BookCost bookCost){
        libraryInterface.saveBookCost(bookCost);
    }
    @GetMapping("/updateBookStatus")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateBookStatus(@RequestParam String comment,
                                 @RequestParam String status,
                                 @RequestParam Long code){
        libraryInterface.updateBookStatus(comment,status,code);
    }
    @GetMapping("/getStatIssueTbl")
    @ResponseBody
    public DataTablesOutput<StationeryIssue> getStatIssueTbl(DataTablesInput input){
        return libraryInterface.getStatIssueTbl(input);
    }
    @GetMapping("/getBookIssueTbl")
    @ResponseBody
    public DataTablesOutput<BookIssue> getBookIssueTbl(DataTablesInput input){
        return libraryInterface.getBookIssueTbl(input);
    }
    @GetMapping("/getBookCostTbl")
    @ResponseBody
    public DataTablesOutput<BookCost> getBookCostTbl(DataTablesInput input){
        return libraryInterface.getBookCostTbl(input);
    }
    @GetMapping("/deleteStationeryIssue/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStationeryIssue(@PathVariable Long code){
        libraryInterface.deleteStationeryIssue(code);
    }

    @GetMapping("/deleteBookCost/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBookCost(@PathVariable Long code){
        libraryInterface.deleteBookCost(code);
    }

    @GetMapping("/returnBook/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void returnBook(@PathVariable Long code){
        libraryInterface.returnBook(code);
    }
    @GetMapping("/returnItem/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void returnItem(@PathVariable Long code){
        libraryInterface.returnItem(code);
    }

    @GetMapping("/deleteItemIssue/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteItemIssue(@PathVariable Long code){
        libraryInterface.deleteItemIssue(code);
    }
}
