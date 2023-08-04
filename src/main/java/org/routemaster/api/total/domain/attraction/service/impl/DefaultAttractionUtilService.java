package org.routemaster.api.total.domain.attraction.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.domain.attraction.data.utils.CategorySearchResponse;
import org.routemaster.api.total.domain.attraction.service.AttractionUtilService;
import org.routemaster.api.total.infra.tourapi.value.TourAPI;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultAttractionUtilService implements AttractionUtilService {

    private final String MOBILEOS = "ETC";
    private final String MOBILEAPP = "TimeMap";
    private final String TYPE = "json";
    private final ObjectMapper mapper;

    @Override
    public Mono<CategorySearchResponse> searchCategory(
            Integer numOfRows,
            Integer pageNo,
            Integer contentTypeId,
            String largeCategory,
            String mediumCategory,
            String smallCategory) {
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(TourAPI.baseUrl);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);
        WebClient webClient = WebClient.builder()
                .uriBuilderFactory(factory)
                .baseUrl(TourAPI.baseUrl)
                .build();

        Mono<CategorySearchResponse> result = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/categoryCode1")
                        .queryParam("ServiceKey", TourAPI.encodingKey)
                        .queryParam("MobileOS", MOBILEOS)
                        .queryParam("MobileApp", MOBILEAPP)
                        .queryParam("_type", TYPE)
                        .queryParam("numOfRows", numOfRows)
                        .queryParam("pageNo", pageNo)
                        .queryParam("contentTypeId", contentTypeId)
                        .queryParam("cat1", largeCategory)
                        .queryParam("cat2", mediumCategory)
                        .queryParam("cat3", smallCategory)
                        .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .map(str -> {
                    JsonNode jsonNode = null;
                    try {
                        jsonNode = mapper.readTree(str);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                    CategorySearchResponse response = CategorySearchResponse.builder()
                            .buildFromJsonNode(jsonNode)
                            .build();
                    return response;
                });
        return result;
    }
}
