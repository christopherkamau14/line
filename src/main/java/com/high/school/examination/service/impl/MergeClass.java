package com.high.school.examination.service.impl;

import com.high.school.academics.model.GradingSystem;
import com.high.school.academics.repo.GradingSysRepo;
import com.high.school.academics.repo.SubjectsRepo;
import com.high.school.academics.repo.TeacherRepo;
import com.high.school.academics.repo.TeacherSubjectsRepo;
import com.high.school.examination.model.ExamMergeBean;
import com.high.school.examination.model.ExamRecording;
import com.high.school.examination.repo.*;
import com.high.school.examination.service.MergeInterface;
import com.high.school.exceptions.BadRequestException;
import com.high.school.students.model.Student;
import com.high.school.students.repo.FormsRepo;
import com.high.school.students.repo.StudentRepo;
import com.high.school.students.repo.TermRepo;
import com.high.school.students.repo.YearRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class MergeClass implements MergeInterface {

    @Autowired
    ExamTypeRepo examTypeRepo;

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



    public String getGrade(double percentage) throws BadRequestException {
        String system="";
        if (percentage <= 29) {
            system = "E";
        } else if (percentage > 29 && percentage <= 34) {
            system = "D-";
        } else if (percentage > 34 && percentage <= 39) {
            system = "D";
        } else if (percentage > 39 && percentage <= 44) {
            system = "D+";
        } else if (percentage > 44 && percentage <= 49) {
            system = "C-";
        } else if (percentage > 49 && percentage <= 54) {
            system = "C";
        } else if (percentage > 54 && percentage <= 59) {
            system = "C+";
        } else if (percentage > 59 && percentage <= 64) {
            system = "B-";
        } else if (percentage > 64 && percentage <= 69) {
            system = "B";
        } else if (percentage > 69 && percentage <= 74) {
            system = "B+";
        } else if (percentage > 74 && percentage <= 79) {
            system = "A-";
        } else if (percentage > 79 && percentage <= 100) {
            system = "A";
        } else {
            throw new BadRequestException("Invalid marks" +percentage);
        }
        return system;
    }
    @Override
    public void mergeExam(ExamMergeBean examMergeBean) throws BadRequestException {

        if (examMergeBean.getForms() != null && examMergeBean.getSubjects() == null) {
            BigDecimal mark = examMergeBean.getMark1().add(examMergeBean.getMark2());
            if (mark.equals(examMergeBean.getOutOff())) {
                List<Student> students = studentRepo.findByForms_ClassCode(examMergeBean.getForms().getClassCode());
                if (!(students.isEmpty())) {
                    for (Student s : students) {
                        List<ExamRecording> examRecordings = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam(), s);
                        List<ExamRecording> examRecordings1 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam1(), s);
                        if (!(examRecordings.isEmpty()) && !(examRecordings1.isEmpty())) {
                            for (ExamRecording e : examRecordings) {
                                BigDecimal marks = BigDecimal.valueOf(e.getMark());
                                BigDecimal outOff = BigDecimal.valueOf(e.getOutOff());
                                BigDecimal aggr = marks.divide(outOff, 2, RoundingMode.HALF_UP);
                                BigDecimal t1 = aggr.multiply(examMergeBean.getMark1());
                                for (ExamRecording e1 : examRecordings1) {
                                    if (e.getTeacherSubjects().getSubjects() == e1.getTeacherSubjects().getSubjects()) {
                                        BigDecimal marks1 = BigDecimal.valueOf(e1.getMark());
                                        BigDecimal outOff1 = BigDecimal.valueOf(e1.getOutOff());
                                        BigDecimal aggr1 = marks1.divide(outOff1, 2, RoundingMode.HALF_UP);
                                        BigDecimal t2 = aggr1.multiply(examMergeBean.getMark2());
                                        BigDecimal tt = t1.add(t2);
                                        BigDecimal total = tt.divide(examMergeBean.getOutOff(), 2, RoundingMode.HALF_UP);
                                        total = total.multiply(BigDecimal.valueOf(100));
                                        double percentage = total.doubleValue();
                                        ExamRecording exam = new ExamRecording();
                                        GradingSystem system = new GradingSystem();
                                        exam.setPercentage(percentage);
                                        if (percentage <= 29) {
                                            system = gradingSysRepo.findByGradeIgnoreCase("E");
                                        } else if (percentage > 29 && percentage <= 34) {
                                            system = gradingSysRepo.findByGradeIgnoreCase("D-");
                                        } else if (percentage > 34 && percentage <= 39) {
                                            system = gradingSysRepo.findByGradeIgnoreCase("D");
                                        } else if (percentage > 39 && percentage <= 44) {
                                            system = gradingSysRepo.findByGradeIgnoreCase("D+");
                                        } else if (percentage > 44 && percentage <= 49) {
                                            system = gradingSysRepo.findByGradeIgnoreCase("C-");
                                        } else if (percentage > 49 && percentage <= 54) {
                                            system = gradingSysRepo.findByGradeIgnoreCase("C");
                                        } else if (percentage > 54 && percentage <= 59) {
                                            system = gradingSysRepo.findByGradeIgnoreCase("C+");
                                        } else if (percentage > 59 && percentage <= 64) {
                                            system = gradingSysRepo.findByGradeIgnoreCase("B-");
                                        } else if (percentage > 64 && percentage <= 69) {
                                            system = gradingSysRepo.findByGradeIgnoreCase("B");
                                        } else if (percentage > 69 && percentage <= 74) {
                                            system = gradingSysRepo.findByGradeIgnoreCase("B+");
                                        } else if (percentage > 74 && percentage <= 79) {
                                            system = gradingSysRepo.findByGradeIgnoreCase("A-");
                                        } else if (percentage > 79 && percentage <= 100) {
                                            system = gradingSysRepo.findByGradeIgnoreCase("A");
                                        } else {
                                            throw new BadRequestException("Invalid marks");
                                        }
                                        exam.setGradingSystem(system);
                                        exam.setTerm(examMergeBean.getTerm());
                                        exam.setYear(examMergeBean.getYear());
                                        exam.setStudent(s);
                                        exam.setTeacherSubjects(e1.getTeacherSubjects());
                                        exam.setExamDate(examMergeBean.getExamRegister().getEffectiveDate());
                                        exam.setExamRegister(examMergeBean.getExamRegister());
                                        exam.setForms(e1.getForms());
                                        exam.setTeachers(e1.getTeachers());
                                        exam.setMark(tt.doubleValue());
                                        exam.setOutOff(examMergeBean.getOutOff().doubleValue());
                                        examRecordingRepo.save(exam);
                                    }
                                }

                            }
                        }
                    }
                }
            } else {
                throw new BadRequestException("Totals don't add up");
            }
        } else if (examMergeBean.getSubjects() != null && examMergeBean.getForms() == null) {
            BigDecimal mark = examMergeBean.getMark1().add(examMergeBean.getMark2());
            if (mark.equals(examMergeBean.getOutOff())) {
                List<ExamRecording> examRecordings = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam(), examMergeBean.getSubjects());
                List<ExamRecording> examRecordings1 = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam1(), examMergeBean.getSubjects());
                if (!(examRecordings.isEmpty()) && !(examRecordings1.isEmpty())) {
                    for (ExamRecording e : examRecordings) {
                        BigDecimal marks = BigDecimal.valueOf(e.getMark());
                        BigDecimal outOff = BigDecimal.valueOf(e.getOutOff());
                        BigDecimal aggr = marks.divide(outOff, 2, RoundingMode.HALF_UP);
                        BigDecimal t1 = aggr.multiply(examMergeBean.getMark1());
                        for (ExamRecording e1 : examRecordings1) {
                            if (e.getTeacherSubjects().getSubjects() == e1.getTeacherSubjects().getSubjects() && e.getStudent()
                                    == e1.getStudent()) {
                                BigDecimal marks1 = BigDecimal.valueOf(e1.getMark());
                                BigDecimal outOff1 = BigDecimal.valueOf(e1.getOutOff());
                                BigDecimal aggr1 = marks1.divide(outOff1, 2, RoundingMode.HALF_UP);
                                BigDecimal t2 = aggr1.multiply(examMergeBean.getMark2());
                                BigDecimal tt = t1.add(t2);
                                BigDecimal total = tt.divide(examMergeBean.getOutOff(), 2, RoundingMode.HALF_UP);
                                total = total.multiply(BigDecimal.valueOf(100));
                                double percentage = total.doubleValue();
                                ExamRecording exam = new ExamRecording();
                                GradingSystem system = new GradingSystem();
                                exam.setPercentage(percentage);
                                if (percentage <= 29) {
                                    system = gradingSysRepo.findByGradeIgnoreCase("E");
                                } else if (percentage > 29 && percentage <= 34) {
                                    system = gradingSysRepo.findByGradeIgnoreCase("D-");
                                } else if (percentage > 34 && percentage <= 39) {
                                    system = gradingSysRepo.findByGradeIgnoreCase("D");
                                } else if (percentage > 39 && percentage <= 44) {
                                    system = gradingSysRepo.findByGradeIgnoreCase("D+");
                                } else if (percentage > 44 && percentage <= 49) {
                                    system = gradingSysRepo.findByGradeIgnoreCase("C-");
                                } else if (percentage > 49 && percentage <= 54) {
                                    system = gradingSysRepo.findByGradeIgnoreCase("C");
                                } else if (percentage > 54 && percentage <= 59) {
                                    system = gradingSysRepo.findByGradeIgnoreCase("C+");
                                } else if (percentage > 59 && percentage <= 64) {
                                    system = gradingSysRepo.findByGradeIgnoreCase("B-");
                                } else if (percentage > 64 && percentage <= 69) {
                                    system = gradingSysRepo.findByGradeIgnoreCase("B");
                                } else if (percentage > 69 && percentage <= 74) {
                                    system = gradingSysRepo.findByGradeIgnoreCase("B+");
                                } else if (percentage > 74 && percentage <= 79) {
                                    system = gradingSysRepo.findByGradeIgnoreCase("A-");
                                } else if (percentage > 79 && percentage <= 100) {
                                    system = gradingSysRepo.findByGradeIgnoreCase("A");
                                } else {
                                    throw new BadRequestException("Invalid marks");
                                }
                                exam.setGradingSystem(system);
                                exam.setTerm(examMergeBean.getTerm());
                                exam.setYear(examMergeBean.getYear());
                                exam.setStudent(e1.getStudent());
                                exam.setTeacherSubjects(e1.getTeacherSubjects());
                                exam.setExamDate(examMergeBean.getExamRegister().getEffectiveDate());
                                exam.setExamRegister(examMergeBean.getExamRegister());
                                exam.setForms(e1.getForms());
                                exam.setTeachers(e1.getTeachers());
                                exam.setMark(tt.doubleValue());
                                exam.setOutOff(examMergeBean.getOutOff().doubleValue());
                                examRecordingRepo.save(exam);
                            }
                        }

                    }

                }
            } else {
                throw new BadRequestException("Totals don't add up");
            }
        } else if (examMergeBean.getSubjects() != null && examMergeBean.getForms() != null) {
            BigDecimal mark = examMergeBean.getMark1().add(examMergeBean.getMark2());
            if (mark.equals(examMergeBean.getOutOff())) {
                List<Student> students = studentRepo.findByForms_ClassCode(examMergeBean.getForms().getClassCode());
                if (!(students.isEmpty())) {
                    for (Student s : students) {
                        List<ExamRecording> examRecordings = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam(), s, examMergeBean.getSubjects());
                        List<ExamRecording> examRecordings1 = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam1(), s, examMergeBean.getSubjects());
                        if (!(examRecordings.isEmpty()) && !(examRecordings1.isEmpty())) {
                            for (ExamRecording e : examRecordings) {
                                BigDecimal marks = BigDecimal.valueOf(e.getMark());
                                BigDecimal outOff = BigDecimal.valueOf(e.getOutOff());
                                BigDecimal aggr = marks.divide(outOff, 2, RoundingMode.HALF_UP);
                                BigDecimal t1 = aggr.multiply(examMergeBean.getMark1());
                                for (ExamRecording e1 : examRecordings1) {
                                    if (e.getTeacherSubjects().getSubjects() == e1.getTeacherSubjects().getSubjects() && e.getStudent() == e1.getStudent()) {
                                        BigDecimal marks1 = BigDecimal.valueOf(e1.getMark());
                                        BigDecimal outOff1 = BigDecimal.valueOf(e1.getOutOff());
                                        BigDecimal aggr1 = marks1.divide(outOff1, 2, RoundingMode.HALF_UP);
                                        BigDecimal t2 = aggr1.multiply(examMergeBean.getMark2());
                                        BigDecimal tt = t1.add(t2);
                                        BigDecimal total = tt.divide(examMergeBean.getOutOff(), 2, RoundingMode.HALF_UP);
                                        total = total.multiply(BigDecimal.valueOf(100));
                                        double percentage = total.doubleValue();
                                        ExamRecording exam = new ExamRecording();
                                        GradingSystem system = new GradingSystem();
                                        exam.setPercentage(percentage);
                                        if (percentage <= 29) {
                                            system = gradingSysRepo.findByGradeIgnoreCase("E");
                                        } else if (percentage > 29 && percentage <= 34) {
                                            system = gradingSysRepo.findByGradeIgnoreCase("D-");
                                        } else if (percentage > 34 && percentage <= 39) {
                                            system = gradingSysRepo.findByGradeIgnoreCase("D");
                                        } else if (percentage > 39 && percentage <= 44) {
                                            system = gradingSysRepo.findByGradeIgnoreCase("D+");
                                        } else if (percentage > 44 && percentage <= 49) {
                                            system = gradingSysRepo.findByGradeIgnoreCase("C-");
                                        } else if (percentage > 49 && percentage <= 54) {
                                            system = gradingSysRepo.findByGradeIgnoreCase("C");
                                        } else if (percentage > 54 && percentage <= 59) {
                                            system = gradingSysRepo.findByGradeIgnoreCase("C+");
                                        } else if (percentage > 59 && percentage <= 64) {
                                            system = gradingSysRepo.findByGradeIgnoreCase("B-");
                                        } else if (percentage > 64 && percentage <= 69) {
                                            system = gradingSysRepo.findByGradeIgnoreCase("B");
                                        } else if (percentage > 69 && percentage <= 74) {
                                            system = gradingSysRepo.findByGradeIgnoreCase("B+");
                                        } else if (percentage > 74 && percentage <= 79) {
                                            system = gradingSysRepo.findByGradeIgnoreCase("A-");
                                        } else if (percentage > 79 && percentage <= 100) {
                                            system = gradingSysRepo.findByGradeIgnoreCase("A");
                                        } else {
                                            throw new BadRequestException("Invalid marks");
                                        }
                                        exam.setGradingSystem(system);
                                        exam.setTerm(examMergeBean.getTerm());
                                        exam.setYear(examMergeBean.getYear());
                                        exam.setStudent(s);
                                        exam.setTeacherSubjects(e1.getTeacherSubjects());
                                        exam.setExamDate(examMergeBean.getExamRegister().getEffectiveDate());
                                        exam.setExamRegister(examMergeBean.getExamRegister());
                                        exam.setForms(e1.getForms());
                                        exam.setTeachers(e1.getTeachers());
                                        exam.setMark(tt.doubleValue());
                                        exam.setOutOff(examMergeBean.getOutOff().doubleValue());
                                        examRecordingRepo.save(exam);
                                    }
                                }

                            }
                        }
                    }
                }
            } else {
                throw new BadRequestException("Totals don't add up");
            }
        } else {
            BigDecimal mark = examMergeBean.getMark1().add(examMergeBean.getMark2());
            if (mark.equals(examMergeBean.getOutOff())) {
                List<Student> students = studentRepo.findAllByOrderByForms();
                for (Student s : students) {
                    List<ExamRecording> examRecordings = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam(), s);
                    List<ExamRecording> examRecordings1 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam1(), s);
                    if (!(examRecordings.isEmpty()) && !(examRecordings1.isEmpty())) {
                        for (ExamRecording e : examRecordings) {
                            BigDecimal marks = BigDecimal.valueOf(e.getMark());
                            BigDecimal outOff = BigDecimal.valueOf(e.getOutOff());
                            BigDecimal aggr = marks.divide(outOff, 2, RoundingMode.HALF_UP);
                            BigDecimal t1 = aggr.multiply(examMergeBean.getMark1());
                            for (ExamRecording e1 : examRecordings1) {
                                if (e.getTeacherSubjects().getSubjects() == e1.getTeacherSubjects().getSubjects()) {
                                    BigDecimal marks1 = BigDecimal.valueOf(e1.getMark());
                                    BigDecimal outOff1 = BigDecimal.valueOf(e1.getOutOff());
                                    BigDecimal aggr1 = marks1.divide(outOff1, 2, RoundingMode.HALF_UP);
                                    BigDecimal t2 = aggr1.multiply(examMergeBean.getMark2());
                                    BigDecimal tt = t1.add(t2);
                                    BigDecimal total = tt.divide(examMergeBean.getOutOff(), 2, RoundingMode.HALF_UP);
                                    total = total.multiply(BigDecimal.valueOf(100));
                                    double percentage = total.doubleValue();
                                    ExamRecording exam = new ExamRecording();
                                    GradingSystem system = new GradingSystem();
                                    exam.setPercentage(percentage);
                                    if (percentage <= 29) {
                                        system = gradingSysRepo.findByGradeIgnoreCase("E");
                                    } else if (percentage > 29 && percentage <= 34) {
                                        system = gradingSysRepo.findByGradeIgnoreCase("D-");
                                    } else if (percentage > 34 && percentage <= 39) {
                                        system = gradingSysRepo.findByGradeIgnoreCase("D");
                                    } else if (percentage > 39 && percentage <= 44) {
                                        system = gradingSysRepo.findByGradeIgnoreCase("D+");
                                    } else if (percentage > 44 && percentage <= 49) {
                                        system = gradingSysRepo.findByGradeIgnoreCase("C-");
                                    } else if (percentage > 49 && percentage <= 54) {
                                        system = gradingSysRepo.findByGradeIgnoreCase("C");
                                    } else if (percentage > 54 && percentage <= 59) {
                                        system = gradingSysRepo.findByGradeIgnoreCase("C+");
                                    } else if (percentage > 59 && percentage <= 64) {
                                        system = gradingSysRepo.findByGradeIgnoreCase("B-");
                                    } else if (percentage > 64 && percentage <= 69) {
                                        system = gradingSysRepo.findByGradeIgnoreCase("B");
                                    } else if (percentage > 69 && percentage <= 74) {
                                        system = gradingSysRepo.findByGradeIgnoreCase("B+");
                                    } else if (percentage > 74 && percentage <= 79) {
                                        system = gradingSysRepo.findByGradeIgnoreCase("A-");
                                    } else if (percentage > 79 && percentage <= 100) {
                                        system = gradingSysRepo.findByGradeIgnoreCase("A");
                                    } else {
                                        throw new BadRequestException("Invalid marks");
                                    }
                                    exam.setTerm(examMergeBean.getTerm());
                                    exam.setGradingSystem(system);
                                    exam.setYear(examMergeBean.getYear());
                                    exam.setStudent(s);
                                    exam.setTeacherSubjects(e1.getTeacherSubjects());
                                    exam.setExamDate(examMergeBean.getExamRegister().getEffectiveDate());
                                    exam.setExamRegister(examMergeBean.getExamRegister());
                                    exam.setForms(e1.getForms());
                                    exam.setTeachers(e1.getTeachers());
                                    exam.setMark(tt.doubleValue());
                                    exam.setOutOff(examMergeBean.getOutOff().doubleValue());
                                    examRecordingRepo.save(exam);
                                }
                            }

                        }
                    }
                }
            } else {
                throw new BadRequestException("Totals don't add up");
            }
        }
    }

    @Override
    public void mergeThreeExams(ExamMergeBean examMergeBean) throws BadRequestException {
        if (examMergeBean.getForms() != null && examMergeBean.getSubjects() == null) {
            BigDecimal mark = examMergeBean.getMark1().add(examMergeBean.getMark2()).add(examMergeBean.getMark3());
            if (mark.equals(examMergeBean.getOutOff())) {
                List<Student> students = studentRepo.findByForms_ClassCode(examMergeBean.getForms().getClassCode());
                if (!(students.isEmpty())) {
                    for (Student s : students) {
                        List<ExamRecording> examRecordings = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam(), s);
                        List<ExamRecording> examRecordings1 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam1(), s);
                        List<ExamRecording> examRecordings2 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam2(), s);
                        if (!(examRecordings.isEmpty()) && !(examRecordings1.isEmpty()) && !(examRecordings2.isEmpty())) {
                            for (ExamRecording e : examRecordings) {
                                BigDecimal marks = BigDecimal.valueOf(e.getMark());
                                BigDecimal outOff = BigDecimal.valueOf(e.getOutOff());
                                BigDecimal aggr = marks.divide(outOff, 2, RoundingMode.HALF_UP);
                                BigDecimal t1 = aggr.multiply(examMergeBean.getMark1());
                                for (ExamRecording e1 : examRecordings1) {
                                    if (e.getTeacherSubjects().getSubjects() == e1.getTeacherSubjects().getSubjects()) {
                                        BigDecimal marks1 = BigDecimal.valueOf(e1.getMark());
                                        BigDecimal outOff1 = BigDecimal.valueOf(e1.getOutOff());
                                        BigDecimal aggr1 = marks1.divide(outOff1, 2, RoundingMode.HALF_UP);
                                        BigDecimal t2 = aggr1.multiply(examMergeBean.getMark2());
                                        for (ExamRecording e2 : examRecordings2) {
                                            if (e1.getTeacherSubjects().getSubjects()
                                                    == e2.getTeacherSubjects().getSubjects()) {

                                                BigDecimal marks2 = BigDecimal.valueOf(e2.getMark());
                                                BigDecimal outOff2 = BigDecimal.valueOf(e2.getOutOff());
                                                BigDecimal aggr2 = marks2.divide(outOff2, 2, RoundingMode.HALF_UP);
                                                BigDecimal t3 = aggr2.multiply(examMergeBean.getMark3());
                                                BigDecimal tt = t1.add(t2).add(t3);
                                                BigDecimal total = tt.divide(examMergeBean.getOutOff(), 2, RoundingMode.HALF_UP);
                                                total = total.multiply(BigDecimal.valueOf(100));
                                                double percentage = total.doubleValue();
                                                ExamRecording exam = new ExamRecording();
                                                GradingSystem system = new GradingSystem();
                                                exam.setPercentage(percentage);
                                                if (percentage <= 29) {
                                                    system = gradingSysRepo.findByGradeIgnoreCase("E");
                                                } else if (percentage > 29 && percentage <= 34) {
                                                    system = gradingSysRepo.findByGradeIgnoreCase("D-");
                                                } else if (percentage > 34 && percentage <= 39) {
                                                    system = gradingSysRepo.findByGradeIgnoreCase("D");
                                                } else if (percentage > 39 && percentage <= 44) {
                                                    system = gradingSysRepo.findByGradeIgnoreCase("D+");
                                                } else if (percentage > 44 && percentage <= 49) {
                                                    system = gradingSysRepo.findByGradeIgnoreCase("C-");
                                                } else if (percentage > 49 && percentage <= 54) {
                                                    system = gradingSysRepo.findByGradeIgnoreCase("C");
                                                } else if (percentage > 54 && percentage <= 59) {
                                                    system = gradingSysRepo.findByGradeIgnoreCase("C+");
                                                } else if (percentage > 59 && percentage <= 64) {
                                                    system = gradingSysRepo.findByGradeIgnoreCase("B-");
                                                } else if (percentage > 64 && percentage <= 69) {
                                                    system = gradingSysRepo.findByGradeIgnoreCase("B");
                                                } else if (percentage > 69 && percentage <= 74) {
                                                    system = gradingSysRepo.findByGradeIgnoreCase("B+");
                                                } else if (percentage > 74 && percentage <= 79) {
                                                    system = gradingSysRepo.findByGradeIgnoreCase("A-");
                                                } else if (percentage > 79 && percentage <= 100) {
                                                    system = gradingSysRepo.findByGradeIgnoreCase("A");
                                                } else {
                                                    throw new BadRequestException("Invalid marks");
                                                }
                                                exam.setTerm(examMergeBean.getTerm());
                                                exam.setYear(examMergeBean.getYear());
                                                exam.setStudent(s);
                                                exam.setTeacherSubjects(e1.getTeacherSubjects());
                                                exam.setExamDate(examMergeBean.getExamRegister().getEffectiveDate());
                                                exam.setExamRegister(examMergeBean.getExamRegister());
                                                exam.setForms(e1.getForms());
                                                exam.setTeachers(e1.getTeachers());
                                                exam.setMark(tt.doubleValue());
                                                exam.setGradingSystem(system);
                                                exam.setOutOff(examMergeBean.getOutOff().doubleValue());
                                                examRecordingRepo.save(exam);
                                            }
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            } else {
                throw new BadRequestException("Totals don't add up");
            }
        } else if (examMergeBean.getSubjects() != null && examMergeBean.getForms() == null) {
            BigDecimal mark = examMergeBean.getMark1().add(examMergeBean.getMark2()).add(examMergeBean.getMark3());
            if (mark.equals(examMergeBean.getOutOff())) {
                List<ExamRecording> examRecordings = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam(), examMergeBean.getSubjects());
                List<ExamRecording> examRecordings1 = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam1(), examMergeBean.getSubjects());
                List<ExamRecording> examRecordings2 = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam2(), examMergeBean.getSubjects());

                if (!(examRecordings.isEmpty()) && !(examRecordings1.isEmpty()) && !(examRecordings2.isEmpty())) {
                    for (ExamRecording e : examRecordings) {
                        BigDecimal marks = BigDecimal.valueOf(e.getMark());
                        BigDecimal outOff = BigDecimal.valueOf(e.getOutOff());
                        BigDecimal aggr = marks.divide(outOff, 2, RoundingMode.HALF_UP);
                        BigDecimal t1 = aggr.multiply(examMergeBean.getMark1());
                        for (ExamRecording e1 : examRecordings1) {
                            if (e.getTeacherSubjects().getSubjects() == e1.getTeacherSubjects().getSubjects() && e.getStudent()
                                    == e1.getStudent()) {
                                BigDecimal marks1 = BigDecimal.valueOf(e1.getMark());
                                BigDecimal outOff1 = BigDecimal.valueOf(e1.getOutOff());
                                BigDecimal aggr1 = marks1.divide(outOff1, 2, RoundingMode.HALF_UP);
                                BigDecimal t2 = aggr1.multiply(examMergeBean.getMark2());
                                for (ExamRecording e2 : examRecordings2) {

                                    if (e1.getTeacherSubjects().getSubjects() == e2.getTeacherSubjects().getSubjects() && e1.getStudent()
                                            == e2.getStudent()) {
                                        BigDecimal marks2 = BigDecimal.valueOf(e2.getMark());
                                        BigDecimal outOff2 = BigDecimal.valueOf(e2.getOutOff());
                                        BigDecimal aggr2 = marks2.divide(outOff2, 2, RoundingMode.HALF_UP);
                                        BigDecimal t3 = aggr2.multiply(examMergeBean.getMark3());
                                        BigDecimal tt = t1.add(t2).add(t3);
                                        BigDecimal total = tt.divide(examMergeBean.getOutOff(), 2, RoundingMode.HALF_UP);
                                        total = total.multiply(BigDecimal.valueOf(100));
                                        double percentage = total.doubleValue();
                                        ExamRecording exam = new ExamRecording();
                                        GradingSystem system = new GradingSystem();
                                        exam.setPercentage(percentage);
                                        if (percentage <= 29) {
                                            system = gradingSysRepo.findByGradeIgnoreCase("E");
                                        } else if (percentage > 29 && percentage <= 34) {
                                            system = gradingSysRepo.findByGradeIgnoreCase("D-");
                                        } else if (percentage > 34 && percentage <= 39) {
                                            system = gradingSysRepo.findByGradeIgnoreCase("D");
                                        } else if (percentage > 39 && percentage <= 44) {
                                            system = gradingSysRepo.findByGradeIgnoreCase("D+");
                                        } else if (percentage > 44 && percentage <= 49) {
                                            system = gradingSysRepo.findByGradeIgnoreCase("C-");
                                        } else if (percentage > 49 && percentage <= 54) {
                                            system = gradingSysRepo.findByGradeIgnoreCase("C");
                                        } else if (percentage > 54 && percentage <= 59) {
                                            system = gradingSysRepo.findByGradeIgnoreCase("C+");
                                        } else if (percentage > 59 && percentage <= 64) {
                                            system = gradingSysRepo.findByGradeIgnoreCase("B-");
                                        } else if (percentage > 64 && percentage <= 69) {
                                            system = gradingSysRepo.findByGradeIgnoreCase("B");
                                        } else if (percentage > 69 && percentage <= 74) {
                                            system = gradingSysRepo.findByGradeIgnoreCase("B+");
                                        } else if (percentage > 74 && percentage <= 79) {
                                            system = gradingSysRepo.findByGradeIgnoreCase("A-");
                                        } else if (percentage > 79 && percentage <= 100) {
                                            system = gradingSysRepo.findByGradeIgnoreCase("A");
                                        } else {
                                            throw new BadRequestException("Invalid marks");
                                        }
                                        exam.setTerm(examMergeBean.getTerm());
                                        exam.setYear(examMergeBean.getYear());
                                        exam.setStudent(e2.getStudent());
                                        exam.setTeacherSubjects(e2.getTeacherSubjects());
                                        exam.setExamDate(examMergeBean.getExamRegister().getEffectiveDate());
                                        exam.setExamRegister(examMergeBean.getExamRegister());
                                        exam.setForms(e2.getForms());
                                        exam.setTeachers(e2.getTeachers());
                                        exam.setMark(tt.doubleValue());
                                        exam.setGradingSystem(system);
                                        exam.setOutOff(examMergeBean.getOutOff().doubleValue());
                                        examRecordingRepo.save(exam);
                                    }
                                }
                            }
                        }

                    }

                }
            } else {
                throw new BadRequestException("Totals don't add up");
            }
        } else if (examMergeBean.getSubjects() != null && examMergeBean.getForms() != null) {
            BigDecimal mark = examMergeBean.getMark1().add(examMergeBean.getMark2()).add(examMergeBean.getMark3());
            if (mark.equals(examMergeBean.getOutOff())) {
                List<Student> students = studentRepo.findByForms_ClassCode(examMergeBean.getForms().getClassCode());
                if (!(students.isEmpty())) {
                    for (Student s : students) {
                        List<ExamRecording> examRecordings = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam(), s, examMergeBean.getSubjects());
                        List<ExamRecording> examRecordings1 = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam1(), s, examMergeBean.getSubjects());
                        List<ExamRecording> examRecordings2 = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam2(), s, examMergeBean.getSubjects());

                        if (!(examRecordings.isEmpty()) && !(examRecordings1.isEmpty()) && !(examRecordings2.isEmpty())) {
                            for (ExamRecording e : examRecordings) {
                                BigDecimal marks = BigDecimal.valueOf(e.getMark());
                                BigDecimal outOff = BigDecimal.valueOf(e.getOutOff());
                                BigDecimal aggr = marks.divide(outOff, 2, RoundingMode.HALF_UP);
                                BigDecimal t1 = aggr.multiply(examMergeBean.getMark1());

                                for (ExamRecording e1 : examRecordings1) {
                                    if (e.getTeacherSubjects().getSubjects() == e1.getTeacherSubjects().getSubjects() && e.getStudent() == e1.getStudent()) {
                                        BigDecimal marks1 = BigDecimal.valueOf(e1.getMark());
                                        BigDecimal outOff1 = BigDecimal.valueOf(e1.getOutOff());
                                        BigDecimal aggr1 = marks1.divide(outOff1, 2, RoundingMode.HALF_UP);
                                        BigDecimal t2 = aggr1.multiply(examMergeBean.getMark2());
                                        for (ExamRecording e2 : examRecordings2) {
                                            if (e1.getTeacherSubjects().getSubjects() == e2.getTeacherSubjects().getSubjects() && e1.getStudent() == e2.getStudent()) {
                                                BigDecimal marks2 = BigDecimal.valueOf(e2.getMark());
                                                BigDecimal outOff2 = BigDecimal.valueOf(e2.getOutOff());
                                                BigDecimal aggr2 = marks2.divide(outOff2, 2, RoundingMode.HALF_UP);
                                                BigDecimal t3 = aggr2.multiply(examMergeBean.getMark3());
                                                BigDecimal tt = t1.add(t2).add(t3);
                                                BigDecimal total = tt.divide(examMergeBean.getOutOff(), 2, RoundingMode.HALF_UP);
                                                total = total.multiply(BigDecimal.valueOf(100));
                                                double percentage = total.doubleValue();
                                                ExamRecording exam = new ExamRecording();
                                                GradingSystem system = new GradingSystem();
                                                exam.setPercentage(percentage);
                                                if (percentage <= 29) {
                                                    system = gradingSysRepo.findByGradeIgnoreCase("E");
                                                } else if (percentage > 29 && percentage <= 34) {
                                                    system = gradingSysRepo.findByGradeIgnoreCase("D-");
                                                } else if (percentage > 34 && percentage <= 39) {
                                                    system = gradingSysRepo.findByGradeIgnoreCase("D");
                                                } else if (percentage > 39 && percentage <= 44) {
                                                    system = gradingSysRepo.findByGradeIgnoreCase("D+");
                                                } else if (percentage > 44 && percentage <= 49) {
                                                    system = gradingSysRepo.findByGradeIgnoreCase("C-");
                                                } else if (percentage > 49 && percentage <= 54) {
                                                    system = gradingSysRepo.findByGradeIgnoreCase("C");
                                                } else if (percentage > 54 && percentage <= 59) {
                                                    system = gradingSysRepo.findByGradeIgnoreCase("C+");
                                                } else if (percentage > 59 && percentage <= 64) {
                                                    system = gradingSysRepo.findByGradeIgnoreCase("B-");
                                                } else if (percentage > 64 && percentage <= 69) {
                                                    system = gradingSysRepo.findByGradeIgnoreCase("B");
                                                } else if (percentage > 69 && percentage <= 74) {
                                                    system = gradingSysRepo.findByGradeIgnoreCase("B+");
                                                } else if (percentage > 74 && percentage <= 79) {
                                                    system = gradingSysRepo.findByGradeIgnoreCase("A-");
                                                } else if (percentage > 79 && percentage <= 100) {
                                                    system = gradingSysRepo.findByGradeIgnoreCase("A");
                                                } else {
                                                    throw new BadRequestException("Invalid marks");
                                                }
                                                exam.setGradingSystem(system);
                                                exam.setTerm(examMergeBean.getTerm());
                                                exam.setYear(examMergeBean.getYear());
                                                exam.setStudent(s);
                                                exam.setTeacherSubjects(e1.getTeacherSubjects());
                                                exam.setExamDate(examMergeBean.getExamRegister().getEffectiveDate());
                                                exam.setExamRegister(examMergeBean.getExamRegister());
                                                exam.setForms(e1.getForms());
                                                exam.setTeachers(e1.getTeachers());
                                                exam.setMark(tt.doubleValue());
                                                exam.setOutOff(examMergeBean.getOutOff().doubleValue());
                                                examRecordingRepo.save(exam);
                                            }
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            } else {
                throw new BadRequestException("Totals don't add up");
            }
        } else {
            BigDecimal mark = examMergeBean.getMark1().add(examMergeBean.getMark2()).add(examMergeBean.getMark3());
            if (mark.equals(examMergeBean.getOutOff())) {
                List<Student> students = studentRepo.findAllByOrderByForms();
                for (Student s : students) {
                    List<ExamRecording> examRecordings = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam(), s);
                    List<ExamRecording> examRecordings1 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam1(), s);
                    List<ExamRecording> examRecordings2 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam2(), s);
                    if (!(examRecordings.isEmpty()) && !(examRecordings1.isEmpty()) && !(examRecordings2.isEmpty())) {
                        for (ExamRecording e : examRecordings) {
                            BigDecimal marks = BigDecimal.valueOf(e.getMark());
                            BigDecimal outOff = BigDecimal.valueOf(e.getOutOff());
                            BigDecimal aggr = marks.divide(outOff, 2, RoundingMode.HALF_UP);
                            BigDecimal t1 = aggr.multiply(examMergeBean.getMark1());
                            for (ExamRecording e1 : examRecordings1) {
                                if (e.getTeacherSubjects().getSubjects() == e1.getTeacherSubjects().getSubjects()) {
                                    BigDecimal marks1 = BigDecimal.valueOf(e1.getMark());
                                    BigDecimal outOff1 = BigDecimal.valueOf(e1.getOutOff());
                                    BigDecimal aggr1 = marks1.divide(outOff1, 2, RoundingMode.HALF_UP);
                                    BigDecimal t2 = aggr1.multiply(examMergeBean.getMark2());
                                    for (ExamRecording e2 : examRecordings2) {
                                        if (e1.getTeacherSubjects().getSubjects() == e2.getTeacherSubjects().getSubjects()) {
                                            BigDecimal marks2 = BigDecimal.valueOf(e2.getMark());
                                            BigDecimal outOff2 = BigDecimal.valueOf(e2.getOutOff());
                                            BigDecimal aggr2 = marks2.divide(outOff2, 2, RoundingMode.HALF_UP);
                                            BigDecimal t3 = aggr2.multiply(examMergeBean.getMark3());
                                            BigDecimal tt = t1.add(t2).add(t3);
                                            BigDecimal total = tt.divide(examMergeBean.getOutOff(), 2, RoundingMode.HALF_UP);
                                            total = total.multiply(BigDecimal.valueOf(100));
                                            double percentage = total.doubleValue();
                                            ExamRecording exam = new ExamRecording();
                                            GradingSystem system = new GradingSystem();
                                            exam.setPercentage(percentage);
                                            if (percentage <= 29) {
                                                system = gradingSysRepo.findByGradeIgnoreCase("E");
                                            } else if (percentage > 29 && percentage <= 34) {
                                                system = gradingSysRepo.findByGradeIgnoreCase("D-");
                                            } else if (percentage > 34 && percentage <= 39) {
                                                system = gradingSysRepo.findByGradeIgnoreCase("D");
                                            } else if (percentage > 39 && percentage <= 44) {
                                                system = gradingSysRepo.findByGradeIgnoreCase("D+");
                                            } else if (percentage > 44 && percentage <= 49) {
                                                system = gradingSysRepo.findByGradeIgnoreCase("C-");
                                            } else if (percentage > 49 && percentage <= 54) {
                                                system = gradingSysRepo.findByGradeIgnoreCase("C");
                                            } else if (percentage > 54 && percentage <= 59) {
                                                system = gradingSysRepo.findByGradeIgnoreCase("C+");
                                            } else if (percentage > 59 && percentage <= 64) {
                                                system = gradingSysRepo.findByGradeIgnoreCase("B-");
                                            } else if (percentage > 64 && percentage <= 69) {
                                                system = gradingSysRepo.findByGradeIgnoreCase("B");
                                            } else if (percentage > 69 && percentage <= 74) {
                                                system = gradingSysRepo.findByGradeIgnoreCase("B+");
                                            } else if (percentage > 74 && percentage <= 79) {
                                                system = gradingSysRepo.findByGradeIgnoreCase("A-");
                                            } else if (percentage > 79 && percentage <= 100) {
                                                system = gradingSysRepo.findByGradeIgnoreCase("A");
                                            } else {
                                                throw new BadRequestException("Invalid marks");
                                            }
                                            exam.setGradingSystem(system);
                                            exam.setTerm(examMergeBean.getTerm());
                                            exam.setYear(examMergeBean.getYear());
                                            exam.setStudent(s);
                                            exam.setTeacherSubjects(e1.getTeacherSubjects());
                                            exam.setExamDate(examMergeBean.getExamRegister().getEffectiveDate());
                                            exam.setExamRegister(examMergeBean.getExamRegister());
                                            exam.setForms(e1.getForms());
                                            exam.setTeachers(e1.getTeachers());
                                            exam.setMark(tt.doubleValue());
                                            exam.setOutOff(examMergeBean.getOutOff().doubleValue());
                                            examRecordingRepo.save(exam);
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            } else {
                throw new BadRequestException("Totals don't add up");
            }
        }
    }

    @Override
    public void mergeFourExams(ExamMergeBean examMergeBean) throws BadRequestException {
        if (examMergeBean.getForms() != null && examMergeBean.getSubjects() == null) {
            BigDecimal mark = examMergeBean.getMark1().add(examMergeBean.getMark2()).add(examMergeBean.getMark3()).add(examMergeBean.getMark4());
            if (mark.equals(examMergeBean.getOutOff())) {
                List<Student> students = studentRepo.findByForms_ClassCode(examMergeBean.getForms().getClassCode());
                if (!(students.isEmpty())) {
                    for (Student s : students) {
                        List<ExamRecording> examRecordings = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam(), s);
                        List<ExamRecording> examRecordings1 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam1(), s);
                        List<ExamRecording> examRecordings2 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam2(), s);
                        List<ExamRecording> examRecordings3 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam3(), s);
                        if (!(examRecordings.isEmpty()) && !(examRecordings1.isEmpty()) && !(examRecordings2.isEmpty()) && !(examRecordings3.isEmpty())) {
                            for (ExamRecording e : examRecordings) {
                                BigDecimal marks = BigDecimal.valueOf(e.getMark());
                                BigDecimal outOff = BigDecimal.valueOf(e.getOutOff());
                                BigDecimal aggr = marks.divide(outOff, 2, RoundingMode.HALF_UP);
                                BigDecimal t1 = aggr.multiply(examMergeBean.getMark1());
                                for (ExamRecording e1 : examRecordings1) {
                                    if (e.getTeacherSubjects().getSubjects() == e1.getTeacherSubjects().getSubjects()) {
                                        BigDecimal marks1 = BigDecimal.valueOf(e1.getMark());
                                        BigDecimal outOff1 = BigDecimal.valueOf(e1.getOutOff());
                                        BigDecimal aggr1 = marks1.divide(outOff1, 2, RoundingMode.HALF_UP);
                                        BigDecimal t2 = aggr1.multiply(examMergeBean.getMark2());
                                        for (ExamRecording e2 : examRecordings2) {
                                            if (e1.getTeacherSubjects().getSubjects()
                                                    == e2.getTeacherSubjects().getSubjects()) {
                                                BigDecimal marks2 = BigDecimal.valueOf(e2.getMark());
                                                BigDecimal outOff2 = BigDecimal.valueOf(e2.getOutOff());
                                                BigDecimal aggr2 = marks2.divide(outOff2, 2, RoundingMode.HALF_UP);
                                                BigDecimal t3 = aggr2.multiply(examMergeBean.getMark3());
                                                for (ExamRecording e3 : examRecordings3) {
                                                    if (e2.getTeacherSubjects().getSubjects()
                                                            == e3.getTeacherSubjects().getSubjects()) {
                                                        BigDecimal marks3 = BigDecimal.valueOf(e3.getMark());
                                                        BigDecimal outOff3 = BigDecimal.valueOf(e3.getOutOff());
                                                        BigDecimal aggr3 = marks3.divide(outOff3, 2, RoundingMode.HALF_UP);
                                                        BigDecimal t4 = aggr3.multiply(examMergeBean.getMark4());
                                                        BigDecimal tt = t1.add(t2).add(t3).add(t4);
                                                        BigDecimal total = tt.divide(examMergeBean.getOutOff(), 2, RoundingMode.HALF_UP);
                                                        total = total.multiply(BigDecimal.valueOf(100));
                                                        double percentage = total.doubleValue();
                                                        ExamRecording exam = new ExamRecording();
                                                        GradingSystem system = new GradingSystem();
                                                        exam.setPercentage(percentage);
                                                        if (percentage <= 29) {
                                                            system = gradingSysRepo.findByGradeIgnoreCase("E");
                                                        } else if (percentage > 29 && percentage <= 34) {
                                                            system = gradingSysRepo.findByGradeIgnoreCase("D-");
                                                        } else if (percentage > 34 && percentage <= 39) {
                                                            system = gradingSysRepo.findByGradeIgnoreCase("D");
                                                        } else if (percentage > 39 && percentage <= 44) {
                                                            system = gradingSysRepo.findByGradeIgnoreCase("D+");
                                                        } else if (percentage > 44 && percentage <= 49) {
                                                            system = gradingSysRepo.findByGradeIgnoreCase("C-");
                                                        } else if (percentage > 49 && percentage <= 54) {
                                                            system = gradingSysRepo.findByGradeIgnoreCase("C");
                                                        } else if (percentage > 54 && percentage <= 59) {
                                                            system = gradingSysRepo.findByGradeIgnoreCase("C+");
                                                        } else if (percentage > 59 && percentage <= 64) {
                                                            system = gradingSysRepo.findByGradeIgnoreCase("B-");
                                                        } else if (percentage > 64 && percentage <= 69) {
                                                            system = gradingSysRepo.findByGradeIgnoreCase("B");
                                                        } else if (percentage > 69 && percentage <= 74) {
                                                            system = gradingSysRepo.findByGradeIgnoreCase("B+");
                                                        } else if (percentage > 74 && percentage <= 79) {
                                                            system = gradingSysRepo.findByGradeIgnoreCase("A-");
                                                        } else if (percentage > 79 && percentage <= 100) {
                                                            system = gradingSysRepo.findByGradeIgnoreCase("A");
                                                        } else {
                                                            throw new BadRequestException("Invalid marks");
                                                        }
                                                        exam.setTerm(examMergeBean.getTerm());
                                                        exam.setYear(examMergeBean.getYear());
                                                        exam.setStudent(s);
                                                        exam.setTeacherSubjects(e1.getTeacherSubjects());
                                                        exam.setExamDate(examMergeBean.getExamRegister().getEffectiveDate());
                                                        exam.setExamRegister(examMergeBean.getExamRegister());
                                                        exam.setForms(e3.getForms());
                                                        exam.setTeachers(e3.getTeachers());
                                                        exam.setMark(tt.doubleValue());
                                                        exam.setGradingSystem(system);
                                                        exam.setOutOff(examMergeBean.getOutOff().doubleValue());
                                                        examRecordingRepo.save(exam);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            } else {
                throw new BadRequestException("Totals don't add up");
            }
        } else if (examMergeBean.getSubjects() != null && examMergeBean.getForms() == null) {
            BigDecimal mark = examMergeBean.getMark1().add(examMergeBean.getMark2()).add(examMergeBean.getMark3()).add(examMergeBean.getMark4());
            if (mark.equals(examMergeBean.getOutOff())) {
                List<ExamRecording> examRecordings = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam(), examMergeBean.getSubjects());
                List<ExamRecording> examRecordings1 = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam1(), examMergeBean.getSubjects());
                List<ExamRecording> examRecordings2 = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam2(), examMergeBean.getSubjects());
                List<ExamRecording> examRecordings3 = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam3(), examMergeBean.getSubjects());

                if (!(examRecordings.isEmpty()) && !(examRecordings1.isEmpty()) && !(examRecordings2.isEmpty()) && !(examRecordings3.isEmpty())) {
                    for (ExamRecording e : examRecordings) {
                        BigDecimal marks = BigDecimal.valueOf(e.getMark());
                        BigDecimal outOff = BigDecimal.valueOf(e.getOutOff());
                        BigDecimal aggr = marks.divide(outOff, 2, RoundingMode.HALF_UP);
                        BigDecimal t1 = aggr.multiply(examMergeBean.getMark1());
                        for (ExamRecording e1 : examRecordings1) {
                            if (e.getTeacherSubjects().getSubjects() == e1.getTeacherSubjects().getSubjects() && e.getStudent()
                                    == e1.getStudent()) {
                                BigDecimal marks1 = BigDecimal.valueOf(e1.getMark());
                                BigDecimal outOff1 = BigDecimal.valueOf(e1.getOutOff());
                                BigDecimal aggr1 = marks1.divide(outOff1, 2, RoundingMode.HALF_UP);
                                BigDecimal t2 = aggr1.multiply(examMergeBean.getMark2());
                                for (ExamRecording e2 : examRecordings2) {

                                    if (e1.getTeacherSubjects().getSubjects() == e2.getTeacherSubjects().getSubjects() && e1.getStudent()
                                            == e2.getStudent()) {
                                        BigDecimal marks2 = BigDecimal.valueOf(e2.getMark());
                                        BigDecimal outOff2 = BigDecimal.valueOf(e2.getOutOff());
                                        BigDecimal aggr2 = marks2.divide(outOff2, 2, RoundingMode.HALF_UP);
                                        BigDecimal t3 = aggr2.multiply(examMergeBean.getMark3());
                                        for (ExamRecording e3 : examRecordings3) {
                                            if (e2.getTeacherSubjects().getSubjects() == e3.getTeacherSubjects().getSubjects() && e2.getStudent()
                                                    == e3.getStudent()) {
                                                BigDecimal marks3 = BigDecimal.valueOf(e3.getMark());
                                                BigDecimal outOff3 = BigDecimal.valueOf(e3.getOutOff());
                                                BigDecimal aggr3 = marks3.divide(outOff3, 2, RoundingMode.HALF_UP);
                                                BigDecimal t4 = aggr3.multiply(examMergeBean.getMark4());
                                                BigDecimal tt = t1.add(t2).add(t3).add(t4);
                                                BigDecimal total = tt.divide(examMergeBean.getOutOff(), 2, RoundingMode.HALF_UP);
                                                total = total.multiply(BigDecimal.valueOf(100));
                                                double percentage = total.doubleValue();
                                                ExamRecording exam = new ExamRecording();
                                                GradingSystem system = new GradingSystem();
                                                exam.setPercentage(percentage);
                                                if (percentage <= 29) {
                                                    system = gradingSysRepo.findByGradeIgnoreCase("E");
                                                } else if (percentage > 29 && percentage <= 34) {
                                                    system = gradingSysRepo.findByGradeIgnoreCase("D-");
                                                } else if (percentage > 34 && percentage <= 39) {
                                                    system = gradingSysRepo.findByGradeIgnoreCase("D");
                                                } else if (percentage > 39 && percentage <= 44) {
                                                    system = gradingSysRepo.findByGradeIgnoreCase("D+");
                                                } else if (percentage > 44 && percentage <= 49) {
                                                    system = gradingSysRepo.findByGradeIgnoreCase("C-");
                                                } else if (percentage > 49 && percentage <= 54) {
                                                    system = gradingSysRepo.findByGradeIgnoreCase("C");
                                                } else if (percentage > 54 && percentage <= 59) {
                                                    system = gradingSysRepo.findByGradeIgnoreCase("C+");
                                                } else if (percentage > 59 && percentage <= 64) {
                                                    system = gradingSysRepo.findByGradeIgnoreCase("B-");
                                                } else if (percentage > 64 && percentage <= 69) {
                                                    system = gradingSysRepo.findByGradeIgnoreCase("B");
                                                } else if (percentage > 69 && percentage <= 74) {
                                                    system = gradingSysRepo.findByGradeIgnoreCase("B+");
                                                } else if (percentage > 74 && percentage <= 79) {
                                                    system = gradingSysRepo.findByGradeIgnoreCase("A-");
                                                } else if (percentage > 79 && percentage <= 100) {
                                                    system = gradingSysRepo.findByGradeIgnoreCase("A");
                                                } else {
                                                    throw new BadRequestException("Invalid marks");
                                                }
                                                exam.setTerm(examMergeBean.getTerm());
                                                exam.setYear(examMergeBean.getYear());
                                                exam.setStudent(e3.getStudent());
                                                exam.setTeacherSubjects(e3.getTeacherSubjects());
                                                exam.setExamDate(examMergeBean.getExamRegister().getEffectiveDate());
                                                exam.setExamRegister(examMergeBean.getExamRegister());
                                                exam.setForms(e3.getForms());
                                                exam.setTeachers(e3.getTeachers());
                                                exam.setMark(tt.doubleValue());
                                                exam.setGradingSystem(system);
                                                exam.setOutOff(examMergeBean.getOutOff().doubleValue());
                                                examRecordingRepo.save(exam);
                                            }
                                        }
                                    }
                                }
                            }
                        }

                    }

                }
            } else {
                throw new BadRequestException("Totals don't add up");
            }
        } else if (examMergeBean.getSubjects() != null && examMergeBean.getForms() != null) {
            BigDecimal mark = examMergeBean.getMark1().add(examMergeBean.getMark2()).add(examMergeBean.getMark3()).add(examMergeBean.getMark4());
            if (mark.equals(examMergeBean.getOutOff())) {
                List<Student> students = studentRepo.findByForms_ClassCode(examMergeBean.getForms().getClassCode());
                if (!(students.isEmpty())) {
                    for (Student s : students) {
                        List<ExamRecording> examRecordings = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam(), s, examMergeBean.getSubjects());
                        List<ExamRecording> examRecordings1 = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam1(), s, examMergeBean.getSubjects());
                        List<ExamRecording> examRecordings2 = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam2(), s, examMergeBean.getSubjects());
                        List<ExamRecording> examRecordings3 = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam3(), s, examMergeBean.getSubjects());

                        if (!(examRecordings.isEmpty()) && !(examRecordings1.isEmpty()) && !(examRecordings2.isEmpty()) && !(examRecordings3.isEmpty())) {
                            for (ExamRecording e : examRecordings) {
                                BigDecimal marks = BigDecimal.valueOf(e.getMark());
                                BigDecimal outOff = BigDecimal.valueOf(e.getOutOff());
                                BigDecimal aggr = marks.divide(outOff, 2, RoundingMode.HALF_UP);
                                BigDecimal t1 = aggr.multiply(examMergeBean.getMark1());

                                for (ExamRecording e1 : examRecordings1) {
                                    if (e.getTeacherSubjects().getSubjects() == e1.getTeacherSubjects().getSubjects() && e.getStudent() == e1.getStudent()) {
                                        BigDecimal marks1 = BigDecimal.valueOf(e1.getMark());
                                        BigDecimal outOff1 = BigDecimal.valueOf(e1.getOutOff());
                                        BigDecimal aggr1 = marks1.divide(outOff1, 2, RoundingMode.HALF_UP);
                                        BigDecimal t2 = aggr1.multiply(examMergeBean.getMark2());
                                        for (ExamRecording e2 : examRecordings2) {
                                            if (e1.getTeacherSubjects().getSubjects() == e2.getTeacherSubjects().getSubjects() && e1.getStudent() == e2.getStudent()) {
                                                BigDecimal marks2 = BigDecimal.valueOf(e2.getMark());
                                                BigDecimal outOff2 = BigDecimal.valueOf(e2.getOutOff());
                                                BigDecimal aggr2 = marks2.divide(outOff2, 2, RoundingMode.HALF_UP);
                                                BigDecimal t3 = aggr2.multiply(examMergeBean.getMark3());
                                                for (ExamRecording e3 : examRecordings3) {
                                                    if (e2.getTeacherSubjects().getSubjects() == e3.getTeacherSubjects().getSubjects() && e2.getStudent() == e3.getStudent()) {
                                                        BigDecimal marks3 = BigDecimal.valueOf(e3.getMark());
                                                        BigDecimal outOff3 = BigDecimal.valueOf(e3.getOutOff());
                                                        BigDecimal aggr3 = marks3.divide(outOff3, 2, RoundingMode.HALF_UP);
                                                        BigDecimal t4 = aggr3.multiply(examMergeBean.getMark4());
                                                        BigDecimal tt = t1.add(t2).add(t3).add(t4);
                                                        BigDecimal total = tt.divide(examMergeBean.getOutOff(), 2, RoundingMode.HALF_UP);
                                                        total = total.multiply(BigDecimal.valueOf(100));
                                                        double percentage = total.doubleValue();
                                                        ExamRecording exam = new ExamRecording();
                                                        GradingSystem system = new GradingSystem();
                                                        exam.setPercentage(percentage);
                                                        if (percentage <= 29) {
                                                            system = gradingSysRepo.findByGradeIgnoreCase("E");
                                                        } else if (percentage > 29 && percentage <= 34) {
                                                            system = gradingSysRepo.findByGradeIgnoreCase("D-");
                                                        } else if (percentage > 34 && percentage <= 39) {
                                                            system = gradingSysRepo.findByGradeIgnoreCase("D");
                                                        } else if (percentage > 39 && percentage <= 44) {
                                                            system = gradingSysRepo.findByGradeIgnoreCase("D+");
                                                        } else if (percentage > 44 && percentage <= 49) {
                                                            system = gradingSysRepo.findByGradeIgnoreCase("C-");
                                                        } else if (percentage > 49 && percentage <= 54) {
                                                            system = gradingSysRepo.findByGradeIgnoreCase("C");
                                                        } else if (percentage > 54 && percentage <= 59) {
                                                            system = gradingSysRepo.findByGradeIgnoreCase("C+");
                                                        } else if (percentage > 59 && percentage <= 64) {
                                                            system = gradingSysRepo.findByGradeIgnoreCase("B-");
                                                        } else if (percentage > 64 && percentage <= 69) {
                                                            system = gradingSysRepo.findByGradeIgnoreCase("B");
                                                        } else if (percentage > 69 && percentage <= 74) {
                                                            system = gradingSysRepo.findByGradeIgnoreCase("B+");
                                                        } else if (percentage > 74 && percentage <= 79) {
                                                            system = gradingSysRepo.findByGradeIgnoreCase("A-");
                                                        } else if (percentage > 79 && percentage <= 100) {
                                                            system = gradingSysRepo.findByGradeIgnoreCase("A");
                                                        } else {
                                                            throw new BadRequestException("Invalid marks");
                                                        }
                                                        exam.setGradingSystem(system);
                                                        exam.setTerm(examMergeBean.getTerm());
                                                        exam.setYear(examMergeBean.getYear());
                                                        exam.setStudent(s);
                                                        exam.setTeacherSubjects(e1.getTeacherSubjects());
                                                        exam.setExamDate(examMergeBean.getExamRegister().getEffectiveDate());
                                                        exam.setExamRegister(examMergeBean.getExamRegister());
                                                        exam.setForms(e3.getForms());
                                                        exam.setTeachers(e3.getTeachers());
                                                        exam.setMark(tt.doubleValue());
                                                        exam.setOutOff(examMergeBean.getOutOff().doubleValue());
                                                        examRecordingRepo.save(exam);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            } else {
                throw new BadRequestException("Totals don't add up");
            }
        } else {
            BigDecimal mark = examMergeBean.getMark1().add(examMergeBean.getMark2()).add(examMergeBean.getMark3()).add(examMergeBean.getMark4());
            if (mark.equals(examMergeBean.getOutOff())) {
                List<Student> students = studentRepo.findAllByOrderByForms();
                for (Student s : students) {
                    List<ExamRecording> examRecordings = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam(), s);
                    List<ExamRecording> examRecordings1 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam1(), s);
                    List<ExamRecording> examRecordings2 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam2(), s);
                    List<ExamRecording> examRecordings3 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam3(), s);

                    if (!(examRecordings.isEmpty()) && !(examRecordings1.isEmpty()) && !(examRecordings2.isEmpty()) && !(examRecordings3.isEmpty())) {
                        for (ExamRecording e : examRecordings) {
                            BigDecimal marks = BigDecimal.valueOf(e.getMark());
                            BigDecimal outOff = BigDecimal.valueOf(e.getOutOff());
                            BigDecimal aggr = marks.divide(outOff, 2, RoundingMode.HALF_UP);
                            BigDecimal t1 = aggr.multiply(examMergeBean.getMark1());
                            for (ExamRecording e1 : examRecordings1) {
                                if (e.getTeacherSubjects().getSubjects() == e1.getTeacherSubjects().getSubjects()) {
                                    BigDecimal marks1 = BigDecimal.valueOf(e1.getMark());
                                    BigDecimal outOff1 = BigDecimal.valueOf(e1.getOutOff());
                                    BigDecimal aggr1 = marks1.divide(outOff1, 2, RoundingMode.HALF_UP);
                                    BigDecimal t2 = aggr1.multiply(examMergeBean.getMark2());
                                    for (ExamRecording e2 : examRecordings2) {
                                        if (e1.getTeacherSubjects().getSubjects() == e2.getTeacherSubjects().getSubjects()) {
                                            BigDecimal marks2 = BigDecimal.valueOf(e2.getMark());
                                            BigDecimal outOff2 = BigDecimal.valueOf(e2.getOutOff());
                                            BigDecimal aggr2 = marks2.divide(outOff2, 2, RoundingMode.HALF_UP);
                                            BigDecimal t3 = aggr2.multiply(examMergeBean.getMark3());
                                            for (ExamRecording e3 : examRecordings3) {
                                                if (e2.getTeacherSubjects().getSubjects() == e3.getTeacherSubjects().getSubjects()) {
                                                    BigDecimal marks3 = BigDecimal.valueOf(e3.getMark());
                                                    BigDecimal outOff3 = BigDecimal.valueOf(e3.getOutOff());
                                                    BigDecimal aggr3 = marks3.divide(outOff3, 2, RoundingMode.HALF_UP);
                                                    BigDecimal t4 = aggr3.multiply(examMergeBean.getMark4());
                                                    BigDecimal tt = t1.add(t2).add(t3).add(t4);
                                                    BigDecimal total = tt.divide(examMergeBean.getOutOff(), 2, RoundingMode.HALF_UP);
                                                    total = total.multiply(BigDecimal.valueOf(100));
                                                    double percentage = total.doubleValue();
                                                    ExamRecording exam = new ExamRecording();
                                                    GradingSystem system = new GradingSystem();
                                                    exam.setPercentage(percentage);
                                                    if (percentage <= 29) {
                                                        system = gradingSysRepo.findByGradeIgnoreCase("E");
                                                    } else if (percentage > 29 && percentage <= 34) {
                                                        system = gradingSysRepo.findByGradeIgnoreCase("D-");
                                                    } else if (percentage > 34 && percentage <= 39) {
                                                        system = gradingSysRepo.findByGradeIgnoreCase("D");
                                                    } else if (percentage > 39 && percentage <= 44) {
                                                        system = gradingSysRepo.findByGradeIgnoreCase("D+");
                                                    } else if (percentage > 44 && percentage <= 49) {
                                                        system = gradingSysRepo.findByGradeIgnoreCase("C-");
                                                    } else if (percentage > 49 && percentage <= 54) {
                                                        system = gradingSysRepo.findByGradeIgnoreCase("C");
                                                    } else if (percentage > 54 && percentage <= 59) {
                                                        system = gradingSysRepo.findByGradeIgnoreCase("C+");
                                                    } else if (percentage > 59 && percentage <= 64) {
                                                        system = gradingSysRepo.findByGradeIgnoreCase("B-");
                                                    } else if (percentage > 64 && percentage <= 69) {
                                                        system = gradingSysRepo.findByGradeIgnoreCase("B");
                                                    } else if (percentage > 69 && percentage <= 74) {
                                                        system = gradingSysRepo.findByGradeIgnoreCase("B+");
                                                    } else if (percentage > 74 && percentage <= 79) {
                                                        system = gradingSysRepo.findByGradeIgnoreCase("A-");
                                                    } else if (percentage > 79 && percentage <= 100) {
                                                        system = gradingSysRepo.findByGradeIgnoreCase("A");
                                                    } else {
                                                        throw new BadRequestException("Invalid marks");
                                                    }
                                                    exam.setGradingSystem(system);
                                                    exam.setTerm(examMergeBean.getTerm());
                                                    exam.setYear(examMergeBean.getYear());
                                                    exam.setStudent(s);
                                                    exam.setTeacherSubjects(e3.getTeacherSubjects());
                                                    exam.setExamDate(examMergeBean.getExamRegister().getEffectiveDate());
                                                    exam.setExamRegister(examMergeBean.getExamRegister());
                                                    exam.setForms(e3.getForms());
                                                    exam.setTeachers(e3.getTeachers());
                                                    exam.setMark(tt.doubleValue());
                                                    exam.setOutOff(examMergeBean.getOutOff().doubleValue());
                                                    examRecordingRepo.save(exam);
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            } else {
                throw new BadRequestException("Totals don't add up");
            }
        }

    }

    @Override
    public void mergeFiveExams(ExamMergeBean examMergeBean) throws BadRequestException {
        if (examMergeBean.getForms() != null && examMergeBean.getSubjects() == null) {
            BigDecimal mark = examMergeBean.getMark1().add(examMergeBean.getMark2()).add(examMergeBean.getMark3()).add(examMergeBean.getMark4()).add(examMergeBean.getMark5());
            if (mark.equals(examMergeBean.getOutOff())) {
                List<Student> students = studentRepo.findByForms_ClassCode(examMergeBean.getForms().getClassCode());
                if (!(students.isEmpty())) {
                    for (Student s : students) {
                        List<ExamRecording> examRecordings = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam(), s);
                        List<ExamRecording> examRecordings1 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam1(), s);
                        List<ExamRecording> examRecordings2 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam2(), s);
                        List<ExamRecording> examRecordings3 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam3(), s);
                        List<ExamRecording> examRecordings4 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam4(), s);

                        if (!(examRecordings.isEmpty()) && !(examRecordings1.isEmpty()) && !(examRecordings2.isEmpty()) && !(examRecordings3.isEmpty()) && !(examRecordings4.isEmpty())) {
                            for (ExamRecording e : examRecordings) {
                                BigDecimal marks = BigDecimal.valueOf(e.getMark());
                                BigDecimal outOff = BigDecimal.valueOf(e.getOutOff());
                                BigDecimal aggr = marks.divide(outOff, 2, RoundingMode.HALF_UP);
                                BigDecimal t1 = aggr.multiply(examMergeBean.getMark1());
                                for (ExamRecording e1 : examRecordings1) {
                                    if (e.getTeacherSubjects().getSubjects() == e1.getTeacherSubjects().getSubjects()) {
                                        BigDecimal marks1 = BigDecimal.valueOf(e1.getMark());
                                        BigDecimal outOff1 = BigDecimal.valueOf(e1.getOutOff());
                                        BigDecimal aggr1 = marks1.divide(outOff1, 2, RoundingMode.HALF_UP);
                                        BigDecimal t2 = aggr1.multiply(examMergeBean.getMark2());
                                        for (ExamRecording e2 : examRecordings2) {
                                            if (e1.getTeacherSubjects().getSubjects()
                                                    == e2.getTeacherSubjects().getSubjects()) {
                                                BigDecimal marks2 = BigDecimal.valueOf(e2.getMark());
                                                BigDecimal outOff2 = BigDecimal.valueOf(e2.getOutOff());
                                                BigDecimal aggr2 = marks2.divide(outOff2, 2, RoundingMode.HALF_UP);
                                                BigDecimal t3 = aggr2.multiply(examMergeBean.getMark3());
                                                for (ExamRecording e3 : examRecordings3) {
                                                    if (e2.getTeacherSubjects().getSubjects()
                                                            == e3.getTeacherSubjects().getSubjects()) {
                                                        BigDecimal marks3 = BigDecimal.valueOf(e3.getMark());
                                                        BigDecimal outOff3 = BigDecimal.valueOf(e3.getOutOff());
                                                        BigDecimal aggr3 = marks3.divide(outOff3, 2, RoundingMode.HALF_UP);
                                                        BigDecimal t4 = aggr3.multiply(examMergeBean.getMark4());
                                                        for (ExamRecording e4 : examRecordings4) {
                                                            if (e3.getTeacherSubjects().getSubjects()
                                                                    == e4.getTeacherSubjects().getSubjects()) {

                                                                BigDecimal marks4 = BigDecimal.valueOf(e4.getMark());
                                                                BigDecimal outOff4 = BigDecimal.valueOf(e4.getOutOff());
                                                                BigDecimal aggr4 = marks4.divide(outOff4, 2, RoundingMode.HALF_UP);
                                                                BigDecimal t5 = aggr4.multiply(examMergeBean.getMark5());
                                                                BigDecimal tt = t1.add(t2).add(t3).add(t4).add(t5);
                                                                BigDecimal total = tt.divide(examMergeBean.getOutOff(), 2, RoundingMode.HALF_UP);
                                                                total = total.multiply(BigDecimal.valueOf(100));
                                                                double percentage = total.doubleValue();
                                                                ExamRecording exam = new ExamRecording();
                                                                GradingSystem system = new GradingSystem();
                                                                exam.setPercentage(percentage);
                                                                if (percentage <= 29) {
                                                                    system = gradingSysRepo.findByGradeIgnoreCase("E");
                                                                } else if (percentage > 29 && percentage <= 34) {
                                                                    system = gradingSysRepo.findByGradeIgnoreCase("D-");
                                                                } else if (percentage > 34 && percentage <= 39) {
                                                                    system = gradingSysRepo.findByGradeIgnoreCase("D");
                                                                } else if (percentage > 39 && percentage <= 44) {
                                                                    system = gradingSysRepo.findByGradeIgnoreCase("D+");
                                                                } else if (percentage > 44 && percentage <= 49) {
                                                                    system = gradingSysRepo.findByGradeIgnoreCase("C-");
                                                                } else if (percentage > 49 && percentage <= 54) {
                                                                    system = gradingSysRepo.findByGradeIgnoreCase("C");
                                                                } else if (percentage > 54 && percentage <= 59) {
                                                                    system = gradingSysRepo.findByGradeIgnoreCase("C+");
                                                                } else if (percentage > 59 && percentage <= 64) {
                                                                    system = gradingSysRepo.findByGradeIgnoreCase("B-");
                                                                } else if (percentage > 64 && percentage <= 69) {
                                                                    system = gradingSysRepo.findByGradeIgnoreCase("B");
                                                                } else if (percentage > 69 && percentage <= 74) {
                                                                    system = gradingSysRepo.findByGradeIgnoreCase("B+");
                                                                } else if (percentage > 74 && percentage <= 79) {
                                                                    system = gradingSysRepo.findByGradeIgnoreCase("A-");
                                                                } else if (percentage > 79 && percentage <= 100) {
                                                                    system = gradingSysRepo.findByGradeIgnoreCase("A");
                                                                } else {
                                                                    throw new BadRequestException("Invalid marks");
                                                                }
                                                                exam.setTerm(examMergeBean.getTerm());
                                                                exam.setYear(examMergeBean.getYear());
                                                                exam.setStudent(s);
                                                                exam.setTeacherSubjects(e4.getTeacherSubjects());
                                                                exam.setExamDate(examMergeBean.getExamRegister().getEffectiveDate());
                                                                exam.setExamRegister(examMergeBean.getExamRegister());
                                                                exam.setForms(e4.getForms());
                                                                exam.setTeachers(e4.getTeachers());
                                                                exam.setMark(tt.doubleValue());
                                                                exam.setGradingSystem(system);
                                                                exam.setOutOff(examMergeBean.getOutOff().doubleValue());
                                                                examRecordingRepo.save(exam);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            } else {
                throw new BadRequestException("Totals don't add up");
            }
        } else if (examMergeBean.getSubjects() != null && examMergeBean.getForms() == null) {
            BigDecimal mark = examMergeBean.getMark1().add(examMergeBean.getMark2()).add(examMergeBean.getMark3()).add(examMergeBean.getMark4()).add(examMergeBean.getMark5());
            if (mark.equals(examMergeBean.getOutOff())) {
                List<ExamRecording> examRecordings = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam(), examMergeBean.getSubjects());
                List<ExamRecording> examRecordings1 = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam1(), examMergeBean.getSubjects());
                List<ExamRecording> examRecordings2 = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam2(), examMergeBean.getSubjects());
                List<ExamRecording> examRecordings3 = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam3(), examMergeBean.getSubjects());
                List<ExamRecording> examRecordings4 = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam4(), examMergeBean.getSubjects());

                if (!(examRecordings.isEmpty()) && !(examRecordings1.isEmpty()) && !(examRecordings2.isEmpty()) && !(examRecordings3.isEmpty()) && !(examRecordings4.isEmpty())) {
                    for (ExamRecording e : examRecordings) {
                        BigDecimal marks = BigDecimal.valueOf(e.getMark());
                        BigDecimal outOff = BigDecimal.valueOf(e.getOutOff());
                        BigDecimal aggr = marks.divide(outOff, 2, RoundingMode.HALF_UP);
                        BigDecimal t1 = aggr.multiply(examMergeBean.getMark1());
                        for (ExamRecording e1 : examRecordings1) {
                            if (e.getTeacherSubjects().getSubjects() == e1.getTeacherSubjects().getSubjects() && e.getStudent()
                                    == e1.getStudent()) {
                                BigDecimal marks1 = BigDecimal.valueOf(e1.getMark());
                                BigDecimal outOff1 = BigDecimal.valueOf(e1.getOutOff());
                                BigDecimal aggr1 = marks1.divide(outOff1, 2, RoundingMode.HALF_UP);
                                BigDecimal t2 = aggr1.multiply(examMergeBean.getMark2());
                                for (ExamRecording e2 : examRecordings2) {

                                    if (e1.getTeacherSubjects().getSubjects() == e2.getTeacherSubjects().getSubjects() && e1.getStudent()
                                            == e2.getStudent()) {
                                        BigDecimal marks2 = BigDecimal.valueOf(e2.getMark());
                                        BigDecimal outOff2 = BigDecimal.valueOf(e2.getOutOff());
                                        BigDecimal aggr2 = marks2.divide(outOff2, 2, RoundingMode.HALF_UP);
                                        BigDecimal t3 = aggr2.multiply(examMergeBean.getMark3());
                                        for (ExamRecording e3 : examRecordings3) {
                                            if (e2.getTeacherSubjects().getSubjects() == e3.getTeacherSubjects().getSubjects() && e2.getStudent()
                                                    == e3.getStudent()) {
                                                BigDecimal marks3 = BigDecimal.valueOf(e3.getMark());
                                                BigDecimal outOff3 = BigDecimal.valueOf(e3.getOutOff());
                                                BigDecimal aggr3 = marks3.divide(outOff3, 2, RoundingMode.HALF_UP);
                                                BigDecimal t4 = aggr3.multiply(examMergeBean.getMark4());
                                                for (ExamRecording e4 : examRecordings4) {
                                                    if (e3.getTeacherSubjects().getSubjects() == e4.getTeacherSubjects().getSubjects() && e3.getStudent()
                                                            == e4.getStudent()) {
                                                        BigDecimal marks4 = BigDecimal.valueOf(e4.getMark());
                                                        BigDecimal outOff4 = BigDecimal.valueOf(e4.getOutOff());
                                                        BigDecimal aggr4 = marks4.divide(outOff4, 2, RoundingMode.HALF_UP);
                                                        BigDecimal t5 = aggr4.multiply(examMergeBean.getMark5());
                                                        BigDecimal tt = t1.add(t2).add(t3).add(t4).add(t5);
                                                        BigDecimal total = tt.divide(examMergeBean.getOutOff(), 2, RoundingMode.HALF_UP);
                                                        total = total.multiply(BigDecimal.valueOf(100));
                                                        double percentage = total.doubleValue();
                                                        ExamRecording exam = new ExamRecording();
                                                        GradingSystem system = new GradingSystem();
                                                        exam.setPercentage(percentage);
                                                        if (percentage <= 29) {
                                                            system = gradingSysRepo.findByGradeIgnoreCase("E");
                                                        } else if (percentage > 29 && percentage <= 34) {
                                                            system = gradingSysRepo.findByGradeIgnoreCase("D-");
                                                        } else if (percentage > 34 && percentage <= 39) {
                                                            system = gradingSysRepo.findByGradeIgnoreCase("D");
                                                        } else if (percentage > 39 && percentage <= 44) {
                                                            system = gradingSysRepo.findByGradeIgnoreCase("D+");
                                                        } else if (percentage > 44 && percentage <= 49) {
                                                            system = gradingSysRepo.findByGradeIgnoreCase("C-");
                                                        } else if (percentage > 49 && percentage <= 54) {
                                                            system = gradingSysRepo.findByGradeIgnoreCase("C");
                                                        } else if (percentage > 54 && percentage <= 59) {
                                                            system = gradingSysRepo.findByGradeIgnoreCase("C+");
                                                        } else if (percentage > 59 && percentage <= 64) {
                                                            system = gradingSysRepo.findByGradeIgnoreCase("B-");
                                                        } else if (percentage > 64 && percentage <= 69) {
                                                            system = gradingSysRepo.findByGradeIgnoreCase("B");
                                                        } else if (percentage > 69 && percentage <= 74) {
                                                            system = gradingSysRepo.findByGradeIgnoreCase("B+");
                                                        } else if (percentage > 74 && percentage <= 79) {
                                                            system = gradingSysRepo.findByGradeIgnoreCase("A-");
                                                        } else if (percentage > 79 && percentage <= 100) {
                                                            system = gradingSysRepo.findByGradeIgnoreCase("A");
                                                        } else {
                                                            throw new BadRequestException("Invalid marks");
                                                        }
                                                        exam.setTerm(examMergeBean.getTerm());
                                                        exam.setYear(examMergeBean.getYear());
                                                        exam.setStudent(e4.getStudent());
                                                        exam.setTeacherSubjects(e4.getTeacherSubjects());
                                                        exam.setExamDate(examMergeBean.getExamRegister().getEffectiveDate());
                                                        exam.setExamRegister(examMergeBean.getExamRegister());
                                                        exam.setForms(e4.getForms());
                                                        exam.setTeachers(e4.getTeachers());
                                                        exam.setMark(tt.doubleValue());
                                                        exam.setGradingSystem(system);
                                                        exam.setOutOff(examMergeBean.getOutOff().doubleValue());
                                                        examRecordingRepo.save(exam);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                    }

                }
            } else {
                throw new BadRequestException("Totals don't add up");
            }
        } else if (examMergeBean.getSubjects() != null && examMergeBean.getForms() != null) {
            BigDecimal mark = examMergeBean.getMark1().add(examMergeBean.getMark2()).add(examMergeBean.getMark3()).add(examMergeBean.getMark4()).add(examMergeBean.getMark5());
            if (mark.equals(examMergeBean.getOutOff())) {
                List<Student> students = studentRepo.findByForms_ClassCode(examMergeBean.getForms().getClassCode());
                if (!(students.isEmpty())) {
                    for (Student s : students) {
                        List<ExamRecording> examRecordings = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam(), s, examMergeBean.getSubjects());
                        List<ExamRecording> examRecordings1 = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam1(), s, examMergeBean.getSubjects());
                        List<ExamRecording> examRecordings2 = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam2(), s, examMergeBean.getSubjects());
                        List<ExamRecording> examRecordings3 = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam3(), s, examMergeBean.getSubjects());
                        List<ExamRecording> examRecordings4 = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam4(), s, examMergeBean.getSubjects());

                        if (!(examRecordings.isEmpty()) && !(examRecordings1.isEmpty()) && !(examRecordings2.isEmpty()) && !(examRecordings3.isEmpty()) && !(examRecordings4.isEmpty())) {
                            for (ExamRecording e : examRecordings) {
                                BigDecimal marks = BigDecimal.valueOf(e.getMark());
                                BigDecimal outOff = BigDecimal.valueOf(e.getOutOff());
                                BigDecimal aggr = marks.divide(outOff, 2, RoundingMode.HALF_UP);
                                BigDecimal t1 = aggr.multiply(examMergeBean.getMark1());

                                for (ExamRecording e1 : examRecordings1) {
                                    if (e.getTeacherSubjects().getSubjects() == e1.getTeacherSubjects().getSubjects() && e.getStudent() == e1.getStudent()) {
                                        BigDecimal marks1 = BigDecimal.valueOf(e1.getMark());
                                        BigDecimal outOff1 = BigDecimal.valueOf(e1.getOutOff());
                                        BigDecimal aggr1 = marks1.divide(outOff1, 2, RoundingMode.HALF_UP);
                                        BigDecimal t2 = aggr1.multiply(examMergeBean.getMark2());
                                        for (ExamRecording e2 : examRecordings2) {
                                            if (e1.getTeacherSubjects().getSubjects() == e2.getTeacherSubjects().getSubjects() && e1.getStudent() == e2.getStudent()) {
                                                BigDecimal marks2 = BigDecimal.valueOf(e2.getMark());
                                                BigDecimal outOff2 = BigDecimal.valueOf(e2.getOutOff());
                                                BigDecimal aggr2 = marks2.divide(outOff2, 2, RoundingMode.HALF_UP);
                                                BigDecimal t3 = aggr2.multiply(examMergeBean.getMark3());
                                                for (ExamRecording e3 : examRecordings3) {
                                                    if (e2.getTeacherSubjects().getSubjects() == e3.getTeacherSubjects().getSubjects() && e2.getStudent() == e3.getStudent()) {
                                                        BigDecimal marks3 = BigDecimal.valueOf(e3.getMark());
                                                        BigDecimal outOff3 = BigDecimal.valueOf(e3.getOutOff());
                                                        BigDecimal aggr3 = marks3.divide(outOff3, 2, RoundingMode.HALF_UP);
                                                        BigDecimal t4 = aggr3.multiply(examMergeBean.getMark4());
                                                        for (ExamRecording e4 : examRecordings4) {
                                                            if (e3.getTeacherSubjects().getSubjects() == e4.getTeacherSubjects().getSubjects() && e3.getStudent() == e4.getStudent()) {
                                                                BigDecimal marks4 = BigDecimal.valueOf(e4.getMark());
                                                                BigDecimal outOff4 = BigDecimal.valueOf(e4.getOutOff());
                                                                BigDecimal aggr4 = marks4.divide(outOff4, 2, RoundingMode.HALF_UP);
                                                                BigDecimal t5 = aggr4.multiply(examMergeBean.getMark5());
                                                                BigDecimal tt = t1.add(t2).add(t3).add(t4).add(t5);
                                                                BigDecimal total = tt.divide(examMergeBean.getOutOff(), 2, RoundingMode.HALF_UP);
                                                                total = total.multiply(BigDecimal.valueOf(100));
                                                                double percentage = total.doubleValue();
                                                                ExamRecording exam = new ExamRecording();
                                                                GradingSystem system = new GradingSystem();
                                                                exam.setPercentage(percentage);
                                                                if (percentage <= 29) {
                                                                    system = gradingSysRepo.findByGradeIgnoreCase("E");
                                                                } else if (percentage > 29 && percentage <= 34) {
                                                                    system = gradingSysRepo.findByGradeIgnoreCase("D-");
                                                                } else if (percentage > 34 && percentage <= 39) {
                                                                    system = gradingSysRepo.findByGradeIgnoreCase("D");
                                                                } else if (percentage > 39 && percentage <= 44) {
                                                                    system = gradingSysRepo.findByGradeIgnoreCase("D+");
                                                                } else if (percentage > 44 && percentage <= 49) {
                                                                    system = gradingSysRepo.findByGradeIgnoreCase("C-");
                                                                } else if (percentage > 49 && percentage <= 54) {
                                                                    system = gradingSysRepo.findByGradeIgnoreCase("C");
                                                                } else if (percentage > 54 && percentage <= 59) {
                                                                    system = gradingSysRepo.findByGradeIgnoreCase("C+");
                                                                } else if (percentage > 59 && percentage <= 64) {
                                                                    system = gradingSysRepo.findByGradeIgnoreCase("B-");
                                                                } else if (percentage > 64 && percentage <= 69) {
                                                                    system = gradingSysRepo.findByGradeIgnoreCase("B");
                                                                } else if (percentage > 69 && percentage <= 74) {
                                                                    system = gradingSysRepo.findByGradeIgnoreCase("B+");
                                                                } else if (percentage > 74 && percentage <= 79) {
                                                                    system = gradingSysRepo.findByGradeIgnoreCase("A-");
                                                                } else if (percentage > 79 && percentage <= 100) {
                                                                    system = gradingSysRepo.findByGradeIgnoreCase("A");
                                                                } else {
                                                                    throw new BadRequestException("Invalid marks");
                                                                }
                                                                exam.setGradingSystem(system);
                                                                exam.setTerm(examMergeBean.getTerm());
                                                                exam.setYear(examMergeBean.getYear());
                                                                exam.setStudent(s);
                                                                exam.setTeacherSubjects(e1.getTeacherSubjects());
                                                                exam.setExamDate(examMergeBean.getExamRegister().getEffectiveDate());
                                                                exam.setExamRegister(examMergeBean.getExamRegister());
                                                                exam.setForms(e4.getForms());
                                                                exam.setTeachers(e4.getTeachers());
                                                                exam.setMark(tt.doubleValue());
                                                                exam.setOutOff(examMergeBean.getOutOff().doubleValue());
                                                                examRecordingRepo.save(exam);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            } else {
                throw new BadRequestException("Totals don't add up");
            }
        } else {
            BigDecimal mark = examMergeBean.getMark1().add(examMergeBean.getMark2()).add(examMergeBean.getMark3()).add(examMergeBean.getMark4()).add(examMergeBean.getMark5());
            if (mark.equals(examMergeBean.getOutOff())) {
                List<Student> students = studentRepo.findAllByOrderByForms();
                for (Student s : students) {
                    List<ExamRecording> examRecordings = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam(), s);
                    List<ExamRecording> examRecordings1 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam1(), s);
                    List<ExamRecording> examRecordings2 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam2(), s);
                    List<ExamRecording> examRecordings3 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam3(), s);
                    List<ExamRecording> examRecordings4 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam4(), s);

                    if (!(examRecordings.isEmpty()) && !(examRecordings1.isEmpty()) && !(examRecordings2.isEmpty()) && !(examRecordings3.isEmpty()) && !(examRecordings4.isEmpty())) {
                        for (ExamRecording e : examRecordings) {
                            BigDecimal marks = BigDecimal.valueOf(e.getMark());
                            BigDecimal outOff = BigDecimal.valueOf(e.getOutOff());
                            BigDecimal aggr = marks.divide(outOff, 2, RoundingMode.HALF_UP);
                            BigDecimal t1 = aggr.multiply(examMergeBean.getMark1());
                            for (ExamRecording e1 : examRecordings1) {
                                if (e.getTeacherSubjects().getSubjects() == e1.getTeacherSubjects().getSubjects()) {
                                    BigDecimal marks1 = BigDecimal.valueOf(e1.getMark());
                                    BigDecimal outOff1 = BigDecimal.valueOf(e1.getOutOff());
                                    BigDecimal aggr1 = marks1.divide(outOff1, 2, RoundingMode.HALF_UP);
                                    BigDecimal t2 = aggr1.multiply(examMergeBean.getMark2());
                                    for (ExamRecording e2 : examRecordings2) {
                                        if (e1.getTeacherSubjects().getSubjects() == e2.getTeacherSubjects().getSubjects()) {
                                            BigDecimal marks2 = BigDecimal.valueOf(e2.getMark());
                                            BigDecimal outOff2 = BigDecimal.valueOf(e2.getOutOff());
                                            BigDecimal aggr2 = marks2.divide(outOff2, 2, RoundingMode.HALF_UP);
                                            BigDecimal t3 = aggr2.multiply(examMergeBean.getMark3());
                                            for (ExamRecording e3 : examRecordings3) {
                                                if (e2.getTeacherSubjects().getSubjects() == e3.getTeacherSubjects().getSubjects()) {
                                                    BigDecimal marks3 = BigDecimal.valueOf(e3.getMark());
                                                    BigDecimal outOff3 = BigDecimal.valueOf(e3.getOutOff());
                                                    BigDecimal aggr3 = marks3.divide(outOff3, 2, RoundingMode.HALF_UP);
                                                    BigDecimal t4 = aggr3.multiply(examMergeBean.getMark4());
                                                    for (ExamRecording e4 : examRecordings4) {
                                                        if (e3.getTeacherSubjects().getSubjects() == e4.getTeacherSubjects().getSubjects()) {
                                                            BigDecimal marks4 = BigDecimal.valueOf(e4.getMark());
                                                            BigDecimal outOff4 = BigDecimal.valueOf(e4.getOutOff());
                                                            BigDecimal aggr4 = marks4.divide(outOff4, 2, RoundingMode.HALF_UP);
                                                            BigDecimal t5 = aggr4.multiply(examMergeBean.getMark5());
                                                            BigDecimal tt = t1.add(t2).add(t3).add(t4).add(t5);
                                                            BigDecimal total = tt.divide(examMergeBean.getOutOff(), 2, RoundingMode.HALF_UP);
                                                            total = total.multiply(BigDecimal.valueOf(100));
                                                            double percentage = total.doubleValue();
                                                            ExamRecording exam = new ExamRecording();
                                                            GradingSystem system = new GradingSystem();
                                                            exam.setPercentage(percentage);
                                                            if (percentage <= 29) {
                                                                system = gradingSysRepo.findByGradeIgnoreCase("E");
                                                            } else if (percentage > 29 && percentage <= 34) {
                                                                system = gradingSysRepo.findByGradeIgnoreCase("D-");
                                                            } else if (percentage > 34 && percentage <= 39) {
                                                                system = gradingSysRepo.findByGradeIgnoreCase("D");
                                                            } else if (percentage > 39 && percentage <= 44) {
                                                                system = gradingSysRepo.findByGradeIgnoreCase("D+");
                                                            } else if (percentage > 44 && percentage <= 49) {
                                                                system = gradingSysRepo.findByGradeIgnoreCase("C-");
                                                            } else if (percentage > 49 && percentage <= 54) {
                                                                system = gradingSysRepo.findByGradeIgnoreCase("C");
                                                            } else if (percentage > 54 && percentage <= 59) {
                                                                system = gradingSysRepo.findByGradeIgnoreCase("C+");
                                                            } else if (percentage > 59 && percentage <= 64) {
                                                                system = gradingSysRepo.findByGradeIgnoreCase("B-");
                                                            } else if (percentage > 64 && percentage <= 69) {
                                                                system = gradingSysRepo.findByGradeIgnoreCase("B");
                                                            } else if (percentage > 69 && percentage <= 74) {
                                                                system = gradingSysRepo.findByGradeIgnoreCase("B+");
                                                            } else if (percentage > 74 && percentage <= 79) {
                                                                system = gradingSysRepo.findByGradeIgnoreCase("A-");
                                                            } else if (percentage > 79 && percentage <= 100) {
                                                                system = gradingSysRepo.findByGradeIgnoreCase("A");
                                                            } else {
                                                                throw new BadRequestException("Invalid marks");
                                                            }
                                                            exam.setGradingSystem(system);
                                                            exam.setTerm(examMergeBean.getTerm());
                                                            exam.setYear(examMergeBean.getYear());
                                                            exam.setStudent(s);
                                                            exam.setTeacherSubjects(e3.getTeacherSubjects());
                                                            exam.setExamDate(examMergeBean.getExamRegister().getEffectiveDate());
                                                            exam.setExamRegister(examMergeBean.getExamRegister());
                                                            exam.setForms(e4.getForms());
                                                            exam.setTeachers(e4.getTeachers());
                                                            exam.setMark(tt.doubleValue());
                                                            exam.setOutOff(examMergeBean.getOutOff().doubleValue());
                                                            examRecordingRepo.save(exam);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            } else {
                throw new BadRequestException("Totals don't add up");
            }


        }
    }

    @Override
    public void mergeSevenExams(ExamMergeBean examMergeBean) throws BadRequestException {
        if (examMergeBean.getForms() != null && examMergeBean.getSubjects() == null) {
            BigDecimal mark = examMergeBean.getMark1().add(examMergeBean.getMark2()).add(examMergeBean.getMark3()).add(examMergeBean.getMark4()).add(examMergeBean.getMark5()).add(examMergeBean.getMark6()).add(examMergeBean.getMark7());
            if (mark.equals(examMergeBean.getOutOff())) {
                List<Student> students = studentRepo.findByForms_ClassCode(examMergeBean.getForms().getClassCode());
                if (!(students.isEmpty())) {
                    for (Student s : students) {
                        List<ExamRecording> examRecordings = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam(), s);
                        List<ExamRecording> examRecordings1 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam1(), s);
                        List<ExamRecording> examRecordings2 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam2(), s);
                        List<ExamRecording> examRecordings3 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam3(), s);
                        List<ExamRecording> examRecordings4 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam4(), s);
                        List<ExamRecording> examRecordings5 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam5(), s);
                        List<ExamRecording> examRecordings6 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam6(), s);

                        if (!(examRecordings.isEmpty()) && !(examRecordings1.isEmpty()) && !(examRecordings2.isEmpty()) && !(examRecordings3.isEmpty()) && !(examRecordings4.isEmpty()) && !(examRecordings5.isEmpty()) && !(examRecordings6.isEmpty())) {
                            for (ExamRecording e : examRecordings) {
                                BigDecimal marks = BigDecimal.valueOf(e.getMark());
                                BigDecimal outOff = BigDecimal.valueOf(e.getOutOff());
                                BigDecimal aggr = marks.divide(outOff, 2, RoundingMode.HALF_UP);
                                BigDecimal t1 = aggr.multiply(examMergeBean.getMark1());
                                for (ExamRecording e1 : examRecordings1) {
                                    if (e.getTeacherSubjects().getSubjects() == e1.getTeacherSubjects().getSubjects()) {
                                        BigDecimal marks1 = BigDecimal.valueOf(e1.getMark());
                                        BigDecimal outOff1 = BigDecimal.valueOf(e1.getOutOff());
                                        BigDecimal aggr1 = marks1.divide(outOff1, 2, RoundingMode.HALF_UP);
                                        BigDecimal t2 = aggr1.multiply(examMergeBean.getMark2());
                                        for (ExamRecording e2 : examRecordings2) {
                                            if (e1.getTeacherSubjects().getSubjects()
                                                    == e2.getTeacherSubjects().getSubjects()) {
                                                BigDecimal marks2 = BigDecimal.valueOf(e2.getMark());
                                                BigDecimal outOff2 = BigDecimal.valueOf(e2.getOutOff());
                                                BigDecimal aggr2 = marks2.divide(outOff2, 2, RoundingMode.HALF_UP);
                                                BigDecimal t3 = aggr2.multiply(examMergeBean.getMark3());
                                                for (ExamRecording e3 : examRecordings3) {
                                                    if (e2.getTeacherSubjects().getSubjects()
                                                            == e3.getTeacherSubjects().getSubjects()) {
                                                        BigDecimal marks3 = BigDecimal.valueOf(e3.getMark());
                                                        BigDecimal outOff3 = BigDecimal.valueOf(e3.getOutOff());
                                                        BigDecimal aggr3 = marks3.divide(outOff3, 2, RoundingMode.HALF_UP);
                                                        BigDecimal t4 = aggr3.multiply(examMergeBean.getMark4());
                                                        for (ExamRecording e4 : examRecordings4) {
                                                            if (e3.getTeacherSubjects().getSubjects()
                                                                    == e4.getTeacherSubjects().getSubjects()) {

                                                                BigDecimal marks4 = BigDecimal.valueOf(e4.getMark());
                                                                BigDecimal outOff4 = BigDecimal.valueOf(e4.getOutOff());
                                                                BigDecimal aggr4 = marks4.divide(outOff4, 2, RoundingMode.HALF_UP);
                                                                BigDecimal t5 = aggr4.multiply(examMergeBean.getMark5());
                                                                for (ExamRecording e5 : examRecordings5) {
                                                                    if (e4.getTeacherSubjects().getSubjects()
                                                                            == e5.getTeacherSubjects().getSubjects()) {
                                                                        BigDecimal marks5 = BigDecimal.valueOf(e5.getMark());
                                                                        BigDecimal outOff5 = BigDecimal.valueOf(e5.getOutOff());
                                                                        BigDecimal aggr5 = marks5.divide(outOff5, 2, RoundingMode.HALF_UP);
                                                                        BigDecimal t6 = aggr5.multiply(examMergeBean.getMark6());
                                                                        for (ExamRecording e6 : examRecordings6) {
                                                                            if (e5.getTeacherSubjects().getSubjects()
                                                                                    == e6.getTeacherSubjects().getSubjects()) {
                                                                                BigDecimal marks6 = BigDecimal.valueOf(e6.getMark());
                                                                                BigDecimal outOff6 = BigDecimal.valueOf(e6.getOutOff());
                                                                                BigDecimal aggr6 = marks6.divide(outOff6, 2, RoundingMode.HALF_UP);
                                                                                BigDecimal t7 = aggr6.multiply(examMergeBean.getMark7());
                                                                                BigDecimal tt = t1.add(t2).add(t3).add(t4).add(t5).add(t6).add(t7);
                                                                                BigDecimal total = tt.divide(examMergeBean.getOutOff(), 2, RoundingMode.HALF_UP);
                                                                                total = total.multiply(BigDecimal.valueOf(100));
                                                                                double percentage = total.doubleValue();
                                                                                ExamRecording exam = new ExamRecording();
                                                                                GradingSystem system = new GradingSystem();
                                                                                exam.setPercentage(percentage);
                                                                                if (percentage <= 29) {
                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("E");
                                                                                } else if (percentage > 29 && percentage <= 34) {
                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("D-");
                                                                                } else if (percentage > 34 && percentage <= 39) {
                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("D");
                                                                                } else if (percentage > 39 && percentage <= 44) {
                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("D+");
                                                                                } else if (percentage > 44 && percentage <= 49) {
                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("C-");
                                                                                } else if (percentage > 49 && percentage <= 54) {
                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("C");
                                                                                } else if (percentage > 54 && percentage <= 59) {
                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("C+");
                                                                                } else if (percentage > 59 && percentage <= 64) {
                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("B-");
                                                                                } else if (percentage > 64 && percentage <= 69) {
                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("B");
                                                                                } else if (percentage > 69 && percentage <= 74) {
                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("B+");
                                                                                } else if (percentage > 74 && percentage <= 79) {
                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("A-");
                                                                                } else if (percentage > 79 && percentage <= 100) {
                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("A");
                                                                                } else {
                                                                                    throw new BadRequestException("Invalid marks");
                                                                                }
                                                                                exam.setTerm(examMergeBean.getTerm());
                                                                                exam.setYear(examMergeBean.getYear());
                                                                                exam.setStudent(s);
                                                                                exam.setTeacherSubjects(e6.getTeacherSubjects());
                                                                                exam.setExamDate(examMergeBean.getExamRegister().getEffectiveDate());
                                                                                exam.setExamRegister(examMergeBean.getExamRegister());
                                                                                exam.setForms(e6.getForms());
                                                                                exam.setTeachers(e6.getTeachers());
                                                                                exam.setMark(tt.doubleValue());
                                                                                exam.setGradingSystem(system);
                                                                                exam.setOutOff(examMergeBean.getOutOff().doubleValue());
                                                                                examRecordingRepo.save(exam);
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            } else {
                throw new BadRequestException("Totals don't add up");
            }
        } else if (examMergeBean.getSubjects() != null && examMergeBean.getForms() == null) {
            BigDecimal mark = examMergeBean.getMark1().add(examMergeBean.getMark2()).add(examMergeBean.getMark3()).add(examMergeBean.getMark4()).add(examMergeBean.getMark5()).add(examMergeBean.getMark6()).add(examMergeBean.getMark7());
            if (mark.equals(examMergeBean.getOutOff())) {
                List<ExamRecording> examRecordings = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam(), examMergeBean.getSubjects());
                List<ExamRecording> examRecordings1 = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam1(), examMergeBean.getSubjects());
                List<ExamRecording> examRecordings2 = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam2(), examMergeBean.getSubjects());
                List<ExamRecording> examRecordings3 = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam3(), examMergeBean.getSubjects());
                List<ExamRecording> examRecordings4 = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam4(), examMergeBean.getSubjects());
                List<ExamRecording> examRecordings5 = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam5(), examMergeBean.getSubjects());
                List<ExamRecording> examRecordings6 = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam6(), examMergeBean.getSubjects());

                if (!(examRecordings.isEmpty()) && !(examRecordings1.isEmpty()) && !(examRecordings2.isEmpty()) && !(examRecordings3.isEmpty()) && !(examRecordings4.isEmpty()) && !(examRecordings5.isEmpty()) && !(examRecordings6.isEmpty())) {
                    for (ExamRecording e : examRecordings) {
                        BigDecimal marks = BigDecimal.valueOf(e.getMark());
                        BigDecimal outOff = BigDecimal.valueOf(e.getOutOff());
                        BigDecimal aggr = marks.divide(outOff, 2, RoundingMode.HALF_UP);
                        BigDecimal t1 = aggr.multiply(examMergeBean.getMark1());
                        for (ExamRecording e1 : examRecordings1) {
                            if (e.getTeacherSubjects().getSubjects() == e1.getTeacherSubjects().getSubjects() && e.getStudent()
                                    == e1.getStudent()) {
                                BigDecimal marks1 = BigDecimal.valueOf(e1.getMark());
                                BigDecimal outOff1 = BigDecimal.valueOf(e1.getOutOff());
                                BigDecimal aggr1 = marks1.divide(outOff1, 2, RoundingMode.HALF_UP);
                                BigDecimal t2 = aggr1.multiply(examMergeBean.getMark2());
                                for (ExamRecording e2 : examRecordings2) {

                                    if (e1.getTeacherSubjects().getSubjects() == e2.getTeacherSubjects().getSubjects() && e1.getStudent()
                                            == e2.getStudent()) {
                                        BigDecimal marks2 = BigDecimal.valueOf(e2.getMark());
                                        BigDecimal outOff2 = BigDecimal.valueOf(e2.getOutOff());
                                        BigDecimal aggr2 = marks2.divide(outOff2, 2, RoundingMode.HALF_UP);
                                        BigDecimal t3 = aggr2.multiply(examMergeBean.getMark3());
                                        for (ExamRecording e3 : examRecordings3) {
                                            if (e2.getTeacherSubjects().getSubjects() == e3.getTeacherSubjects().getSubjects() && e2.getStudent()
                                                    == e3.getStudent()) {
                                                BigDecimal marks3 = BigDecimal.valueOf(e3.getMark());
                                                BigDecimal outOff3 = BigDecimal.valueOf(e3.getOutOff());
                                                BigDecimal aggr3 = marks3.divide(outOff3, 2, RoundingMode.HALF_UP);
                                                BigDecimal t4 = aggr3.multiply(examMergeBean.getMark4());
                                                for (ExamRecording e4 : examRecordings4) {
                                                    if (e3.getTeacherSubjects().getSubjects() == e4.getTeacherSubjects().getSubjects() && e3.getStudent()
                                                            == e4.getStudent()) {
                                                        BigDecimal marks4 = BigDecimal.valueOf(e4.getMark());
                                                        BigDecimal outOff4 = BigDecimal.valueOf(e4.getOutOff());
                                                        BigDecimal aggr4 = marks4.divide(outOff4, 2, RoundingMode.HALF_UP);
                                                        BigDecimal t5 = aggr4.multiply(examMergeBean.getMark5());
                                                        for (ExamRecording e5 : examRecordings5) {
                                                            if (e4.getTeacherSubjects().getSubjects() == e5.getTeacherSubjects().getSubjects() && e4.getStudent() == e5.getStudent()) {
                                                                BigDecimal marks5 = BigDecimal.valueOf(e5.getMark());
                                                                BigDecimal outOff5 = BigDecimal.valueOf(e5.getOutOff());
                                                                BigDecimal aggr5 = marks5.divide(outOff5, 2, RoundingMode.HALF_UP);
                                                                BigDecimal t6 = aggr5.multiply(examMergeBean.getMark6());
                                                                for (ExamRecording e6 : examRecordings6) {
                                                                    if (e5.getTeacherSubjects().getSubjects() == e6.getTeacherSubjects().getSubjects() && e5.getStudent() == e6.getStudent()) {
                                                                        BigDecimal marks6 = BigDecimal.valueOf(e6.getMark());
                                                                        BigDecimal outOff6 = BigDecimal.valueOf(e6.getOutOff());
                                                                        BigDecimal aggr6 = marks6.divide(outOff6, 2, RoundingMode.HALF_UP);
                                                                        BigDecimal t7 = aggr6.multiply(examMergeBean.getMark7());
                                                                        BigDecimal tt = t1.add(t2).add(t3).add(t4).add(t5).add(t6).add(t7);
                                                                        BigDecimal total = tt.divide(examMergeBean.getOutOff(), 2, RoundingMode.HALF_UP);
                                                                        total = total.multiply(BigDecimal.valueOf(100));
                                                                        double percentage = total.doubleValue();
                                                                        ExamRecording exam = new ExamRecording();
                                                                        GradingSystem system = new GradingSystem();
                                                                        exam.setPercentage(percentage);
                                                                        if (percentage <= 29) {
                                                                            system = gradingSysRepo.findByGradeIgnoreCase("E");
                                                                        } else if (percentage > 29 && percentage <= 34) {
                                                                            system = gradingSysRepo.findByGradeIgnoreCase("D-");
                                                                        } else if (percentage > 34 && percentage <= 39) {
                                                                            system = gradingSysRepo.findByGradeIgnoreCase("D");
                                                                        } else if (percentage > 39 && percentage <= 44) {
                                                                            system = gradingSysRepo.findByGradeIgnoreCase("D+");
                                                                        } else if (percentage > 44 && percentage <= 49) {
                                                                            system = gradingSysRepo.findByGradeIgnoreCase("C-");
                                                                        } else if (percentage > 49 && percentage <= 54) {
                                                                            system = gradingSysRepo.findByGradeIgnoreCase("C");
                                                                        } else if (percentage > 54 && percentage <= 59) {
                                                                            system = gradingSysRepo.findByGradeIgnoreCase("C+");
                                                                        } else if (percentage > 59 && percentage <= 64) {
                                                                            system = gradingSysRepo.findByGradeIgnoreCase("B-");
                                                                        } else if (percentage > 64 && percentage <= 69) {
                                                                            system = gradingSysRepo.findByGradeIgnoreCase("B");
                                                                        } else if (percentage > 69 && percentage <= 74) {
                                                                            system = gradingSysRepo.findByGradeIgnoreCase("B+");
                                                                        } else if (percentage > 74 && percentage <= 79) {
                                                                            system = gradingSysRepo.findByGradeIgnoreCase("A-");
                                                                        } else if (percentage > 79 && percentage <= 100) {
                                                                            system = gradingSysRepo.findByGradeIgnoreCase("A");
                                                                        } else {
                                                                            throw new BadRequestException("Invalid marks");
                                                                        }
                                                                        exam.setTerm(examMergeBean.getTerm());
                                                                        exam.setYear(examMergeBean.getYear());
                                                                        exam.setStudent(e6.getStudent());
                                                                        exam.setTeacherSubjects(e6.getTeacherSubjects());
                                                                        exam.setExamDate(examMergeBean.getExamRegister().getEffectiveDate());
                                                                        exam.setExamRegister(examMergeBean.getExamRegister());
                                                                        exam.setForms(e6.getForms());
                                                                        exam.setTeachers(e6.getTeachers());
                                                                        exam.setMark(tt.doubleValue());
                                                                        exam.setGradingSystem(system);
                                                                        exam.setOutOff(examMergeBean.getOutOff().doubleValue());
                                                                        examRecordingRepo.save(exam);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                    }

                }
            } else {
                throw new BadRequestException("Totals don't add up");
            }
        } else if (examMergeBean.getSubjects() != null && examMergeBean.getForms() != null) {
            BigDecimal mark = examMergeBean.getMark1().add(examMergeBean.getMark2()).add(examMergeBean.getMark3()).add(examMergeBean.getMark4()).add(examMergeBean.getMark5()).add(examMergeBean.getMark6()).add(examMergeBean.getMark7());
            if (mark.equals(examMergeBean.getOutOff())) {
                List<Student> students = studentRepo.findByForms_ClassCode(examMergeBean.getForms().getClassCode());
                if (!(students.isEmpty())) {
                    for (Student s : students) {
                        List<ExamRecording> examRecordings = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam(), s, examMergeBean.getSubjects());
                        List<ExamRecording> examRecordings1 = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam1(), s, examMergeBean.getSubjects());
                        List<ExamRecording> examRecordings2 = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam2(), s, examMergeBean.getSubjects());
                        List<ExamRecording> examRecordings3 = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam3(), s, examMergeBean.getSubjects());
                        List<ExamRecording> examRecordings4 = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam4(), s, examMergeBean.getSubjects());
                        List<ExamRecording> examRecordings5 = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam5(), s, examMergeBean.getSubjects());
                        List<ExamRecording> examRecordings6 = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam6(), s, examMergeBean.getSubjects());

                        if (!(examRecordings.isEmpty()) && !(examRecordings1.isEmpty()) && !(examRecordings2.isEmpty()) && !(examRecordings3.isEmpty()) && !(examRecordings4.isEmpty()) && !(examRecordings5.isEmpty()) && !(examRecordings6.isEmpty())) {
                            for (ExamRecording e : examRecordings) {
                                BigDecimal marks = BigDecimal.valueOf(e.getMark());
                                BigDecimal outOff = BigDecimal.valueOf(e.getOutOff());
                                BigDecimal aggr = marks.divide(outOff, 2, RoundingMode.HALF_UP);
                                BigDecimal t1 = aggr.multiply(examMergeBean.getMark1());

                                for (ExamRecording e1 : examRecordings1) {
                                    if (e.getTeacherSubjects().getSubjects() == e1.getTeacherSubjects().getSubjects() && e.getStudent() == e1.getStudent()) {
                                        BigDecimal marks1 = BigDecimal.valueOf(e1.getMark());
                                        BigDecimal outOff1 = BigDecimal.valueOf(e1.getOutOff());
                                        BigDecimal aggr1 = marks1.divide(outOff1, 2, RoundingMode.HALF_UP);
                                        BigDecimal t2 = aggr1.multiply(examMergeBean.getMark2());
                                        for (ExamRecording e2 : examRecordings2) {
                                            if (e1.getTeacherSubjects().getSubjects() == e2.getTeacherSubjects().getSubjects() && e1.getStudent() == e2.getStudent()) {
                                                BigDecimal marks2 = BigDecimal.valueOf(e2.getMark());
                                                BigDecimal outOff2 = BigDecimal.valueOf(e2.getOutOff());
                                                BigDecimal aggr2 = marks2.divide(outOff2, 2, RoundingMode.HALF_UP);
                                                BigDecimal t3 = aggr2.multiply(examMergeBean.getMark3());
                                                for (ExamRecording e3 : examRecordings3) {
                                                    if (e2.getTeacherSubjects().getSubjects() == e3.getTeacherSubjects().getSubjects() && e2.getStudent() == e3.getStudent()) {
                                                        BigDecimal marks3 = BigDecimal.valueOf(e3.getMark());
                                                        BigDecimal outOff3 = BigDecimal.valueOf(e3.getOutOff());
                                                        BigDecimal aggr3 = marks3.divide(outOff3, 2, RoundingMode.HALF_UP);
                                                        BigDecimal t4 = aggr3.multiply(examMergeBean.getMark4());
                                                        for (ExamRecording e4 : examRecordings4) {
                                                            if (e3.getTeacherSubjects().getSubjects() == e4.getTeacherSubjects().getSubjects() && e3.getStudent() == e4.getStudent()) {
                                                                BigDecimal marks4 = BigDecimal.valueOf(e4.getMark());
                                                                BigDecimal outOff4 = BigDecimal.valueOf(e4.getOutOff());
                                                                BigDecimal aggr4 = marks4.divide(outOff4, 2, RoundingMode.HALF_UP);
                                                                BigDecimal t5 = aggr4.multiply(examMergeBean.getMark5());
                                                                for (ExamRecording e5 : examRecordings5) {
                                                                    if (e4.getTeacherSubjects().getSubjects() == e5.getTeacherSubjects().getSubjects() && e4.getStudent() == e5.getStudent()) {
                                                                        BigDecimal marks5 = BigDecimal.valueOf(e5.getMark());
                                                                        BigDecimal outOff5 = BigDecimal.valueOf(e5.getOutOff());
                                                                        BigDecimal aggr5 = marks5.divide(outOff5, 2, RoundingMode.HALF_UP);
                                                                        BigDecimal t6 = aggr5.multiply(examMergeBean.getMark6());
                                                                        for (ExamRecording e6 : examRecordings6) {
                                                                            if (e5.getTeacherSubjects().getSubjects() == e6.getTeacherSubjects().getSubjects() && e5.getStudent() == e6.getStudent()) {
                                                                                BigDecimal marks6 = BigDecimal.valueOf(e6.getMark());
                                                                                BigDecimal outOff6 = BigDecimal.valueOf(e6.getOutOff());
                                                                                BigDecimal aggr6 = marks6.divide(outOff6, 2, RoundingMode.HALF_UP);
                                                                                BigDecimal t7 = aggr6.multiply(examMergeBean.getMark7());
                                                                                BigDecimal tt = t1.add(t2).add(t3).add(t4).add(t5).add(t6).add(t7);
                                                                                BigDecimal total = tt.divide(examMergeBean.getOutOff(), 2, RoundingMode.HALF_UP);
                                                                                total = total.multiply(BigDecimal.valueOf(100));
                                                                                double percentage = total.doubleValue();
                                                                                ExamRecording exam = new ExamRecording();
                                                                                GradingSystem system = new GradingSystem();
                                                                                exam.setPercentage(percentage);
                                                                                if (percentage <= 29) {
                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("E");
                                                                                } else if (percentage > 29 && percentage <= 34) {
                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("D-");
                                                                                } else if (percentage > 34 && percentage <= 39) {
                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("D");
                                                                                } else if (percentage > 39 && percentage <= 44) {
                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("D+");
                                                                                } else if (percentage > 44 && percentage <= 49) {
                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("C-");
                                                                                } else if (percentage > 49 && percentage <= 54) {
                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("C");
                                                                                } else if (percentage > 54 && percentage <= 59) {
                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("C+");
                                                                                } else if (percentage > 59 && percentage <= 64) {
                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("B-");
                                                                                } else if (percentage > 64 && percentage <= 69) {
                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("B");
                                                                                } else if (percentage > 69 && percentage <= 74) {
                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("B+");
                                                                                } else if (percentage > 74 && percentage <= 79) {
                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("A-");
                                                                                } else if (percentage > 79 && percentage <= 100) {
                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("A");
                                                                                } else {
                                                                                    throw new BadRequestException("Invalid marks");
                                                                                }
                                                                                exam.setGradingSystem(system);
                                                                                exam.setTerm(examMergeBean.getTerm());
                                                                                exam.setYear(examMergeBean.getYear());
                                                                                exam.setStudent(s);
                                                                                exam.setTeacherSubjects(e6.getTeacherSubjects());
                                                                                exam.setExamDate(examMergeBean.getExamRegister().getEffectiveDate());
                                                                                exam.setExamRegister(examMergeBean.getExamRegister());
                                                                                exam.setForms(e6.getForms());
                                                                                exam.setTeachers(e6.getTeachers());
                                                                                exam.setMark(tt.doubleValue());
                                                                                exam.setOutOff(examMergeBean.getOutOff().doubleValue());
                                                                                examRecordingRepo.save(exam);
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            } else {
                throw new BadRequestException("Totals don't add up");
            }
        } else {
            BigDecimal mark = examMergeBean.getMark1().add(examMergeBean.getMark2()).add(examMergeBean.getMark3()).add(examMergeBean.getMark4()).add(examMergeBean.getMark5()).add(examMergeBean.getMark6()).add(examMergeBean.getMark7());
            if (mark.equals(examMergeBean.getOutOff())) {
                List<Student> students = studentRepo.findAllByOrderByForms();
                for (Student s : students) {
                    List<ExamRecording> examRecordings = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam(), s);
                    List<ExamRecording> examRecordings1 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam1(), s);
                    List<ExamRecording> examRecordings2 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam2(), s);
                    List<ExamRecording> examRecordings3 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam3(), s);
                    List<ExamRecording> examRecordings4 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam4(), s);
                    List<ExamRecording> examRecordings5 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam5(), s);
                    List<ExamRecording> examRecordings6 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam6(), s);

                    if (!(examRecordings.isEmpty()) && !(examRecordings1.isEmpty()) && !(examRecordings2.isEmpty()) && !(examRecordings3.isEmpty()) && !(examRecordings4.isEmpty()) && !(examRecordings5.isEmpty()) && !(examRecordings6.isEmpty())) {
                        for (ExamRecording e : examRecordings) {
                            BigDecimal marks = BigDecimal.valueOf(e.getMark());
                            BigDecimal outOff = BigDecimal.valueOf(e.getOutOff());
                            BigDecimal aggr = marks.divide(outOff, 2, RoundingMode.HALF_UP);
                            BigDecimal t1 = aggr.multiply(examMergeBean.getMark1());
                            for (ExamRecording e1 : examRecordings1) {
                                if (e.getTeacherSubjects().getSubjects() == e1.getTeacherSubjects().getSubjects()) {
                                    BigDecimal marks1 = BigDecimal.valueOf(e1.getMark());
                                    BigDecimal outOff1 = BigDecimal.valueOf(e1.getOutOff());
                                    BigDecimal aggr1 = marks1.divide(outOff1, 2, RoundingMode.HALF_UP);
                                    BigDecimal t2 = aggr1.multiply(examMergeBean.getMark2());
                                    for (ExamRecording e2 : examRecordings2) {
                                        if (e1.getTeacherSubjects().getSubjects() == e2.getTeacherSubjects().getSubjects()) {
                                            BigDecimal marks2 = BigDecimal.valueOf(e2.getMark());
                                            BigDecimal outOff2 = BigDecimal.valueOf(e2.getOutOff());
                                            BigDecimal aggr2 = marks2.divide(outOff2, 2, RoundingMode.HALF_UP);
                                            BigDecimal t3 = aggr2.multiply(examMergeBean.getMark3());
                                            for (ExamRecording e3 : examRecordings3) {
                                                if (e2.getTeacherSubjects().getSubjects() == e3.getTeacherSubjects().getSubjects()) {
                                                    BigDecimal marks3 = BigDecimal.valueOf(e3.getMark());
                                                    BigDecimal outOff3 = BigDecimal.valueOf(e3.getOutOff());
                                                    BigDecimal aggr3 = marks3.divide(outOff3, 2, RoundingMode.HALF_UP);
                                                    BigDecimal t4 = aggr3.multiply(examMergeBean.getMark4());
                                                    for (ExamRecording e4 : examRecordings4) {
                                                        if (e3.getTeacherSubjects().getSubjects() == e4.getTeacherSubjects().getSubjects()) {
                                                            BigDecimal marks4 = BigDecimal.valueOf(e4.getMark());
                                                            BigDecimal outOff4 = BigDecimal.valueOf(e4.getOutOff());
                                                            BigDecimal aggr4 = marks4.divide(outOff4, 2, RoundingMode.HALF_UP);
                                                            BigDecimal t5 = aggr4.multiply(examMergeBean.getMark5());
                                                            for (ExamRecording e5 : examRecordings5) {
                                                                if (e4.getTeacherSubjects().getSubjects() == e5.getTeacherSubjects().getSubjects()) {
                                                                    BigDecimal marks5 = BigDecimal.valueOf(e5.getMark());
                                                                    BigDecimal outOff5 = BigDecimal.valueOf(e5.getOutOff());
                                                                    BigDecimal aggr5 = marks5.divide(outOff5, 2, RoundingMode.HALF_UP);
                                                                    BigDecimal t6 = aggr5.multiply(examMergeBean.getMark6());
                                                                    for (ExamRecording e6 : examRecordings6) {
                                                                        if (e5.getTeacherSubjects().getSubjects() == e6.getTeacherSubjects().getSubjects()) {
                                                                            BigDecimal marks6 = BigDecimal.valueOf(e6.getMark());
                                                                            BigDecimal outOff6 = BigDecimal.valueOf(e6.getOutOff());
                                                                            BigDecimal aggr6 = marks6.divide(outOff6, 2, RoundingMode.HALF_UP);
                                                                            BigDecimal t7 = aggr6.multiply(examMergeBean.getMark7());
                                                                            BigDecimal tt = t1.add(t2).add(t3).add(t4).add(t5).add(t6).add(t7);
                                                                            BigDecimal total = tt.divide(examMergeBean.getOutOff(), 2, RoundingMode.HALF_UP);
                                                                            total = total.multiply(BigDecimal.valueOf(100));
                                                                            double percentage = total.doubleValue();
                                                                            ExamRecording exam = new ExamRecording();
                                                                            GradingSystem system = new GradingSystem();
                                                                            exam.setPercentage(percentage);
                                                                            if (percentage <= 29) {
                                                                                system = gradingSysRepo.findByGradeIgnoreCase("E");
                                                                            } else if (percentage > 29 && percentage <= 34) {
                                                                                system = gradingSysRepo.findByGradeIgnoreCase("D-");
                                                                            } else if (percentage > 34 && percentage <= 39) {
                                                                                system = gradingSysRepo.findByGradeIgnoreCase("D");
                                                                            } else if (percentage > 39 && percentage <= 44) {
                                                                                system = gradingSysRepo.findByGradeIgnoreCase("D+");
                                                                            } else if (percentage > 44 && percentage <= 49) {
                                                                                system = gradingSysRepo.findByGradeIgnoreCase("C-");
                                                                            } else if (percentage > 49 && percentage <= 54) {
                                                                                system = gradingSysRepo.findByGradeIgnoreCase("C");
                                                                            } else if (percentage > 54 && percentage <= 59) {
                                                                                system = gradingSysRepo.findByGradeIgnoreCase("C+");
                                                                            } else if (percentage > 59 && percentage <= 64) {
                                                                                system = gradingSysRepo.findByGradeIgnoreCase("B-");
                                                                            } else if (percentage > 64 && percentage <= 69) {
                                                                                system = gradingSysRepo.findByGradeIgnoreCase("B");
                                                                            } else if (percentage > 69 && percentage <= 74) {
                                                                                system = gradingSysRepo.findByGradeIgnoreCase("B+");
                                                                            } else if (percentage > 74 && percentage <= 79) {
                                                                                system = gradingSysRepo.findByGradeIgnoreCase("A-");
                                                                            } else if (percentage > 79 && percentage <= 100) {
                                                                                system = gradingSysRepo.findByGradeIgnoreCase("A");
                                                                            } else {
                                                                                throw new BadRequestException("Invalid marks");
                                                                            }
                                                                            exam.setGradingSystem(system);
                                                                            exam.setTerm(examMergeBean.getTerm());
                                                                            exam.setYear(examMergeBean.getYear());
                                                                            exam.setStudent(s);
                                                                            exam.setTeacherSubjects(e5.getTeacherSubjects());
                                                                            exam.setExamDate(examMergeBean.getExamRegister().getEffectiveDate());
                                                                            exam.setExamRegister(examMergeBean.getExamRegister());
                                                                            exam.setForms(e5.getForms());
                                                                            exam.setTeachers(e5.getTeachers());
                                                                            exam.setMark(tt.doubleValue());
                                                                            exam.setOutOff(examMergeBean.getOutOff().doubleValue());
                                                                            examRecordingRepo.save(exam);
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            } else {
                throw new BadRequestException("Totals don't add up");
            }


        }
    }

    @Override
    public void mergeEightExams(ExamMergeBean examMergeBean) throws BadRequestException {
        if (examMergeBean.getForms() != null && examMergeBean.getSubjects() == null) {
            BigDecimal mark = examMergeBean.getMark1().add(examMergeBean.getMark2()).add(examMergeBean.getMark3()).add(examMergeBean.getMark4()).add(examMergeBean.getMark5()).add(examMergeBean.getMark6()).add(examMergeBean.getMark7()).add(examMergeBean.getMark8());
            if (mark.equals(examMergeBean.getOutOff())) {
                List<Student> students = studentRepo.findByForms_ClassCode(examMergeBean.getForms().getClassCode());
                if (!(students.isEmpty())) {
                    for (Student s : students) {
                        List<ExamRecording> examRecordings = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam(), s);
                        List<ExamRecording> examRecordings1 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam1(), s);
                        List<ExamRecording> examRecordings2 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam2(), s);
                        List<ExamRecording> examRecordings3 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam3(), s);
                        List<ExamRecording> examRecordings4 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam4(), s);
                        List<ExamRecording> examRecordings5 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam5(), s);
                        List<ExamRecording> examRecordings6 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam6(), s);
                        List<ExamRecording> examRecordings7 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam7(), s);

                        if (!(examRecordings.isEmpty()) && !(examRecordings1.isEmpty()) && !(examRecordings2.isEmpty()) && !(examRecordings3.isEmpty()) && !(examRecordings4.isEmpty()) && !(examRecordings5.isEmpty()) && !(examRecordings6.isEmpty()) && !(examRecordings7.isEmpty())) {
                            for (ExamRecording e : examRecordings) {
                                BigDecimal marks = BigDecimal.valueOf(e.getMark());
                                BigDecimal outOff = BigDecimal.valueOf(e.getOutOff());
                                BigDecimal aggr = marks.divide(outOff, 2, RoundingMode.HALF_UP);
                                BigDecimal t1 = aggr.multiply(examMergeBean.getMark1());
                                for (ExamRecording e1 : examRecordings1) {
                                    if (e.getTeacherSubjects().getSubjects() == e1.getTeacherSubjects().getSubjects()) {
                                        BigDecimal marks1 = BigDecimal.valueOf(e1.getMark());
                                        BigDecimal outOff1 = BigDecimal.valueOf(e1.getOutOff());
                                        BigDecimal aggr1 = marks1.divide(outOff1, 2, RoundingMode.HALF_UP);
                                        BigDecimal t2 = aggr1.multiply(examMergeBean.getMark2());
                                        for (ExamRecording e2 : examRecordings2) {
                                            if (e1.getTeacherSubjects().getSubjects()
                                                    == e2.getTeacherSubjects().getSubjects()) {
                                                BigDecimal marks2 = BigDecimal.valueOf(e2.getMark());
                                                BigDecimal outOff2 = BigDecimal.valueOf(e2.getOutOff());
                                                BigDecimal aggr2 = marks2.divide(outOff2, 2, RoundingMode.HALF_UP);
                                                BigDecimal t3 = aggr2.multiply(examMergeBean.getMark3());
                                                for (ExamRecording e3 : examRecordings3) {
                                                    if (e2.getTeacherSubjects().getSubjects()
                                                            == e3.getTeacherSubjects().getSubjects()) {
                                                        BigDecimal marks3 = BigDecimal.valueOf(e3.getMark());
                                                        BigDecimal outOff3 = BigDecimal.valueOf(e3.getOutOff());
                                                        BigDecimal aggr3 = marks3.divide(outOff3, 2, RoundingMode.HALF_UP);
                                                        BigDecimal t4 = aggr3.multiply(examMergeBean.getMark4());
                                                        for (ExamRecording e4 : examRecordings4) {
                                                            if (e3.getTeacherSubjects().getSubjects()
                                                                    == e4.getTeacherSubjects().getSubjects()) {

                                                                BigDecimal marks4 = BigDecimal.valueOf(e4.getMark());
                                                                BigDecimal outOff4 = BigDecimal.valueOf(e4.getOutOff());
                                                                BigDecimal aggr4 = marks4.divide(outOff4, 2, RoundingMode.HALF_UP);
                                                                BigDecimal t5 = aggr4.multiply(examMergeBean.getMark5());
                                                                for (ExamRecording e5 : examRecordings5) {
                                                                    if (e4.getTeacherSubjects().getSubjects()
                                                                            == e5.getTeacherSubjects().getSubjects()) {
                                                                        BigDecimal marks5 = BigDecimal.valueOf(e5.getMark());
                                                                        BigDecimal outOff5 = BigDecimal.valueOf(e5.getOutOff());
                                                                        BigDecimal aggr5 = marks5.divide(outOff5, 2, RoundingMode.HALF_UP);
                                                                        BigDecimal t6 = aggr5.multiply(examMergeBean.getMark6());
                                                                        for (ExamRecording e6 : examRecordings6) {
                                                                            if (e5.getTeacherSubjects().getSubjects()
                                                                                    == e6.getTeacherSubjects().getSubjects()) {
                                                                                BigDecimal marks6 = BigDecimal.valueOf(e6.getMark());
                                                                                BigDecimal outOff6 = BigDecimal.valueOf(e6.getOutOff());
                                                                                BigDecimal aggr6 = marks6.divide(outOff6, 2, RoundingMode.HALF_UP);
                                                                                BigDecimal t7 = aggr6.multiply(examMergeBean.getMark7());
                                                                                for (ExamRecording e7 : examRecordings7) {
                                                                                    if (e6.getTeacherSubjects().getSubjects()
                                                                                            == e7.getTeacherSubjects().getSubjects()) {
                                                                                        BigDecimal marks7 = BigDecimal.valueOf(e7.getMark());
                                                                                        BigDecimal outOff7 = BigDecimal.valueOf(e7.getOutOff());
                                                                                        BigDecimal aggr7 = marks7.divide(outOff7, 2, RoundingMode.HALF_UP);
                                                                                        BigDecimal t8 = aggr7.multiply(examMergeBean.getMark8());
                                                                                        BigDecimal tt = t1.add(t2).add(t3).add(t4).add(t5).add(t6).add(t7).add(t8);
                                                                                        BigDecimal total = tt.divide(examMergeBean.getOutOff(), 2, RoundingMode.HALF_UP);
                                                                                        total = total.multiply(BigDecimal.valueOf(100));
                                                                                        double percentage = total.doubleValue();
                                                                                        ExamRecording exam = new ExamRecording();
                                                                                        GradingSystem system = new GradingSystem();
                                                                                        exam.setPercentage(percentage);
                                                                                        if (percentage <= 29) {
                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("E");
                                                                                        } else if (percentage > 29 && percentage <= 34) {
                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("D-");
                                                                                        } else if (percentage > 34 && percentage <= 39) {
                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("D");
                                                                                        } else if (percentage > 39 && percentage <= 44) {
                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("D+");
                                                                                        } else if (percentage > 44 && percentage <= 49) {
                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("C-");
                                                                                        } else if (percentage > 49 && percentage <= 54) {
                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("C");
                                                                                        } else if (percentage > 54 && percentage <= 59) {
                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("C+");
                                                                                        } else if (percentage > 59 && percentage <= 64) {
                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("B-");
                                                                                        } else if (percentage > 64 && percentage <= 69) {
                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("B");
                                                                                        } else if (percentage > 69 && percentage <= 74) {
                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("B+");
                                                                                        } else if (percentage > 74 && percentage <= 79) {
                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("A-");
                                                                                        } else if (percentage > 79 && percentage <= 100) {
                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("A");
                                                                                        } else {
                                                                                            throw new BadRequestException("Invalid marks");
                                                                                        }
                                                                                        exam.setTerm(examMergeBean.getTerm());
                                                                                        exam.setYear(examMergeBean.getYear());
                                                                                        exam.setStudent(s);
                                                                                        exam.setTeacherSubjects(e7.getTeacherSubjects());
                                                                                        exam.setExamDate(examMergeBean.getExamRegister().getEffectiveDate());
                                                                                        exam.setExamRegister(examMergeBean.getExamRegister());
                                                                                        exam.setForms(e7.getForms());
                                                                                        exam.setTeachers(e7.getTeachers());
                                                                                        exam.setMark(tt.doubleValue());
                                                                                        exam.setGradingSystem(system);
                                                                                        exam.setOutOff(examMergeBean.getOutOff().doubleValue());
                                                                                        examRecordingRepo.save(exam);
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            } else {
                throw new BadRequestException("Totals don't add up");
            }
        } else if (examMergeBean.getSubjects() != null && examMergeBean.getForms() == null) {
            BigDecimal mark = examMergeBean.getMark1().add(examMergeBean.getMark2()).add(examMergeBean.getMark3()).add(examMergeBean.getMark4()).add(examMergeBean.getMark5()).add(examMergeBean.getMark6()).add(examMergeBean.getMark7()).add(examMergeBean.getMark8());
            if (mark.equals(examMergeBean.getOutOff())) {
                List<ExamRecording> examRecordings = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam(), examMergeBean.getSubjects());
                List<ExamRecording> examRecordings1 = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam1(), examMergeBean.getSubjects());
                List<ExamRecording> examRecordings2 = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam2(), examMergeBean.getSubjects());
                List<ExamRecording> examRecordings3 = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam3(), examMergeBean.getSubjects());
                List<ExamRecording> examRecordings4 = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam4(), examMergeBean.getSubjects());
                List<ExamRecording> examRecordings5 = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam5(), examMergeBean.getSubjects());
                List<ExamRecording> examRecordings6 = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam6(), examMergeBean.getSubjects());
                List<ExamRecording> examRecordings7 = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam7(), examMergeBean.getSubjects());

                if (!(examRecordings.isEmpty()) && !(examRecordings1.isEmpty()) && !(examRecordings2.isEmpty()) && !(examRecordings3.isEmpty()) && !(examRecordings4.isEmpty()) && !(examRecordings5.isEmpty()) && !(examRecordings6.isEmpty()) && !(examRecordings7.isEmpty())) {
                    for (ExamRecording e : examRecordings) {
                        BigDecimal marks = BigDecimal.valueOf(e.getMark());
                        BigDecimal outOff = BigDecimal.valueOf(e.getOutOff());
                        BigDecimal aggr = marks.divide(outOff, 2, RoundingMode.HALF_UP);
                        BigDecimal t1 = aggr.multiply(examMergeBean.getMark1());
                        for (ExamRecording e1 : examRecordings1) {
                            if (e.getTeacherSubjects().getSubjects() == e1.getTeacherSubjects().getSubjects() && e.getStudent()
                                    == e1.getStudent()) {
                                BigDecimal marks1 = BigDecimal.valueOf(e1.getMark());
                                BigDecimal outOff1 = BigDecimal.valueOf(e1.getOutOff());
                                BigDecimal aggr1 = marks1.divide(outOff1, 2, RoundingMode.HALF_UP);
                                BigDecimal t2 = aggr1.multiply(examMergeBean.getMark2());
                                for (ExamRecording e2 : examRecordings2) {

                                    if (e1.getTeacherSubjects().getSubjects() == e2.getTeacherSubjects().getSubjects() && e1.getStudent()
                                            == e2.getStudent()) {
                                        BigDecimal marks2 = BigDecimal.valueOf(e2.getMark());
                                        BigDecimal outOff2 = BigDecimal.valueOf(e2.getOutOff());
                                        BigDecimal aggr2 = marks2.divide(outOff2, 2, RoundingMode.HALF_UP);
                                        BigDecimal t3 = aggr2.multiply(examMergeBean.getMark3());
                                        for (ExamRecording e3 : examRecordings3) {
                                            if (e2.getTeacherSubjects().getSubjects() == e3.getTeacherSubjects().getSubjects() && e2.getStudent()
                                                    == e3.getStudent()) {
                                                BigDecimal marks3 = BigDecimal.valueOf(e3.getMark());
                                                BigDecimal outOff3 = BigDecimal.valueOf(e3.getOutOff());
                                                BigDecimal aggr3 = marks3.divide(outOff3, 2, RoundingMode.HALF_UP);
                                                BigDecimal t4 = aggr3.multiply(examMergeBean.getMark4());
                                                for (ExamRecording e4 : examRecordings4) {
                                                    if (e3.getTeacherSubjects().getSubjects() == e4.getTeacherSubjects().getSubjects() && e3.getStudent()
                                                            == e4.getStudent()) {
                                                        BigDecimal marks4 = BigDecimal.valueOf(e4.getMark());
                                                        BigDecimal outOff4 = BigDecimal.valueOf(e4.getOutOff());
                                                        BigDecimal aggr4 = marks4.divide(outOff4, 2, RoundingMode.HALF_UP);
                                                        BigDecimal t5 = aggr4.multiply(examMergeBean.getMark5());
                                                        for (ExamRecording e5 : examRecordings5) {
                                                            if (e4.getTeacherSubjects().getSubjects() == e5.getTeacherSubjects().getSubjects() && e4.getStudent() == e5.getStudent()) {
                                                                BigDecimal marks5 = BigDecimal.valueOf(e5.getMark());
                                                                BigDecimal outOff5 = BigDecimal.valueOf(e5.getOutOff());
                                                                BigDecimal aggr5 = marks5.divide(outOff5, 2, RoundingMode.HALF_UP);
                                                                BigDecimal t6 = aggr5.multiply(examMergeBean.getMark6());
                                                                for (ExamRecording e6 : examRecordings6) {
                                                                    if (e5.getTeacherSubjects().getSubjects() == e6.getTeacherSubjects().getSubjects() && e5.getStudent() == e6.getStudent()) {
                                                                        BigDecimal marks6 = BigDecimal.valueOf(e6.getMark());
                                                                        BigDecimal outOff6 = BigDecimal.valueOf(e6.getOutOff());
                                                                        BigDecimal aggr6 = marks6.divide(outOff6, 2, RoundingMode.HALF_UP);
                                                                        BigDecimal t7 = aggr6.multiply(examMergeBean.getMark7());
                                                                        for (ExamRecording e7 : examRecordings7) {
                                                                            if (e6.getTeacherSubjects().getSubjects() == e7.getTeacherSubjects().getSubjects() && e6.getStudent() == e7.getStudent()) {
                                                                                BigDecimal marks7 = BigDecimal.valueOf(e7.getMark());
                                                                                BigDecimal outOff7 = BigDecimal.valueOf(e7.getOutOff());
                                                                                BigDecimal aggr7 = marks7.divide(outOff7, 2, RoundingMode.HALF_UP);
                                                                                BigDecimal t8 = aggr7.multiply(examMergeBean.getMark8());
                                                                                BigDecimal tt = t1.add(t2).add(t3).add(t4).add(t5).add(t6).add(t7).add(t8);
                                                                                BigDecimal total = tt.divide(examMergeBean.getOutOff(), 2, RoundingMode.HALF_UP);
                                                                                total = total.multiply(BigDecimal.valueOf(100));
                                                                                double percentage = total.doubleValue();
                                                                                ExamRecording exam = new ExamRecording();
                                                                                GradingSystem system = new GradingSystem();
                                                                                exam.setPercentage(percentage);
                                                                                if (percentage <= 29) {
                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("E");
                                                                                } else if (percentage > 29 && percentage <= 34) {
                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("D-");
                                                                                } else if (percentage > 34 && percentage <= 39) {
                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("D");
                                                                                } else if (percentage > 39 && percentage <= 44) {
                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("D+");
                                                                                } else if (percentage > 44 && percentage <= 49) {
                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("C-");
                                                                                } else if (percentage > 49 && percentage <= 54) {
                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("C");
                                                                                } else if (percentage > 54 && percentage <= 59) {
                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("C+");
                                                                                } else if (percentage > 59 && percentage <= 64) {
                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("B-");
                                                                                } else if (percentage > 64 && percentage <= 69) {
                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("B");
                                                                                } else if (percentage > 69 && percentage <= 74) {
                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("B+");
                                                                                } else if (percentage > 74 && percentage <= 79) {
                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("A-");
                                                                                } else if (percentage > 79 && percentage <= 100) {
                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("A");
                                                                                } else {
                                                                                    throw new BadRequestException("Invalid marks");
                                                                                }
                                                                                exam.setTerm(examMergeBean.getTerm());
                                                                                exam.setYear(examMergeBean.getYear());
                                                                                exam.setStudent(e7.getStudent());
                                                                                exam.setTeacherSubjects(e7.getTeacherSubjects());
                                                                                exam.setExamDate(examMergeBean.getExamRegister().getEffectiveDate());
                                                                                exam.setExamRegister(examMergeBean.getExamRegister());
                                                                                exam.setForms(e7.getForms());
                                                                                exam.setTeachers(e7.getTeachers());
                                                                                exam.setMark(tt.doubleValue());
                                                                                exam.setGradingSystem(system);
                                                                                exam.setOutOff(examMergeBean.getOutOff().doubleValue());
                                                                                examRecordingRepo.save(exam);
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                    }

                }
            } else {
                throw new BadRequestException("Totals don't add up");
            }
        } else if (examMergeBean.getSubjects() != null && examMergeBean.getForms() != null) {
            BigDecimal mark = examMergeBean.getMark1().add(examMergeBean.getMark2()).add(examMergeBean.getMark3()).add(examMergeBean.getMark4()).add(examMergeBean.getMark5()).add(examMergeBean.getMark6()).add(examMergeBean.getMark7()).add(examMergeBean.getMark8());
            if (mark.equals(examMergeBean.getOutOff())) {
                List<Student> students = studentRepo.findByForms_ClassCode(examMergeBean.getForms().getClassCode());
                if (!(students.isEmpty())) {
                    for (Student s : students) {
                        List<ExamRecording> examRecordings = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam(), s, examMergeBean.getSubjects());
                        List<ExamRecording> examRecordings1 = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam1(), s, examMergeBean.getSubjects());
                        List<ExamRecording> examRecordings2 = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam2(), s, examMergeBean.getSubjects());
                        List<ExamRecording> examRecordings3 = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam3(), s, examMergeBean.getSubjects());
                        List<ExamRecording> examRecordings4 = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam4(), s, examMergeBean.getSubjects());
                        List<ExamRecording> examRecordings5 = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam5(), s, examMergeBean.getSubjects());
                        List<ExamRecording> examRecordings6 = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam6(), s, examMergeBean.getSubjects());
                        List<ExamRecording> examRecordings7 = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam7(), s, examMergeBean.getSubjects());

                        if (!(examRecordings.isEmpty()) && !(examRecordings1.isEmpty()) && !(examRecordings2.isEmpty()) && !(examRecordings3.isEmpty()) && !(examRecordings4.isEmpty()) && !(examRecordings5.isEmpty()) && !(examRecordings6.isEmpty()) && !(examRecordings7.isEmpty())) {
                            for (ExamRecording e : examRecordings) {
                                BigDecimal marks = BigDecimal.valueOf(e.getMark());
                                BigDecimal outOff = BigDecimal.valueOf(e.getOutOff());
                                BigDecimal aggr = marks.divide(outOff, 2, RoundingMode.HALF_UP);
                                BigDecimal t1 = aggr.multiply(examMergeBean.getMark1());

                                for (ExamRecording e1 : examRecordings1) {
                                    if (e.getTeacherSubjects().getSubjects() == e1.getTeacherSubjects().getSubjects() && e.getStudent() == e1.getStudent()) {
                                        BigDecimal marks1 = BigDecimal.valueOf(e1.getMark());
                                        BigDecimal outOff1 = BigDecimal.valueOf(e1.getOutOff());
                                        BigDecimal aggr1 = marks1.divide(outOff1, 2, RoundingMode.HALF_UP);
                                        BigDecimal t2 = aggr1.multiply(examMergeBean.getMark2());
                                        for (ExamRecording e2 : examRecordings2) {
                                            if (e1.getTeacherSubjects().getSubjects() == e2.getTeacherSubjects().getSubjects() && e1.getStudent() == e2.getStudent()) {
                                                BigDecimal marks2 = BigDecimal.valueOf(e2.getMark());
                                                BigDecimal outOff2 = BigDecimal.valueOf(e2.getOutOff());
                                                BigDecimal aggr2 = marks2.divide(outOff2, 2, RoundingMode.HALF_UP);
                                                BigDecimal t3 = aggr2.multiply(examMergeBean.getMark3());
                                                for (ExamRecording e3 : examRecordings3) {
                                                    if (e2.getTeacherSubjects().getSubjects() == e3.getTeacherSubjects().getSubjects() && e2.getStudent() == e3.getStudent()) {
                                                        BigDecimal marks3 = BigDecimal.valueOf(e3.getMark());
                                                        BigDecimal outOff3 = BigDecimal.valueOf(e3.getOutOff());
                                                        BigDecimal aggr3 = marks3.divide(outOff3, 2, RoundingMode.HALF_UP);
                                                        BigDecimal t4 = aggr3.multiply(examMergeBean.getMark4());
                                                        for (ExamRecording e4 : examRecordings4) {
                                                            if (e3.getTeacherSubjects().getSubjects() == e4.getTeacherSubjects().getSubjects() && e3.getStudent() == e4.getStudent()) {
                                                                BigDecimal marks4 = BigDecimal.valueOf(e4.getMark());
                                                                BigDecimal outOff4 = BigDecimal.valueOf(e4.getOutOff());
                                                                BigDecimal aggr4 = marks4.divide(outOff4, 2, RoundingMode.HALF_UP);
                                                                BigDecimal t5 = aggr4.multiply(examMergeBean.getMark5());
                                                                for (ExamRecording e5 : examRecordings5) {
                                                                    if (e4.getTeacherSubjects().getSubjects() == e5.getTeacherSubjects().getSubjects() && e4.getStudent() == e5.getStudent()) {
                                                                        BigDecimal marks5 = BigDecimal.valueOf(e5.getMark());
                                                                        BigDecimal outOff5 = BigDecimal.valueOf(e5.getOutOff());
                                                                        BigDecimal aggr5 = marks5.divide(outOff5, 2, RoundingMode.HALF_UP);
                                                                        BigDecimal t6 = aggr5.multiply(examMergeBean.getMark6());
                                                                        for (ExamRecording e6 : examRecordings6) {
                                                                            if (e5.getTeacherSubjects().getSubjects() == e6.getTeacherSubjects().getSubjects() && e5.getStudent() == e6.getStudent()) {
                                                                                BigDecimal marks6 = BigDecimal.valueOf(e6.getMark());
                                                                                BigDecimal outOff6 = BigDecimal.valueOf(e6.getOutOff());
                                                                                BigDecimal aggr6 = marks6.divide(outOff6, 2, RoundingMode.HALF_UP);
                                                                                BigDecimal t7 = aggr6.multiply(examMergeBean.getMark7());
                                                                                for (ExamRecording e7 : examRecordings7) {
                                                                                    if (e6.getTeacherSubjects().getSubjects() == e7.getTeacherSubjects().getSubjects() && e6.getStudent() == e7.getStudent()) {
                                                                                        BigDecimal marks7 = BigDecimal.valueOf(e7.getMark());
                                                                                        BigDecimal outOff7 = BigDecimal.valueOf(e7.getOutOff());
                                                                                        BigDecimal aggr7 = marks7.divide(outOff7, 2, RoundingMode.HALF_UP);
                                                                                        BigDecimal t8 = aggr7.multiply(examMergeBean.getMark8());
                                                                                        BigDecimal tt = t1.add(t2).add(t3).add(t4).add(t5).add(t6).add(t7).add(t8);
                                                                                        BigDecimal total = tt.divide(examMergeBean.getOutOff(), 2, RoundingMode.HALF_UP);
                                                                                        total = total.multiply(BigDecimal.valueOf(100));
                                                                                        double percentage = total.doubleValue();
                                                                                        ExamRecording exam = new ExamRecording();
                                                                                        GradingSystem system = new GradingSystem();
                                                                                        exam.setPercentage(percentage);
                                                                                        if (percentage <= 29) {
                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("E");
                                                                                        } else if (percentage > 29 && percentage <= 34) {
                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("D-");
                                                                                        } else if (percentage > 34 && percentage <= 39) {
                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("D");
                                                                                        } else if (percentage > 39 && percentage <= 44) {
                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("D+");
                                                                                        } else if (percentage > 44 && percentage <= 49) {
                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("C-");
                                                                                        } else if (percentage > 49 && percentage <= 54) {
                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("C");
                                                                                        } else if (percentage > 54 && percentage <= 59) {
                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("C+");
                                                                                        } else if (percentage > 59 && percentage <= 64) {
                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("B-");
                                                                                        } else if (percentage > 64 && percentage <= 69) {
                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("B");
                                                                                        } else if (percentage > 69 && percentage <= 74) {
                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("B+");
                                                                                        } else if (percentage > 74 && percentage <= 79) {
                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("A-");
                                                                                        } else if (percentage > 79 && percentage <= 100) {
                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("A");
                                                                                        } else {
                                                                                            throw new BadRequestException("Invalid marks");
                                                                                        }
                                                                                        exam.setGradingSystem(system);
                                                                                        exam.setTerm(examMergeBean.getTerm());
                                                                                        exam.setYear(examMergeBean.getYear());
                                                                                        exam.setStudent(s);
                                                                                        exam.setTeacherSubjects(e7.getTeacherSubjects());
                                                                                        exam.setExamDate(examMergeBean.getExamRegister().getEffectiveDate());
                                                                                        exam.setExamRegister(examMergeBean.getExamRegister());
                                                                                        exam.setForms(e7.getForms());
                                                                                        exam.setTeachers(e7.getTeachers());
                                                                                        exam.setMark(tt.doubleValue());
                                                                                        exam.setOutOff(examMergeBean.getOutOff().doubleValue());
                                                                                        examRecordingRepo.save(exam);
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            } else {
                throw new BadRequestException("Totals don't add up");
            }
        } else {
            BigDecimal mark = examMergeBean.getMark1().add(examMergeBean.getMark2()).add(examMergeBean.getMark3()).add(examMergeBean.getMark4()).add(examMergeBean.getMark5()).add(examMergeBean.getMark6()).add(examMergeBean.getMark7()).add(examMergeBean.getMark8());
            if (mark.equals(examMergeBean.getOutOff())) {
                List<Student> students = studentRepo.findAllByOrderByForms();
                for (Student s : students) {
                    List<ExamRecording> examRecordings = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam(), s);
                    List<ExamRecording> examRecordings1 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam1(), s);
                    List<ExamRecording> examRecordings2 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam2(), s);
                    List<ExamRecording> examRecordings3 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam3(), s);
                    List<ExamRecording> examRecordings4 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam4(), s);
                    List<ExamRecording> examRecordings5 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam5(), s);
                    List<ExamRecording> examRecordings6 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam6(), s);
                    List<ExamRecording> examRecordings7 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam7(), s);

                    if (!(examRecordings.isEmpty()) && !(examRecordings1.isEmpty()) && !(examRecordings2.isEmpty()) && !(examRecordings3.isEmpty()) && !(examRecordings4.isEmpty()) && !(examRecordings5.isEmpty()) && !(examRecordings6.isEmpty()) && !(examRecordings7.isEmpty())) {
                        for (ExamRecording e : examRecordings) {
                            BigDecimal marks = BigDecimal.valueOf(e.getMark());
                            BigDecimal outOff = BigDecimal.valueOf(e.getOutOff());
                            BigDecimal aggr = marks.divide(outOff, 2, RoundingMode.HALF_UP);
                            BigDecimal t1 = aggr.multiply(examMergeBean.getMark1());
                            for (ExamRecording e1 : examRecordings1) {
                                if (e.getTeacherSubjects().getSubjects() == e1.getTeacherSubjects().getSubjects()) {
                                    BigDecimal marks1 = BigDecimal.valueOf(e1.getMark());
                                    BigDecimal outOff1 = BigDecimal.valueOf(e1.getOutOff());
                                    BigDecimal aggr1 = marks1.divide(outOff1, 2, RoundingMode.HALF_UP);
                                    BigDecimal t2 = aggr1.multiply(examMergeBean.getMark2());
                                    for (ExamRecording e2 : examRecordings2) {
                                        if (e1.getTeacherSubjects().getSubjects() == e2.getTeacherSubjects().getSubjects()) {
                                            BigDecimal marks2 = BigDecimal.valueOf(e2.getMark());
                                            BigDecimal outOff2 = BigDecimal.valueOf(e2.getOutOff());
                                            BigDecimal aggr2 = marks2.divide(outOff2, 2, RoundingMode.HALF_UP);
                                            BigDecimal t3 = aggr2.multiply(examMergeBean.getMark3());
                                            for (ExamRecording e3 : examRecordings3) {
                                                if (e2.getTeacherSubjects().getSubjects() == e3.getTeacherSubjects().getSubjects()) {
                                                    BigDecimal marks3 = BigDecimal.valueOf(e3.getMark());
                                                    BigDecimal outOff3 = BigDecimal.valueOf(e3.getOutOff());
                                                    BigDecimal aggr3 = marks3.divide(outOff3, 2, RoundingMode.HALF_UP);
                                                    BigDecimal t4 = aggr3.multiply(examMergeBean.getMark4());
                                                    for (ExamRecording e4 : examRecordings4) {
                                                        if (e3.getTeacherSubjects().getSubjects() == e4.getTeacherSubjects().getSubjects()) {
                                                            BigDecimal marks4 = BigDecimal.valueOf(e4.getMark());
                                                            BigDecimal outOff4 = BigDecimal.valueOf(e4.getOutOff());
                                                            BigDecimal aggr4 = marks4.divide(outOff4, 2, RoundingMode.HALF_UP);
                                                            BigDecimal t5 = aggr4.multiply(examMergeBean.getMark5());
                                                            for (ExamRecording e5 : examRecordings5) {
                                                                if (e4.getTeacherSubjects().getSubjects() == e5.getTeacherSubjects().getSubjects()) {
                                                                    BigDecimal marks5 = BigDecimal.valueOf(e5.getMark());
                                                                    BigDecimal outOff5 = BigDecimal.valueOf(e5.getOutOff());
                                                                    BigDecimal aggr5 = marks5.divide(outOff5, 2, RoundingMode.HALF_UP);
                                                                    BigDecimal t6 = aggr5.multiply(examMergeBean.getMark6());
                                                                    for (ExamRecording e6 : examRecordings6) {
                                                                        if (e5.getTeacherSubjects().getSubjects() == e6.getTeacherSubjects().getSubjects()) {
                                                                            BigDecimal marks6 = BigDecimal.valueOf(e6.getMark());
                                                                            BigDecimal outOff6 = BigDecimal.valueOf(e6.getOutOff());
                                                                            BigDecimal aggr6 = marks6.divide(outOff6, 2, RoundingMode.HALF_UP);
                                                                            BigDecimal t7 = aggr6.multiply(examMergeBean.getMark7());
                                                                            for (ExamRecording e7 : examRecordings7) {
                                                                                if (e6.getTeacherSubjects().getSubjects() == e7.getTeacherSubjects().getSubjects()) {
                                                                                    BigDecimal marks7 = BigDecimal.valueOf(e7.getMark());
                                                                                    BigDecimal outOff7 = BigDecimal.valueOf(e7.getOutOff());
                                                                                    BigDecimal aggr7 = marks7.divide(outOff7, 2, RoundingMode.HALF_UP);
                                                                                    BigDecimal t8 = aggr7.multiply(examMergeBean.getMark7());
                                                                                    BigDecimal tt = t1.add(t2).add(t3).add(t4).add(t5).add(t6).add(t7).add(t8);
                                                                                    BigDecimal total = tt.divide(examMergeBean.getOutOff(), 2, RoundingMode.HALF_UP);
                                                                                    total = total.multiply(BigDecimal.valueOf(100));
                                                                                    double percentage = total.doubleValue();
                                                                                    ExamRecording exam = new ExamRecording();
                                                                                    GradingSystem system = new GradingSystem();
                                                                                    exam.setPercentage(percentage);
                                                                                    if (percentage <= 29) {
                                                                                        system = gradingSysRepo.findByGradeIgnoreCase("E");
                                                                                    } else if (percentage > 29 && percentage <= 34) {
                                                                                        system = gradingSysRepo.findByGradeIgnoreCase("D-");
                                                                                    } else if (percentage > 34 && percentage <= 39) {
                                                                                        system = gradingSysRepo.findByGradeIgnoreCase("D");
                                                                                    } else if (percentage > 39 && percentage <= 44) {
                                                                                        system = gradingSysRepo.findByGradeIgnoreCase("D+");
                                                                                    } else if (percentage > 44 && percentage <= 49) {
                                                                                        system = gradingSysRepo.findByGradeIgnoreCase("C-");
                                                                                    } else if (percentage > 49 && percentage <= 54) {
                                                                                        system = gradingSysRepo.findByGradeIgnoreCase("C");
                                                                                    } else if (percentage > 54 && percentage <= 59) {
                                                                                        system = gradingSysRepo.findByGradeIgnoreCase("C+");
                                                                                    } else if (percentage > 59 && percentage <= 64) {
                                                                                        system = gradingSysRepo.findByGradeIgnoreCase("B-");
                                                                                    } else if (percentage > 64 && percentage <= 69) {
                                                                                        system = gradingSysRepo.findByGradeIgnoreCase("B");
                                                                                    } else if (percentage > 69 && percentage <= 74) {
                                                                                        system = gradingSysRepo.findByGradeIgnoreCase("B+");
                                                                                    } else if (percentage > 74 && percentage <= 79) {
                                                                                        system = gradingSysRepo.findByGradeIgnoreCase("A-");
                                                                                    } else if (percentage > 79 && percentage <= 100) {
                                                                                        system = gradingSysRepo.findByGradeIgnoreCase("A");
                                                                                    } else {
                                                                                        throw new BadRequestException("Invalid marks");
                                                                                    }
                                                                                    exam.setGradingSystem(system);
                                                                                    exam.setTerm(examMergeBean.getTerm());
                                                                                    exam.setYear(examMergeBean.getYear());
                                                                                    exam.setStudent(s);
                                                                                    exam.setTeacherSubjects(e7.getTeacherSubjects());
                                                                                    exam.setExamDate(examMergeBean.getExamRegister().getEffectiveDate());
                                                                                    exam.setExamRegister(examMergeBean.getExamRegister());
                                                                                    exam.setForms(e7.getForms());
                                                                                    exam.setTeachers(e7.getTeachers());
                                                                                    exam.setMark(tt.doubleValue());
                                                                                    exam.setOutOff(examMergeBean.getOutOff().doubleValue());
                                                                                    examRecordingRepo.save(exam);
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            } else {
                throw new BadRequestException("Totals don't add up");
            }


        }
    }

    @Override
    public void mergeNineExams(ExamMergeBean examMergeBean) throws BadRequestException {
        if (examMergeBean.getForms() != null && examMergeBean.getSubjects() == null) {
            BigDecimal mark = examMergeBean.getMark1().add(examMergeBean.getMark2()).add(examMergeBean.getMark3()).add(examMergeBean.getMark4()).add(examMergeBean.getMark5()).add(examMergeBean.getMark6()).add(examMergeBean.getMark7()).add(examMergeBean.getMark8()).add(examMergeBean.getMark9());
            if (mark.equals(examMergeBean.getOutOff())) {
                List<Student> students = studentRepo.findByForms_ClassCode(examMergeBean.getForms().getClassCode());
                if (!(students.isEmpty())) {
                    for (Student s : students) {
                        List<ExamRecording> examRecordings = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam(), s);
                        List<ExamRecording> examRecordings1 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam1(), s);
                        List<ExamRecording> examRecordings2 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam2(), s);
                        List<ExamRecording> examRecordings3 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam3(), s);
                        List<ExamRecording> examRecordings4 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam4(), s);
                        List<ExamRecording> examRecordings5 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam5(), s);
                        List<ExamRecording> examRecordings6 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam6(), s);
                        List<ExamRecording> examRecordings7 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam7(), s);
                        List<ExamRecording> examRecordings8 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam8(), s);

                        if (!(examRecordings.isEmpty()) && !(examRecordings1.isEmpty()) && !(examRecordings2.isEmpty()) && !(examRecordings3.isEmpty()) && !(examRecordings4.isEmpty()) && !(examRecordings5.isEmpty()) && !(examRecordings6.isEmpty()) && !(examRecordings7.isEmpty()) && !(examRecordings8.isEmpty())) {
                            for (ExamRecording e : examRecordings) {
                                BigDecimal marks = BigDecimal.valueOf(e.getMark());
                                BigDecimal outOff = BigDecimal.valueOf(e.getOutOff());
                                BigDecimal aggr = marks.divide(outOff, 2, RoundingMode.HALF_UP);
                                BigDecimal t1 = aggr.multiply(examMergeBean.getMark1());
                                for (ExamRecording e1 : examRecordings1) {
                                    if (e.getTeacherSubjects().getSubjects() == e1.getTeacherSubjects().getSubjects()) {
                                        BigDecimal marks1 = BigDecimal.valueOf(e1.getMark());
                                        BigDecimal outOff1 = BigDecimal.valueOf(e1.getOutOff());
                                        BigDecimal aggr1 = marks1.divide(outOff1, 2, RoundingMode.HALF_UP);
                                        BigDecimal t2 = aggr1.multiply(examMergeBean.getMark2());
                                        for (ExamRecording e2 : examRecordings2) {
                                            if (e1.getTeacherSubjects().getSubjects()
                                                    == e2.getTeacherSubjects().getSubjects()) {
                                                BigDecimal marks2 = BigDecimal.valueOf(e2.getMark());
                                                BigDecimal outOff2 = BigDecimal.valueOf(e2.getOutOff());
                                                BigDecimal aggr2 = marks2.divide(outOff2, 2, RoundingMode.HALF_UP);
                                                BigDecimal t3 = aggr2.multiply(examMergeBean.getMark3());
                                                for (ExamRecording e3 : examRecordings3) {
                                                    if (e2.getTeacherSubjects().getSubjects()
                                                            == e3.getTeacherSubjects().getSubjects()) {
                                                        BigDecimal marks3 = BigDecimal.valueOf(e3.getMark());
                                                        BigDecimal outOff3 = BigDecimal.valueOf(e3.getOutOff());
                                                        BigDecimal aggr3 = marks3.divide(outOff3, 2, RoundingMode.HALF_UP);
                                                        BigDecimal t4 = aggr3.multiply(examMergeBean.getMark4());
                                                        for (ExamRecording e4 : examRecordings4) {
                                                            if (e3.getTeacherSubjects().getSubjects()
                                                                    == e4.getTeacherSubjects().getSubjects()) {

                                                                BigDecimal marks4 = BigDecimal.valueOf(e4.getMark());
                                                                BigDecimal outOff4 = BigDecimal.valueOf(e4.getOutOff());
                                                                BigDecimal aggr4 = marks4.divide(outOff4, 2, RoundingMode.HALF_UP);
                                                                BigDecimal t5 = aggr4.multiply(examMergeBean.getMark5());
                                                                for (ExamRecording e5 : examRecordings5) {
                                                                    if (e4.getTeacherSubjects().getSubjects()
                                                                            == e5.getTeacherSubjects().getSubjects()) {
                                                                        BigDecimal marks5 = BigDecimal.valueOf(e5.getMark());
                                                                        BigDecimal outOff5 = BigDecimal.valueOf(e5.getOutOff());
                                                                        BigDecimal aggr5 = marks5.divide(outOff5, 2, RoundingMode.HALF_UP);
                                                                        BigDecimal t6 = aggr5.multiply(examMergeBean.getMark6());
                                                                        for (ExamRecording e6 : examRecordings6) {
                                                                            if (e5.getTeacherSubjects().getSubjects()
                                                                                    == e6.getTeacherSubjects().getSubjects()) {
                                                                                BigDecimal marks6 = BigDecimal.valueOf(e6.getMark());
                                                                                BigDecimal outOff6 = BigDecimal.valueOf(e6.getOutOff());
                                                                                BigDecimal aggr6 = marks6.divide(outOff6, 2, RoundingMode.HALF_UP);
                                                                                BigDecimal t7 = aggr6.multiply(examMergeBean.getMark7());
                                                                                for (ExamRecording e7 : examRecordings7) {
                                                                                    if (e6.getTeacherSubjects().getSubjects()
                                                                                            == e7.getTeacherSubjects().getSubjects()) {
                                                                                        BigDecimal marks7 = BigDecimal.valueOf(e7.getMark());
                                                                                        BigDecimal outOff7 = BigDecimal.valueOf(e7.getOutOff());
                                                                                        BigDecimal aggr7 = marks7.divide(outOff7, 2, RoundingMode.HALF_UP);
                                                                                        BigDecimal t8 = aggr7.multiply(examMergeBean.getMark8());
                                                                                        for (ExamRecording e8 : examRecordings8) {
                                                                                            if (e7.getTeacherSubjects().getSubjects()
                                                                                                    == e8.getTeacherSubjects().getSubjects()) {
                                                                                                BigDecimal marks8 = BigDecimal.valueOf(e8.getMark());
                                                                                                BigDecimal outOff8 = BigDecimal.valueOf(e8.getOutOff());
                                                                                                BigDecimal aggr8 = marks8.divide(outOff8, 2, RoundingMode.HALF_UP);
                                                                                                BigDecimal t9 = aggr8.multiply(examMergeBean.getMark9());
                                                                                                BigDecimal tt = t1.add(t2).add(t3).add(t4).add(t5).add(t6).add(t7).add(t8).add(t9);
                                                                                                BigDecimal total = tt.divide(examMergeBean.getOutOff(), 2, RoundingMode.HALF_UP);
                                                                                                total = total.multiply(BigDecimal.valueOf(100));
                                                                                                double percentage = total.doubleValue();
                                                                                                ExamRecording exam = new ExamRecording();
                                                                                                GradingSystem system = new GradingSystem();
                                                                                                exam.setPercentage(percentage);
                                                                                                if (percentage <= 29) {
                                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("E");
                                                                                                } else if (percentage > 29 && percentage <= 34) {
                                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("D-");
                                                                                                } else if (percentage > 34 && percentage <= 39) {
                                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("D");
                                                                                                } else if (percentage > 39 && percentage <= 44) {
                                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("D+");
                                                                                                } else if (percentage > 44 && percentage <= 49) {
                                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("C-");
                                                                                                } else if (percentage > 49 && percentage <= 54) {
                                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("C");
                                                                                                } else if (percentage > 54 && percentage <= 59) {
                                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("C+");
                                                                                                } else if (percentage > 59 && percentage <= 64) {
                                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("B-");
                                                                                                } else if (percentage > 64 && percentage <= 69) {
                                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("B");
                                                                                                } else if (percentage > 69 && percentage <= 74) {
                                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("B+");
                                                                                                } else if (percentage > 74 && percentage <= 79) {
                                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("A-");
                                                                                                } else if (percentage > 79 && percentage <= 100) {
                                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("A");
                                                                                                } else {
                                                                                                    throw new BadRequestException("Invalid marks");
                                                                                                }
                                                                                                exam.setTerm(examMergeBean.getTerm());
                                                                                                exam.setYear(examMergeBean.getYear());
                                                                                                exam.setStudent(s);
                                                                                                exam.setTeacherSubjects(e8.getTeacherSubjects());
                                                                                                exam.setExamDate(examMergeBean.getExamRegister().getEffectiveDate());
                                                                                                exam.setExamRegister(examMergeBean.getExamRegister());
                                                                                                exam.setForms(e8.getForms());
                                                                                                exam.setTeachers(e8.getTeachers());
                                                                                                exam.setMark(tt.doubleValue());
                                                                                                exam.setGradingSystem(system);
                                                                                                exam.setOutOff(examMergeBean.getOutOff().doubleValue());
                                                                                                examRecordingRepo.save(exam);
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            } else {
                throw new BadRequestException("Totals don't add up");
            }
        } else if (examMergeBean.getSubjects() != null && examMergeBean.getForms() == null) {
            BigDecimal mark = examMergeBean.getMark1().add(examMergeBean.getMark2()).add(examMergeBean.getMark3()).add(examMergeBean.getMark4()).add(examMergeBean.getMark5()).add(examMergeBean.getMark6()).add(examMergeBean.getMark7()).add(examMergeBean.getMark8()).add(examMergeBean.getMark9());
            if (mark.equals(examMergeBean.getOutOff())) {
                List<ExamRecording> examRecordings = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam(), examMergeBean.getSubjects());
                List<ExamRecording> examRecordings1 = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam1(), examMergeBean.getSubjects());
                List<ExamRecording> examRecordings2 = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam2(), examMergeBean.getSubjects());
                List<ExamRecording> examRecordings3 = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam3(), examMergeBean.getSubjects());
                List<ExamRecording> examRecordings4 = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam4(), examMergeBean.getSubjects());
                List<ExamRecording> examRecordings5 = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam5(), examMergeBean.getSubjects());
                List<ExamRecording> examRecordings6 = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam6(), examMergeBean.getSubjects());
                List<ExamRecording> examRecordings7 = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam7(), examMergeBean.getSubjects());
                List<ExamRecording> examRecordings8 = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam8(), examMergeBean.getSubjects());

                if (!(examRecordings.isEmpty()) && !(examRecordings1.isEmpty()) && !(examRecordings2.isEmpty()) && !(examRecordings3.isEmpty()) && !(examRecordings4.isEmpty()) && !(examRecordings5.isEmpty()) && !(examRecordings6.isEmpty()) && !(examRecordings7.isEmpty()) && !(examRecordings8.isEmpty())) {
                    for (ExamRecording e : examRecordings) {
                        BigDecimal marks = BigDecimal.valueOf(e.getMark());
                        BigDecimal outOff = BigDecimal.valueOf(e.getOutOff());
                        BigDecimal aggr = marks.divide(outOff, 2, RoundingMode.HALF_UP);
                        BigDecimal t1 = aggr.multiply(examMergeBean.getMark1());
                        for (ExamRecording e1 : examRecordings1) {
                            if (e.getTeacherSubjects().getSubjects() == e1.getTeacherSubjects().getSubjects() && e.getStudent()
                                    == e1.getStudent()) {
                                BigDecimal marks1 = BigDecimal.valueOf(e1.getMark());
                                BigDecimal outOff1 = BigDecimal.valueOf(e1.getOutOff());
                                BigDecimal aggr1 = marks1.divide(outOff1, 2, RoundingMode.HALF_UP);
                                BigDecimal t2 = aggr1.multiply(examMergeBean.getMark2());
                                for (ExamRecording e2 : examRecordings2) {

                                    if (e1.getTeacherSubjects().getSubjects() == e2.getTeacherSubjects().getSubjects() && e1.getStudent()
                                            == e2.getStudent()) {
                                        BigDecimal marks2 = BigDecimal.valueOf(e2.getMark());
                                        BigDecimal outOff2 = BigDecimal.valueOf(e2.getOutOff());
                                        BigDecimal aggr2 = marks2.divide(outOff2, 2, RoundingMode.HALF_UP);
                                        BigDecimal t3 = aggr2.multiply(examMergeBean.getMark3());
                                        for (ExamRecording e3 : examRecordings3) {
                                            if (e2.getTeacherSubjects().getSubjects() == e3.getTeacherSubjects().getSubjects() && e2.getStudent()
                                                    == e3.getStudent()) {
                                                BigDecimal marks3 = BigDecimal.valueOf(e3.getMark());
                                                BigDecimal outOff3 = BigDecimal.valueOf(e3.getOutOff());
                                                BigDecimal aggr3 = marks3.divide(outOff3, 2, RoundingMode.HALF_UP);
                                                BigDecimal t4 = aggr3.multiply(examMergeBean.getMark4());
                                                for (ExamRecording e4 : examRecordings4) {
                                                    if (e3.getTeacherSubjects().getSubjects() == e4.getTeacherSubjects().getSubjects() && e3.getStudent()
                                                            == e4.getStudent()) {
                                                        BigDecimal marks4 = BigDecimal.valueOf(e4.getMark());
                                                        BigDecimal outOff4 = BigDecimal.valueOf(e4.getOutOff());
                                                        BigDecimal aggr4 = marks4.divide(outOff4, 2, RoundingMode.HALF_UP);
                                                        BigDecimal t5 = aggr4.multiply(examMergeBean.getMark5());
                                                        for (ExamRecording e5 : examRecordings5) {
                                                            if (e4.getTeacherSubjects().getSubjects() == e5.getTeacherSubjects().getSubjects() && e4.getStudent() == e5.getStudent()) {
                                                                BigDecimal marks5 = BigDecimal.valueOf(e5.getMark());
                                                                BigDecimal outOff5 = BigDecimal.valueOf(e5.getOutOff());
                                                                BigDecimal aggr5 = marks5.divide(outOff5, 2, RoundingMode.HALF_UP);
                                                                BigDecimal t6 = aggr5.multiply(examMergeBean.getMark6());
                                                                for (ExamRecording e6 : examRecordings6) {
                                                                    if (e5.getTeacherSubjects().getSubjects() == e6.getTeacherSubjects().getSubjects() && e5.getStudent() == e6.getStudent()) {
                                                                        BigDecimal marks6 = BigDecimal.valueOf(e6.getMark());
                                                                        BigDecimal outOff6 = BigDecimal.valueOf(e6.getOutOff());
                                                                        BigDecimal aggr6 = marks6.divide(outOff6, 2, RoundingMode.HALF_UP);
                                                                        BigDecimal t7 = aggr6.multiply(examMergeBean.getMark7());
                                                                        for (ExamRecording e7 : examRecordings7) {
                                                                            if (e6.getTeacherSubjects().getSubjects() == e7.getTeacherSubjects().getSubjects() && e6.getStudent() == e7.getStudent()) {
                                                                                BigDecimal marks7 = BigDecimal.valueOf(e7.getMark());
                                                                                BigDecimal outOff7 = BigDecimal.valueOf(e7.getOutOff());
                                                                                BigDecimal aggr7 = marks7.divide(outOff7, 2, RoundingMode.HALF_UP);
                                                                                BigDecimal t8 = aggr7.multiply(examMergeBean.getMark8());
                                                                                for (ExamRecording e8 : examRecordings8) {
                                                                                    if (e7.getTeacherSubjects().getSubjects() == e8.getTeacherSubjects().getSubjects() && e7.getStudent() == e8.getStudent()) {
                                                                                        BigDecimal marks8 = BigDecimal.valueOf(e8.getMark());
                                                                                        BigDecimal outOff8 = BigDecimal.valueOf(e8.getOutOff());
                                                                                        BigDecimal aggr8 = marks8.divide(outOff8, 2, RoundingMode.HALF_UP);
                                                                                        BigDecimal t9 = aggr8.multiply(examMergeBean.getMark9());
                                                                                        BigDecimal tt = t1.add(t2).add(t3).add(t4).add(t5).add(t6).add(t7).add(t8).add(t9);
                                                                                        BigDecimal total = tt.divide(examMergeBean.getOutOff(), 2, RoundingMode.HALF_UP);
                                                                                        total = total.multiply(BigDecimal.valueOf(100));
                                                                                        double percentage = total.doubleValue();
                                                                                        ExamRecording exam = new ExamRecording();
                                                                                        GradingSystem system = new GradingSystem();
                                                                                        exam.setPercentage(percentage);
                                                                                        if (percentage <= 29) {
                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("E");
                                                                                        } else if (percentage > 29 && percentage <= 34) {
                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("D-");
                                                                                        } else if (percentage > 34 && percentage <= 39) {
                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("D");
                                                                                        } else if (percentage > 39 && percentage <= 44) {
                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("D+");
                                                                                        } else if (percentage > 44 && percentage <= 49) {
                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("C-");
                                                                                        } else if (percentage > 49 && percentage <= 54) {
                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("C");
                                                                                        } else if (percentage > 54 && percentage <= 59) {
                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("C+");
                                                                                        } else if (percentage > 59 && percentage <= 64) {
                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("B-");
                                                                                        } else if (percentage > 64 && percentage <= 69) {
                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("B");
                                                                                        } else if (percentage > 69 && percentage <= 74) {
                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("B+");
                                                                                        } else if (percentage > 74 && percentage <= 79) {
                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("A-");
                                                                                        } else if (percentage > 79 && percentage <= 100) {
                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("A");
                                                                                        } else {
                                                                                            throw new BadRequestException("Invalid marks");
                                                                                        }
                                                                                        exam.setTerm(examMergeBean.getTerm());
                                                                                        exam.setYear(examMergeBean.getYear());
                                                                                        exam.setStudent(e7.getStudent());
                                                                                        exam.setTeacherSubjects(e7.getTeacherSubjects());
                                                                                        exam.setExamDate(examMergeBean.getExamRegister().getEffectiveDate());
                                                                                        exam.setExamRegister(examMergeBean.getExamRegister());
                                                                                        exam.setForms(e7.getForms());
                                                                                        exam.setTeachers(e7.getTeachers());
                                                                                        exam.setMark(tt.doubleValue());
                                                                                        exam.setGradingSystem(system);
                                                                                        exam.setOutOff(examMergeBean.getOutOff().doubleValue());
                                                                                        examRecordingRepo.save(exam);
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                    }

                }
            } else {
                throw new BadRequestException("Totals don't add up");
            }
        } else if (examMergeBean.getSubjects() != null && examMergeBean.getForms() != null) {
            BigDecimal mark = examMergeBean.getMark1().add(examMergeBean.getMark2()).add(examMergeBean.getMark3()).add(examMergeBean.getMark4()).add(examMergeBean.getMark5()).add(examMergeBean.getMark6()).add(examMergeBean.getMark7()).add(examMergeBean.getMark8()).add(examMergeBean.getMark9());
            if (mark.equals(examMergeBean.getOutOff())) {
                List<Student> students = studentRepo.findByForms_ClassCode(examMergeBean.getForms().getClassCode());
                if (!(students.isEmpty())) {
                    for (Student s : students) {
                        List<ExamRecording> examRecordings = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam(), s, examMergeBean.getSubjects());
                        List<ExamRecording> examRecordings1 = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam1(), s, examMergeBean.getSubjects());
                        List<ExamRecording> examRecordings2 = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam2(), s, examMergeBean.getSubjects());
                        List<ExamRecording> examRecordings3 = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam3(), s, examMergeBean.getSubjects());
                        List<ExamRecording> examRecordings4 = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam4(), s, examMergeBean.getSubjects());
                        List<ExamRecording> examRecordings5 = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam5(), s, examMergeBean.getSubjects());
                        List<ExamRecording> examRecordings6 = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam6(), s, examMergeBean.getSubjects());
                        List<ExamRecording> examRecordings7 = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam7(), s, examMergeBean.getSubjects());
                        List<ExamRecording> examRecordings8 = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam8(), s, examMergeBean.getSubjects());

                        if (!(examRecordings.isEmpty()) && !(examRecordings1.isEmpty()) && !(examRecordings2.isEmpty()) && !(examRecordings3.isEmpty()) && !(examRecordings4.isEmpty()) && !(examRecordings5.isEmpty()) && !(examRecordings6.isEmpty()) && !(examRecordings7.isEmpty()) && !(examRecordings8.isEmpty())) {
                            for (ExamRecording e : examRecordings) {
                                BigDecimal marks = BigDecimal.valueOf(e.getMark());
                                BigDecimal outOff = BigDecimal.valueOf(e.getOutOff());
                                BigDecimal aggr = marks.divide(outOff, 2, RoundingMode.HALF_UP);
                                BigDecimal t1 = aggr.multiply(examMergeBean.getMark1());

                                for (ExamRecording e1 : examRecordings1) {
                                    if (e.getTeacherSubjects().getSubjects() == e1.getTeacherSubjects().getSubjects() && e.getStudent() == e1.getStudent()) {
                                        BigDecimal marks1 = BigDecimal.valueOf(e1.getMark());
                                        BigDecimal outOff1 = BigDecimal.valueOf(e1.getOutOff());
                                        BigDecimal aggr1 = marks1.divide(outOff1, 2, RoundingMode.HALF_UP);
                                        BigDecimal t2 = aggr1.multiply(examMergeBean.getMark2());
                                        for (ExamRecording e2 : examRecordings2) {
                                            if (e1.getTeacherSubjects().getSubjects() == e2.getTeacherSubjects().getSubjects() && e1.getStudent() == e2.getStudent()) {
                                                BigDecimal marks2 = BigDecimal.valueOf(e2.getMark());
                                                BigDecimal outOff2 = BigDecimal.valueOf(e2.getOutOff());
                                                BigDecimal aggr2 = marks2.divide(outOff2, 2, RoundingMode.HALF_UP);
                                                BigDecimal t3 = aggr2.multiply(examMergeBean.getMark3());
                                                for (ExamRecording e3 : examRecordings3) {
                                                    if (e2.getTeacherSubjects().getSubjects() == e3.getTeacherSubjects().getSubjects() && e2.getStudent() == e3.getStudent()) {
                                                        BigDecimal marks3 = BigDecimal.valueOf(e3.getMark());
                                                        BigDecimal outOff3 = BigDecimal.valueOf(e3.getOutOff());
                                                        BigDecimal aggr3 = marks3.divide(outOff3, 2, RoundingMode.HALF_UP);
                                                        BigDecimal t4 = aggr3.multiply(examMergeBean.getMark4());
                                                        for (ExamRecording e4 : examRecordings4) {
                                                            if (e3.getTeacherSubjects().getSubjects() == e4.getTeacherSubjects().getSubjects() && e3.getStudent() == e4.getStudent()) {
                                                                BigDecimal marks4 = BigDecimal.valueOf(e4.getMark());
                                                                BigDecimal outOff4 = BigDecimal.valueOf(e4.getOutOff());
                                                                BigDecimal aggr4 = marks4.divide(outOff4, 2, RoundingMode.HALF_UP);
                                                                BigDecimal t5 = aggr4.multiply(examMergeBean.getMark5());
                                                                for (ExamRecording e5 : examRecordings5) {
                                                                    if (e4.getTeacherSubjects().getSubjects() == e5.getTeacherSubjects().getSubjects() && e4.getStudent() == e5.getStudent()) {
                                                                        BigDecimal marks5 = BigDecimal.valueOf(e5.getMark());
                                                                        BigDecimal outOff5 = BigDecimal.valueOf(e5.getOutOff());
                                                                        BigDecimal aggr5 = marks5.divide(outOff5, 2, RoundingMode.HALF_UP);
                                                                        BigDecimal t6 = aggr5.multiply(examMergeBean.getMark6());
                                                                        for (ExamRecording e6 : examRecordings6) {
                                                                            if (e5.getTeacherSubjects().getSubjects() == e6.getTeacherSubjects().getSubjects() && e5.getStudent() == e6.getStudent()) {
                                                                                BigDecimal marks6 = BigDecimal.valueOf(e6.getMark());
                                                                                BigDecimal outOff6 = BigDecimal.valueOf(e6.getOutOff());
                                                                                BigDecimal aggr6 = marks6.divide(outOff6, 2, RoundingMode.HALF_UP);
                                                                                BigDecimal t7 = aggr6.multiply(examMergeBean.getMark7());
                                                                                for (ExamRecording e7 : examRecordings7) {
                                                                                    if (e6.getTeacherSubjects().getSubjects() == e7.getTeacherSubjects().getSubjects() && e6.getStudent() == e7.getStudent()) {
                                                                                        BigDecimal marks7 = BigDecimal.valueOf(e7.getMark());
                                                                                        BigDecimal outOff7 = BigDecimal.valueOf(e7.getOutOff());
                                                                                        BigDecimal aggr7 = marks7.divide(outOff7, 2, RoundingMode.HALF_UP);
                                                                                        BigDecimal t8 = aggr7.multiply(examMergeBean.getMark8());
                                                                                        for (ExamRecording e8 : examRecordings8) {
                                                                                            if (e7.getTeacherSubjects().getSubjects() == e8.getTeacherSubjects().getSubjects() && e7.getStudent() == e8.getStudent()) {
                                                                                                BigDecimal marks8 = BigDecimal.valueOf(e8.getMark());
                                                                                                BigDecimal outOff8 = BigDecimal.valueOf(e8.getOutOff());
                                                                                                BigDecimal aggr8 = marks8.divide(outOff8, 2, RoundingMode.HALF_UP);
                                                                                                BigDecimal t9 = aggr8.multiply(examMergeBean.getMark9());
                                                                                                BigDecimal tt = t1.add(t2).add(t3).add(t4).add(t5).add(t6).add(t7).add(t8).add(t9);
                                                                                                BigDecimal total = tt.divide(examMergeBean.getOutOff(), 2, RoundingMode.HALF_UP);
                                                                                                total = total.multiply(BigDecimal.valueOf(100));
                                                                                                double percentage = total.doubleValue();
                                                                                                ExamRecording exam = new ExamRecording();
                                                                                                GradingSystem system = new GradingSystem();
                                                                                                exam.setPercentage(percentage);
                                                                                                if (percentage <= 29) {
                                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("E");
                                                                                                } else if (percentage > 29 && percentage <= 34) {
                                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("D-");
                                                                                                } else if (percentage > 34 && percentage <= 39) {
                                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("D");
                                                                                                } else if (percentage > 39 && percentage <= 44) {
                                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("D+");
                                                                                                } else if (percentage > 44 && percentage <= 49) {
                                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("C-");
                                                                                                } else if (percentage > 49 && percentage <= 54) {
                                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("C");
                                                                                                } else if (percentage > 54 && percentage <= 59) {
                                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("C+");
                                                                                                } else if (percentage > 59 && percentage <= 64) {
                                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("B-");
                                                                                                } else if (percentage > 64 && percentage <= 69) {
                                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("B");
                                                                                                } else if (percentage > 69 && percentage <= 74) {
                                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("B+");
                                                                                                } else if (percentage > 74 && percentage <= 79) {
                                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("A-");
                                                                                                } else if (percentage > 79 && percentage <= 100) {
                                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("A");
                                                                                                } else {
                                                                                                    throw new BadRequestException("Invalid marks");
                                                                                                }
                                                                                                exam.setGradingSystem(system);
                                                                                                exam.setTerm(examMergeBean.getTerm());
                                                                                                exam.setYear(examMergeBean.getYear());
                                                                                                exam.setStudent(s);
                                                                                                exam.setTeacherSubjects(e8.getTeacherSubjects());
                                                                                                exam.setExamDate(examMergeBean.getExamRegister().getEffectiveDate());
                                                                                                exam.setExamRegister(examMergeBean.getExamRegister());
                                                                                                exam.setForms(e8.getForms());
                                                                                                exam.setTeachers(e8.getTeachers());
                                                                                                exam.setMark(tt.doubleValue());
                                                                                                exam.setOutOff(examMergeBean.getOutOff().doubleValue());
                                                                                                examRecordingRepo.save(exam);
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            } else {
                throw new BadRequestException("Totals don't add up");
            }
        } else {
            BigDecimal mark = examMergeBean.getMark1().add(examMergeBean.getMark2()).add(examMergeBean.getMark3()).add(examMergeBean.getMark4()).add(examMergeBean.getMark5()).add(examMergeBean.getMark6()).add(examMergeBean.getMark7()).add(examMergeBean.getMark8()).add(examMergeBean.getMark9());
            if (mark.equals(examMergeBean.getOutOff())) {
                List<Student> students = studentRepo.findAllByOrderByForms();
                for (Student s : students) {
                    List<ExamRecording> examRecordings = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam(), s);
                    List<ExamRecording> examRecordings1 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam1(), s);
                    List<ExamRecording> examRecordings2 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam2(), s);
                    List<ExamRecording> examRecordings3 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam3(), s);
                    List<ExamRecording> examRecordings4 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam4(), s);
                    List<ExamRecording> examRecordings5 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam5(), s);
                    List<ExamRecording> examRecordings6 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam6(), s);
                    List<ExamRecording> examRecordings7 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam7(), s);
                    List<ExamRecording> examRecordings8 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam8(), s);

                    if (!(examRecordings.isEmpty()) && !(examRecordings1.isEmpty()) && !(examRecordings2.isEmpty()) && !(examRecordings3.isEmpty()) && !(examRecordings4.isEmpty()) && !(examRecordings5.isEmpty()) && !(examRecordings6.isEmpty()) && !(examRecordings7.isEmpty()) && !(examRecordings8.isEmpty())) {
                        for (ExamRecording e : examRecordings) {
                            BigDecimal marks = BigDecimal.valueOf(e.getMark());
                            BigDecimal outOff = BigDecimal.valueOf(e.getOutOff());
                            BigDecimal aggr = marks.divide(outOff, 2, RoundingMode.HALF_UP);
                            BigDecimal t1 = aggr.multiply(examMergeBean.getMark1());
                            for (ExamRecording e1 : examRecordings1) {
                                if (e.getTeacherSubjects().getSubjects() == e1.getTeacherSubjects().getSubjects()) {
                                    BigDecimal marks1 = BigDecimal.valueOf(e1.getMark());
                                    BigDecimal outOff1 = BigDecimal.valueOf(e1.getOutOff());
                                    BigDecimal aggr1 = marks1.divide(outOff1, 2, RoundingMode.HALF_UP);
                                    BigDecimal t2 = aggr1.multiply(examMergeBean.getMark2());
                                    for (ExamRecording e2 : examRecordings2) {
                                        if (e1.getTeacherSubjects().getSubjects() == e2.getTeacherSubjects().getSubjects()) {
                                            BigDecimal marks2 = BigDecimal.valueOf(e2.getMark());
                                            BigDecimal outOff2 = BigDecimal.valueOf(e2.getOutOff());
                                            BigDecimal aggr2 = marks2.divide(outOff2, 2, RoundingMode.HALF_UP);
                                            BigDecimal t3 = aggr2.multiply(examMergeBean.getMark3());
                                            for (ExamRecording e3 : examRecordings3) {
                                                if (e2.getTeacherSubjects().getSubjects() == e3.getTeacherSubjects().getSubjects()) {
                                                    BigDecimal marks3 = BigDecimal.valueOf(e3.getMark());
                                                    BigDecimal outOff3 = BigDecimal.valueOf(e3.getOutOff());
                                                    BigDecimal aggr3 = marks3.divide(outOff3, 2, RoundingMode.HALF_UP);
                                                    BigDecimal t4 = aggr3.multiply(examMergeBean.getMark4());
                                                    for (ExamRecording e4 : examRecordings4) {
                                                        if (e3.getTeacherSubjects().getSubjects() == e4.getTeacherSubjects().getSubjects()) {
                                                            BigDecimal marks4 = BigDecimal.valueOf(e4.getMark());
                                                            BigDecimal outOff4 = BigDecimal.valueOf(e4.getOutOff());
                                                            BigDecimal aggr4 = marks4.divide(outOff4, 2, RoundingMode.HALF_UP);
                                                            BigDecimal t5 = aggr4.multiply(examMergeBean.getMark5());
                                                            for (ExamRecording e5 : examRecordings5) {
                                                                if (e4.getTeacherSubjects().getSubjects() == e5.getTeacherSubjects().getSubjects()) {
                                                                    BigDecimal marks5 = BigDecimal.valueOf(e5.getMark());
                                                                    BigDecimal outOff5 = BigDecimal.valueOf(e5.getOutOff());
                                                                    BigDecimal aggr5 = marks5.divide(outOff5, 2, RoundingMode.HALF_UP);
                                                                    BigDecimal t6 = aggr5.multiply(examMergeBean.getMark6());
                                                                    for (ExamRecording e6 : examRecordings6) {
                                                                        if (e5.getTeacherSubjects().getSubjects() == e6.getTeacherSubjects().getSubjects()) {
                                                                            BigDecimal marks6 = BigDecimal.valueOf(e6.getMark());
                                                                            BigDecimal outOff6 = BigDecimal.valueOf(e6.getOutOff());
                                                                            BigDecimal aggr6 = marks6.divide(outOff6, 2, RoundingMode.HALF_UP);
                                                                            BigDecimal t7 = aggr6.multiply(examMergeBean.getMark7());
                                                                            for (ExamRecording e7 : examRecordings7) {
                                                                                if (e6.getTeacherSubjects().getSubjects() == e7.getTeacherSubjects().getSubjects()) {
                                                                                    BigDecimal marks7 = BigDecimal.valueOf(e7.getMark());
                                                                                    BigDecimal outOff7 = BigDecimal.valueOf(e7.getOutOff());
                                                                                    BigDecimal aggr7 = marks7.divide(outOff7, 2, RoundingMode.HALF_UP);
                                                                                    BigDecimal t8 = aggr7.multiply(examMergeBean.getMark7());
                                                                                    for (ExamRecording e8 : examRecordings8) {
                                                                                        if (e7.getTeacherSubjects().getSubjects() == e8.getTeacherSubjects().getSubjects()) {
                                                                                            BigDecimal marks8 = BigDecimal.valueOf(e8.getMark());
                                                                                            BigDecimal outOff8 = BigDecimal.valueOf(e8.getOutOff());
                                                                                            BigDecimal aggr8 = marks8.divide(outOff8, 2, RoundingMode.HALF_UP);
                                                                                            BigDecimal t9 = aggr8.multiply(examMergeBean.getMark9());
                                                                                            BigDecimal tt = t1.add(t2).add(t3).add(t4).add(t5).add(t6).add(t7).add(t8).add(t9);
                                                                                            BigDecimal total = tt.divide(examMergeBean.getOutOff(), 2, RoundingMode.HALF_UP);
                                                                                            total = total.multiply(BigDecimal.valueOf(100));
                                                                                            double percentage = total.doubleValue();
                                                                                            ExamRecording exam = new ExamRecording();
                                                                                            GradingSystem system = new GradingSystem();
                                                                                            exam.setPercentage(percentage);
                                                                                            if (percentage <= 29) {
                                                                                                system = gradingSysRepo.findByGradeIgnoreCase("E");
                                                                                            } else if (percentage > 29 && percentage <= 34) {
                                                                                                system = gradingSysRepo.findByGradeIgnoreCase("D-");
                                                                                            } else if (percentage > 34 && percentage <= 39) {
                                                                                                system = gradingSysRepo.findByGradeIgnoreCase("D");
                                                                                            } else if (percentage > 39 && percentage <= 44) {
                                                                                                system = gradingSysRepo.findByGradeIgnoreCase("D+");
                                                                                            } else if (percentage > 44 && percentage <= 49) {
                                                                                                system = gradingSysRepo.findByGradeIgnoreCase("C-");
                                                                                            } else if (percentage > 49 && percentage <= 54) {
                                                                                                system = gradingSysRepo.findByGradeIgnoreCase("C");
                                                                                            } else if (percentage > 54 && percentage <= 59) {
                                                                                                system = gradingSysRepo.findByGradeIgnoreCase("C+");
                                                                                            } else if (percentage > 59 && percentage <= 64) {
                                                                                                system = gradingSysRepo.findByGradeIgnoreCase("B-");
                                                                                            } else if (percentage > 64 && percentage <= 69) {
                                                                                                system = gradingSysRepo.findByGradeIgnoreCase("B");
                                                                                            } else if (percentage > 69 && percentage <= 74) {
                                                                                                system = gradingSysRepo.findByGradeIgnoreCase("B+");
                                                                                            } else if (percentage > 74 && percentage <= 79) {
                                                                                                system = gradingSysRepo.findByGradeIgnoreCase("A-");
                                                                                            } else if (percentage > 79 && percentage <= 100) {
                                                                                                system = gradingSysRepo.findByGradeIgnoreCase("A");
                                                                                            } else {
                                                                                                throw new BadRequestException("Invalid marks");
                                                                                            }
                                                                                            exam.setGradingSystem(system);
                                                                                            exam.setTerm(examMergeBean.getTerm());
                                                                                            exam.setYear(examMergeBean.getYear());
                                                                                            exam.setStudent(s);
                                                                                            exam.setTeacherSubjects(e8.getTeacherSubjects());
                                                                                            exam.setExamDate(examMergeBean.getExamRegister().getEffectiveDate());
                                                                                            exam.setExamRegister(examMergeBean.getExamRegister());
                                                                                            exam.setForms(e8.getForms());
                                                                                            exam.setTeachers(e8.getTeachers());
                                                                                            exam.setMark(tt.doubleValue());
                                                                                            exam.setOutOff(examMergeBean.getOutOff().doubleValue());
                                                                                            examRecordingRepo.save(exam);
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            } else {
                throw new BadRequestException("Totals don't add up");
            }


        }
    }

    @Override
    public void mergeTenExams(ExamMergeBean examMergeBean) throws BadRequestException {
        if (examMergeBean.getForms() != null && examMergeBean.getSubjects() == null) {
            BigDecimal mark = examMergeBean.getMark1().add(examMergeBean.getMark2()).add(examMergeBean.getMark3()).add(examMergeBean.getMark4()).add(examMergeBean.getMark5()).add(examMergeBean.getMark6()).add(examMergeBean.getMark7()).add(examMergeBean.getMark8()).add(examMergeBean.getMark9()).add(examMergeBean.getMark10());
            if (mark.equals(examMergeBean.getOutOff())) {
                List<Student> students = studentRepo.findByForms_ClassCode(examMergeBean.getForms().getClassCode());
                if (!(students.isEmpty())) {
                    for (Student s : students) {
                        List<ExamRecording> examRecordings = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam(), s);
                        List<ExamRecording> examRecordings1 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam1(), s);
                        List<ExamRecording> examRecordings2 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam2(), s);
                        List<ExamRecording> examRecordings3 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam3(), s);
                        List<ExamRecording> examRecordings4 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam4(), s);
                        List<ExamRecording> examRecordings5 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam5(), s);
                        List<ExamRecording> examRecordings6 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam6(), s);
                        List<ExamRecording> examRecordings7 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam7(), s);
                        List<ExamRecording> examRecordings8 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam8(), s);
                        List<ExamRecording> examRecordings9 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam9(), s);

                        if (!(examRecordings.isEmpty()) && !(examRecordings1.isEmpty()) && !(examRecordings2.isEmpty()) && !(examRecordings3.isEmpty()) && !(examRecordings4.isEmpty()) && !(examRecordings5.isEmpty()) && !(examRecordings6.isEmpty()) && !(examRecordings7.isEmpty()) && !(examRecordings8.isEmpty()) && !(examRecordings9.isEmpty())) {
                            for (ExamRecording e : examRecordings) {
                                BigDecimal marks = BigDecimal.valueOf(e.getMark());
                                BigDecimal outOff = BigDecimal.valueOf(e.getOutOff());
                                BigDecimal aggr = marks.divide(outOff, 2, RoundingMode.HALF_UP);
                                BigDecimal t1 = aggr.multiply(examMergeBean.getMark1());
                                for (ExamRecording e1 : examRecordings1) {
                                    if (e.getTeacherSubjects().getSubjects() == e1.getTeacherSubjects().getSubjects()) {
                                        BigDecimal marks1 = BigDecimal.valueOf(e1.getMark());
                                        BigDecimal outOff1 = BigDecimal.valueOf(e1.getOutOff());
                                        BigDecimal aggr1 = marks1.divide(outOff1, 2, RoundingMode.HALF_UP);
                                        BigDecimal t2 = aggr1.multiply(examMergeBean.getMark2());
                                        for (ExamRecording e2 : examRecordings2) {
                                            if (e1.getTeacherSubjects().getSubjects()
                                                    == e2.getTeacherSubjects().getSubjects()) {
                                                BigDecimal marks2 = BigDecimal.valueOf(e2.getMark());
                                                BigDecimal outOff2 = BigDecimal.valueOf(e2.getOutOff());
                                                BigDecimal aggr2 = marks2.divide(outOff2, 2, RoundingMode.HALF_UP);
                                                BigDecimal t3 = aggr2.multiply(examMergeBean.getMark3());
                                                for (ExamRecording e3 : examRecordings3) {
                                                    if (e2.getTeacherSubjects().getSubjects()
                                                            == e3.getTeacherSubjects().getSubjects()) {
                                                        BigDecimal marks3 = BigDecimal.valueOf(e3.getMark());
                                                        BigDecimal outOff3 = BigDecimal.valueOf(e3.getOutOff());
                                                        BigDecimal aggr3 = marks3.divide(outOff3, 2, RoundingMode.HALF_UP);
                                                        BigDecimal t4 = aggr3.multiply(examMergeBean.getMark4());
                                                        for (ExamRecording e4 : examRecordings4) {
                                                            if (e3.getTeacherSubjects().getSubjects()
                                                                    == e4.getTeacherSubjects().getSubjects()) {

                                                                BigDecimal marks4 = BigDecimal.valueOf(e4.getMark());
                                                                BigDecimal outOff4 = BigDecimal.valueOf(e4.getOutOff());
                                                                BigDecimal aggr4 = marks4.divide(outOff4, 2, RoundingMode.HALF_UP);
                                                                BigDecimal t5 = aggr4.multiply(examMergeBean.getMark5());
                                                                for (ExamRecording e5 : examRecordings5) {
                                                                    if (e4.getTeacherSubjects().getSubjects()
                                                                            == e5.getTeacherSubjects().getSubjects()) {
                                                                        BigDecimal marks5 = BigDecimal.valueOf(e5.getMark());
                                                                        BigDecimal outOff5 = BigDecimal.valueOf(e5.getOutOff());
                                                                        BigDecimal aggr5 = marks5.divide(outOff5, 2, RoundingMode.HALF_UP);
                                                                        BigDecimal t6 = aggr5.multiply(examMergeBean.getMark6());
                                                                        for (ExamRecording e6 : examRecordings6) {
                                                                            if (e5.getTeacherSubjects().getSubjects()
                                                                                    == e6.getTeacherSubjects().getSubjects()) {
                                                                                BigDecimal marks6 = BigDecimal.valueOf(e6.getMark());
                                                                                BigDecimal outOff6 = BigDecimal.valueOf(e6.getOutOff());
                                                                                BigDecimal aggr6 = marks6.divide(outOff6, 2, RoundingMode.HALF_UP);
                                                                                BigDecimal t7 = aggr6.multiply(examMergeBean.getMark7());
                                                                                for (ExamRecording e7 : examRecordings7) {
                                                                                    if (e6.getTeacherSubjects().getSubjects()
                                                                                            == e7.getTeacherSubjects().getSubjects()) {
                                                                                        BigDecimal marks7 = BigDecimal.valueOf(e7.getMark());
                                                                                        BigDecimal outOff7 = BigDecimal.valueOf(e7.getOutOff());
                                                                                        BigDecimal aggr7 = marks7.divide(outOff7, 2, RoundingMode.HALF_UP);
                                                                                        BigDecimal t8 = aggr7.multiply(examMergeBean.getMark8());
                                                                                        for (ExamRecording e8 : examRecordings8) {
                                                                                            if (e7.getTeacherSubjects().getSubjects()
                                                                                                    == e8.getTeacherSubjects().getSubjects()) {
                                                                                                BigDecimal marks8 = BigDecimal.valueOf(e8.getMark());
                                                                                                BigDecimal outOff8 = BigDecimal.valueOf(e8.getOutOff());
                                                                                                BigDecimal aggr8 = marks8.divide(outOff8, 2, RoundingMode.HALF_UP);
                                                                                                BigDecimal t9 = aggr8.multiply(examMergeBean.getMark9());
                                                                                                for (ExamRecording e9 : examRecordings9) {
                                                                                                    if (e8.getTeacherSubjects().getSubjects()
                                                                                                            == e9.getTeacherSubjects().getSubjects()) {
                                                                                                        BigDecimal marks9 = BigDecimal.valueOf(e9.getMark());
                                                                                                        BigDecimal outOff9 = BigDecimal.valueOf(e9.getOutOff());
                                                                                                        BigDecimal aggr9 = marks9.divide(outOff9, 2, RoundingMode.HALF_UP);
                                                                                                        BigDecimal t10 = aggr9.multiply(examMergeBean.getMark10());
                                                                                                        BigDecimal tt = t1.add(t2).add(t3).add(t4).add(t5).add(t6).add(t7).add(t8).add(t9).add(t10);
                                                                                                        BigDecimal total = tt.divide(examMergeBean.getOutOff(), 2, RoundingMode.HALF_UP);
                                                                                                        total = total.multiply(BigDecimal.valueOf(100));
                                                                                                        double percentage = total.doubleValue();
                                                                                                        ExamRecording exam = new ExamRecording();
                                                                                                        GradingSystem system = new GradingSystem();
                                                                                                        exam.setPercentage(percentage);
                                                                                                        if (percentage <= 29) {
                                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("E");
                                                                                                        } else if (percentage > 29 && percentage <= 34) {
                                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("D-");
                                                                                                        } else if (percentage > 34 && percentage <= 39) {
                                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("D");
                                                                                                        } else if (percentage > 39 && percentage <= 44) {
                                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("D+");
                                                                                                        } else if (percentage > 44 && percentage <= 49) {
                                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("C-");
                                                                                                        } else if (percentage > 49 && percentage <= 54) {
                                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("C");
                                                                                                        } else if (percentage > 54 && percentage <= 59) {
                                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("C+");
                                                                                                        } else if (percentage > 59 && percentage <= 64) {
                                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("B-");
                                                                                                        } else if (percentage > 64 && percentage <= 69) {
                                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("B");
                                                                                                        } else if (percentage > 69 && percentage <= 74) {
                                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("B+");
                                                                                                        } else if (percentage > 74 && percentage <= 79) {
                                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("A-");
                                                                                                        } else if (percentage > 79 && percentage <= 100) {
                                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("A");
                                                                                                        } else {
                                                                                                            throw new BadRequestException("Invalid marks");
                                                                                                        }
                                                                                                        exam.setTerm(examMergeBean.getTerm());
                                                                                                        exam.setYear(examMergeBean.getYear());
                                                                                                        exam.setStudent(s);
                                                                                                        exam.setTeacherSubjects(e9.getTeacherSubjects());
                                                                                                        exam.setExamDate(examMergeBean.getExamRegister().getEffectiveDate());
                                                                                                        exam.setExamRegister(examMergeBean.getExamRegister());
                                                                                                        exam.setForms(e9.getForms());
                                                                                                        exam.setTeachers(e9.getTeachers());
                                                                                                        exam.setMark(tt.doubleValue());
                                                                                                        exam.setGradingSystem(system);
                                                                                                        exam.setOutOff(examMergeBean.getOutOff().doubleValue());
                                                                                                        examRecordingRepo.save(exam);
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            } else {
                throw new BadRequestException("Totals don't add up");
            }
        } else if (examMergeBean.getSubjects() != null && examMergeBean.getForms() == null) {
            BigDecimal mark = examMergeBean.getMark1().add(examMergeBean.getMark2()).add(examMergeBean.getMark3()).add(examMergeBean.getMark4()).add(examMergeBean.getMark5()).add(examMergeBean.getMark6()).add(examMergeBean.getMark7()).add(examMergeBean.getMark8()).add(examMergeBean.getMark9()).add(examMergeBean.getMark10());
            if (mark.equals(examMergeBean.getOutOff())) {
                List<ExamRecording> examRecordings = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam(), examMergeBean.getSubjects());
                List<ExamRecording> examRecordings1 = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam1(), examMergeBean.getSubjects());
                List<ExamRecording> examRecordings2 = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam2(), examMergeBean.getSubjects());
                List<ExamRecording> examRecordings3 = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam3(), examMergeBean.getSubjects());
                List<ExamRecording> examRecordings4 = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam4(), examMergeBean.getSubjects());
                List<ExamRecording> examRecordings5 = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam5(), examMergeBean.getSubjects());
                List<ExamRecording> examRecordings6 = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam6(), examMergeBean.getSubjects());
                List<ExamRecording> examRecordings7 = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam7(), examMergeBean.getSubjects());
                List<ExamRecording> examRecordings8 = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam8(), examMergeBean.getSubjects());
                List<ExamRecording> examRecordings9 = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam9(), examMergeBean.getSubjects());

                if (!(examRecordings.isEmpty()) && !(examRecordings1.isEmpty()) && !(examRecordings2.isEmpty()) && !(examRecordings3.isEmpty()) && !(examRecordings4.isEmpty()) && !(examRecordings5.isEmpty()) && !(examRecordings6.isEmpty()) && !(examRecordings7.isEmpty()) && !(examRecordings8.isEmpty()) && !(examRecordings9.isEmpty())) {
                    for (ExamRecording e : examRecordings) {
                        BigDecimal marks = BigDecimal.valueOf(e.getMark());
                        BigDecimal outOff = BigDecimal.valueOf(e.getOutOff());
                        BigDecimal aggr = marks.divide(outOff, 2, RoundingMode.HALF_UP);
                        BigDecimal t1 = aggr.multiply(examMergeBean.getMark1());
                        for (ExamRecording e1 : examRecordings1) {
                            if (e.getTeacherSubjects().getSubjects() == e1.getTeacherSubjects().getSubjects() && e.getStudent()
                                    == e1.getStudent()) {
                                BigDecimal marks1 = BigDecimal.valueOf(e1.getMark());
                                BigDecimal outOff1 = BigDecimal.valueOf(e1.getOutOff());
                                BigDecimal aggr1 = marks1.divide(outOff1, 2, RoundingMode.HALF_UP);
                                BigDecimal t2 = aggr1.multiply(examMergeBean.getMark2());
                                for (ExamRecording e2 : examRecordings2) {

                                    if (e1.getTeacherSubjects().getSubjects() == e2.getTeacherSubjects().getSubjects() && e1.getStudent()
                                            == e2.getStudent()) {
                                        BigDecimal marks2 = BigDecimal.valueOf(e2.getMark());
                                        BigDecimal outOff2 = BigDecimal.valueOf(e2.getOutOff());
                                        BigDecimal aggr2 = marks2.divide(outOff2, 2, RoundingMode.HALF_UP);
                                        BigDecimal t3 = aggr2.multiply(examMergeBean.getMark3());
                                        for (ExamRecording e3 : examRecordings3) {
                                            if (e2.getTeacherSubjects().getSubjects() == e3.getTeacherSubjects().getSubjects() && e2.getStudent()
                                                    == e3.getStudent()) {
                                                BigDecimal marks3 = BigDecimal.valueOf(e3.getMark());
                                                BigDecimal outOff3 = BigDecimal.valueOf(e3.getOutOff());
                                                BigDecimal aggr3 = marks3.divide(outOff3, 2, RoundingMode.HALF_UP);
                                                BigDecimal t4 = aggr3.multiply(examMergeBean.getMark4());
                                                for (ExamRecording e4 : examRecordings4) {
                                                    if (e3.getTeacherSubjects().getSubjects() == e4.getTeacherSubjects().getSubjects() && e3.getStudent()
                                                            == e4.getStudent()) {
                                                        BigDecimal marks4 = BigDecimal.valueOf(e4.getMark());
                                                        BigDecimal outOff4 = BigDecimal.valueOf(e4.getOutOff());
                                                        BigDecimal aggr4 = marks4.divide(outOff4, 2, RoundingMode.HALF_UP);
                                                        BigDecimal t5 = aggr4.multiply(examMergeBean.getMark5());
                                                        for (ExamRecording e5 : examRecordings5) {
                                                            if (e4.getTeacherSubjects().getSubjects() == e5.getTeacherSubjects().getSubjects() && e4.getStudent() == e5.getStudent()) {
                                                                BigDecimal marks5 = BigDecimal.valueOf(e5.getMark());
                                                                BigDecimal outOff5 = BigDecimal.valueOf(e5.getOutOff());
                                                                BigDecimal aggr5 = marks5.divide(outOff5, 2, RoundingMode.HALF_UP);
                                                                BigDecimal t6 = aggr5.multiply(examMergeBean.getMark6());
                                                                for (ExamRecording e6 : examRecordings6) {
                                                                    if (e5.getTeacherSubjects().getSubjects() == e6.getTeacherSubjects().getSubjects() && e5.getStudent() == e6.getStudent()) {
                                                                        BigDecimal marks6 = BigDecimal.valueOf(e6.getMark());
                                                                        BigDecimal outOff6 = BigDecimal.valueOf(e6.getOutOff());
                                                                        BigDecimal aggr6 = marks6.divide(outOff6, 2, RoundingMode.HALF_UP);
                                                                        BigDecimal t7 = aggr6.multiply(examMergeBean.getMark7());
                                                                        for (ExamRecording e7 : examRecordings7) {
                                                                            if (e6.getTeacherSubjects().getSubjects() == e7.getTeacherSubjects().getSubjects() && e6.getStudent() == e7.getStudent()) {
                                                                                BigDecimal marks7 = BigDecimal.valueOf(e7.getMark());
                                                                                BigDecimal outOff7 = BigDecimal.valueOf(e7.getOutOff());
                                                                                BigDecimal aggr7 = marks7.divide(outOff7, 2, RoundingMode.HALF_UP);
                                                                                BigDecimal t8 = aggr7.multiply(examMergeBean.getMark8());
                                                                                for (ExamRecording e8 : examRecordings8) {
                                                                                    if (e7.getTeacherSubjects().getSubjects() == e8.getTeacherSubjects().getSubjects() && e7.getStudent() == e8.getStudent()) {
                                                                                        BigDecimal marks8 = BigDecimal.valueOf(e8.getMark());
                                                                                        BigDecimal outOff8 = BigDecimal.valueOf(e8.getOutOff());
                                                                                        BigDecimal aggr8 = marks8.divide(outOff8, 2, RoundingMode.HALF_UP);
                                                                                        BigDecimal t9 = aggr8.multiply(examMergeBean.getMark9());
                                                                                        for (ExamRecording e9 : examRecordings9) {
                                                                                            if (e8.getTeacherSubjects().getSubjects() == e9.getTeacherSubjects().getSubjects() && e8.getStudent() == e9.getStudent()) {
                                                                                                BigDecimal marks9 = BigDecimal.valueOf(e9.getMark());
                                                                                                BigDecimal outOff9 = BigDecimal.valueOf(e9.getOutOff());
                                                                                                BigDecimal aggr9 = marks9.divide(outOff9, 2, RoundingMode.HALF_UP);
                                                                                                BigDecimal t10 = aggr9.multiply(examMergeBean.getMark10());
                                                                                                BigDecimal tt = t1.add(t2).add(t3).add(t4).add(t5).add(t6).add(t7).add(t8).add(t9).add(t10);
                                                                                                BigDecimal total = tt.divide(examMergeBean.getOutOff(), 2, RoundingMode.HALF_UP);
                                                                                                total = total.multiply(BigDecimal.valueOf(100));
                                                                                                double percentage = total.doubleValue();
                                                                                                ExamRecording exam = new ExamRecording();
                                                                                                GradingSystem system = new GradingSystem();
                                                                                                exam.setPercentage(percentage);
                                                                                                if (percentage <= 29) {
                                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("E");
                                                                                                } else if (percentage > 29 && percentage <= 34) {
                                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("D-");
                                                                                                } else if (percentage > 34 && percentage <= 39) {
                                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("D");
                                                                                                } else if (percentage > 39 && percentage <= 44) {
                                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("D+");
                                                                                                } else if (percentage > 44 && percentage <= 49) {
                                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("C-");
                                                                                                } else if (percentage > 49 && percentage <= 54) {
                                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("C");
                                                                                                } else if (percentage > 54 && percentage <= 59) {
                                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("C+");
                                                                                                } else if (percentage > 59 && percentage <= 64) {
                                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("B-");
                                                                                                } else if (percentage > 64 && percentage <= 69) {
                                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("B");
                                                                                                } else if (percentage > 69 && percentage <= 74) {
                                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("B+");
                                                                                                } else if (percentage > 74 && percentage <= 79) {
                                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("A-");
                                                                                                } else if (percentage > 79 && percentage <= 100) {
                                                                                                    system = gradingSysRepo.findByGradeIgnoreCase("A");
                                                                                                } else {
                                                                                                    throw new BadRequestException("Invalid marks");
                                                                                                }
                                                                                                exam.setTerm(examMergeBean.getTerm());
                                                                                                exam.setYear(examMergeBean.getYear());
                                                                                                exam.setStudent(e9.getStudent());
                                                                                                exam.setTeacherSubjects(e9.getTeacherSubjects());
                                                                                                exam.setExamDate(examMergeBean.getExamRegister().getEffectiveDate());
                                                                                                exam.setExamRegister(examMergeBean.getExamRegister());
                                                                                                exam.setForms(e9.getForms());
                                                                                                exam.setTeachers(e9.getTeachers());
                                                                                                exam.setMark(tt.doubleValue());
                                                                                                exam.setGradingSystem(system);
                                                                                                exam.setOutOff(examMergeBean.getOutOff().doubleValue());
                                                                                                examRecordingRepo.save(exam);
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                    }

                }
            } else {
                throw new BadRequestException("Totals don't add up");
            }
        } else if (examMergeBean.getSubjects() != null && examMergeBean.getForms() != null) {
            BigDecimal mark = examMergeBean.getMark1().add(examMergeBean.getMark2()).add(examMergeBean.getMark3()).add(examMergeBean.getMark4()).add(examMergeBean.getMark5()).add(examMergeBean.getMark6()).add(examMergeBean.getMark7()).add(examMergeBean.getMark8()).add(examMergeBean.getMark9()).add(examMergeBean.getMark10());
            if (mark.equals(examMergeBean.getOutOff())) {
                List<Student> students = studentRepo.findByForms_ClassCode(examMergeBean.getForms().getClassCode());
                if (!(students.isEmpty())) {
                    for (Student s : students) {
                        List<ExamRecording> examRecordings = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam(), s, examMergeBean.getSubjects());
                        List<ExamRecording> examRecordings1 = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam1(), s, examMergeBean.getSubjects());
                        List<ExamRecording> examRecordings2 = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam2(), s, examMergeBean.getSubjects());
                        List<ExamRecording> examRecordings3 = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam3(), s, examMergeBean.getSubjects());
                        List<ExamRecording> examRecordings4 = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam4(), s, examMergeBean.getSubjects());
                        List<ExamRecording> examRecordings5 = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam5(), s, examMergeBean.getSubjects());
                        List<ExamRecording> examRecordings6 = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam6(), s, examMergeBean.getSubjects());
                        List<ExamRecording> examRecordings7 = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam7(), s, examMergeBean.getSubjects());
                        List<ExamRecording> examRecordings8 = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam8(), s, examMergeBean.getSubjects());
                        List<ExamRecording> examRecordings9 = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam9(), s, examMergeBean.getSubjects());

                        if (!(examRecordings.isEmpty()) && !(examRecordings1.isEmpty()) && !(examRecordings2.isEmpty()) && !(examRecordings3.isEmpty()) && !(examRecordings4.isEmpty()) && !(examRecordings5.isEmpty()) && !(examRecordings6.isEmpty()) && !(examRecordings7.isEmpty()) && !(examRecordings8.isEmpty()) && !(examRecordings9.isEmpty())) {
                            for (ExamRecording e : examRecordings) {
                                BigDecimal marks = BigDecimal.valueOf(e.getMark());
                                BigDecimal outOff = BigDecimal.valueOf(e.getOutOff());
                                BigDecimal aggr = marks.divide(outOff, 2, RoundingMode.HALF_UP);
                                BigDecimal t1 = aggr.multiply(examMergeBean.getMark1());

                                for (ExamRecording e1 : examRecordings1) {
                                    if (e.getTeacherSubjects().getSubjects() == e1.getTeacherSubjects().getSubjects() && e.getStudent() == e1.getStudent()) {
                                        BigDecimal marks1 = BigDecimal.valueOf(e1.getMark());
                                        BigDecimal outOff1 = BigDecimal.valueOf(e1.getOutOff());
                                        BigDecimal aggr1 = marks1.divide(outOff1, 2, RoundingMode.HALF_UP);
                                        BigDecimal t2 = aggr1.multiply(examMergeBean.getMark2());
                                        for (ExamRecording e2 : examRecordings2) {
                                            if (e1.getTeacherSubjects().getSubjects() == e2.getTeacherSubjects().getSubjects() && e1.getStudent() == e2.getStudent()) {
                                                BigDecimal marks2 = BigDecimal.valueOf(e2.getMark());
                                                BigDecimal outOff2 = BigDecimal.valueOf(e2.getOutOff());
                                                BigDecimal aggr2 = marks2.divide(outOff2, 2, RoundingMode.HALF_UP);
                                                BigDecimal t3 = aggr2.multiply(examMergeBean.getMark3());
                                                for (ExamRecording e3 : examRecordings3) {
                                                    if (e2.getTeacherSubjects().getSubjects() == e3.getTeacherSubjects().getSubjects() && e2.getStudent() == e3.getStudent()) {
                                                        BigDecimal marks3 = BigDecimal.valueOf(e3.getMark());
                                                        BigDecimal outOff3 = BigDecimal.valueOf(e3.getOutOff());
                                                        BigDecimal aggr3 = marks3.divide(outOff3, 2, RoundingMode.HALF_UP);
                                                        BigDecimal t4 = aggr3.multiply(examMergeBean.getMark4());
                                                        for (ExamRecording e4 : examRecordings4) {
                                                            if (e3.getTeacherSubjects().getSubjects() == e4.getTeacherSubjects().getSubjects() && e3.getStudent() == e4.getStudent()) {
                                                                BigDecimal marks4 = BigDecimal.valueOf(e4.getMark());
                                                                BigDecimal outOff4 = BigDecimal.valueOf(e4.getOutOff());
                                                                BigDecimal aggr4 = marks4.divide(outOff4, 2, RoundingMode.HALF_UP);
                                                                BigDecimal t5 = aggr4.multiply(examMergeBean.getMark5());
                                                                for (ExamRecording e5 : examRecordings5) {
                                                                    if (e4.getTeacherSubjects().getSubjects() == e5.getTeacherSubjects().getSubjects() && e4.getStudent() == e5.getStudent()) {
                                                                        BigDecimal marks5 = BigDecimal.valueOf(e5.getMark());
                                                                        BigDecimal outOff5 = BigDecimal.valueOf(e5.getOutOff());
                                                                        BigDecimal aggr5 = marks5.divide(outOff5, 2, RoundingMode.HALF_UP);
                                                                        BigDecimal t6 = aggr5.multiply(examMergeBean.getMark6());
                                                                        for (ExamRecording e6 : examRecordings6) {
                                                                            if (e5.getTeacherSubjects().getSubjects() == e6.getTeacherSubjects().getSubjects() && e5.getStudent() == e6.getStudent()) {
                                                                                BigDecimal marks6 = BigDecimal.valueOf(e6.getMark());
                                                                                BigDecimal outOff6 = BigDecimal.valueOf(e6.getOutOff());
                                                                                BigDecimal aggr6 = marks6.divide(outOff6, 2, RoundingMode.HALF_UP);
                                                                                BigDecimal t7 = aggr6.multiply(examMergeBean.getMark7());
                                                                                for (ExamRecording e7 : examRecordings7) {
                                                                                    if (e6.getTeacherSubjects().getSubjects() == e7.getTeacherSubjects().getSubjects() && e6.getStudent() == e7.getStudent()) {
                                                                                        BigDecimal marks7 = BigDecimal.valueOf(e7.getMark());
                                                                                        BigDecimal outOff7 = BigDecimal.valueOf(e7.getOutOff());
                                                                                        BigDecimal aggr7 = marks7.divide(outOff7, 2, RoundingMode.HALF_UP);
                                                                                        BigDecimal t8 = aggr7.multiply(examMergeBean.getMark8());
                                                                                        for (ExamRecording e8 : examRecordings8) {
                                                                                            if (e7.getTeacherSubjects().getSubjects() == e8.getTeacherSubjects().getSubjects() && e7.getStudent() == e8.getStudent()) {
                                                                                                BigDecimal marks8 = BigDecimal.valueOf(e8.getMark());
                                                                                                BigDecimal outOff8 = BigDecimal.valueOf(e8.getOutOff());
                                                                                                BigDecimal aggr8 = marks8.divide(outOff8, 2, RoundingMode.HALF_UP);
                                                                                                BigDecimal t9 = aggr8.multiply(examMergeBean.getMark9());
                                                                                                for (ExamRecording e9 : examRecordings9) {
                                                                                                    if (e8.getTeacherSubjects().getSubjects() == e9.getTeacherSubjects().getSubjects() && e8.getStudent() == e9.getStudent()) {
                                                                                                        BigDecimal marks9 = BigDecimal.valueOf(e9.getMark());
                                                                                                        BigDecimal outOff9 = BigDecimal.valueOf(e9.getOutOff());
                                                                                                        BigDecimal aggr9 = marks9.divide(outOff9, 2, RoundingMode.HALF_UP);
                                                                                                        BigDecimal t10 = aggr9.multiply(examMergeBean.getMark10());
                                                                                                        BigDecimal tt = t1.add(t2).add(t3).add(t4).add(t5).add(t6).add(t7).add(t8).add(t9).add(t10);
                                                                                                        BigDecimal total = tt.divide(examMergeBean.getOutOff(), 2, RoundingMode.HALF_UP);
                                                                                                        total = total.multiply(BigDecimal.valueOf(100));
                                                                                                        double percentage = total.doubleValue();
                                                                                                        ExamRecording exam = new ExamRecording();
                                                                                                        GradingSystem system = new GradingSystem();
                                                                                                        exam.setPercentage(percentage);
                                                                                                        if (percentage <= 29) {
                                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("E");
                                                                                                        } else if (percentage > 29 && percentage <= 34) {
                                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("D-");
                                                                                                        } else if (percentage > 34 && percentage <= 39) {
                                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("D");
                                                                                                        } else if (percentage > 39 && percentage <= 44) {
                                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("D+");
                                                                                                        } else if (percentage > 44 && percentage <= 49) {
                                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("C-");
                                                                                                        } else if (percentage > 49 && percentage <= 54) {
                                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("C");
                                                                                                        } else if (percentage > 54 && percentage <= 59) {
                                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("C+");
                                                                                                        } else if (percentage > 59 && percentage <= 64) {
                                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("B-");
                                                                                                        } else if (percentage > 64 && percentage <= 69) {
                                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("B");
                                                                                                        } else if (percentage > 69 && percentage <= 74) {
                                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("B+");
                                                                                                        } else if (percentage > 74 && percentage <= 79) {
                                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("A-");
                                                                                                        } else if (percentage > 79 && percentage <= 100) {
                                                                                                            system = gradingSysRepo.findByGradeIgnoreCase("A");
                                                                                                        } else {
                                                                                                            throw new BadRequestException("Invalid marks");
                                                                                                        }
                                                                                                        exam.setGradingSystem(system);
                                                                                                        exam.setTerm(examMergeBean.getTerm());
                                                                                                        exam.setYear(examMergeBean.getYear());
                                                                                                        exam.setStudent(s);
                                                                                                        exam.setTeacherSubjects(e9.getTeacherSubjects());
                                                                                                        exam.setExamDate(examMergeBean.getExamRegister().getEffectiveDate());
                                                                                                        exam.setExamRegister(examMergeBean.getExamRegister());
                                                                                                        exam.setForms(e9.getForms());
                                                                                                        exam.setTeachers(e9.getTeachers());
                                                                                                        exam.setMark(tt.doubleValue());
                                                                                                        exam.setOutOff(examMergeBean.getOutOff().doubleValue());
                                                                                                        examRecordingRepo.save(exam);
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            } else {
                throw new BadRequestException("Totals don't add up");
            }
        } else {
            BigDecimal mark = examMergeBean.getMark1().add(examMergeBean.getMark2()).add(examMergeBean.getMark3()).add(examMergeBean.getMark4()).add(examMergeBean.getMark5()).add(examMergeBean.getMark6()).add(examMergeBean.getMark7()).add(examMergeBean.getMark8()).add(examMergeBean.getMark9()).add(examMergeBean.getMark10());
            if (mark.equals(examMergeBean.getOutOff())) {
                List<Student> students = studentRepo.findAllByOrderByForms();
                for (Student s : students) {
                    List<ExamRecording> examRecordings = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam(), s);
                    List<ExamRecording> examRecordings1 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam1(), s);
                    List<ExamRecording> examRecordings2 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam2(), s);
                    List<ExamRecording> examRecordings3 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam3(), s);
                    List<ExamRecording> examRecordings4 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam4(), s);
                    List<ExamRecording> examRecordings5 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam5(), s);
                    List<ExamRecording> examRecordings6 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam6(), s);
                    List<ExamRecording> examRecordings7 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam7(), s);
                    List<ExamRecording> examRecordings8 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam8(), s);
                    List<ExamRecording> examRecordings9 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam9(), s);

                    if (!(examRecordings.isEmpty()) && !(examRecordings1.isEmpty()) && !(examRecordings2.isEmpty()) && !(examRecordings3.isEmpty()) && !(examRecordings4.isEmpty()) && !(examRecordings5.isEmpty()) && !(examRecordings6.isEmpty()) && !(examRecordings7.isEmpty()) && !(examRecordings8.isEmpty()) && !(examRecordings9.isEmpty())) {
                        for (ExamRecording e : examRecordings) {
                            BigDecimal marks = BigDecimal.valueOf(e.getMark());
                            BigDecimal outOff = BigDecimal.valueOf(e.getOutOff());
                            BigDecimal aggr = marks.divide(outOff, 2, RoundingMode.HALF_UP);
                            BigDecimal t1 = aggr.multiply(examMergeBean.getMark1());
                            for (ExamRecording e1 : examRecordings1) {
                                if (e.getTeacherSubjects().getSubjects() == e1.getTeacherSubjects().getSubjects()) {
                                    BigDecimal marks1 = BigDecimal.valueOf(e1.getMark());
                                    BigDecimal outOff1 = BigDecimal.valueOf(e1.getOutOff());
                                    BigDecimal aggr1 = marks1.divide(outOff1, 2, RoundingMode.HALF_UP);
                                    BigDecimal t2 = aggr1.multiply(examMergeBean.getMark2());
                                    for (ExamRecording e2 : examRecordings2) {
                                        if (e1.getTeacherSubjects().getSubjects() == e2.getTeacherSubjects().getSubjects()) {
                                            BigDecimal marks2 = BigDecimal.valueOf(e2.getMark());
                                            BigDecimal outOff2 = BigDecimal.valueOf(e2.getOutOff());
                                            BigDecimal aggr2 = marks2.divide(outOff2, 2, RoundingMode.HALF_UP);
                                            BigDecimal t3 = aggr2.multiply(examMergeBean.getMark3());
                                            for (ExamRecording e3 : examRecordings3) {
                                                if (e2.getTeacherSubjects().getSubjects() == e3.getTeacherSubjects().getSubjects()) {
                                                    BigDecimal marks3 = BigDecimal.valueOf(e3.getMark());
                                                    BigDecimal outOff3 = BigDecimal.valueOf(e3.getOutOff());
                                                    BigDecimal aggr3 = marks3.divide(outOff3, 2, RoundingMode.HALF_UP);
                                                    BigDecimal t4 = aggr3.multiply(examMergeBean.getMark4());
                                                    for (ExamRecording e4 : examRecordings4) {
                                                        if (e3.getTeacherSubjects().getSubjects() == e4.getTeacherSubjects().getSubjects()) {
                                                            BigDecimal marks4 = BigDecimal.valueOf(e4.getMark());
                                                            BigDecimal outOff4 = BigDecimal.valueOf(e4.getOutOff());
                                                            BigDecimal aggr4 = marks4.divide(outOff4, 2, RoundingMode.HALF_UP);
                                                            BigDecimal t5 = aggr4.multiply(examMergeBean.getMark5());
                                                            for (ExamRecording e5 : examRecordings5) {
                                                                if (e4.getTeacherSubjects().getSubjects() == e5.getTeacherSubjects().getSubjects()) {
                                                                    BigDecimal marks5 = BigDecimal.valueOf(e5.getMark());
                                                                    BigDecimal outOff5 = BigDecimal.valueOf(e5.getOutOff());
                                                                    BigDecimal aggr5 = marks5.divide(outOff5, 2, RoundingMode.HALF_UP);
                                                                    BigDecimal t6 = aggr5.multiply(examMergeBean.getMark6());
                                                                    for (ExamRecording e6 : examRecordings6) {
                                                                        if (e5.getTeacherSubjects().getSubjects() == e6.getTeacherSubjects().getSubjects()) {
                                                                            BigDecimal marks6 = BigDecimal.valueOf(e6.getMark());
                                                                            BigDecimal outOff6 = BigDecimal.valueOf(e6.getOutOff());
                                                                            BigDecimal aggr6 = marks6.divide(outOff6, 2, RoundingMode.HALF_UP);
                                                                            BigDecimal t7 = aggr6.multiply(examMergeBean.getMark7());
                                                                            for (ExamRecording e7 : examRecordings7) {
                                                                                if (e6.getTeacherSubjects().getSubjects() == e7.getTeacherSubjects().getSubjects()) {
                                                                                    BigDecimal marks7 = BigDecimal.valueOf(e7.getMark());
                                                                                    BigDecimal outOff7 = BigDecimal.valueOf(e7.getOutOff());
                                                                                    BigDecimal aggr7 = marks7.divide(outOff7, 2, RoundingMode.HALF_UP);
                                                                                    BigDecimal t8 = aggr7.multiply(examMergeBean.getMark7());
                                                                                    for (ExamRecording e8 : examRecordings8) {
                                                                                        if (e7.getTeacherSubjects().getSubjects() == e8.getTeacherSubjects().getSubjects()) {
                                                                                            BigDecimal marks8 = BigDecimal.valueOf(e8.getMark());
                                                                                            BigDecimal outOff8 = BigDecimal.valueOf(e8.getOutOff());
                                                                                            BigDecimal aggr8 = marks8.divide(outOff8, 2, RoundingMode.HALF_UP);
                                                                                            BigDecimal t9 = aggr8.multiply(examMergeBean.getMark9());
                                                                                            for (ExamRecording e9 : examRecordings9) {
                                                                                                if (e8.getTeacherSubjects().getSubjects() == e9.getTeacherSubjects().getSubjects()) {
                                                                                                    BigDecimal marks9 = BigDecimal.valueOf(e9.getMark());
                                                                                                    BigDecimal outOff9 = BigDecimal.valueOf(e9.getOutOff());
                                                                                                    BigDecimal aggr9 = marks9.divide(outOff9, 2, RoundingMode.HALF_UP);
                                                                                                    BigDecimal t10 = aggr9.multiply(examMergeBean.getMark10());
                                                                                                    BigDecimal tt = t1.add(t2).add(t3).add(t4).add(t5).add(t6).add(t7).add(t8).add(t9).add(t10);
                                                                                                    BigDecimal total = tt.divide(examMergeBean.getOutOff(), 2, RoundingMode.HALF_UP);
                                                                                                    total = total.multiply(BigDecimal.valueOf(100));
                                                                                                    double percentage = total.doubleValue();
                                                                                                    ExamRecording exam = new ExamRecording();
                                                                                                    GradingSystem system = new GradingSystem();
                                                                                                    exam.setPercentage(percentage);
                                                                                                    if (percentage <= 29) {
                                                                                                        system = gradingSysRepo.findByGradeIgnoreCase("E");
                                                                                                    } else if (percentage > 29 && percentage <= 34) {
                                                                                                        system = gradingSysRepo.findByGradeIgnoreCase("D-");
                                                                                                    } else if (percentage > 34 && percentage <= 39) {
                                                                                                        system = gradingSysRepo.findByGradeIgnoreCase("D");
                                                                                                    } else if (percentage > 39 && percentage <= 44) {
                                                                                                        system = gradingSysRepo.findByGradeIgnoreCase("D+");
                                                                                                    } else if (percentage > 44 && percentage <= 49) {
                                                                                                        system = gradingSysRepo.findByGradeIgnoreCase("C-");
                                                                                                    } else if (percentage > 49 && percentage <= 54) {
                                                                                                        system = gradingSysRepo.findByGradeIgnoreCase("C");
                                                                                                    } else if (percentage > 54 && percentage <= 59) {
                                                                                                        system = gradingSysRepo.findByGradeIgnoreCase("C+");
                                                                                                    } else if (percentage > 59 && percentage <= 64) {
                                                                                                        system = gradingSysRepo.findByGradeIgnoreCase("B-");
                                                                                                    } else if (percentage > 64 && percentage <= 69) {
                                                                                                        system = gradingSysRepo.findByGradeIgnoreCase("B");
                                                                                                    } else if (percentage > 69 && percentage <= 74) {
                                                                                                        system = gradingSysRepo.findByGradeIgnoreCase("B+");
                                                                                                    } else if (percentage > 74 && percentage <= 79) {
                                                                                                        system = gradingSysRepo.findByGradeIgnoreCase("A-");
                                                                                                    } else if (percentage > 79 && percentage <= 100) {
                                                                                                        system = gradingSysRepo.findByGradeIgnoreCase("A");
                                                                                                    } else {
                                                                                                        throw new BadRequestException("Invalid marks");
                                                                                                    }
                                                                                                    exam.setGradingSystem(system);
                                                                                                    exam.setTerm(examMergeBean.getTerm());
                                                                                                    exam.setYear(examMergeBean.getYear());
                                                                                                    exam.setStudent(s);
                                                                                                    exam.setTeacherSubjects(e9.getTeacherSubjects());
                                                                                                    exam.setExamDate(examMergeBean.getExamRegister().getEffectiveDate());
                                                                                                    exam.setExamRegister(examMergeBean.getExamRegister());
                                                                                                    exam.setForms(e9.getForms());
                                                                                                    exam.setTeachers(e9.getTeachers());
                                                                                                    exam.setMark(tt.doubleValue());
                                                                                                    exam.setOutOff(examMergeBean.getOutOff().doubleValue());
                                                                                                    examRecordingRepo.save(exam);
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            } else {
                throw new BadRequestException("Totals don't add up");
            }


        }
    }

    @Override
    public void mergeSixExams(ExamMergeBean examMergeBean) throws BadRequestException {
        if (examMergeBean.getForms() != null && examMergeBean.getSubjects() == null) {
            BigDecimal mark = examMergeBean.getMark1().add(examMergeBean.getMark2()).add(examMergeBean.getMark3()).add(examMergeBean.getMark4()).add(examMergeBean.getMark5()).add(examMergeBean.getMark6());
            if (mark.equals(examMergeBean.getOutOff())) {
                List<Student> students = studentRepo.findByForms_ClassCode(examMergeBean.getForms().getClassCode());
                if (!(students.isEmpty())) {
                    for (Student s : students) {
                        List<ExamRecording> examRecordings = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam(), s);
                        List<ExamRecording> examRecordings1 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam1(), s);
                        List<ExamRecording> examRecordings2 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam2(), s);
                        List<ExamRecording> examRecordings3 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam3(), s);
                        List<ExamRecording> examRecordings4 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam4(), s);
                        List<ExamRecording> examRecordings5 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam5(), s);

                        if (!(examRecordings.isEmpty()) && !(examRecordings1.isEmpty()) && !(examRecordings2.isEmpty()) && !(examRecordings3.isEmpty()) && !(examRecordings4.isEmpty()) && !(examRecordings5.isEmpty())) {
                            for (ExamRecording e : examRecordings) {
                                BigDecimal marks = BigDecimal.valueOf(e.getMark());
                                BigDecimal outOff = BigDecimal.valueOf(e.getOutOff());
                                BigDecimal aggr = marks.divide(outOff, 2, RoundingMode.HALF_UP);
                                BigDecimal t1 = aggr.multiply(examMergeBean.getMark1());
                                for (ExamRecording e1 : examRecordings1) {
                                    if (e.getTeacherSubjects().getSubjects() == e1.getTeacherSubjects().getSubjects()) {
                                        BigDecimal marks1 = BigDecimal.valueOf(e1.getMark());
                                        BigDecimal outOff1 = BigDecimal.valueOf(e1.getOutOff());
                                        BigDecimal aggr1 = marks1.divide(outOff1, 2, RoundingMode.HALF_UP);
                                        BigDecimal t2 = aggr1.multiply(examMergeBean.getMark2());
                                        for (ExamRecording e2 : examRecordings2) {
                                            if (e1.getTeacherSubjects().getSubjects()
                                                    == e2.getTeacherSubjects().getSubjects()) {
                                                BigDecimal marks2 = BigDecimal.valueOf(e2.getMark());
                                                BigDecimal outOff2 = BigDecimal.valueOf(e2.getOutOff());
                                                BigDecimal aggr2 = marks2.divide(outOff2, 2, RoundingMode.HALF_UP);
                                                BigDecimal t3 = aggr2.multiply(examMergeBean.getMark3());
                                                for (ExamRecording e3 : examRecordings3) {
                                                    if (e2.getTeacherSubjects().getSubjects()
                                                            == e3.getTeacherSubjects().getSubjects()) {
                                                        BigDecimal marks3 = BigDecimal.valueOf(e3.getMark());
                                                        BigDecimal outOff3 = BigDecimal.valueOf(e3.getOutOff());
                                                        BigDecimal aggr3 = marks3.divide(outOff3, 2, RoundingMode.HALF_UP);
                                                        BigDecimal t4 = aggr3.multiply(examMergeBean.getMark4());
                                                        for (ExamRecording e4 : examRecordings4) {
                                                            if (e3.getTeacherSubjects().getSubjects()
                                                                    == e4.getTeacherSubjects().getSubjects()) {

                                                                BigDecimal marks4 = BigDecimal.valueOf(e4.getMark());
                                                                BigDecimal outOff4 = BigDecimal.valueOf(e4.getOutOff());
                                                                BigDecimal aggr4 = marks4.divide(outOff4, 2, RoundingMode.HALF_UP);
                                                                BigDecimal t5 = aggr4.multiply(examMergeBean.getMark5());
                                                                for (ExamRecording e5 : examRecordings5) {
                                                                    if (e4.getTeacherSubjects().getSubjects()
                                                                            == e5.getTeacherSubjects().getSubjects()) {
                                                                        BigDecimal marks5 = BigDecimal.valueOf(e5.getMark());
                                                                        BigDecimal outOff5 = BigDecimal.valueOf(e5.getOutOff());
                                                                        BigDecimal aggr5 = marks5.divide(outOff5, 2, RoundingMode.HALF_UP);
                                                                        BigDecimal t6 = aggr5.multiply(examMergeBean.getMark6());
                                                                        BigDecimal tt = t1.add(t2).add(t3).add(t4).add(t5).add(t6);
                                                                        BigDecimal total = tt.divide(examMergeBean.getOutOff(), 2, RoundingMode.HALF_UP);
                                                                        total = total.multiply(BigDecimal.valueOf(100));
                                                                        double percentage = total.doubleValue();
                                                                        ExamRecording exam = new ExamRecording();
                                                                        GradingSystem system = new GradingSystem();
                                                                        exam.setPercentage(percentage);
                                                                        if (percentage <= 29) {
                                                                            system = gradingSysRepo.findByGradeIgnoreCase("E");
                                                                        } else if (percentage > 29 && percentage <= 34) {
                                                                            system = gradingSysRepo.findByGradeIgnoreCase("D-");
                                                                        } else if (percentage > 34 && percentage <= 39) {
                                                                            system = gradingSysRepo.findByGradeIgnoreCase("D");
                                                                        } else if (percentage > 39 && percentage <= 44) {
                                                                            system = gradingSysRepo.findByGradeIgnoreCase("D+");
                                                                        } else if (percentage > 44 && percentage <= 49) {
                                                                            system = gradingSysRepo.findByGradeIgnoreCase("C-");
                                                                        } else if (percentage > 49 && percentage <= 54) {
                                                                            system = gradingSysRepo.findByGradeIgnoreCase("C");
                                                                        } else if (percentage > 54 && percentage <= 59) {
                                                                            system = gradingSysRepo.findByGradeIgnoreCase("C+");
                                                                        } else if (percentage > 59 && percentage <= 64) {
                                                                            system = gradingSysRepo.findByGradeIgnoreCase("B-");
                                                                        } else if (percentage > 64 && percentage <= 69) {
                                                                            system = gradingSysRepo.findByGradeIgnoreCase("B");
                                                                        } else if (percentage > 69 && percentage <= 74) {
                                                                            system = gradingSysRepo.findByGradeIgnoreCase("B+");
                                                                        } else if (percentage > 74 && percentage <= 79) {
                                                                            system = gradingSysRepo.findByGradeIgnoreCase("A-");
                                                                        } else if (percentage > 79 && percentage <= 100) {
                                                                            system = gradingSysRepo.findByGradeIgnoreCase("A");
                                                                        } else {
                                                                            throw new BadRequestException("Invalid marks");
                                                                        }
                                                                        exam.setTerm(examMergeBean.getTerm());
                                                                        exam.setYear(examMergeBean.getYear());
                                                                        exam.setStudent(s);
                                                                        exam.setTeacherSubjects(e5.getTeacherSubjects());
                                                                        exam.setExamDate(examMergeBean.getExamRegister().getEffectiveDate());
                                                                        exam.setExamRegister(examMergeBean.getExamRegister());
                                                                        exam.setForms(e5.getForms());
                                                                        exam.setTeachers(e5.getTeachers());
                                                                        exam.setMark(tt.doubleValue());
                                                                        exam.setGradingSystem(system);
                                                                        exam.setOutOff(examMergeBean.getOutOff().doubleValue());
                                                                        examRecordingRepo.save(exam);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            } else {
                throw new BadRequestException("Totals don't add up");
            }
        } else if (examMergeBean.getSubjects() != null && examMergeBean.getForms() == null) {
            BigDecimal mark = examMergeBean.getMark1().add(examMergeBean.getMark2()).add(examMergeBean.getMark3()).add(examMergeBean.getMark4()).add(examMergeBean.getMark5()).add(examMergeBean.getMark6());
            if (mark.equals(examMergeBean.getOutOff())) {
                List<ExamRecording> examRecordings = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam(), examMergeBean.getSubjects());
                List<ExamRecording> examRecordings1 = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam1(), examMergeBean.getSubjects());
                List<ExamRecording> examRecordings2 = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam2(), examMergeBean.getSubjects());
                List<ExamRecording> examRecordings3 = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam3(), examMergeBean.getSubjects());
                List<ExamRecording> examRecordings4 = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam4(), examMergeBean.getSubjects());
                List<ExamRecording> examRecordings5 = examRecordingRepo.findByExamRegisterAndTeacherSubjects_Subjects(examMergeBean.getExam5(), examMergeBean.getSubjects());

                if (!(examRecordings.isEmpty()) && !(examRecordings1.isEmpty()) && !(examRecordings2.isEmpty()) && !(examRecordings3.isEmpty()) && !(examRecordings4.isEmpty()) && !(examRecordings5.isEmpty())) {
                    for (ExamRecording e : examRecordings) {
                        BigDecimal marks = BigDecimal.valueOf(e.getMark());
                        BigDecimal outOff = BigDecimal.valueOf(e.getOutOff());
                        BigDecimal aggr = marks.divide(outOff, 2, RoundingMode.HALF_UP);
                        BigDecimal t1 = aggr.multiply(examMergeBean.getMark1());
                        for (ExamRecording e1 : examRecordings1) {
                            if (e.getTeacherSubjects().getSubjects() == e1.getTeacherSubjects().getSubjects() && e.getStudent()
                                    == e1.getStudent()) {
                                BigDecimal marks1 = BigDecimal.valueOf(e1.getMark());
                                BigDecimal outOff1 = BigDecimal.valueOf(e1.getOutOff());
                                BigDecimal aggr1 = marks1.divide(outOff1, 2, RoundingMode.HALF_UP);
                                BigDecimal t2 = aggr1.multiply(examMergeBean.getMark2());
                                for (ExamRecording e2 : examRecordings2) {

                                    if (e1.getTeacherSubjects().getSubjects() == e2.getTeacherSubjects().getSubjects() && e1.getStudent()
                                            == e2.getStudent()) {
                                        BigDecimal marks2 = BigDecimal.valueOf(e2.getMark());
                                        BigDecimal outOff2 = BigDecimal.valueOf(e2.getOutOff());
                                        BigDecimal aggr2 = marks2.divide(outOff2, 2, RoundingMode.HALF_UP);
                                        BigDecimal t3 = aggr2.multiply(examMergeBean.getMark3());
                                        for (ExamRecording e3 : examRecordings3) {
                                            if (e2.getTeacherSubjects().getSubjects() == e3.getTeacherSubjects().getSubjects() && e2.getStudent()
                                                    == e3.getStudent()) {
                                                BigDecimal marks3 = BigDecimal.valueOf(e3.getMark());
                                                BigDecimal outOff3 = BigDecimal.valueOf(e3.getOutOff());
                                                BigDecimal aggr3 = marks3.divide(outOff3, 2, RoundingMode.HALF_UP);
                                                BigDecimal t4 = aggr3.multiply(examMergeBean.getMark4());
                                                for (ExamRecording e4 : examRecordings4) {
                                                    if (e3.getTeacherSubjects().getSubjects() == e4.getTeacherSubjects().getSubjects() && e3.getStudent()
                                                            == e4.getStudent()) {
                                                        BigDecimal marks4 = BigDecimal.valueOf(e4.getMark());
                                                        BigDecimal outOff4 = BigDecimal.valueOf(e4.getOutOff());
                                                        BigDecimal aggr4 = marks4.divide(outOff4, 2, RoundingMode.HALF_UP);
                                                        BigDecimal t5 = aggr4.multiply(examMergeBean.getMark5());
                                                        for (ExamRecording e5 : examRecordings5) {
                                                            if (e4.getTeacherSubjects().getSubjects() == e5.getTeacherSubjects().getSubjects() && e4.getStudent() == e5.getStudent()) {
                                                                BigDecimal marks5 = BigDecimal.valueOf(e5.getMark());
                                                                BigDecimal outOff5 = BigDecimal.valueOf(e5.getOutOff());
                                                                BigDecimal aggr5 = marks5.divide(outOff5, 2, RoundingMode.HALF_UP);
                                                                BigDecimal t6 = aggr5.multiply(examMergeBean.getMark6());
                                                                BigDecimal tt = t1.add(t2).add(t3).add(t4).add(t5).add(t6);
                                                                BigDecimal total = tt.divide(examMergeBean.getOutOff(), 2, RoundingMode.HALF_UP);
                                                                total = total.multiply(BigDecimal.valueOf(100));
                                                                double percentage = total.doubleValue();
                                                                ExamRecording exam = new ExamRecording();
                                                                GradingSystem system = new GradingSystem();
                                                                exam.setPercentage(percentage);
                                                                if (percentage <= 29) {
                                                                    system = gradingSysRepo.findByGradeIgnoreCase("E");
                                                                } else if (percentage > 29 && percentage <= 34) {
                                                                    system = gradingSysRepo.findByGradeIgnoreCase("D-");
                                                                } else if (percentage > 34 && percentage <= 39) {
                                                                    system = gradingSysRepo.findByGradeIgnoreCase("D");
                                                                } else if (percentage > 39 && percentage <= 44) {
                                                                    system = gradingSysRepo.findByGradeIgnoreCase("D+");
                                                                } else if (percentage > 44 && percentage <= 49) {
                                                                    system = gradingSysRepo.findByGradeIgnoreCase("C-");
                                                                } else if (percentage > 49 && percentage <= 54) {
                                                                    system = gradingSysRepo.findByGradeIgnoreCase("C");
                                                                } else if (percentage > 54 && percentage <= 59) {
                                                                    system = gradingSysRepo.findByGradeIgnoreCase("C+");
                                                                } else if (percentage > 59 && percentage <= 64) {
                                                                    system = gradingSysRepo.findByGradeIgnoreCase("B-");
                                                                } else if (percentage > 64 && percentage <= 69) {
                                                                    system = gradingSysRepo.findByGradeIgnoreCase("B");
                                                                } else if (percentage > 69 && percentage <= 74) {
                                                                    system = gradingSysRepo.findByGradeIgnoreCase("B+");
                                                                } else if (percentage > 74 && percentage <= 79) {
                                                                    system = gradingSysRepo.findByGradeIgnoreCase("A-");
                                                                } else if (percentage > 79 && percentage <= 100) {
                                                                    system = gradingSysRepo.findByGradeIgnoreCase("A");
                                                                } else {
                                                                    throw new BadRequestException("Invalid marks");
                                                                }
                                                                exam.setTerm(examMergeBean.getTerm());
                                                                exam.setYear(examMergeBean.getYear());
                                                                exam.setStudent(e5.getStudent());
                                                                exam.setTeacherSubjects(e5.getTeacherSubjects());
                                                                exam.setExamDate(examMergeBean.getExamRegister().getEffectiveDate());
                                                                exam.setExamRegister(examMergeBean.getExamRegister());
                                                                exam.setForms(e5.getForms());
                                                                exam.setTeachers(e5.getTeachers());
                                                                exam.setMark(tt.doubleValue());
                                                                exam.setGradingSystem(system);
                                                                exam.setOutOff(examMergeBean.getOutOff().doubleValue());
                                                                examRecordingRepo.save(exam);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                    }

                }
            } else {
                throw new BadRequestException("Totals don't add up");
            }
        } else if (examMergeBean.getSubjects() != null && examMergeBean.getForms() != null) {
            BigDecimal mark = examMergeBean.getMark1().add(examMergeBean.getMark2()).add(examMergeBean.getMark3()).add(examMergeBean.getMark4()).add(examMergeBean.getMark5()).add(examMergeBean.getMark6());
            if (mark.equals(examMergeBean.getOutOff())) {
                List<Student> students = studentRepo.findByForms_ClassCode(examMergeBean.getForms().getClassCode());
                if (!(students.isEmpty())) {
                    for (Student s : students) {
                        List<ExamRecording> examRecordings = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam(), s, examMergeBean.getSubjects());
                        List<ExamRecording> examRecordings1 = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam1(), s, examMergeBean.getSubjects());
                        List<ExamRecording> examRecordings2 = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam2(), s, examMergeBean.getSubjects());
                        List<ExamRecording> examRecordings3 = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam3(), s, examMergeBean.getSubjects());
                        List<ExamRecording> examRecordings4 = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam4(), s, examMergeBean.getSubjects());
                        List<ExamRecording> examRecordings5 = examRecordingRepo.findByExamRegisterAndStudentAndTeacherSubjects_Subjects(examMergeBean.getExam5(), s, examMergeBean.getSubjects());

                        if (!(examRecordings.isEmpty()) && !(examRecordings1.isEmpty()) && !(examRecordings2.isEmpty()) && !(examRecordings3.isEmpty()) && !(examRecordings4.isEmpty()) && !(examRecordings5.isEmpty())) {
                            for (ExamRecording e : examRecordings) {
                                BigDecimal marks = BigDecimal.valueOf(e.getMark());
                                BigDecimal outOff = BigDecimal.valueOf(e.getOutOff());
                                BigDecimal aggr = marks.divide(outOff, 2, RoundingMode.HALF_UP);
                                BigDecimal t1 = aggr.multiply(examMergeBean.getMark1());

                                for (ExamRecording e1 : examRecordings1) {
                                    if (e.getTeacherSubjects().getSubjects() == e1.getTeacherSubjects().getSubjects() && e.getStudent() == e1.getStudent()) {
                                        BigDecimal marks1 = BigDecimal.valueOf(e1.getMark());
                                        BigDecimal outOff1 = BigDecimal.valueOf(e1.getOutOff());
                                        BigDecimal aggr1 = marks1.divide(outOff1, 2, RoundingMode.HALF_UP);
                                        BigDecimal t2 = aggr1.multiply(examMergeBean.getMark2());
                                        for (ExamRecording e2 : examRecordings2) {
                                            if (e1.getTeacherSubjects().getSubjects() == e2.getTeacherSubjects().getSubjects() && e1.getStudent() == e2.getStudent()) {
                                                BigDecimal marks2 = BigDecimal.valueOf(e2.getMark());
                                                BigDecimal outOff2 = BigDecimal.valueOf(e2.getOutOff());
                                                BigDecimal aggr2 = marks2.divide(outOff2, 2, RoundingMode.HALF_UP);
                                                BigDecimal t3 = aggr2.multiply(examMergeBean.getMark3());
                                                for (ExamRecording e3 : examRecordings3) {
                                                    if (e2.getTeacherSubjects().getSubjects() == e3.getTeacherSubjects().getSubjects() && e2.getStudent() == e3.getStudent()) {
                                                        BigDecimal marks3 = BigDecimal.valueOf(e3.getMark());
                                                        BigDecimal outOff3 = BigDecimal.valueOf(e3.getOutOff());
                                                        BigDecimal aggr3 = marks3.divide(outOff3, 2, RoundingMode.HALF_UP);
                                                        BigDecimal t4 = aggr3.multiply(examMergeBean.getMark4());
                                                        for (ExamRecording e4 : examRecordings4) {
                                                            if (e3.getTeacherSubjects().getSubjects() == e4.getTeacherSubjects().getSubjects() && e3.getStudent() == e4.getStudent()) {
                                                                BigDecimal marks4 = BigDecimal.valueOf(e4.getMark());
                                                                BigDecimal outOff4 = BigDecimal.valueOf(e4.getOutOff());
                                                                BigDecimal aggr4 = marks4.divide(outOff4, 2, RoundingMode.HALF_UP);
                                                                BigDecimal t5 = aggr4.multiply(examMergeBean.getMark5());
                                                                for (ExamRecording e5 : examRecordings5) {
                                                                    if (e4.getTeacherSubjects().getSubjects() == e5.getTeacherSubjects().getSubjects() && e4.getStudent() == e5.getStudent()) {
                                                                        BigDecimal marks5 = BigDecimal.valueOf(e5.getMark());
                                                                        BigDecimal outOff5 = BigDecimal.valueOf(e5.getOutOff());
                                                                        BigDecimal aggr5 = marks5.divide(outOff5, 2, RoundingMode.HALF_UP);
                                                                        BigDecimal t6 = aggr5.multiply(examMergeBean.getMark6());
                                                                        BigDecimal tt = t1.add(t2).add(t3).add(t4).add(t5).add(t6);
                                                                        BigDecimal total = tt.divide(examMergeBean.getOutOff(), 2, RoundingMode.HALF_UP);
                                                                        total = total.multiply(BigDecimal.valueOf(100));
                                                                        double percentage = total.doubleValue();
                                                                        ExamRecording exam = new ExamRecording();
                                                                        GradingSystem system = new GradingSystem();
                                                                        exam.setPercentage(percentage);
                                                                        if (percentage <= 29) {
                                                                            system = gradingSysRepo.findByGradeIgnoreCase("E");
                                                                        } else if (percentage > 29 && percentage <= 34) {
                                                                            system = gradingSysRepo.findByGradeIgnoreCase("D-");
                                                                        } else if (percentage > 34 && percentage <= 39) {
                                                                            system = gradingSysRepo.findByGradeIgnoreCase("D");
                                                                        } else if (percentage > 39 && percentage <= 44) {
                                                                            system = gradingSysRepo.findByGradeIgnoreCase("D+");
                                                                        } else if (percentage > 44 && percentage <= 49) {
                                                                            system = gradingSysRepo.findByGradeIgnoreCase("C-");
                                                                        } else if (percentage > 49 && percentage <= 54) {
                                                                            system = gradingSysRepo.findByGradeIgnoreCase("C");
                                                                        } else if (percentage > 54 && percentage <= 59) {
                                                                            system = gradingSysRepo.findByGradeIgnoreCase("C+");
                                                                        } else if (percentage > 59 && percentage <= 64) {
                                                                            system = gradingSysRepo.findByGradeIgnoreCase("B-");
                                                                        } else if (percentage > 64 && percentage <= 69) {
                                                                            system = gradingSysRepo.findByGradeIgnoreCase("B");
                                                                        } else if (percentage > 69 && percentage <= 74) {
                                                                            system = gradingSysRepo.findByGradeIgnoreCase("B+");
                                                                        } else if (percentage > 74 && percentage <= 79) {
                                                                            system = gradingSysRepo.findByGradeIgnoreCase("A-");
                                                                        } else if (percentage > 79 && percentage <= 100) {
                                                                            system = gradingSysRepo.findByGradeIgnoreCase("A");
                                                                        } else {
                                                                            throw new BadRequestException("Invalid marks");
                                                                        }
                                                                        exam.setGradingSystem(system);
                                                                        exam.setTerm(examMergeBean.getTerm());
                                                                        exam.setYear(examMergeBean.getYear());
                                                                        exam.setStudent(s);
                                                                        exam.setTeacherSubjects(e1.getTeacherSubjects());
                                                                        exam.setExamDate(examMergeBean.getExamRegister().getEffectiveDate());
                                                                        exam.setExamRegister(examMergeBean.getExamRegister());
                                                                        exam.setForms(e4.getForms());
                                                                        exam.setTeachers(e4.getTeachers());
                                                                        exam.setMark(tt.doubleValue());
                                                                        exam.setOutOff(examMergeBean.getOutOff().doubleValue());
                                                                        examRecordingRepo.save(exam);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            } else {
                throw new BadRequestException("Totals don't add up");
            }
        } else {
            BigDecimal mark = examMergeBean.getMark1().add(examMergeBean.getMark2()).add(examMergeBean.getMark3()).add(examMergeBean.getMark4()).add(examMergeBean.getMark5()).add(examMergeBean.getMark6());
            if (mark.equals(examMergeBean.getOutOff())) {
                List<Student> students = studentRepo.findAllByOrderByForms();
                for (Student s : students) {
                    List<ExamRecording> examRecordings = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam(), s);
                    List<ExamRecording> examRecordings1 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam1(), s);
                    List<ExamRecording> examRecordings2 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam2(), s);
                    List<ExamRecording> examRecordings3 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam3(), s);
                    List<ExamRecording> examRecordings4 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam4(), s);
                    List<ExamRecording> examRecordings5 = examRecordingRepo.findByExamRegisterAndStudent(examMergeBean.getExam5(), s);

                    if (!(examRecordings.isEmpty()) && !(examRecordings1.isEmpty()) && !(examRecordings2.isEmpty()) && !(examRecordings3.isEmpty()) && !(examRecordings4.isEmpty()) && !(examRecordings5.isEmpty())) {
                        for (ExamRecording e : examRecordings) {
                            BigDecimal marks = BigDecimal.valueOf(e.getMark());
                            BigDecimal outOff = BigDecimal.valueOf(e.getOutOff());
                            BigDecimal aggr = marks.divide(outOff, 2, RoundingMode.HALF_UP);
                            BigDecimal t1 = aggr.multiply(examMergeBean.getMark1());
                            for (ExamRecording e1 : examRecordings1) {
                                if (e.getTeacherSubjects().getSubjects() == e1.getTeacherSubjects().getSubjects()) {
                                    BigDecimal marks1 = BigDecimal.valueOf(e1.getMark());
                                    BigDecimal outOff1 = BigDecimal.valueOf(e1.getOutOff());
                                    BigDecimal aggr1 = marks1.divide(outOff1, 2, RoundingMode.HALF_UP);
                                    BigDecimal t2 = aggr1.multiply(examMergeBean.getMark2());
                                    for (ExamRecording e2 : examRecordings2) {
                                        if (e1.getTeacherSubjects().getSubjects() == e2.getTeacherSubjects().getSubjects()) {
                                            BigDecimal marks2 = BigDecimal.valueOf(e2.getMark());
                                            BigDecimal outOff2 = BigDecimal.valueOf(e2.getOutOff());
                                            BigDecimal aggr2 = marks2.divide(outOff2, 2, RoundingMode.HALF_UP);
                                            BigDecimal t3 = aggr2.multiply(examMergeBean.getMark3());
                                            for (ExamRecording e3 : examRecordings3) {
                                                if (e2.getTeacherSubjects().getSubjects() == e3.getTeacherSubjects().getSubjects()) {
                                                    BigDecimal marks3 = BigDecimal.valueOf(e3.getMark());
                                                    BigDecimal outOff3 = BigDecimal.valueOf(e3.getOutOff());
                                                    BigDecimal aggr3 = marks3.divide(outOff3, 2, RoundingMode.HALF_UP);
                                                    BigDecimal t4 = aggr3.multiply(examMergeBean.getMark4());
                                                    for (ExamRecording e4 : examRecordings4) {
                                                        if (e3.getTeacherSubjects().getSubjects() == e4.getTeacherSubjects().getSubjects()) {
                                                            BigDecimal marks4 = BigDecimal.valueOf(e4.getMark());
                                                            BigDecimal outOff4 = BigDecimal.valueOf(e4.getOutOff());
                                                            BigDecimal aggr4 = marks4.divide(outOff4, 2, RoundingMode.HALF_UP);
                                                            BigDecimal t5 = aggr4.multiply(examMergeBean.getMark5());
                                                            for (ExamRecording e5 : examRecordings5) {
                                                                if (e4.getTeacherSubjects().getSubjects() == e5.getTeacherSubjects().getSubjects()) {
                                                                    BigDecimal marks5 = BigDecimal.valueOf(e5.getMark());
                                                                    BigDecimal outOff5 = BigDecimal.valueOf(e5.getOutOff());
                                                                    BigDecimal aggr5 = marks5.divide(outOff5, 2, RoundingMode.HALF_UP);
                                                                    BigDecimal t6 = aggr5.multiply(examMergeBean.getMark6());
                                                                    BigDecimal tt = t1.add(t2).add(t3).add(t4).add(t5).add(t6);
                                                                    BigDecimal total = tt.divide(examMergeBean.getOutOff(), 2, RoundingMode.HALF_UP);
                                                                    total = total.multiply(BigDecimal.valueOf(100));
                                                                    double percentage = total.doubleValue();
                                                                    ExamRecording exam = new ExamRecording();
                                                                    GradingSystem system = new GradingSystem();
                                                                    exam.setPercentage(percentage);
                                                                    if (percentage <= 29) {
                                                                        system = gradingSysRepo.findByGradeIgnoreCase("E");
                                                                    } else if (percentage > 29 && percentage <= 34) {
                                                                        system = gradingSysRepo.findByGradeIgnoreCase("D-");
                                                                    } else if (percentage > 34 && percentage <= 39) {
                                                                        system = gradingSysRepo.findByGradeIgnoreCase("D");
                                                                    } else if (percentage > 39 && percentage <= 44) {
                                                                        system = gradingSysRepo.findByGradeIgnoreCase("D+");
                                                                    } else if (percentage > 44 && percentage <= 49) {
                                                                        system = gradingSysRepo.findByGradeIgnoreCase("C-");
                                                                    } else if (percentage > 49 && percentage <= 54) {
                                                                        system = gradingSysRepo.findByGradeIgnoreCase("C");
                                                                    } else if (percentage > 54 && percentage <= 59) {
                                                                        system = gradingSysRepo.findByGradeIgnoreCase("C+");
                                                                    } else if (percentage > 59 && percentage <= 64) {
                                                                        system = gradingSysRepo.findByGradeIgnoreCase("B-");
                                                                    } else if (percentage > 64 && percentage <= 69) {
                                                                        system = gradingSysRepo.findByGradeIgnoreCase("B");
                                                                    } else if (percentage > 69 && percentage <= 74) {
                                                                        system = gradingSysRepo.findByGradeIgnoreCase("B+");
                                                                    } else if (percentage > 74 && percentage <= 79) {
                                                                        system = gradingSysRepo.findByGradeIgnoreCase("A-");
                                                                    } else if (percentage > 79 && percentage <= 100) {
                                                                        system = gradingSysRepo.findByGradeIgnoreCase("A");
                                                                    } else {
                                                                        throw new BadRequestException("Invalid marks");
                                                                    }
                                                                    exam.setGradingSystem(system);
                                                                    exam.setTerm(examMergeBean.getTerm());
                                                                    exam.setYear(examMergeBean.getYear());
                                                                    exam.setStudent(s);
                                                                    exam.setTeacherSubjects(e5.getTeacherSubjects());
                                                                    exam.setExamDate(examMergeBean.getExamRegister().getEffectiveDate());
                                                                    exam.setExamRegister(examMergeBean.getExamRegister());
                                                                    exam.setForms(e5.getForms());
                                                                    exam.setTeachers(e5.getTeachers());
                                                                    exam.setMark(tt.doubleValue());
                                                                    exam.setOutOff(examMergeBean.getOutOff().doubleValue());
                                                                    examRecordingRepo.save(exam);
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            } else {
                throw new BadRequestException("Totals don't add up");
            }


        }
    }
}
