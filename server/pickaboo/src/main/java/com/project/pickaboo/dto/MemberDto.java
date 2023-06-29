package com.project.pickaboo.dto;

import com.project.pickaboo.domain.MemberRoleEnum;
import com.project.pickaboo.domain.PlatformEnum;
import lombok.Builder;
import lombok.Getter;

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
}
