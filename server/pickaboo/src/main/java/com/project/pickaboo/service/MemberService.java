package com.project.pickaboo.service;

import com.project.pickaboo.domain.Member;
import com.project.pickaboo.domain.RefreshToken;
import com.project.pickaboo.dto.LoginDto;
import com.project.pickaboo.dto.RegisterDto;
import com.project.pickaboo.exception.CustomException;
import com.project.pickaboo.exception.ErrorCode;
import com.project.pickaboo.jwt.TokenProvider;
import com.project.pickaboo.repository.MemberRepository;
import com.project.pickaboo.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final HttpServletResponse response;
    public static final String BEARER_TYPE = "bearer ";
    public static final String AUTHORIZATION = "Authorization";
    public static final String REFRESH_HEADER = "RefreshToken";

    public RegisterDto.Response register(RegisterDto.Request request) {
        if (memberRepository.existsByUsername(request.getUsername())) {
            throw new CustomException(ErrorCode.EXIST_USERNAME);
        }

        if (!request.getPassword().equals(request.getPasswordConfirm())) {
            throw new CustomException(ErrorCode.NOT_MATCH_PASSWORD);
        }

        Member member = request.toMember(passwordEncoder);
        memberRepository.save(member);

        return RegisterDto.Response.of(member);
    }

    public LoginDto.Response login(LoginDto.Request request) {
        Member member = memberRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) throw  new CustomException(ErrorCode.NOT_MATCH_PASSWORD);

        if (!refreshTokenRepository.existsByMember_Id(member.getId())) {
            RefreshToken refreshToken = RefreshToken.builder()
                    .refreshToken(tokenProvider.generateRefreshToken(request.getUsername()))
                    .member(member)
                    .build();
            refreshTokenRepository.save(refreshToken);
            response.addHeader(REFRESH_HEADER, refreshToken.getRefreshToken());
        }

        String accessToken = tokenProvider.generateAccessToken(request.getUsername());
        response.addHeader(AUTHORIZATION, BEARER_TYPE + accessToken);
        log.info("accessToken = {}", accessToken);

        return LoginDto.Response.of(member);
    }
}
