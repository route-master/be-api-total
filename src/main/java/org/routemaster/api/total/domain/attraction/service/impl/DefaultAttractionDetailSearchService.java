package org.routemaster.api.total.domain.attraction.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.domain.attraction.service.AttractionDetailSearchService;
import org.routemaster.api.total.infra.tourapi.value.TourAPI;
import org.routemaster.api.total.infra.tourapi.vo.AttractionSearchVO;
import org.routemaster.api.total.infra.tourapi.vo.detail.*;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Slf4j
@Service
public class DefaultAttractionDetailSearchService implements AttractionDetailSearchService {

    private final String MOBILEOS = "ETC";
    private final String MOBILEAPP = "TimeMap";
    private final String TYPE = "json";

    @Override
    public Mono<AttractionSearchVO> searchAttractionCommonDetail(
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
                        AttractionSearchVO vo = AttractionSearchVO.builder()
                                .resultCode(jsonNode.get("response").get("header").get("resultCode").asText())
                                .resultMessage(jsonNode.get("response").get("header").get("resultMsg").asText())
                                .commonDetailItems(jsonNode.get("response").get("body"))
                                .build();
                        return vo;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
        return result;
    }

    public Mono<TourAttractionDetailVO> searchTourAttractionDetail(Integer contentId) {
        ObjectMapper mapper = new ObjectMapper();

        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(TourAPI.baseUrl);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

        WebClient webClient = WebClient.builder()
                .uriBuilderFactory(factory)
                .baseUrl(TourAPI.baseUrl)
                .build();

        Mono<TourAttractionDetailVO> result = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/detailIntro1")
                        .queryParam("serviceKey", TourAPI.encodingKey)
                        .queryParam("MobileOS", MOBILEOS)
                        .queryParam("MobileApp", MOBILEAPP)
                        .queryParam("_type", TYPE)
                        .queryParam("contentId", contentId)
                        .queryParam("contentTypeId", "12")
                        .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .map(str -> {
                    ObjectMapper objectMapper = new ObjectMapper();
                    try {
                        JsonNode jsonNode = mapper.readTree(str);
                        TourAttractionDetailVO vo = TourAttractionDetailVO.builder()
                                .builder(jsonNode.get("response").get("body"))
                                .resultCode(jsonNode.get("response").get("header").get("resultCode").asText())
                                .resultMessage(jsonNode.get("response").get("header").get("resultMsg").asText())
                                .numOfRows(jsonNode.get("response").get("body").get("numOfRows").asInt())
                                .pageNo(jsonNode.get("response").get("body").get("pageNo").asInt())
                                .totalCount(jsonNode.get("response").get("body").get("totalCount").asInt())
                                .build();
                        return vo;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
        return result;
    }

    @Override
    public Mono<CultureAttractionDetailVO> searchCultureAttractionDetail(Integer contentId) {
        ObjectMapper mapper = new ObjectMapper();

        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(TourAPI.baseUrl);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

        WebClient webClient = WebClient.builder()
                .uriBuilderFactory(factory)
                .baseUrl(TourAPI.baseUrl)
                .build();

        Mono<CultureAttractionDetailVO> result = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/detailIntro1")
                        .queryParam("serviceKey", TourAPI.encodingKey)
                        .queryParam("MobileOS", MOBILEOS)
                        .queryParam("MobileApp", MOBILEAPP)
                        .queryParam("_type", TYPE)
                        .queryParam("contentId", contentId)
                        .queryParam("contentTypeId", "14")
                        .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .map(str -> {
                    try {
                        JsonNode jsonNode = mapper.readTree(str);
                        CultureAttractionDetailVO vo = CultureAttractionDetailVO.builder()
                                .builder(jsonNode.get("response").get("body"))
                                .resultCode(jsonNode.get("response").get("header").get("resultCode").asText())
                                .resultMessage(jsonNode.get("response").get("header").get("resultMsg").asText())
                                .numOfRows(jsonNode.get("response").get("body").get("numOfRows").asInt())
                                .pageNo(jsonNode.get("response").get("body").get("pageNo").asInt())
                                .totalCount(jsonNode.get("response").get("body").get("totalCount").asInt())
                                .build();
                        return vo;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
        return result;
    }

    @Override
    public Mono<LeportsAttractionDetailVO> searchLeportsAttractionDetail(Integer contentId) {
        ObjectMapper mapper = new ObjectMapper();

        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(TourAPI.baseUrl);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

        WebClient webClient = WebClient.builder()
                .uriBuilderFactory(factory)
                .baseUrl(TourAPI.baseUrl)
                .build();

        Mono<LeportsAttractionDetailVO> result = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/detailIntro1")
                        .queryParam("serviceKey", TourAPI.encodingKey)
                        .queryParam("MobileOS", MOBILEOS)
                        .queryParam("MobileApp", MOBILEAPP)
                        .queryParam("_type", TYPE)
                        .queryParam("contentId", contentId)
                        .queryParam("contentTypeId", "28")
                        .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .map(str -> {
                    try {
                        JsonNode jsonNode = mapper.readTree(str);
                        LeportsAttractionDetailVO vo = LeportsAttractionDetailVO.builder()
                                .builder(jsonNode.get("response").get("body"))
                                .resultCode(jsonNode.get("response").get("header").get("resultCode").asText())
                                .resultMessage(jsonNode.get("response").get("header").get("resultMsg").asText())
                                .numOfRows(jsonNode.get("response").get("body").get("numOfRows").asInt())
                                .pageNo(jsonNode.get("response").get("body").get("pageNo").asInt())
                                .totalCount(jsonNode.get("response").get("body").get("totalCount").asInt())
                                .build();
                        return vo;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
        return result;
    }

    @Override
    public Mono<FestivalAttractionDetailVO> searchFestivalAttractionDetail(Integer contentId) {

        ObjectMapper mapper = new ObjectMapper();

        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(TourAPI.baseUrl);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

        WebClient webClient = WebClient.builder()
                .uriBuilderFactory(factory)
                .baseUrl(TourAPI.baseUrl)
                .build();

        Mono<FestivalAttractionDetailVO> result = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/detailIntro1")
                        .queryParam("serviceKey", TourAPI.encodingKey)
                        .queryParam("MobileOS", MOBILEOS)
                        .queryParam("MobileApp", MOBILEAPP)
                        .queryParam("_type", TYPE)
                        .queryParam("contentId", contentId)
                        .queryParam("contentTypeId", "15")
                        .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .map(str -> {
                    try {
                        JsonNode jsonNode = mapper.readTree(str);
                        FestivalAttractionDetailVO vo = FestivalAttractionDetailVO.builder()
                                .builder(jsonNode.get("response").get("body"))
                                .resultCode(jsonNode.get("response").get("header").get("resultCode").asText())
                                .resultMessage(jsonNode.get("response").get("header").get("resultMsg").asText())
                                .numOfRows(jsonNode.get("response").get("body").get("numOfRows").asInt())
                                .pageNo(jsonNode.get("response").get("body").get("pageNo").asInt())
                                .totalCount(jsonNode.get("response").get("body").get("totalCount").asInt())
                                .build();
                        return vo;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
        return result;
    }

    @Override
    public Mono<RestaurantAttractionDetailVO> searchRestaurantAttractionDetail(Integer contentId) {

        ObjectMapper mapper = new ObjectMapper();

        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(TourAPI.baseUrl);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

        WebClient webClient = WebClient.builder()
                .uriBuilderFactory(factory)
                .baseUrl(TourAPI.baseUrl)
                .build();

        Mono<RestaurantAttractionDetailVO> result = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/detailIntro1")
                        .queryParam("serviceKey", TourAPI.encodingKey)
                        .queryParam("MobileOS", MOBILEOS)
                        .queryParam("MobileApp", MOBILEAPP)
                        .queryParam("_type", TYPE)
                        .queryParam("contentId", contentId)
                        .queryParam("contentTypeId", "39")
                        .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .map(str -> {
                    try {
                        JsonNode jsonNode = mapper.readTree(str);
                        RestaurantAttractionDetailVO vo = RestaurantAttractionDetailVO.builder()
                                .builder(jsonNode.get("response").get("body"))
                                .resultCode(jsonNode.get("response").get("header").get("resultCode").asText())
                                .resultMessage(jsonNode.get("response").get("header").get("resultMsg").asText())
                                .numOfRows(jsonNode.get("response").get("body").get("numOfRows").asInt())
                                .pageNo(jsonNode.get("response").get("body").get("pageNo").asInt())
                                .totalCount(jsonNode.get("response").get("body").get("totalCount").asInt())
                                .build();
                        return vo;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
        return result;
    }

    @Override
    public Mono<ShoppingAttractionDetailVO> searchShoppingAttractionDetail(Integer contentId) {
        ObjectMapper mapper = new ObjectMapper();

        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(TourAPI.baseUrl);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

        WebClient webClient = WebClient.builder()
                .uriBuilderFactory(factory)
                .baseUrl(TourAPI.baseUrl)
                .build();

        Mono<ShoppingAttractionDetailVO> result = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/detailIntro1")
                        .queryParam("serviceKey", TourAPI.encodingKey)
                        .queryParam("MobileOS", MOBILEOS)
                        .queryParam("MobileApp", MOBILEAPP)
                        .queryParam("_type", TYPE)
                        .queryParam("contentId", contentId)
                        .queryParam("contentTypeId", "38")
                        .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .map(str -> {
                    try {
                        JsonNode jsonNode = mapper.readTree(str);
                        ShoppingAttractionDetailVO vo = ShoppingAttractionDetailVO.builder()
                                .builder(jsonNode.get("response").get("body"))
                                .resultCode(jsonNode.get("response").get("header").get("resultCode").asText())
                                .resultMessage(jsonNode.get("response").get("header").get("resultMsg").asText())
                                .numOfRows(jsonNode.get("response").get("body").get("numOfRows").asInt())
                                .pageNo(jsonNode.get("response").get("body").get("pageNo").asInt())
                                .totalCount(jsonNode.get("response").get("body").get("totalCount").asInt())
                                .build();
                        return vo;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
        return result;
    }

    @Override
    public Mono<StayAttractionDetailVO> searchStayAttractionDetail(Integer contentId) {

        ObjectMapper mapper = new ObjectMapper();

        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(TourAPI.baseUrl);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

        WebClient webClient = WebClient.builder()
                .uriBuilderFactory(factory)
                .baseUrl(TourAPI.baseUrl)
                .build();

        Mono<StayAttractionDetailVO> result = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/detailIntro1")
                        .queryParam("serviceKey", TourAPI.encodingKey)
                        .queryParam("MobileOS", MOBILEOS)
                        .queryParam("MobileApp", MOBILEAPP)
                        .queryParam("_type", TYPE)
                        .queryParam("contentId", contentId)
                        .queryParam("contentTypeId", "32")
                        .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .map(str -> {
                    try {
                        JsonNode jsonNode = mapper.readTree(str);
                        StayAttractionDetailVO vo = StayAttractionDetailVO.builder()
                                .builder(jsonNode.get("response").get("body"))
                                .resultCode(jsonNode.get("response").get("header").get("resultCode").asText())
                                .resultMessage(jsonNode.get("response").get("header").get("resultMsg").asText())
                                .numOfRows(jsonNode.get("response").get("body").get("numOfRows").asInt())
                                .pageNo(jsonNode.get("response").get("body").get("pageNo").asInt())
                                .totalCount(jsonNode.get("response").get("body").get("totalCount").asInt())
                                .build();
                        return vo;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
        return result;
    }

    @Override
    public Mono<CourseAttractionDetailVO> searchCourseAttractionDetail(Integer contentId) {
        ObjectMapper mapper = new ObjectMapper();

        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(TourAPI.baseUrl);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

        WebClient webClient = WebClient.builder()
                .uriBuilderFactory(factory)
                .baseUrl(TourAPI.baseUrl)
                .build();

        Mono<CourseAttractionDetailVO> result = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/detailIntro1")
                        .queryParam("serviceKey", TourAPI.encodingKey)
                        .queryParam("MobileOS", MOBILEOS)
                        .queryParam("MobileApp", MOBILEAPP)
                        .queryParam("_type", TYPE)
                        .queryParam("contentId", contentId)
                        .queryParam("contentTypeId", "25")
                        .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .map(str -> {
                    try {
                        JsonNode jsonNode = mapper.readTree(str);
                        CourseAttractionDetailVO vo = CourseAttractionDetailVO.builder()
                                .builder(jsonNode.get("response").get("body"))
                                .resultCode(jsonNode.get("response").get("header").get("resultCode").asText())
                                .resultMessage(jsonNode.get("response").get("header").get("resultMsg").asText())
                                .numOfRows(jsonNode.get("response").get("body").get("numOfRows").asInt())
                                .pageNo(jsonNode.get("response").get("body").get("pageNo").asInt())
                                .totalCount(jsonNode.get("response").get("body").get("totalCount").asInt())
                                .build();
                        return vo;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
        return result;
    }
}
