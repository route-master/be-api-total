package org.routemaster.api.total.domain.weather.controller;

import lombok.RequiredArgsConstructor;
import org.routemaster.api.total.domain.weather.data.VeryShortLiveWeather;
import org.routemaster.api.total.domain.weather.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
public class WeatherRestController {

    private final WeatherService weatherService;

    @GetMapping("/very-short-live")
    public ResponseEntity<Mono<VeryShortLiveWeather>> getVeryShortLiveWeather(
            @RequestParam String baseDate,
            @RequestParam String baseTime,
            @RequestParam Double latitude,
            @RequestParam Double longitude
    ) {
        Mono<VeryShortLiveWeather> result = weatherService.getVeryShortLiveWeather(
                baseDate, baseTime, latitude, longitude);
        return ResponseEntity.ok(result);
    }


}
