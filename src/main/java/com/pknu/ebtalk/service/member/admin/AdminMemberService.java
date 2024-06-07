package com.pknu.ebtalk.service.member.admin;

import com.pknu.ebtalk.dto.member.UserMemberDto;
import com.pknu.ebtalk.mappers.member.admin.IAdminMemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class AdminMemberService implements IAdminMemberService{

    private final IAdminMemberMapper iAdminMemberMapper;
    
    // 승인 대기 명수
    @Override
    public int selectUserApproveWattingCount() {
        log.info("[AdminMemberService] selectUserApproveWattingCount()");

        return iAdminMemberMapper.selectUserApproveWattingCount();
    }

    // 승인 대기 회원 정보
    @Override
    public List<UserMemberDto> selectUserApproveWatting() {
        log.info("[AdminMemberService] selectUserApproveWatting()");

        return iAdminMemberMapper.selectUserApproveWatting();
    }

    // 회원 요청 승인
    public boolean updateApproveYnToY(String id){
        log.info("[AdminMemberService] updateApproveYnToY()");

        return iAdminMemberMapper.updateApproveYnToY(id) > 0;
    }

    // 회원 요청 거부
    public boolean deleteUser(String id){
        log.info("[AdminMemberService] deleteUser()");

        return iAdminMemberMapper.deleteUser(id) > 0;
    }
}
