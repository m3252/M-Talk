package com.msc.mtalk.entity;

import com.msc.mtalk.entity.base.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
public class Chatroom extends BaseEntity {

    @Id
    @Column(nullable = false, name = "chatroom_sq")
    @GeneratedValue(strategy = AUTO)
    private Long sq;

    @Column(length = 1, nullable = false)
    private String isDelete;

    @Builder
    public Chatroom(Long sq, String isDelete) {
        this.isDelete = isDelete;
    }
}
