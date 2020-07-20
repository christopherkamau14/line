package com.high.school.academics.service.Impl;

import com.high.school.Locale.model.CountrySpecs;
import com.high.school.academics.model.*;
import com.high.school.academics.repo.*;
import com.high.school.academics.service.StaffInterface;
import com.high.school.exceptions.BadRequestException;
import com.high.school.students.model.LeaveOuts;
import com.high.school.students.model.Student;
import com.high.school.users.model.User;
import com.high.school.users.model.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class StaffService implements StaffInterface {

    @Autowired
    TeacherRepo teacherRepo;

    @Autowired
    TeacherLeavesRepo teacherLeavesRepo;

    @Autowired
    UserUtils userUtils;

    @Autowired
    TeacherRegisterRepo teacherRegisterRepo;

    @Autowired
    StaffRegisterRepo staffRegisterRepo;

    @Autowired
    StaffRepo staffRepo;

    @Override
    public DataTablesOutput<Teachers> findLeaveOuts(DataTablesInput input) {
        return teacherRepo.findAll(input, CountrySpecs.dataTeachersUnAssigned());
    }

    @Override
    public DataTablesOutput<Teachers> findSchoolTeachers(DataTablesInput input) {
        return teacherRepo.findAll(input,CountrySpecs.dataTeachersAssigned());
    }

    @Override
    public DataTablesOutput<TeacherLeaves> onLeaveTeachers(DataTablesInput input) {

        return teacherLeavesRepo.findAll(input);
    }

    @Override
    public DataTablesOutput<TeacherLeaves> pendingLeaveTeachers(DataTablesInput input) {

        return teacherLeavesRepo.findAll(input,CountrySpecs.dataLeaveTeacherLeaves());
    }

    @Override
    public DataTablesOutput<TeacherLeaves> returnedLeaveTeachers(DataTablesInput input) {

        return teacherLeavesRepo.findAll(input,CountrySpecs.dataTeacherLeaveOffs());
    }

    @Override
    public DataTablesOutput<TeacherLeaves> nameLeaveTeachers(DataTablesInput input, String name) {

        return teacherLeavesRepo.findAll(input,CountrySpecs.dataTeacherName(name));
    }

    @Override
    public void grantLeaveOut(Date left, Date returnDate, String reason) throws BadRequestException {
        List<Teachers> teachers=teacherRepo.findByInSession("pending");
        for(Teachers t:teachers) {
            TeacherLeaves leaveOut= teacherLeavesRepo.findByTeachersAndDateReturnedIsNull(t);
            if(leaveOut!=null){
                clearAllPending();
                throw new BadRequestException("Teacher " +t.getName()+" is already on leave");
            }
            else{
                int p=left.compareTo(returnDate);
                if(p>0){
                    clearAllPending();
                    throw new BadRequestException("Date of Return cannot be before leave date");
                }else {
                    TeacherLeaves leaveOuts= new TeacherLeaves();
                    User user = userUtils.getCurrentUser();
                    leaveOuts.setAuthorisedBy(user);
                    leaveOuts.setGrantedBy(user);
                    leaveOuts.setDateExpected(returnDate);
                    leaveOuts.setReason(reason);
                    leaveOuts.setDateLeft(left);
                    leaveOuts.setTeachers(t);
                    teacherLeavesRepo.save(leaveOuts);
                }
            }
        }
        clearAllPending();
    }

    @Override
    public void returnLeaveOut(Date returnDate) throws BadRequestException {
        List<Teachers> teachers=teacherRepo.findByInSession("pending");
        for(Teachers s:teachers) {
            TeacherLeaves lea = teacherLeavesRepo.findByTeachersAndDateReturnedIsNull(s);
            if (lea != null) {
                TeacherLeaves leaveOuts = teacherLeavesRepo.findByTeachersAndDateReturnedIsNull(s);
                int p = leaveOuts.getDateLeft().compareTo(returnDate);
                if (p > 0) {
                    clearAllPending();
                    throw new BadRequestException("Return Date cannot occur before leave date");
                } else {
                    leaveOuts.setDateReturned(returnDate);
                    teacherLeavesRepo.save(leaveOuts);
                }
            }
            else{
                clearAllPending();
                throw new BadRequestException("Teacher "+s.getName()+" was not on leave");

            }
        }
        clearAllPending();

    }

    @Override
    public void clearAllPending() {
        List<Teachers> teachers=teacherRepo.findByInSession("pending");
        if(!(teachers.isEmpty())) {
            for (Teachers teacher : teachers) {
                Teachers t = teacherRepo.findByTeacherCode(teacher.getTeacherCode());
                t.setInSession("inSession");
                teacherRepo.save(t);
            }
        }
    }

    @Override
    public void recordStaffTimeOut(Long regCode) throws BadRequestException {
        Staff staff=staffRepo.findByStaffCode(regCode);
        LocalDate today = LocalDate.now();
        Date date = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
        StaffRegister s=staffRegisterRepo.findByStaffAndRegisterDateAndTimeInNotNull(staff,date);
        if(s==null){
            throw new BadRequestException("Staff time in was never registered today");
        }
        else {
            if (s.getTimeOut() != null) {
                throw new BadRequestException("Already Signed out for today");
            }
            else {
                StaffRegister staffRegister = staffRegisterRepo.findByStaffRegisterCode(s.getStaffRegisterCode());
                LocalTime localTime = LocalTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH.mm.ss");
                String timeString = localTime.format(formatter);

                staffRegister.setTimeOut(timeString);
                staffRegisterRepo.save(staffRegister);
            }
        }

    }

    @Override
    public void recordTimeOut(Long regCode) throws BadRequestException {
        Teachers teachers=teacherRepo.findByTeacherCode(regCode);
        LocalDate today = LocalDate.now();
        Date date = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
        TeacherRegister t=teacherRegisterRepo.findByTeachersAndRegisterDateAndTimeInNotNull(teachers,date);
        if(t==null){
            throw new BadRequestException("Teacher time in was never registered today");
        }
        else {
            if (t.getTimeOut() != null) {
               throw new BadRequestException("Already Signed out for today");
            }
            else {
                TeacherRegister staffRegister = teacherRegisterRepo.findByTeacherRegisterCode(t.getTeacherRegisterCode());
                LocalTime localTime = LocalTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH.mm.ss");
                String timeString = localTime.format(formatter);

                staffRegister.setTimeOut(timeString);
                teacherRegisterRepo.save(staffRegister);
            }
        }
    }

    @Override
    public void recordTimeIn(Long regCode) throws BadRequestException {
        Teachers teachers=teacherRepo.findByTeacherCode(regCode);
        LocalDate today = LocalDate.now();
        Date date = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
        TeacherRegister t=teacherRegisterRepo.findByTeachersAndRegisterDate(teachers,date);
        if(t!=null){
         throw new BadRequestException("Teacher is already registered today");
        }
        else {
            TeacherRegister teacherRegister=new TeacherRegister();
            LocalTime localTime = LocalTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH.mm.ss");
            String timeString = localTime.format(formatter);
            teacherRegister.setRegisterDate(date);
            teacherRegister.setTeachers(teachers);
            teacherRegister.setTimeIn(timeString);
            teacherRegisterRepo.save(teacherRegister);
        }
    }

    @Override
    public void recordStaffTimeIn(Long regCode) throws BadRequestException {
        Staff staff=staffRepo.findByStaffCode(regCode);
        LocalDate today = LocalDate.now();
        Date date = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());

        StaffRegister s=staffRegisterRepo.findByStaffAndRegisterDate(staff,date);
        if(s!=null){
            throw new BadRequestException("Staff is already registered today");
        }
        else {
            StaffRegister staffRegister=new StaffRegister();
            LocalTime localTime = LocalTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH.mm.ss");
            String timeString = localTime.format(formatter);
            staffRegister.setRegisterDate(date);
            staffRegister.setStaff(staff);
            staffRegister.setTimeIn(timeString);
            staffRegisterRepo.save(staffRegister);
        }
    }
}
