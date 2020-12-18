package com.msc.mtalk.domain.member;

import com.msc.mtalk.domain.common.CommonServiceTest;
import com.msc.mtalk.domain.member.dto.MemberCreateRequest;
import com.msc.mtalk.entity.Member;
import com.msc.mtalk.error.exception.DuplicateException;
import com.msc.mtalk.error.exception.ErrorCode;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

class MemberServiceTest extends CommonServiceTest {

    @InjectMocks
    MemberService memberService;

    @Mock
    MemberRepository memberRepository;
    static EnhancedRandom memberCreator;

    @BeforeAll
    static void setUp() {
        memberCreator = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                .stringLengthRange(3, 5)
                .dateRange(LocalDate.of(1920, 1, 1), LocalDate.of(2005, 1, 1))
                .excludeField(f -> f.getName().equals("id"))
                .excludeField(f -> f.getName().equals("contactNumber"))
                .excludeField(f -> f.getName().equals("dateCreated"))
                .excludeField(f -> f.getName().equals("lastUpdated"))
                .excludeField(f -> f.getName().equals("name"))
                .excludeField(f -> f.getName().equals("password"))
                .randomize(f -> f.getName().equals("email"), () -> "test@gmail.com")
                .build();
    }

    @Test
    @DisplayName("회원가입 성공")
    void create(){
        // given
        final Member member = memberCreator.nextObject(Member.class);
        given(memberRepository.existsByEmail(member.getEmail())).willReturn(false);
        given(memberRepository.existsById(member.getId())).willReturn(false);

        // when
        memberService.create(member);

        // then
        then(memberRepository).should(times(1)).save(member);
        then(memberRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    @DisplayName("회원가입 이메일 중복")
    void memberCreateDuplicateEmail() {
        // given
        Member member = memberCreator.nextObject(Member.class);
        given(memberRepository.existsByEmail(member.getEmail())).willReturn(true);

        // then
        assertThatThrownBy(() -> memberService.create(member))
                .isInstanceOf(DuplicateException.class);
    }

    @Test
    @DisplayName("회원가입 아이디 중복")
    void memberCreateDuplicateId() {
        // given
        Member member = memberCreator.nextObject(Member.class);
        given(memberRepository.existsByEmail(member.getEmail())).willReturn(true);

        // then
        assertThatThrownBy(() -> memberService.create(member))
                .isInstanceOf(DuplicateException.class);
    }

}