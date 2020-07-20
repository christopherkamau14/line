package com.high.school.Reports.service.impl;

import com.high.school.Reports.service.ReportsInterface;
import com.high.school.Reports.utils.*;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
}
