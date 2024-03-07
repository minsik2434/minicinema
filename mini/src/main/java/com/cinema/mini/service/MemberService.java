package com.cinema.mini.service;

import com.cinema.mini.domain.Member;
import com.cinema.mini.dto.LoginDto;
import com.cinema.mini.dto.MemberRegisterDto;
import com.cinema.mini.dto.ProfileUpdateDto;

public interface MemberService {

    void memberRegister(MemberRegisterDto memberRegisterDto);
    Member memberLogin(LoginDto loginDto);
    boolean isDuplicateMemberId(String userId);
    void profileUpdate(long memberId, ProfileUpdateDto profileUpdateDto);
//    void MemberFindId(UserFindInfoDTO userFindInfoDTO);
//    void MemberFindPw(UserFindInfoDTO userFindInfoDTO);
//    void MemberFindReserveInfo(UserFindInfoDTO userFindInfoDTO);
    
}
