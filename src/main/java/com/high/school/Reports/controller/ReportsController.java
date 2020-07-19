package com.high.school.Reports.controller;

import com.high.school.Reports.Repts;
import com.high.school.Reports.service.ReportsInterface;
import com.high.school.examination.model.ExamRecording;
import com.high.school.examination.model.ExamRegister;
import com.high.school.examination.model.PositioningBean;
import com.high.school.examination.repo.ExamRecordingRepo;
import com.high.school.examination.repo.ExamRegRepo;
import com.high.school.examination.repo.PositionRepo;
import com.high.school.examination.service.ExamInterface;
import com.high.school.examination.service.impl.ExamService;
import com.high.school.exceptions.BadRequestException;
import com.high.school.students.model.Forms;
import com.high.school.students.model.Student;
import com.high.school.students.repo.FormsRepo;
import com.high.school.students.repo.StudentRepo;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Controller
@RequestMapping("/protected/reports")
public class ReportsController {

    @Autowired
    Repts repts;

    @Autowired
    ExamRegRepo examRegRepo;

    @Autowired
    ExamRecordingRepo examRecordingRepo;

    @Autowired
    PositionRepo positionRepo;

    @Autowired
    StudentRepo  studentRepo;

    @Autowired
    FormsRepo formsRepo;

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    ReportsInterface examService;

    @Autowired
    ExamInterface examInterface;

    @GetMapping("/process")
    public String getProcess(){
        return "examprocess";
    }
    @GetMapping("/positionExam")
    @ResponseStatus(HttpStatus.CREATED)
    public void positionExam(@RequestParam ExamRegister exam,@RequestParam Forms forms) throws BadRequestException {
        examInterface.positionExams(exam,forms);
    }
    @GetMapping("/lockExam")
    @ResponseStatus(HttpStatus.CREATED)
    public void lockExam(@RequestParam ExamRegister exam) throws BadRequestException {
        examInterface.lockExams(exam);
    }
    @GetMapping("/unLockExam")
    @ResponseStatus(HttpStatus.CREATED)
    public void unLockExam(@RequestParam ExamRegister exam) throws BadRequestException {
        examInterface.unLockExam(exam);
    }
    @GetMapping("emailExam")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void sendMail(@RequestParam ExamRegister exam, Forms forms) throws MessagingException, BadRequestException {
        ExamRegister e=examRegRepo.findByExamCode(exam.getExamCode());

        List<Student> studentList=studentRepo.findByForms_ClassCode(forms.getClassCode());
        if(!(studentList.isEmpty())) {
            for (Student s : studentList) {

                List<ExamRecording> myList = examRecordingRepo.findByExamRegisterAndFormsAndStudent(exam, forms, s);
                if (!(myList.isEmpty())) {
                    PositioningBean positioningBean = positionRepo.findByExamRegisterAndFormsAndStudent(exam, forms, s);
                    if (positioningBean != null) {
                        MimeMessage message = emailSender.createMimeMessage();

                        MimeMessageHelper helper = new MimeMessageHelper(message, true);
                        helper.setFrom("kamaugaby13@gmail.com");
                        helper.setTo(s.getParent().getEmailFather());
                        helper.setSubject("REPORT FORM");
                        helper.setText("Your Child's marks");
                        FileSystemResource file
                                = new FileSystemResource(new File("C:/Users/user/Downloads/" + s.getAdmNo() + "_" + e.getExamName() + "_" + "reportform.pdf"));
                        helper.addAttachment("reportform.pdf", file);
                        emailSender.send(message);
                    }
                }
            }
        }

    }

    @GetMapping("/missed")
    public String missedReport(@RequestParam ExamRegister exam,@RequestParam Forms forms,HttpServletResponse response) throws IOException, JRException {

        JasperPrint jasperPrint = null;

        jasperPrint = repts.missedReport(exam,forms);
        byte[] pdf = null;

        String filename = "missedexam.pdf";
        pdf = JasperExportManager.exportReportToPdf(jasperPrint);
        response.setContentType("application/x-download");
        response.setContentLength(pdf.length);
        response.addHeader("Content-disposition", "attachment; filename=\"" + filename + "\"");
        OutputStream out = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, out);

      return "examprocess";
    }

    @GetMapping("/subjectanalysis")
    public String subjectAnalysisReport(@RequestParam ExamRegister exam,@RequestParam Forms forms,HttpServletResponse response) throws IOException, JRException {

        JasperPrint jasperPrint = null;

        jasperPrint = repts.subjectAnalysisReport(exam,forms);
        byte[] pdf = null;

        String filename = "subjectanalysis.pdf";
        pdf = JasperExportManager.exportReportToPdf(jasperPrint);
        response.setContentType("application/x-download");
        response.setContentLength(pdf.length);
        response.addHeader("Content-disposition", "attachment; filename=\"" + filename + "\"");
        OutputStream out = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, out);

return "examprocess";
    }
    @GetMapping("/classanalysis")
    public String classAnalysisReport(@RequestParam ExamRegister exam,@RequestParam Forms forms,HttpServletResponse response) throws IOException, JRException {

        JasperPrint jasperPrint = null;

        jasperPrint = repts.classAnalysisReport(exam,forms);
        byte[] pdf = null;

        String filename = "classanalysis.pdf";
        pdf = JasperExportManager.exportReportToPdf(jasperPrint);
        response.setContentType("application/x-download");
        response.setContentLength(pdf.length);
        response.addHeader("Content-disposition", "attachment; filename=\"" + filename + "\"");
        OutputStream out = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, out);

return "examprocess";
    }
    @GetMapping("/genderanalysis")
    public String genderAnalysisReport(@RequestParam ExamRegister exam,@RequestParam Forms forms,HttpServletResponse response) throws IOException, JRException {

        JasperPrint jasperPrint = null;

        jasperPrint = repts.genderAnalysisReport(exam,forms);
        byte[] pdf = null;

        String filename = "genderanalysis.pdf";
        pdf = JasperExportManager.exportReportToPdf(jasperPrint);
        response.setContentType("application/x-download");
        response.setContentLength(pdf.length);
        response.addHeader("Content-disposition", "attachment; filename=\"" + filename + "\"");
        OutputStream out = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, out);

return "examprocess";
    }
    @GetMapping("/meritlist")
    public String meritListReport(@RequestParam ExamRegister exam,@RequestParam Forms forms,HttpServletResponse response) throws IOException, JRException, BadRequestException {

        JasperPrint jasperPrint = null;
ExamRegister e=examRegRepo.findByExamCode(exam.getExamCode());
        jasperPrint = repts.meritListReport(exam,forms);
        byte[] pdf = null;

        String filename = e.getExamName()+"meritlist.pdf";
        pdf = JasperExportManager.exportReportToPdf(jasperPrint);
        response.setContentType("application/x-download");
        response.setContentLength(pdf.length);
        response.addHeader("Content-disposition", "attachment; filename=\"" + filename + "\"");
        OutputStream out = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, out);

return "examprocess";
    }
    @GetMapping("/gendermeritlist")
    public String genderMeritListReport(@RequestParam ExamRegister exam,@RequestParam Forms forms,HttpServletResponse response) throws IOException, JRException, BadRequestException {

        JasperPrint jasperPrint = null;

        jasperPrint = repts.genderMeritListReport(exam,forms);
        byte[] pdf = null;

        String filename = "gendermeritlist.pdf";
        pdf = JasperExportManager.exportReportToPdf(jasperPrint);
        response.setContentType("application/x-download");
        response.setContentLength(pdf.length);
        response.addHeader("Content-disposition", "attachment; filename=\"" + filename + "\"");
        OutputStream out = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, out);

return "examprocess";
    }
    @GetMapping("/singleexamlist")
    public String singleExamMeritListReport(@RequestParam ExamRegister exam,@RequestParam Forms forms,HttpServletResponse response) throws IOException, JRException, BadRequestException {
        ExamRegister e=examRegRepo.findByExamCode(exam.getExamCode());

        JasperPrint jasperPrint = null;

        jasperPrint = repts.singleExamMeritListReport(exam,forms);
        byte[] pdf = null;

        String filename = e.getExamName()+"_"+forms.getAbbr()+"_"+"singleexamreport.pdf";
        pdf = JasperExportManager.exportReportToPdf(jasperPrint);
        response.setContentType("application/x-download");
        response.setContentLength(pdf.length);
        response.addHeader("Content-disposition", "attachment; filename=\"" + filename + "\"");
        OutputStream out = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, out);

return "examprocess";
    }
    @GetMapping("/broadstream")
    public String streamExamMeritListReport(@RequestParam ExamRegister exam,HttpServletResponse response) throws IOException, JRException, BadRequestException {

        JasperPrint jasperPrint = null;

        jasperPrint = repts.broadExamMeritListReport(exam);
        byte[] pdf = null;

        String filename = "singelexamreport.pdf";
        pdf = JasperExportManager.exportReportToPdf(jasperPrint);
        response.setContentType("application/x-download");
        response.setContentLength(pdf.length);
        response.addHeader("Content-disposition", "attachment; filename=\"" + filename + "\"");
        OutputStream out = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, out);

        return "examprocess";
    }
}
