package com.high.school.api;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UniversityService {
    @Autowired
    UniversityRepo studentRepo;

    public Page<University> pageStudent(Pageable pageable, String term) {
        if (term == null || StringUtils.isBlank(term)) {
            return studentRepo.findAll(pageable);
        } else {
            return studentRepo.findByRegNoContainingIgnoreCase(term, pageable);
        }
    }

    public void postStudent(University student) {
        studentRepo.save(student);
    }

    public University getStudent(Long studCode) {
        return studentRepo.findByStId(studCode);
    }
}
