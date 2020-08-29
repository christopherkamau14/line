package com.high.school.library.service.impl;

import com.high.school.academics.model.Staff;
import com.high.school.academics.model.Teachers;
import com.high.school.academics.repo.StaffRepo;
import com.high.school.academics.repo.TeacherRepo;
import com.high.school.exceptions.BadRequestException;
import com.high.school.library.model.*;
import com.high.school.library.repo.*;
import com.high.school.library.service.LibraryInterface;
import com.high.school.users.model.User;
import com.high.school.users.model.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LibraryService implements LibraryInterface {

    @Autowired
    UserUtils userUtils;

    @Autowired
    StationeryRegRepo stationeryRegRepo;

    @Autowired
    ItemIssueRepo itemIssueRepo;

    @Autowired
    TeacherRepo teacherRepo;

    @Autowired
    BookIssueRepo bookIssueRepo;

    @Autowired
    StationeryIssueRepo stationeryIssueRepo;

    @Autowired
    StaffRepo staffRepo;

    @Autowired
    BookCostRepo bookCostRepo;

    @Autowired
    BookRegRepo bookRegRepo;

    @Autowired
    StoreRepo storeRepo;

    @Autowired
    LibCatRepo libCatRepo;

    @Autowired
    BookClassRepo bookClassRepo;

    @Autowired
    ShelfRepo shelvesRepo;

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

    @Override
    public void saveShelves(Shelves shelves) {
        if(shelves.getShelfId()==null){
            shelvesRepo.save(shelves);
        }
        else{
            Shelves s=shelvesRepo.findByShelfId(shelves.getShelfId());
            s.setShelfName(shelves.getShelfName());
            shelvesRepo.save(s);
        }
    }

    @Override
    public DataTablesOutput<Shelves> getShelvesTbl(DataTablesInput input) {
        return shelvesRepo.findAll(input);
    }

    @Override
    public void deleteShelves(Long code) {
    shelvesRepo.deleteById(code);
    }

    @Override
    public void saveCategory(LibCategory libCategory) {
        if(libCategory.getCategoryId()==null){
            libCatRepo.save(libCategory);
        }
        else{
         LibCategory l=libCatRepo.findByCategoryId(libCategory.getCategoryId());
         l.setBookNumber(libCategory.getBookNumber());
         l.setCategoryName(libCategory.getCategoryName());
         l.setDuration(libCategory.getDuration());
         l.setDurationType(libCategory.getDurationType());
         l.setCharges(libCategory.getCharges());
         libCatRepo.save(l);
        }

    }

    @Override
    public DataTablesOutput<LibCategory> getCategoryTbl(DataTablesInput input) {
        return libCatRepo.findAll(input);
    }

    @Override
    public void deleteCategory(Long code) {
          libCatRepo.deleteById(code);
    }

    @Override
    public void saveClassification(BookClass bookClass) {
        if(bookClass.getClassificationCode()==null){
            bookClassRepo.save(bookClass);
        }
        else{
            BookClass b=bookClassRepo.findByClassificationCode(bookClass.getClassificationCode());
            b.setClassificationName(bookClass.getClassificationName());
            bookClassRepo.save(b);
        }
    }

    @Override
    public DataTablesOutput<BookClass> getClassificationTbl(DataTablesInput input) {
        return bookClassRepo.findAll(input);
    }

    @Override
    public void deleteClassification(Long code) {
bookClassRepo.deleteById(code);
    }

    @Override
    public Page<Stores> pageStores(Pageable pageable, String term) {
        if (term == null || StringUtils.isBlank(term)) {
            return storeRepo.findAll(pageable);
        } else {
            return storeRepo.findByStoreNameContainingIgnoreCase(term, pageable);
        }        }

    @Override
    public Page<Stationery> pageStationery(Pageable pageable, String term) {
        if (term == null || StringUtils.isBlank(term)) {
            return stationeryRepo.findAll(pageable);
        } else {
            return stationeryRepo.findByStationeryNameContainingIgnoreCase(term, pageable);
        }      }

    @Override
    public Page<Shelves> pageShelves(Pageable pageable, String term) {
        if (term == null || StringUtils.isBlank(term)) {
            return shelvesRepo.findAll(pageable);
        } else {
            return shelvesRepo.findByShelfNameContainingIgnoreCase(term, pageable);
        }      }

    @Override
    public Page<BookClass> pageClassification(Pageable pageable, String term) {
        if (term == null || StringUtils.isBlank(term)) {
            return bookClassRepo.findAll(pageable);
        } else {
            return bookClassRepo.findByClassificationNameContainingIgnoreCase(term, pageable);
        }      }
    @Override
    public Page<BookRegister> pageBookRegister(Pageable pageable, String term) {
        if (term == null || StringUtils.isBlank(term)) {
            return bookRegRepo.findAll(pageable);
        } else {
            return bookRegRepo.findByCallNoContainingIgnoreCaseOrTitleContainingIgnoreCase(term,term, pageable);
        }
    }
    protected Long getCountTotal(Stationery stationery){
          List<StationeryRegister> stationeryRegisters=stationeryRegRepo.findByStationery(stationery);
          Long tt=Long.valueOf(0);
          if(!stationeryRegisters.isEmpty()) {
              for (StationeryRegister s : stationeryRegisters) {
                  Long units = s.getUnits();
                  tt = tt + units;
                  System.out.print(tt);
              }
          }
          return tt;
      }
      /*
      protected  Long getBookCountTotal(BookRegister bookRegister){
          List<BookRegister> b=bookRegRepo.findByBookClassAndTitleAndPublisherAndYearAndAuthor(bookRegister.getBookClass(),bookRegister.getTitle(),bookRegister.getPublisher(),bookRegister.getYear(),bookRegister.getAuthor());
          Long tt=Long.valueOf(0);
          if(!b.isEmpty()) {
              for (BookRegister s : b) {
                  Long units = s.getUnits();
                  tt = tt + units;
                  System.out.print(tt);
              }
          }
          return tt;
      }

       */
      public void updateStationeryRegister(StationeryRegister stationeryRegister){
          List<StationeryRegister> stationeryRegisters=stationeryRegRepo.findByStationery(stationeryRegister.getStationery());
          for(StationeryRegister s:stationeryRegisters){
              StationeryRegister sr=stationeryRegRepo.findByStationeryRegCode(s.getStationeryRegCode());
              sr.setTotalUnits(stationeryRegister.getTotalUnits());
              stationeryRegRepo.save(sr);

          }

      }
    public void updateBookRegister(BookRegister bookRegister){
        List<BookRegister> b=bookRegRepo.findByCallNoAndBookClassAndTitleAndPublisherAndYearAndAuthor(bookRegister.getCallNo(),bookRegister.getBookClass(),bookRegister.getTitle(),bookRegister.getPublisher(),bookRegister.getYear(),bookRegister.getAuthor());
        if(!b.isEmpty()) {
            for (BookRegister s : b) {
                BookRegister sr = bookRegRepo.findByBookRegCode(s.getBookRegCode());
                sr.setUnits(bookRegister.getUnits() - 1);
                bookRegRepo.save(sr);

            }
        }
    }
    public void updateSaveStationeryRegister(StationeryRegister stationeryRegister){
        List<StationeryRegister> stationeryRegisters=stationeryRegRepo.findByStationery(stationeryRegister.getStationery());
        Long tt=Long.valueOf(0);
        if(!stationeryRegisters.isEmpty()) {
            for (StationeryRegister s : stationeryRegisters) {
                Long units = s.getUnits();
                tt = tt + units;
                System.out.print(tt);
            }
        }
        List<StationeryRegister> ss=stationeryRegRepo.findByStationery(stationeryRegister.getStationery());
        for(StationeryRegister s:ss){
            StationeryRegister sr=stationeryRegRepo.findByStationeryRegCode(s.getStationeryRegCode());
            sr.setTotalUnits(tt);
            stationeryRegRepo.save(sr);

        }

    }
    /*
    public void updateSaveBookRegister(BookRegister bookRegister){
        List<BookRegister> b=bookRegRepo.findByBookClassAndTitleAndPublisherAndYearAndAuthor(bookRegister.getBookClass(),bookRegister.getTitle(),bookRegister.getPublisher(),bookRegister.getYear(),bookRegister.getAuthor());

        Long tt=Long.valueOf(0);
        if(!b.isEmpty()) {
            for (BookRegister s : b) {
                Long units = s.getUnits();
                tt = tt + units;
                System.out.print(tt);
            }
        }
        List<BookRegister> bb=bookRegRepo.findByBookClassAndTitleAndPublisherAndYearAndAuthor(bookRegister.getBookClass(),bookRegister.getTitle(),bookRegister.getPublisher(),bookRegister.getYear(),bookRegister.getAuthor());
        for(BookRegister s:bb){
            BookRegister sr=bookRegRepo.findByBookRegCode(s.getBookRegCode());
            sr.setCopies(tt);
            bookRegRepo.save(sr);

        }
    }
*/


    @Override
    public void saveStationeryRegister(StationeryRegister stationeryRegister) {
        Long totalUnits=getCountTotal(stationeryRegister.getStationery());
        totalUnits=totalUnits+stationeryRegister.getUnits();

        if(stationeryRegister.getStationeryRegCode()==null){
            stationeryRegister.setTotalUnits(totalUnits);
            stationeryRegRepo.save(stationeryRegister);
            updateStationeryRegister(stationeryRegister);
        }
        else{
          StationeryRegister s=stationeryRegRepo.findByStationeryRegCode(stationeryRegister.getStationeryRegCode());
          s.setIssueDate(stationeryRegister.getIssueDate());
          s.setUnits(stationeryRegister.getUnits());
          s.setStationery(stationeryRegister.getStationery());
          s.setStores(stationeryRegister.getStores());
          s.setTotalUnits(Long.valueOf(0));
          stationeryRegRepo.save(s);
            updateSaveStationeryRegister(s);
        }
    }

    @Override
    public void saveBookRegister(BookRegister bookRegister) throws BadRequestException {

        String shortLoan=bookRegister.getShortLoan()==null?"N":"Y";
      //  Long totalUnits=getBookCountTotal(bookRegister);
       // totalUnits=totalUnits+bookRegister.getUnits();

        if(bookRegister.getBookRegCode()==null){
            //BookRegister br=bookRegRepo.findFirstByCallNo(bookRegister.getCallNo());
               if(bookRegister.getUnits()!=null) {
                   if(bookRegister.getUnits()>1) {
                       int counter = bookRegister.getUnits();
                       for (int i = 0; i<counter;i++){
                           BookRegister b=new BookRegister();
                           b.setShortLoan(shortLoan);
                           b.setYear(bookRegister.getYear());
                           b.setClassNo(bookRegister.getClassNo());
                           b.setPublisher(bookRegister.getPublisher());
                           b.setTitle(bookRegister.getTitle());
                           b.setIsbnNo(bookRegister.getIsbnNo());
                           b.setAuthor(bookRegister.getAuthor());
                           b.setCallNo(bookRegister.getCallNo());
                           b.setBookClass(bookRegister.getBookClass());
                           b.setIssueDate(bookRegister.getIssueDate());
                           b.setUnits(bookRegister.getUnits());
                           b.setShelves(bookRegister.getShelves());
                           b.setStores(bookRegister.getStores());
                           bookRegRepo.save(b);
                       }
                   }
                   else{
                       bookRegister.setShortLoan(shortLoan);
                       bookRegRepo.save(bookRegister);
                   }

               }
            }
        else{
            BookRegister s=bookRegRepo.findByBookRegCode(bookRegister.getBookRegCode());
            s.setIssueDate(bookRegister.getIssueDate());
            //s.setUnits(bookRegister.getUnits());
            s.setBookClass(bookRegister.getBookClass());
            s.setStores(bookRegister.getStores());
           // s.setCopies(Long.valueOf(0));
            s.setAuthor(bookRegister.getAuthor());
            s.setCallNo(bookRegister.getCallNo());
            s.setIsbnNo(bookRegister.getIsbnNo());
            s.setPublisher(bookRegister.getPublisher());
            s.setClassNo(bookRegister.getClassNo());
            s.setTitle(bookRegister.getTitle());
            s.setYear(bookRegister.getYear());
            s.setShelves(bookRegister.getShelves());
            s.setShortLoan(shortLoan);
            bookRegRepo.save(s);
            //updateSaveBookRegister(s);
        }
    }

    @Override
    public DataTablesOutput<StationeryRegister> getStatRegTbl(DataTablesInput input) {
        return stationeryRegRepo.findAll(input);
    }

    @Override
    public void deleteStatReg(Long code) {
        StationeryRegister s=stationeryRegRepo.findByStationeryRegCode(code);
    stationeryRegRepo.deleteById(code);
        Long totalUnits=getCountTotal(s.getStationery());
        s.setTotalUnits(totalUnits);
        updateStationeryRegister(s);

    }

    @Override
    public DataTablesOutput<BookRegister> getBookRegTbl(DataTablesInput input) {
        return bookRegRepo.findAll(input);
    }

    @Override
    public void deleteBookReg(Long code) {
        BookRegister s=bookRegRepo.findByBookRegCode(code);
        bookRegRepo.deleteById(code);
        //s.setCopies(totalUnits);
        updateBookRegister(s);
    }

    @Override
    public Page<Staff> pageWorker(Pageable pageable, String term) {
        if (term == null || StringUtils.isBlank(term)) {
            return staffRepo.findAll(pageable);
        } else {
            return staffRepo.findByNameContainingIgnoreCase(term, pageable);
        }
    }

    @Override
    public void saveStationeryIssue(StationeryIssue stationeryIssue) {
        if(stationeryIssue.getStatIssueCode()==null){
            stationeryIssueRepo.save(stationeryIssue);
        }
        else{
           StationeryIssue s=stationeryIssueRepo.findByStatIssueCode(stationeryIssue.getStatIssueCode());
           s.setIssueDate(stationeryIssue.getIssueDate());
           s.setUnits(stationeryIssue.getUnits());
           s.setStationery(stationeryIssue.getStationery());
           s.setStores(stationeryIssue.getStores());
           s.setForms(stationeryIssue.getForms());
           s.setRegisteredBy(stationeryIssue.getRegisteredBy());
           s.setStudent(stationeryIssue.getStudent());
           s.setTeachers(stationeryIssue.getTeachers());
           s.setStaff(stationeryIssue.getStaff());
           stationeryIssueRepo.save(s);

        }
    }

    @Override
    public DataTablesOutput<StationeryIssue> getStatIssueTbl(DataTablesInput input) {
        return stationeryIssueRepo.findAll(input);
    }

    @Override
    public void deleteStationeryIssue(Long code) {
    stationeryIssueRepo.deleteById(code);
    }

    @Override
    public BookRegister getBook(String callNo) {
        return bookRegRepo.findFirstByCallNo(callNo);
    }

    @Override
    public void saveBookIssue(BookIssue bookIssue) {
        if(bookIssue.getBookIssueCode()==null){
            bookIssueRepo.save(bookIssue);
        }
        else{
           BookIssue b=bookIssueRepo.findByBookIssueCode(bookIssue.getBookIssueCode());
           b.setIssueDate(bookIssue.getIssueDate());
           b.setReturnDate(bookIssue.getReturnDate());
           //b.setDateReturned();
            b.setBorrowerCat(bookIssue.getBorrowerCat());
            b.setBookRegister(bookIssue.getBookRegister());
            b.setForms(bookIssue.getForms());
            b.setTeachers(bookIssue.getTeachers());
            b.setStudent(bookIssue.getStudent());
            b.setCallNo(bookIssue.getCallNo());
            b.setTitle(bookIssue.getTitle());
            bookIssueRepo.save(b);
        }
    }

    @Override
    public DataTablesOutput<BookIssue> getBookIssueTbl(DataTablesInput input) {
        return bookIssueRepo.findAll(input);
    }

    @Override
    public void returnBook(Long code) {
        bookIssueRepo.deleteById(code);
    }

    @Override
    public void updateBookStatus(String comment, String status, Long code) {
        BookRegister b=bookRegRepo.findByBookRegCode(code);
        b.setStatusComments(comment);
        b.setStatus(status);
        bookRegRepo.save(b);
    }

    @Override
    public void saveBookCost(BookCost bookCost) {
        if(bookCost.getCostCode()==null){
            User user=userUtils.getCurrentUser();
            LocalDate localDate = LocalDate.now();
            Date date= Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            bookCost.setIssuedDate(date);
            bookCost.setUser(user);
            bookCostRepo.save(bookCost);
        }
        else{
             BookCost b=bookCostRepo.findByCostCode(bookCost.getCostCode());
             b.setCost(bookCost.getCost());
             b.setBookRegister(bookCost.getBookRegister());
             LocalDate localDate = LocalDate.now();
             Date date= Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
             b.setIssuedDate(date);
             bookCostRepo.save(b);
        }
    }

    @Override
    public DataTablesOutput<BookCost> getBookCostTbl(DataTablesInput input) {
        return bookCostRepo.findAll(input);
    }

    @Override
    public Page<ItemsIssuedBean> pageIssuedBean(Pageable pageable, String term) {
        if (term == null || StringUtils.isBlank(term)) {
            List<Staff> staffList=staffRepo.findAll();
            List<Teachers> teachersList=teacherRepo.findAll();
            List<ItemsIssuedBean> issuedBeans=new ArrayList<>();
            for(Teachers t:teachersList){
                ItemsIssuedBean b=new ItemsIssuedBean();
                b.setIssuerCode(t.getTeacherCode());
                b.setIssuerName(t.getName());
                issuedBeans.add(b);
            }
            for(Staff t:staffList){
                ItemsIssuedBean b=new ItemsIssuedBean();
                b.setIssuerCode(t.getStaffCode());
                b.setIssuerName(t.getName());
                issuedBeans.add(b);
            }
            return new PageImpl<>(issuedBeans);
        } else {
            List<Staff> staffList=staffRepo.findAll();
            List<Teachers> teachersList=teacherRepo.findAll();
            List<ItemsIssuedBean> issuedBeans=new ArrayList<>();
            for(Teachers t:teachersList){
                ItemsIssuedBean b=new ItemsIssuedBean();
                if(t.getName().toLowerCase().contains(term.toLowerCase())) {
                    b.setIssuerCode(t.getTeacherCode());
                    b.setIssuerName(t.getName());
                    issuedBeans.add(b);
                }
            }
            for(Staff t:staffList){
                ItemsIssuedBean b=new ItemsIssuedBean();
                if(t.getName().toLowerCase().contains(term.toLowerCase())) {
                    b.setIssuerCode(t.getStaffCode());
                    b.setIssuerName(t.getName());
                    issuedBeans.add(b);
                }
            }
            return new PageImpl<>(issuedBeans);
        }
    }

    @Override
    public void deleteBookCost(Long code) {
        bookCostRepo.deleteById(code);
    }

    @Override
    public void saveItemIssue(ItemIssue itemIssue) {
        if(itemIssue.getItemCode()==null){
            itemIssueRepo.save(itemIssue);
        }
        else{
            ItemIssue i=itemIssueRepo.findByItemCode(itemIssue.getItemCode());
            i.setBorrowerCat(itemIssue.getBorrowerCat());
            i.setBorrowerName(itemIssue.getBorrowerName());
            i.setDescription(itemIssue.getDescription());
            i.setForms(itemIssue.getForms());
            i.setIssueDate(itemIssue.getIssueDate());
            i.setIssuerCode(itemIssue.getIssuerCode());
            i.setIssuerName(itemIssue.getIssuerName());
            i.setItemName(itemIssue.getItemName());
            i.setStudent(itemIssue.getStudent());
            i.setTeachers(itemIssue.getTeachers());
            i.setUnits(itemIssue.getUnits());
            i.setReturnDate(itemIssue.getReturnDate());
            itemIssueRepo.save(i);
        }
    }

    @Override
    public DataTablesOutput<ItemIssue> getItemIssueTbl(DataTablesInput input) {
        return itemIssueRepo.findAll(input);
    }

    @Override
    public void returnItem(Long code) {
        ItemIssue i=itemIssueRepo.findByItemCode(code);
        LocalDate localDate = LocalDate.now();
        Date date= Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        i.setDateReturned(date);
        itemIssueRepo.save(i);
    }

    @Override
    public void deleteItemIssue(Long code) {
        itemIssueRepo.deleteById(code);
    }
}
