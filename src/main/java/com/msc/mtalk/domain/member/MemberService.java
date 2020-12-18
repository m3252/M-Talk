package com.msc.mtalk.domain.member;

import com.msc.mtalk.entity.Member;
import com.msc.mtalk.error.exception.DuplicateException;
import com.msc.mtalk.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Long create(final Member member) {
        checkEmail(member.getEmail());
        checkDuplicateId(member.getId());
        memberRepository.save(member);
        return member.getNo();
    }

    public void checkEmail(final String email) {
        checkDuplicateEmail(email);
    }
    public void checkId(final String id) {
        checkDuplicateId(id);
    }

    private void checkDuplicateEmail(final String email){
        if(memberRepository.existsByEmail(email)){
            throw new DuplicateException(email ,ErrorCode.EMAIL_DUPLICATION);
        }
    }

    private void checkDuplicateId(final String id){
        if(memberRepository.existsById(id)){
            throw new DuplicateException(id, ErrorCode.ID_DUPLICATION);
        }
    }
}
