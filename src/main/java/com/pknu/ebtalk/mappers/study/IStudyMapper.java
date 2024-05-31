package com.pknu.ebtalk.mappers.study;

import com.pknu.ebtalk.dto.study.StudyDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IStudyMapper {
    /*
     * 스터디 모집 등록
     */
    int insertStudyRegistInfo(StudyDto studyDto);

}
