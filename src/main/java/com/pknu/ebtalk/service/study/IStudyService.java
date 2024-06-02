package com.pknu.ebtalk.service.study;

import com.pknu.ebtalk.dto.study.StudyDto;

import java.util.List;


public interface IStudyService {
    // 스터디 모집 등록
    StudyDto insertStudyConfirm(StudyDto studyDto);

    // 스터디 모집 수정
    StudyDto selectStudyInfoByNo(int no);

    StudyDto updateStudyConfirm(StudyDto studyDto);

    // 스터디 모집 리스트
    List<StudyDto> selectStudyAllList();

    void deleteStudyConfirm(int no);
}
