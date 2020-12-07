package com.msc.mtalk.service;

import com.msc.mtalk.domain.Member;
import com.msc.mtalk.error.MemberNotFoundException;
import com.msc.mtalk.model.MemberDTO;
import com.msc.mtalk.repos.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<MemberDTO> findAll() {
        return memberRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public MemberDTO get(final Long memberNo) {
        return memberRepository.findById(memberNo)
                .map(this::mapToDTO)
                .orElseThrow(() -> new MemberNotFoundException("Member Not Found Exception member No => " + memberNo));
    }

    public Long create(final MemberDTO memberDTO) {
        final Member member = mapToEntity(memberDTO);
        return memberRepository.save(member).getNo();
    }

    public void update(final Long memberNo, final MemberDTO memberDTO) {
        final Member member = memberRepository.findById(memberNo)
                .orElseThrow(() -> new MemberNotFoundException("Member Not Found Exception member No => " + memberNo));
        mapToEntity(memberDTO);
        memberRepository.save(member);
    }


    private MemberDTO mapToDTO(final Member member) {
        return MemberDTO.builder()
                .memberNo(member.getNo())
                .id(member.getId())
                .email(member.getEmail())
                .name(member.getName())
                .statusMessage(member.getStatusMessage())
                .contactNumber(member.getContactNumber())
                .profileUrl(member.getProfileUrl())
                .birth(member.getBirth())
                .status(member.getStatus())
                .build();
    }

    private Member mapToEntity(MemberDTO memberDTO){
        return Member.builder()
                .contactNumber(memberDTO.getContactNumber())
                .name(memberDTO.getName())
                .birth(memberDTO.getBirth())
                .email(memberDTO.getEmail())
                .status(memberDTO.getStatus())
                .id(memberDTO.getId())
                .build();

    }


}
