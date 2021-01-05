package com.msc.mtalk.domain.friend;

import com.msc.mtalk.entity.FriendRelation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends JpaRepository<FriendRelation, Long> {

}
