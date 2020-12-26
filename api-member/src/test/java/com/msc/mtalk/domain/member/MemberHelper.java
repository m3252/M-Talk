package com.msc.mtalk.domain.member;

import com.msc.mtalk.domain.member.dto.MemberCreateRequest;
import com.msc.mtalk.entity.Member;

public class MemberHelper {

    static Member mapToEntity(MemberCreateRequest memberCreateRequest) {
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
