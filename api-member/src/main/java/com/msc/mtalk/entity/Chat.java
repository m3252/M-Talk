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
public class Chat extends BaseEntity {

    @Id
    @Column(nullable = false, name = "chat_sq")
    @GeneratedValue(strategy = AUTO)
    private Long sq;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "chatroom_sq", nullable = false)
    private Chatroom chatroom;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_sq", nullable = false)
    private Member member;

    @Column(length = 1000, nullable = false)
    private String message;

    @Column(length = 1, name = "chat_status", nullable = false)
    private String status;

    @Builder
    public Chat(Chatroom chatroom, Member member, String message, String status) {
        this.chatroom = chatroom;
        this.member = member;
        this.message = message;
        this.status = status;
    }
}
