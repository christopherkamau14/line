package com.high.school.students.controllers;


import com.high.school.Reports.Repts;
import com.high.school.exceptions.BadRequestException;
import com.high.school.students.model.*;
import com.high.school.students.repo.StudentRepo;
import com.high.school.students.service.StudentInterface;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/protected/details")
public class StudentController {

    @Autowired
    StudentInterface studentInterface;

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    ResourceLoader resourceLoader;


    @Autowired
    Repts repts;



    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping("/leaveouts")
    public String leaveouts() {
        return "leaveouts";
    }

    @GetMapping("/reports")
    public String reports() {
        return "reports";
    }

    @PostMapping("/inSession")
    @ResponseBody
    public ResponseEntity<String> postGlVouchers(@RequestBody InSession inSession, HttpServletRequest request) throws IllegalAccessException, IOException, BadRequestException {
       for(String admNo: inSession.getAdmNo()){
      Student student=studentInterface.findSts(admNo);
      student.setInSession("pending");
      studentRepo.save(student);
       }
           return new ResponseEntity<String>("OK", HttpStatus.OK);
    }
    @PostMapping("/grantAll")
    @ResponseBody
    public ResponseEntity<String> postAllStudents(@RequestParam Long classId) throws BadRequestException {
        List<Student> getAll=studentRepo.findByForms_ClassCode(classId);
        for(Student s: getAll){
            Student student=studentRepo.findByStId(s.getStId());
            student.setInSession("pending");
            studentRepo.save(student);
        }
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }
    @PostMapping("/removeAllLeaves")
    @ResponseBody
    public ResponseEntity<String> removeAllStudents(@RequestParam Long classId) {
        List<Student> getAll=studentRepo.findByInSession("pending");
        for(Student s: getAll){
            Student student=studentRepo.findByStId(s.getStId());
            student.setInSession("inSession");
            studentRepo.save(student);
        }
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }
    @PostMapping("/offSession")
    public ResponseEntity<String> postVouchers(@RequestBody InSession inSession, HttpServletRequest request) throws IllegalAccessException, IOException, BadRequestException {
        for(String admNo: inSession.getAdmNo()){
            Student student=studentRepo.findFirstByAdmNo(admNo);
            student.setInSession("inSession");
            studentRepo.save(student);
        }
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }
    @GetMapping("/report")
    public void studentReport(@RequestParam(required = false) Long stdId, @RequestParam(required = false) String format, HttpServletResponse response) throws IOException, JRException {

            JasperPrint jasperPrint = null;

            jasperPrint = repts.studReport(stdId, format);
            byte[] pdf = null;

            String filename = "Student.pdf";
            pdf = JasperExportManager.exportReportToPdf(jasperPrint);
            response.setContentType("application/x-download");
            response.setContentLength(pdf.length);
            response.addHeader("Content-disposition", "attachment; filename=\"" + filename + "\"");
            OutputStream out = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, out);


    }
    @GetMapping("/getClassBlock/{classId}")
    @ResponseBody
    public DataTablesOutput<Student> getClasses(DataTablesInput input,@PathVariable Long classId)
            throws IllegalAccessException {
        return studentInterface.findStudents(input,classId);
    }
    @GetMapping("/getLeaveClassBlock/{classId}")
    @ResponseBody
    public DataTablesOutput<Student> getLeaveOuts(DataTablesInput input, @PathVariable Long classId)
            throws IllegalAccessException {
        return studentInterface.findLeaveOuts(input,classId);
    }
    @GetMapping("/onLeaveStudents")
    @ResponseBody
    public DataTablesOutput<LeaveOuts> onLeaveStudents(DataTablesInput input)
            throws IllegalAccessException {
        return studentInterface.onLeaveStudents(input);
    }
    @GetMapping("/pendingLeaveStudents")
    @ResponseBody
    public DataTablesOutput<LeaveOuts> pendingLeaveStudents(DataTablesInput input)
            throws IllegalAccessException {
        return studentInterface.pendingLeaveStudents(input);
    }
    @GetMapping("/returnedLeaveStudents")
    @ResponseBody
    public DataTablesOutput<LeaveOuts> returnedLeaveStudents(DataTablesInput input)
            throws IllegalAccessException {
        return studentInterface.returnedLeaveStudents(input);
    }
    @GetMapping("/nameLeaveStudents")
    @ResponseBody
    public DataTablesOutput<LeaveOuts> nameLeaveStudents(DataTablesInput input,@RequestParam String name)
            throws IllegalAccessException {
        return studentInterface.nameLeaveStudents(input,name);
    }
    @GetMapping("/grantLeaveOut")
    @ResponseBody
    public void grantLeaveOut(@RequestParam Long classId,
                              @RequestParam Date left,
                              @RequestParam Date returnDate,
                              @RequestParam(required = false) String reason) throws BadRequestException {
        studentInterface.grantLeaveOut(classId,left,returnDate,reason);
    }
    @GetMapping("/returnLeaveOut")
    @ResponseBody
    public void returnLeaveOut(@RequestParam Date returnDate) throws BadRequestException {
        if(returnDate==null){
          throw new BadRequestException("Enter return date");
        }else {
            studentInterface.returnLeaveOut(returnDate);
        }
    }
    @GetMapping("/clearAllPendingLeaves")
    @ResponseBody
    public void clearAllPendingLeaves() throws BadRequestException {
            studentInterface.clearAllPending();

    }
    @GetMapping("/setPending")
    @ResponseBody
    public Student setPending(@RequestParam Long stId) throws BadRequestException {
        studentInterface.clearAllPending();
     Student student=studentRepo.findByStId(stId);
     student.setInSession("pending");
     studentRepo.save(student);
     return student;
    }
}
