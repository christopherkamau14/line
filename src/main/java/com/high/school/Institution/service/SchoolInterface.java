package com.high.school.Institution.service;

import com.high.school.Institution.model.AcademicOrg;

import java.util.List;

public interface SchoolInterface {
    List<AcademicOrg> findSchool();



    AcademicOrg saveSchool(AcademicOrg school);

    AcademicOrg getSchool();
}
