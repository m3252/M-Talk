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
public class ChatroomConfig extends BaseEntity {

    @Id
    @Column(nullable = false, name = "chatroom_config_sq")
    @GeneratedValue(strategy = AUTO)
    private Long sq;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_sq")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "chatroom_sq")
    private Chatroom chatroom;

    private String chatroomName;

    @Builder
    public ChatroomConfig(Member member, Chatroom chatroom, String chatroomName) {
        this.member = member;
        this.chatroom = chatroom;
        this.chatroomName = chatroomName;
    }
}
