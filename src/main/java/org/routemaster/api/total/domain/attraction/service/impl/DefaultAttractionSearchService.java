package org.routemaster.api.total.domain.attraction.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.domain.attraction.service.AttractionSearchService;
import org.routemaster.api.total.infra.tourapi.value.TourAPI;
import org.routemaster.api.total.domain.attraction.data.search.AttractionSearchVO;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


@Slf4j
@Service
public class DefaultAttractionSearchService implements AttractionSearchService {

    private final String MOBILEOS = "ETC";
    private final String MOBILEAPP = "TimeMap";
    private final String TYPE = "json";
    private final String LISTNY = "Y";

    @Override
    public Mono<AttractionSearchVO> searchLocationBasedAttraction(
            Integer numOfRows,
            Integer pageNo,
            String arrange,
            Double mapX,
            Double mapY,
            Integer radius,
            Integer contentTypeId,
            String modifiedtime
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
                        .path("/locationBasedList1")
                        .queryParam("serviceKey", TourAPI.encodingKey)
                        .queryParam("MobileOS", MOBILEOS)
                        .queryParam("MobileApp", MOBILEAPP)
                        .queryParam("mapX", mapX)
                        .queryParam("mapY", mapY)
                        .queryParam("radius", radius)
                        .queryParam("_type", TYPE)
                        .queryParam("numOfRows", numOfRows)
                        .queryParam("pageNo", pageNo)
                        .queryParam("listYN", LISTNY)
                        .queryParam("arrange", arrange)
                        .queryParam("contentTypeId", contentTypeId)
                        .queryParam("modifiedtime", modifiedtime)
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
                                                .locationBasedItems(jsonNode.get("response").get("body"))
                                                .build();
                                        return attractionSearchVO;
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                        );
        return result;
    }

    @Override
    public Mono<AttractionSearchVO> searchAreaBasedAttraction(
            Integer numOfRows,
            Integer pageNo,
            String arrange,
            Integer contentTypeId,
            Integer areaCode,
            Integer sigunguCode,
            String cat1,
            String cat2,
            String cat3,
            String modifiedtime) {
        ObjectMapper mapper = new ObjectMapper();

        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(TourAPI.baseUrl);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

        WebClient webClient = WebClient.builder()
                .uriBuilderFactory(factory)
                .baseUrl(TourAPI.baseUrl)
                .build();

        Mono<AttractionSearchVO> result = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/areaBasedList1")
                        .queryParam("serviceKey", TourAPI.encodingKey)
                        .queryParam("MobileOS", MOBILEOS)
                        .queryParam("MobileApp", MOBILEAPP)
                        .queryParam("_type", TYPE)
                        .queryParam("numOfRows", numOfRows)
                        .queryParam("pageNo", pageNo)
                        .queryParam("listYN", LISTNY)
                        .queryParam("arrange", arrange)
                        .queryParam("contentTypeId", contentTypeId)
                        .queryParam("areaCode", areaCode)
                        .queryParam("sigunguCode", sigunguCode)
                        .queryParam("cat1", cat1)
                        .queryParam("cat2", cat2)
                        .queryParam("cat3", cat3)
                        .queryParam("modifiedtime", modifiedtime)
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
                                        .areaBasedItems(jsonNode.get("response").get("body"))
                                        .build();
                                return attractionSearchVO;
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                );
        return result;
    }

    @Override
    public Mono<AttractionSearchVO> searchKeywordBasedAttraction(
            Integer numOfRows,
            Integer pageNo,
            String arrange,
            String keyword,
            Integer contentTypeId,
            Integer areaCode,
            Integer sigunguCode,
            String cat1,
            String cat2,
            String cat3
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
                        .path("/searchKeyword1")
                        .queryParam("serviceKey", TourAPI.encodingKey)
                        .queryParam("MobileOS", MOBILEOS)
                        .queryParam("MobileApp", MOBILEAPP)
                        .queryParam("_type", "json")
                        .queryParam("numOfRows", numOfRows)
                        .queryParam("pageNo", pageNo)
                        .queryParam("listYN", LISTNY)
                        .queryParam("arrange", arrange)
                        .queryParam("keyword", URLEncoder.encode(keyword, StandardCharsets.UTF_8))
                        .queryParam("contentTypeId", contentTypeId)
                        .queryParam("areaCode", areaCode)
                        .queryParam("sigunguCode", sigunguCode)
                        .queryParam("cat1", cat1)
                        .queryParam("cat2", cat2)
                        .queryParam("cat3", cat3)
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
                                        .keywordBasedItems(jsonNode.get("response").get("body"))
                                        .build();
                                return attractionSearchVO;
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                );
        return result;
    }

    @Override
    public Mono<AttractionSearchVO> searchFestival(
            Integer numOfRows,
            Integer pageNo,
            String arrange,
            String eventStartDate,
            String eventEndDate,
            Integer areaCode,
            Integer sigunguCode,
            String modifiedTime
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
                        .path("/searchFestival1")
                        .queryParam("serviceKey", TourAPI.encodingKey)
                        .queryParam("MobileOS", MOBILEOS)
                        .queryParam("MobileApp", MOBILEAPP)
                        .queryParam("_type", TYPE)
                        .queryParam("numOfRows", numOfRows)
                        .queryParam("pageNo", pageNo)
                        .queryParam("listYN", LISTNY)
                        .queryParam("arrange", arrange)
                        .queryParam("eventStartDate", eventStartDate)
                        .queryParam("eventEndDate", eventEndDate)
                        .queryParam("areaCode", areaCode)
                        .queryParam("sigunguCode", sigunguCode)
                        .queryParam("modifiedtime", modifiedTime)
                        .build()
                ).accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .map(str -> {
                            ObjectMapper objectMapper = new ObjectMapper();
                            try {
                                JsonNode jsonNode = mapper.readTree(str);
                                AttractionSearchVO attractionSearchVO = AttractionSearchVO.builder()
                                        .resultCode(jsonNode.get("response").get("header").get("resultCode").asText())
                                        .resultMessage(jsonNode.get("response").get("header").get("resultMsg").asText())
                                        .festivalItems(jsonNode.get("response").get("body"))
                                        .build();
                                return attractionSearchVO;
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                );
        return result;
    }

    @Override
    public Mono<AttractionSearchVO> searchStay(
            Integer numOfRows,
            Integer pageNo,
            String arrange,
            Integer areaCode,
            Integer sigunguCode,
            String modifiedTime
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
                        .path("/searchStay1")
                        .queryParam("serviceKey", TourAPI.encodingKey)
                        .queryParam("MobileOS", MOBILEOS)
                        .queryParam("MobileApp", MOBILEAPP)
                        .queryParam("_type", TYPE)
                        .queryParam("numOfRows", numOfRows)
                        .queryParam("pageNo", pageNo)
                        .queryParam("listYN", LISTNY)
                        .queryParam("arrange", arrange)
                        .queryParam("areaCode", areaCode)
                        .queryParam("sigunguCode", sigunguCode)
                        .queryParam("modifiedtime", modifiedTime)
                        .build()
                ).accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .map(str -> {
                            ObjectMapper objectMapper = new ObjectMapper();
                            try {
                                JsonNode jsonNode = mapper.readTree(str);
                                AttractionSearchVO attractionSearchVO = AttractionSearchVO.builder()
                                        .resultCode(jsonNode.get("response").get("header").get("resultCode").asText())
                                        .resultMessage(jsonNode.get("response").get("header").get("resultMsg").asText())
                                        .stayItems(jsonNode.get("response").get("body"))
                                        .build();
                                return attractionSearchVO;
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                );
        return result;
    }
}
