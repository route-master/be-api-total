package org.routemaster.api.total.domain.attraction.controller;

import lombok.RequiredArgsConstructor;
import org.routemaster.api.total.domain.attraction.data.search.AttractionSearchVO;
import org.routemaster.api.total.domain.attraction.service.AttractionSearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class AttractionRecommendRestController {

    private final AttractionSearchService attractionSearchService;

    @GetMapping("/attraction-recommend/location-based")
    public Flux<AttractionSearchVO> locationBasedRecommend(
        @RequestParam Double mapX,
        @RequestParam Double mapY) {
        return attractionSearchService.recommendLocationBasedAttraction(mapX, mapY);
    }
}
