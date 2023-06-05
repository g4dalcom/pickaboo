package com.project.pickaboo.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;        // 이메일

    @Column(nullable = false, unique = true)
    private String nickname;        // 이메일에서 추출

    @Column(nullable = false)
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private MemberRoleEnum role;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private PlatformEnum platform;

    @Builder
    public Member(final Long id, final String username, final String nickname, final String password, final MemberRoleEnum role, final PlatformEnum platform) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.role = role;
        this.platform = platform;
    }
}
