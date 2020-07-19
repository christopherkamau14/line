package com.high.school.academics.controllers;


import com.high.school.academics.model.*;
import com.high.school.academics.service.AcademicInterface;
import com.high.school.exceptions.BadRequestException;
import com.high.school.students.model.Forms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/protected/academics")
public class AcademicsController {

    @Autowired
    AcademicInterface academicInterface;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping("subjectgroups")
    public String subjectgroups(){
        return "subjectgroups";
    }

    @GetMapping("department")
    public String getDepartment(){
        return "department";
    }

    @GetMapping("subjects")
    public String getsubjects(){
        return "subjects";
    }

    @GetMapping("titles")
    public String getTitles(){
        return "titles";
    }

    @GetMapping("teachers")
    public String getTeachers(Model model){
        model.addAttribute("teacherId",-2000);
        return "teachers";
    }

    @GetMapping("workers")
    public String getWorkers(Model model){
        model.addAttribute("staffId",-2000);
        return "workers";
    }

    @GetMapping("classsubjects")
    public String classsubjects(){
        return "classsubjects";
    }

    @GetMapping("responsibility")
    public String getResponsibility(){
        return "responsibility";
    }

    @GetMapping("assignedTeacherSubjects")
    public String assignedTeacherSubjects(){
        return "assigned";
    }

    @GetMapping("termdates")
    public String termdates(){
        return "termdates";
    }

    @GetMapping("/pageDept")
    @ResponseBody
    Page<Department> getDepts(@RequestParam(required = false) String term, Pageable pageable) {
        return academicInterface.findDepartments(pageable,term);
    }

    @GetMapping("/pageTitle")
    @ResponseBody
    Page<Titles> pageTitle(@RequestParam(required = false) String term, Pageable pageable) {
        return academicInterface.pageTitle(pageable,term);
    }

    @GetMapping("/pageSubjects")
    @ResponseBody
    Page<StudSubjects> pageSubjects(@RequestParam(required = false) String term, Pageable pageable) {
        return academicInterface.pageSubjects(pageable,term);
    }

    @GetMapping("/pageTeacher")
    @ResponseBody
    Page<Teachers> pageTeacher(@RequestParam(required = false) String term, Pageable pageable) {
        return academicInterface.pageTeacher(pageable,term);
    }

    @GetMapping("/pageResponsibility")
    @ResponseBody
    Page<Responsibility> pageResponsibility(@RequestParam(required = false) String term, Pageable pageable) {
        return academicInterface.pageResponsibility(pageable,term);
    }
    @GetMapping("/pageWorkerResponsibility")
    @ResponseBody
    Page<Responsibility> pageWorkerResponsibility(@RequestParam(required = false) String term, Pageable pageable) {
        return academicInterface.pageWorkerResponsibility(pageable,term);
    }
    @GetMapping("/getSubjects")
    @ResponseBody
    public DataTablesOutput<StudSubjects> getSubjects(DataTablesInput input){
        return academicInterface.findSubs(input);
    }

    @GetMapping("/getResponsibilities")
    @ResponseBody
    public DataTablesOutput<Responsibility> getResponsibilities(DataTablesInput input){
    return  academicInterface.findResp(input);
    }

    @GetMapping("/findAllTeachers")
    @ResponseBody
    public DataTablesOutput<Teachers> findAllTeachers(DataTablesInput input){
        return  academicInterface.findAllTeachers(input);
    }
    @GetMapping("/findAllWorkers")
    @ResponseBody
    public DataTablesOutput<Staff> findAllWorkers(DataTablesInput input){
        return  academicInterface.findAllWorkers(input);
    }
    @GetMapping("/getTitles")
    @ResponseBody
    public DataTablesOutput<Titles> getTitles(DataTablesInput input){
        return  academicInterface.findTitles(input);
    }

    @GetMapping("/getAllSubjects")
    @ResponseBody
    public DataTablesOutput<StudSubjects> getAllAssigned(DataTablesInput input){
        return  academicInterface.getAllAssigned(input);
    }

    @GetMapping("/getClassSubjects")
    @ResponseBody
    public DataTablesOutput<ClassSubjects> getClassSubjects(DataTablesInput input){
        return  academicInterface.getClassSubjects(input);
    }

    @GetMapping("/getSubjectGroups")
    @ResponseBody
    public DataTablesOutput<SubjectGroups> getSubjectGroups(DataTablesInput input){
        return  academicInterface.getSubjectGroups(input);
    }
    @GetMapping("/getTermDatesTbl")
    @ResponseBody
    public DataTablesOutput<TermDates> getTermDatesTbl(DataTablesInput input){
        return  academicInterface.getTermDatesTbl(input);
    }

    @PostMapping("/setPending")
    public ResponseEntity<String> postVouchers(@RequestBody SubjectBean subjectBean, HttpServletRequest request) throws IllegalAccessException, IOException, BadRequestException {
        for(Long code: subjectBean.getSubjectCode()){
            StudSubjects subjects=academicInterface.subjectEdit(code);
            subjects.setAssigned("pending");
            academicInterface.saveSubjects(subjects);
        }
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }
    @PostMapping("/setAssigned")
    public ResponseEntity<String> postAssigned(@RequestBody SubjectBean subjectBean, HttpServletRequest request) throws IllegalAccessException, IOException, BadRequestException {
        for(Long code: subjectBean.getSubjectCode()){
            StudSubjects subjects=academicInterface.subjectEdit(code);
            subjects.setAssigned("assigned");
            academicInterface.saveSubjects(subjects);
        }
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }
    @PostMapping("/setAllPending")
    @ResponseBody
    public ResponseEntity<String> postAllStudents() throws BadRequestException {
        List<StudSubjects> getAllSubjects=academicInterface.findAssigned();
        if(!(getAllSubjects.isEmpty())) {
            for (StudSubjects s : getAllSubjects) {
                StudSubjects subjects = academicInterface.subjectEdit(s.getSubjectCode());
                subjects.setAssigned("pending");
                academicInterface.saveSubjects(subjects);
            }
        }
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }
    @PostMapping("/setAllAssigned")
    @ResponseBody
    public ResponseEntity<String> setAllAssigned() throws BadRequestException {
        List<StudSubjects> getAllSubjects=academicInterface.findPending();
        if(!(getAllSubjects.isEmpty())) {
            for (StudSubjects s : getAllSubjects) {
                StudSubjects subjects = academicInterface.subjectEdit(s.getSubjectCode());
                subjects.setAssigned("assigned");
                academicInterface.saveSubjects(subjects);
            }
        }
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }
    @GetMapping("/getAssignedSubjects")
    @ResponseBody
    public DataTablesOutput<StudSubjects> getAllUnAssigned(DataTablesInput input){
        return  academicInterface.getAllUnAssigned(input);
    }

    @GetMapping("/findAllAssignedSubjects")
    @ResponseBody
    public DataTablesOutput<TeacherSubjects> findAllAssignedSubjects(DataTablesInput input){
        return  academicInterface.findAllAssignedSubjects(input);
    }

    @GetMapping("/titlesEdit/{title}")
    @ResponseBody
    public Titles getTitle(@PathVariable Long title){
        return  academicInterface.findTitle(title);
    }

    @GetMapping("/responsibilityEdit/{res}")
    @ResponseBody
    public Responsibility getResp(@PathVariable Long res){
        return  academicInterface.findgetResp(res);
    }

    @GetMapping("/getDepartment")
    @ResponseBody
    public DataTablesOutput<Department> getDept(DataTablesInput input){
        return  academicInterface.findDepts(input);
    }

    @PostMapping("/saveDept")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveDept(Department department){
        academicInterface.saveDept(department);
    }


    @PostMapping("/saveSubject")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveSubjects(StudSubjects studSubjects){
        academicInterface.saveSubjects(studSubjects);
    }

    @PostMapping("/saveClassSubjects")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveClassSubjects(ClassSubjects classSubjects){
        academicInterface.saveClassSubjects(classSubjects);
    }

    @PostMapping("/saveTermDates")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveTermDates(TermDates termDates){
        academicInterface.saveTermDates(termDates);
    }

    @PostMapping("/saveSubjectGroup")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveSubjectGroup(SubjectGroups subjectGroups){
        academicInterface.saveSubjectGroup(subjectGroups);
    }

    @PostMapping("/saveTitles")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveTitles(Titles titles){
        academicInterface.saveTitles(titles);
    }


    @PostMapping("/saveRes")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveRes(Responsibility responsibility){
        academicInterface.saveRes(responsibility);
    }

    @PostMapping("/saveTeacher")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveTeacher(Teachers teachers) throws IOException, BadRequestException {
        if ((teachers.getFile() != null) && (!teachers.getFile().isEmpty())) {
            if (teachers.getFile().getSize() != 0) {
                byte[] photo = teachers.getFile().getBytes();
                teachers.setPhoto(photo);
            }
        }

        academicInterface.saveTeacher(teachers);
    }

    @PostMapping("/saveStaff")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveStaff(Staff staff) throws IOException, BadRequestException {
        if ((staff.getFile() != null) && (!staff.getFile().isEmpty())) {
            if (staff.getFile().getSize() != 0) {
                byte[] photo = staff.getFile().getBytes();
                staff.setPhoto(photo);
            }
        }

        academicInterface.saveStaff(staff);
    }

    @PostMapping("/transferAssignedSubjects")
    @ResponseStatus(HttpStatus.CREATED)
    public void transferAssignedSubjects(@RequestParam(required = false) Long classId,
                                         @RequestParam Long tFrom,
                                         @RequestParam Long tTo) throws BadRequestException {
        academicInterface.transferAssignedSubjects(classId,tFrom,tTo);
    }
    @GetMapping("/teacherPhoto/{teaCode}")
    public void getImage(HttpServletResponse response, @PathVariable Long teaCode)
            throws IOException, ServletException {
        Teachers teachers = academicInterface.getTeacher(teaCode);
        if (teachers.getTeacherCode()!=null && teachers.getPhoto()!=null ) {
            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
            response.getOutputStream().write(teachers.getPhoto());
            response.getOutputStream().close();
        }
    }

    @GetMapping("/workerPhoto/{teaCode}")
    public void getWorkerImage(HttpServletResponse response, @PathVariable Long teaCode)
            throws IOException, ServletException {
        Staff staff = academicInterface.getStaff(teaCode);
        if (staff.getStaffCode()!=null && staff.getPhoto()!=null ) {
            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
            response.getOutputStream().write(staff.getPhoto());
            response.getOutputStream().close();
        }
    }

    @GetMapping("/deleteClassSubject/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClassSubject(@PathVariable Long code) throws BadRequestException {
        academicInterface.deleteClassSubject(code);
    }

    @GetMapping("/deleteSubjectGroup/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSubjectGroup(@PathVariable Long code) throws BadRequestException {
        academicInterface.deleteSubjectGroup(code);
    }
    @GetMapping("/deleteTermDates/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTermDates(@PathVariable Long code) throws BadRequestException {
        academicInterface.deleteTermDates(code);
    }
    @PostMapping("/removeAllAssignedSubjects")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAllAssignedSubjects(@RequestParam Long teacher) throws BadRequestException {
        academicInterface.removeAllAssignedSubjects(teacher);
    }

    @PostMapping("/saveTeacherSubjects")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveTeacherSubjects(@RequestParam Long classId,
                            @RequestParam(required = false)Long teacherId) throws BadRequestException {
        List<StudSubjects> subjects=academicInterface.findPending();
        Teachers t = academicInterface.getTeacher(teacherId);
        Forms forms = academicInterface.findForm(classId);
        if(subjects.isEmpty()){
            throw new BadRequestException("No subjects selected to assign");
        }
        else {
            for (StudSubjects s : subjects) {
                TeacherSubjects teacherSubjects=academicInterface.checkFirst(t,forms,s);
                if(teacherSubjects==null){
                    academicInterface.clearAllPending();
                    throw new BadRequestException(s.getName()+" is already assigned in this class");
                }
                else {
                    TeacherSubjects subject = new TeacherSubjects();
                    subject.setForms(forms);
                    subject.setTeachers(t);
                    subject.setSubjects(s);
                    academicInterface.saveTeacherSubject(subject);
                }
            }

        }
        academicInterface.clearAllPending();
    }
    @PostMapping("/saveAssigned")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveAssigned(@RequestParam Long classId,
                              @RequestParam Long teacher
                              ) throws BadRequestException {
        academicInterface.saveAssigned(classId,teacher);
    }
    @GetMapping("/clearAllPendingSubjects")
    @ResponseBody
    public void clearAllPendingLeaves() throws BadRequestException {
        academicInterface.clearAllPending();

    }
    @GetMapping("/deptEdit/{deptId}")
    @ResponseBody
    public Department getMyDept(@PathVariable Long deptId){
        return academicInterface.findDeptById(deptId);
    }
    @GetMapping("/subjectEdit/{subId}")
    @ResponseBody
    public StudSubjects subjectEdit(@PathVariable Long subId){
        return academicInterface.subjectEdit(subId);
    }

    @RequestMapping(value = { "deleteDepartment/{dept}" }, method = {
            org.springframework.web.bind.annotation.RequestMethod.GET })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDept(@PathVariable Long dept){
        academicInterface.deleteDept(dept);
    }

    @RequestMapping(value = { "deleteSubjects/{subject}" }, method = {
            org.springframework.web.bind.annotation.RequestMethod.GET })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSubjects(@PathVariable Long subject){
        academicInterface.deleteSubjects(subject);
    }

    @RequestMapping(value = { "deleteTeacher/{teacher}" }, method = {
            org.springframework.web.bind.annotation.RequestMethod.GET })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTeacher(@PathVariable Long teacher){
        academicInterface.deleteTeacher(teacher);
    }


    @RequestMapping(value = { "deleteWorker/{staff}" }, method = {
            org.springframework.web.bind.annotation.RequestMethod.GET })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWorker(@PathVariable Long staff){
        academicInterface.deleteWorker(staff);
    }
    @RequestMapping(value = { "deleteTitle/{title}" }, method = {
            org.springframework.web.bind.annotation.RequestMethod.GET })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTitle(@PathVariable Long title){
        academicInterface.deleteTitle(title);
    }

    @RequestMapping(value = { "deleteRes/{res}" }, method = {
            org.springframework.web.bind.annotation.RequestMethod.GET })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRes(@PathVariable Long res){
        academicInterface.deleteRes(res);
    }

    @GetMapping("/getTeacher/{tCode}")
    @ResponseBody
    public Teachers setPending(@PathVariable Long tCode) throws BadRequestException {

        return academicInterface.getTeacher(tCode);
    }
    @PostMapping("/editMyTeacher")
    public String editTeacher(@RequestParam Long teacher,Model model) throws BadRequestException {
         model.addAttribute("teacherId",teacher);
         System.out.print(teacher);
        return "teachers";
    }
    @GetMapping("/getWorker/{tCode}")
    @ResponseBody
    public Staff getStaff(@PathVariable Long tCode) throws BadRequestException {

        return academicInterface.getStaff(tCode);
    }
    @PostMapping("/editMyWorker")
    public String editWorker(@RequestParam Long staff,Model model) throws BadRequestException {
        model.addAttribute("staffId",staff);
        return "workers";
    }
}
