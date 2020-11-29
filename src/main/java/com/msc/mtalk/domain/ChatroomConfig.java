package com.msc.mtalk.domain;

import com.msc.mtalk.domain.base.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.AUTO;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
public class ChatroomConfig extends BaseEntity {

    @Id
    @Column(nullable = false, name = "chatroom_config_no")
    @GeneratedValue(strategy = AUTO)
    private Long no;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_no")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "chatroom_no")
    private Chatroom chatroom;

    private String chatroomName;

    @Builder
    public ChatroomConfig(Member member, Chatroom chatroom, String chatroomName) {
        this.member = member;
        this.chatroom = chatroom;
        this.chatroomName = chatroomName;
    }
}
