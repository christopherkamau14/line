package com.high.school.examination.service;

import com.high.school.examination.model.ExamMergeBean;
import com.high.school.exceptions.BadRequestException;

public interface MergeInterface {
    void mergeExam(ExamMergeBean examMergeBean) throws BadRequestException;

    void mergeThreeExams(ExamMergeBean examMergeBean) throws BadRequestException;

    void mergeFourExams(ExamMergeBean examMergeBean) throws BadRequestException;

    void mergeFiveExams(ExamMergeBean examMergeBean) throws BadRequestException;

    void mergeSixExams(ExamMergeBean examMergeBean) throws BadRequestException;

    void mergeSevenExams(ExamMergeBean examMergeBean) throws BadRequestException;

    void mergeEightExams(ExamMergeBean examMergeBean) throws BadRequestException;

    void mergeNineExams(ExamMergeBean examMergeBean) throws BadRequestException;

    void mergeTenExams(ExamMergeBean examMergeBean) throws BadRequestException;
}
