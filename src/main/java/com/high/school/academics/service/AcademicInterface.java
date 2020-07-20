package com.high.school.academics.service;

import com.high.school.academics.model.*;
import com.high.school.exceptions.BadRequestException;
import com.high.school.students.model.Forms;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import java.util.List;

public interface AcademicInterface {
    Page<Department> findDepartments(Pageable pageable, String term);

    DataTablesOutput<StudSubjects> findSubs(DataTablesInput input);

    DataTablesOutput<Department> findDepts(DataTablesInput input);

    void saveDept(Department department);

    void saveSubjects(StudSubjects studSubjects);

    Department findDeptById(Long deptId);

    StudSubjects subjectEdit(Long subId);

    void deleteDept(Long dept);

    void deleteSubjects(Long subject);

    void saveTitles(Titles titles);

    void saveRes(Responsibility responsibility);

    DataTablesOutput<Responsibility> findResp(DataTablesInput input);

    DataTablesOutput<Titles> findTitles(DataTablesInput input);

    Titles findTitle(Long title);

    Responsibility findgetResp(Long res);

    void deleteTitle(Long title);

    void deleteRes(Long res);

    Page<Titles> pageTitle(Pageable pageable, String term);

    Page<Responsibility> pageResponsibility(Pageable pageable, String term);

    void saveTeacher(Teachers teachers) throws BadRequestException;

    Teachers getTeacher(Long teacherId);

    Forms findForm(Long classId);

    List<StudSubjects> findPending();

    void saveTeacherSubject(TeacherSubjects subject);

    TeacherSubjects checkFirst(Teachers t, Forms forms, StudSubjects s);

    void clearAllPending();

    DataTablesOutput<StudSubjects> getAllAssigned(DataTablesInput input);

    DataTablesOutput<StudSubjects> getAllUnAssigned(DataTablesInput input);

    List<StudSubjects> findAssigned();

    DataTablesOutput<Teachers> findAllTeachers(DataTablesInput input);

    void saveAssigned(Long classId, Long teacher) throws BadRequestException;

    DataTablesOutput<TeacherSubjects> findAllAssignedSubjects(DataTablesInput input);

    Page<Teachers> pageTeacher(Pageable pageable, String term);

    void transferAssignedSubjects(Long classId, Long tFrom, Long tTo) throws BadRequestException;

    void removeAllAssignedSubjects(Long teacher);

    void deleteTeacher(Long teacher);

    Page<Responsibility> pageWorkerResponsibility(Pageable pageable, String term);

    Staff getStaff(Long tCode);

    void saveStaff(Staff staff) throws BadRequestException;

    DataTablesOutput<Staff> findAllWorkers(DataTablesInput input);

    void deleteWorker(Long staff);

    DataTablesOutput<Staff> findRegisterWorkers(DataTablesInput input);

    DataTablesOutput<Teachers> findRegisterTeachers(DataTablesInput input);

    DataTablesOutput<Staff> nameRegisterStaff(DataTablesInput input, String name);

    DataTablesOutput<Teachers> nameRegisterTeachers(DataTablesInput input, String name);

    Page<StudSubjects> pageSubjects(Pageable pageable, String term);

    DataTablesOutput<ClassSubjects> getClassSubjects(DataTablesInput input);

    void saveClassSubjects(ClassSubjects classSubjects);

    void deleteClassSubject(Long code);

    void deleteSubjectGroup(Long code);

    void saveSubjectGroup(SubjectGroups subjectGroups);

    DataTablesOutput<SubjectGroups> getSubjectGroups(DataTablesInput input);

    void saveTermDates(TermDates termDates);

    DataTablesOutput<TermDates> getTermDatesTbl(DataTablesInput input);

    void deleteTermDates(Long code);
}
