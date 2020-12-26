package com.msc.mtalk.domain.member;

import com.msc.mtalk.domain.inject.InjectRepositoryTest;
import com.msc.mtalk.domain.member.dto.MemberCreateRequest;
import com.msc.mtalk.entity.Member;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class MemberRepositoryTest extends InjectRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    static MemberCreateRequest memberCreateRequest;

    @BeforeAll
    static void setup() {
        memberCreateRequest = MemberCreateRequest.builder()
                .birth(LocalDate.now())
                .contactNumber("01045863363")
                .email("m3252@naver.com")
                .id("m32522")
                .name("문승찬")
                .password("1234")
                .build();
    }

    @Test
    @DisplayName("회원가입 성공")
    void save() {
        // given
        final Member member = mapToEntity(memberCreateRequest);

        // when
        memberRepository.save(member);

        em.flush();

        Member memberFindById = memberRepository.findById(member.getNo()).orElseThrow(EntityNotFoundException::new);

        // then
        assertThat(memberFindById).isEqualTo(member).isSameAs(member);

        assertThat(memberFindById.getNo()).isNotZero();
        assertThat(memberFindById.getId()).isEqualTo(member.getId());
        assertThat(memberFindById.getEmail()).isEqualTo(member.getEmail());
        assertThat(memberFindById.getPassword()).isEqualTo(member.getPassword());
        assertThat(memberFindById.getName()).isEqualTo(member.getName());
        assertThat(memberFindById.getBirth()).isEqualTo(member.getBirth());
        assertThat(memberFindById.getDateCreated()).isNotNull();
        assertThat(memberFindById.getLastUpdated()).isNotNull();
    }

    private Member mapToEntity(MemberCreateRequest memberCreateRequest){
        return Member.builder()
                .contactNumber(memberCreateRequest.getContactNumber())
                .name(memberCreateRequest.getName())
                .birth(memberCreateRequest.getBirth())
                .email(memberCreateRequest.getEmail())
                .id(memberCreateRequest.getId())
                .password(memberCreateRequest.getPassword())
                .build();
    }
}