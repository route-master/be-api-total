package org.routemaster.api.total.endpoints.kakao.controller;


import lombok.RequiredArgsConstructor;
import org.routemaster.api.total.domain.kakao.friend.data.KakaoFriendResponse;
import org.routemaster.api.total.endpoints.kakao.vo.KakaoFriendEndpointRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/kakao")
@RequiredArgsConstructor
public class KakaoRestController {

    @PostMapping("/friends")
    public Mono<KakaoFriendResponse> getFriends(@RequestBody @Validated KakaoFriendEndpointRequest request) {
        return null;
    }
}
