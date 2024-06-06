package com.pknu.ebtalk.mappers.study;

import com.pknu.ebtalk.dto.study.StudyRoomDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IStudyRoomMapper {

    // 스터디룸 등록글 리스트 조회
    List<StudyRoomDto> selectStudyRoomAllListByNo(int study_no);

    // 스터디룸 글 등록
    int insertStudyRoomRegistInfo(StudyRoomDto studyRoomDto);

    // 스터디룸 수정
    int updateStudyRoomInfo(StudyRoomDto studyRoomDto);
}
