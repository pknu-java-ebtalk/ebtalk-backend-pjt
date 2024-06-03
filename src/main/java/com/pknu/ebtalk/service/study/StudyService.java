package com.pknu.ebtalk.service.study;

import com.pknu.ebtalk.dto.study.StudyDto;
import com.pknu.ebtalk.mappers.study.IStudyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class StudyService implements IStudyService{

    private final IStudyMapper iStudyMapper;

    /*
     * 스터디 모집 등록
     */
    @Override
    public StudyDto insertStudyConfirm(StudyDto studyDto) {
        log.info("[StudyService] insertStudyConfirm()");

        // UserMemberDto loginedUserDto = (UserMemberDto) session.getAttribute("loginedUserDto");

        int result = iStudyMapper.insertStudyRegistInfo(studyDto);

        // insertStudyRegistInfo(studyDto)에서 가져온 no 값 변수에 담기
        int no = studyDto.getNo();
        log.info(no);

        if(result > 0) {
            log.info("성공");
            iStudyMapper.registStudyMate(studyDto);
            studyDto = iStudyMapper.selectStudyInfoByNo(no);

        } else {
            log.info("실패");
            //등록 성공 -> dto를 보여주고
            //실패 -> 알람과 함께 다시 등록한 페이지로 가는거
        }
        return studyDto ;

    }

    /*
     * 스터디 모집 수정
     */
    @Override
    public StudyDto selectStudyInfoByNo(int no) {
        log.info("[StudyService] selectStudyInfoByNo()");

        return iStudyMapper.selectStudyInfoByNo(no);
        
    }

    @Override
    public StudyDto updateStudyConfirm(StudyDto studyDto) {
        log.info("[StudyService] updateStudyConfirm()");

        int result = iStudyMapper.updateStudyInfo(studyDto);
        
        if(result > 0) {
            log.info("성공");
            studyDto = iStudyMapper.selectStudyInfoByNo(studyDto.getNo());

        } else {
            log.info("실패");

        }
        return studyDto ;
    }

    /*
     * 스터디 모집 리스트
     */
    @Override
    public List<StudyDto> selectStudyAllList() {
        log.info("[StudyService] selectStudyAllList()");

        List<StudyDto> studyCountMembers = iStudyMapper.selectStudyCountMembers();

        return studyCountMembers;
        
    }

    /*
     * 스터디 모집글 삭제
     */
    @Override
    public void deleteStudyConfirm(int no) {
        log.info("[StudyService] deleteStudyConfirm()");

        int result = iStudyMapper.deleteStudyInfoByNo(no);
        
        if(result > 0) {
            log.info("삭제완료");
//            iStudyMapper.deleteStudyMateByNo(no);

        } else {
            log.info("삭제실패");

        }

    }

    /*
     * 스터디 관리 페이지 - 진행중인 스터디 리스트
     */
    @Override
    public List<StudyDto> selectStudyInProgressByUId(StudyDto studyDto) {
        log.info("[StudyService] selectStudyInProgressByUId()");

        List<StudyDto> studyDtos = iStudyMapper.selectStudyInProgressByUid(studyDto);

        return studyDtos;

    }

    /*
     * 스터디 관리 페이지 - 신청목록
     */
    @Override
    public void selectStudyApplicationListById(StudyDto studyDto) {
        log.info("[StudyService] selectStudyApplicationListById()");



    }





}
