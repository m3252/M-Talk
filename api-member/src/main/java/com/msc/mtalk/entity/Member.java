package com.msc.mtalk.entity;

import com.msc.mtalk.entity.base.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static javax.persistence.GenerationType.AUTO;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
@ToString(of = {"sq", "id", "email", "name", "contactNumber", "profileUrl", "birth", "password"})
public class Member extends BaseEntity {

    @Id
    @Column(nullable = false, name = "member_sq")
    @GeneratedValue(strategy = AUTO)
    private Long sq;

    @Column(nullable = false, unique = true)
    private String id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    private String statusMessage;

    @Column(nullable = false, unique = true, length = 30)
    private String contactNumber;

    private String profileUrl;

    private LocalDate birth;

    @Column(length = 1, name = "member_status")
    private String status;

    @OneToMany(mappedBy = "member")
    private List<FriendRelation> friends = Collections.emptyList();

    @Builder
    public Member(String id, String email, String name, String contactNumber, LocalDate birth, String password) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.contactNumber = contactNumber;
        this.birth = birth;
        this.password = password;
    }

    public void update(String name, String contactNumber, LocalDate birth, String status, String password) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.birth = birth;
        this.status = status;
        this.password = password;
    }
}
