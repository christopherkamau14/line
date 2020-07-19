package com.high.school.academics.service.Impl;

import com.high.school.Locale.model.CountrySpecs;
import com.high.school.academics.model.*;
import com.high.school.academics.repo.*;
import com.high.school.academics.service.AcademicInterface;
import com.high.school.exceptions.BadRequestException;
import com.high.school.students.model.Forms;
import com.high.school.students.repo.FormsRepo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcademicService implements AcademicInterface {

    @Autowired
    DepartmentRepo departmentRepo;

    @Autowired
    SubjectsRepo subjectsRepo;

    @Autowired
    TitlesRepo titlesRepo;

    @Autowired
    TeacherRepo teacherRepo;

    @Autowired
    FormsRepo formsRepo;

    @Autowired
    StaffRepo staffRepo;

    @Autowired
    TeacherSubjectsRepo teacherSubjectsRepo;

    @Autowired
    ResponsibilityRepo responsibilityRepo;

    @Autowired
    ClassSubjectsRepo classSubjectsRepo;

    @Autowired
    SubjectGroupRepo subjectGroupRepo;

    @Autowired
    TermDatesRepo termDatesRepo;

    @Override
    public Page<Department> findDepartments(Pageable pageable, String term) {
        if (term == null || StringUtils.isBlank(term)) {
            return departmentRepo.findAll(pageable);
        }else{
            return departmentRepo.findByDepartmentNameContainingIgnoreCase(term, pageable);
        }
    }

    @Override
    public DataTablesOutput<StudSubjects> findSubs(DataTablesInput input) {
        return subjectsRepo.findAll(input);
    }

    @Override
    public DataTablesOutput<Department> findDepts(DataTablesInput input) {
        return departmentRepo.findAll(input);
    }

    @Override
    public void saveDept(Department department) {
        if(department.getDepartmentCode()==null){
            departmentRepo.save(department);
        }
        else{
            Department dept=departmentRepo.findByDepartmentCode(department.getDepartmentCode());
            dept.setCode(department.getCode());
            dept.setDepartmentDesc(department.getDepartmentDesc());
            dept.setDepartmentName(department.getDepartmentName());
            dept.setDepartmentSeq(department.getDepartmentSeq());
            departmentRepo.save(dept);
        }
    }

    @Override
    public void saveSubjects(StudSubjects studSubjects) {
        if(studSubjects.getSubjectCode()==null){
            if(studSubjects.getMainSubject()!=null){
                studSubjects.setMainSubject("Y");
            }
            else{
                studSubjects.setMainSubject("N");
            }
            if(studSubjects.getCombinedSubject()!=null){
                studSubjects.setCombinedSubject("Y");
            }
            else{
                studSubjects.setCombinedSubject("N");
            }
            if(studSubjects.getProsSubject()!=null){
                studSubjects.setProsSubject("Y");
            }
            else{
                studSubjects.setProsSubject("N");
            }
            studSubjects.setAssigned("assigned");
            subjectsRepo.save(studSubjects);
        }
        else{
            StudSubjects subjects=subjectsRepo.findBySubjectCode(studSubjects.getSubjectCode());
            subjects.setName(studSubjects.getName());
            subjects.setOrder(studSubjects.getOrder());
            subjects.setDepartment(studSubjects.getDepartment());
            subjects.setSubjectDesc(studSubjects.getSubjectDesc());
            subjects.setTimeTableName(studSubjects.getTimeTableName());
            subjects.setMultiplier(studSubjects.getMultiplier());
            if(studSubjects.getMainSubject()!=null){
                subjects.setMainSubject("Y");
            }
            else{
                subjects.setMainSubject("N");
            }
            if(studSubjects.getCombinedSubject()!=null){
                subjects.setCombinedSubject("Y");
            }
            else{
                subjects.setCombinedSubject("N");
            }
            if(studSubjects.getProsSubject()!=null){
                subjects.setProsSubject("Y");
            }
            else{
                subjects.setProsSubject("N");
            }
           subjectsRepo.save(subjects);
        }

    }

    @Override
    public void saveClassSubjects(ClassSubjects classSubjects) {
        if(classSubjects.getClassSubjectCode()==null){
            if(classSubjects.getStrokedSubject()!=null){
                classSubjects.setStrokedSubject("Y");
            }
            else{
                classSubjects.setStrokedSubject("N");
            }
            if(classSubjects.getSubjects()!=null){
                classSubjects.setName(classSubjects.getSubjects().getName());
            }
            classSubjectsRepo.save(classSubjects);
        }
        else{
            ClassSubjects subjects=classSubjectsRepo.findByClassSubjectCode(classSubjects.getClassSubjectCode());
            subjects.setDoubleLessons(classSubjects.getDoubleLessons());
            subjects.setForms(classSubjects.getForms());
            subjects.setSubjects(classSubjects.getSubjects());
            subjects.setWeekLessons(classSubjects.getWeekLessons());
            subjects.setStrokedWith(classSubjects.getStrokedWith());
            if(classSubjects.getStrokedSubject()!=null){
                subjects.setStrokedSubject("Y");
            }
            else{
                subjects.setStrokedSubject("N");
            }
            if(classSubjects.getSubjects()!=null){
                subjects.setName(classSubjects.getSubjects().getName());
            }
            classSubjectsRepo.save(subjects);
        }
    }

    @Override
    public void saveSubjectGroup(SubjectGroups subjectGroups) {
        if(subjectGroups.getGroupCode()==null){
            if(subjectGroups.getCompulsorySubject()!=null){
                subjectGroups.setCompulsorySubject("Y");
            }
            else{
                subjectGroups.setCompulsorySubject("N");
            }
            if(subjectGroups.getSubjects()!=null){
                subjectGroups.setName(subjectGroups.getSubjects().getName());
            }
            subjectGroupRepo.save(subjectGroups);
        }
        else{
            SubjectGroups subjects=subjectGroupRepo.findByGroupCode(subjectGroups.getGroupCode());
            subjects.setGroupNumber(subjectGroups.getGroupNumber());
            subjects.setSubjects(subjectGroups.getSubjects());
            subjects.setSubjects(subjectGroups.getSubjects());
            if(subjectGroups.getCompulsorySubject()!=null){
                subjects.setCompulsorySubject("Y");
            }
            else{
                subjects.setCompulsorySubject("N");
            }
            if(subjectGroups.getSubjects()!=null){
                subjects.setName(subjectGroups.getSubjects().getName());
            }
            subjectGroupRepo.save(subjects);
        }
    }

    @Override
    public Department findDeptById(Long deptId) {
        return departmentRepo.findByDepartmentCode(deptId);
    }

    @Override
    public StudSubjects subjectEdit(Long subId) {
        return subjectsRepo.findBySubjectCode(subId);
    }

    @Override
    public void deleteDept(Long dept) {
        departmentRepo.deleteById(dept);
    }

    @Override
    public void deleteSubjects(Long subject) {
      subjectsRepo.deleteById(subject);
    }

    @Override
    public void saveTitles(Titles titles) {
        if (titles.getTitleCode()==null){
            titlesRepo.save(titles);
        }
        else{
            Titles title=titlesRepo.findByTitleCode(titles.getTitleCode());
            title.setTitleName(titles.getTitleName());
            title.setTitleDesc(titles.getTitleDesc());
            titlesRepo.save(title);
        }
    }

    @Override
    public void saveRes(Responsibility responsibility) {
    if (responsibility.getResponsibilityCode()==null){
    responsibilityRepo.save(responsibility);
    }
    else{
        Responsibility res=responsibilityRepo.findByResponsibilityCode(responsibility.getResponsibilityCode());
        res.setCategory(responsibility.getCategory());
        res.setResponsibilityName(responsibility.getResponsibilityName());
        responsibilityRepo.save(res);
    }
    }

    @Override
    public DataTablesOutput<Responsibility> findResp(DataTablesInput input) {
        return responsibilityRepo.findAll(input);
    }

    @Override
    public DataTablesOutput<Titles> findTitles(DataTablesInput input) {
        return titlesRepo.findAll(input);
    }

    @Override
    public Titles findTitle(Long title) {
        return titlesRepo.findByTitleCode(title);
    }

    @Override
    public Responsibility findgetResp(Long res) {
        return responsibilityRepo.findByResponsibilityCode(res);
    }

    @Override
    public void deleteTitle(Long title) {
        titlesRepo.deleteById(title);
    }

    @Override
    public void deleteRes(Long res) {
       responsibilityRepo.deleteById(res);
    }

    @Override
    public Page<Titles> pageTitle(Pageable pageable, String term) {
        if (term == null || StringUtils.isBlank(term)) {
            return titlesRepo.findAll(pageable);
        }else{
            return titlesRepo.findByTitleNameContainingIgnoreCase(term, pageable);
        }    }

    @Override
    public Page<StudSubjects> pageSubjects(Pageable pageable, String term) {
        if (term == null || StringUtils.isBlank(term)) {
            return subjectsRepo.findAll(pageable);
        }else{
            return subjectsRepo.findByNameContainingIgnoreCase(term, pageable);
        }
    }

    @Override
    public Page<Responsibility> pageResponsibility(Pageable pageable, String term) {
        if (term == null || StringUtils.isBlank(term)) {
            return responsibilityRepo.findByCategory("T",pageable);
        }else{
            return responsibilityRepo.findByCategoryAndResponsibilityNameContainingIgnoreCase("T",term, pageable);
        }
    }

    @Override
    public Page<Responsibility> pageWorkerResponsibility(Pageable pageable, String term) {
        if (term == null || StringUtils.isBlank(term)) {
            return responsibilityRepo.findByCategory("S",pageable);
        }else{
            return responsibilityRepo.findByCategoryAndResponsibilityNameContainingIgnoreCase("S",term, pageable);
        }
    }

    @Override
    public Page<Teachers> pageTeacher(Pageable pageable, String term) {
        if (term == null || StringUtils.isBlank(term)) {
            return teacherRepo.findAll(pageable);
        }else{
            return teacherRepo.findByNameContainingIgnoreCase(term, pageable);
        }
    }

    @Override
    public void saveTeacher(Teachers teachers) throws BadRequestException {
        if(teachers.getTeacherCode()==null){
            Teachers t1 = teacherRepo.findByIdNo(teachers.getIdNo());
            Teachers t2 = teacherRepo.findByTscNo(teachers.getTscNo());
            Teachers t3 = teacherRepo.findByPhoneNumber(teachers.getPhoneNumber());
            if (t1 != null) {
                throw new BadRequestException("A Teacher with similar ID number exists");
            } else if (t2 != null) {
                throw new BadRequestException("A Teacher with similar TSC number exists");

            } else if (t3 != null) {
                throw new BadRequestException("A Teacher with similar Phone number exists");

            } else {
                teachers.setInSession("inSession");
                teacherRepo.save(teachers);
            }

        }else {

                Teachers teacher = teacherRepo.findByTeacherCode(teachers.getTeacherCode());
                if (teachers.getPhoto() != null) {
                    teacher.setPhoto(teachers.getPhoto());
                }
                teacher.setName(teachers.getName());
                teacher.setAddress(teachers.getAddress());
                teacher.setTitles(teacher.getTitles());
                teacher.setEndDate(teacher.getEndDate());
                teacher.setIdNo(teachers.getIdNo());
                teacher.setIntials(teachers.getIntials());
                teacher.setPhoneNumber(teachers.getPhoneNumber());
                teacher.setTscNo(teachers.getTscNo());
                teacher.setResponsibility(teachers.getResponsibility());
                teacher.setGender(teachers.getGender());
                teacher.setStartDate(teachers.getStartDate());

                teacher.setStatus(teachers.getStatus());
                teacherRepo.save(teacher);

        }
    }

    @Override
    public void saveStaff(Staff staff) throws BadRequestException {
        if(staff.getStaffCode()==null){
            Staff t1 = staffRepo.findByIdNo(staff.getIdNo());
            Staff t2 = staffRepo.findByPhoneNumber(staff.getPhoneNumber());
            if (t1 != null) {
                throw new BadRequestException("A Worker with similar ID number exists");
            } else if (t2 != null) {
                throw new BadRequestException("A Worker with similar Phone number exists");

            }  else {
                staffRepo.save(staff);
            }

        }else{
            Staff s=staffRepo.findByStaffCode(staff.getStaffCode());
            if(s.getPhoto()!=null) {
                s.setPhoto(staff.getPhoto());
            }
            s.setName(staff.getName());
            s.setAddress(staff.getAddress());
            s.setTitles(staff.getTitles());
            s.setEndDate(staff.getEndDate());
            s.setIdNo(staff.getIdNo());
            s.setPhoneNumber(staff.getPhoneNumber());
            s.setResponsibility(staff.getResponsibility());
            s.setGender(staff.getGender());
            s.setStartDate(staff.getStartDate());

            s.setStatus(staff.getStatus());
            staffRepo.save(s);
        }
    }

    @Override
    public Teachers getTeacher(Long teacherId) {
        return teacherRepo.findByTeacherCode(teacherId);
    }

    @Override
    public Forms findForm(Long classId) {
        return formsRepo.findByClassCode(classId);
    }

    @Override
    public List<StudSubjects> findPending() {
        List<StudSubjects> studSubjects=subjectsRepo.findByAssigned("pending");
        return studSubjects;
    }

    @Override
    public void saveTeacherSubject(TeacherSubjects subject) {
        teacherSubjectsRepo.save(subject);
    }

    @Override
    public TeacherSubjects checkFirst(Teachers t, Forms forms, StudSubjects s) {
        return teacherSubjectsRepo.findFirstByTeachersAndFormsAndSubjects(t,forms,s);
    }

    @Override
    public void clearAllPending(){
        List<StudSubjects> studSubjects=subjectsRepo.findByAssigned("pending");
        if(!(studSubjects.isEmpty())) {
            for (StudSubjects s: studSubjects) {
                StudSubjects subjects = subjectsRepo.findBySubjectCode(s.getSubjectCode());
                subjects.setAssigned("assigned");
                subjectsRepo.save(subjects);
            }
        }
    }

    @Override
    public DataTablesOutput<StudSubjects> getAllAssigned(DataTablesInput input) {
        return subjectsRepo.findAll(input, CountrySpecs.dataSubAssigned());
    }

    @Override
    public DataTablesOutput<StudSubjects> getAllUnAssigned(DataTablesInput input) {
        return subjectsRepo.findAll(input,CountrySpecs.dataSubUnAssigned());
    }

    @Override
    public List<StudSubjects> findAssigned() {
        return subjectsRepo.findByAssigned("assigned");
    }

    @Override
    public DataTablesOutput<Teachers> findAllTeachers(DataTablesInput input) {
        return teacherRepo.findAll(input);
    }

    @Override
    public void saveAssigned(Long classId, Long teacher) throws BadRequestException {
        List<StudSubjects> mySubjects=subjectsRepo.findByAssigned("pending");

        if(mySubjects.isEmpty()){
            throw new BadRequestException("No Subjects selected to assign");
        }
        else{
            Teachers teachers=teacherRepo.findByTeacherCode(teacher);
            Forms forms=formsRepo.findByClassCode(classId);
            for(StudSubjects s:mySubjects){
                TeacherSubjects subjects=teacherSubjectsRepo.findFirstByTeachersAndFormsAndSubjects(teachers,forms,s);
                if(subjects!=null){
                    clearAllPending();
                    throw new BadRequestException("Subject "+s.getName()+" is already assigned in this class");
                }else {
                    TeacherSubjects teacherSubjects = new TeacherSubjects();
                    teacherSubjects.setSubjects(s);
                    teacherSubjects.setTeachers(teachers);
                    teacherSubjects.setForms(forms);
                    teacherSubjectsRepo.save(teacherSubjects);
                }
            }

        }
        clearAllPending();
    }

    @Override
    public DataTablesOutput<TeacherSubjects> findAllAssignedSubjects(DataTablesInput input) {
        return teacherSubjectsRepo.findAll(input);
    }

    @Override
    public void transferAssignedSubjects(Long classId, Long tFrom, Long tTo) throws BadRequestException {
        Teachers t=teacherRepo.findByTeacherCode(tFrom);
         Teachers f=teacherRepo.findByTeacherCode(tTo);
        if(classId==null){
            List<TeacherSubjects> subjects=teacherSubjectsRepo.findByTeachers(t);
            if(!(subjects.isEmpty())) {
                for (TeacherSubjects s : subjects) {
                    TeacherSubjects ts = teacherSubjectsRepo.findByTeacherSubjectCode(s.getTeacherSubjectCode());
                    ts.setTeachers(f);
                    teacherSubjectsRepo.save(ts);
                }
            }
                else{
                    throw new BadRequestException("No subjects are assigned to "+t.getName());
                }

        }
        else{
            Forms forms=formsRepo.findByClassCode(classId);
          List<TeacherSubjects> subjects=teacherSubjectsRepo.findByTeachersAndForms(t,forms);
            if(!(subjects.isEmpty())) {
                for (TeacherSubjects s : subjects) {
                    TeacherSubjects ts = teacherSubjectsRepo.findByTeacherSubjectCode(s.getTeacherSubjectCode());
                    ts.setTeachers(f);
                    teacherSubjectsRepo.save(ts);
                }
            }
            else{
                throw new BadRequestException("No subjects are assigned to "+t.getName());
            }
        }
    }

    @Override
    public void removeAllAssignedSubjects(Long teacher) {
        Teachers teachers=teacherRepo.findByTeacherCode(teacher);
        teacherSubjectsRepo.deleteAssocSubjects(teachers);
    }

    @Override
    public void deleteTeacher(Long teacher) {
        teacherRepo.deleteById(teacher);
    }

    @Override
    public Staff getStaff(Long tCode) {
        return staffRepo.findByStaffCode(tCode);
    }

    @Override
    public DataTablesOutput<Staff> findAllWorkers(DataTablesInput input) {
        return staffRepo.findAll(input);
    }

    @Override
    public void deleteWorker(Long staff) {
        staffRepo.deleteById(staff);
    }

    @Override
    public DataTablesOutput<Staff> findRegisterWorkers(DataTablesInput input) {
        return staffRepo.findAll(input,CountrySpecs.dataStaffActive());
    }

    @Override
    public DataTablesOutput<Teachers> findRegisterTeachers(DataTablesInput input) {
        return teacherRepo.findAll(input,CountrySpecs.dataTeachersActive());
    }

    @Override
    public DataTablesOutput<Staff> nameRegisterStaff(DataTablesInput input, String name) {
        return staffRepo.findAll(input,CountrySpecs.dataStaffRegisterName(name));
    }

    @Override
    public DataTablesOutput<Teachers> nameRegisterTeachers(DataTablesInput input, String name) {
        return teacherRepo.findAll(input,CountrySpecs.dataRegisterName(name));
    }

    @Override
    public DataTablesOutput<ClassSubjects> getClassSubjects(DataTablesInput input) {
        return classSubjectsRepo.findAll(input);
    }

    @Override
    public void deleteClassSubject(Long code) {
        classSubjectsRepo.deleteById(code);
    }

    @Override
    public void deleteSubjectGroup(Long code) {
        subjectGroupRepo.deleteById(code);
    }

    @Override
    public DataTablesOutput<SubjectGroups> getSubjectGroups(DataTablesInput input) {
        return subjectGroupRepo.findAll(input);
    }

    @Override
    public void saveTermDates(TermDates termDates) {
        if(termDates.getTermDateCode()==null){
            if(termDates.getCurrentTerm()!=null){
               termDates.setCurrentTerm("Y");
            }
            else{
              termDates.setCurrentTerm("N");
            }
            termDatesRepo.save(termDates);
        }
        else{
            TermDates term=termDatesRepo.findByTermDateCode(termDates.getTermDateCode());
            term.setStartDate(termDates.getStartDate());
            term.setEndDate(termDates.getEndDate());
            term.setTerm(termDates.getTerm());
            if(termDates.getCurrentTerm()!=null){
                term.setCurrentTerm("Y");
            }
            else{
                term.setCurrentTerm("N");
            }
            termDatesRepo.save(term);
        }
    }

    @Override
    public DataTablesOutput<TermDates> getTermDatesTbl(DataTablesInput input) {
        return termDatesRepo.findAll(input);
    }

    @Override
    public void deleteTermDates(Long code) {
        termDatesRepo.deleteById(code);
    }
}
