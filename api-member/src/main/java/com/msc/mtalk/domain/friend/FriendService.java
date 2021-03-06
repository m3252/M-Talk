package com.msc.mtalk.domain.friend;

import com.msc.mtalk.entity.FriendRelation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.msc.mtalk.domain.friend.FriendController.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;

    public Long addFriend(final Long friendSq){
        //friendRepository.save(friendSq);
        return 0L;
    }

    public List<FriendRelation> findAll(){
        return Optional.of(friendRepository.findAll()).orElse(Collections.emptyList());
    }

    public Long friendsCount(){
        return friendRepository.count();
    }
}
