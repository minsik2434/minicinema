package com.cinema.mini.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Builder
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberId;
    private String loginId;
    private String password;
    private String email;
    private String name;
    private LocalDate birth;
    @Enumerated(EnumType.STRING)
    private MemberGrade grade;
}
