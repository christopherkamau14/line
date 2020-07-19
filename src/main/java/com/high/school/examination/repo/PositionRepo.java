package com.high.school.examination.repo;

import com.high.school.examination.model.ExamRegister;
import com.high.school.examination.model.PositioningBean;
import com.high.school.students.model.Forms;
import com.high.school.students.model.Student;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PositionRepo extends JpaRepository<PositioningBean,Long>, DataTablesRepository<PositioningBean,Long> {
    PositioningBean findByExamRegisterAndFormsAndStudent(ExamRegister exam, Forms forms, Student student);

    List<PositioningBean> findByExamRegisterAndFormsAndStudent_AdmNo(ExamRegister exam, Forms forms, String admNo);

    List<PositioningBean> findByExamRegister(ExamRegister exam);
}
