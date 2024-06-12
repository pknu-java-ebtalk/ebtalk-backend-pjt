package com.pknu.ebtalk.mappers.member.admin;

import com.pknu.ebtalk.dto.member.UserMemberDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IAdminMemberMapper {
    // 승인 대기 명수
    int selectUserApproveWattingCount();

    // 승인 대기 정보
    List<UserMemberDto> selectUserApproveWatting();

    // 회원 요청 승인
    int updateApproveYnToY(String id);

    // 회원 요청 거부(db에서 회원 삭제)
    int deleteUser(String id);
}
