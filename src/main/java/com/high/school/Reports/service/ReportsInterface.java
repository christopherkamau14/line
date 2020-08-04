package com.high.school.Reports.service;

import com.high.school.Reports.utils.*;
import com.high.school.academics.model.Teachers;
import com.high.school.examination.model.ExamRegister;
import com.high.school.examination.model.MIssedExams;
import com.high.school.examination.model.PositioningBean;
import com.high.school.examination.model.ReportFormBean;
import com.high.school.exceptions.BadRequestException;
import com.high.school.students.model.Forms;
import com.high.school.students.model.Student;
import com.high.school.timetabling.model.TeacherTableBean;
import com.high.school.timetabling.model.TimetableBean;

import java.util.List;

public interface ReportsInterface {

    List<MIssedExams> findMissed(ExamRegister examRegister,Forms forms);

    List<SubjectAnalysisBean> subjectAnalys(ExamRegister examRegister, Forms forms);

    List<ClassAnalysisBean> classAnalys(ExamRegister exam,Forms forms);

    List<GenderAnalysisBean> genderAnalys(ExamRegister exam,Forms forms);

    List<MeritListBean> meritList(ExamRegister exam,Forms forms) throws BadRequestException;

    List<GenderMeritBean> genderMeritList(ExamRegister exam,Forms forms);

    List<MeritListBean> singleExamList(ExamRegister exam,Forms forms);

    List<MeritListBean> singleExamBroadList(ExamRegister examRegister);

    List<ReportFormBean> reportForm(ExamRegister exam, Forms forms, Student student, String remarks, String hRemarks) throws BadRequestException;

    List<TimetableBean> classtt(Forms forms);

    List<TeacherTableBean> teacherstt(Teachers teachers);
}
