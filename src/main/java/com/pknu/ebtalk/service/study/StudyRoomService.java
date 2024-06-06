package com.pknu.ebtalk.service.study;

import com.pknu.ebtalk.dto.study.StudyRoomDto;
import com.pknu.ebtalk.mappers.study.IStudyRoomMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@Service
@RequiredArgsConstructor
public class StudyRoomService implements IStudyRoomService {
    private final IStudyRoomMapper iStudyRoomMapper;
    /*
     * 스터디룸 리스트 조회
     */
    @Override
    public List<StudyRoomDto> selectStudyRoomAllList(int study_no) {
        log.info("[StudyRoomService] selectStudyRoomAllList()");

        List<StudyRoomDto> studyRoomDtos = iStudyRoomMapper.selectStudyRoomAllListByNo(study_no);

        return studyRoomDtos;

    }

    /*
     * 스터디룸 글 등록
     */
    @Override
    public void insertStudyRoomConfirm(StudyRoomDto studyRoomDto) {
        log.info("[StudyRoomService] insertStudyRoomConfirm()");
        
        // 세션

        iStudyRoomMapper.insertStudyRoomRegistInfo(studyRoomDto);
        
    }
}
