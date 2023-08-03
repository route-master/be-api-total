package org.routemaster.api.total.domain.weather.service.Impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.domain.weather.data.ShortForecastWeather;
import org.routemaster.api.total.domain.weather.data.VeryShortForecastWeather;
import org.routemaster.api.total.domain.weather.data.VeryShortLiveWeather;
import org.routemaster.api.total.domain.weather.service.WeatherService;
import org.routemaster.api.total.domain.weather.utils.GpsTransfer;
import org.routemaster.api.total.infra.weatherapi.value.WeatherAPI;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultWeatherService implements WeatherService {

    private final String DATA_TYPE = "JSON";
    private final String NUM_OF_ROWS = "1000";
    private final String PAGE_NO = "1";
    private final ObjectMapper mapper;
    private final GpsTransfer gpsTransfer;

    @Override
    public Mono<VeryShortLiveWeather> getVeryShortLiveWeather(String baseDate, Integer baseTime, Double latitude, Double longitude) {
        gpsTransfer.setLat(latitude);
        gpsTransfer.setLng(longitude);
        gpsTransfer.transfer(gpsTransfer);

        final DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(WeatherAPI.baseUrl);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

        WebClient webClient = WebClient.builder()
                .uriBuilderFactory(factory)
                .baseUrl(WeatherAPI.baseUrl)
                .build();

        Mono<VeryShortLiveWeather> result = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/getUltraSrtNcst")
                        .queryParam("serviceKey", WeatherAPI.encodingKey)
                        .queryParam("numOfRows", NUM_OF_ROWS)
                        .queryParam("pageNo", PAGE_NO)
                        .queryParam("dataType", DATA_TYPE)
                        .queryParam("base_date", baseDate)
                        .queryParam("base_time", String.format("%02d", baseTime) + "00")
                        .queryParam("nx", gpsTransfer.getNx())
                        .queryParam("ny", gpsTransfer.getNy())
                        .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .map(str -> {
                    ObjectMapper objectMapper = new ObjectMapper();
                    try {
                        JsonNode jsonNode = mapper.readTree(str);
                        VeryShortLiveWeather vo = VeryShortLiveWeather.builder()
                                .buildFromJsonNode(jsonNode)
                                .build();
                        return vo;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                });
        return result;
    }

    @Override
    public Mono<VeryShortForecastWeather> getVeryShortForecastWeather(String baseDate, String baseTime, Double latitude, Double longitude) {
        gpsTransfer.setLat(latitude);
        gpsTransfer.setLng(longitude);
        gpsTransfer.transfer(gpsTransfer);

        final DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(WeatherAPI.baseUrl);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

        WebClient webClient = WebClient.builder()
                .uriBuilderFactory(factory)
                .baseUrl(WeatherAPI.baseUrl)
                .build();

        Mono<VeryShortForecastWeather> result = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/getUltraSrtFcst")
                        .queryParam("serviceKey", WeatherAPI.encodingKey)
                        .queryParam("numOfRows", NUM_OF_ROWS)
                        .queryParam("pageNo", PAGE_NO)
                        .queryParam("dataType", DATA_TYPE)
                        .queryParam("base_date", baseDate)
                        .queryParam("base_time", baseTime)
                        .queryParam("nx", gpsTransfer.getNx())
                        .queryParam("ny", gpsTransfer.getNy())
                        .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .map(str -> {
                    ObjectMapper objectMapper = new ObjectMapper();
                    try {
                        JsonNode jsonNode = mapper.readTree(str);
                        VeryShortForecastWeather vo = VeryShortForecastWeather.builder()
                                .buildFromJsonNode(jsonNode)
                                .build();
                        return vo;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
        return result;
    }

    @Override
    public Mono<ShortForecastWeather> getShortForecastWeather(String baseDate, String baseTime, Double latitude, Double longitude) {
        gpsTransfer.setLat(latitude);
        gpsTransfer.setLng(longitude);
        gpsTransfer.transfer(gpsTransfer);

        final DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(WeatherAPI.baseUrl);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

        WebClient webClient = WebClient.builder()
                .uriBuilderFactory(factory)
                .baseUrl(WeatherAPI.baseUrl)
                .build();

        Mono<ShortForecastWeather> result = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/getVilageFcst")
                        .queryParam("serviceKey", WeatherAPI.encodingKey)
                        .queryParam("numOfRows", NUM_OF_ROWS)
                        .queryParam("pageNo", PAGE_NO)
                        .queryParam("dataType", DATA_TYPE)
                        .queryParam("base_date", baseDate)
                        .queryParam("base_time", baseTime)
                        .queryParam("nx", gpsTransfer.getNx())
                        .queryParam("ny", gpsTransfer.getNy())
                        .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .map(str -> {
                    ObjectMapper objectMapper = new ObjectMapper();
                    try {
                        JsonNode jsonNode = mapper.readTree(str);
                        ShortForecastWeather vo = ShortForecastWeather.builder()
                                .buildFromJsonNode(jsonNode)
                                .build();
                        return vo;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
        return result;
    }
}
