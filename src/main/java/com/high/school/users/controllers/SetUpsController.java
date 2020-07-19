package com.high.school.users.controllers;

import com.high.school.Fee.model.FeeCategory;
import com.high.school.Locale.model.*;
import com.high.school.academics.model.Clearance;
import com.high.school.academics.model.GradingSystem;
import com.high.school.examination.model.CombineBean;
import com.high.school.examination.service.ExamInterface;
import com.high.school.exceptions.BadRequestException;
import com.high.school.students.model.*;
import com.high.school.students.service.StudentInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Produces;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/protected")
public class SetUpsController {

    @Autowired
    StudentInterface studentInterface;

    @Autowired
    ExamInterface examInterface;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping("/home")
    public ModelAndView home(){
        return new ModelAndView("home");
    }
    @RequestMapping("/student")
    public String student(Model model){
        model.addAttribute("studentId",-2000);
        return "student";

    }
    @RequestMapping("/clearance")
    public String clearance(Model model){
        return "clearance";

    }

    @GetMapping("/stddocs")
    public String stddocs(Model model){
        return "stddocs";
    }

    @GetMapping("/grading")
    public String grading(Model model){
        return "grading";
    }

    @GetMapping("/parent")
    public String parent(){
        return "parent";

    }

    @GetMapping("/locale")
    public String locality(){
        return "locale";

    }

    @GetMapping("/health")
    public String health(){
        return "health";

    }

    @GetMapping("/class")
    public String viewClass(){
        return "class";

    }

    @GetMapping("/term")
    public String term(){
        return "term";

    }

    @GetMapping("/fee")
    public String fee(){
        return "fee";

    }

    @GetMapping("/setup")
    public ModelAndView setup(){
        return new ModelAndView("setup");

    }

    @GetMapping("/house")
    public String house(){
        return "house";

    }

    @GetMapping("/religion")
    public String religion(){
        return "religion";

    }

    @GetMapping("/source")
    public String source(){
        return "source";

    }
    @GetMapping("/year")
    public String year(){
        return "year";

    }
    @GetMapping("/block")
    public ModelAndView block(){
        return new ModelAndView("block");

    }
    @GetMapping("/docs")
    public String docs(){
        return "docs";

    }
    @GetMapping("/proffession")
    public String proffession(){
        return "proffession";

    }
    @GetMapping("/getParentForm")
    public String getParentForm(Model model){
        model.addAttribute("parentId",-2000);
        return "parentForm";

    }
    @GetMapping("/status")
    public String getstatus(Model model){
        return "status";

    }
    @GetMapping("/deleteVillage/{parCode}")
    @ResponseBody
    public void deleteVillage(@PathVariable Long parCode){
        studentInterface.deleteVillage(parCode);
    }
    @GetMapping("/deleteSubLocation/{parCode}")
    @ResponseBody
    public void deleteSubLocation(@PathVariable Long parCode){
        studentInterface.deleteSubLocation(parCode);
    }
    @GetMapping("/deleteLocation/{parCode}")
    @ResponseBody
    public void deleteLocation(@PathVariable Long parCode){
        studentInterface.deleteLocation(parCode);
    }
    @GetMapping("/deleteFeeCategory/{parCode}")
    @ResponseBody
    public void deleteFeeCategory(@PathVariable Long parCode){
        studentInterface.deleteFeeCategory(parCode);
    }
    @GetMapping("/deleteSubCounty/{parCode}")
    @ResponseBody
    public void deleteSubCounty(@PathVariable Long parCode){
        studentInterface.deleteSubCounty(parCode);
    }
    @GetMapping("/deleteCounty/{parCode}")
    @ResponseBody
    public void deleteCounty(@PathVariable Long parCode){
        studentInterface.deleteCounty(parCode);
    }
    @GetMapping("/deleteCountry/{parCode}")
    @ResponseBody
    public void deleteCountry(@PathVariable Long parCode){
        studentInterface.deleteCountry(parCode);
    }
    @GetMapping("/deleteStudent/{parCode}")
    @ResponseBody
    public void deleteStudent(@PathVariable Long parCode){
        studentInterface.deleteStudent(parCode);
    }
    @GetMapping("/deleteStatus/{parCode}")
    @ResponseBody
    public void deleteStatus(@PathVariable Long parCode){
        studentInterface.deleteStatus(parCode);
    }
    @GetMapping("/deleteDocuments/{parCode}")
    @ResponseBody
    public void deleteDocuments(@PathVariable Long parCode){
        studentInterface.deleteDocuments(parCode);
    }
    @GetMapping("/deleteDorm/{parCode}")
    @ResponseBody
    public void deleteDorm(@PathVariable Long parCode){
        studentInterface.deleteDorm(parCode);
    }
    @GetMapping("/deleteHealth/{parCode}")
    @ResponseBody
    public void deleteHealth(@PathVariable Long parCode){
        studentInterface.deleteHealth(parCode);
    }
    @GetMapping("/deleteYear/{parCode}")
    @ResponseBody
    public void deleteYear(@PathVariable Long parCode){
        studentInterface.deleteYear(parCode);
    }
    @GetMapping("/deleteTerm/{parCode}")
    @ResponseBody
    public void deleteTerm(@PathVariable Long parCode){
        studentInterface.deleteTerm(parCode);
    }
    @GetMapping("/deleteProffession/{parCode}")
    @ResponseBody
    public void deleteProffession(@PathVariable Long parCode){
        studentInterface.deleteProffession(parCode);
    }
    @GetMapping("/deleteReligion/{parCode}")
    @ResponseBody
    public void deleteReligion(@PathVariable Long parCode){
        studentInterface.deleteReligion(parCode);
    }
    @GetMapping("/deleteSources/{parCode}")
    @ResponseBody
    public void deleteSources(@PathVariable Long parCode){
        studentInterface.deleteSources(parCode);
    }
    @GetMapping("/deleteBlock/{parCode}")
    @ResponseBody
    public void deleteBlock(@PathVariable Long parCode){
        studentInterface.deleteBlock(parCode);
    }
    @GetMapping("/deleteClass/{parCode}")
    @ResponseBody
    public void deleteClass(@PathVariable Long parCode){
        studentInterface.deleteClass(parCode);
    }
    @GetMapping("/deleteParent/{parCode}")
    @ResponseBody
    public void deleteParent(@PathVariable Long parCode){
studentInterface.deleteParent(parCode);
    }

    @PostMapping("/editStudentForm")
    public String editStudentForm(Model model, @RequestParam Long id){
        model.addAttribute("studentId",id);
        model.addAttribute("rpts","rpts");
        return "student";
    }

    @PostMapping("/editParentForm")
    public String getParentForm(Model model, @RequestParam Long id){
        model.addAttribute("parentId",id);
        return "parentForm";

    }

    @RequestMapping(value = "/parentPhoto/{parCode}")
    public void getImage(HttpServletResponse response, @PathVariable Long parCode)
            throws IOException {
        Parent parent = studentInterface.getParent(parCode);
        if (parent.getParCode()!=null && parent.getPhoto()!=null ) {
            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
            response.getOutputStream().write(parent.getPhoto());
            response.getOutputStream().close();
        }
    }
    @RequestMapping(value = "/studentPhoto/{studCode}")
    public void getStudImage(HttpServletResponse response, @PathVariable Long studCode)
            throws IOException {
        Student stud = studentInterface.getStudent(studCode);
        if (stud.getStId()!=null && stud.getPhoto()!=null ) {
            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
            response.getOutputStream().write(stud.getPhoto());
            response.getOutputStream().close();
        }
    }
    @RequestMapping(value = "/parentDetails/{parCode}")
    @ResponseBody
    public Parent getDetails(HttpServletResponse response, @PathVariable Long parCode) {
        return studentInterface.getParent(parCode);
    }
    @RequestMapping(value = "/studentDetails/{stdId}")
    @ResponseBody
    public Student studentDetails(HttpServletResponse response, @PathVariable Long stdId) {
        return studentInterface.getStudent(stdId);
    }

    @PostMapping("/postStudent")
    @ResponseStatus(HttpStatus.CREATED)
    public void postStudent(Student student) throws IOException, BadRequestException {
        if ((student.getFile() != null) && (!student.getFile().isEmpty())) {
            if (student.getFile().getSize() != 0) {
                byte[] photo = student.getFile().getBytes();
                student.setPhoto(photo);
            }
        }
        studentInterface.postStudent(student);
    }

    @PostMapping("/saveGrades")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveGrades(GradingSystem gradingSystem) {

        studentInterface.saveGrades(gradingSystem);
    }

    @PostMapping("/parent")
    @ResponseStatus(HttpStatus.CREATED)
    public void postParent(Parent parent)
     throws BadRequestException, IOException {

        if(parent.getMailReq()!= null){
            parent.setEmailRequired(true);
        }else{
            parent.setEmailRequired(false);
        }
        if ((parent.getFile() != null) && (!parent.getFile().isEmpty())) {
            if (parent.getFile().getSize() != 0) {
                byte[] photo = parent.getFile().getBytes();
                parent.setPhoto(photo);
            }
        }
           studentInterface.postParent(parent);
        }

    @PostMapping("/saveClearance")
    @ResponseStatus(HttpStatus.CREATED)
    public void postClearance(Clearance clearance) {
        studentInterface.postClearance(clearance);
    }


    @GetMapping("/getUsers")
    @ResponseBody
    public DataTablesOutput<Student> getClasses(DataTablesInput input) {
        return studentInterface.findAllStudents(input);
    }

    @GetMapping("/searchStudents")
    @ResponseBody
    public DataTablesOutput<Student> studentsSearch(DataTablesInput input,

                                                    @RequestParam(required = false) String name,
                                                    @RequestParam(required = false) String admNo,
                                                    @RequestParam(required = false) String mobile,
                                                    @RequestParam(required = false) String form,
                                                    @RequestParam(required = false) String suspended,
                                                    @RequestParam(required = false) String transfers, @RequestParam(required = false) String graduated

                                                    ) {
        return studentInterface.findSpecificStudents(input,name,admNo,mobile,form,suspended,transfers,graduated);
    }
    @GetMapping("/getCountryTable")
    @ResponseBody
    public DataTablesOutput<Country> getCountryTable(DataTablesInput input) {
        return studentInterface.findAllCountries(input);
    }
    @GetMapping("/getGradingSystem")
    @ResponseBody
    public DataTablesOutput<GradingSystem> getGradingSystem(DataTablesInput input) {
        return studentInterface.getGradingSystem(input);
    }
    @GetMapping("/findAllClearances")
    @ResponseBody
    public DataTablesOutput<Clearance> findAllClearances(DataTablesInput input) {
        return studentInterface.findAllClearances(input);
    }
    @GetMapping("getCountyTable")
    @ResponseBody
    public DataTablesOutput<County> getCountyTable(DataTablesInput input, @RequestParam(required = false) Long country) {
        return studentInterface.findAllCounties(input,country);
    }
    @GetMapping("getStdDocsTable/{stdId}")
    @ResponseBody
    public DataTablesOutput<StudentDocs> getStudentDocs(DataTablesInput input, @PathVariable Long stdId) {
        return studentInterface.findAllDocuments(input,stdId);
    }
    @GetMapping("getSubCountyTable/{county}")
    @ResponseBody
    public DataTablesOutput<SubCounty> getSubCountyTable(DataTablesInput input, @PathVariable Long county) {
        return studentInterface.findAllSubCounties(input,county);
    }
    @GetMapping("getLocationTable/{subCounty}")
    @ResponseBody
    public DataTablesOutput<Location> getLocationTable(DataTablesInput input, @PathVariable Long subCounty) {
        return studentInterface.getLocationTable(input,subCounty);
    }
    @GetMapping("getSubLocationTable/{location}")
    @ResponseBody
    public DataTablesOutput<SubLocation> getSubLocationTable(DataTablesInput input, @PathVariable Long location) {
        return studentInterface.getSubLocationTable(input,location);
    }
    @GetMapping("getVillageTable/{subLocation}")
    @ResponseBody
    public DataTablesOutput<Village> getVillageTable(DataTablesInput input, @PathVariable Long subLocation) {
        return studentInterface.getVillageTable(input,subLocation);
}
    @GetMapping("getHealthTable")
    @ResponseBody
    public DataTablesOutput<Health> getHealthTable(DataTablesInput input) {
        return studentInterface.getHealthTable(input);
    }
    @GetMapping("getSourcesTable")
    @ResponseBody
    public DataTablesOutput<Sources> getSourcesTable(DataTablesInput input) {
        return studentInterface.getSourcesTable(input);
    }
    @GetMapping("getReligionTable")
    @ResponseBody
    public DataTablesOutput<Religion> getReligionTable(DataTablesInput input) {
        return studentInterface.getReligionTable(input);
    }
    @GetMapping("getYearTable")
    @ResponseBody
    public DataTablesOutput<Year> getYearTable(DataTablesInput input) {
        return studentInterface.getYearTable(input);
    }
    @GetMapping("getDocumentsTable")
    @ResponseBody
    public DataTablesOutput<Document> getDocumentsTable(DataTablesInput input) {
        return studentInterface.getDocumentsTable(input);
    }
    @GetMapping("getProffessionTable")
    @ResponseBody
    public DataTablesOutput<Proffession> getProffessionTable(DataTablesInput input) {
        return studentInterface.getProffessionTable(input);
    }
    @GetMapping("getParentsTable")
    @ResponseBody
    public DataTablesOutput<Parent> getParentsTable( DataTablesInput input) {
        return studentInterface.getParentsTable(input);
    }
    @GetMapping("getBlockTable")
    @ResponseBody
    public DataTablesOutput<Block> getBlockTable(DataTablesInput input) {
        return studentInterface.getBlockTable(input);
    }
    @GetMapping("getClassTable")
    @ResponseBody
    public DataTablesOutput<Forms> getClassTable(DataTablesInput input) {
        return studentInterface.getClassTable(input);
    }

    @GetMapping("getMyClassTable")
    @ResponseBody
    public DataTablesOutput<Forms> getClassTable(DataTablesInput input,@RequestParam String search) {
        return studentInterface.getMyClassTable(input,search);
    }

    @GetMapping("getTermTable")
    @ResponseBody
    public DataTablesOutput<Term> getTermTable(DataTablesInput input) {
        return studentInterface.getTermTable(input);
    }
    @GetMapping("getFeeTable")
    @ResponseBody
    public DataTablesOutput<FeeCategory> getFeeTable(DataTablesInput input) {
        return studentInterface.getFeeTable(input);
    }
    @GetMapping("getHouseTable")
    @ResponseBody
    public DataTablesOutput<Dorm> getHouseTable(DataTablesInput input) {
        return studentInterface.getHouseTable(input);
    }
    @GetMapping("getStatusTable")
    @ResponseBody
    public DataTablesOutput<StudStatus> getStatusTable(DataTablesInput input) {
        return studentInterface.getStatusTable(input);
    }

    @PostMapping("/saveCountry")
    @ResponseStatus(HttpStatus.CREATED)
    public void postNation(Country country) throws BadRequestException {
    studentInterface.postNation(country);
    }
    @PostMapping("/saveFeeCategory")
    @ResponseStatus(HttpStatus.CREATED)
    public void postFeeCategory(FeeCategory feeCategory) throws BadRequestException {
        studentInterface.postFeeCategory(feeCategory);
    }
    @PostMapping("/saveCounty")
    @ResponseStatus(HttpStatus.CREATED)
    public void postCounty(County county) throws BadRequestException {
        studentInterface.postCounty(county);
    }
    @PostMapping("/saveSubCounty")
    @ResponseStatus(HttpStatus.CREATED)
    public void postSubCounty(SubCounty subCounty) throws BadRequestException {
        studentInterface.postSubCounty(subCounty);
    }
    @PostMapping("/saveLocation")
    @ResponseStatus(HttpStatus.CREATED)
    public void postLocation(Location location) throws BadRequestException {
        studentInterface.postLocation(location);
    }
    @PostMapping("/saveSubLocation")
    @ResponseStatus(HttpStatus.CREATED)
    public void postSubLocation(SubLocation subLocation) throws BadRequestException {
        studentInterface.postSubLocation(subLocation);
    }
    @PostMapping("/saveVillage")
    @ResponseStatus(HttpStatus.CREATED)
    public void postVillage(Village village) throws BadRequestException {
        studentInterface.postVillage(village);
    }
    @GetMapping("/getCountry")
    @ResponseBody
    Page<Country> getCountry(@RequestParam(required = false) String term, Pageable pageable) {
        return studentInterface.findCountries(pageable,term);
    }
    @GetMapping("/getSubCounty")
    @ResponseBody
    Page<SubCounty> getSubCounty(@RequestParam(required = false) String term, Pageable pageable) {
        return studentInterface.findSubCounty(pageable,term);
    }
    @GetMapping("/getCounty")
    @ResponseBody
    Page<County> getCounty(@RequestParam(required = false) String term, Pageable pageable) {
        return studentInterface.findCounties(pageable,term);
    }
    @GetMapping("/getLocation")
    @ResponseBody
    Page<Location> getLocation(@RequestParam(required = false) String term, Pageable pageable) {
        return studentInterface.findLocation(pageable,term);
    }
    @GetMapping("/getSubLocation")
    @ResponseBody
    Page<SubLocation> getSubLocation(@RequestParam(required = false) String term, Pageable pageable) {
        return studentInterface.findSubLocation(pageable,term);
    }
    @GetMapping("/getVillage")
    @ResponseBody
    Page<Village> getVillage(@RequestParam(required = false) String term, Pageable pageable) {
        return studentInterface.findVillage(pageable,term);
    }
    @GetMapping("/pageProffession")
    @ResponseBody
    Page<Proffession> pageProffession(@RequestParam(required = false) String term, Pageable pageable) {
        return studentInterface.findProffession(pageable,term);
    }
    @GetMapping("/pageBlock")
    @ResponseBody
    Page<Block> pageBlock(@RequestParam(required = false) String term, Pageable pageable) {
        return studentInterface.pageBlock(pageable,term);
    }
    @GetMapping("/pageFees")
    @ResponseBody
    Page<FeeCategory> pageFees(@RequestParam(required = false) String term, Pageable pageable) {
        return studentInterface.pageFees(pageable,term);
    }
    @GetMapping("/pageDorm")
    @ResponseBody
    Page<Dorm> pageDorm(@RequestParam(required = false) String term, Pageable pageable) {
        return studentInterface.pageDorm(pageable,term);
    }
    @GetMapping("/pageClass")
    @ResponseBody
    Page<Forms> pageClass(@RequestParam(required = false) String term, Pageable pageable) {
        return studentInterface.pageClass(pageable,term);
    }
    @GetMapping("/pageTerm")
    @ResponseBody
    Page<Term> pageTerm(@RequestParam(required = false) String term, Pageable pageable) {
        return studentInterface.pageTerm(pageable,term);
    }
    @GetMapping("/pageParent")
    @ResponseBody
    Page<Parent> pageParent(@RequestParam(required = false) String term, Pageable pageable) {
        return studentInterface.pageParent(pageable,term);
    }
    @GetMapping("/pageReligion")
    @ResponseBody
    Page<Religion> pageReligion(@RequestParam(required = false) String term, Pageable pageable) {
        return studentInterface.pageReligion(pageable,term);
    }
    @GetMapping("/pageSources")
    @ResponseBody
    Page<Sources> pageSources(@RequestParam(required = false) String term, Pageable pageable) {
        return studentInterface.pageSources(pageable,term);
    }
    @GetMapping("/pageYear")
    @ResponseBody
    Page<Year> pageYear(@RequestParam(required = false) String term, Pageable pageable) {
        return studentInterface.pageYear(pageable,term);
    }
    @GetMapping("/pageHealth")
    @ResponseBody
    Page<Health> pageHealth(@RequestParam(required = false) String term, Pageable pageable) {
        return studentInterface.pageHealth(pageable,term);
    }
    @GetMapping("/pageDocs")
    @ResponseBody
    Page<Document> pageDocs(@RequestParam(required = false) String term, Pageable pageable) {
        return studentInterface.pageDocs(pageable,term);
    }
    @GetMapping("/pageStatus")
    @ResponseBody
    Page<StudStatus> pageStatus(@RequestParam(required = false) String term, Pageable pageable) {
        return studentInterface.pageStatus(pageable,term);
    }
    @GetMapping("/pageStudent")
    @ResponseBody
    Page<Student> pageStudent(@RequestParam(required = false) String term, Pageable pageable) {
        return studentInterface.pageStudent(pageable,term);
    }
    @PostMapping("/saveDorm")
    @ResponseStatus(HttpStatus.CREATED)
    public void postDorm(Dorm dorm) throws BadRequestException {
        studentInterface.postDorm(dorm);
    }

    @PostMapping("/saveStatus")
    @ResponseStatus(HttpStatus.CREATED)
    public void postStatus(StudStatus studStatus) throws BadRequestException {
        studentInterface.postStatus(studStatus);
    }

    @RequestMapping(value = "/studentDocumentsView/{docId}/{stdId}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> thumbnail(@PathVariable Long stdId , @PathVariable Long docId) throws BadRequestException {
        byte[] content = studentInterface.getStudentDocument(docId,stdId);
        if (content.length>0) {
            String contentType = studentInterface.getStudentDocumentType(docId);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(contentType));
            headers.setContentLength(content.length);
            return new ResponseEntity<byte[]>(content, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("uploadStudentDocs")
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadClientDocs(UploadBean uploadBean) throws BadRequestException {
        studentInterface.uploadClientDocument(uploadBean);
    }
    @GetMapping("countryEdit/{fCode}")
    @ResponseBody
    public Country countryEdit(@PathVariable Long fCode){
        return studentInterface.countryEdit(fCode);
    }
    @GetMapping("subcountyEdit/{fCode}")
    @ResponseBody
    public SubCounty subcountyEdit(@PathVariable Long fCode){
        return studentInterface.subcountyEdit(fCode);
    }
    @GetMapping("locationEdit/{fCode}")
    @ResponseBody
    public Location locationEdit(@PathVariable Long fCode){
        return studentInterface.locationEdit(fCode);
    }
    @GetMapping("villageEdit/{fCode}")
    @ResponseBody
    public Village villageEdit(@PathVariable Long fCode){
        return studentInterface.villageEdit(fCode);
    }
    @GetMapping("sublocationEdit/{fCode}")
    @ResponseBody
    public SubLocation sublocationEdit(@PathVariable Long fCode){
        return studentInterface.sublocationEdit(fCode);
    }
    @GetMapping("countyEdit/{fCode}")
    @ResponseBody
    public County countyEdit(@PathVariable Long fCode){
        return studentInterface.countyEdit(fCode);
    }
    @GetMapping("classEdit/{fCode}")
    @ResponseBody
    public Forms getForms(@PathVariable Long fCode){
        return studentInterface.findForm(fCode);
    }
    @GetMapping("feeEdit/{fCode}")
    @ResponseBody
    public FeeCategory getFeeCat(@PathVariable Long fCode){
        return studentInterface.getFeeCat(fCode);
    }
    @GetMapping("termEdit/{fCode}")
    @ResponseBody
    public Term termEdit(@PathVariable Long fCode){
        return studentInterface.termEdit(fCode);
    }

    @GetMapping("blockEdit/{fCode}")
    @ResponseBody
    public Block getBlock(@PathVariable Long fCode){
        return studentInterface.findBlock(fCode);
    }

    @GetMapping("yearEdit/{fCode}")
    @ResponseBody
    public Year yearEdit(@PathVariable Long fCode){
        return studentInterface.yearEdit(fCode);
    }
    @GetMapping("dormEdit/{fCode}")
    @ResponseBody
    public Dorm dormEdit(@PathVariable Long fCode){
        return studentInterface.dormEdit(fCode);
    }
    @GetMapping("mystudEdit/{fCode}")
    @ResponseBody
    public StudStatus statusEdit(@PathVariable Long fCode){
        return studentInterface.statusEdit(fCode);
    }
    @GetMapping("healthEdit/{fCode}")
    @ResponseBody
    public Health healthEdit(@PathVariable Long fCode){
        return studentInterface.healthEdit(fCode);
    }
    @GetMapping("religionEdit/{fCode}")
    @ResponseBody
    public Religion religionEdit(@PathVariable Long fCode){
        return studentInterface.religionEdit(fCode);
    }
    @GetMapping("docsEdit/{fCode}")
    @ResponseBody
    public Document docEdit(@PathVariable Long fCode){
        return studentInterface.docEdit(fCode);
    }
    @GetMapping("sourcesEdit/{fCode}")
    @ResponseBody
    public Sources sourcesEdit(@PathVariable Long fCode){
        return studentInterface.sourcesEdit(fCode);
    }
    @GetMapping("proffessionEdit/{fCode}")
    @ResponseBody
    public Proffession proffessionEdit(@PathVariable Long fCode){
        return studentInterface.proffessionEdit(fCode);
    }
    @PostMapping("/saveClass")
    @ResponseBody
    public void postClass(Forms forms) throws BadRequestException {
        studentInterface.postClass(forms);
    }

    @PostMapping(value = "/saveBlock")
    @ResponseBody
    public void postBlock(Block block) throws BadRequestException {
        studentInterface.postBlock(block);
    }
    @PostMapping("/saveTerm")
    @ResponseBody
    public void postTerm(Term term) throws BadRequestException {
        studentInterface.postTerm(term);
    }

    @PostMapping("/saveReligion")
    @ResponseBody
    public ResponseEntity<?> multiUploadFileModel(@ModelAttribute Religion religion) throws BadRequestException {

           studentInterface.postReligion(religion);
        return new ResponseEntity("Successfully added!", HttpStatus.OK);
    }
    @PostMapping("/saveSources")
    @ResponseBody
    public void postSources(@ModelAttribute Sources sources) throws BadRequestException {
        studentInterface.postSources(sources);
    }

    @PostMapping("/saveHealth")
    @ResponseBody
    public void postHealth(Health health) throws BadRequestException {
        studentInterface.postHealth(health);
    }

    @PostMapping("/saveDocument")
    @ResponseBody
    public void postDocs(Document document) throws BadRequestException {

        studentInterface.postDocument(document);
    }

    @PostMapping("/saveYear")
    @ResponseBody
    public void postHealth(Year year) throws BadRequestException {
        studentInterface.postYear(year);
    }
    @RequestMapping(value = { "deleteStudDoc/{adId}/{stud}" }, method = {
            org.springframework.web.bind.annotation.RequestMethod.GET })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClientDoc(@PathVariable Long adId, @PathVariable Long stud) throws BadRequestException {
        studentInterface.deleteStdDocument(adId,stud);
    }

    @RequestMapping(value = { "deleteGrade/{code}" }, method = {
            org.springframework.web.bind.annotation.RequestMethod.GET })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGrade(@PathVariable Long code) {
        studentInterface.deleteGrade(code);
    }
    @RequestMapping(value = { "deleteClearance/{code}" }, method = {
            org.springframework.web.bind.annotation.RequestMethod.GET })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClearance(@PathVariable Long code) {
        studentInterface.deleteClearance(code);
    }

    @PostMapping("/saveProffession")
    @ResponseBody
    public void postHealth(Proffession proffession) throws BadRequestException {
        studentInterface.postProffession(proffession);
    }
}
