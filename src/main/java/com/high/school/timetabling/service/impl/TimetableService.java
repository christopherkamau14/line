package com.high.school.timetabling.service.impl;

import com.high.school.academics.model.ClassSubjects;
import com.high.school.academics.model.StudSubjects;
import com.high.school.academics.model.TeacherSubjects;
import com.high.school.academics.repo.ClassSubjectsRepo;
import com.high.school.academics.repo.SubjectsRepo;
import com.high.school.academics.repo.TeacherSubjectsRepo;
import com.high.school.exceptions.BadRequestException;
import com.high.school.students.model.Forms;
import com.high.school.timetabling.model.Allocations;
import com.high.school.timetabling.model.Days;
import com.high.school.timetabling.model.Lessons;
import com.high.school.timetabling.repo.AllocationRepo;
import com.high.school.timetabling.repo.DaysRepo;
import com.high.school.timetabling.repo.LessonRepo;
import com.high.school.timetabling.service.TimetableInterface;
import com.high.school.users.model.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimetableService implements TimetableInterface {

    @Autowired
    DaysRepo daysRepo;

    @Autowired
    UserUtils userUtils;

    @Autowired
    LessonRepo lessonRepo;

    @Autowired
    SubjectsRepo subjectsRepo;

    @Autowired
    TeacherSubjectsRepo teacherSubjectsRepo;

    @Autowired
    AllocationRepo allocationRepo;

    @Autowired
    ClassSubjectsRepo csrepo;

    @Override
    public void saveDays(Days days) throws BadRequestException {
        if(days.getDayCode()==null){

                daysRepo.save(days);
            }

        else {

                Days days1 = daysRepo.findByDayCode(days.getDayCode());

                days1.setName(days.getName());
                daysRepo.save(days1);


        }
    }

    @Override
    public DataTablesOutput<Days> getDaysTable(DataTablesInput input) {
        return daysRepo.findAll(input);
    }

    @Override
    public void deleteDays(Long code) {
        daysRepo.deleteById(code);
    }

    @Override
    public void saveLesson(Lessons lessons) throws BadRequestException {
        String auto="";
        auto=lessons.getAutoLesson()==null?"N":"Y";
        if(lessons.getLessonId()==null) {
           lessons.setAutoLesson(auto);
                lessonRepo.save(lessons);
        }
           else{
               Lessons le=lessonRepo.findByLessonId(lessons.getLessonId());
               le.setAutoLesson(auto);
               le.setLessonStart(lessons.getLessonStart());
               le.setLessonEnd(lessons.getLessonEnd());
               le.setLessonName(lessons.getLessonName());
               lessonRepo.save(le);
           }

        }


    @Override
    public void deleteLessons(Long code) {
        lessonRepo.deleteById(code);
    }

    @Override
    public DataTablesOutput<Lessons> getLessonsTable(DataTablesInput input) {
        return lessonRepo.findAll(input);
    }

    @Override
    public Page<StudSubjects> pageSubject(Pageable pageable, String term) {
        if (term == null || StringUtils.isBlank(term)) {
            return subjectsRepo.findAll(pageable);
        } else {
            return subjectsRepo.findByNameContainingIgnoreCase(term, pageable);
        }
    }

    @Override
    public Page<Days> pageDays(Pageable pageable, String term) {
        if (term == null || StringUtils.isBlank(term)) {
            return daysRepo.findAll(pageable);
        } else {
            return daysRepo.findByNameContainingIgnoreCase(term, pageable);
        }       }

    @Override
    public Page<Lessons> pageLessons(Pageable pageable, String term) {
        if (term == null || StringUtils.isBlank(term)) {
            return lessonRepo.findByLessonNameLikeIgnoreCase("%lesson%",pageable);
        } else {
            return lessonRepo.findByLessonNameLikeIgnoreCaseAndLessonNameContainingIgnoreCase("%lesson%",term, pageable);
        }
    }

    @Override
    public TeacherSubjects findTeacher(Forms className, StudSubjects subject) {
        return teacherSubjectsRepo.findByFormsAndSubjects(className,subject);
    }

    @Override
    public void saveAllocation(Allocations allocations) throws BadRequestException {
        if(allocations.getAllocationCode()==null){
            Allocations a=allocationRepo.findByFormsAndLessonsAndDays(allocations.getForms(),allocations.getLessons(),allocations.getDays());
            if(a==null){
                ClassSubjects classSubjects=csrepo.findFirstByFormsAndSubjects(allocations.getForms(),allocations.getSubjects());
                if(classSubjects==null){
                    throw new BadRequestException("Subject not assigned to class");
                }
                else {
                    String stroked=classSubjects.getStrokedSubject().equalsIgnoreCase("Y")?"Y":"N";
                    allocations.setStroked(stroked);
                    allocations.setClassName(allocations.getForms().getAbbr());
                    allocations.setDayName(allocations.getDays().getName());
                    allocations.setSubjectName(allocations.getSubjects().getName());
                    allocations.setTeacherName(allocations.getTeachers().getName());
                    allocations.setCreatedBy(userUtils.getCurrentUser());
                    allocationRepo.save(allocations);
                }

            }else {
                throw new BadRequestException("Already allocated");
            }

        }
        else{
            Allocations al=allocationRepo.findByAllocationCode(allocations.getAllocationCode());
            al.setDays(allocations.getDays());
            al.setForms(allocations.getForms());
            al.setLessons(allocations.getLessons());
            al.setSubjects(allocations.getSubjects());
            al.setTeachers(allocations.getTeachers());
            al.setTerm(allocations.getTerm());
            al.setClassName(allocations.getForms().getAbbr());
            al.setDayName(allocations.getDays().getName());
            al.setSubjectName(allocations.getSubjects().getName());
            al.setTeacherName(allocations.getTeachers().getName());
            allocationRepo.save(al);
        }
    }

    @Override
    public DataTablesOutput<Allocations> getAllocTable(DataTablesInput input) {
        return allocationRepo.findAll(input);
    }

    @Override
    public void deleteAllocation(Long code) {
        allocationRepo.deleteById(code);
    }


}
