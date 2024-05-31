package com.pknu.ebtalk.service.study;

import com.pknu.ebtalk.dto.study.StudyDto;
import org.springframework.stereotype.Component;


public interface IStudyService {
    // 스터디 모집 등록
    void insertStudyConfirm(StudyDto studyDto);
}
