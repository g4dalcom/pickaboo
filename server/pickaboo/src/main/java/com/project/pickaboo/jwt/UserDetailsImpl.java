package com.project.pickaboo.jwt;

import com.project.pickaboo.domain.Member;
import com.project.pickaboo.domain.MemberRoleEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UserDetailsImpl implements UserDetails {
    private final Member member;

    public UserDetailsImpl(final Member member) {
        this.member = member;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        MemberRoleEnum role = member.getRole();
        String authority = role.getAuthority();

        SimpleGrantedAuthority simpleAuthority = new SimpleGrantedAuthority(authority);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(simpleAuthority);

        return authorities;
    }

    public Member getMember() {
        return member;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {      // 계정 만료 여부 (true: 만료되지 않음)
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {       // 계정 잠김 여부 (true: 잠기지 않음)
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {      // 패스워드 만료 여부 (true: 만료되지 않음)
        return true;
    }

    @Override
    public boolean isEnabled() {        // 계정 사용가능 여부 (true: 사용 가능)
        return true;
    }
}
