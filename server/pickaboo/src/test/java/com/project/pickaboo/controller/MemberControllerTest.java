package com.project.pickaboo.controller;

import com.project.pickaboo.dto.LoginDto;
import com.project.pickaboo.dto.RegisterDto;
import com.project.pickaboo.repository.MemberRepository;
import com.project.pickaboo.util.ControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;

@ExtendWith(RestDocumentationExtension.class)
class MemberControllerTest extends ControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Mock
    private MemberRepository memberRepository;

    @DisplayName("회원가입을 성공하면 201 반환")
    @Test
    public void register() throws Exception {
        RegisterDto.Request request = new RegisterDto.Request("user1@user.com", "1234", "1234");

        restDocs
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when().post("/api/members/register")
                .then().log().all()
                .assertThat()
                .apply(document("member/register/success"))
                .statusCode(HttpStatus.CREATED.value());
    }

    @DisplayName("로그인을 성공하면 200 반환")
    @Test
    public void login() throws Exception {
        LoginDto.Request request = new LoginDto.Request("user1@user.com", "1234");

        restDocs
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when().post("/api/members/login")
                .then().log().all()
                .apply(document("member/login/success"))
                .statusCode(HttpStatus.OK.value());
    }
}