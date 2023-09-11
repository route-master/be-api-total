package org.routemaster.api.total.endpoints.kakao.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.domain.kakao.friend.KakaoFriendService;
import org.routemaster.api.total.domain.kakao.friend.data.KakaoFriendResponse;
import org.routemaster.api.total.endpoints.kakao.vo.KakaoFriendEndpointRequest;
import org.routemaster.api.total.endpoints.kakao.vo.KakaoFriendEndpointResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class KakaoEndpointService {

    private final KakaoFriendService kakaoFriendService;

    public Mono<KakaoFriendEndpointResponse> details(KakaoFriendEndpointRequest request) {
        Mono<KakaoFriendResponse> data = kakaoFriendService.getTalkFriends(
            request.getAccessToken(),
            request.getOffset(),
            request.getLimit(),
            request.getOrder(),
            request.getFriendOrder()
        );

        return data.map(d -> KakaoFriendEndpointResponse.builder()
            .data(d)
            .build()
        );
    }
}
