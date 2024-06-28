package com.pknu.ebtalk.service.study;

import com.pknu.ebtalk.dto.study.FavDto;
import com.pknu.ebtalk.dto.study.StudyDto;

import java.util.List;
import java.util.Map;


public interface IStudyService {
    // 스터디 카테고리 조회
    List<StudyDto> selectStudyType();

    // 스터디 모집 등록
    StudyDto insertStudyConfirm(StudyDto studyDto);

    // 스터디 모집 수정
    StudyDto selectStudyInfoByNo(int no);

    StudyDto updateStudyConfirm(StudyDto studyDto);

    // 스터디 모집 리스트
    List<StudyDto> selectStudyAllList();

    void deleteStudyConfirm(StudyDto studyDto);

    // 스터디 관리 페이지 - 진행중인 스터디 리스트
    List<StudyDto> selectStudyInProgressByUId(String user_id);

    // 스터디 관리 페이지 - 신청목록
    List<StudyDto> selectStudyApplicationListById(String user_id);

    // 스터디 관리 페이지 - 신청목록 승인처리
    Map<String, Object> updateStudyApplicationListById(StudyDto studyDto);

    // 스터디 모집 신청
    Map<String, String> insertStudyApproval(StudyDto studyDto);

    // 스터디 즐겨찾기 - 등록
    Map<String, Object> favStudy(FavDto studyDto);

    // 스터디 즐겨찾기 조회
    FavDto selectFavStudy(FavDto favDto);

    // 스터디 즐겨찾기 - 취소
    Map<String, Object> cancelStudy(FavDto favDto);

    // 스터디 리스트 - 최신순
    List<StudyDto> selectStudyListOrderByfavCount();

    // 스터디 리스트 - 필터
    List<StudyDto> selectStudyListByCategoryNo(int categoryNo);

    // 즐겨찾기 목록 리스트
    List<StudyDto> selectStudyListByFav(String id);
}
