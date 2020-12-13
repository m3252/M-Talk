package com.msc.mtalk.domain.member;

import com.msc.mtalk.IntegrationTest;
import com.msc.mtalk.domain.member.dto.MemberCreateRequest;
import com.msc.mtalk.entity.Member;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class MemberControllerTest extends IntegrationTest {

    @Test
    public void 회원가입_성공() throws Exception {
        //given
        final Member member = Member.builder().build();
        final MemberCreateRequest dto = MemberCreateRequest.builder()
                .birth(LocalDate.now())
                .contactNumber("01045863362")
                .email("m3252@naver.com")
                .id("m3252")
                .name("문승찬")
                .password("1234")
                .build();

        //when
        final ResultActions resultActions = requestSignUp(dto);

        //then
        resultActions
                .andExpect(status().isOk())
        ;
    }

    @Test
    public void 회원가입_유효하지않은_입력값() throws Exception {
        //given
        final String email = "asdasd@dasd.com";
        //when
        final ResultActions resultActions = requestCheckEmail(email);
        //then
        resultActions
                .andExpect(status().isBadRequest());
    }



    private ResultActions requestSignUp(MemberCreateRequest dto) throws Exception {
        return mvc.perform(post("/api/members")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(dto)))
                .andDo(print());
    }

    private ResultActions requestCheckEmail(String email) throws Exception {
        return mvc.perform(get("/api/members/check/email/{email}", email)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print());
    }
    private ResultActions requestGetMember(Long memberId) throws Exception {
        return mvc.perform(get("/api/members/check/id/{id}", memberId)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print());
    }


}