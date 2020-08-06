package com.high.school.timetabling.repo;

import com.high.school.timetabling.model.Lessons;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface LessonRepo extends JpaRepository<Lessons,Long>, DataTablesRepository<Lessons,Long> {

    Lessons findByLessonId(Long lessonId);

    Page<Lessons> findByLessonNameLikeIgnoreCase(String s, Pageable pageable);

    Page<Lessons> findByLessonNameLikeIgnoreCaseAndLessonNameContainingIgnoreCase(String s, String term, Pageable pageable);
}
