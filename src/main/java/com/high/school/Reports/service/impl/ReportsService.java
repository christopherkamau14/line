package com.high.school.Reports.service.impl;

import com.high.school.Reports.service.ReportsInterface;
import com.high.school.Reports.utils.*;
import com.high.school.academics.model.Teachers;
import com.high.school.academics.repo.GradingSysRepo;
import com.high.school.academics.repo.SubjectsRepo;
import com.high.school.academics.repo.TeacherRepo;
import com.high.school.academics.repo.TeacherSubjectsRepo;
import com.high.school.examination.model.*;
import com.high.school.examination.repo.ExamRecordingRepo;
import com.high.school.examination.repo.ExamRegRepo;
import com.high.school.examination.repo.MissedExamRepo;
import com.high.school.examination.repo.PositionRepo;
import com.high.school.exceptions.BadRequestException;
import com.high.school.students.model.Forms;
import com.high.school.students.model.Student;
import com.high.school.students.repo.FormsRepo;
import com.high.school.students.repo.StudentRepo;
import com.high.school.students.repo.TermRepo;
import com.high.school.students.repo.YearRepo;
import com.high.school.timetabling.model.Allocations;
import com.high.school.timetabling.model.Lessons;
import com.high.school.timetabling.model.TeacherTableBean;
import com.high.school.timetabling.model.TimetableBean;
import com.high.school.timetabling.repo.AllocationRepo;
import com.high.school.timetabling.repo.LessonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

@Service
public class ReportsService implements ReportsInterface {

    @Autowired
    ExamRecordingRepo examRecordingRepo;

    @Autowired
    AllocationRepo allocationRepo;

    @Autowired
    LessonRepo lessonRepo;

    @Autowired
    MissedExamRepo missedExamRepo;

    @Autowired
    YearRepo yearRepo;

    @Autowired
    TermRepo termRepo;

    @Autowired
    ExamRegRepo examRegRepo;

    @Autowired
    TeacherRepo teacherRepo;


    @Autowired
    GradingSysRepo gradingSysRepo;

    @Autowired
    SubjectsRepo subjectsRepo;

    @Autowired
    FormsRepo formsRepo;

    @Autowired
    TeacherSubjectsRepo teacherSubjectsRepo;

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    PositionRepo positionRepo;

    @Override
    public List<MIssedExams> findMissed(ExamRegister examRegister,Forms forms) {

        return missedExamRepo.findByExamRegisterAndFormsOrderBySubjectName(examRegister,forms);
    }

    @Override
    public List<SubjectAnalysisBean> subjectAnalys(ExamRegister exam, Forms forms) {
        List<ExamRecording> examRecordings = examRecordingRepo.findByExamRegisterAndForms(exam,forms);

        List<SubjectAnalysisBean> subjectAnalysisBeans = new ArrayList<>();
        for (ExamRecording e : examRecordings) {
            SubjectAnalysisBean subjectAnalysisBean = new SubjectAnalysisBean();
            Long t = examRecordingRepo.countStudentDistinctByExamRegisterAndTeacherSubjects_SubjectsAndForms(exam,e.getTeacherSubjects().getSubjects(),e.getForms());
            Long a = examRecordingRepo.countStudentDistinctByExamRegisterAndTeacherSubjects_SubjectsAndGradingSystem_GradeAndForms(exam,e.getTeacherSubjects().getSubjects(), "A",e.getForms());
            Long b = examRecordingRepo.countStudentDistinctByExamRegisterAndTeacherSubjects_SubjectsAndGradingSystem_GradeAndForms(exam,e.getTeacherSubjects().getSubjects(), "A-",e.getForms());
            Long c = examRecordingRepo.countStudentDistinctByExamRegisterAndTeacherSubjects_SubjectsAndGradingSystem_GradeAndForms(exam,e.getTeacherSubjects().getSubjects(), "B+",e.getForms());
            Long d = examRecordingRepo.countStudentDistinctByExamRegisterAndTeacherSubjects_SubjectsAndGradingSystem_GradeAndForms(exam,e.getTeacherSubjects().getSubjects(), "B",e.getForms());
            Long u = examRecordingRepo.countStudentDistinctByExamRegisterAndTeacherSubjects_SubjectsAndGradingSystem_GradeAndForms(exam,e.getTeacherSubjects().getSubjects(), "B-",e.getForms());
            Long f = examRecordingRepo.countStudentDistinctByExamRegisterAndTeacherSubjects_SubjectsAndGradingSystem_GradeAndForms(exam,e.getTeacherSubjects().getSubjects(), "C+",e.getForms());
            Long g = examRecordingRepo.countStudentDistinctByExamRegisterAndTeacherSubjects_SubjectsAndGradingSystem_GradeAndForms(exam,e.getTeacherSubjects().getSubjects(), "C",e.getForms());
            Long h = examRecordingRepo.countStudentDistinctByExamRegisterAndTeacherSubjects_SubjectsAndGradingSystem_GradeAndForms(exam,e.getTeacherSubjects().getSubjects(), "C-",e.getForms());
            Long i = examRecordingRepo.countStudentDistinctByExamRegisterAndTeacherSubjects_SubjectsAndGradingSystem_GradeAndForms(exam,e.getTeacherSubjects().getSubjects(), "D+",e.getForms());
            Long j = examRecordingRepo.countStudentDistinctByExamRegisterAndTeacherSubjects_SubjectsAndGradingSystem_GradeAndForms(exam,e.getTeacherSubjects().getSubjects(), "D",e.getForms());
            Long k = examRecordingRepo.countStudentDistinctByExamRegisterAndTeacherSubjects_SubjectsAndGradingSystem_GradeAndForms(exam,e.getTeacherSubjects().getSubjects(), "D-",e.getForms());
            Long l = examRecordingRepo.countStudentDistinctByExamRegisterAndTeacherSubjects_SubjectsAndGradingSystem_GradeAndForms(exam,e.getTeacherSubjects().getSubjects(), "E",e.getForms());

            subjectAnalysisBean.setExam(e.getExamRegister().getExamName());
            subjectAnalysisBean.setSubject(e.getTeacherSubjects().getSubjects().getName());
            subjectAnalysisBean.setGradeA(a);
            subjectAnalysisBean.setGradeAM(b);
            subjectAnalysisBean.setGradeBP(c);
            subjectAnalysisBean.setGradeB(d);
            subjectAnalysisBean.setGradeBM(u);
            subjectAnalysisBean.setGradeCP(f);
            subjectAnalysisBean.setGradeC(g);
            subjectAnalysisBean.setGradeCM(h);
            subjectAnalysisBean.setGradeDP(i);
            subjectAnalysisBean.setGradeD(j);
            subjectAnalysisBean.setGradeDM(k);
            subjectAnalysisBean.setGradeE(l);
            subjectAnalysisBean.setTotal(t);
            subjectAnalysisBeans.add(subjectAnalysisBean);

        }
        List<SubjectAnalysisBean> unique = subjectAnalysisBeans.stream()
                .collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparing(SubjectAnalysisBean::getSubject))),
                        ArrayList::new));
        return unique;
    }

    @Override
    public List<ClassAnalysisBean> classAnalys(ExamRegister exam,Forms forms) {

        List<ExamRecording> examRecordings = examRecordingRepo.findByExamRegisterAndForms(exam,forms);
        List<ClassAnalysisBean> classAnalysisBeans = new ArrayList<>();

        for (ExamRecording e : examRecordings) {
            ClassAnalysisBean subjectAnalysisBean = new ClassAnalysisBean();
            Long t = examRecordingRepo.countStudentDistinctByExamRegisterAndFormsAndTeacherSubjects_Subjects(exam,e.getForms(), e.getTeacherSubjects().getSubjects());
            Long a = examRecordingRepo.countStudentDistinctByExamRegisterAndFormsAndTeacherSubjects_SubjectsAndGradingSystem_Grade(exam,e.getForms(), e.getTeacherSubjects().getSubjects(), "A");
            Long b = examRecordingRepo.countStudentDistinctByExamRegisterAndFormsAndTeacherSubjects_SubjectsAndGradingSystem_Grade(exam,e.getForms(), e.getTeacherSubjects().getSubjects(), "A-");
            Long c = examRecordingRepo.countStudentDistinctByExamRegisterAndFormsAndTeacherSubjects_SubjectsAndGradingSystem_Grade(exam,e.getForms(), e.getTeacherSubjects().getSubjects(), "B+");
            Long d = examRecordingRepo.countStudentDistinctByExamRegisterAndFormsAndTeacherSubjects_SubjectsAndGradingSystem_Grade(exam,e.getForms(), e.getTeacherSubjects().getSubjects(), "B");
            Long u = examRecordingRepo.countStudentDistinctByExamRegisterAndFormsAndTeacherSubjects_SubjectsAndGradingSystem_Grade(exam,e.getForms(), e.getTeacherSubjects().getSubjects(), "B-");
            Long f = examRecordingRepo.countStudentDistinctByExamRegisterAndFormsAndTeacherSubjects_SubjectsAndGradingSystem_Grade(exam,e.getForms(), e.getTeacherSubjects().getSubjects(), "C+");
            Long g = examRecordingRepo.countStudentDistinctByExamRegisterAndFormsAndTeacherSubjects_SubjectsAndGradingSystem_Grade(exam,e.getForms(), e.getTeacherSubjects().getSubjects(), "C");
            Long h = examRecordingRepo.countStudentDistinctByExamRegisterAndFormsAndTeacherSubjects_SubjectsAndGradingSystem_Grade(exam,e.getForms(), e.getTeacherSubjects().getSubjects(), "C-");
            Long i = examRecordingRepo.countStudentDistinctByExamRegisterAndFormsAndTeacherSubjects_SubjectsAndGradingSystem_Grade(exam,e.getForms(), e.getTeacherSubjects().getSubjects(), "D+");
            Long j = examRecordingRepo.countStudentDistinctByExamRegisterAndFormsAndTeacherSubjects_SubjectsAndGradingSystem_Grade(exam,e.getForms(), e.getTeacherSubjects().getSubjects(), "D");
            Long k = examRecordingRepo.countStudentDistinctByExamRegisterAndFormsAndTeacherSubjects_SubjectsAndGradingSystem_Grade(exam,e.getForms(), e.getTeacherSubjects().getSubjects(), "D-");
            Long l = examRecordingRepo.countStudentDistinctByExamRegisterAndFormsAndTeacherSubjects_SubjectsAndGradingSystem_Grade(exam,e.getForms(), e.getTeacherSubjects().getSubjects(), "E");

            subjectAnalysisBean.setExam(e.getExamRegister().getExamName());
            subjectAnalysisBean.setClassName(e.getForms().getAbbr());
            subjectAnalysisBean.setSubject(e.getTeacherSubjects().getSubjects().getName());
            subjectAnalysisBean.setGradeA(a);
            subjectAnalysisBean.setGradeAM(b);
            subjectAnalysisBean.setGradeBP(c);
            subjectAnalysisBean.setGradeB(d);
            subjectAnalysisBean.setGradeBM(u);
            subjectAnalysisBean.setGradeCP(f);
            subjectAnalysisBean.setGradeC(g);
            subjectAnalysisBean.setGradeCM(h);
            subjectAnalysisBean.setGradeDP(i);
            subjectAnalysisBean.setGradeD(j);
            subjectAnalysisBean.setGradeDM(k);
            subjectAnalysisBean.setGradeE(l);
            subjectAnalysisBean.setTotal(t);
            subjectAnalysisBean.setClassSubject(e.getTeacherSubjects().getSubjects().getName() + e.getForms().getAbbr());
            classAnalysisBeans.add(subjectAnalysisBean);

        }
        List<ClassAnalysisBean> unique = classAnalysisBeans.stream()
                .collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparing(ClassAnalysisBean::getClassSubject))),
                        ArrayList::new));
        return unique;
    }

    @Override
    public List<GenderAnalysisBean> genderAnalys(ExamRegister exam,Forms forms) {
        List<ExamRecording> examRecordings = examRecordingRepo.findByExamRegisterAndForms(exam,forms);
        List<GenderAnalysisBean> genderAnalysisBeans = new ArrayList<>();
        for (ExamRecording e : examRecordings) {
            GenderAnalysisBean subjectAnalysisBean = new GenderAnalysisBean();
            Long t = examRecordingRepo.countByExamRegisterAndStudent_GenderAndTeacherSubjects_SubjectsAndForms(exam,e.getStudent().getGender(), e.getTeacherSubjects().getSubjects(), e.getForms());
            Long a = examRecordingRepo.countByExamRegisterAndStudent_GenderAndTeacherSubjects_SubjectsAndFormsAndGradingSystem_Grade(exam,e.getStudent().getGender(), e.getTeacherSubjects().getSubjects(), e.getForms(), "A");
            Long b = examRecordingRepo.countByExamRegisterAndStudent_GenderAndTeacherSubjects_SubjectsAndFormsAndGradingSystem_Grade(exam,e.getStudent().getGender(), e.getTeacherSubjects().getSubjects(), e.getForms(), "A-");
            Long c = examRecordingRepo.countByExamRegisterAndStudent_GenderAndTeacherSubjects_SubjectsAndFormsAndGradingSystem_Grade(exam,e.getStudent().getGender(), e.getTeacherSubjects().getSubjects(), e.getForms(), "B+");
            Long d = examRecordingRepo.countByExamRegisterAndStudent_GenderAndTeacherSubjects_SubjectsAndFormsAndGradingSystem_Grade(exam,e.getStudent().getGender(), e.getTeacherSubjects().getSubjects(), e.getForms(), "B");
            Long u = examRecordingRepo.countByExamRegisterAndStudent_GenderAndTeacherSubjects_SubjectsAndFormsAndGradingSystem_Grade(exam,e.getStudent().getGender(), e.getTeacherSubjects().getSubjects(), e.getForms(), "B-");
            Long f = examRecordingRepo.countByExamRegisterAndStudent_GenderAndTeacherSubjects_SubjectsAndFormsAndGradingSystem_Grade(exam,e.getStudent().getGender(), e.getTeacherSubjects().getSubjects(), e.getForms(), "C+");
            Long g = examRecordingRepo.countByExamRegisterAndStudent_GenderAndTeacherSubjects_SubjectsAndFormsAndGradingSystem_Grade(exam,e.getStudent().getGender(), e.getTeacherSubjects().getSubjects(), e.getForms(), "C");
            Long h = examRecordingRepo.countByExamRegisterAndStudent_GenderAndTeacherSubjects_SubjectsAndFormsAndGradingSystem_Grade(exam,e.getStudent().getGender(), e.getTeacherSubjects().getSubjects(), e.getForms(), "C-");
            Long i = examRecordingRepo.countByExamRegisterAndStudent_GenderAndTeacherSubjects_SubjectsAndFormsAndGradingSystem_Grade(exam,e.getStudent().getGender(), e.getTeacherSubjects().getSubjects(), e.getForms(), "D+");
            Long j = examRecordingRepo.countByExamRegisterAndStudent_GenderAndTeacherSubjects_SubjectsAndFormsAndGradingSystem_Grade(exam,e.getStudent().getGender(), e.getTeacherSubjects().getSubjects(), e.getForms(), "D");
            Long k = examRecordingRepo.countByExamRegisterAndStudent_GenderAndTeacherSubjects_SubjectsAndFormsAndGradingSystem_Grade(exam,e.getStudent().getGender(), e.getTeacherSubjects().getSubjects(), e.getForms(), "D-");
            Long l = examRecordingRepo.countByExamRegisterAndStudent_GenderAndTeacherSubjects_SubjectsAndFormsAndGradingSystem_Grade(exam,e.getStudent().getGender(), e.getTeacherSubjects().getSubjects(), e.getForms(), "E");

            subjectAnalysisBean.setExam(e.getExamRegister().getExamName());
            subjectAnalysisBean.setClassName(e.getForms().getAbbr());
            subjectAnalysisBean.setSubject(e.getTeacherSubjects().getSubjects().getName());
            subjectAnalysisBean.setGradeA(a);
            subjectAnalysisBean.setGradeAM(b);
            subjectAnalysisBean.setGradeBP(c);
            subjectAnalysisBean.setGradeB(d);
            subjectAnalysisBean.setGradeBM(u);
            subjectAnalysisBean.setGradeCP(f);
            subjectAnalysisBean.setGradeC(g);
            subjectAnalysisBean.setGradeCM(h);
            subjectAnalysisBean.setGradeDP(i);
            subjectAnalysisBean.setGradeD(j);
            subjectAnalysisBean.setGradeDM(k);
            subjectAnalysisBean.setGradeE(l);
            subjectAnalysisBean.setTotal(t);
            subjectAnalysisBean.setGender(e.getStudent().getGender());
            subjectAnalysisBean.setClassSubjectGender(e.getStudent().getGender() + e.getForms().getAbbr() + e.getTeacherSubjects().getSubjects());
            genderAnalysisBeans.add(subjectAnalysisBean);

        }
        return genderAnalysisBeans.stream()
                .collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparing(GenderAnalysisBean::getClassSubjectGender))),
                        ArrayList::new));
    }

    @Override
    public List<MeritListBean> meritList(ExamRegister exam,Forms forms) throws BadRequestException {
        List<Student> examRecordings = studentRepo.findByForms_ClassCode(forms.getClassCode());
        List<MeritListBean> meritListBeans = new ArrayList<>();
        for (Student e : examRecordings) {
            List<ExamRecording> list = examRecordingRepo.findByExamRegisterAndStudent(exam, e);
            if (!(list.isEmpty())) {
                MeritListBean m = new MeritListBean();
                m.setName(e.getName());
                m.setAdmNo(e.getAdmNo());
                m.setClassName(e.getForms().getAbbr());
                for (ExamRecording f : list) {
                    Long subjects = f.getTeacherSubjects().getSubjects().getOrder();
                    if (subjects==1) {
                        m.setPmath(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }
                    if (subjects==2) {
                        m.setPeng(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }

                    if (subjects==3) {
                        m.setPkisw(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }
                    if (subjects==4) {
                        m.setPchem(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }
                    if (subjects==5) {
                        m.setPbio(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }
                    if (subjects==6) {
                        m.setPphys(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }
                    if (subjects==7) {
                        m.setPhist(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }
                    if (subjects==8) {
                        m.setPgeo(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }
                    if (subjects==9) {
                        m.setPcre(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }
                    if (subjects==10) {
                        m.setPagr(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }
                    if (subjects==11) {
                        m.setPcomp(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }
                    if (subjects==12) {
                        m.setPbs(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }
                    if (subjects==13) {
                        m.setPpe(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }
                    m.setExam(f.getExamRegister().getExamName());


                }
                BigDecimal math = BigDecimal.ZERO;
                BigDecimal eng = BigDecimal.ZERO;
                BigDecimal kisw = BigDecimal.ZERO;
                BigDecimal bio = BigDecimal.ZERO;
                BigDecimal chem = BigDecimal.ZERO;
                BigDecimal phys = BigDecimal.ZERO;
                BigDecimal hist = BigDecimal.ZERO;
                BigDecimal geo = BigDecimal.ZERO;
                BigDecimal cre = BigDecimal.ZERO;
                BigDecimal agr = BigDecimal.ZERO;
                BigDecimal bs = BigDecimal.ZERO;
                BigDecimal comp = BigDecimal.ZERO;
                BigDecimal pe = BigDecimal.ZERO;
                m.setCounter(BigDecimal.ZERO);
                BigDecimal counter = m.getCounter();

                if (m.getPmath() != null) {
                    math = m.getPmath();
                    counter = m.getCounter().add(BigDecimal.ONE);
                    m.setCounter(counter);
                }
                if (m.getPeng() != null) {
                    eng = m.getPeng();
                    counter = m.getCounter().add(BigDecimal.ONE);
                    m.setCounter(counter);
                }
                if (m.getPkisw() != null) {
                    kisw = m.getPkisw();
                    counter = m.getCounter().add(BigDecimal.ONE);
                    m.setCounter(counter);
                }
                if (m.getPbio() != null) {
                    bio = m.getPbio();
                    counter = m.getCounter().add(BigDecimal.ONE);
                    m.setCounter(counter);
                }
                if (m.getPchem() != null) {
                    chem = m.getPchem();
                    counter = m.getCounter().add(BigDecimal.ONE);
                    m.setCounter(counter);
                }
                if (m.getPphys() != null) {
                    phys = m.getPphys();
                    counter = m.getCounter().add(BigDecimal.ONE);
                    m.setCounter(counter);
                }
                if (m.getPhist() != null) {
                    hist = m.getPhist();
                    counter = m.getCounter().add(BigDecimal.ONE);
                    m.setCounter(counter);
                }
                if (m.getPgeo() != null) {
                    geo = m.getPgeo();
                    counter = m.getCounter().add(BigDecimal.ONE);
                    m.setCounter(counter);
                }
                if (m.getPcre() != null) {
                    cre = m.getPcre();
                    counter = m.getCounter().add(BigDecimal.ONE);
                    m.setCounter(counter);
                }
                if (m.getPagr() != null) {
                    agr = m.getPagr();
                    counter = m.getCounter().add(BigDecimal.ONE);
                    m.setCounter(counter);
                }
                if (m.getPbs() != null) {
                    bs = m.getPbs();
                    counter = m.getCounter().add(BigDecimal.ONE);
                    m.setCounter(counter);
                }
                if (m.getPpe() != null) {
                    pe = m.getPpe();
                    counter = m.getCounter().add(BigDecimal.ONE);
                    m.setCounter(counter);
                }
                BigDecimal ag = BigDecimal.ZERO;
                ag = ag.add(math);
                ag = ag.add(eng);
                ag = ag.add(kisw);
                ag = ag.add(bio);
                ag = ag.add(chem);
                ag = ag.add(phys);
                ag = ag.add(hist);
                ag = ag.add(geo);
                ag = ag.add(agr);
                ag = ag.add(cre);
                ag = ag.add(comp);
                ag = ag.add(bs);
                ag = ag.add(pe);


                ag = ag.divide(m.getCounter(), 2, RoundingMode.HALF_UP);
                m.setAggregate(ag);
                meritListBeans.add(m);
            }
        }
        return meritListBeans;
    }

    @Override
    public List<GenderMeritBean> genderMeritList(ExamRegister exam,Forms forms) {
        List<Student> examRecordings = studentRepo.findByForms_ClassCode(forms.getClassCode());
        List<GenderMeritBean> meritListBeans = new ArrayList<>();
        for (Student e : examRecordings) {
            List<ExamRecording> list = examRecordingRepo.findByExamRegisterAndStudent(exam, e);
            if (!(list.isEmpty())) {
                GenderMeritBean m = new GenderMeritBean();
                m.setGender(e.getGender());
                m.setAdmNo(e.getAdmNo());
                m.setClassName(e.getForms().getAbbr());
                for (ExamRecording f : list) {
                    Long subjects = f.getTeacherSubjects().getSubjects().getOrder();
                    if (subjects==1) {
                        m.setPmath(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }
                    if (subjects==2) {
                        m.setPeng(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }

                    if (subjects==3) {
                        m.setPkisw(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }
                    if (subjects==4) {
                        m.setPchem(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }
                    if (subjects==5) {
                        m.setPbio(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }
                    if (subjects==6) {
                        m.setPphys(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }
                    if (subjects==7) {
                        m.setPhist(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }
                    if (subjects==8) {
                        m.setPgeo(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }
                    if (subjects==9) {
                        m.setPcre(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }
                    if (subjects==10) {
                        m.setPagr(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }
                    if (subjects==11) {
                        m.setPcomp(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }
                    if (subjects==12) {
                        m.setPbs(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }
                    if (subjects==13) {
                        m.setPpe(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }
                    m.setExam(f.getExamRegister().getExamName());


                }
                BigDecimal math = BigDecimal.ZERO;
                BigDecimal eng = BigDecimal.ZERO;
                BigDecimal kisw = BigDecimal.ZERO;
                BigDecimal bio = BigDecimal.ZERO;
                BigDecimal chem = BigDecimal.ZERO;
                BigDecimal phys = BigDecimal.ZERO;
                BigDecimal hist = BigDecimal.ZERO;
                BigDecimal geo = BigDecimal.ZERO;
                BigDecimal cre = BigDecimal.ZERO;
                BigDecimal agr = BigDecimal.ZERO;
                BigDecimal bs = BigDecimal.ZERO;
                BigDecimal comp = BigDecimal.ZERO;
                BigDecimal pe = BigDecimal.ZERO;
                m.setCounter(BigDecimal.ZERO);
                BigDecimal counter = m.getCounter();

                if (m.getPmath() != null) {
                    math = m.getPmath();
                    counter = m.getCounter().add(BigDecimal.ONE);
                    m.setCounter(counter);
                }
                if (m.getPeng() != null) {
                    eng = m.getPeng();
                    counter = m.getCounter().add(BigDecimal.ONE);
                    m.setCounter(counter);
                }
                if (m.getPkisw() != null) {
                    kisw = m.getPkisw();
                    counter = m.getCounter().add(BigDecimal.ONE);
                    m.setCounter(counter);
                }
                if (m.getPbio() != null) {
                    bio = m.getPbio();
                    counter = m.getCounter().add(BigDecimal.ONE);
                    m.setCounter(counter);
                }
                if (m.getPchem() != null) {
                    chem = m.getPchem();
                    counter = m.getCounter().add(BigDecimal.ONE);
                    m.setCounter(counter);
                }
                if (m.getPphys() != null) {
                    phys = m.getPphys();
                    counter = m.getCounter().add(BigDecimal.ONE);
                    m.setCounter(counter);
                }
                if (m.getPhist() != null) {
                    hist = m.getPhist();
                    counter = m.getCounter().add(BigDecimal.ONE);
                    m.setCounter(counter);
                }
                if (m.getPgeo() != null) {
                    geo = m.getPgeo();
                    counter = m.getCounter().add(BigDecimal.ONE);
                    m.setCounter(counter);
                }
                if (m.getPcre() != null) {
                    cre = m.getPcre();
                    counter = m.getCounter().add(BigDecimal.ONE);
                    m.setCounter(counter);
                }
                if (m.getPagr() != null) {
                    agr = m.getPagr();
                    counter = m.getCounter().add(BigDecimal.ONE);
                    m.setCounter(counter);
                }
                if (m.getPbs() != null) {
                    bs = m.getPbs();
                    counter = m.getCounter().add(BigDecimal.ONE);
                    m.setCounter(counter);
                }
                if (m.getPpe() != null) {
                    pe = m.getPpe();
                    counter = m.getCounter().add(BigDecimal.ONE);
                    m.setCounter(counter);
                }
                BigDecimal ag = BigDecimal.ZERO;
                ag = ag.add(math);
                ag = ag.add(eng);
                ag = ag.add(kisw);
                ag = ag.add(bio);
                ag = ag.add(chem);
                ag = ag.add(phys);
                ag = ag.add(hist);
                ag = ag.add(geo);
                ag = ag.add(agr);
                ag = ag.add(cre);
                ag = ag.add(comp);
                ag = ag.add(bs);
                ag = ag.add(pe);


                ag = ag.divide(m.getCounter(), 2, RoundingMode.HALF_UP);
                m.setAggregate(ag);
                meritListBeans.add(m);
            }
        }
        Comparator<GenderMeritBean> compareByName = Comparator
                .comparing(GenderMeritBean::getAggregate)
                .reversed();

        return meritListBeans.stream()
                .sorted(compareByName)
                .collect(Collectors.toList());
    }

    @Override
    public List<MeritListBean> singleExamList(ExamRegister exam,Forms forms) {
        List<Student> examRecordings = studentRepo.findByForms_ClassCode(forms.getClassCode());
        List<MeritListBean> meritListBeans = new ArrayList<>();
        for (Student e : examRecordings) {
            List<ExamRecording> list = examRecordingRepo.findByExamRegisterAndStudent(exam, e);
            if (!(list.isEmpty())) {
                MeritListBean m = new MeritListBean();
                m.setName(e.getName());
                m.setAdmNo(e.getAdmNo());
                m.setClassName(e.getForms().getAbbr());
                for (ExamRecording f : list) {
                    Long subjects = f.getTeacherSubjects().getSubjects().getOrder();
                    if (subjects==1) {
                        m.setPmath(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }
                    if (subjects==2) {
                        m.setPeng(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }

                    if (subjects==3) {
                        m.setPkisw(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }
                    if (subjects==4) {
                        m.setPchem(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }
                    if (subjects==5) {
                        m.setPbio(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }
                    if (subjects==6) {
                        m.setPphys(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }
                    if (subjects==7) {
                        m.setPhist(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }
                    if (subjects==8) {
                        m.setPgeo(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }
                    if (subjects==9) {
                        m.setPcre(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }
                    if (subjects==10) {
                        m.setPagr(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }
                    if (subjects==11) {
                        m.setPcomp(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }
                    if (subjects==12) {
                        m.setPbs(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }
                    if (subjects==13) {
                        m.setPpe(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }
                    m.setExam(f.getExamRegister().getExamName());


                }
                BigDecimal math = BigDecimal.ZERO;
                BigDecimal eng = BigDecimal.ZERO;
                BigDecimal kisw = BigDecimal.ZERO;
                BigDecimal bio = BigDecimal.ZERO;
                BigDecimal chem = BigDecimal.ZERO;
                BigDecimal phys = BigDecimal.ZERO;
                BigDecimal hist = BigDecimal.ZERO;
                BigDecimal geo = BigDecimal.ZERO;
                BigDecimal cre = BigDecimal.ZERO;
                BigDecimal agr = BigDecimal.ZERO;
                BigDecimal bs = BigDecimal.ZERO;
                BigDecimal comp = BigDecimal.ZERO;
                BigDecimal pe = BigDecimal.ZERO;
                m.setCounter(BigDecimal.ZERO);
                BigDecimal counter = m.getCounter();

                if (m.getPmath() != null) {
                    math = m.getPmath();
                    counter = m.getCounter().add(BigDecimal.ONE);
                    m.setCounter(counter);
                }
                if (m.getPeng() != null) {
                    eng = m.getPeng();
                    counter = m.getCounter().add(BigDecimal.ONE);
                    m.setCounter(counter);
                }
                if (m.getPkisw() != null) {
                    kisw = m.getPkisw();
                    counter = m.getCounter().add(BigDecimal.ONE);
                    m.setCounter(counter);
                }
                if (m.getPbio() != null) {
                    bio = m.getPbio();
                    counter = m.getCounter().add(BigDecimal.ONE);
                    m.setCounter(counter);
                }
                if (m.getPchem() != null) {
                    chem = m.getPchem();
                    counter = m.getCounter().add(BigDecimal.ONE);
                    m.setCounter(counter);
                }
                if (m.getPphys() != null) {
                    phys = m.getPphys();
                    counter = m.getCounter().add(BigDecimal.ONE);
                    m.setCounter(counter);
                }
                if (m.getPhist() != null) {
                    hist = m.getPhist();
                    counter = m.getCounter().add(BigDecimal.ONE);
                    m.setCounter(counter);
                }
                if (m.getPgeo() != null) {
                    geo = m.getPgeo();
                    counter = m.getCounter().add(BigDecimal.ONE);
                    m.setCounter(counter);
                }
                if (m.getPcre() != null) {
                    cre = m.getPcre();
                    counter = m.getCounter().add(BigDecimal.ONE);
                    m.setCounter(counter);
                }
                if (m.getPagr() != null) {
                    agr = m.getPagr();
                    counter = m.getCounter().add(BigDecimal.ONE);
                    m.setCounter(counter);
                }
                if (m.getPbs() != null) {
                    bs = m.getPbs();
                    counter = m.getCounter().add(BigDecimal.ONE);
                    m.setCounter(counter);
                }
                if (m.getPpe() != null) {
                    pe = m.getPpe();
                    counter = m.getCounter().add(BigDecimal.ONE);
                    m.setCounter(counter);
                }
                BigDecimal ag = BigDecimal.ZERO;
                ag = ag.add(math);
                ag = ag.add(eng);
                ag = ag.add(kisw);
                ag = ag.add(bio);
                ag = ag.add(chem);
                ag = ag.add(phys);
                ag = ag.add(hist);
                ag = ag.add(geo);
                ag = ag.add(agr);
                ag = ag.add(cre);
                ag = ag.add(comp);
                ag = ag.add(bs);
                ag = ag.add(pe);


                ag = ag.divide(m.getCounter(), 2, RoundingMode.HALF_UP);
                m.setAggregate(ag);
                meritListBeans.add(m);
            }
        }

        Comparator<MeritListBean> compareByName = Comparator
                .comparing(MeritListBean::getAggregate)
                .reversed();

        return meritListBeans.stream()
                .sorted(compareByName)
                .collect(Collectors.toList());


    }
    @Override
    public List<MeritListBean> singleExamBroadList(ExamRegister exam) {
        List<Student> examRecordings = studentRepo.findAll(Sort.by(Sort.Direction.ASC, "forms"));
        List<MeritListBean> meritListBeans = new ArrayList<>();
        for (Student e : examRecordings) {
            List<ExamRecording> list = examRecordingRepo.findByExamRegisterAndStudent(exam, e);
            if (!(list.isEmpty())) {
                MeritListBean m = new MeritListBean();
                m.setName(e.getName());
                m.setAdmNo(e.getAdmNo());
                m.setClassName(e.getForms().getAbbr());
                for (ExamRecording f : list) {
                    Long subjects = f.getTeacherSubjects().getSubjects().getOrder();
                    if (subjects==1) {
                        m.setPmath(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }
                    if (subjects==2) {
                        m.setPeng(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }

                    if (subjects==3) {
                        m.setPkisw(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }
                    if (subjects==4) {
                        m.setPchem(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }
                    if (subjects==5) {
                        m.setPbio(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }
                    if (subjects==6) {
                        m.setPphys(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }
                    if (subjects==7) {
                        m.setPhist(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }
                    if (subjects==8) {
                        m.setPgeo(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }
                    if (subjects==9) {
                        m.setPcre(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }
                    if (subjects==10) {
                        m.setPagr(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }
                    if (subjects==11) {
                        m.setPcomp(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }
                    if (subjects==12) {
                        m.setPbs(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }
                    if (subjects==13) {
                        m.setPpe(BigDecimal.valueOf(Math.round(f.getPercentage())));
                    }
                    m.setExam(f.getExamRegister().getExamName());


                }
                BigDecimal math = BigDecimal.ZERO;
                BigDecimal eng = BigDecimal.ZERO;
                BigDecimal kisw = BigDecimal.ZERO;
                BigDecimal bio = BigDecimal.ZERO;
                BigDecimal chem = BigDecimal.ZERO;
                BigDecimal phys = BigDecimal.ZERO;
                BigDecimal hist = BigDecimal.ZERO;
                BigDecimal geo = BigDecimal.ZERO;
                BigDecimal cre = BigDecimal.ZERO;
                BigDecimal agr = BigDecimal.ZERO;
                BigDecimal bs = BigDecimal.ZERO;
                BigDecimal comp = BigDecimal.ZERO;
                BigDecimal pe = BigDecimal.ZERO;
                m.setCounter(BigDecimal.ZERO);
                BigDecimal counter = m.getCounter();

                if (m.getPmath() != null) {
                    math = m.getPmath();
                    counter = m.getCounter().add(BigDecimal.ONE);
                    m.setCounter(counter);
                }
                if (m.getPeng() != null) {
                    eng = m.getPeng();
                    counter = m.getCounter().add(BigDecimal.ONE);
                    m.setCounter(counter);
                }
                if (m.getPkisw() != null) {
                    kisw = m.getPkisw();
                    counter = m.getCounter().add(BigDecimal.ONE);
                    m.setCounter(counter);
                }
                if (m.getPbio() != null) {
                    bio = m.getPbio();
                    counter = m.getCounter().add(BigDecimal.ONE);
                    m.setCounter(counter);
                }
                if (m.getPchem() != null) {
                    chem = m.getPchem();
                    counter = m.getCounter().add(BigDecimal.ONE);
                    m.setCounter(counter);
                }
                if (m.getPphys() != null) {
                    phys = m.getPphys();
                    counter = m.getCounter().add(BigDecimal.ONE);
                    m.setCounter(counter);
                }
                if (m.getPhist() != null) {
                    hist = m.getPhist();
                    counter = m.getCounter().add(BigDecimal.ONE);
                    m.setCounter(counter);
                }
                if (m.getPgeo() != null) {
                    geo = m.getPgeo();
                    counter = m.getCounter().add(BigDecimal.ONE);
                    m.setCounter(counter);
                }
                if (m.getPcre() != null) {
                    cre = m.getPcre();
                    counter = m.getCounter().add(BigDecimal.ONE);
                    m.setCounter(counter);
                }
                if (m.getPagr() != null) {
                    agr = m.getPagr();
                    counter = m.getCounter().add(BigDecimal.ONE);
                    m.setCounter(counter);
                }
                if (m.getPbs() != null) {
                    bs = m.getPbs();
                    counter = m.getCounter().add(BigDecimal.ONE);
                    m.setCounter(counter);
                }
                if (m.getPpe() != null) {
                    pe = m.getPpe();
                    counter = m.getCounter().add(BigDecimal.ONE);
                    m.setCounter(counter);
                }
                BigDecimal ag = BigDecimal.ZERO;
                ag = ag.add(math);
                ag = ag.add(eng);
                ag = ag.add(kisw);
                ag = ag.add(bio);
                ag = ag.add(chem);
                ag = ag.add(phys);
                ag = ag.add(hist);
                ag = ag.add(geo);
                ag = ag.add(agr);
                ag = ag.add(cre);
                ag = ag.add(comp);
                ag = ag.add(bs);
                ag = ag.add(pe);


                ag = ag.divide(m.getCounter(), 2, RoundingMode.HALF_UP);
                m.setAggregate(ag);
                meritListBeans.add(m);
            }
        }

        Comparator<MeritListBean> compareByName = Comparator
                .comparing(MeritListBean::getClassName)
                .thenComparing(MeritListBean::getAggregate)
                .reversed();

        return meritListBeans.stream()
                .sorted(compareByName)
                .collect(Collectors.toList());


    }

    @Override
    public List<ReportFormBean> reportForm(ExamRegister exam, Forms forms, Student student, String remarks, String hRemarks) throws BadRequestException {
        PositioningBean positioningBean = positionRepo.findByExamRegisterAndFormsAndStudent(exam, forms, student);
        List<ReportFormBean> reportFormBeans = new ArrayList<>();
        ReportFormBean myBean = new ReportFormBean();
        List<ExamRecording> examRecordings = examRecordingRepo.findByExamRegisterAndFormsAndStudent(exam, forms, student);

        for (ExamRecording e : examRecordings) {
            if (e.getTeacherSubjects().getSubjects().getOrder() == 1) {
                myBean.setGrade1(e.getGradingSystem().getGrade());
                myBean.setSub1(e.getTeacherSubjects().getSubjects().getName());
                myBean.setMark1(BigDecimal.valueOf(e.getMark()));
                myBean.setGradeR1(e.getGradingSystem().getRemarks());
            } else if (e.getTeacherSubjects().getSubjects().getOrder() == 2) {
                myBean.setGrade2(e.getGradingSystem().getGrade());
                myBean.setSub2(e.getTeacherSubjects().getSubjects().getName());
                myBean.setMark2(BigDecimal.valueOf(e.getMark()));
                myBean.setGradeR2(e.getGradingSystem().getRemarks());
            } else if (e.getTeacherSubjects().getSubjects().getOrder() == 3) {
                myBean.setGrade3(e.getGradingSystem().getGrade());
                myBean.setSub3(e.getTeacherSubjects().getSubjects().getName());
                myBean.setMark3(BigDecimal.valueOf(e.getMark()));
                myBean.setGradeR3(e.getGradingSystem().getRemarks());
            } else if (e.getTeacherSubjects().getSubjects().getOrder() == 4) {
                myBean.setGrade4(e.getGradingSystem().getGrade());
                myBean.setSub4(e.getTeacherSubjects().getSubjects().getName());
                myBean.setMark4(BigDecimal.valueOf(e.getMark()));
                myBean.setGradeR4(e.getGradingSystem().getRemarks());
            } else if (e.getTeacherSubjects().getSubjects().getOrder() == 5) {
                myBean.setGrade5(e.getGradingSystem().getGrade());
                myBean.setSub5(e.getTeacherSubjects().getSubjects().getName());
                myBean.setMark5(BigDecimal.valueOf(e.getMark()));
                myBean.setGradeR5(e.getGradingSystem().getRemarks());
            } else if (e.getTeacherSubjects().getSubjects().getOrder() == 6) {
                myBean.setGrade6(e.getGradingSystem().getGrade());
                myBean.setSub6(e.getTeacherSubjects().getSubjects().getName());
                myBean.setMark6(BigDecimal.valueOf(e.getMark()));
                myBean.setGradeR6(e.getGradingSystem().getRemarks());
            } else if (e.getTeacherSubjects().getSubjects().getOrder() == 7) {
                myBean.setGrade7(e.getGradingSystem().getGrade());
                myBean.setSub7(e.getTeacherSubjects().getSubjects().getName());
                myBean.setMark7(BigDecimal.valueOf(e.getMark()));
                myBean.setGradeR7(e.getGradingSystem().getRemarks());
            } else if (e.getTeacherSubjects().getSubjects().getOrder() == 8) {
                myBean.setGrade8(e.getGradingSystem().getGrade());
                myBean.setSub8(e.getTeacherSubjects().getSubjects().getName());
                myBean.setMark8(BigDecimal.valueOf(e.getMark()));
                myBean.setGradeR8(e.getGradingSystem().getRemarks());
            } else if (e.getTeacherSubjects().getSubjects().getOrder() == 9) {
                myBean.setGrade9(e.getGradingSystem().getGrade());
                myBean.setSub9(e.getTeacherSubjects().getSubjects().getName());
                myBean.setMark9(BigDecimal.valueOf(e.getMark()));
                myBean.setGradeR9(e.getGradingSystem().getRemarks());
            } else if (e.getTeacherSubjects().getSubjects().getOrder() == 10) {
                myBean.setGrade10(e.getGradingSystem().getGrade());
                myBean.setSub10(e.getTeacherSubjects().getSubjects().getName());
                myBean.setMark10(BigDecimal.valueOf(e.getMark()));
                myBean.setGradeR10(e.getGradingSystem().getRemarks());
            } else if (e.getTeacherSubjects().getSubjects().getOrder() == 11) {
                myBean.setGrade11(e.getGradingSystem().getGrade());
                myBean.setSub11(e.getTeacherSubjects().getSubjects().getName());
                myBean.setMark11(BigDecimal.valueOf(e.getMark()));
                myBean.setGradeR11(e.getGradingSystem().getRemarks());
            } else if (e.getTeacherSubjects().getSubjects().getOrder() == 12) {
                myBean.setGrade12(e.getGradingSystem().getGrade());
                myBean.setSub12(e.getTeacherSubjects().getSubjects().getName());
                myBean.setMark12(BigDecimal.valueOf(e.getMark()));
                myBean.setGradeR12(e.getGradingSystem().getRemarks());
            } else if (e.getTeacherSubjects().getSubjects().getOrder() == 13) {
                myBean.setGrade13(e.getGradingSystem().getGrade());
                myBean.setSub13(e.getTeacherSubjects().getSubjects().getName());
                myBean.setMark13(BigDecimal.valueOf(e.getMark()));
                myBean.setGradeR13(e.getGradingSystem().getRemarks());
            } else if (e.getTeacherSubjects().getSubjects().getOrder() == 14) {
                myBean.setGrade14(e.getGradingSystem().getGrade());
                myBean.setSub14(e.getTeacherSubjects().getSubjects().getName());
                myBean.setMark14(BigDecimal.valueOf(e.getMark()));
                myBean.setGradeR14(e.getGradingSystem().getRemarks());
            } else {
                throw new BadRequestException("Subject Order Not Set");
            }
        }
        myBean.setPosition(positioningBean.getPosition());
        myBean.setAggregate(positioningBean.getMarks());
        myBean.setTotalGrade(positioningBean.getGrade());
        myBean.setClassName(forms.getAbbr());
        myBean.setName(student.getName());
        myBean.setAdmNo(student.getAdmNo());
        myBean.setTeacherComments(remarks);
        myBean.setHeadTeacherComments(hRemarks);
        myBean.setExam(exam.getExamName());
        myBean.setTeacher(forms.getTeachers().getName());
        myBean.setClassAggregate(positioningBean.getClassMarks());
        reportFormBeans.add(myBean);
        return reportFormBeans;

    }

    @Override
    public List<TimetableBean> classtt(Forms forms) {
        List<Allocations> allocations=allocationRepo.findByForms(forms);
        List<Lessons> gkc=lessonRepo.findAll();
        List<TimetableBean> timetableBeans=new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("hh:mm");
        TimetableBean t=new TimetableBean();
        for(Lessons l:gkc){
            if(l.getLessonId()==3){
                t.setBt1(dateFormat.format(l.getLessonStart()));
            }
            if(l.getLessonId()==6){
                t.setBt2(dateFormat.format(l.getLessonStart()));
            }
            if(l.getLessonId()==9){
                t.setBt3(dateFormat.format(l.getLessonStart()));
            }
            if(l.getLessonId()==12){
                t.setBt4(dateFormat.format(l.getLessonStart()));
            }
            if(l.getLessonId()==15){
                t.setBt5(dateFormat.format(l.getLessonStart()));
            }
        }
        for(Allocations a:allocations){
            if(a.getDays().getDayCode()==1){
                t.setD1(a.getDays().getName());
                if(a.getLessons().getLessonId()==1){
                    t.setL1m(a.getSubjects().getTimeTableName());
                    t.setT1(dateFormat.format(a.getLessons().getLessonStart()));

                }
                if(a.getLessons().getLessonId()==2){
                    t.setL2m(a.getSubjects().getTimeTableName());
                    t.setT2(dateFormat.format(a.getLessons().getLessonStart()));
                }
                if(a.getLessons().getLessonId()==4){
                    t.setL3m(a.getSubjects().getTimeTableName());
                    t.setT3(dateFormat.format(a.getLessons().getLessonStart()));
                }
                if(a.getLessons().getLessonId()==5){
                    t.setL4m(a.getSubjects().getTimeTableName());
                    t.setT4(dateFormat.format(a.getLessons().getLessonStart()));
                }
                if(a.getLessons().getLessonId()==7){
                    t.setL5m(a.getSubjects().getTimeTableName());
                    t.setT5(dateFormat.format(a.getLessons().getLessonStart()));
                }
                if(a.getLessons().getLessonId()==8){
                    t.setL6m(a.getSubjects().getTimeTableName());
                    t.setT6(dateFormat.format(a.getLessons().getLessonStart()));
                }
                if(a.getLessons().getLessonId()==10){
                    t.setL7m("PREPS");
                    t.setT7(dateFormat.format(a.getLessons().getLessonStart()));
                }
                if(a.getLessons().getLessonId()==11){
                    t.setL8m(a.getSubjects().getTimeTableName());
                    t.setT8(dateFormat.format(a.getLessons().getLessonStart()));
                }
                if(a.getLessons().getLessonId()==13){
                    t.setL9m(a.getSubjects().getTimeTableName());
                    t.setT9(dateFormat.format(a.getLessons().getLessonStart()));
                }
                if(a.getLessons().getLessonId()==14){
                    t.setL10m(a.getSubjects().getTimeTableName());
                    t.setT10(dateFormat.format(a.getLessons().getLessonStart()));
                }
            }
            if(a.getDays().getDayCode()==2){
                t.setD2(a.getDays().getName());
                if(a.getLessons().getLessonId()==1){
                    t.setL1t(a.getSubjects().getTimeTableName());
                    if(t.getT1()==null) {
                        t.setT1(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                }
                if(a.getLessons().getLessonId()==2){
                    t.setL2t(a.getSubjects().getTimeTableName());
                    if(t.getT2()==null) {
                        t.setT2(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                }
                if(a.getLessons().getLessonId()==4){
                    t.setL3t(a.getSubjects().getTimeTableName());
                    if(t.getT3()==null) {
                        t.setT3(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                }
                if(a.getLessons().getLessonId()==5){
                    t.setL4t(a.getSubjects().getTimeTableName());
                    if(t.getT4()==null) {
                        t.setT4(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                }
                if(a.getLessons().getLessonId()==7){
                    t.setL5t(a.getSubjects().getTimeTableName());
                    if(t.getT5()==null) {
                        t.setT5(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                }
                if(a.getLessons().getLessonId()==8){
                    t.setL6t(a.getSubjects().getTimeTableName());
                    if(t.getT6()==null) {
                        t.setT6(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                }
                if(a.getLessons().getLessonId()==10){
                    t.setL7t("PREPS");
                    if(t.getT7()==null) {
                        t.setT7(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                }
                if(a.getLessons().getLessonId()==11){
                    t.setL8t(a.getSubjects().getTimeTableName());
                    if(t.getT8()==null) {
                        t.setT8(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                }
                if(a.getLessons().getLessonId()==13){
                    t.setL9t(a.getSubjects().getTimeTableName());
                    if(t.getT9()==null) {
                        t.setT9(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                }
                if(a.getLessons().getLessonId()==14){
                    t.setL10t(a.getSubjects().getTimeTableName());
                    if(t.getT10()==null) {
                        t.setT10(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                }
            }
            if(a.getDays().getDayCode()==3){
                t.setD3(a.getDays().getName());
                if(a.getLessons().getLessonId()==1){
                    t.setL1w(a.getSubjects().getTimeTableName());
                    if(t.getT1()==null) {
                        t.setT1(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                }
                if(a.getLessons().getLessonId()==2){
                    t.setL2w(a.getSubjects().getTimeTableName());
                    if(t.getT2()==null) {
                        t.setT2(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                }
                if(a.getLessons().getLessonId()==4){
                    t.setL3w(a.getSubjects().getTimeTableName());
                    if(t.getT3()==null) {
                        t.setT3(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                }
                if(a.getLessons().getLessonId()==5){
                    t.setL4w(a.getSubjects().getTimeTableName());
                    if(t.getT4()==null) {
                        t.setT4(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                }
                if(a.getLessons().getLessonId()==7){
                    t.setL5w(a.getSubjects().getTimeTableName());
                    if(t.getT5()==null) {
                        t.setT5(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                }
                if(a.getLessons().getLessonId()==8){
                    t.setL6w(a.getSubjects().getTimeTableName());
                    if(t.getT6()==null) {
                        t.setT6(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                }
                if(a.getLessons().getLessonId()==10){
                    t.setL7w("PREPS");
                    if(t.getT7()==null) {
                        t.setT7(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                }
                if(a.getLessons().getLessonId()==11){
                    t.setL8w(a.getSubjects().getTimeTableName());
                    if(t.getT8()==null) {
                        t.setT8(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                }
                if(a.getLessons().getLessonId()==13){
                    t.setL9w(a.getSubjects().getTimeTableName());
                    if(t.getT9()==null) {
                        t.setT9(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                }
                if(a.getLessons().getLessonId()==14){
                    t.setL10w(a.getSubjects().getTimeTableName());
                    if(t.getT10()==null) {
                        t.setT10(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                }
            }
            if(a.getDays().getDayCode()==4){
                t.setD4(a.getDays().getName());
                if(a.getLessons().getLessonId()==1){
                    t.setL1th(a.getSubjects().getTimeTableName());
                    if(t.getT1()==null) {
                        t.setT1(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                }
                if(a.getLessons().getLessonId()==2){
                    t.setL2th(a.getSubjects().getTimeTableName());
                    if(t.getT2()==null) {
                        t.setT2(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                }
                if(a.getLessons().getLessonId()==4){
                    t.setL3th(a.getSubjects().getTimeTableName());
                    if(t.getT3()==null) {
                        t.setT3(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                }
                if(a.getLessons().getLessonId()==5){
                    t.setL4th(a.getSubjects().getTimeTableName());
                    if(t.getT4()==null) {
                        t.setT4(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                }
                if(a.getLessons().getLessonId()==7){
                    t.setL5th(a.getSubjects().getTimeTableName());
                    if(t.getT5()==null) {
                        t.setT5(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                }
                if(a.getLessons().getLessonId()==8){
                    t.setL6th(a.getSubjects().getTimeTableName());
                    if(t.getT6()==null) {
                        t.setT6(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                }
                if(a.getLessons().getLessonId()==10){
                    t.setL7th("PREPS");
                    if(t.getT7()==null) {
                        t.setT7(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                }
                if(a.getLessons().getLessonId()==11){
                    t.setL8th(a.getSubjects().getTimeTableName());
                    if(t.getT8()==null) {
                        t.setT8(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                }
                if(a.getLessons().getLessonId()==13){
                    t.setL9th(a.getSubjects().getTimeTableName());
                    if(t.getT9()==null) {
                        t.setT9(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                }
                if(a.getLessons().getLessonId()==14){
                    t.setL10th(a.getSubjects().getTimeTableName());
                    if(t.getT10()==null) {
                        t.setT10(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                }
            }
            if(a.getDays().getDayCode()==5){
                t.setD5(a.getDays().getName());
                if(a.getLessons().getLessonId()==1){
                    t.setL1f(a.getSubjects().getTimeTableName());
                    if(t.getT1()==null) {
                        t.setT1(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                }
                if(a.getLessons().getLessonId()==2){
                    t.setL2f(a.getSubjects().getTimeTableName());
                    if(t.getT2()==null) {
                        t.setT2(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                }
                if(a.getLessons().getLessonId()==4){
                    t.setL3f(a.getSubjects().getTimeTableName());
                    if(t.getT3()==null) {
                        t.setT3(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                }
                if(a.getLessons().getLessonId()==5){
                    t.setL4f(a.getSubjects().getTimeTableName());
                    if(t.getT4()==null) {
                        t.setT4(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                }
                if(a.getLessons().getLessonId()==7){
                    t.setL5f(a.getSubjects().getTimeTableName());
                    if(t.getT5()==null) {
                        t.setT5(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                }
                if(a.getLessons().getLessonId()==8){
                    t.setL6f(a.getSubjects().getTimeTableName());
                    if(t.getT6()==null) {
                        t.setT6(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                }
                if(a.getLessons().getLessonId()==10){
                    t.setL7f("PREPS");
                    if(t.getT7()==null) {
                        t.setT7(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                }
                if(a.getLessons().getLessonId()==11){
                    t.setL8f(a.getSubjects().getTimeTableName());
                    if(t.getT8()==null) {
                        t.setT8(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                }
                if(a.getLessons().getLessonId()==13){
                    t.setL9f(a.getSubjects().getTimeTableName());
                    if(t.getT9()==null) {
                        t.setT9(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                }
                if(a.getLessons().getLessonId()==14){
                    t.setL10f(a.getSubjects().getTimeTableName());
                    if(t.getT10()==null) {
                        t.setT10(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                }
            }

        }
        timetableBeans.add(t);
        return timetableBeans;
    }

    @Override
    public List<TeacherTableBean> teacherstt(Teachers teachers) {
        List<Allocations> allocations=allocationRepo.findByTeachers(teachers);
        List<Lessons> gkc=lessonRepo.findAll();
        List<TeacherTableBean> timetableBeans=new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("hh:mm");
        TeacherTableBean t=new TeacherTableBean();
        for(Lessons l:gkc){
            if(l.getLessonId()==3){
                t.setBt1(dateFormat.format(l.getLessonStart()));
            }
            if(l.getLessonId()==6){
                t.setBt2(dateFormat.format(l.getLessonStart()));
            }
            if(l.getLessonId()==9){
                t.setBt3(dateFormat.format(l.getLessonStart()));
            }
            if(l.getLessonId()==12){
                t.setBt4(dateFormat.format(l.getLessonStart()));
            }
            if(l.getLessonId()==15){
                t.setBt5(dateFormat.format(l.getLessonStart()));
            }
        }
        for(Allocations a:allocations){
            if(a.getDays().getDayCode()==1){
                t.setD1(a.getDays().getName());
                if(a.getLessons().getLessonId()==1){
                    t.setL1m(a.getSubjects().getTimeTableName());
                    t.setT1(dateFormat.format(a.getLessons().getLessonStart()));
                     t.setC1m(a.getClassName());
                }
                if(a.getLessons().getLessonId()==2){
                    t.setL2m(a.getSubjects().getTimeTableName());
                    t.setT2(dateFormat.format(a.getLessons().getLessonStart()));
                    t.setC2m(a.getClassName());
                }
                if(a.getLessons().getLessonId()==4){
                    t.setL3m(a.getSubjects().getTimeTableName());
                    t.setT3(dateFormat.format(a.getLessons().getLessonStart()));
                    t.setC3m(a.getClassName());
                }
                if(a.getLessons().getLessonId()==5){
                    t.setL4m(a.getSubjects().getTimeTableName());
                    t.setT4(dateFormat.format(a.getLessons().getLessonStart()));
                    t.setC4m(a.getClassName());
                }
                if(a.getLessons().getLessonId()==7){
                    t.setL5m(a.getSubjects().getTimeTableName());
                    t.setT5(dateFormat.format(a.getLessons().getLessonStart()));
                    t.setC5m(a.getClassName());
                }
                if(a.getLessons().getLessonId()==8){
                    t.setL6m(a.getSubjects().getTimeTableName());
                    t.setT6(dateFormat.format(a.getLessons().getLessonStart()));
                    t.setC6m(a.getClassName());
                }
                if(a.getLessons().getLessonId()==10){
                    t.setL7m("PREPS");
                    t.setT7(dateFormat.format(a.getLessons().getLessonStart()));
                    //t.setC7m(a.getClassName());
                }
                if(a.getLessons().getLessonId()==11){
                    t.setL8m(a.getSubjects().getTimeTableName());
                    t.setT8(dateFormat.format(a.getLessons().getLessonStart()));
                    t.setC8m(a.getClassName());
                }
                if(a.getLessons().getLessonId()==13){
                    t.setL9m(a.getSubjects().getTimeTableName());
                    t.setT9(dateFormat.format(a.getLessons().getLessonStart()));
                    t.setC9m(a.getClassName());
                }
                if(a.getLessons().getLessonId()==14){
                    t.setL10m(a.getSubjects().getTimeTableName());
                    t.setT10(dateFormat.format(a.getLessons().getLessonStart()));
                    t.setC10m(a.getClassName());
                }
            }
            if(a.getDays().getDayCode()==2){
                t.setD2(a.getDays().getName());
                if(a.getLessons().getLessonId()==1){
                    t.setL1t(a.getSubjects().getTimeTableName());
                    if(t.getT1()==null) {
                        t.setT1(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                    t.setC1t(a.getClassName());
                }
                if(a.getLessons().getLessonId()==2){
                    t.setL2t(a.getSubjects().getTimeTableName());
                    if(t.getT2()==null) {
                        t.setT2(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                    t.setC2t(a.getClassName());

                }
                if(a.getLessons().getLessonId()==4){
                    t.setL3t(a.getSubjects().getTimeTableName());
                    if(t.getT3()==null) {
                        t.setT3(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                    t.setC3t(a.getClassName());

                }
                if(a.getLessons().getLessonId()==5){
                    t.setL4t(a.getSubjects().getTimeTableName());
                    if(t.getT4()==null) {
                        t.setT4(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                    t.setC4t(a.getClassName());

                }
                if(a.getLessons().getLessonId()==7){
                    t.setL5t(a.getSubjects().getTimeTableName());
                    if(t.getT5()==null) {
                        t.setT5(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                    t.setC5t(a.getClassName());

                }
                if(a.getLessons().getLessonId()==8){
                    t.setL6t(a.getSubjects().getTimeTableName());
                    if(t.getT6()==null) {
                        t.setT6(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                    t.setC6t(a.getClassName());

                }
                if(a.getLessons().getLessonId()==10){
                    t.setL7t("PREPS");
                    if(t.getT7()==null) {
                        t.setT7(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                   // t.setC7t(a.getClassName());

                }
                if(a.getLessons().getLessonId()==11){
                    t.setL8t(a.getSubjects().getTimeTableName());
                    if(t.getT8()==null) {
                        t.setT8(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                    t.setC8t(a.getClassName());

                }
                if(a.getLessons().getLessonId()==13){
                    t.setL9t(a.getSubjects().getTimeTableName());
                    if(t.getT9()==null) {
                        t.setT9(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                    t.setC9t(a.getClassName());

                }
                if(a.getLessons().getLessonId()==14){
                    t.setL10t(a.getSubjects().getTimeTableName());
                    if(t.getT10()==null) {
                        t.setT10(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                    t.setC10t(a.getClassName());

                }
            }
            if(a.getDays().getDayCode()==3){
                t.setD3(a.getDays().getName());
                if(a.getLessons().getLessonId()==1){
                    t.setL1w(a.getSubjects().getTimeTableName());
                    if(t.getT1()==null) {
                        t.setT1(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                    t.setC1w(a.getClassName());
                }
                if(a.getLessons().getLessonId()==2){
                    t.setL2w(a.getSubjects().getTimeTableName());
                    if(t.getT2()==null) {
                        t.setT2(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                    t.setC2w(a.getClassName());
                }
                if(a.getLessons().getLessonId()==4){
                    t.setL3w(a.getSubjects().getTimeTableName());
                    if(t.getT3()==null) {
                        t.setT3(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                    t.setC3w(a.getClassName());
                }
                if(a.getLessons().getLessonId()==5){
                    t.setL4w(a.getSubjects().getTimeTableName());
                    if(t.getT4()==null) {
                        t.setT4(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                    t.setC4w(a.getClassName());
                }
                if(a.getLessons().getLessonId()==7){
                    t.setL5w(a.getSubjects().getTimeTableName());
                    if(t.getT5()==null) {
                        t.setT5(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                    t.setC5w(a.getClassName());
                }
                if(a.getLessons().getLessonId()==8){
                    t.setL6w(a.getSubjects().getTimeTableName());
                    if(t.getT6()==null) {
                        t.setT6(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                    t.setC6w(a.getClassName());
                }
                if(a.getLessons().getLessonId()==10){
                    t.setL7w("PREPS");
                    if(t.getT7()==null) {
                        t.setT7(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                   // t.setC7w(a.getClassName());
                }
                if(a.getLessons().getLessonId()==11){
                    t.setL8w(a.getSubjects().getTimeTableName());
                    if(t.getT8()==null) {
                        t.setT8(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                    t.setC8w(a.getClassName());
                }
                if(a.getLessons().getLessonId()==13){
                    t.setL9w(a.getSubjects().getTimeTableName());
                    if(t.getT9()==null) {
                        t.setT9(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                    t.setC9w(a.getClassName());
                }
                if(a.getLessons().getLessonId()==14){
                    t.setL10w(a.getSubjects().getTimeTableName());
                    if(t.getT10()==null) {
                        t.setT10(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                    t.setC10w(a.getClassName());
                }
            }
            if(a.getDays().getDayCode()==4){
                t.setD4(a.getDays().getName());
                if(a.getLessons().getLessonId()==1){
                    t.setL1th(a.getSubjects().getTimeTableName());
                    if(t.getT1()==null) {
                        t.setT1(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                    t.setC1th(a.getClassName());
                }
                if(a.getLessons().getLessonId()==2){
                    t.setL2th(a.getSubjects().getTimeTableName());
                    if(t.getT2()==null) {
                        t.setT2(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                    t.setC2th(a.getClassName());

                }
                if(a.getLessons().getLessonId()==4){
                    t.setL3th(a.getSubjects().getTimeTableName());
                    if(t.getT3()==null) {
                        t.setT3(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                    t.setC3th(a.getClassName());

                }
                if(a.getLessons().getLessonId()==5){
                    t.setL4th(a.getSubjects().getTimeTableName());
                    if(t.getT4()==null) {
                        t.setT4(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                    t.setC4th(a.getClassName());

                }
                if(a.getLessons().getLessonId()==7){
                    t.setL5th(a.getSubjects().getTimeTableName());
                    if(t.getT5()==null) {
                        t.setT5(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                    t.setC5th(a.getClassName());

                }
                if(a.getLessons().getLessonId()==8){
                    t.setL6th(a.getSubjects().getTimeTableName());
                    if(t.getT6()==null) {
                        t.setT6(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                    t.setC6th(a.getClassName());

                }
                if(a.getLessons().getLessonId()==10){
                    t.setL7th("PREPS");
                    if(t.getT7()==null) {
                        t.setT7(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                    //t.setC7th(a.getClassName());

                }
                if(a.getLessons().getLessonId()==11){
                    t.setL8th(a.getSubjects().getTimeTableName());
                    if(t.getT8()==null) {
                        t.setT8(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                    t.setC8th(a.getClassName());

                }
                if(a.getLessons().getLessonId()==13){
                    t.setL9th(a.getSubjects().getTimeTableName());
                    if(t.getT9()==null) {
                        t.setT9(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                    t.setC9th(a.getClassName());

                }
                if(a.getLessons().getLessonId()==14){
                    t.setL10th(a.getSubjects().getTimeTableName());
                    if(t.getT10()==null) {
                        t.setT10(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                    t.setC10th(a.getClassName());

                }
            }
            if(a.getDays().getDayCode()==5){
                t.setD5(a.getDays().getName());
                if(a.getLessons().getLessonId()==1){
                    t.setL1f(a.getSubjects().getTimeTableName());
                    if(t.getT1()==null) {
                        t.setT1(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                    t.setC1f(a.getClassName());

                }
                if(a.getLessons().getLessonId()==2){
                    t.setL2f(a.getSubjects().getTimeTableName());
                    if(t.getT2()==null) {
                        t.setT2(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                    t.setC2f(a.getClassName());

                }
                if(a.getLessons().getLessonId()==4){
                    t.setL3f(a.getSubjects().getTimeTableName());
                    if(t.getT3()==null) {
                        t.setT3(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                    t.setC3f(a.getClassName());

                }
                if(a.getLessons().getLessonId()==5){
                    t.setL4f(a.getSubjects().getTimeTableName());
                    if(t.getT4()==null) {
                        t.setT4(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                    t.setC4f(a.getClassName());

                }
                if(a.getLessons().getLessonId()==7){
                    t.setL5f(a.getSubjects().getTimeTableName());
                    if(t.getT5()==null) {
                        t.setT5(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                    t.setC5f(a.getClassName());

                }
                if(a.getLessons().getLessonId()==8){
                    t.setL6f(a.getSubjects().getTimeTableName());
                    if(t.getT6()==null) {
                        t.setT6(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                    t.setC6f(a.getClassName());

                }
                if(a.getLessons().getLessonId()==10){
                    t.setL7f("PREPS");
                    if(t.getT7()==null) {
                        t.setT7(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                    //t.setC7f(a.getClassName());

                }
                if(a.getLessons().getLessonId()==11){
                    t.setL8f(a.getSubjects().getTimeTableName());
                    if(t.getT8()==null) {
                        t.setT8(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                    t.setC8f(a.getClassName());

                }
                if(a.getLessons().getLessonId()==13){
                    t.setL9f(a.getSubjects().getTimeTableName());
                    if(t.getT9()==null) {
                        t.setT9(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                    t.setC9f(a.getClassName());

                }
                if(a.getLessons().getLessonId()==14){
                    t.setL10f(a.getSubjects().getTimeTableName());
                    if(t.getT10()==null) {
                        t.setT10(dateFormat.format(a.getLessons().getLessonStart()));
                    }
                    t.setC10f(a.getClassName());

                }
            }

        }
        timetableBeans.add(t);
        return timetableBeans;
    }
}
