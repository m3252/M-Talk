package com.msc.mtalk.domain.friend;

import com.msc.mtalk.entity.FriendRelation;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("friends")
public class FriendController {

    private final FriendService friendService;

    @GetMapping
    public ResponseEntity<FriendsResponse> friends(HttpServletRequest req, @RequestParam @Valid FriendController.FriendsRequest searchParam) {
        // TODO redis session 클러스터링 혹은 Spring cloud Security 를 통해 사용자의 PK를 가져온다.
        FriendsResponse friendsResponse = new FriendsResponse();
        friendsResponse.setFriends(friendService.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList()));
        friendsResponse.setFriendsCount(friendService.friendsCount());
        return ResponseEntity.status(HttpStatus.OK).body(friendsResponse);
    }

    private FriendsDTO mapToDTO(final FriendRelation friendRelation) {
        return FriendsDTO.builder()
                .friendRelationSq(friendRelation.getSq())
                .friendSq(friendRelation.getSq())
                .memberSq(friendRelation.getMember().getSq())
                .friendNickname(friendRelation.getFriend_nickname())
                .friendStatus(friendRelation.getStatus())
                .build();
    }

    @Getter
    static class FriendsRequest {

    }

    @Data
    static class FriendsResponse {
        private List<FriendsDTO> friends = Collections.emptyList();
        private Long friendsCount;
    }

    @Getter
    @Builder
    @ToString
    static class FriendsDTO {
        private Long friendRelationSq;

        @NotNull
        private Long memberSq;

        @NotNull
        private Long friendSq;

        @NotNull
        @Size(max = 255)
        private String friendNickname;

        @NotNull
        @Size(max = 1)
        private String friendStatus;
    }

}
