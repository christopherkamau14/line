package com.high.school.Locale.model;

import com.high.school.academics.model.Staff;
import com.high.school.academics.model.StudSubjects;
import com.high.school.academics.model.TeacherLeaves;
import com.high.school.academics.model.Teachers;
import com.high.school.examination.model.ExamRecording;
import com.high.school.students.model.Forms;
import com.high.school.students.model.LeaveOuts;
import com.high.school.students.model.Student;
import com.high.school.students.model.StudentDocs;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class CountrySpecs{
    public static Specification<County> joinTest(Country country) {
        return (Specification<County>) (root, query, cb) -> {
            Subquery<County> subquery = query.subquery(County.class);
            Root<County> subRoot = subquery.from(County.class);
            return cb.equal(root.get("country").get("couCode"), country.getCouCode().toString());
        };
    }

    public static Specification<County> joinTests(String name) {
        return (Specification<County>) (root, query, cb) -> {
            Subquery<County> subquery = query.subquery(County.class);
            Root<County> subRoot = subquery.from(County.class);
            return cb.equal(root.get("countyName"), name);
        };
    }
    public static Specification<SubCounty> dataSub(County county) {
        return (Specification<SubCounty>) (root, query, cb) -> {
            Subquery<SubCounty> subquery = query.subquery(SubCounty.class);
            Root<SubCounty> subRoot = subquery.from(SubCounty.class);
            return cb.equal(root.get("county").get("countyCode"), county.getCountyCode().toString());
        };
    }
    public static Specification<Location> dataLoc(SubCounty subCounty) {
        return (Specification<Location>) (root, query, cb) -> {
            Subquery<Location> subquery = query.subquery(Location.class);
            Root<Location> subRoot = subquery.from(Location.class);
            return cb.equal(root.get("subCounty").get("subCode"), subCounty.getSubCode().toString());
        };
    }
    public static Specification<SubLocation> dataSubLoc(Location location) {
        return (Specification<SubLocation>) (root, query, cb) -> {
            Subquery<SubLocation> subquery = query.subquery(SubLocation.class);
            Root<SubLocation> subRoot = subquery.from(SubLocation.class);
            return cb.equal(root.get("location").get("louCode"), location.getLouCode().toString());
        };
    }
    public static Specification<Village> dataVil(SubLocation subLocation) {
        return (Specification<Village>) (root, query, cb) -> {
            Subquery<Village> subquery = query.subquery(Village.class);
            Root<Village> subRoot = subquery.from(Village.class);
            return cb.equal(root.get("subLocation").get("subLouCode"), subLocation.getSubLouCode().toString());
        };
    }

    public static Specification<StudentDocs> dataStud(Student student) {
        return (Specification<StudentDocs>) (root, query, cb) -> {
            Subquery<StudentDocs> subquery = query.subquery(StudentDocs.class);
            Root<StudentDocs> subRoot = subquery.from(StudentDocs.class);
            return cb.equal(root.get("student").get("stId"), student.getStId().toString());
        };
    }
    public static Specification<LeaveOuts> dataLeaves(Long classId) {
        return (Specification<LeaveOuts>) (root, query, cb) -> {
            Subquery<LeaveOuts> subquery = query.subquery(LeaveOuts.class);
            Root<LeaveOuts> subRoot = subquery.from(LeaveOuts.class);
            return cb.equal(root.get("forms").get("classCode"), classId.toString());
        };
    }
    public static Specification<Student> dataName(String name) {
        return (Specification<Student>) (root, query, cb) -> {
            Subquery<Student> subquery = query.subquery(Student.class);
            Root<Student> subRoot = subquery.from(Student.class);
            return cb.like(root.get("name"), name);
        };
    }

    public static Specification<TeacherLeaves> dataTeacherName(String name) {
        return (Specification<TeacherLeaves>) (root, query, cb) -> {
            Subquery<TeacherLeaves> subquery = query.subquery(TeacherLeaves.class);
            Root<TeacherLeaves> subRoot = subquery.from(TeacherLeaves.class);
            return cb.like(root.get("teachers").get("name"), "%"+name+"%");
        };
    }

    public static Specification<Teachers> dataRegisterName(String name) {
        return (Specification<Teachers>) (root, query, cb) -> {
            Subquery<Teachers> subquery = query.subquery(Teachers.class);
            Root<Teachers> subRoot = subquery.from(Teachers.class);
            return cb.like(root.get("name"), "%"+name+"%");
        };
    }

    public static Specification<Staff> dataStaffRegisterName(String name) {
        return (Specification<Staff>) (root, query, cb) -> {
            Subquery<Staff> subquery = query.subquery(Staff.class);
            Root<Staff> subRoot = subquery.from(Staff.class);
            return cb.like(root.get("name"), "%"+name+"%");
        };
    }

    public static Specification<Student> studSession() {
        return (Specification<Student>) (root, query, cb) -> {
            Subquery<Student> subquery = query.subquery(Student.class);
            Root<Student> subRoot = subquery.from(Student.class);
            return cb.equal(root.get("inSession"), "inSession");
        };
    }
    public static Specification<Student> dataForm(String form) {
        return (Specification<Student>) (root, query, cb) -> {
            Subquery<Student> subquery = query.subquery(Student.class);
            Root<Student> subRoot = subquery.from(Student.class);
            return cb.equal(root.get("forms").get("classNo"), form);
        };
    }

    public static Specification<StudSubjects> dataSubAssigned() {
        return (Specification<StudSubjects>) (root, query, cb) -> {
            Subquery<StudSubjects> subquery = query.subquery(StudSubjects.class);
            Root<StudSubjects> subRoot = subquery.from(StudSubjects.class);
            return cb.equal(root.get("assigned"), "assigned");
        };
    }
    public static Specification<StudSubjects> dataSubUnAssigned() {
        return (Specification<StudSubjects>) (root, query, cb) -> {
            Subquery<StudSubjects> subquery = query.subquery(StudSubjects.class);
            Root<StudSubjects> subRoot = subquery.from(StudSubjects.class);
            return cb.equal(root.get("assigned"), "pending");
        };
    }

    public static Specification<Teachers> dataTeachersAssigned() {
        return (Specification<Teachers>) (root, query, cb) -> {
            Subquery<Teachers> subquery = query.subquery(Teachers.class);
            Root<Teachers> subRoot = subquery.from(Teachers.class);
            return cb.equal(root.get("inSession"), "inSession");
        };
    }

    public static Specification<Teachers> dataTeachersActive() {
        return (Specification<Teachers>) (root, query, cb) -> {
            Subquery<Teachers> subquery = query.subquery(Teachers.class);
            Root<Teachers> subRoot = subquery.from(Teachers.class);
            return cb.equal(root.get("status"), "Active");
        };
    }

    public static Specification<Forms> dataMyForm(String search) {
        return (Specification<Forms>) (root, query, cb) -> {
            Subquery<Forms> subquery = query.subquery(Forms.class);
            Root<Forms> subRoot = subquery.from(Forms.class);
            return cb.like(root.get("classNo"),"%"+search+"%");
        };
    }

    public static Specification<Staff> dataStaffActive() {
        return (Specification<Staff>) (root, query, cb) -> {
            Subquery<Staff> subquery = query.subquery(Staff.class);
            Root<Staff> subRoot = subquery.from(Staff.class);
            return cb.equal(root.get("status"), "Active");
        };
    }

    public static Specification<Teachers> dataTeachersUnAssigned() {
        return (Specification<Teachers>) (root, query, cb) -> {
            Subquery<Teachers> subquery = query.subquery(Teachers.class);
            Root<Teachers> subRoot = subquery.from(Teachers.class);
            return cb.equal(root.get("inSession"), "pending");
        };
    }

    public static Specification<LeaveOuts> dataLikeLeaveOuts(String name) {
        return (Specification<LeaveOuts>) (root, query, cb) -> {
            Subquery<LeaveOuts> subquery = query.subquery(LeaveOuts.class);
            Root<LeaveOuts> subRoot = subquery.from(LeaveOuts.class);
            return cb.like(root.get("student").get("name"),"%"+name+"%");
        };
    }

    public static Specification<LeaveOuts> dataLeaveOuts() {
        return (Specification<LeaveOuts>) (root, query, cb) -> {
            Subquery<LeaveOuts> subquery = query.subquery(LeaveOuts.class);
            Root<LeaveOuts> subRoot = subquery.from(LeaveOuts.class);
            return cb.isNotNull(root.get("dateReturned"));
        };
    }

    public static Specification<LeaveOuts> dataLeaveOffs() {
        return (Specification<LeaveOuts>) (root, query, cb) -> {
            Subquery<LeaveOuts> subquery = query.subquery(LeaveOuts.class);
            Root<LeaveOuts> subRoot = subquery.from(LeaveOuts.class);
            return cb.isNull(root.get("dateReturned") );
        };
    }

    public static Specification<TeacherLeaves> dataTeacherLeaveOffs() {
        return (Specification<TeacherLeaves>) (root, query, cb) -> {
            Subquery<TeacherLeaves> subquery = query.subquery(TeacherLeaves.class);
            Root<TeacherLeaves> subRoot = subquery.from(TeacherLeaves.class);
            return cb.isNotNull(root.get("dateReturned"));
        };
    }


    public static Specification<TeacherLeaves> dataLeaveTeacherLeaves() {
        return (Specification<TeacherLeaves>) (root, query, cb) -> {
            Subquery<TeacherLeaves> subquery = query.subquery(TeacherLeaves.class);
            Root<TeacherLeaves> subRoot = subquery.from(TeacherLeaves.class);
            return cb.isNull(root.get("dateReturned") );
        };
    }

    public static Specification<Student> dataClass(Long classId) {
        return (Specification<Student>) (root, query, cb) -> {
            Subquery<Student> subquery = query.subquery(Student.class);
            Root<Student> subRoot = subquery.from(Student.class);
            return cb.equal(root.get("forms").get("classCode"), classId.toString());
        };
    }
    public static Specification<Student> dataSession() {
        return (Specification<Student>) (root, query, cb) -> {
            Subquery<Student> subquery = query.subquery(Student.class);
            Root<Student> subRoot = subquery.from(Student.class);
            return cb.equal(root.get("inSession"),"offSession");
        };
    }
    public static Specification<Student> datapenSession() {
        return (Specification<Student>) (root, query, cb) -> {
            Subquery<Student> subquery = query.subquery(Student.class);
            Root<Student> subRoot = subquery.from(Student.class);
            return cb.equal(root.get("inSession"),"pending");
        };
    }
    public static Specification<Student> dataMobile(String mobile) {
        return (Specification<Student>) (root, query, cb) -> {
            Subquery<Student> subquery = query.subquery(Student.class);
            Root<Student> subRoot = subquery.from(Student.class);
            return cb.equal(root.get("studentContact"), mobile);
        };
    }
    public static Specification<Student> dataAdmNo(String admNo) {
        return (Specification<Student>) (root, query, cb) -> {
            Subquery<Student> subquery = query.subquery(Student.class);
            Root<Student> subRoot = subquery.from(Student.class);
            return cb.equal(root.get("admNo"), admNo);
        };
    }

    public static Specification<ExamRecording> dataExamRec(Long code) {
        return (Specification<ExamRecording>) (root, query, cb) -> {
            Subquery<ExamRecording> subquery = query.subquery(ExamRecording.class);
            Root<ExamRecording> subRoot = subquery.from(ExamRecording.class);
            return cb.equal(root.get("examRegister").get("examCode"), code.toString());
        };
    }
    public static Specification<ExamRecording> dataExamTea(Long code) {
        return (Specification<ExamRecording>) (root, query, cb) -> {
            Subquery<ExamRecording> subquery = query.subquery(ExamRecording.class);
            Root<ExamRecording> subRoot = subquery.from(ExamRecording.class);
            return cb.equal(root.get("teachers").get("teacherCode"), code.toString());
        };
    }

    public static Specification<ExamRecording> dataExamStud(Long code) {
        return (Specification<ExamRecording>) (root, query, cb) -> {
            Subquery<ExamRecording> subquery = query.subquery(ExamRecording.class);
            Root<ExamRecording> subRoot = subquery.from(ExamRecording.class);
            return cb.equal(root.get("student").get("stId"), code.toString());
        };
    }
    public static Specification<ExamRecording> dataExamClass(Long code) {
        return (Specification<ExamRecording>) (root, query, cb) -> {
            Subquery<ExamRecording> subquery = query.subquery(ExamRecording.class);
            Root<ExamRecording> subRoot = subquery.from(ExamRecording.class);
            return cb.equal(root.get("forms").get("classCode"), code.toString());
        };
    }
    public static Specification<ExamRecording> dataExamSubject(Long code) {
        return (Specification<ExamRecording>) (root, query, cb) -> {
            Subquery<ExamRecording> subquery = query.subquery(ExamRecording.class);
            Root<ExamRecording> subRoot = subquery.from(ExamRecording.class);
            return cb.equal(root.get("teacherSubjects").get("teacherSubjectCode"), code.toString());
        };
    }
}
