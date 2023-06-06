package com.project.pickaboo.service;

import com.project.pickaboo.domain.Member;
import com.project.pickaboo.dto.RegisterDto;
import com.project.pickaboo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    /**
     * TODO: 토큰 작업 후 login 로직 추가
     */
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterDto.Response register(RegisterDto.Request request) {
        if (memberRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        }

        if (!request.getPassword().equals(request.getPasswordConfirm())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        Member member = request.toMember(passwordEncoder);
        memberRepository.save(member);

        return RegisterDto.Response.of(member);
    }
}
