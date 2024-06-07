package com.pknu.ebtalk.service.study;

import com.pknu.ebtalk.dto.study.StudyRoomDto;
import com.pknu.ebtalk.mappers.study.IStudyRoomMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public int insertStudyRoomConfirm(StudyRoomDto studyRoomDto) {
        log.info("[StudyRoomService] insertStudyRoomConfirm()");
        
        // 세션

        return iStudyRoomMapper.insertStudyRoomRegistInfo(studyRoomDto);
        
    }

    /*
     * 스터디룸 수정
     */
    @Override
    public String updateStudyRoomConfirm(StudyRoomDto studyRoomDto) {
        log.info("[StudyRoomService] updateStudyRoomConfirm()");

        int result = iStudyRoomMapper.updateStudyRoomInfo(studyRoomDto);
        if(result > 0){
            return  "수정이 완료되었습니다.";

        }
        return "수정에 실패하였습니다. 다시 시도해주세요.";
        
    }

    /*
     * 스터디룸 삭제
     */
    @Override
    public int deleteStudyRoomConfirm(StudyRoomDto studyRoomDto) {
        log.info("[StudyRoomService] deleteStudyRoomConfirm()");

        return iStudyRoomMapper.deleteStudyRoomConfirm(studyRoomDto);
        
    }
}
