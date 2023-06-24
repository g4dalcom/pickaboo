package com.project.pickaboo.controller;

import com.project.pickaboo.dto.LoginDto;
import com.project.pickaboo.dto.MemberDto;
import com.project.pickaboo.dto.RegisterDto;
import com.project.pickaboo.jwt.UserDetailsImpl;
import com.project.pickaboo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/api/members/register")
    public ResponseEntity<RegisterDto.Response> register(@RequestBody RegisterDto.Request request) {
        return ResponseEntity.created(URI.create("/")).body(memberService.register(request));
    }

    @PostMapping("/api/members/login")
    public ResponseEntity<LoginDto.Response> login(@RequestBody LoginDto.Request request) {
        return ResponseEntity.ok(memberService.login(request));
    }

    @GetMapping("/api/members/{id}")
    public ResponseEntity<MemberDto> getMember(@PathVariable Long id,
                                               @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return ResponseEntity.ok(memberService.getMember(id, userDetails));
    }
}
