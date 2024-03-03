package com.cinema.mini.service;

import com.cinema.mini.domain.Member;
import com.cinema.mini.dto.LoginDto;
import com.cinema.mini.dto.MemberRegisterDto;

public interface MemberService {

    void memberRegister(MemberRegisterDto memberRegisterDto);
    Member memberLogin(LoginDto loginDto);
    boolean isDuplicateMemberId(String userId);
//    void MemberFindId(UserFindInfoDTO userFindInfoDTO);
//    void MemberFindPw(UserFindInfoDTO userFindInfoDTO);
//    void MemberFindReserveInfo(UserFindInfoDTO userFindInfoDTO);
    
}
