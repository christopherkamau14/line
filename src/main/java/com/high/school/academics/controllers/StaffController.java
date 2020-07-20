package com.high.school.academics.controllers;


import com.high.school.academics.model.Staff;
import com.high.school.academics.model.TeacherLeaves;
import com.high.school.academics.model.TeacherSession;
import com.high.school.academics.model.Teachers;
import com.high.school.academics.repo.TeacherRepo;
import com.high.school.academics.service.AcademicInterface;
import com.high.school.academics.service.StaffInterface;
import com.high.school.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/protected/staff")
public class StaffController {

    @Autowired
    StaffInterface staffInterface;

    @Autowired
    TeacherRepo teacherRepo;

    @Autowired
    AcademicInterface academicInterface;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping("/teacherleaveouts")
    public String leaveouts() {
        return "teacherleaveouts";
    }

    @GetMapping("/staffregister")
    public String staffregister() {
        return "staffregister";
    }

    @GetMapping("/teacherregister")
    public String teacherregister() {
        return "teacherregister";
    }

    @PostMapping("/inSessionTeacher")
    @ResponseBody
    public ResponseEntity<String> postGlVouchers(@RequestBody TeacherSession teacherSession, HttpServletRequest request) throws IllegalAccessException, IOException, BadRequestException {
        for(Long tc: teacherSession.getTeacherCode()){
            Teachers teachers=teacherRepo.findByTeacherCode(tc);
            teachers.setInSession("pending");
            teacherRepo.save(teachers);
        }
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }
    @PostMapping("/grantAllTeachers")
    @ResponseBody
    public ResponseEntity<String> postAllStudents() throws BadRequestException {
        List<Teachers> getAll=teacherRepo.findAll();
        for(Teachers t: getAll){
            Teachers teachers=teacherRepo.findByTeacherCode(t.getTeacherCode());
            teachers.setInSession("pending");
            teacherRepo.save(teachers);
        }
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }
    @PostMapping("/removeAllTeacherLeaves")
    @ResponseBody
    public ResponseEntity<String> removeAllStudents(@RequestParam Long classId) {
        List<Teachers> getAll=teacherRepo.findByInSession("pending");
        for(Teachers t: getAll){
            Teachers teachers=teacherRepo.findByTeacherCode(t.getTeacherCode());
            teachers.setInSession("inSession");
            teacherRepo.save(teachers);
        }
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }
    @PostMapping("/offSessionTeacher")
    public ResponseEntity<String> postVouchers(@RequestBody TeacherSession teacherSession, HttpServletRequest request) throws IllegalAccessException, IOException, BadRequestException {
        for(Long tc: teacherSession.getTeacherCode()){
            Teachers teachers=teacherRepo.findByTeacherCode(tc);
            teachers.setInSession("inSession");
            teacherRepo.save(teachers);
        }
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }


    @GetMapping("/getTeacherLeaves")
    @ResponseBody
    public DataTablesOutput<Teachers> getLeaveOuts(DataTablesInput input)
            throws IllegalAccessException {
        return staffInterface.findLeaveOuts(input);
    }
    @GetMapping("/getSchoolTeachers")
    @ResponseBody
    public DataTablesOutput<Teachers> getSchoolTeachers(DataTablesInput input)
            throws IllegalAccessException {
        return staffInterface.findSchoolTeachers(input);
    }
    @GetMapping("/onLeaveTeachers")
    @ResponseBody
    public DataTablesOutput<TeacherLeaves> onLeaveStudents(DataTablesInput input)
            throws IllegalAccessException {
        return staffInterface.onLeaveTeachers(input);
    }
    @GetMapping("/pendingLeaveTeachers")
    @ResponseBody
    public DataTablesOutput<TeacherLeaves> pendingLeaveStudents(DataTablesInput input)
            throws IllegalAccessException {
        return staffInterface.pendingLeaveTeachers(input);
    }
    @GetMapping("/returnedLeaveTeachers")
    @ResponseBody
    public DataTablesOutput<TeacherLeaves> returnedLeaveStudents(DataTablesInput input)
            throws IllegalAccessException {
        return staffInterface.returnedLeaveTeachers(input);
    }
    @GetMapping("/nameLeaveTeachers")
    @ResponseBody
    public DataTablesOutput<TeacherLeaves> nameLeaveStudents(DataTablesInput input,@RequestParam String name)
            throws IllegalAccessException {
        return staffInterface.nameLeaveTeachers(input,name);
    }
    @GetMapping("/grantTeacherLeaveOut")
    @ResponseBody
    public void grantLeaveOut(
                              @RequestParam Date left,
                              @RequestParam Date returnDate,
                              @RequestParam(required = false) String reason) throws BadRequestException {
        staffInterface.grantLeaveOut(left,returnDate,reason);
    }
    @GetMapping("/returnTeacherLeaveOut")
    @ResponseBody
    public void returnLeaveOut(@RequestParam Date returnDate) throws BadRequestException {
        if(returnDate==null){
            throw new BadRequestException("Enter return date");
        }else {
            staffInterface.returnLeaveOut(returnDate);
        }
    }
    @GetMapping("/clearAllPendingTeacherLeaves")
    @ResponseBody
    public void clearAllPendingLeaves() throws BadRequestException {
        staffInterface.clearAllPending();

    }
    @GetMapping("/setTeacherPending")
    @ResponseBody
    public Teachers setPending(@RequestParam Long teacher) throws BadRequestException {
        staffInterface.clearAllPending();
        Teachers teachers=teacherRepo.findByTeacherCode(teacher);
        teachers.setInSession("pending");
        teacherRepo.save(teachers);
        return teachers;
    }

    @RequestMapping(value = { "recordStaffTimeIn/{regCode}" }, method = {
            org.springframework.web.bind.annotation.RequestMethod.GET })
    public ResponseEntity<String> recordStaffTimeIn(@PathVariable Long regCode) throws BadRequestException {
        staffInterface.recordStaffTimeIn(regCode);
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }

    @RequestMapping(value = { "recordTimeIn/{regCode}" }, method = {
            org.springframework.web.bind.annotation.RequestMethod.GET })
    public ResponseEntity<String> recordTimeIn(@PathVariable Long regCode) throws BadRequestException {
        staffInterface.recordTimeIn(regCode);
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }

    @RequestMapping(value = { "recordTimeOut/{regCode}" }, method = {
            org.springframework.web.bind.annotation.RequestMethod.GET })
    public ResponseEntity<String> recordTimeOut(@PathVariable Long regCode) throws BadRequestException {
        staffInterface.recordTimeOut(regCode);
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }

    @RequestMapping(value = { "recordStaffTimeOut/{regCode}" }, method = {
            org.springframework.web.bind.annotation.RequestMethod.GET })
    public ResponseEntity<String> recordStaffTimeOut(@PathVariable Long regCode) throws BadRequestException {
        staffInterface.recordStaffTimeOut(regCode);
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }
    @GetMapping("/findRegisterWorkers")
    @ResponseBody
    public DataTablesOutput<Staff> findRegisterWorkers(DataTablesInput input){
        return  academicInterface.findRegisterWorkers(input);

    }

    @GetMapping("/findRegisterTeachers")
    @ResponseBody
    public DataTablesOutput<Teachers> findRegisterTeachers(DataTablesInput input){
        return  academicInterface.findRegisterTeachers(input);
    }
    @GetMapping("/nameRegisterStaff")
    @ResponseBody
    public DataTablesOutput<Staff> nameRegisterStaff(DataTablesInput input,
                                                     @RequestParam String name){
        return  academicInterface.nameRegisterStaff(input,name);
    }

    @GetMapping("/nameRegisterTeachers")
    @ResponseBody
    public DataTablesOutput<Teachers> nameRegisterTeachers(DataTablesInput input,
                                                           @RequestParam String name){
        return  academicInterface.nameRegisterTeachers(input,name);
    }
}
