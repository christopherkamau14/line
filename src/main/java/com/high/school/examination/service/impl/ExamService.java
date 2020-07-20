package com.high.school.examination.service.impl;

import com.high.school.Locale.model.CountrySpecs;
import com.high.school.academics.model.GradingSystem;
import com.high.school.academics.model.StudSubjects;
import com.high.school.academics.model.TeacherSubjects;
import com.high.school.academics.model.Teachers;
import com.high.school.academics.repo.GradingSysRepo;
import com.high.school.academics.repo.SubjectsRepo;
import com.high.school.academics.repo.TeacherRepo;
import com.high.school.academics.repo.TeacherSubjectsRepo;
import com.high.school.examination.model.*;
import com.high.school.examination.repo.*;
import com.high.school.examination.service.ExamInterface;
import com.high.school.exceptions.BadRequestException;
import com.high.school.students.model.Forms;
import com.high.school.students.model.Student;
import com.high.school.students.model.Term;
import com.high.school.students.model.Year;
import com.high.school.students.repo.FormsRepo;
import com.high.school.students.repo.StudentRepo;
import com.high.school.students.repo.TermRepo;
import com.high.school.students.repo.YearRepo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamService implements ExamInterface {

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


    @Override
    public void deleteType(Long code) {
        examTypeRepo.deleteById(code);
    }

    @Override
    public void postType(ExamType examType) {
        if (examType.getTypeCode() == null) {
            examTypeRepo.save(examType);
        } else {
            ExamType type = examTypeRepo.findByTypeCode(examType.getTypeCode());
            type.setTypeName(examType.getTypeName());
            type.setTypeDesc(examType.getTypeDesc());
            examTypeRepo.save(type);
        }
    }

    @Override
    public DataTablesOutput<ExamType> getTypeTable(DataTablesInput input) {
        return examTypeRepo.findAll(input);
    }

    @Override
    public ExamType typeEdit(Long fCode) {
        return examTypeRepo.findByTypeCode(fCode);
    }

    @Override
    public Page<Year> pageYear(Pageable pageable, String term) {
        if (term == null || StringUtils.isBlank(term)) {
            return yearRepo.findAll(pageable);
        } else {
            return yearRepo.findByYearNameContainingIgnoreCase(term, pageable);
        }

    }

    @Override
    public Page<TeacherSubjects> pageSubject(Pageable pageable, String term, Long code, Long classCode) {
        Teachers teachers = teacherRepo.findByTeacherCode(code);
        if (term == null || StringUtils.isBlank(term)) {
            return teacherSubjectsRepo.findByTeachersAndForms_ClassCode(teachers, classCode, pageable);
        } else {
            return teacherSubjectsRepo.findByTeachersAndForms_ClassCodeAndSubjects_NameContainingIgnoreCase(teachers, term, classCode, pageable);
        }
    }

    @Override
    public Page<Student> pageStudent(Pageable pageable, String term, Long code) {
        Forms forms = formsRepo.findByClassCode(code);
        if (term == null || StringUtils.isBlank(term)) {
            return studentRepo.findByForms(forms, pageable);
        } else {
            return studentRepo.findByFormsAndNameContainingIgnoreCase(forms, term, pageable);
        }

    }

    @Override
    public Page<Term> pageTerm(Pageable pageable, String term) {
        if (term == null || StringUtils.isBlank(term)) {
            return termRepo.findAll(pageable);
        } else {
            return termRepo.findByTermNumberContaining(term, pageable);
        }
    }

    @Override
    public Page<ExamType> pageType(Pageable pageable, String term) {
        if (term == null || StringUtils.isBlank(term)) {
            return examTypeRepo.findAll(pageable);
        } else {
            return examTypeRepo.findByTypeNameContainingIgnoreCase(term, pageable);
        }
    }

    @Override
    public void postReg(ExamRegister examRegister) {
        if (examRegister.getExamCode() == null) {
            examRegister.setAuthorised("N");
            if (examRegister.getCombinedExam() == null) {
                examRegister.setCombinedExam("N");
            } else {
                examRegister.setCombinedExam("Y");
            }
            if (examRegister.getFinalExam() == null) {
                examRegister.setFinalExam("N");
            } else {
                examRegister.setFinalExam("Y");
            }
            examRegister.setDisplayName(examRegister.getExamName());
            examRegRepo.save(examRegister);
        } else {
            ExamRegister exam = examRegRepo.findByExamCode(examRegister.getExamCode());
            exam.setAuthorised("N");
            exam.setGrading(examRegister.getGrading());
            exam.setDisplayName(examRegister.getExamName());
            exam.setEffectiveDate(examRegister.getEffectiveDate());
            exam.setLockDate(examRegister.getLockDate());
            exam.setGrading(examRegister.getGrading());
            exam.setExamName(examRegister.getExamName());
            exam.setExamType(examRegister.getExamType());
            exam.setForms(examRegister.getForms());
            exam.setMonth(examRegister.getMonth());
            exam.setTerm(examRegister.getTerm());
            exam.setYear(examRegister.getYear());
            if (examRegister.getCombinedExam() == null) {
                exam.setCombinedExam("N");
            } else {
                exam.setCombinedExam("Y");
            }
            if (examRegister.getFinalExam() == null) {
                exam.setFinalExam("N");
            } else {
                exam.setFinalExam("Y");
            }
            examRegRepo.save(exam);
        }
    }

    @Override
    public DataTablesOutput<ExamRegister> getExamRegTable(DataTablesInput input) {
        return examRegRepo.findAll(input);
    }

    @Override
    public void deleteExamReg(Long code) {
        examRegRepo.deleteById(code);
    }

    @Override
    public Page<ExamRegister> pageRegister(Pageable pageable, String term) {
        if (term == null || StringUtils.isBlank(term)) {
            return examRegRepo.findAll(pageable);
        } else {
            return examRegRepo.findByExamNameContainingIgnoreCase(term, pageable);
        }
    }

    @Override
    public Page<Teachers> pageTeachers(Pageable pageable, String term) {
        if (term == null || StringUtils.isBlank(term)) {
            return teacherRepo.findAll(pageable);
        } else {
            return teacherRepo.findByNameContainingIgnoreCase(term, pageable);
        }
    }

    @Override
    public ExamRecording postRecording(ExamRecording examRecording) throws BadRequestException {
        if(examRecording.getExamRegister().getStatus()==null) {
            if (examRecording.getExamRecordCode() == null) {
                ExamRecording exam = examRecordingRepo.findByStudentAndExamRegisterAndTeacherSubjects_Subjects(examRecording.getStudent(),
                        examRecording.getExamRegister(), examRecording.getTeacherSubjects().getSubjects());
                if (exam == null) {
                    double marks = examRecording.getMark();
                    double outOff = examRecording.getOutOff();
                    double perc = (marks / outOff) * 100;
                    double percentage = Math.round(perc);

                    examRecording.setPercentage(percentage);
                    GradingSystem system = new GradingSystem();

                    if (percentage <= 29) {
                        system = gradingSysRepo.findByGradeIgnoreCase("E");
                        if (system == null) {
                            throw new BadRequestException("Grading system is not set");
                        }
                    } else if (percentage > 29 && percentage <= 34) {
                        system = gradingSysRepo.findByGradeIgnoreCase("D-");
                        if (system == null) {
                            throw new BadRequestException("Grading system is not set");
                        }
                    } else if (percentage > 34 && percentage <= 39) {
                        system = gradingSysRepo.findByGradeIgnoreCase("D");
                        if (system == null) {
                            throw new BadRequestException("Grading system is not set");
                        }
                    } else if (percentage > 39 && percentage <= 44) {
                        system = gradingSysRepo.findByGradeIgnoreCase("D+");
                        if (system == null) {
                            throw new BadRequestException("Grading system is not set");
                        }
                    } else if (percentage > 44 && percentage <= 49) {
                        system = gradingSysRepo.findByGradeIgnoreCase("C-");
                        if (system == null) {
                            throw new BadRequestException("Grading system is not set");
                        }
                    } else if (percentage > 49 && percentage <= 54) {
                        system = gradingSysRepo.findByGradeIgnoreCase("C");
                        if (system == null) {
                            throw new BadRequestException("Grading system is not set");
                        }
                    } else if (percentage > 54 && percentage <= 59) {
                        system = gradingSysRepo.findByGradeIgnoreCase("C+");
                        if (system == null) {
                            throw new BadRequestException("Grading system is not set");
                        }
                    } else if (percentage > 59 && percentage <= 64) {
                        system = gradingSysRepo.findByGradeIgnoreCase("B-");
                        if (system == null) {
                            throw new BadRequestException("Grading system is not set");
                        }
                    } else if (percentage > 64 && percentage <= 69) {
                        system = gradingSysRepo.findByGradeIgnoreCase("B");
                        if (system == null) {
                            throw new BadRequestException("Grading system is not set");
                        }
                    } else if (percentage > 69 && percentage <= 74) {
                        system = gradingSysRepo.findByGradeIgnoreCase("B+");
                        if (system == null) {
                            throw new BadRequestException("Grading system is not set");
                        }
                    } else if (percentage > 74 && percentage <= 79) {
                        system = gradingSysRepo.findByGradeIgnoreCase("A-");
                        if (system == null) {
                            throw new BadRequestException("Grading system is not set");
                        }
                    } else if (percentage > 79 && percentage <= 100) {
                        system = gradingSysRepo.findByGradeIgnoreCase("A");
                        if (system == null) {
                            throw new BadRequestException("Grading system is not set");
                        }
                    } else {
                        throw new BadRequestException("Invalid marks");
                    }
                    examRecording.setGradingSystem(system);
                    examRecordingRepo.save(examRecording);
                    return examRecording;
                } else {
                    throw new BadRequestException("Marks for this student are already recorded");
                }
            } else {
                ExamRecording recording = examRecordingRepo.findByExamRecordCode(examRecording.getExamRecordCode());
                double marks = examRecording.getMark();
                double outOff = examRecording.getOutOff();
                double percentage = (marks / outOff) * 100;
                examRecording.setPercentage(percentage);
                GradingSystem system = new GradingSystem();
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
                examRecording.setGradingSystem(system);
                recording.setGradingSystem(examRecording.getGradingSystem());
                recording.setPercentage(examRecording.getPercentage());
                recording.setExamDate(examRecording.getExamDate());
                recording.setExamRegister(examRecording.getExamRegister());
                recording.setForms(examRecording.getForms());
                recording.setMark(examRecording.getMark());
                recording.setOutOff(examRecording.getOutOff());
                recording.setRemarks(examRecording.getRemarks());
                recording.setStudent(examRecording.getStudent());
                recording.setTeacherSubjects(examRecording.getTeacherSubjects());
                recording.setTeachers(examRecording.getTeachers());
                recording.setTerm(examRecording.getTerm());
                recording.setYear(examRecording.getYear());
                examRecordingRepo.save(recording);
                return recording;
            }
        }else{
            throw new BadRequestException("Exam is already locked so no operations on it");
        }
    }

    @Override
    public DataTablesOutput<ExamRecording> getExamRecTable(DataTablesInput input, Long code) {
        return examRecordingRepo.findAll(input, CountrySpecs.dataExamRec(code));
    }

    @Override
    public DataTablesOutput<ExamRecording> getExamRecClassTable(DataTablesInput input, Long code, Long classCode) {
        return examRecordingRepo.findAll(input, CountrySpecs.dataExamRec(code).and(CountrySpecs.dataExamClass(classCode)));
    }

    @Override
    public DataTablesOutput<ExamRecording> getExamRecTeaTable(DataTablesInput input, Long code, Long classCode, Long teacher) {
        return examRecordingRepo.findAll(input,
                CountrySpecs.dataExamRec(code).and(CountrySpecs.dataExamClass(classCode).and(CountrySpecs.dataExamTea(teacher))));
    }

    @Override
    public DataTablesOutput<ExamRecording> getExamRecSubTable(DataTablesInput input, Long code, Long classCode, Long teacher, Long subject) {
        return examRecordingRepo.findAll(input, CountrySpecs.dataExamRec(code)
                .and(CountrySpecs.dataExamClass(classCode))
                .and(CountrySpecs.dataExamTea(teacher))
                .and(CountrySpecs.dataExamSubject(subject)));
    }

    @Override
    public DataTablesOutput<ExamRecording> getExamRecStudTable(DataTablesInput input, Long code, Long classCode, Long teacher, Long subject, Long student) {
        return examRecordingRepo.findAll(input, CountrySpecs.dataExamRec(code)
                .and(CountrySpecs.dataExamClass(classCode))
                .and(CountrySpecs.dataExamTea(teacher))
                .and(CountrySpecs.dataExamSubject(subject))
                .and(CountrySpecs.dataExamStud(student)));
    }

    @Override
    public void postMissed(Student student, Forms forms, TeacherSubjects subjects, ExamRegister exam) throws BadRequestException {
        if(exam.getStatus()==null) {
            MIssedExams mIssed = missedExamRepo.findFirstByTeacherSubjectsAndFormsAndExamRegisterAndStudent(subjects, forms, exam, student);
            if (mIssed == null) {
                MIssedExams mIssedExams = new MIssedExams();
                mIssedExams.setExamRegister(exam);
                mIssedExams.setForms(forms);
                mIssedExams.setTeacherSubjects(subjects);
                mIssedExams.setStudent(student);
                mIssedExams.setSubjectName(subjects.getSubjects().getName());
                mIssedExams.setClassName(subjects.getForms().getAbbr());
                missedExamRepo.save(mIssedExams);
            } else {
                throw new BadRequestException("This student has already missed the exam");
            }
        }
        else{
            throw new BadRequestException("Exam has been locked so no operations allowed on it");
        }

    }

    @Override
    public void undoMissExam(Student student, Forms forms, TeacherSubjects subjects, ExamRegister exam) throws BadRequestException {
        MIssedExams mIssed = missedExamRepo.findByTeacherSubjectsAndFormsAndExamRegisterAndStudent(subjects, forms, exam, student);
        if (mIssed == null) {
            throw new BadRequestException("This student has not Missed the exam");
        } else {
            missedExamRepo.deleteById(mIssed.getMissedCode());
        }

    }

    @Override
    public ExamRecording checkExcel(String student, String subject, String form, String teacher) {

        return examRecordingRepo.findFirstByStudent_admNoAndTeacherSubjects_Subjects_nameAndForms_Abbr(student, subject, form);

    }

    @Override
    public TeacherSubjects findTeacherSubject(Forms forms, StudSubjects subject, Teachers teachers) {
        return teacherSubjectsRepo.findFirstByTeachersAndFormsAndSubjects(teachers, forms, subject);
    }

    @Override
    public Student findStudent(String student) {
        return studentRepo.findFirstByAdmNo(student);
    }

    @Override
    public Forms findForm(String form) {
        return formsRepo.findFirstByAbbrIgnoreCase(form);
    }

    @Override
    public StudSubjects findSubjects(String subject) {
        return subjectsRepo.findFirstByNameIgnoreCase(subject);
    }

    @Override
    public ExamRegister findExam(String exam) {
        return examRegRepo.findFirstByExamNameIgnoreCase(exam);
    }

    @Override
    public Term findTerm(String term) {
        int terms = Integer.parseInt(term);
        return termRepo.findFirstByTermNumber(terms);
    }

    @Override
    public Year findYear(String year) {
        return yearRepo.findFirstByYearName(year);
    }

    @Override
    public Teachers findTeachers(String teacher) {
        return teacherRepo.findFirstByName(teacher);
    }

    @Override
    public void postExcel(ExamRecording examRecording) throws BadRequestException {
        if(examRecording.getExamRegister().getStatus()==null) {
            ExamRecording exam = examRecordingRepo.findByStudentAndExamRegisterAndTeacherSubjectsAndForms(examRecording.getStudent(), examRecording.getExamRegister(), examRecording.getTeacherSubjects(), examRecording.getForms());
            GradingSystem system = new GradingSystem();

            if (exam == null) {
                double marks = examRecording.getMark();
                double outOff = examRecording.getOutOff();
                double percentage = (marks / outOff) * 100;
                examRecording.setPercentage(percentage);

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
                examRecording.setGradingSystem(system);
                examRecordingRepo.save(examRecording);
            } else {
                double marks = examRecording.getMark();
                double outOff = examRecording.getOutOff();
                double percentage = (marks / outOff) * 100;
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
                exam.setTeacherSubjects(examRecording.getTeacherSubjects());
                exam.setTeachers(examRecording.getTeachers());
                exam.setExamRegister(examRecording.getExamRegister());
                exam.setStudent(examRecording.getStudent());
                exam.setForms(examRecording.getForms());
                exam.setMark(marks);
                exam.setOutOff(outOff);
                examRecording.setRemarks(examRecording.getRemarks());
                exam.setExamDate(examRecording.getExamDate());
                exam.setYear(examRecording.getYear());
                exam.setTerm(examRecording.getTerm());
                examRecordingRepo.save(exam);
            }
        }else{
            throw new BadRequestException("Exam is locked so no operations can be perfomed on it");
        }
    }

    @Override
    public List<ExamBean> drawRecords(Forms code, TeacherSubjects subject, ExamRegister exam) {
        List<ExamBean> myList = new ArrayList<>();
        List<ExamRecording> examRecordings = examRecordingRepo.findByTeacherSubjectsAndFormsAndExamRegister(subject, code, exam);
        if (!(examRecordings.isEmpty())) {

            for (ExamRecording e : examRecordings) {
                ExamBean examBean = new ExamBean();
                examBean.setName(e.getExamRegister().getExamName());
                examBean.setSubject(e.getTeacherSubjects().getSubjects().getName());
                examBean.setTeacher(e.getTeachers().getName());
                examBean.setForm(e.getForms().getAbbr());
                examBean.setStudent(e.getStudent().getAdmNo());
                examBean.setTerm(String.valueOf(e.getTerm().getTermNumber()));
                examBean.setYear(e.getYear().getYearName());
                examBean.setDate(e.getExamDate().toString());
                examBean.setMarks(String.valueOf(e.getMark()));
                examBean.setTotal(String.valueOf(e.getOutOff()));
                examBean.setGrade(e.getGradingSystem().getGrade());
                examBean.setRemarks(e.getRemarks());
                myList.add(examBean);

            }
            ExamBean bean = new ExamBean();
            bean.setName("EXAM");
            bean.setSubject("SUBJECT");
            bean.setTeacher("TEACHER");
            bean.setForm("FORM");
            bean.setStudent("STUDENT");
            bean.setTerm("TERM");
            bean.setYear("YEAR");
            bean.setDate("DATE");
            bean.setMarks("MARKS");
            bean.setTotal("TOTAL");
            bean.setGrade("GRADE");
            bean.setRemarks("REMARKS");
            myList.add(0, bean);

        }
        return myList;

    }

    @Override
    public void positionExams(ExamRegister exam, Forms code) throws BadRequestException {
        if(exam.getStatus()==null) {
            List<Student> examRecordings = studentRepo.findByForms_ClassCode(code.getClassCode());
            List<PositioningBean> positioningBeans = new ArrayList<>();
            Long number = Long.valueOf(0);

            for (Student e : examRecordings) {
                List<ExamRecording> list = examRecordingRepo.findByExamRegisterAndStudent(exam, e);
                BigDecimal stds = BigDecimal.valueOf(examRecordingRepo.countTeacherSubjectsDistinctByStudentAndExamRegister(e, exam));

                if (!(list.isEmpty())) {
                    BigDecimal aggregate = BigDecimal.ZERO;
                    for (ExamRecording f : list) {
                        aggregate = aggregate.add(BigDecimal.valueOf(f.getPercentage()));

                    }
                    BigDecimal mean = aggregate.divide(stds, 2, RoundingMode.HALF_UP);
                    PositioningBean pos = new PositioningBean();
                    String stGrade = getGrade(mean.doubleValue());
                    pos.setGrade(stGrade);
                    pos.setTotalMarks(aggregate);
                    pos.setMarks(mean);
                    pos.setExamRegister(exam);
                    pos.setStudent(e);
                    pos.setExam(exam.getExamName());
                    pos.setForms(code);
                    pos.setClassName(e.getForms().getAbbr());
                    pos.setAdmNo(e.getAdmNo());
                    pos.setName(e.getName());
                    pos.setSubjectsEntered(stds);
                    number = number + 1;
                    positioningBeans.add(pos);

                }
            }
            Comparator<PositioningBean> compareByName = Comparator
                    .comparing(PositioningBean::getMarks).reversed();

            List<PositioningBean> beans = positioningBeans.stream()
                    .sorted(compareByName)
                    .collect(Collectors.toList());

            Long position = Long.valueOf(0);
            BigDecimal classMarks = BigDecimal.ZERO;
            for (PositioningBean m : beans) {
                classMarks = classMarks.add(m.getMarks());
            }
            classMarks = classMarks.divide(BigDecimal.valueOf(number), 2, RoundingMode.HALF_UP);
            PositionClass pc = new PositionClass();
            String gr = getGrade(classMarks.doubleValue());
            pc.setGrade(gr);
            pc.setAggregate(classMarks);
            for (PositioningBean m : beans) {
                PositioningBean posBean = new PositioningBean();
                ExamRegister examRegister = examRegRepo.findByExamCode(m.getExamRegister().getExamCode());
                Student student = studentRepo.findByStId(m.getStudent().getStId());
                Forms forms = formsRepo.findByClassCode(m.getForms().getClassCode());
                position = position + 1;
                String sgrade = getGrade(m.getMarks().doubleValue());
                posBean.setName(m.getName());
                posBean.setAdmNo(m.getAdmNo());
                posBean.setClassName(m.getClassName());
                posBean.setExam(m.getExam());
                posBean.setPosition(position);
                posBean.setForms(forms);
                posBean.setStudent(student);
                posBean.setExamRegister(examRegister);
                posBean.setGrade(sgrade);
                posBean.setClassMarks(pc.getAggregate());
                posBean.setClassGrade(pc.getGrade());
                posBean.setMarks(m.getMarks());
                posBean.setTotalMarks(m.getTotalMarks());
                posBean.setSubjectsEntered(m.getSubjectsEntered());
                positionRepo.save(posBean);

            }
        }
        else{
            throw new BadRequestException("Exam is already locked and cant be positioned again");
        }
        }

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
    public void combineExam(CombineBean combineBean) throws BadRequestException {
        List<Student> students = studentRepo.findAllByOrderByForms();
        if (!(students.isEmpty())) {
            for (Student s : students) {
                List<ExamRecording> examRecordings = examRecordingRepo.findByExamRegisterAndStudent(combineBean.getFinalExam(), s);
                List<ExamRecording> examRecordings1 = examRecordingRepo.findByExamRegisterAndStudent(combineBean.getCombineExam(), s);
                if (!(examRecordings.isEmpty()) && !(examRecordings1.isEmpty())) {
                    for (ExamRecording e : examRecordings) {
                        BigDecimal marks = BigDecimal.valueOf(e.getMark());
                        BigDecimal outOff = BigDecimal.valueOf(e.getOutOff());
                        BigDecimal aggr = marks.divide(outOff, 2, RoundingMode.HALF_UP);
                        BigDecimal t1 = aggr.multiply(BigDecimal.valueOf(100));
                        for (ExamRecording e1 : examRecordings1) {
                            if (e.getTeacherSubjects().getSubjects() == e1.getTeacherSubjects().getSubjects()) {
                                BigDecimal marks1 = BigDecimal.valueOf(e1.getMark());
                                BigDecimal outOff1 = BigDecimal.valueOf(e1.getOutOff());
                                BigDecimal aggr1 = marks1.divide(outOff1, 2, RoundingMode.HALF_UP);
                                BigDecimal t2 = aggr1.multiply(BigDecimal.valueOf(100));
                                BigDecimal tt = t1.add(t2);
                                BigDecimal score = tt.divide(BigDecimal.valueOf(2));
                                double percentage = score.doubleValue();
                                ExamRecording exam = examRecordingRepo.findByExamRecordCode(e1.getExamRecordCode());
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
                                exam.setTerm(combineBean.getTerm());
                                exam.setYear(combineBean.getYear());
                                exam.setStudent(s);
                                exam.setTeacherSubjects(e1.getTeacherSubjects());
                                exam.setExamDate(combineBean.getCombineExam().getEffectiveDate());
                                exam.setExamRegister(combineBean.getCombineExam());
                                exam.setForms(e1.getForms());
                                exam.setTeachers(e1.getTeachers());
                                exam.setMark(score.doubleValue());
                                double r = 100.0;
                                exam.setOutOff(r);
                                examRecordingRepo.save(exam);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public PositioningBean positioningBean(ExamRegister exam, Forms forms, Student student) {
        return positionRepo.findByExamRegisterAndFormsAndStudent(exam,forms,student);
    }

    @Override
    public void lockExams(ExamRegister exam) throws BadRequestException {
        ExamRegister examRegister=examRegRepo.findByExamCode(exam.getExamCode());
        List<PositioningBean> positioningBeans=positionRepo.findByExamRegister(exam);
        if (!(positioningBeans.isEmpty())){
            examRegister.setStatus("Locked");
            examRegRepo.save(examRegister);
        }
       else{
           throw new BadRequestException("Position Exam First");
        }
    }

    @Override
    public void unLockExam(ExamRegister exam) {
        ExamRegister examRegister=examRegRepo.findByExamCode(exam.getExamCode());
        examRegister.setStatus(null);
        examRegRepo.save(examRegister);
    }
}
