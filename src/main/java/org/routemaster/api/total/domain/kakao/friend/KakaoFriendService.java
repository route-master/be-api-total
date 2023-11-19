package org.routemaster.api.total.domain.kakao.friend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.domain.kakao.KakaoAPI;
import org.routemaster.api.total.domain.kakao.friend.data.KakaoFriendResponse;
import org.springframework.http.MediaType;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
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

        ObjectMapper newMapper = new ObjectMapper();
        newMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
            .codecs(configurer ->
                configurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(newMapper)))
            .build();

        WebClient client = WebClient.builder()
            .defaultHeaders(httpHeaders -> {
                httpHeaders.add("Authorization", String.format("%s %s", "Bearer", accessToken));
            })
            .uriBuilderFactory(factory)
            .baseUrl(KakaoAPI.KAKAO_FRIEND_PATH)
            .exchangeStrategies(exchangeStrategies)
            .build();

        Mono<KakaoFriendResponse> result = client.get()
            .uri(uriBuilder -> uriBuilder
                .queryParam("offset", offset == null ? 0 : offset)
                .queryParam("limit", limit == null ? 10 : limit)
                .queryParam("order", order == null ? "asc" : order)
                .queryParam("friend_order", friendOrder == null ? "favorite" : friendOrder)
                .build()
            )
            .retrieve()
            .bodyToMono(KakaoFriendResponse.class);

        return result;

    }
}
