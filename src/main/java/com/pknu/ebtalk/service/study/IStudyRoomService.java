package com.pknu.ebtalk.service.study;


import com.pknu.ebtalk.dto.study.StudyRoomDto;

import java.util.List;

public interface IStudyRoomService {
    // 스터디룸 리스트 조회
    List<StudyRoomDto> selectStudyRoomAllList(int study_no);

    // 스터디룸 글 등록
    void insertStudyRoomConfirm(StudyRoomDto studyRoomDto);
}
