package com.msc.mtalk.repository;

import com.msc.mtalk.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import java.time.LocalDate;
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
                .contact("01045863362")
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

        member.update("수정", "1");


        System.out.println(member.getDateCreated());
        System.out.println(member.getLastUpdated());
        assertThat(member.getDateCreated()).isNotNull();
        assertThat(member.getLastUpdated()).isNotNull();
    }
}