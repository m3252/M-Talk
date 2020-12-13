package com.msc.mtalk.domain.member;

import com.msc.mtalk.domain.member.dto.MemberCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/members", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("")
    public ResponseEntity<?> createMember(@RequestBody @Valid final MemberCreateRequest memberCreateRequest) {
        Long createNo = memberService.create(memberCreateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(createNo);
    }

    @GetMapping("/check/email/{email}")
    public ResponseEntity<?> checkEmail(@PathVariable("email") String email) {
        boolean check = memberService.checkEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body("ok");
    }

    @GetMapping("/check/id/{id}")
    public ResponseEntity<?> checkId(@PathVariable("id") String id) {
        boolean check = memberService.checkId(id);
        return ResponseEntity.status(HttpStatus.OK).body("ok");
    }

//    @PutMapping("{memberNo}")
//    public ResponseEntity<?> updateMember(@PathVariable final Long memberNo, @RequestBody @Valid final MemberCreateRequest memberCreateRequest) {
//        memberService.update(memberNo, memberCreateRequest);
//        return ResponseEntity.status(HttpStatus.OK).body("");
//    }



}
