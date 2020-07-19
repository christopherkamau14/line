package com.high.school.examination.controller;

import com.high.school.Reports.Repts;
import com.high.school.academics.model.StudSubjects;
import com.high.school.academics.model.TeacherSubjects;
import com.high.school.academics.model.Teachers;
import com.high.school.examination.model.*;
import com.high.school.examination.repo.ExamRegRepo;
import com.high.school.examination.service.ExamInterface;
import com.high.school.examination.service.MergeInterface;
import com.high.school.exceptions.BadRequestException;
import com.high.school.students.model.Forms;
import com.high.school.students.model.Student;
import com.high.school.students.model.Term;
import com.high.school.students.model.Year;
import com.high.school.students.repo.StudentRepo;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/protected/exams")
public class ExamsController {

    @Autowired
    ExamInterface examInterface;

    @Autowired
    MergeInterface mergeInterface;

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    Repts repts;

    @Autowired
    ExamRegRepo examRegRepo;

    @Autowired
    private JavaMailSender emailSender;

    @GetMapping("/examtype")
    public String examtype() {

        return "examtype";
    }
    @GetMapping("/combine")
    public String combine() {

        return "combine";
    }
    @GetMapping("/merge")
    public String exammerge() {
        return "exammerge";
    }

    @GetMapping("/comments")
    public String examcomments() {
        return "comments";
    }

    @GetMapping("/examreg")
    public String examreg() {

        return "examreg";
    }

    @GetMapping("/examrec")
    public String examrec() {

        return "examrec";
    }

    @RequestMapping(value = {"deleteType/{code}"}, method = {
            org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGrade(@PathVariable Long code) {
        examInterface.deleteType(code);
    }

    @RequestMapping(value = {"/position"}, method = {
            org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    public PositioningBean positioningBean(@RequestParam ExamRegister exam,
                            @RequestParam Forms forms,
                            @RequestParam Student student) {
       return examInterface.positioningBean(exam,forms,student);
    }

    @PostMapping("/saveExamReg")
    @ResponseStatus(HttpStatus.CREATED)
    public void postReg(ExamRegister examRegister) {
        examInterface.postReg(examRegister);
    }

    @ResponseStatus(HttpStatus.CREATED)
    public void postClearance(ExamType examType) {
        examInterface.postType(examType);
    }
    @PostMapping("/combineExam")
    @ResponseStatus(HttpStatus.CREATED)
    public void combineExam(CombineBean combineBean) throws BadRequestException {
        examInterface.combineExam(combineBean);
    }
    @PostMapping("/mergeExam")
    @ResponseStatus(HttpStatus.CREATED)
    public void mergeExams(ExamMergeBean examMergeBean) throws BadRequestException {
        if (examMergeBean.getExam() != null && examMergeBean.getExam1() != null &&
                examMergeBean.getExam2() == null && examMergeBean.getExam3() == null &&
                examMergeBean.getExam4() == null && examMergeBean.getExam5() == null &&
                examMergeBean.getExam6() == null && examMergeBean.getExam7() == null &&
                examMergeBean.getExam8() == null && examMergeBean.getExam9() == null) {
            mergeInterface.mergeExam(examMergeBean);
        }
       else if (examMergeBean.getExam() != null && examMergeBean.getExam1() != null &&
                examMergeBean.getExam2() != null && examMergeBean.getExam3() == null &&
                examMergeBean.getExam4() == null && examMergeBean.getExam5() == null &&
                examMergeBean.getExam6() == null && examMergeBean.getExam7() == null &&
                examMergeBean.getExam8() == null && examMergeBean.getExam9() == null) {
            mergeInterface.mergeThreeExams(examMergeBean);
        }
        else if (examMergeBean.getExam() != null && examMergeBean.getExam1() != null &&
                examMergeBean.getExam2() != null && examMergeBean.getExam3() != null &&
                examMergeBean.getExam4() == null && examMergeBean.getExam5() == null &&
                examMergeBean.getExam6() == null && examMergeBean.getExam7() == null &&
                examMergeBean.getExam8() == null && examMergeBean.getExam9() == null) {
            mergeInterface.mergeFourExams(examMergeBean);
        }
        else if (examMergeBean.getExam() != null && examMergeBean.getExam1() != null &&
                examMergeBean.getExam2() != null && examMergeBean.getExam3() != null &&
                examMergeBean.getExam4() != null && examMergeBean.getExam5() == null &&
                examMergeBean.getExam6() == null && examMergeBean.getExam7() == null &&
                examMergeBean.getExam8() == null && examMergeBean.getExam9() == null) {
            mergeInterface.mergeFiveExams(examMergeBean);
        }
        else if (examMergeBean.getExam() != null && examMergeBean.getExam1() != null &&
                examMergeBean.getExam2() != null && examMergeBean.getExam3() != null &&
                examMergeBean.getExam4() != null && examMergeBean.getExam5() != null &&
                examMergeBean.getExam6() == null && examMergeBean.getExam7() == null &&
                examMergeBean.getExam8() == null && examMergeBean.getExam9() == null) {
            mergeInterface.mergeSixExams(examMergeBean);
        }
        else if (examMergeBean.getExam() != null && examMergeBean.getExam1() != null &&
                examMergeBean.getExam2() != null && examMergeBean.getExam3() != null &&
                examMergeBean.getExam4() != null && examMergeBean.getExam5() != null &&
                examMergeBean.getExam6() != null && examMergeBean.getExam7() == null &&
                examMergeBean.getExam8() == null && examMergeBean.getExam9() == null) {
            mergeInterface.mergeSevenExams(examMergeBean);
        }
        else if (examMergeBean.getExam() != null && examMergeBean.getExam1() != null &&
                examMergeBean.getExam2() != null && examMergeBean.getExam3() != null &&
                examMergeBean.getExam4() != null && examMergeBean.getExam5() != null &&
                examMergeBean.getExam6() != null && examMergeBean.getExam7() != null &&
                examMergeBean.getExam8() == null && examMergeBean.getExam9() == null) {
            mergeInterface.mergeEightExams(examMergeBean);
        }
        else if (examMergeBean.getExam() != null && examMergeBean.getExam1() != null &&
                examMergeBean.getExam2() != null && examMergeBean.getExam3() != null &&
                examMergeBean.getExam4() != null && examMergeBean.getExam5() != null &&
                examMergeBean.getExam6() != null && examMergeBean.getExam7() != null &&
                examMergeBean.getExam8() != null && examMergeBean.getExam9() == null) {
            mergeInterface.mergeNineExams(examMergeBean);
        }
        else if (examMergeBean.getExam() != null && examMergeBean.getExam1() != null &&
                examMergeBean.getExam2() != null && examMergeBean.getExam3() != null &&
                examMergeBean.getExam4() != null && examMergeBean.getExam5() != null &&
                examMergeBean.getExam6() != null && examMergeBean.getExam7() != null &&
                examMergeBean.getExam8() != null && examMergeBean.getExam9() != null) {
            mergeInterface.mergeTenExams(examMergeBean);
        }
        else{
            throw new BadRequestException("Choose a consequtive Exam pair to merge");
        }
    }

    @GetMapping("getExamRegTable")
    @ResponseBody
    public DataTablesOutput<ExamRegister> getExamRegTable(DataTablesInput input) {
        return examInterface.getExamRegTable(input);
    }


    @GetMapping("getExamRecTable/{code}")
    @ResponseBody
    public DataTablesOutput<ExamRecording> getExamRecTable(DataTablesInput input,@PathVariable Long code) {
        return examInterface.getExamRecTable(input,code);
    }
    @GetMapping("getExamRecClassTable/{code}/{classCode}")
    @ResponseBody
    public DataTablesOutput<ExamRecording> getExamRecClassTable(DataTablesInput input,@PathVariable Long code,@PathVariable Long classCode) {
        return examInterface.getExamRecClassTable(input,code,classCode);
    }
    @GetMapping("getExamRecTeaTable/{code}/{classCode}/{teacher}")
    @ResponseBody
    public DataTablesOutput<ExamRecording> getExamRecTeaTable(DataTablesInput input,
                                                              @PathVariable Long code,
                                                              @PathVariable Long classCode,
                                                              @PathVariable Long teacher) {
        return examInterface.getExamRecTeaTable(input,code,classCode,teacher);
    }
    @GetMapping("getExamRecSubTable/{code}/{classCode}/{teacher}/{subject}")
    @ResponseBody
    public DataTablesOutput<ExamRecording> getExamRecSubTable(DataTablesInput input,
                                                              @PathVariable Long code,
                                                              @PathVariable Long classCode,
                                                              @PathVariable Long teacher,
                                                              @PathVariable Long subject) {
        return examInterface.getExamRecSubTable(input,code,classCode,teacher,subject);
    }
    @GetMapping("getExamRecStudTable/{code}/{classCode}/{teacher}/{subject}/{student}")
    @ResponseBody
    public DataTablesOutput<ExamRecording> getExamRecStudTable(DataTablesInput input,
                                                              @PathVariable Long code,
                                                              @PathVariable Long classCode,
                                                              @PathVariable Long teacher,
                                                              @PathVariable Long subject,
                                                               @PathVariable Long student) {
        return examInterface.getExamRecStudTable(input,code,classCode,teacher,subject,student);
    }
    @PostMapping("/reportForms")
    public void reportForms(@RequestParam ExamRegister exam,
                              @RequestParam Forms forms,
                              @RequestParam Student student,
                              @RequestParam String remarks,
                              @RequestParam String hRemarks,
                              HttpServletResponse response) throws IOException, JRException, BadRequestException {

        JasperPrint jasperPrint = null;

        jasperPrint = repts.generateReportForm(exam,forms,student,remarks,hRemarks);
        byte[] pdf = null;
  Student student1=studentRepo.findByStId(student.getStId());
  ExamRegister e=examRegRepo.findByExamCode(exam.getExamCode());
        String filename = student1.getAdmNo()+"_"+e.getExamName()+"_"+"reportform.pdf";
        pdf = JasperExportManager.exportReportToPdf(jasperPrint);
        response.setContentType("application/x-download");
        response.setContentLength(pdf.length);
        response.addHeader("Content-disposition", "attachment; filename=\"" + filename + "\"");
        OutputStream out = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, out);

    }

    @RequestMapping(value = {"deleteExamReg/{code}"}, method = {
            org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteExamReg(@PathVariable Long code) {
        examInterface.deleteExamReg(code);
    }

    @GetMapping("getTypeTable")
    @ResponseBody
    public DataTablesOutput<ExamType> getTypeTable(DataTablesInput input) {
        return examInterface.getTypeTable(input);
    }

    @GetMapping("editType/{fCode}")
    @ResponseBody
    public ExamType examEdit(@PathVariable Long fCode) {
        return examInterface.typeEdit(fCode);
    }

    @GetMapping("/pageYear")
    @ResponseBody
    Page<Year> pageYear(@RequestParam(required = false) String term, Pageable pageable) {
        return examInterface.pageYear(pageable, term);
    }
    @GetMapping("/pageSubject/{code}/{classCode}")
    @ResponseBody
    Page<TeacherSubjects> pageSubject(@RequestParam(required = false) String term, Pageable pageable,@PathVariable Long code,@PathVariable Long classCode) {
        return examInterface.pageSubject(pageable, term,code,classCode);
    }

    @GetMapping("/pageStudent/{code}")
    @ResponseBody
    Page<Student> pageStudent(@RequestParam(required = false) String term, Pageable pageable, @PathVariable Long code) {
        return examInterface.pageStudent(pageable, term,code);
    }


    @GetMapping("/pageTerm")
    @ResponseBody
    Page<Term> pageTerm(@RequestParam(required = false) String term, Pageable pageable) {
        return examInterface.pageTerm(pageable, term);
    }
    @GetMapping("/pageType")
    @ResponseBody
    Page<ExamType> pageType(@RequestParam(required = false) String term, Pageable pageable) {
        return examInterface.pageType(pageable, term);
    }

    @GetMapping("/pageRegister")
    @ResponseBody
    Page<ExamRegister> pageRegister(@RequestParam(required = false) String term, Pageable pageable) {
        return examInterface.pageRegister(pageable, term);
    }

    @GetMapping("/pageTeachers")
    @ResponseBody
    Page<Teachers> pageTeachers(@RequestParam(required = false) String term, Pageable pageable) {
        return examInterface.pageTeachers(pageable, term);
    }

    @PostMapping("/saveExamRecord")
    @ResponseBody
    public ExamRecording postRecord(ExamRecording examRecording) throws BadRequestException {
        return examInterface.postRecording(examRecording);
    }
    @GetMapping("/missExam")
    @ResponseStatus(HttpStatus.CREATED)
    public void missExam(@RequestParam Student student,
                         @RequestParam Forms forms,
                         @RequestParam TeacherSubjects subjects,
                         @RequestParam ExamRegister exam
                         ) throws BadRequestException {
         examInterface.postMissed(student,forms,subjects,exam);
    }
    @PostMapping("/saveType")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveType(ExamType examType) throws BadRequestException {
        examInterface.postType(examType);
    }
    @GetMapping("/undoMissExam")
    @ResponseStatus(HttpStatus.CREATED)
    public void undoMissExam(@RequestParam Student student,
                         @RequestParam Forms forms,
                         @RequestParam TeacherSubjects subjects,
                         @RequestParam ExamRegister exam
    ) throws BadRequestException {
        examInterface.undoMissExam(student,forms,subjects,exam);
    }
    @RequestMapping(value="checkExcel",method = RequestMethod.GET)
    @ResponseBody
    public ExamRecording checkExcel(@RequestParam String student,@RequestParam String subject,@RequestParam String form,@RequestParam String teacher) throws BadRequestException, FileNotFoundException {
        return examInterface.checkExcel(student,subject,form,teacher);
    }

    @RequestMapping(value="saveExcel",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.CREATED)
    public void saveBudgetExcel(@RequestParam String teacher,
                                @RequestParam String subject,
                                @RequestParam String student,
                                @RequestParam String form,
                                @RequestParam(required = false) String year,
                                @RequestParam(required = false) String term,
                                @RequestParam String exam,
                                @RequestParam(required = false) String date,
                                @RequestParam(required = false) String remarks,
                                @RequestParam(required = false) String marks,
                                @RequestParam(required = false) String total
    ) throws BadRequestException, FileNotFoundException, ParseException {
        Student stud=examInterface.findStudent(student);
        Forms forms=examInterface.findForm(form);
        StudSubjects subjects=examInterface.findSubjects(subject);
        ExamRegister examRegister=examInterface.findExam(exam);
        Teachers teachers=examInterface.findTeachers(teacher);
        Term terms=examInterface.findTerm(term);
        Year years=examInterface.findYear(year);
        if(stud==null)
            throw new BadRequestException("No student with number "+student);
        if (forms==null)
            throw new BadRequestException("No Class named "+form);
        if(examRegister==null)
            throw new BadRequestException("No Exam named "+exam);
        if(teachers==null)
            throw new BadRequestException("No Teacher named "+teacher);
         if (terms==null)
             throw new BadRequestException("No Term "+term);
         if(years==null)
             throw new BadRequestException("No Year named "+year);

        TeacherSubjects teacherSubjects=examInterface.findTeacherSubject(forms,subjects,teachers);
        if(teacherSubjects==null)
            throw new BadRequestException("No subjects found");
        ExamRecording examRecording=new ExamRecording();
        examRecording.setTeacherSubjects(teacherSubjects);
        examRecording.setTeachers(teachers);
        examRecording.setExamRegister(examRegister);
        examRecording.setStudent(stud);
        examRecording.setForms(forms);
        examRecording.setMark(Double.parseDouble(marks));
        examRecording.setOutOff(Double.parseDouble(total));
        examRecording.setRemarks(remarks);
        java.util.Date sDate=new SimpleDateFormat("dd/MM/yyyy").parse(date);
        examRecording.setExamDate(sDate);
        examRecording.setYear(years);
        examRecording.setTerm(terms);
        examInterface.postExcel(examRecording);
    }
    @RequestMapping(value="drawRecords",method = RequestMethod.GET)
    @ResponseBody
    public List<ExamBean> getAll(@RequestParam Forms code,
                                 @RequestParam TeacherSubjects subject,
                                 @RequestParam ExamRegister exam){
        return examInterface.drawRecords(code,subject,exam);
    }
}