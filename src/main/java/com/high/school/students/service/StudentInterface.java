package com.high.school.students.service;


import com.high.school.Fee.model.FeeCategory;
import com.high.school.Locale.model.*;
import com.high.school.academics.model.Clearance;
import com.high.school.academics.model.GradingSystem;
import com.high.school.exceptions.BadRequestException;
import com.high.school.students.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public interface StudentInterface {
    byte[] getStudentDocument(Long docId, Long stdId) throws BadRequestException;

    void postParent(Parent parent) throws BadRequestException;

    DataTablesOutput<Student> findAllStudents(DataTablesInput input);

    void postNation(Country country) throws BadRequestException;

    void postCounty(County county) throws BadRequestException;

    void postSubCounty(SubCounty subCounty) throws BadRequestException;

    void postLocation(Location location) throws BadRequestException;

    void postSubLocation(SubLocation subLocation) throws BadRequestException;

    void postVillage(Village village) throws BadRequestException;

    void postDorm(Dorm dorm) throws BadRequestException;

    void postClass(Forms forms) throws BadRequestException;

    void postBlock(Block block) throws BadRequestException;

    void postTerm(Term term) throws BadRequestException;

    void postReligion(Religion religion) throws BadRequestException;

    void postSources(Sources sources) throws BadRequestException;

    void postHealth(Health health) throws BadRequestException;

    void postDocument(Document document) throws BadRequestException;

    void postYear(Year year) throws BadRequestException;

    void postProffession(Proffession proffession) throws BadRequestException;

    Page<Country> findCountries(Pageable pageable, String term);

    Page<County> findCounties(Pageable pageable, String term);

    Page<SubCounty> findSubCounty(Pageable pageable, String term);

    Page<Location> findLocation(Pageable pageable, String term);

    Page<SubLocation> findSubLocation(Pageable pageable, String term);

    Page<Village> findVillage(Pageable pageable, String term);

    DataTablesOutput<Country> findAllCountries(DataTablesInput input);

    DataTablesOutput<County> findAllCounties(DataTablesInput pageable, Long country);

    DataTablesOutput<SubCounty> findAllSubCounties(DataTablesInput input, Long county);

    DataTablesOutput<Location> getLocationTable(DataTablesInput input, Long subCounty);

    DataTablesOutput<SubLocation> getSubLocationTable(DataTablesInput input, Long location);

    DataTablesOutput<Village> getVillageTable(DataTablesInput input, Long subLocation);

    DataTablesOutput<Health> getHealthTable(DataTablesInput input);

    DataTablesOutput<Sources> getSourcesTable(DataTablesInput input);

    DataTablesOutput<Religion> getReligionTable(DataTablesInput input);

    DataTablesOutput<Document> getDocumentsTable(DataTablesInput input);

    DataTablesOutput<Proffession> getProffessionTable(DataTablesInput input);

    Page<Proffession> findProffession(Pageable pageable, String term);

    DataTablesOutput<Parent> getParentsTable(DataTablesInput input);

    DataTablesOutput<Block> getBlockTable(DataTablesInput input);

    Page<Block> pageBlock(Pageable pageable, String term);

    DataTablesOutput<Forms> getClassTable(DataTablesInput input);

    DataTablesOutput<Term> getTermTable(DataTablesInput input);

    DataTablesOutput<FeeCategory> getFeeTable(DataTablesInput input);

    void postFeeCategory(FeeCategory feeCategory) throws BadRequestException;

    DataTablesOutput<Dorm> getHouseTable(DataTablesInput input);

    DataTablesOutput<Year> getYearTable(DataTablesInput input);

    Parent getParent(Long parCode);

    void deleteParent(Long parCode);

    Page<FeeCategory> pageFees(Pageable pageable, String term);

    Page<Dorm> pageDorm(Pageable pageable, String term);

    Page<Forms> pageClass(Pageable pageable, String term);

    Page<Term> pageTerm(Pageable pageable, String term);

    Page<Parent> pageParent(Pageable pageable, String term);

    Page<Religion> pageReligion(Pageable pageable, String term);

    Page<Sources> pageSources(Pageable pageable, String term);

    Page<Year> pageYear(Pageable pageable, String term);

    Page<Health> pageHealth(Pageable pageable, String term);

    Page<Document> pageDocs(Pageable pageable, String term);

    void postStatus(StudStatus studStatus) throws BadRequestException;

    DataTablesOutput<StudStatus> getStatusTable(DataTablesInput input);

    Page<StudStatus> pageStatus(Pageable pageable, String term);

    Student getStudent(Long studCode);

    void postStudent(Student student) throws IOException, BadRequestException;

    void uploadClientDocument(UploadBean uploadBean) throws BadRequestException;

    Page<Student> pageStudent(Pageable pageable, String term);


    byte[] getStudentDocument(Long stdId) throws BadRequestException;

    String getStudentDocumentType(Long docId) throws BadRequestException;

    DataTablesOutput<StudentDocs> findAllDocuments(DataTablesInput input, Long stdId);

    Forms findForm(Long fCode);

    Block findBlock(Long fCode);

    Term termEdit(Long fCode);

    FeeCategory getFeeCat(Long fCode);

    Year yearEdit(Long fCode);

    Dorm dormEdit(Long fCode);

    StudStatus statusEdit(Long fCode);

    Health healthEdit(Long fCode);

    Sources sourcesEdit(Long fCode);

    Religion religionEdit(Long fCode);

    Document docEdit(Long fCode);

    Proffession proffessionEdit(Long fCode);

    Country countryEdit(Long fCode);

    County countyEdit(Long fCode);

    SubCounty subcountyEdit(Long fCode);

    Location locationEdit(Long fCode);

    SubLocation sublocationEdit(Long fCode);

    Village villageEdit(Long fCode);

    DataTablesOutput<Student> findSpecificStudents(DataTablesInput input, String name, String admNo, String mobile, String form, String suspended, String transfers, String graduated);

    void deleteBlock(Long parCode);

    void deleteClass(Long parCode);

    void deleteSources(Long parCode);

    void deleteHealth(Long parCode);

    void deleteYear(Long parCode);

    void deleteTerm(Long parCode);

    void deleteProffession(Long parCode);

    void deleteReligion(Long parCode);

    void deleteDorm(Long parCode);

    void deleteStatus(Long parCode);

    void deleteDocuments(Long parCode);

    void deleteVillage(Long parCode);

    void deleteSubLocation(Long parCode);

    void deleteLocation(Long parCode);

    void deleteSubCounty(Long parCode);

    void deleteCounty(Long parCode);

    void deleteCountry(Long parCode);

    void deleteFeeCategory(Long parCode);

    void deleteStdDocument(Long adId, Long stud) throws BadRequestException;

    void deleteStudent(Long parCode);

    ArrayList<Student> findStuds(Long stdId);

    DataTablesOutput<Student> findStudents(DataTablesInput input, Long classId);

    DataTablesOutput<Student> findLeaveOuts(DataTablesInput input, Long classId);

    Student findSts(String admNo);

    void grantLeaveOut(Long classId, Date dateLeft, Date dateReturn, String reason) throws BadRequestException;

    DataTablesOutput<LeaveOuts> onLeaveStudents(DataTablesInput input);

    void returnLeaveOut(Date returnDate) throws BadRequestException;

    DataTablesOutput<LeaveOuts> pendingLeaveStudents(DataTablesInput input);

    DataTablesOutput<LeaveOuts> returnedLeaveStudents(DataTablesInput input);

    DataTablesOutput<LeaveOuts> nameLeaveStudents(DataTablesInput input, String name);

    public void clearAllPending();

    DataTablesOutput<Forms> getMyClassTable(DataTablesInput input, String search);

    void saveGrades(GradingSystem gradingSystem);

    DataTablesOutput<GradingSystem> getGradingSystem(DataTablesInput input);

    void deleteGrade(Long code);

    void postClearance(Clearance clearance);

    DataTablesOutput<Clearance> findAllClearances(DataTablesInput input);

    void deleteClearance(Long code);
}
