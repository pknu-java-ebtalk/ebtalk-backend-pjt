package com.pknu.ebtalk.mappers.study;

import com.pknu.ebtalk.dto.study.StudyDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IStudyMapper {
    /*
     * 스터디 모집 등록
     */
    int insertStudyRegistInfo(StudyDto studyDto);
    void registStudyMate(StudyDto studyDto);
    StudyDto selectStudyInfoByNo(int no);

    /*
     * 스터디 모집 수정
     */
    int updateStudyInfo(StudyDto studyDto);

    /*
     * 스터디 모집 리스트
     */
    List<StudyDto> selectStudyCountMembers();

    /*
     * 스터디 모집글 삭제
     */
    int deleteStudyInfoByNo(int no);
    void deleteStudyMateByNo(int no);

    /*
     * 스터디 관리 페이지 - 진행중인 스터디 리스트
     */
    List<StudyDto> selectStudyInProgressByUid(StudyDto studyDto);

    // 스터디 승인여부  유효성 검사
    String selectStudyApproveYnByUid(String user_id);

    /*
     * 스터디 관리 페이지 - 신청목록 리스트
     */
    List<StudyDto> selectStudyApplicationListByUId(StudyDto studyDto);

    /*
     * 스터디 관리 페이지 - 스터디 신청 승인 처리
     */
    int updateStudyApplicationListById(String user_id);
}
