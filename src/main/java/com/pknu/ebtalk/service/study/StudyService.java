package com.pknu.ebtalk.service.study;

import com.pknu.ebtalk.dto.member.UserMemberDto;
import com.pknu.ebtalk.dto.study.StudyDto;
import com.pknu.ebtalk.mappers.study.IStudyMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        StudyDto studyDto = iStudyMapper.selectStudyInfoByNo(no);
        iStudyMapper.updateStudyViews(no);

        return studyDto;
        
    }

    @Override
    public StudyDto updateStudyConfirm(StudyDto studyDto) {
        log.info("[StudyService] updateStudyConfirm()");

        // 세션에서 가져오기

        studyDto.setUser_id("eunji123");

        int result = iStudyMapper.updateStudyInfo(studyDto);
        
        if(result > 0) {
            log.info("성공");
            studyDto = iStudyMapper.selectStudyInfoByNo(studyDto.getNo());
            return studyDto;

        } else {
            log.info("실패");

        }
        return null;
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
            
        } else {
            log.info("삭제실패");

        }

    }

    /*
     * 스터디 신청
     */
    @Override
    public Map<String, String> insertStudyApproval(int no) {
        log.info("[StudyService] insertStudyApproval()");

        // 로그인한 유저 들고오기
        // UserMemberDto loginedUserDto = (UserMemberDto) session.getAttribute("loginedUserDto");

        Map<String, String> msgData = new HashMap<>();
        String user_id = "user1@gmail.com";

        StudyDto studyDto = new StudyDto();
        studyDto.setUser_id(user_id);
        studyDto.setNo(no);

        // 이미 신청한 사용자 중복 체크
        int isStudyInMember =iStudyMapper.selectStudyMateUserId(studyDto);

        if(isStudyInMember == 0) {
            // 스터디 인원 유효성 검사
            StudyDto studyCountDto = iStudyMapper.selectStudyCount(no);

            if(studyCountDto.getCount_member() < studyCountDto.getCount()) {
                int result = iStudyMapper.insertStudyApproval(studyDto);

                if(result > 0) {
                    log.info("성공");
                    msgData.put("msg", "성공적으로 완료되었습니다.");
                    msgData.put("result", "1");

                    return msgData;

                }

            } else {
                log.info("(신청인원) 실패");
                msgData.put("msg", "신청인원을 초과하였습니다.");
                msgData.put("result", "0");
                return msgData;
            }

        }

        log.info("(이미 신청) 실패");
        msgData.put("msg", "이미 신청하신 스터디 입니다");
        msgData.put("result", "0");
        return msgData;

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
    public List<StudyDto> selectStudyApplicationListById(String user_id) {
        log.info("[StudyService] selectStudyApplicationListById()");

        // 세션 가져오기
        user_id = "kkk";

        List<StudyDto> studyDtos = iStudyMapper.selectStudyApplicationListByUId(user_id);

        return studyDtos;


    }

    /*
     * 스터디 관리 페이지 - 스터디 신청 승인 처리
     */
    @Override
    public Map<String, Object> updateStudyApplicationListById(String user_id) {
        log.info("[StudyService] updateStudyApplicationListById()");

        Map<String, Object> map = new HashMap<>();

//        String user_id = studyDto.getUser_id();
//        String approve_yn = studyDto.getApprove_yn();

        // approve_yn 값 업데이트 하기 위한 mapper
        int result = iStudyMapper.updateStudyApplicationListById(user_id);

        if(result > 0) {
            String approve_yn = iStudyMapper.selectStudyApproveYnByUid(user_id);
            log.info(approve_yn);
            log.info(user_id);

            map.put("result", approve_yn);

            return map;

        }
        return null;

    }

}
