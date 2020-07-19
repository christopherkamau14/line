package com.high.school.students.service.impl;

import com.high.school.Fee.model.FeeCategory;
import com.high.school.Fee.repo.FeeRepo;
import com.high.school.Locale.model.*;
import com.high.school.Locale.repo.*;
import com.high.school.academics.model.Clearance;
import com.high.school.academics.model.GradingSystem;
import com.high.school.academics.repo.ClearanceRepo;
import com.high.school.academics.repo.GradingSysRepo;
import com.high.school.exceptions.BadRequestException;
import com.high.school.students.model.*;
import com.high.school.students.repo.*;
import com.high.school.students.service.StudentInterface;
import com.high.school.users.model.User;
import com.high.school.users.model.UserUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StudentService implements StudentInterface {

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    GradingSysRepo gradingSysRepo;

    @Autowired
    LeaveOutsRepo leaveOutsRepo;

    @Autowired
    ParentsRepo parentsRepo;

    @Autowired
    CountryRepo countryRepo;

    @Autowired
    CountyRepo countyRepo;

    @Autowired
    SubCountyRepo subCountyRepo;

    @Autowired
    LocationRepo locationRepo;

    @Autowired
    SubLocationRepo subLocationRepo;

    @Autowired
    VillageRepo villageRepo;

    @Autowired
    DormRepo dormRepo;

    @Autowired
    FormsRepo formsRepo;

    @Autowired
    BlockRepo blockRepo;

    @Autowired
    TermRepo termRepo;

    @Autowired
    ReligionRepo religionRepo;

    @Autowired
    HealthRepo healthRepo;

    @Autowired
    SourcesRepo sourcesRepo;

    @Autowired
    DocumentRepo documentRepo;

    @Autowired
    ProffessionRepo proffessionRepo;

    @Autowired
    YearRepo yearRepo;

    @Autowired
    FeeRepo feeRepo;

    @Autowired
    StudStatusRepo studStatusRepo;

    @Autowired
    StudentDocsRepo studentDocsRepo;

    @Autowired
    ClearanceRepo clearanceRepo;

    @Autowired
    UserUtils userUtils;

    public DataTablesOutput<Student> findAllStudents(DataTablesInput input) {
        return studentRepo.findAll(input);
    }

    @Override
    public void postNation(Country country) throws BadRequestException {
        if(country.getCouCode()==null){
            Country country1=countryRepo.findByCouNameIgnoreCase(country.getCouName());
            if(country1!=null){
                throw new BadRequestException("A similar Country Exist");
            }
            else {
                countryRepo.save(country);
            }
        }else{
          Country c=countryRepo.findByCouCode(country.getCouCode());
          c.setCouContinent(country.getCouContinent());
          c.setCouDesc(country.getCouDesc());
          c.setCouName(country.getCouName());
          countryRepo.save(c);
        }
    }

    @Override
    public void postCounty(County county) throws BadRequestException {
        if(county.getCountyCode()==null) {
            County county1=countyRepo.findByCountyNameIgnoreCase(county.getCountyName());
            if(county1!=null){
                throw new BadRequestException("A similar County Exist");
            }
            else {
                countyRepo.save(county);
            }
        }
        else {
            County c=countyRepo.findByCountyCode(county.getCountyCode());
            c.setCountry(county.getCountry());
            c.setCountyDesc(county.getCountyDesc());
            c.setCountyName(county.getCountyName());
            countyRepo.save(c);
        }

    }

    @Override
    public void postSubCounty(SubCounty subCounty) throws BadRequestException {
        if(subCounty.getSubCode()==null) {
            SubCounty country1=subCountyRepo.findBySubNameIgnoreCase(subCounty.getSubName());
            if(country1!=null){
                throw new BadRequestException("A similar SubCounty Exist");
            }
            else {
                subCountyRepo.save(subCounty);
            }
        }
        else{
            SubCounty sub=subCountyRepo.findBySubCode(subCounty.getSubCode());
            sub.setCounty(subCounty.getCounty());
            sub.setSubDesc(subCounty.getSubDesc());
            sub.setSubName(subCounty.getSubName());
            subCountyRepo.save(sub);
        }
    }

    @Override
    public void postLocation(Location location) throws BadRequestException {
        if(location.getLouCode()==null) {
            Location country1=locationRepo.findByLouNameIgnoreCase(location.getLouName());
            if(country1!=null){
                throw new BadRequestException("A similar Location Exist");
            }
            else {
                locationRepo.save(location);
            }
        }else{
            Location loc=locationRepo.findByLouCode(location.getLouCode());
            loc.setLouDesc(location.getLouDesc());
            loc.setSubCounty(location.getSubCounty());
            loc.setLouName(location.getLouName());
            locationRepo.save(loc);
        }
    }

    @Override
    public void postSubLocation(SubLocation subLocation) throws BadRequestException {
        if(subLocation.getSubLouCode()==null){
            SubLocation country1=subLocationRepo.findBySubLouNameIgnoreCase(subLocation.getSubLouName());
            if(country1!=null){
                throw new BadRequestException("A similar SubLocation Exist");
            }
            else {
                subLocationRepo.save(subLocation);
            }
        }else{
            SubLocation sub=subLocationRepo.findBySubLouCode(subLocation.getSubLouCode());
            sub.setLocation(subLocation.getLocation());
            sub.setSubLouDesc(subLocation.getSubLouDesc());
            sub.setSubLouName(subLocation.getSubLouName());
            subLocationRepo.save(sub);
        }
    }

    @Override
    public void postVillage(Village village) throws BadRequestException {
        if(village.getVilCode()==null) {
            Village country1=villageRepo.findByVilNameIgnoreCase(village.getVilName());
            if(country1!=null){
                throw new BadRequestException("A similar Village Exist");
            }
            else {
                villageRepo.save(village);
            }
        }
        else {
            Village vil=villageRepo.findByVilCode(village.getVilCode());
            vil.setSubLocation(village.getSubLocation());
            vil.setVilName(village.getVilName());
            vil.setVilDesc(village.getVilDesc());
            villageRepo.save(vil);
        }
    }

    @Override
    public void postDorm(Dorm dorm) throws BadRequestException {
        if(dorm.getDormCode()==null) {
            Dorm country1=dormRepo.findByDormNameIgnoreCase(dorm.getDormName());
            if(country1!=null){
                throw new BadRequestException("A similar Dorm Exist");
            }
            else {
                dormRepo.save(dorm);
            }
        }
       else{
           Dorm dormade=dormRepo.findByDormCode(dorm.getDormCode());
           dormade.setCapacity(dorm.getCapacity());
           dormade.setDormDesc(dorm.getDormDesc());
           dormade.setDormName(dorm.getDormName());
           dormRepo.save(dormade);
        }
    }

    @Override
    public void postClass(Forms forms) throws BadRequestException {
        if(forms.getClassCode()==null) {
            Forms country1=formsRepo.findByClassNoAndBlock_BlockCode(forms.getClassNo(),forms.getBlock().getBlockCode());
            if(country1!=null){
                throw new BadRequestException("A similar Class Exist");
            }
            else {
                if(forms.getActive()==null){
                    forms.setActive("No");
                }
                else{
                    forms.setActive("Yes");
                }
                if(forms.getBlock()!=null){
                    forms.setAbbr(forms.getClassNo()+forms.getBlock().getBlockDesc());
                }
                formsRepo.save(forms);
            }
        }else {
        Forms classes=formsRepo.findByClassCode(forms.getClassCode());
        classes.setBlock(forms.getBlock());
        classes.setClassNo(forms.getClassNo());
        classes.setClassDesc(forms.getClassDesc());
            if(forms.getActive()==null){
                classes.setActive("No");
            }
            else{
                classes.setActive("Yes");
            }
            if(forms.getBlock()!=null){
                classes.setAbbr(forms.getClassNo()+forms.getBlock().getBlockDesc());
            }
            classes.setCapacity(forms.getCapacity());
            classes.setTeachers(forms.getTeachers());
        formsRepo.save(classes);
        }
    }

    @Override
    public void postBlock(Block block) throws BadRequestException {
        if(block.getBlockCode()==null) {
            Block country1=blockRepo.findByBlockNameIgnoreCase(block.getBlockName());
            if(country1!=null){
                throw new BadRequestException("A similar Block Exist");
            }
            else {
                blockRepo.save(block);
            }
        }
        else{
            Block blockade=blockRepo.findByBlockCode(block.getBlockCode());
            blockade.setBlockName(block.getBlockName());
            blockade.setBlockDesc(block.getBlockDesc());
       blockRepo.save(blockade);
        }
    }

    @Override
    public void postTerm(Term term) throws BadRequestException {
        if(term.getTermCode()==null) {
            Term country1=termRepo.findByTermName(term.getTermNumber());
            if(country1!=null){
                throw new BadRequestException("A similar Term Exist");
            }
            else {
                termRepo.save(term);
            }
        }else{
            Term termade=termRepo.findByTermCode(term.getTermCode());
            termade.setTermName(term.getTermName());
            termade.setTermNumber(term.getTermNumber());
            termRepo.save(termade);
        }
    }

    @Override
    public void postReligion(Religion religion) throws BadRequestException {
        if(religion.getRelCode()==null) {
            Religion country1=religionRepo.findByRelNameIgnoreCase(religion.getRelName());
            if(country1!=null){
                throw new BadRequestException("A similar Religion Exist");
            }
            else {
                religionRepo.save(religion);
            }
        }
        else{
            Religion religionade=religionRepo.findByRelCode(religion.getRelCode());
            religionade.setRelDay(religion.getRelDay());
            religionade.setRelName(religion.getRelName());
            religionade.setRelDesc(religion.getRelDesc());
            religionRepo.save(religionade);
        }
    }

    @Override
    public void postSources(Sources sources) throws BadRequestException {
        if(sources.getSourceCode()==null){
            Sources country1=sourcesRepo.findBySourceNameIgnoreCase(sources.getSourceName());
            if(country1!=null){
                throw new BadRequestException("A similar Source Exist");
            }
            else {
                sourcesRepo.save(sources);
            }
    }
        else{
            Sources sourcade=sourcesRepo.findBySourceCode(sources.getSourceCode());
            sourcade.setSourceDesc(sources.getSourceDesc());
            sourcade.setSourceName(sources.getSourceName());
            sourcesRepo.save(sources);
        }
    }

    @Override
    public void postHealth(Health health) throws BadRequestException {
        if(health.getCondCode()==null){
            Health country1=healthRepo.findByCondNameIgnoreCase(health.getCondName());
            if(country1!=null){
                throw new BadRequestException("A similar Health Condition Exist");
            }
            else {
                healthRepo.save(health);
            }
        }else{
            Health healthade=healthRepo.findByCondCode(health.getCondCode());
            healthade.setCondComments(health.getCondComments());
            healthade.setCondName(health.getCondName());
            healthade.setCondDesc(health.getCondDesc());
            healthRepo.save(healthade);
        }
    }

    @Override
    public void postDocument(Document document) throws BadRequestException {
        if(document.getDocCode()==null) {
            Document country1=documentRepo.findByDocNameIgnoreCase(document.getDocName());
            if(country1!=null){
                throw new BadRequestException("A similar Document Exist");
            }
            else {
                documentRepo.save(document);
            }
        }
        else{
            Document documentade=documentRepo.findByDocCode(document.getDocCode());
            documentade.setDocDesc(document.getDocDesc());
            documentade.setDocRequired(document.getDocRequired());
            documentade.setDocName(document.getDocName());
            documentRepo.save(documentade);
        }
    }

    @Override
    public void postYear(Year year) throws BadRequestException {
        if(year.getYearCode()==null) {
            Year country1=yearRepo.findByYearNameIgnoreCase(year.getYearName());
            if(country1!=null){
                throw new BadRequestException("A similar Year Exist");
            }
            else {
                yearRepo.save(year);
            }
        }
        else{
            Year yearade=yearRepo.findByYearCode(year.getYearCode());
            yearade.setYearNumber(year.getYearNumber());
            yearade.setYearName(year.getYearName());
            yearRepo.save(yearade);
        }
    }

    @Override
    public void postProffession(Proffession proffession) throws BadRequestException {
        if (proffession.getProfCode()==null) {
            Proffession country1=proffessionRepo.findByProfNameIgnoreCase(proffession.getProfName());
            if(country1!=null){
                throw new BadRequestException("A similar Proffession Name Exist");
            }
            else {
                proffessionRepo.save(proffession);
            }
        }
        else {
            Proffession prof=proffessionRepo.findByProfCode(proffession.getProfCode());
            prof.setProfDesc(proffession.getProfDesc());
            prof.setProfName(proffession.getProfName());
            proffessionRepo.save(prof);
        }
    }

    @Override
    public Page<Country> findCountries(Pageable pageable, String term) {
        if (term == null || StringUtils.isBlank(term)) {

            return countryRepo.findAll(pageable);
        }
        else{
            return countryRepo.findByCouNameLike(term,pageable);
        }
    }

    @Override
    public Page<County> findCounties(Pageable pageable, String term) {
        if (term == null || StringUtils.isBlank(term)) {
            return countyRepo.findAll(pageable);
        }else {
            return countyRepo.findByCountyNameContainingIgnoreCase(term, pageable);
        }

    }

    @Override
    public Page<SubCounty> findSubCounty(Pageable pageable, String term) {
        if (term == null || StringUtils.isBlank(term)) {
            return subCountyRepo.findAll(pageable);
        }else {
            return subCountyRepo.findBysubNameContainingIgnoreCase(term, pageable);
        }
    }

    @Override
    public Page<Location> findLocation(Pageable pageable, String term) {
        if (term == null || StringUtils.isBlank(term)) {
            return locationRepo.findAll(pageable);
        }else{
            return locationRepo.findByLouNameContainingIgnoreCase(term, pageable);
        }
    }

    @Override
    public Page<SubLocation> findSubLocation(Pageable pageable, String term) {
        if (term == null || StringUtils.isBlank(term)) {
            return subLocationRepo.findAll(pageable);
        }else{
            return subLocationRepo.findBysubLouNameContainingIgnoreCase(term, pageable);
        }
    }

    @Override
    public Page<Village> findVillage(Pageable pageable, String term) {
        if (term == null || StringUtils.isBlank(term)) {
            return villageRepo.findAll(pageable);
        }else{
            return villageRepo.findByvilNameContainingIgnoreCase(term, pageable);
        }    }

    @Override
    public DataTablesOutput<Country> findAllCountries(DataTablesInput input) {
        return countryRepo.findAll(input);
    }

    @Override
    public DataTablesOutput<County> findAllCounties(DataTablesInput pageable, Long country) {
        Country country1=countryRepo.findByCouCode(country);

        return countyRepo.findAll(pageable, CountrySpecs.joinTest(country1));

    }

    @Override
    public DataTablesOutput<SubCounty> findAllSubCounties(DataTablesInput input, Long county) {
        County county1=countyRepo.findByCountyCode(county);
        return subCountyRepo.findAll(input,CountrySpecs.dataSub(county1));
    }

    @Override
    public DataTablesOutput<Location> getLocationTable(DataTablesInput input, Long subCounty) {
        SubCounty subCounty1=subCountyRepo.findBySubCode(subCounty);
        return locationRepo.findAll(input,CountrySpecs.dataLoc(subCounty1));
    }

    @Override
    public DataTablesOutput<SubLocation> getSubLocationTable(DataTablesInput input, Long location) {
     Location location1=locationRepo.findByLouCode(location);
     return subLocationRepo.findAll(input,CountrySpecs.dataSubLoc(location1));
    }

    @Override
    public DataTablesOutput<Village> getVillageTable(DataTablesInput input, Long subLocation) {
        SubLocation subLocation1=subLocationRepo.findBySubLouCode(subLocation);
        return villageRepo.findAll(input,CountrySpecs.dataVil(subLocation1));
    }

    @Override
    public DataTablesOutput<Health> getHealthTable(DataTablesInput input) {
        return healthRepo.findAll(input);
    }

    @Override
    public DataTablesOutput<Sources> getSourcesTable(DataTablesInput input) {
        return sourcesRepo.findAll(input);
    }

    @Override
    public DataTablesOutput<Religion> getReligionTable(DataTablesInput input) {
        return religionRepo.findAll(input);
    }

    @Override
    public DataTablesOutput<Document> getDocumentsTable(DataTablesInput input) {
        return documentRepo.findAll(input);
    }

    @Override
    public DataTablesOutput<Proffession> getProffessionTable(DataTablesInput input) {
        return proffessionRepo.findAll(input);
    }

    @Override
    public Page<Proffession> findProffession(Pageable pageable, String term) {
        if (term == null || StringUtils.isBlank(term)) {
            return proffessionRepo.findAll(pageable);
        }else {
            return proffessionRepo.findByProfNameContainingIgnoreCase(term, pageable);
        }
    }

    @Override
    public DataTablesOutput<Parent> getParentsTable(DataTablesInput input) {
        return parentsRepo.findAll(input);
    }

    @Override
    public DataTablesOutput<Block> getBlockTable(DataTablesInput input) {
        return blockRepo.findAll(input);
    }

    @Override
    public Page<Block> pageBlock(Pageable pageable, String term) {
        if (term == null || StringUtils.isBlank(term)) {
            return blockRepo.findAll(pageable);
        }else {
            return blockRepo.findByBlockNameContainingIgnoreCase(term, pageable);
        }    }

    @Override
    public DataTablesOutput<Forms> getClassTable(DataTablesInput input) {
        return formsRepo.findAll(input);
    }

    @Override
    public DataTablesOutput<Term> getTermTable(DataTablesInput input) {
        return termRepo.findAll(input);
    }

    @Override
    public DataTablesOutput<FeeCategory> getFeeTable(DataTablesInput input) {
        return feeRepo.findAll(input);
    }

    @Override
    public void postFeeCategory(FeeCategory feeCategory) throws BadRequestException {
        if(feeCategory.getCatCode()==null) {
            FeeCategory country1=feeRepo.findByCatNameIgnoreCase(feeCategory.getCatName());
            if(country1!=null){
                throw new BadRequestException("A similar Category Exists Exist");
            }
            else {
                feeRepo.save(feeCategory);
            }
        }
        else{
            FeeCategory fee=feeRepo.findByCatCode(feeCategory.getCatCode());
            fee.setCatDesc(feeCategory.getCatDesc());
            fee.setCatName(feeCategory.getCatName());
            fee.setCatSuccessor(feeCategory.getCatSuccessor());
            fee.setDefaultCat(feeCategory.getDefaultCat());
            feeRepo.save(fee);
        }
    }

    @Override
    public DataTablesOutput<Dorm> getHouseTable(DataTablesInput input) {
        return dormRepo.findAll(input);
    }

    @Override
    public DataTablesOutput<Year> getYearTable(DataTablesInput input) {
        return yearRepo.findAll(input);
    }

    @Override
    public Parent getParent(Long parCode) {
        return parentsRepo.findByParCode(parCode);
    }

    @Override
    public void deleteParent(Long parCode) {
         parentsRepo.deleteById(parCode);
    }

    @Override
    public Page<FeeCategory> pageFees(Pageable pageable, String term) {
        if (term == null || StringUtils.isBlank(term)) {
            return feeRepo.findAll(pageable);
        }else {
            return feeRepo.findByCatNameContainingIgnoreCase(term, pageable);
        }
    }

    @Override
    public Page<Dorm> pageDorm(Pageable pageable, String term) {
        if (term == null || StringUtils.isBlank(term)) {
            return dormRepo.findAll(pageable);
        }else {
            return dormRepo.findByDormNameContainingIgnoreCase(term, pageable);
        }    }

    @Override
    public Page<Forms> pageClass(Pageable pageable, String term) {
        if (term == null || StringUtils.isBlank(term)) {
            return formsRepo.findAll(pageable);
        }else {
            return formsRepo.findByClassNoContainingIgnoreCase(term, pageable);
        }      }

    @Override
    public Page<Term> pageTerm(Pageable pageable, String term) {
        if (term == null || StringUtils.isBlank(term)) {
            return termRepo.findAll(pageable);
        }else {
            return termRepo.findByTermNumberContaining(term, pageable);
        }     }

    @Override
    public Page<Parent> pageParent(Pageable pageable, String term) {
        if (term == null || StringUtils.isBlank(term)) {
            return parentsRepo.findAll(pageable);
        }else {
            return parentsRepo.findByNameFatherContainingIgnoreCase(term, pageable);
        }
    }

    @Override
    public Page<Religion> pageReligion(Pageable pageable, String term) {
        if (term == null || StringUtils.isBlank(term)) {
            return religionRepo.findAll(pageable);
        }else {
            return religionRepo.findByRelNameContainingIgnoreCase(term, pageable);
        }
    }

    @Override
    public Page<Sources> pageSources(Pageable pageable, String term) {
        if (term == null || StringUtils.isBlank(term)) {
            return sourcesRepo.findAll(pageable);
        }else {
            return sourcesRepo.findBySourceNameContainingIgnoreCase(term, pageable);
        }    }

    @Override
    public Page<Year> pageYear(Pageable pageable, String term) {
        if (term == null || StringUtils.isBlank(term)) {
            return yearRepo.findAll(pageable);
        }else {
            return yearRepo.findByYearNameContainingIgnoreCase(term, pageable);
        }     }

    @Override
    public Page<Health> pageHealth(Pageable pageable, String term) {
        if (term == null || StringUtils.isBlank(term)) {
            return healthRepo.findAll(pageable);
        }else {
            return healthRepo.findByCondNameContainingIgnoreCase(term, pageable);
        }      }

    @Override
    public Page<Document> pageDocs(Pageable pageable, String term) {
        if (term == null || StringUtils.isBlank(term)) {
            return documentRepo.findAll(pageable);
        }else {
            return documentRepo.findByDocNameContainingIgnoreCase(term, pageable);
        }    }

    @Override
    public void postStatus(StudStatus studStatus) throws BadRequestException {
        if(studStatus.getStatusCode()==null) {
            StudStatus country1=studStatusRepo.findByStatusNameIgnoreCase(studStatus.getStatusName());
            if(country1!=null){
                throw new BadRequestException("A similar Status Exist");
            }
            else {
                studStatusRepo.save(studStatus);
            }
        }
        else{
            StudStatus stud=studStatusRepo.findByStatusCode(studStatus.getStatusCode());
            stud.setStatusDesc(studStatus.getStatusDesc());
            stud.setStatusName(studStatus.getStatusName());
            studStatusRepo.save(stud);
        }
    }

    @Override
    public DataTablesOutput<StudStatus> getStatusTable(DataTablesInput input) {
        return studStatusRepo.findAll(input);
    }

    @Override
    public Page<StudStatus> pageStatus(Pageable pageable, String term) {
        if (term == null || StringUtils.isBlank(term)) {
            return studStatusRepo.findAll(pageable);
        }else {
            return studStatusRepo.findByStatusNameContainingIgnoreCase(term, pageable);
        }
    }

    @Override
    public Student getStudent(Long studCode) {

        return studentRepo.findByStId(studCode);
    }

    @Override
    public void postStudent(Student student) throws IOException, BadRequestException {
        if(student.getStId()==null) {
            Student student1=studentRepo.findFirstByAdmNoAndIndexNo(student.getAdmNo(),student.getIndexNo());
            Student student2=studentRepo.findFirstByAdmNo(student.getAdmNo());
            Student student3=studentRepo.findFirstByIndexNo(student.getIndexNo());
            Student student4=studentRepo.findByBirthCertNo(student.getBirthCertNo());
            if(student1!=null){
                throw new BadRequestException("A Student with similar Adm No. or Index No. exists");
            }else if(student2!=null){
                throw new BadRequestException("A Student with similar Adm No. exists");
            }else if(student3!=null){
                throw new BadRequestException("A Student with similar Index No. exists");
            }else if(student4!=null){
                throw new BadRequestException("A Student with similar Birth Cert No.exists");
            }else {
                student.setInSession("inSession");
                studentRepo.save(student);
            }
        }
        else{
            Student stud=studentRepo.findByStId(student.getStId());
            byte[] photo=stud.getPhoto();
            stud.setCountry(student.getCountry());
            stud.setCounty(student.getCounty());
            stud.setSubCounty(student.getSubCounty());
            stud.setLocation(student.getLocation());
            stud.setSubLocation(student.getSubLocation());
            stud.setVillage(student.getVillage());
            stud.setAddress(student.getAddress());
            if(student.getPhoto()==null){
                stud.setPhoto(photo);
            }else {
                stud.setPhoto(student.getPhoto());
            }
            stud.setBirthCertNo(student.getBirthCertNo());
            stud.setBirthDate(student.getBirthDate());
            stud.setCompletionDate(student.getCompletionDate());
            stud.setDorm(student.getDorm());
            stud.setFeeCategory(student.getFeeCategory());
            stud.setAdmDate(student.getAdmDate());
            stud.setForms(student.getForms());
            stud.setGender(student.getGender());
            stud.setGraduate(student.getGraduate());
            stud.setHealth(student.getHealth());
            stud.setKcpeGrade(student.getKcpeGrade());
            stud.setKcpeMarks(student.getKcpeMarks());
            stud.setParent(student.getParent());
            stud.setParentContact(student.getParentContact());
            stud.setName(student.getName());
            stud.setPrimarySchool(student.getPrimarySchool());
            stud.setReligion(student.getReligion());
            stud.setSources(student.getSources());
            stud.setStudEmail(student.getStudEmail());
            stud.setStudentContact(student.getStudentContact());
            stud.setStudStatus(student.getStudStatus());
            stud.setTerm(student.getTerm());
            stud.setUpi(student.getUpi());
            stud.setYear(student.getYear());
            System.out.println(student.getPhoto());
            studentRepo.save(stud);
        }
    }

    @Override
    public void uploadClientDocument(UploadBean uploadBean) throws BadRequestException {
        if(uploadBean.getFile().isEmpty())
            throw new BadRequestException("Upload File is Empty...");
        if(uploadBean.getDocId()==null)
            throw new BadRequestException("Student ID cannot be null");
        String uploadFolder = "C:/TURNSOFT/Documents";
        Student student = studentRepo.findByStId(uploadBean.getDocId());
        String folderName = student.getAdmNo()+"_"+student.getStId();
        StudentDocs docs=new StudentDocs();
        docs.setUploadedFileName(uploadBean.getFile().getOriginalFilename());
        try {
            byte[] bytes = uploadBean.getFile().getBytes();
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(bytes);
            String hashString = new BigInteger(1, digest).toString(16);
            docs.setContentType(uploadBean.getFile().getContentType());
            docs.setFileId(uploadBean.getFileId());
            docs.setDocument(uploadBean.getDocument());
            docs.setStudent(student);
            studentDocsRepo.save(docs);
            String folderPath = uploadFolder+"/"+folderName;
            File file = new File(folderPath);
            if(!file.exists())
                FileUtils.forceMkdir(file);
            Path path = Paths.get(folderPath+"/"+uploadBean.getFile().getOriginalFilename());
            Files.write(path,bytes);
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new BadRequestException(e.getMessage());
        }
    }

    @Override
    public Page<Student> pageStudent(Pageable pageable, String term) {
        if (term == null || StringUtils.isBlank(term)) {
            return studentRepo.findAll(pageable);
        }else {
            return studentRepo.findByNameContainingIgnoreCase(term, pageable);
        }    }

    @Override
    public byte[] getStudentDocument(Long stdId) throws BadRequestException {
        return new byte[0];
    }

    @Override
    public String getStudentDocumentType(Long docId) throws BadRequestException {
        if(docId==null)
            throw new BadRequestException("Document does not exist...");
        StudentDocs studentDocs = studentDocsRepo.findByStdocCode(docId);
        return studentDocs.getContentType();
    }

    @Override
    public DataTablesOutput<StudentDocs> findAllDocuments(DataTablesInput input, Long stdId) {
        Student student=studentRepo.findByStId(stdId);
        return studentDocsRepo.findAll(input,CountrySpecs.dataStud(student));
    }

    @Override
    public DataTablesOutput<Student> findSpecificStudents(DataTablesInput input, String name, String admNo, String mobile, String form, String suspended, String transfers, String graduated) {
        if(name!=""){
            System.out.println("In Name");
            System.out.println(name);
            return studentRepo.findAll(input,CountrySpecs.dataName(name));

        }
        else if(form!=""){
            System.out.println("In Class");
            return studentRepo.findAll(input,CountrySpecs.dataForm(form));
        }
        else if(mobile!=""){
            System.out.println("In Mob");
            return studentRepo.findAll(input,CountrySpecs.dataMobile(mobile));
        }
        else if(admNo!=""){
            System.out.println("In Adm");
            return studentRepo.findAll(input,CountrySpecs.dataAdmNo(admNo));

        }
        else{
                return studentRepo.findAll(input);

        }
    }

    @Override
    public void deleteBlock(Long parCode) {
        blockRepo.deleteById(parCode);
    }

    @Override
    public void deleteClass(Long parCode) {
        formsRepo.deleteById(parCode);
    }

    @Override
    public void deleteSources(Long parCode) {
        sourcesRepo.deleteById(parCode);
    }

    @Override
    public void deleteHealth(Long parCode) {
        healthRepo.deleteById(parCode);
    }

    @Override
    public void deleteYear(Long parCode) {
yearRepo.deleteById(parCode);
    }

    @Override
    public void deleteTerm(Long parCode) {
termRepo.deleteById(parCode);
    }

    @Override
    public void deleteProffession(Long parCode) {
proffessionRepo.deleteById(parCode);
    }

    @Override
    public void deleteReligion(Long parCode) {
religionRepo.deleteById(parCode);
    }

    @Override
    public void deleteDorm(Long parCode) {
        dormRepo.deleteById(parCode);
    }

    @Override
    public void deleteStatus(Long parCode) {
        studStatusRepo.deleteById(parCode);
    }

    @Override
    public void deleteDocuments(Long parCode) {
      documentRepo.deleteById(parCode);
    }

    @Override
    public void deleteVillage(Long parCode) {
        villageRepo.deleteById(parCode);
    }

    @Override
    public void deleteSubLocation(Long parCode) {
      subLocationRepo.deleteById(parCode);
    }

    @Override
    public void deleteLocation(Long parCode) {
     locationRepo.deleteById(parCode);
    }

    @Override
    public void deleteSubCounty(Long parCode) {
     subCountyRepo.deleteById(parCode);
    }

    @Override
    public void deleteCounty(Long parCode) {
    countyRepo.deleteById(parCode);
    }

    @Override
    public void deleteCountry(Long parCode) {
        countryRepo.deleteById(parCode);
    }

    @Override
    public void deleteFeeCategory(Long parCode) {
        feeRepo.deleteById(parCode);
    }

    @Override
    public void deleteStdDocument(Long adId, Long stud) throws BadRequestException {
        if(adId==null || stud==null)
            throw new BadRequestException("Document does not exist For this student...");
        StudentDocs clientDocs = studentDocsRepo.findByStdocCode(adId);
        String uploadFolder = "C:/TURNSOFT/Documents";
        String uploadedName = clientDocs.getUploadedFileName();
        Student clientDef = studentRepo.findByStId(stud);
        String folderName = clientDef.getAdmNo()+"_"+clientDef.getStId();
        Path path = Paths.get(uploadFolder+"/"+folderName+"/"+uploadedName);
        if(path.toFile().exists()){
            try {
                Files.delete(path);
            } catch (IOException e) {
                throw new BadRequestException(e.getMessage());
            }
        }
        studentDocsRepo.deleteById(adId);
    }

    @Override
    public void deleteStudent(Long parCode) {
        studentRepo.deleteById(parCode);
    }


    @Override
    public Forms findForm(Long fCode) {
        return formsRepo.findByClassCode(fCode);
    }

    @Override
    public Block findBlock(Long fCode) {
        return blockRepo.findByBlockCode(fCode);
    }

    @Override
    public Term termEdit(Long fCode) {
        return termRepo.findByTermCode(fCode);
    }

    @Override
    public FeeCategory getFeeCat(Long fCode) {
        return feeRepo.findByCatCode(fCode);
    }

    @Override
    public Year yearEdit(Long fCode) {
        return yearRepo.findByYearCode(fCode);
    }

    @Override
    public Dorm dormEdit(Long fCode) {
        return dormRepo.findByDormCode(fCode);
    }

    @Override
    public StudStatus statusEdit(Long fCode) {
        return studStatusRepo.findByStatusCode(fCode);
    }

    @Override
    public Health healthEdit(Long fCode) {
        return healthRepo.findByCondCode(fCode);
    }

    @Override
    public Sources sourcesEdit(Long fCode) {
        return sourcesRepo.findBySourceCode(fCode);
    }

    @Override
    public Religion religionEdit(Long fCode) {
        return religionRepo.findByRelCode(fCode);
    }

    @Override
    public Document docEdit(Long fCode) {
        return documentRepo.findByDocCode(fCode);
    }

    @Override
    public Proffession proffessionEdit(Long fCode) {
        return proffessionRepo.findByProfCode(fCode);
    }

    @Override
    public Country countryEdit(Long fCode) {
        return countryRepo.findByCouCode(fCode);
    }

    @Override
    public County countyEdit(Long fCode) {
        return countyRepo.findByCountyCode(fCode);
    }

    @Override
    public SubCounty subcountyEdit(Long fCode) {
        return subCountyRepo.findBySubCode(fCode);
    }

    @Override
    public Location locationEdit(Long fCode) {
        return locationRepo.findByLouCode(fCode);
    }

    @Override
    public SubLocation sublocationEdit(Long fCode) {
        return subLocationRepo.findBySubLouCode(fCode);
    }

    @Override
    public Village villageEdit(Long fCode) {
        return villageRepo.findByVilCode(fCode);
    }




    @Override
    public byte[] getStudentDocument(Long docId, Long stdId) throws BadRequestException {
        byte[] arr = new byte[0];
        if(stdId==null)
            throw new BadRequestException("Document does not exist...");
        StudentDocs studentDocs = studentDocsRepo.findByStudent_StIdAndStdocCode(stdId,docId);
        String uploadFolder ="C:/TURNSOFT/Documents";
        String uploadedName = studentDocs.getUploadedFileName();
        String folderName="";
      Student student=studentRepo.findByStId(stdId);

            folderName = student.getAdmNo()+"_"+student.getStId();

        Path path = Paths.get(uploadFolder+"/"+folderName+"/"+uploadedName);
        if(path.toFile().exists()){
            try {
                arr = Files.readAllBytes(path);
            } catch (IOException e) {
                throw new BadRequestException(e.getMessage());
            }
        }
        return arr;
    }


    public void postParent(Parent parent) throws BadRequestException {
        if(parent.getParCode()==null) {
           Parent parent1= parentsRepo.findFirstByIdNoAndTelFather(parent.getIdNo(),parent.getTelFather());
           Parent parent2= parentsRepo.findFirstByIdNoAndTelMother(parent.getIdNo(),parent.getTelMother());
           Parent parent3=parentsRepo.findFirstByIdNo(parent.getIdNo());
           Parent parent4=parentsRepo.findFirstByTelFather(parent.getTelFather());

           if(parent1!=null){
               throw new BadRequestException("A  parent with similar id or phone entered exists");
           }else if(parent2!=null){
               throw new BadRequestException("A  parent with similar id or phone entered exists");
           }else if(parent3!=null){
               throw new BadRequestException("A parent with similar id exists");
           }else if(parent4!=null){
               throw new BadRequestException("A parent with similar phone number exists");
           }else {
               parentsRepo.save(parent);
           }
        }
        else{
            Parent par=parentsRepo.findByParCode(parent.getParCode());
            par.setEmailRequired(parent.isEmailRequired());
            if(parent.getPhoto()!=null) {
                par.setPhoto(parent.getPhoto());
            }
            par.setAddressFather(parent.getAddressFather());
            par.setAddressMother(parent.getAddressMother());
            par.setNameFather(parent.getNameFather());
            par.setNameMother(parent.getNameMother());
            par.setEmailFather(parent.getEmailFather());
            par.setEmailMother(parent.getEmailMother());
            par.setFatherProffession(parent.getFatherProffession());
            par.setMotherProffession(parent.getMotherProffession());
            par.setIdNo(parent.getIdNo());
            par.setParGur(parent.getParGur());
            par.setTelFather(parent.getTelFather());
            par.setTelMother(parent.getTelMother());
            parentsRepo.save(par);
        }
    }

    @Override
    public ArrayList<Student> findStuds(Long stdId) {
        return studentRepo.findByStuedent(stdId);
    }

    @Override
    public DataTablesOutput<Student> findStudents(DataTablesInput input, Long classId) {
        return studentRepo.findAll(input,CountrySpecs.dataClass(classId).and(CountrySpecs.studSession()));
    }

    @Override
    public DataTablesOutput<Student> findLeaveOuts(DataTablesInput input, Long classId) {
        return studentRepo.findAll(input,CountrySpecs.dataClass(classId).and(CountrySpecs.datapenSession()));
    }

    @Override
    public Student findSts(String admNo) {
        return studentRepo.findFirstByAdmNo(admNo);
    }

    @Override
    public void grantLeaveOut(Long classId, Date dateLeft, Date dateReturn, String reason) throws BadRequestException {
        List<Student> students=studentRepo.findByInSession("pending");
        for(Student s:students) {
            LeaveOuts leaveOut= leaveOutsRepo.findByStudent_StIdAndDateReturnedIsNull(s.getStId());
            if(leaveOut!=null){
                clearAllPending();
                throw new BadRequestException("Student " +s.getAdmNo()+" is already on leave");
            }
            else{
            int p=dateLeft.compareTo(dateReturn);
            if(p>0){
                clearAllPending();
                throw new BadRequestException("Date of Return cannot be before leave date");
            }else {
                LeaveOuts leaveOuts = new LeaveOuts();
                User user = userUtils.getCurrentUser();
                leaveOuts.setAuthorisedBy(user);
                leaveOuts.setGrantedBy(user);
                leaveOuts.setDateExpected(dateReturn);
                leaveOuts.setReason(reason);
                leaveOuts.setDateLeft(dateLeft);
                leaveOuts.setStudent(s);
                leaveOuts.setForms(s.getForms());
                leaveOutsRepo.save(leaveOuts);
            }
            }
        }
        clearAllPending();
    }
    @Override
    public void clearAllPending(){
        List<Student> students=studentRepo.findByInSession("pending");
        if(!(students.isEmpty())) {
            for (Student student : students) {
                Student std = studentRepo.findByStId(student.getStId());
                std.setInSession("inSession");
                studentRepo.save(std);
            }
        }
    }
    @Override
    public DataTablesOutput<LeaveOuts> onLeaveStudents(DataTablesInput input) {
        return leaveOutsRepo.findAll(input);
    }

    @Override
    public void returnLeaveOut(Date returnDate) throws BadRequestException {
        List<Student> students=studentRepo.findByInSession("pending");
        for(Student s:students) {
            LeaveOuts lea = leaveOutsRepo.findByStudentAndDateReturnedIsNull(s);
            if (lea != null) {
                LeaveOuts leaveOuts = leaveOutsRepo.findByStudent_StIdAndDateReturnedIsNull(s.getStId());
                int p = leaveOuts.getDateLeft().compareTo(returnDate);
                if (p > 0) {
                    clearAllPending();
                    throw new BadRequestException("Return Date cannot occur before leave date");
                } else {
                    leaveOuts.setDateReturned(returnDate);
                    leaveOutsRepo.save(leaveOuts);
                }
            }
            else{
                clearAllPending();
                throw new BadRequestException("Student "+s.getAdmNo()+" was not on leave");

            }
        }
        clearAllPending();

    }

    @Override
    public DataTablesOutput<LeaveOuts> pendingLeaveStudents(DataTablesInput input) {
        return leaveOutsRepo.findAll(input,CountrySpecs.dataLeaveOffs());
    }

    @Override
    public DataTablesOutput<LeaveOuts> returnedLeaveStudents(DataTablesInput input) {
        return leaveOutsRepo.findAll(input,CountrySpecs.dataLeaveOuts());
    }

    @Override
    public DataTablesOutput<LeaveOuts> nameLeaveStudents(DataTablesInput input, String name) {
        return leaveOutsRepo.findAll(input,CountrySpecs.dataLikeLeaveOuts(name));
    }

    @Override
    public DataTablesOutput<Forms> getMyClassTable(DataTablesInput input, String search) {
        return formsRepo.findAll(input,CountrySpecs.dataMyForm(search));
    }

    @Override
    public void saveGrades(GradingSystem gradingSystem) {
        if(gradingSystem.getGradingCode()==null) {
            if (gradingSystem.getAutoIncrement() != null) {
                gradingSystem.setAutoIncrement("Y");
            } else {
                gradingSystem.setAutoIncrement("N");
            }
            gradingSysRepo.save(gradingSystem);
        }
            else{
            GradingSystem system=gradingSysRepo.findByGradingCode(gradingSystem.getGradingCode());
            system.setApplicableFor(gradingSystem.getApplicableFor());
            system.setDepartment(gradingSystem.getDepartment());
            system.setGrade(gradingSystem.getGrade());
            system.setGradeFrom(gradingSystem.getGradeFrom());
            system.setGradeTo(gradingSystem.getGradeTo());
            system.setRemarks(gradingSystem.getRemarks());
            if (gradingSystem.getAutoIncrement() != null) {
                system.setAutoIncrement("Y");
            } else {
                system.setAutoIncrement("N");
            }
            gradingSysRepo.save(system);
             }
    }

    @Override
    public DataTablesOutput<GradingSystem> getGradingSystem(DataTablesInput input) {
        return gradingSysRepo.findAll(input);
    }

    @Override
    public void deleteGrade(Long code) {
        gradingSysRepo.deleteById(code);
    }

    @Override
    public void postClearance(Clearance clearance) {
      if(clearance.getClearanceCode()==null) {
          if(clearance.getClearedGrad()!=null){
            clearance.setClearedGrad("Y");
          }
          else{
            clearance.setClearedGrad("N");
          }
          LocalDate today = LocalDate.now();
          Date date = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
          clearance.setSaveDate(date);
          clearanceRepo.save(clearance);

      }
      else{
         Clearance clear=clearanceRepo.findByClearanceCode(clearance.getClearanceCode());
         clear.setLibraryName(clearance.getLibraryName());
         clear.setLibraryComment(clearance.getLibraryComment());
         clear.setLibraryDate(clearance.getLibraryDate());
         clear.setLaboratoryName(clearance.getLaboratoryName());
         clear.setLaboratoryComment(clearance.getLaboratoryComment());
         clear.setLaboratoryDate(clearance.getLaboratoryDate());
         clear.setAcctName(clearance.getAcctName());
         clear.setAcctComment(clearance.getAcctComment());
         clear.setAcctDate(clearance.getAcctDate());
         clear.setExamName(clearance.getExamName());
         clear.setExamComment(clearance.getExamComment());
         clear.setExamDate(clearance.getExamDate());
         clear.setHodName(clearance.getHodName());
         clear.setHodComment(clearance.getHodComment());
         clear.setHodDate(clearance.getHodDate());
         clear.setPrincipalName(clearance.getPrincipalName());
         clear.setPrincipalComment(clearance.getPrincipalComment());
         clear.setPrincipalDate(clearance.getPrincipalDate());
         clear.setDeputyName(clearance.getDeputyName());
         clear.setDeputyComment(clearance.getDeputyComment());
         clear.setDeputyDate(clearance.getDeputyDate());
         clear.setCordName(clearance.getCordName());
         clear.setCordComment(clearance.getCordComment());
         clear.setCordDate(clearance.getCordDate());
          LocalDate today = LocalDate.now();
          Date date = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
          clear.setSaveDate(date);
         if(clearance.getClearedGrad()!=null){
             clear.setClearedGrad("Y");
              }
              else{
                  clear.setClearedGrad("N");
              }
              clearanceRepo.save(clear);
      }
    }

    @Override
    public DataTablesOutput<Clearance> findAllClearances(DataTablesInput input) {
        return clearanceRepo.findAll(input);
    }

    @Override
    public void deleteClearance(Long code) {
        clearanceRepo.deleteById(code);
    }
}

