package org.routemaster.api.total.domain.attraction.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.domain.attraction.service.AttractionInformationSearchService;
import org.routemaster.api.total.infra.tourapi.value.TourAPI;
import org.routemaster.api.total.infra.tourapi.vo.AttractionSearchVO;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Slf4j
@Service
public class DefaultAttractionInformationSearchService implements AttractionInformationSearchService {

    private final String MOBILEOS = "ETC";
    private final String MOBILEAPP = "TimeMap";
    private final String TYPE = "json";

    @Override
    public Mono<AttractionSearchVO> searchAttractionDetail(
            Integer contentId
    ) {
        ObjectMapper mapper = new ObjectMapper();

        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(TourAPI.baseUrl);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

        WebClient webClient = WebClient.builder()
                .uriBuilderFactory(factory)
                .baseUrl(TourAPI.baseUrl)
                .build();

        Mono<AttractionSearchVO> result = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/detailCommon1")
                        .queryParam("serviceKey", TourAPI.encodingKey)
                        .queryParam("MobileOS", MOBILEOS)
                        .queryParam("MobileApp", MOBILEAPP)
                        .queryParam("_type", TYPE)
                        .queryParam("contentId", contentId)
                        .queryParam("defaultYN", "Y")
                        .queryParam("firstImageYN", "Y")
                        .queryParam("areacodeYN", "Y")
                        .queryParam("catcodeYN", "Y")
                        .queryParam("addrinfoYN", "Y")
                        .queryParam("mapinfoYN", "Y")
                        .queryParam("overviewYN", "Y")
                        .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .map(str -> {
                    ObjectMapper objectMapper = new ObjectMapper();
                    try {
                        JsonNode jsonNode = mapper.readTree(str);
                        AttractionSearchVO attractionSearchVO = AttractionSearchVO.builder()
                                .resultCode(jsonNode.get("response").get("header").get("resultCode").asText())
                                .resultMessage(jsonNode.get("response").get("header").get("resultMsg").asText())
                                .commonDetailItems(jsonNode.get("response").get("body"))
                                .build();
                        return attractionSearchVO;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
        return result;
    }
}
