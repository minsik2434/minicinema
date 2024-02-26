package com.cinema.mini.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private long memberId;
    @Column(name ="login_id")
    private String loginId;
    @Column(name ="password")
    private String password;
    @Column(name ="email")
    private String email;
    @Column(name="name")
    private String name;
    @Column(name="birth")
    private Date birth;
}
