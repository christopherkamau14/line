package com.high.school.Reports;

import com.high.school.Institution.model.AcademicOrg;
import com.high.school.Institution.service.SchoolInterface;
import com.high.school.Reports.service.ReportsInterface;
import com.high.school.Reports.utils.*;
import com.high.school.academics.model.Teachers;
import com.high.school.examination.model.ExamRegister;
import com.high.school.examination.model.MIssedExams;
import com.high.school.examination.model.PositioningBean;
import com.high.school.examination.model.ReportFormBean;
import com.high.school.examination.service.ExamInterface;
import com.high.school.exceptions.BadRequestException;
import com.high.school.students.model.Forms;
import com.high.school.students.model.Student;
import com.high.school.students.service.StudentInterface;
import com.high.school.timetabling.model.TeacherTableBean;
import com.high.school.timetabling.model.TimetableBean;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class Repts {

    @Autowired
    SchoolInterface schoolInterface;

    @Autowired
    ReportsInterface examInterface;

    @Autowired
    StudentInterface studentService;

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    DataSource dataSource;

    public JasperPrint studReport(Long stdId, String format) throws IOException, JRException {


        ArrayList<Student> dataList = studentService.findStuds(stdId);
        Student s=studentService.getStudent(stdId);
        JRBeanCollectionDataSource beanColDataSource = new
                JRBeanCollectionDataSource(dataList);
        Map<String, Object> parameters = new HashMap<>();
        AcademicOrg org=schoolInterface.getSchool();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            InputStream in = new ByteArrayInputStream(org.getSchoolLogo());
            BufferedImage image = ImageIO.read(in);
            parameters.put("logo",image);
             parameters.put("school",org.getSchoolName());
            parameters.put("graduate","Not Set");
        if(s.getTerm()!=null){
            parameters.put("term",String.valueOf(s.getTerm().getTermNumber()));
        }else{
            parameters.put("term","Not Set");

        }
            if(s.getAddress()!=null){
                parameters.put("address",s.getAddress());
            }else{
                parameters.put("address","Not Set");

            }
        if(s.getBirthCertNo()!=null){
            parameters.put("birthCertNo",s.getBirthCertNo());
        }else{
            parameters.put("birthCertNo","Not Set");

        }
        if(s.getKcpeMarks()!=null){
            parameters.put("kcpeMarks",s.getKcpeMarks());
        }else{
            parameters.put("kcpeMarks","Not Set");

        }
        if(s.getKcpeGrade()!=null){
            parameters.put("kcpeGrade",s.getKcpeGrade());
        }else{
            parameters.put("kcpeGrade","Not Set");

        }
        if(s.getPrimarySchool()!=null){
            parameters.put("primarySchool",s.getPrimarySchool());
        }else{
            parameters.put("primarySchool","Not Set");
        }
        if(s.getGender()!=null){
            parameters.put("gender",s.getGender());
        }else{
            parameters.put("gender","Not Set");
        }
        if(s.getStudEmail()!=null){
            parameters.put("studEmail",s.getStudEmail());
        }else{
            parameters.put("studEmail","Not Set");
        }
        if(s.getStudentContact()!=null){
            parameters.put("studContact",s.getStudentContact());
        }else{
            parameters.put("studContact","Not Set");
        }
        if(s.getIndexNo()!=null){
            parameters.put("indexNo",s.getIndexNo());
        }else{
            parameters.put("indexNo","Not Set");
        }
            if(s.getForms()!=null) {
                parameters.put("class", s.getForms().getClassNo());
                if(s.getForms().getBlock()!=null) {
                    parameters.put("block",s.getForms().getBlock().getBlockName());
                }else{
                    parameters.put("block","Not Set");
                }
            }else{
                parameters.put("class","Not Set");
            }

            if(s.getReligion()!=null) {
                parameters.put("religion", s.getReligion().getRelName());
            }else{
                parameters.put("religion","Not Set");
            }
            if(s.getParent()!=null) {
                parameters.put("phone",s.getParent().getTelFather());
                parameters.put("parent", s.getParent().getNameFather());
            }else{
                parameters.put("parent","Not set");
                parameters.put("phone","Not set");
            }
            parameters.put("phone",s.getParent().getTelFather());
            if(s.getCountry()!=null){
                parameters.put("country",s.getCountry().getCouName());
            }else{
                parameters.put("country","Not Set");
            }
        if(s.getCounty()!=null){
            parameters.put("county",s.getCounty().getCountyName());
        }else{
            parameters.put("county","Not Set");
        }
        if(s.getDorm()!=null){
            parameters.put("dorm",s.getDorm().getDormName());
        }else{
            parameters.put("dorm","Not Set");
        }
        if(s.getHealth()!=null){
            parameters.put("health",s.getHealth().getCondName());
        }else{
            parameters.put("health","Not Set");
        }
        if(s.getAdmDate()!=null){
            parameters.put("admDate", formatter.format(s.getAdmDate()));
        }else{
            parameters.put("admDate","Not Set");
        }
        if(s.getCompletionDate()!=null){
            parameters.put("completionDate",formatter.format(s.getCompletionDate()));
        }else{
            parameters.put("completionDate","Not Set");
        }
        if(s.getBirthDate()!=null){
            parameters.put("birthDate",formatter.format(s.getBirthDate()));
        }else{
            parameters.put("birthDate","Not Set");
        }
        if(s.getBirthDate()!=null){
            parameters.put("birthDate",formatter.format(s.getBirthDate()));
        }else{
            parameters.put("birthDate","Not Set");
        }
        if(s.getStudStatus()!=null){
            parameters.put("status",s.getStudStatus().getStatusName());
        }else{
            parameters.put("status","Not Set");
        }
        if(s.getFeeCategory()!=null){
            parameters.put("fee",s.getFeeCategory().getCatName());
        }else{
            parameters.put("fee","Not Set");
        }
        String path = resourceLoader.getResource("classpath:reports/student.jrxml").getURI().getPath();

        JasperReport jasperReport = JasperCompileManager.compileReport(path);

        return JasperFillManager.fillReport(jasperReport, parameters,beanColDataSource);
    }
    public JasperPrint missedReport(ExamRegister examRegister, Forms forms) throws IOException, JRException {
        List<MIssedExams> dataList = examInterface.findMissed(examRegister,forms);
        List<MissedExamUtils> myList=new ArrayList<>();
        for(MIssedExams m:dataList) {
            MissedExamUtils missedExamUtils = new MissedExamUtils();
            missedExamUtils.setClassName(m.getForms().getAbbr());
            missedExamUtils.setAdmNo(m.getStudent().getAdmNo());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            String date=dateFormat.format(m.getExamRegister().getEffectiveDate());
            missedExamUtils.setDate(date);
            missedExamUtils.setExam(m.getExamRegister().getExamName());
            missedExamUtils.setStudent(m.getStudent().getName());
           missedExamUtils.setTerm(String.valueOf(m.getExamRegister().getTerm().getTermNumber()));
           missedExamUtils.setYear(String.valueOf(m.getExamRegister().getYear().getYearNumber()));
            missedExamUtils.setSubject(m.getTeacherSubjects().getSubjects().getName());
            missedExamUtils.setTeacher(m.getTeacherSubjects().getTeachers().getName());
            myList.add(missedExamUtils);
        }
        JRBeanCollectionDataSource beanColDataSource = new
                JRBeanCollectionDataSource(myList);
        Map<String, Object> parameters = new HashMap<>();
        AcademicOrg org=schoolInterface.getSchool();
        InputStream in = new ByteArrayInputStream(org.getSchoolLogo());
        BufferedImage image = ImageIO.read(in);
        parameters.put("logo",image);
        parameters.put("school",org.getSchoolName());

        String path = resourceLoader.getResource("classpath:reports/missedexams.jrxml").getURI().getPath();

        JasperReport jasperReport = JasperCompileManager.compileReport(path);

        return JasperFillManager.fillReport(jasperReport, parameters,beanColDataSource);
    }

    public JasperPrint subjectAnalysisReport(ExamRegister examRegister, Forms forms) throws IOException, JRException {
        List<SubjectAnalysisBean> dataList = examInterface.subjectAnalys(examRegister,forms);

        JRBeanCollectionDataSource beanColDataSource = new
                JRBeanCollectionDataSource(dataList);
        Map<String, Object> parameters = new HashMap<>();
        AcademicOrg org=schoolInterface.getSchool();
        InputStream in = new ByteArrayInputStream(org.getSchoolLogo());
        BufferedImage image = ImageIO.read(in);
        parameters.put("logo",image);
        parameters.put("school",org.getSchoolName());

        String path = resourceLoader.getResource("classpath:reports/subjectanalysis.jrxml").getURI().getPath();

        JasperReport jasperReport = JasperCompileManager.compileReport(path);

        return JasperFillManager.fillReport(jasperReport, parameters,beanColDataSource);
    }

    public JasperPrint classAnalysisReport(ExamRegister exam, Forms forms) throws JRException, IOException {
        List<ClassAnalysisBean> dataList = examInterface.classAnalys(exam,forms);

        JRBeanCollectionDataSource beanColDataSource = new
                JRBeanCollectionDataSource(dataList);
        Map<String, Object> parameters = new HashMap<>();
        AcademicOrg org=schoolInterface.getSchool();
        InputStream in = new ByteArrayInputStream(org.getSchoolLogo());
        BufferedImage image = ImageIO.read(in);
        parameters.put("logo",image);
        parameters.put("school",org.getSchoolName());

        String path = resourceLoader.getResource("classpath:reports/classanalysis.jrxml").getURI().getPath();

        JasperReport jasperReport = JasperCompileManager.compileReport(path);

        return JasperFillManager.fillReport(jasperReport, parameters,beanColDataSource);
    }

    public JasperPrint genderAnalysisReport(ExamRegister exam, Forms forms) throws JRException, IOException {
        List<GenderAnalysisBean> dataList = examInterface.genderAnalys(exam,forms);

        JRBeanCollectionDataSource beanColDataSource = new
                JRBeanCollectionDataSource(dataList);
        Map<String, Object> parameters = new HashMap<>();
        AcademicOrg org=schoolInterface.getSchool();
        InputStream in = new ByteArrayInputStream(org.getSchoolLogo());
        BufferedImage image = ImageIO.read(in);
        parameters.put("logo",image);
        parameters.put("school",org.getSchoolName());

        String path = resourceLoader.getResource("classpath:reports/genderanalysis.jrxml").getURI().getPath();

        JasperReport jasperReport = JasperCompileManager.compileReport(path);

        return JasperFillManager.fillReport(jasperReport, parameters,beanColDataSource);
    }

    public JasperPrint meritListReport(ExamRegister exam, Forms forms) throws IOException, JRException, BadRequestException {
        List<MeritListBean> dataList = examInterface.meritList(exam,forms);

        JRBeanCollectionDataSource beanColDataSource = new
                JRBeanCollectionDataSource(dataList);
        Map<String, Object> parameters = new HashMap<>();
        AcademicOrg org=schoolInterface.getSchool();
        InputStream in = new ByteArrayInputStream(org.getSchoolLogo());
        BufferedImage image = ImageIO.read(in);
        parameters.put("logo",image);
        parameters.put("school",org.getSchoolName());

        String path = resourceLoader.getResource("classpath:reports/meritlist.jrxml").getURI().getPath();

        JasperReport jasperReport = JasperCompileManager.compileReport(path);

        return JasperFillManager.fillReport(jasperReport, parameters,beanColDataSource);
    }

    public JasperPrint genderMeritListReport(ExamRegister exam, Forms forms) throws IOException, JRException {
        List<GenderMeritBean> dataList = examInterface.genderMeritList(exam,forms);

        JRBeanCollectionDataSource beanColDataSource = new
                JRBeanCollectionDataSource(dataList);
        Map<String, Object> parameters = new HashMap<>();
        AcademicOrg org=schoolInterface.getSchool();
        InputStream in = new ByteArrayInputStream(org.getSchoolLogo());
        BufferedImage image = ImageIO.read(in);
        parameters.put("logo",image);
        parameters.put("school",org.getSchoolName());

        String path = resourceLoader.getResource("classpath:reports/gendermeritlist.jrxml").getURI().getPath();

        JasperReport jasperReport = JasperCompileManager.compileReport(path);

        return JasperFillManager.fillReport(jasperReport, parameters,beanColDataSource);
    }

    public JasperPrint singleExamMeritListReport(ExamRegister exam, Forms forms) throws BadRequestException, JRException, IOException {
        List<MeritListBean> dataList = examInterface.singleExamList(exam,forms);

        JRBeanCollectionDataSource beanColDataSource = new
                JRBeanCollectionDataSource(dataList);
        Map<String, Object> parameters = new HashMap<>();
        AcademicOrg org=schoolInterface.getSchool();
        InputStream in = new ByteArrayInputStream(org.getSchoolLogo());
        BufferedImage image = ImageIO.read(in);
        parameters.put("logo",image);
        parameters.put("school",org.getSchoolName());

        String path = resourceLoader.getResource("classpath:reports/meritlist.jrxml").getURI().getPath();

        JasperReport jasperReport = JasperCompileManager.compileReport(path);

        return JasperFillManager.fillReport(jasperReport, parameters,beanColDataSource);
    }

    public JasperPrint broadExamMeritListReport(ExamRegister exam) throws JRException, IOException {
        List<MeritListBean> dataList = examInterface.singleExamBroadList(exam);

        JRBeanCollectionDataSource beanColDataSource = new
                JRBeanCollectionDataSource(dataList);
        Map<String, Object> parameters = new HashMap<>();
        AcademicOrg org=schoolInterface.getSchool();
        InputStream in = new ByteArrayInputStream(org.getSchoolLogo());
        BufferedImage image = ImageIO.read(in);
        parameters.put("logo",image);
        parameters.put("school",org.getSchoolName());

        String path = resourceLoader.getResource("classpath:reports/meritlist.jrxml").getURI().getPath();

        JasperReport jasperReport = JasperCompileManager.compileReport(path);

        return JasperFillManager.fillReport(jasperReport, parameters,beanColDataSource);
    }

    public JasperPrint generateReportForm(ExamRegister exam, Forms forms, Student student, String remarks, String hRemarks) throws IOException, JRException, BadRequestException {
        List<ReportFormBean> dataList = examInterface.reportForm(exam,forms,student,remarks,hRemarks);

        JRBeanCollectionDataSource beanColDataSource = new
                JRBeanCollectionDataSource(dataList);
        Map<String, Object> parameters = new HashMap<>();
        AcademicOrg org=schoolInterface.getSchool();
        InputStream in = new ByteArrayInputStream(org.getSchoolLogo());
        BufferedImage image = ImageIO.read(in);
        parameters.put("logo",image);
        parameters.put("school",org.getSchoolName());

        String path = resourceLoader.getResource("classpath:reports/reportForm.jrxml").getURI().getPath();

        JasperReport jasperReport = JasperCompileManager.compileReport(path);

        return JasperFillManager.fillReport(jasperReport, parameters,beanColDataSource);
    }

    public JasperPrint classtt(Forms forms) throws IOException, JRException {
        List<TimetableBean> dataList = examInterface.classtt(forms);

        JRBeanCollectionDataSource beanColDataSource = new
                JRBeanCollectionDataSource(dataList);
        Map<String, Object> parameters = new HashMap<>();
        AcademicOrg org=schoolInterface.getSchool();
        InputStream in = new ByteArrayInputStream(org.getSchoolLogo());
        BufferedImage image = ImageIO.read(in);
        parameters.put("logo",image);
        parameters.put("school",org.getSchoolName());
        String path = resourceLoader.getResource("classpath:reports/classtimetable.jrxml").getURI().getPath();

        JasperReport jasperReport = JasperCompileManager.compileReport(path);

        return JasperFillManager.fillReport(jasperReport,parameters,beanColDataSource);

    }

    public JasperPrint teachertt(Teachers teachers) throws JRException, IOException {
        List<TeacherTableBean> dataList = examInterface.teacherstt(teachers);

        JRBeanCollectionDataSource beanColDataSource = new
                JRBeanCollectionDataSource(dataList);
        Map<String, Object> parameters = new HashMap<>();
        AcademicOrg org=schoolInterface.getSchool();
        InputStream in = new ByteArrayInputStream(org.getSchoolLogo());
        BufferedImage image = ImageIO.read(in);
        parameters.put("logo",image);
        parameters.put("school",org.getSchoolName());
        String path = resourceLoader.getResource("classpath:reports/teachertimetable.jrxml").getURI().getPath();

        JasperReport jasperReport = JasperCompileManager.compileReport(path);

        return JasperFillManager.fillReport(jasperReport,parameters,beanColDataSource);
    }
}