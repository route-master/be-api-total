package org.routemaster.api.total.domain.recommend.controller;

import lombok.RequiredArgsConstructor;
import org.routemaster.api.total.domain.recommend.data.TourismClimateIndexItem;
import org.routemaster.api.total.domain.recommend.service.WeatherBasedRecommendService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/recommend/weather-based")
@RequiredArgsConstructor
public class WeatherBasedRecommendRestController {

    private final WeatherBasedRecommendService service;

    @GetMapping("/tourism-climate-index")
    public ResponseEntity<Mono<List<TourismClimateIndexItem>>> getTourismClimateIndex(
            @RequestParam(required = false) String date,
            @RequestParam(required = false) String day,
            @RequestParam(required = false) String cityAreaId
    ) {
        return ResponseEntity.ok(service.getTourismClimateIndex(date, day, cityAreaId));
    }
}
