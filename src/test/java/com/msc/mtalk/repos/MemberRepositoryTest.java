package com.msc.mtalk.repos;

import com.msc.mtalk.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void 베이스엔티티테스트_등록(){
        //given
        memberRepository.save(Member.builder()
                .id("TEST")
                .status("1")
                .birth(LocalDate.now())
                .contactNumber("01045863362")
                .email("m3252@naveer.com")
                .name("문승찬")
                .build());

        //when
        List<Member> members = memberRepository.findAll();

        //then
        Member member = members.get(0);
        System.out.println(member.getDateCreated());
        System.out.println(member.getLastUpdated());
        assertThat(member.getDateCreated()).isNotNull();
        assertThat(member.getLastUpdated()).isNotNull();
    }

    @Test
    void 업데이트(){
        List<Member> members = memberRepository.findAll();
        Member member = members.get(0);
        member.update("name", "01045863362", LocalDate.now(), "1");
        memberRepository.save(member);

        System.out.println(member.getDateCreated());
        System.out.println(member.getLastUpdated());
        assertThat(member.getDateCreated()).isNotNull();
        assertThat(member.getLastUpdated()).isNotNull();

    }
}