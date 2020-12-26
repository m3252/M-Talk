package com.msc.mtalk.domain.member;

import com.msc.mtalk.domain.inject.InjectControllerTest;
import com.msc.mtalk.domain.member.dto.MemberCreateRequest;
import com.msc.mtalk.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import java.time.LocalDate;

import static com.msc.mtalk.domain.docs.ApiDocumentUtils.getDocumentRequest;
import static com.msc.mtalk.domain.docs.ApiDocumentUtils.getDocumentResponse;
import static com.msc.mtalk.domain.member.MemberHelper.mapToEntity;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.JsonFieldType.NUMBER;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
class MemberControllerTest extends InjectControllerTest {

    @MockBean // (2)
    MemberService memberService;

    @MockBean
    MemberRepository memberRepository;

    @Test
    @DisplayName("회원가입 성공")
    public void memberCreate() throws Exception {
        //given
        final MemberCreateRequest dto = MemberCreateRequest.builder()
                .birth(LocalDate.now())
                .contactNumber("01045863362")
                .email("m3252@naver.com")
                .id("m3252")
                .name("문승찬")
                .password("1234")
                .build();
        final Member member = mapToEntity(dto);
        given(memberService.create(member)).willReturn(1L);

        // when
        ResultActions result = mvc.perform(
                post("/members")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );

        //then
        result.andExpect(status().isOk())
                .andDo(document("members/member-create", // (4)
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("id").type(JsonFieldType.STRING).description("아이디"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
                                fieldWithPath("birth").type(JsonFieldType.STRING).attributes(key("format").value("yyyy-MM-dd")).description("생년월일"),
                                fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
                                fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호"),
                                fieldWithPath("contactNumber").type(JsonFieldType.STRING).attributes(key("format").value("01012345678")).description("핸드폰 번호").optional()
                        ),
                        responseFields(
                                fieldWithPath("sq").type(NUMBER).description("회원 일련번호")
                        ))
                );
    }



    @Test
    @DisplayName("이메일 사용가능 여부")
    void checkEmail() throws Exception {
        // given
        String email = "fail@naver.com";
        given(memberRepository.existsByEmail(email)).willReturn(true);

        // when
        ResultActions result = mvc.perform(get("/members/check/email/{email}", email));

        // then
        result.andExpect(status().isOk())
                .andDo(document("members/check-email",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("email").description("이메일")
                        ),
                        responseFields(
                                fieldWithPath("usable").description("사용가능")
                        ))
                );
    }

    @Test
    @DisplayName("아이디 사용가능 여부")
    void checkId() throws Exception {
        // given
        String id = "test";
        given(memberRepository.existsById(id)).willReturn(true);

        // when
        ResultActions result = mvc.perform(get("/members/check/id/{id}", id));

        // then
        result.andExpect(status().isOk())
                .andDo(document("members/check-id",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("id").description("계정 아이디")
                        ),
                        responseFields(
                                fieldWithPath("usable").description("사용가능")
                        ))
                );
    }
}