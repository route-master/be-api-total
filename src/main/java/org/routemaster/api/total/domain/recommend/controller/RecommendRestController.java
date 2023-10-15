package org.routemaster.api.total.domain.recommend.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.routemaster.api.total.domain.attraction.data.search.AttractionSearchVO;
import org.routemaster.api.total.domain.attraction.service.AttractionSearchService;
import org.routemaster.api.total.domain.recommend.data.TourismClimateIndexItem;
import org.routemaster.api.total.domain.recommend.service.WeatherBasedRecommendService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/recommend")
@RequiredArgsConstructor
public class RecommendRestController {

    private final AttractionSearchService attractionSearchService;
    private final WeatherBasedRecommendService service;

    @GetMapping("/location-based")
    public Flux<AttractionSearchVO> locationBased(
        @RequestParam Double mapX,
        @RequestParam Double mapY
    ) {
        return attractionSearchService.recommendLocationBasedAttraction(mapX, mapY);
    }

    @GetMapping("/weather-based/tourism-climate-index")
    public ResponseEntity<Mono<List<TourismClimateIndexItem>>> getTourismClimateIndex(
        @RequestParam(required = false) String date,
        @RequestParam(required = false) String day,
        @RequestParam(required = false) String cityAreaId
    ) {
        return ResponseEntity.ok(service.getTourismClimateIndex(date, day, cityAreaId));
    }

    @GetMapping("/age-based")
    public ResponseEntity<Mono<AttractionSearchVO>> recommendAttractionByAge(
        @RequestParam Integer age
    ) {
        Integer recommendAreaCode = 1;
        Integer recommendContentTypeId = null;
        String recommendLargeCategory = null;
        String recommendMediumCategory = null;
        String recommendSmallCategory = null;

        if (age == 20) {
            recommendAreaCode = 1;
            recommendContentTypeId = 15;
        } else if (age == 30) {
            recommendContentTypeId = 14;
            recommendLargeCategory = "A02";
            recommendMediumCategory = "A0206";
        } else if (age == 40) {
            recommendContentTypeId = 38;
            recommendLargeCategory = "A04";
            recommendMediumCategory = "A0401";
            recommendSmallCategory = "A04010300";
        } else if (age == 50) {
            recommendAreaCode = 32;
            recommendLargeCategory = "A01";
        }

        return ResponseEntity.ok(
            attractionSearchService.searchAreaBasedAttraction(10,
                1,
                "C",
                recommendContentTypeId,
                recommendAreaCode,
                null,
                recommendLargeCategory,
                recommendMediumCategory,
                recommendSmallCategory,
                null)
        );
    }
}
