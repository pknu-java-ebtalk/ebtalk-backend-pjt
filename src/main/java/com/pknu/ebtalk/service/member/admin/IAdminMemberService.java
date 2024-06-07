package com.pknu.ebtalk.service.member.admin;

import com.pknu.ebtalk.dto.member.UserMemberDto;

import java.util.List;

public interface IAdminMemberService {
    // 승인 대기 명수
    int selectUserApproveWattingCount();

    // 승인 대기 회원 정보
    List<UserMemberDto> selectUserApproveWatting();

    // 회원 요청 승인
    boolean updateApproveYnToY(String id);

    // 회원 요청 거부
    boolean deleteUser(String id);
}
