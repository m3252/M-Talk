package com.msc.mtalk.model;

import com.msc.mtalk.domain.member.valid.ContactNumber;
import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
public class MemberRequest {

    @NotBlank(message = "아이디는 필수값 입니다.")
    private String id;

    @Email(message = "이메일 형식을 확인해 주세요.")
    @NotBlank(message = "이메일을 입력해 주세요.")
    private String email;

    @NotBlank(message = "이름을 입력해 주세요.")
    private String name;

    @ContactNumber
    private String contactNumber;

    @NotBlank(message = "비밀번호를 입력해 주세요.")
    private String password;


    @Past(message = "생년월일을 형식을 확인해 주세요.")
    @NotNull(message = "생년월일은 입력해 주세요.")
    private LocalDate birth;

    @Builder
    public MemberRequest(@NotBlank(message = "아이디는 필수값 입니다.") String id, @Email(message = "이메일 형식을 확인해 주세요.") @NotBlank(message = "이메일을 입력해 주세요.") String email, @NotBlank(message = "이름을 입력해 주세요.") String name, String contactNumber, @NotBlank(message = "비밀번호를 입력해 주세요.") String password, @Past(message = "생년월일을 형식을 확인해 주세요.") @NotNull(message = "생년월일은 입력해 주세요.") LocalDate birth) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.contactNumber = contactNumber;
        this.password = password;
        this.birth = birth;
    }
}

