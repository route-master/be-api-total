package org.routemaster.api.total.domain.attraction.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.domain.attraction.service.AttractionSearchService;
import org.routemaster.api.total.infra.tourapi.value.TourAPI;
import org.routemaster.api.total.infra.tourapi.vo.AttractionSearchVO;
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

    @Override
    public Mono<AttractionSearchVO> searchLocationBasedAttraction(
            Integer numOfRows,
            Integer pageNo,
            String MobileOS,
            String MobileApp,
            String _type,
            String listYN,
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
                        .queryParam("MobileOS", MobileOS)
                        .queryParam("MobileApp", MobileApp)
                        .queryParam("mapX", mapX)
                        .queryParam("mapY", mapY)
                        .queryParam("radius", radius)
                        .queryParam("_type", "json")
                        .queryParam("numOfRows", numOfRows)
                        .queryParam("pageNo", pageNo)
                        .queryParam("listYN", listYN)
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
            String MobileOS,
            String MobileApp,
            String _type,
            String listYN,
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
                        .queryParam("MobileOS", MobileOS)
                        .queryParam("MobileApp", MobileApp)
                        .queryParam("_type", "json")
                        .queryParam("numOfRows", numOfRows)
                        .queryParam("pageNo", pageNo)
                        .queryParam("listYN", listYN)
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
            String MobileOS,
            String MobileApp,
            String _type,
            String listYN,
            String arrange,
            String keyword,
            Integer contentTypeId,
            String areaCode,
            String sigunguCode,
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
                        .queryParam("MobileOS", MobileOS)
                        .queryParam("MobileApp", MobileApp)
                        .queryParam("_type", "json")
                        .queryParam("numOfRows", numOfRows)
                        .queryParam("pageNo", pageNo)
                        .queryParam("listYN", listYN)
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
}
