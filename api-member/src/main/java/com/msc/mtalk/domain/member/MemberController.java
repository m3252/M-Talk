package com.msc.mtalk.domain.member;

import com.msc.mtalk.domain.member.dto.MemberCreateRequest;
import com.msc.mtalk.entity.Member;
import lombok.Data;
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
    public ResponseEntity<CreateResponse> createMember(@RequestBody @Valid final MemberCreateRequest memberCreateRequest) {
        Long createSq = memberService.create(mapToEntity(memberCreateRequest));
        return ResponseEntity.status(HttpStatus.OK).body(new CreateResponse(createSq));
    }

    @GetMapping("/check/email/{email}")
    public ResponseEntity<CheckResponse> checkEmail(@PathVariable("email") String email, HttpServletRequest req, HttpServletResponse res) {
        memberService.checkEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(new CheckResponse(true));
    }

    @GetMapping("/check/id/{id}")
    public ResponseEntity<CheckResponse> checkId(@PathVariable("id") String id) {
        memberService.checkId(id);
        return ResponseEntity.status(HttpStatus.OK).body(new CheckResponse(true));
    }

    @GetMapping("/test")
    public ResponseEntity<?> test(HttpServletRequest req, HttpServletResponse res) {
        if (req.getHeader("Test") != null) {
            res.addHeader("Test", req.getHeader("Test"));
        }
        return ResponseEntity.status(HttpStatus.OK).body("ok");
    }

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

    @Data
    static class CreateResponse {
        private Long sq;
        public CreateResponse(Long sq) {
            this.sq = sq;
        }
    }

    @Data
    static class CheckResponse {
        private boolean usable;
        public CheckResponse(boolean usable) {
            this.usable = usable;
        }
    }

}
