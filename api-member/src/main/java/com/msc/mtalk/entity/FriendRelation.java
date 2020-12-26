package com.msc.mtalk.entity;

import com.msc.mtalk.entity.base.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.AUTO;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
public class FriendRelation extends BaseEntity {

    @Id
    @Column(nullable = false, name = "friend_relation_sq")
    @GeneratedValue(strategy = AUTO)
    private Long sq;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_sq")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "friend_sq")
    private Member friend;

    private String friend_nickname;

    @Column(length = 1, name = "friend_relation_status", nullable = false)
    private String status;

    @Builder
    public FriendRelation(Member member, Member friend, String friend_nickname, String status) {
        this.member = member;
        this.friend = friend;
        this.friend_nickname = friend_nickname;
        this.status = status;
    }
}
