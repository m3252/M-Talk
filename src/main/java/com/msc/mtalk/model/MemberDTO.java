package com.msc.mtalk.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Collections;


@Getter
public class MemberDTO {

    private Long memberNo;

    @NotNull
    @Size(max = 50)
    private String id;

    @NotNull
    @Size(max = 255)
    private String email;

    @NotNull
    @Size(max = 50)
    private String name;

    @Size(max = 255)
    private String statusMessage;

    @NotNull
    @Size(max = 30)
    private String contactNumber;

    @Size(max = 255)
    private String profileUrl;

    private LocalDate birth;

    @Size(max = 1)
    private String status;

    private Long friendNo;

    @Builder
    public MemberDTO(Long memberNo, @NotNull @Size(max = 50) String id, @NotNull @Size(max = 255) String email, @NotNull @Size(max = 50) String name, @Size(max = 255) String statusMessage, @NotNull @Size(max = 30) String contactNumber, @Size(max = 255) String profileUrl, LocalDate birth, @Size(max = 1) String status, Long friendNo) {
        this.memberNo = memberNo;
        this.id = id;
        this.email = email;
        this.name = name;
        this.statusMessage = statusMessage;
        this.contactNumber = contactNumber;
        this.profileUrl = profileUrl;
        this.birth = birth;
        this.status = status;
        this.friendNo = friendNo;
    }
}
