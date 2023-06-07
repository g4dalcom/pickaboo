package com.project.pickaboo.dto;

import com.project.pickaboo.domain.Member;
import com.project.pickaboo.domain.MemberRoleEnum;
import com.project.pickaboo.domain.PlatformEnum;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class LoginDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Request {
        private String username;
        private String password;

        @Builder
        public Request(final String username, final String password) {
            this.username = username;
            this.password = password;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Response {
        private Long member_id;
        private String username;
        private String nickname;
        private MemberRoleEnum role;
        private PlatformEnum platform;

        @Builder
        public Response(final Long member_id, final String username, final String nickname, final MemberRoleEnum role, final PlatformEnum platform) {
            this.member_id = member_id;
            this.username = username;
            this.nickname = nickname;
            this.role = role;
            this.platform = platform;
        }

        public static Response of(Member member) {
            return Response.builder()
                    .member_id(member.getId())
                    .username(member.getUsername())
                    .nickname(member.getNickname())
                    .role(member.getRole())
                    .platform(member.getPlatform())
                    .build();
        }
    }
}
