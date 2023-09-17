package org.routemaster.api.total.domain.kakao.message;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.domain.kakao.KakaoAPI;
import org.routemaster.api.total.domain.kakao.message.data.basic.BasicTemplateMessageRequest;
import org.routemaster.api.total.domain.kakao.message.data.basic.BasicTemplateMessageResponse;
import org.routemaster.api.total.domain.kakao.message.data.template.TextTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.core.publisher.Mono;


@Service
@Slf4j
@RequiredArgsConstructor
public class KakaoMessageService {

    public Mono<BasicTemplateMessageResponse> sendTextTemplate(String accessToken, List<String> receiverUuids, TextTemplate templateObject) {
        BasicTemplateMessageRequest body = BasicTemplateMessageRequest.builder()
            .receiverUuids(receiverUuids)
            .templateObject(templateObject)
            .build();

        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(KakaoAPI.KAKAO_FRIEND_SEND_PATH);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

        WebClient client = WebClient.builder()
            .defaultHeaders(httpHeaders -> {
                httpHeaders.add("Authorization", String.format("%s %s", "Bearer", accessToken));
            })
            .uriBuilderFactory(factory)
            .baseUrl(KakaoAPI.KAKAO_FRIEND_SEND_PATH)
            .build();

        Mono<BasicTemplateMessageResponse> result = client.post()
            .uri(uriBuilder -> uriBuilder
                .path("/")
                .build()
            )
            .body(body, BasicTemplateMessageRequest.class)
            .retrieve()
            .bodyToMono(BasicTemplateMessageResponse.class);

        return result;
    }
}
