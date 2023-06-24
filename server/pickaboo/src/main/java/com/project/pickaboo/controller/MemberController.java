package com.project.pickaboo.controller;

import com.project.pickaboo.dto.LoginDto;
import com.project.pickaboo.dto.RegisterDto;
import com.project.pickaboo.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Slf4j
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
}
