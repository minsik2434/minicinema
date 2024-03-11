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
    @Column
    private long memberId;
    @Column
    private String loginId;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private String name;
    @Column
    private LocalDate birth;
    @Column
    private String grade;
}
