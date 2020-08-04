package com.high.school.timetabling.controller;

import com.high.school.academics.model.StudSubjects;
import com.high.school.academics.model.TeacherSubjects;
import com.high.school.exceptions.BadRequestException;
import com.high.school.students.model.Forms;
import com.high.school.timetabling.model.Allocations;
import com.high.school.timetabling.model.Days;
import com.high.school.timetabling.model.Lessons;
import com.high.school.timetabling.service.TimetableInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/protected/timetable")
public class TimetableController {

    @Autowired
    TimetableInterface timetableInterface;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping("/days")
    public String days(){
        return "days";
    }

    @GetMapping("/lessons")
    public String lessons(){
        return "lessons";
    }

    @GetMapping("/allocations")
    public String allocations(){
        return "allocations";
    }


    @PostMapping("saveDay")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveDays(Days days) throws BadRequestException {
     timetableInterface.saveDays(days);
    }
    @PostMapping("saveLesson")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveDays(Lessons lessons) throws BadRequestException {
        timetableInterface.saveLesson(lessons);
    }

    @PostMapping("/saveAllocation")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveAllocation(Allocations allocations) throws BadRequestException {
        timetableInterface.saveAllocation(allocations);
    }

    @GetMapping("deleteDays/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDays(@PathVariable Long code){
        timetableInterface.deleteDays(code);
    }

    @GetMapping("deleteLessons/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLessons(@PathVariable Long code){
        timetableInterface.deleteLessons(code);
    }

    @GetMapping("deleteAllocation/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllocation(@PathVariable Long code){
        timetableInterface.deleteAllocation(code);
    }

    @GetMapping("/getDaysTable")
    @ResponseBody
    public DataTablesOutput<Days> getDaysTable(DataTablesInput input){
       return timetableInterface.getDaysTable(input);

    }
    @GetMapping("/getLessonTable")
    @ResponseBody
    public DataTablesOutput<Lessons> getLessonsTable(DataTablesInput input){
        return timetableInterface.getLessonsTable(input);

    }
    @GetMapping("/getAllocationTbl")
    @ResponseBody
    public DataTablesOutput<Allocations> getAllocTable(DataTablesInput input){
        return timetableInterface.getAllocTable(input);

    }
    @GetMapping("/pageSubject")
    @ResponseBody
    Page<StudSubjects> pageSubject(@RequestParam(required = false) String term, Pageable pageable) {
        return timetableInterface.pageSubject(pageable, term);
    }
    @GetMapping("/pageDays")
    @ResponseBody
    Page<Days> pageDays(@RequestParam(required = false) String term, Pageable pageable) {
        return timetableInterface.pageDays(pageable, term);
    }
    @GetMapping("/pageLessons")
    @ResponseBody
    Page<Lessons> pageLessons(@RequestParam(required = false) String term, Pageable pageable) {
        return timetableInterface.pageLessons(pageable, term);
    }
    @GetMapping("/findTeacher")
    @ResponseBody
    TeacherSubjects findTeacher(@RequestParam Forms className, @RequestParam StudSubjects subject) {
        return timetableInterface.findTeacher(className, subject);
    }
}
