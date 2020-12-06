package com.msc.mtalk.rest;

import com.msc.mtalk.domain.Member;
import com.msc.mtalk.model.MemberDTO;
import com.msc.mtalk.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(final MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members")
    public ResponseEntity<?> getMembers(){
        List<MemberDTO> members = memberService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(members);
    }

    @GetMapping("/member/{memberNo}")
    public ResponseEntity<?> getMember(final @PathVariable Long memberNo){
        MemberDTO memberDTO = memberService.get(memberNo);
        return ResponseEntity.status(HttpStatus.OK).body(memberDTO);
    }

    @PostMapping("/member")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createMember(@RequestBody @Valid final MemberDTO memberDTO) {
        Long createNo = memberService.create(memberDTO);
        return ResponseEntity.status(HttpStatus.OK).body(createNo);
    }

    @PutMapping("/member/{memberNo}")
    public ResponseEntity<?> updateMember(@PathVariable final Long memberNo, @RequestBody @Valid final MemberDTO memberDTO) {
        memberService.update(memberNo, memberDTO);
        return ResponseEntity.status(HttpStatus.OK).body("");
    }

}
