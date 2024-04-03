package com.cinema.mini.service;

import com.cinema.mini.domain.Member;
import com.cinema.mini.dto.LoginDto;
import com.cinema.mini.dto.MemberRegisterDto;
import com.cinema.mini.dto.ProfileUpdateDto;
import com.cinema.mini.dto.PasswordUpdateDto;

public interface MemberService {
    Member memberRegister(MemberRegisterDto memberRegisterDto);
    Member memberLogin(LoginDto loginDto);
    boolean isDuplicateMemberId(String userId);
    void profileUpdate(long memberId, ProfileUpdateDto profileUpdateDto);
    void passwordUpdate(long memberId, String password);
//    void MemberFindPw(UserFindInfoDTO userFindInfoDTO);
//    void MemberFindReserveInfo(UserFindInfoDTO userFindInfoDTO);
    
}
