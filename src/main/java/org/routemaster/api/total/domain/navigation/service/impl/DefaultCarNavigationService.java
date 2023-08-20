package org.routemaster.api.total.domain.navigation.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.domain.navigation.data.CarNavigationRoutes;
import org.routemaster.api.total.domain.navigation.service.CarNavigationService;
import org.routemaster.api.total.infra.kakao.KakaoMobilityAPI;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Slf4j
@Service
public class DefaultCarNavigationService implements CarNavigationService {

    @Override
    public Mono<CarNavigationRoutes> navigate(
            Double originLatitude,
            Double originLongitude,
            Double destinationLatitude,
            Double destinationLogitude,
            String priority,
            String avoid,
            Boolean alternatives,
            Boolean roadDetails,
            Integer carType,
            String carFuel,
            Boolean carHipass,
            Boolean summary
    ) {
        ObjectMapper mapper = new ObjectMapper();
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(KakaoMobilityAPI.baseUrl);

        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);
        WebClient webClient = WebClient.builder()
                .uriBuilderFactory(factory)
                .baseUrl(KakaoMobilityAPI.baseUrl)
                .defaultHeader("Authorization", KakaoMobilityAPI.authorizationKey)
                .build();

        Mono<CarNavigationRoutes> result = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/directions")
                        .queryParam("origin", originLatitude + "," + originLongitude)
                        .queryParam("destination", destinationLatitude + "," + destinationLogitude)
                        .queryParam("priority", priority == null ? "RECOMMEND" : priority)
                        .queryParam("avoid", avoid)
                        .queryParam("alternatives", alternatives == null ? false : alternatives)
                        .queryParam("road_details", roadDetails == null ? false : roadDetails)
                        .queryParam("car_type", carType == null ? 1 : carType)
                        .queryParam("car_fuel", carFuel == null ? "GASOLINE" : carFuel)
                        .queryParam("car_hipass", carHipass == null ? false : carHipass)
                        .queryParam("summary", summary == null ? false : summary)
                        .build()
                        )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .map(str -> {
                    try {
                        JsonNode jsonNode = mapper.readTree(str);
                        CarNavigationRoutes navigationRoutes = CarNavigationRoutes.builder()
                                .transId(jsonNode.get("trans_id").asText())
                                .buildRoutes(jsonNode.get("routes"))
                                .build();
                        return navigationRoutes;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
        return result;
    }
}
