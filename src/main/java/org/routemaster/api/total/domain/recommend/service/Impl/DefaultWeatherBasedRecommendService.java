package org.routemaster.api.total.domain.recommend.service.Impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.domain.recommend.data.AreaCodeMapping;
import org.routemaster.api.total.domain.recommend.data.TourismClimateIndexItem;
import org.routemaster.api.total.domain.recommend.repository.AreaCodeMappingRepository;
import org.routemaster.api.total.domain.recommend.service.WeatherBasedRecommendService;
import org.routemaster.api.total.infra.weatherapi.value.WeatherAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultWeatherBasedRecommendService implements WeatherBasedRecommendService {

    private final String DATA_TYPE = "JSON";
    private final String NUM_OF_ROWS = "1000";
    private final String PAGE_NO = "1";
    private final ObjectMapper mapper;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    private final String currentDate = LocalDate.now(ZoneId.of("Asia/Seoul")).format(formatter).toString();
    @Autowired
    private final AreaCodeMappingRepository areaCodeMappingRepository;

    @Override
    public Mono<Integer> getKntoAreaCode(String kmaRegionCode) {;
        return areaCodeMappingRepository.findByKmaRegionCode(kmaRegionCode).map(AreaCodeMapping::getKntoAreaCode);
    }

    @Override
    public Mono<Integer> getKntoSigunguCode(String kmaRegionCode) {
        return areaCodeMappingRepository.findByKmaRegionCode(kmaRegionCode).map(AreaCodeMapping::getKntoSigunguCode);
    }

    @Override
    public Mono<List<TourismClimateIndexItem>> getTourismClimateIndex(String date, String day, String cityAreaId) {

        final DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(WeatherAPI.baseUrl + "/TourStnInfoService1");
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

        WebClient webClient = WebClient.builder()
                .uriBuilderFactory(factory)
                .baseUrl(WeatherAPI.baseUrl)
                .build();

        log.info("{}", webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/getCityTourClmIdx1")
                        .queryParam("serviceKey", WeatherAPI.encodingKey)
                        .queryParam("numOfRows", NUM_OF_ROWS)
                        .queryParam("pageNo", PAGE_NO)
                        .queryParam("dataType", DATA_TYPE)
                        .queryParam("CURRENT_DATE", date == null ? currentDate : date)
                        .queryParam("DAY", day == null ? "0" : day)
                        .queryParam("CITY_AREA_ID", cityAreaId)
                        .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve());

        Mono<List<TourismClimateIndexItem>> result = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/getCityTourClmIdx1")
                        .queryParam("serviceKey", WeatherAPI.encodingKey)
                        .queryParam("numOfRows", NUM_OF_ROWS)
                        .queryParam("pageNo", PAGE_NO)
                        .queryParam("dataType", DATA_TYPE)
                        .queryParam("CURRENT_DATE", date == null ? currentDate : date)
                        .queryParam("DAY", day == null ? "0" : day)
                        .queryParam("CITY_AREA_ID", cityAreaId)
                        .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .map(str -> {
                    try {
                        List<TourismClimateIndexItem> tourismClimateIndexItemList = new ArrayList<>();
                        JsonNode jsonNode = mapper.readTree(str);
                        jsonNode = jsonNode.get("response").get("body").get("items").get("item");
                        for (JsonNode node : jsonNode) {
                            String kmaRegionCode = node.get("cityAreaId").asText();
                            Mono<Integer> kntoAreaCode = getKntoAreaCode(kmaRegionCode);
                            TourismClimateIndexItem vo = TourismClimateIndexItem.builder()
                                    .buildFromJsonNode(node)
                                    .build();
                            tourismClimateIndexItemList.add(vo);
                        }
                        tourismClimateIndexItemList.sort(Comparator.comparing(TourismClimateIndexItem::getKmaTci)
                                .reversed()
                                .thenComparing(TourismClimateIndexItem::getCityAreaId)
                        );
                        return tourismClimateIndexItemList;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
        return result;
    }
}
