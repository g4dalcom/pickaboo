package com.project.pickaboo.dto;

import com.project.pickaboo.domain.Member;
import com.project.pickaboo.domain.MemberRoleEnum;
import com.project.pickaboo.domain.PlatformEnum;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

public class RegisterDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Request {
        private String username;
        private String password;
        private String passwordConfirm;

        @Builder
        public Request(final String username, final String password, final String passwordConfirm) {
            this.username = username;
            this.password = password;
            this.passwordConfirm = passwordConfirm;
        }

        public Member toMember(PasswordEncoder passwordEncoder) {
            return Member.builder()
                    .username(username)
                    .nickname(Member.getNickname(username))
                    .password(passwordEncoder.encode(password))
                    .role(MemberRoleEnum.MEMBER)
                    .platform(PlatformEnum.NONE)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Response {
        private String username;
        private String nickname;
        private MemberRoleEnum role;
        private PlatformEnum platform;

        @Builder
        public Response(final String username, final String nickname, final MemberRoleEnum role, final PlatformEnum platform) {
            this.username = username;
            this.nickname = nickname;
            this.role = role;
            this.platform = platform;
        }

        // DB 조회를 위한 Entity -> DTO 변환
        public static Response of(Member member) {
            return Response.builder()
                    .username(member.getUsername())
                    .nickname(member.getNickname())
                    .role(member.getRole())
                    .platform(member.getPlatform())
                    .build();
        }
    }
}
