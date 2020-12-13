package com.msc.mtalk.domain.member;

import com.msc.mtalk.entity.Member;
import com.msc.mtalk.domain.member.dto.MemberCreateRequest;
import com.msc.mtalk.error.exception.DuplicationException;
import com.msc.mtalk.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Long create(final MemberCreateRequest memberCreateRequest) {
        final Member member = mapToEntity(memberCreateRequest);
        checkEmail(member.getEmail());
        checkDuplicateId(member.getId());
        return memberRepository.save(member).getNo();
    }

    public boolean checkEmail(final String email) {
        return checkDuplicateEmail(email);
    }

    private boolean checkDuplicateEmail(final String email){
        boolean is = memberRepository.existsByEmail(email);
        if(is){
            throw new DuplicationException(email ,ErrorCode.EMAIL_DUPLICATION);
        }
        return false;
    }


    public boolean checkId(final String id) {
        return checkDuplicateId(id);
    }

    private boolean checkDuplicateId(final String id){
        boolean is = memberRepository.existsById(id);
        if(is){
            throw new DuplicationException(id, ErrorCode.ID_DUPLICATION);
        }
        return false;
    }

    //TODO
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
