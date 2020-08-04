package com.high.school.timetabling.service;

import com.high.school.academics.model.StudSubjects;
import com.high.school.academics.model.TeacherSubjects;
import com.high.school.exceptions.BadRequestException;
import com.high.school.students.model.Forms;
import com.high.school.timetabling.model.Allocations;
import com.high.school.timetabling.model.Days;
import com.high.school.timetabling.model.Lessons;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

public interface TimetableInterface {
    void saveDays(Days days) throws BadRequestException;

    DataTablesOutput<Days> getDaysTable(DataTablesInput input);

    void deleteDays(Long code);

    void saveLesson(Lessons lessons) throws BadRequestException;

    void deleteLessons(Long code);

    DataTablesOutput<Lessons> getLessonsTable(DataTablesInput input);

    Page<StudSubjects> pageSubject(Pageable pageable, String term);

    Page<Days> pageDays(Pageable pageable, String term);

    Page<Lessons> pageLessons(Pageable pageable, String term);

    TeacherSubjects findTeacher(Forms className, StudSubjects subject);

    void saveAllocation(Allocations allocations) throws BadRequestException;

    DataTablesOutput<Allocations> getAllocTable(DataTablesInput input);

    void deleteAllocation(Long code);
}
