package com.cinema.mini.service;

import com.cinema.mini.domain.Member;
import com.cinema.mini.dto.LoginDto;

public interface MemberService {

//    void MemberRegister(UserRegistDTO userRegistDTO);
    Member memberLogin(LoginDto loginDto);
//    void MemberCheckDuplicateId(String userId);
//    void MemberFindId(UserFindInfoDTO userFindInfoDTO);
//    void MemberFindPw(UserFindInfoDTO userFindInfoDTO);
//    void MemberFindReserveInfo(UserFindInfoDTO userFindInfoDTO);
    
}
