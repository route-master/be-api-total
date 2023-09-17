package org.routemaster.api.total.domain.kakao.friend;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.domain.kakao.KakaoAPI;
import org.routemaster.api.total.domain.kakao.friend.data.KakaoFriendResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoFriendService {

    public Mono<KakaoFriendResponse> getTalkFriends(String accessToken, Integer offset, Integer limit, String order, String friendOrder) {
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(KakaoAPI.KAKAO_FRIEND_PATH);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

        WebClient client = WebClient.builder()
            .defaultHeaders(httpHeaders -> {
                httpHeaders.add("Authorization", String.format("%s %s", "Bearer", accessToken));
            })
            .uriBuilderFactory(factory)
            .baseUrl(KakaoAPI.KAKAO_FRIEND_PATH)
            .build();

        Mono<KakaoFriendResponse> result = client.get()
            .uri(uriBuilder -> uriBuilder
                .path("/")
                .queryParam("offset", offset)
                .queryParam("limit", limit)
                .queryParam("order", order)
                .queryParam("friend_order", friendOrder)
                .build()
            )
            .retrieve()
            .bodyToMono(KakaoFriendResponse.class);

        return result;

    }
}
