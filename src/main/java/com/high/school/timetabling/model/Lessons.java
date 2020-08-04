package com.high.school.timetabling.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="lessons_setups")
public class Lessons {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="lesson_id")
    private Long lessonId;

    @Column(name="lesson_code")
    private String lessonCode;

    @Column(name="lesson_name")
    private String lessonName;

    @Column(name="lesson_start")
    private Date lessonStart;

    @Column(name="lesson_end")
    private Date lessonEnd;

    @Column(name="lesson_auto")
    private String autoLesson;

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public String getLessonCode() {
        return lessonCode;
    }

    public void setLessonCode(String lessonCode) {
        this.lessonCode = lessonCode;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public Date getLessonStart() {
        return lessonStart;
    }

    public void setLessonStart(Date lessonStart) {
        this.lessonStart = lessonStart;
    }

    public Date getLessonEnd() {
        return lessonEnd;
    }

    public void setLessonEnd(Date lessonEnd) {
        this.lessonEnd = lessonEnd;
    }

    public String getAutoLesson() {
        return autoLesson;
    }

    public void setAutoLesson(String autoLesson) {
        this.autoLesson = autoLesson;
    }
}
