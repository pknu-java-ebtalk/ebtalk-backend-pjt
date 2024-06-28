package com.pknu.ebtalk.mappers.study;

import com.pknu.ebtalk.dto.study.FavDto;
import com.pknu.ebtalk.dto.study.StudyDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface IStudyMapper {
    /*
     * 스터디 모집 카테고리 조회
     */
    List<StudyDto> selectStudyType();

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
    int deleteStudyInfoByNo(StudyDto studyDto);
    void deleteStudyMateByNo(int no);

    /*
     * 스터디 관리 페이지 - 진행중인 스터디 리스트
     */
    List<StudyDto> selectStudyInProgressByUid(String user_id);

    // 스터디 승인여부  유효성 검사
    String selectStudyApproveYnByUid(StudyDto studyDto);

    /*
     * 스터디 관리 페이지 - 신청목록 리스트
     */
    List<StudyDto> selectStudyApplicationListByUId(String user_id);

    /*
     * 스터디 관리 페이지 - 스터디 신청 승인 처리
     */
    int updateStudyApplicationListById(StudyDto studyDto);

    void updateStudyViews(int no);

    /*
     * 스터디 모집 신청
     */
    int insertStudyApproval(StudyDto studyDto);
    StudyDto selectStudyCount(int no);
    int selectStudyMateUserId(StudyDto studyDto);

    /*
     * 스터디 즐겨찾기
     */
    FavDto selectFavCount(FavDto studyDto);  // 유효성 검사
    int insertFav(FavDto studyDto);  // 즐겨찾기 추가
    int deleteFav(FavDto studyDto);  // 즐겨찾기 삭제

    /*
     * 스터디 리스트 - 최신순
     */
    List<StudyDto> selectStudyInfoOrderByFav();

    /*
     * 스터디 리스트 - 모집중
     */
    List<StudyDto> selectStudyListByCategoryNo1(int categoryNo);

    /*
     * 즐겨찾기 리스트 조회
     */
    List<StudyDto> selectStudyFavList(String userId);
}
