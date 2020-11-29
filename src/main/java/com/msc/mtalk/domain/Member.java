package com.msc.mtalk.domain;

import com.msc.mtalk.domain.base.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static javax.persistence.GenerationType.AUTO;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
public class Member extends BaseEntity {

    @Id
    @Column(nullable = false, name = "member_no")
    @GeneratedValue(strategy = AUTO)
    private Long no;

    @Column(nullable = false, unique = true)
    private String id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    private String statusMessage;

    @Column(nullable = false, unique = true, length = 30)
    private String contact;

    private String profileUrl;

    private LocalDate birth;

    @Column(length = 1, name = "user_status")
    private String status;

    @OneToMany(mappedBy = "member")
    private List<FriendRelation> friends = Collections.emptyList();

    @Builder
    public Member(String id, String email, String name, String contact, LocalDate birth, String status) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.contact = contact;
        this.birth = birth;
        this.status = status;
    }

    public void update(String name, String status) {
        this.name = name;
        this.status = status;
    }
}