package com.high.school.Institution.repo;

import com.high.school.Institution.model.AcademicOrg;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SchoolRepo extends JpaRepository<AcademicOrg,Long>, PagingAndSortingRepository<AcademicOrg,Long>, DataTablesRepository<AcademicOrg,Long> {
@Query( value="SELECT * FROM academic_org",nativeQuery=true)
    AcademicOrg getSchool();
}
