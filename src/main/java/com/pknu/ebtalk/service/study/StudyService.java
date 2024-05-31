package com.pknu.ebtalk.service.study;

import com.pknu.ebtalk.dto.study.StudyDto;
import com.pknu.ebtalk.mappers.study.IStudyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class StudyService implements IStudyService{

    private final IStudyMapper iStudyMapper;

    /*
     * 스터디 모집 등록
     */
    @Override
    public void insertStudyConfirm(StudyDto studyDto) {
        log.info("[StudyService] insertStudyConfirm()");

        // UserMemberDto loginedUserDto = (UserMemberDto) session.getAttribute("loginedUserDto");

        int result = iStudyMapper.insertStudyRegistInfo(studyDto);
        if(result > 0) {
            log.info("성공");

        } else {
            log.info("실패");

        }
        
    }
}
