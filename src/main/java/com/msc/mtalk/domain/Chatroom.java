package com.msc.mtalk.domain;

import com.msc.mtalk.domain.base.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
public class Chatroom extends BaseEntity {

    @Id
    @Column(nullable = false, name = "chatroom_no")
    @GeneratedValue(strategy = AUTO)
    private Long no;

    @Column(length = 1, nullable = false)
    private String isDelete;

    @Builder
    public Chatroom(Long no, String isDelete) {
        this.isDelete = isDelete;
    }
}
