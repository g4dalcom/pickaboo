package com.project.pickaboo.dto;

import com.project.pickaboo.domain.Member;
import com.project.pickaboo.domain.MemberRoleEnum;
import com.project.pickaboo.domain.PlatformEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
public class MemberDto {
    private Long member_id;
    private String username;
    private String nickname;
    private MemberRoleEnum role;
    private PlatformEnum platform;

    public MemberDto(final Long member_id, final String username, final String nickname, final MemberRoleEnum role, final PlatformEnum platform) {
        this.member_id = member_id;
        this.username = username;
        this.nickname = nickname;
        this.role = role;
        this.platform = platform;
    }

    // DB 조회를 위한 Entity -> DTO 변환
    public MemberDto of(Member member) {
        return MemberDto.builder()
                .member_id(member.getId())
                .username(member.getUsername())
                .nickname(member.getNickname())
                .role(member.getRole())
                .platform(member.getPlatform())
                .build();
    }
}
