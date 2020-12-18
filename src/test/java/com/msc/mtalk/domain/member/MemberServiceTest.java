package com.msc.mtalk.domain.member;

import com.msc.mtalk.domain.common.CommonServiceTest;
import com.msc.mtalk.domain.member.dto.MemberCreateRequest;
import com.msc.mtalk.entity.Member;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

class MemberServiceTest extends CommonServiceTest {

    @InjectMocks
    MemberService memberService;

    @Mock
    MemberRepository memberRepository;

    static MemberCreateRequest memberCreateRequest;

    @BeforeAll
    static void setup() {
        memberCreateRequest = MemberCreateRequest.builder()
                .birth(LocalDate.now())
                .contactNumber("01045863362")
                .email("m3252@naver.com")
                .id("m32522")
                .name("문승찬")
                .password("1234")
                .build();
    }

    @Test
    @DisplayName("회원가입 성공")
    void join() {
        // given
        final Member member = mapToEntity(memberCreateRequest);

        given(memberRepository.existsById(member.getId())).willReturn(false);
        given(memberRepository.existsByEmail(member.getEmail())).willReturn(false);

        // when
        memberRepository.save(member);

        // then
        then(memberRepository).should(times(1)).save(member);
        then(memberRepository).shouldHaveNoMoreInteractions();
    }

    private Member mapToEntity(MemberCreateRequest memberCreateRequest){
        return Member.builder()
                .contactNumber(memberCreateRequest.getContactNumber())
                .name(memberCreateRequest.getName())
                .birth(memberCreateRequest.getBirth())
                .email(memberCreateRequest.getEmail())
                .id(memberCreateRequest.getId())
                .build();
    }
}