package com.high.school.Institution.service.impl;

import com.high.school.Institution.model.AcademicOrg;
import com.high.school.Institution.repo.SchoolRepo;
import com.high.school.Institution.service.SchoolInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService implements SchoolInterface {

    @Autowired
    SchoolRepo schoolRepo;

    @Override
    public List<AcademicOrg> findSchool() {
        return schoolRepo.findAll();
    }


    @Override
    public AcademicOrg saveSchool(AcademicOrg school) {
        schoolRepo.save(school);
        return schoolRepo.getSchool();
    }

    @Override
    public AcademicOrg getSchool() {
        return schoolRepo.getSchool();
    }
}
