package com.msc.mtalk.domain.member;

import com.msc.mtalk.domain.member.dto.MemberCreateRequest;
import com.msc.mtalk.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/members", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("")
    public ResponseEntity<?> createMember(@RequestBody @Valid final MemberCreateRequest memberCreateRequest) {
        Long createNo = memberService.create(mapToEntity(memberCreateRequest));
        return ResponseEntity.status(HttpStatus.OK).body(createNo);
    }

    @GetMapping("/check/email/{email}")
    public ResponseEntity<?> checkEmail(@PathVariable("email") String email, HttpServletRequest req, HttpServletResponse res) {
        if (req.getHeader("Test") != null) {
            res.addHeader("Test", req.getHeader("Test"));
        }
        memberService.checkEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body("ok");
    }

    @GetMapping("/check/id/{id}")
    public ResponseEntity<?> checkId(@PathVariable("id") String id) {
        memberService.checkId(id);
        return ResponseEntity.status(HttpStatus.OK).body("ok");
    }

    @GetMapping("/test")
    public ResponseEntity<?> test(HttpServletRequest req, HttpServletResponse res) {
        if (req.getHeader("Test") != null) {
            res.addHeader("Test", req.getHeader("Test"));
        }
        return ResponseEntity.status(HttpStatus.OK).body("ok");
    }

    //    @PutMapping("{memberNo}")
//    public ResponseEntity<?> updateMember(@PathVariable final Long memberNo, @RequestBody @Valid final MemberCreateRequest memberCreateRequest) {
//        memberService.update(memberNo, memberCreateRequest);
//        return ResponseEntity.status(HttpStatus.OK).body("");
//    }

    private Member mapToEntity(MemberCreateRequest memberCreateRequest) {
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
