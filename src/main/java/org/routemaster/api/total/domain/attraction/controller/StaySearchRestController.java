package org.routemaster.api.total.domain.attraction.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.domain.attraction.service.AttractionSearchService;
import org.routemaster.api.total.infra.tourapi.vo.AttractionSearchVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/attraction-search")
@RequiredArgsConstructor
public class StaySearchRestController {

    private final AttractionSearchService attractionSearchService;

    @GetMapping("/stay")
    public ResponseEntity<Mono<AttractionSearchVO>> stay(
            @RequestParam(required = false) Integer numOfRows,
            @RequestParam(required = false) Integer pageNo,
            @RequestParam(required = false) String arrange,
            @RequestParam(required = false) Integer areaCode,
            @RequestParam(required = false) Integer sigunguCode,
            @RequestParam(required = false) String modifiedTime
    ) {
        Mono<AttractionSearchVO> result = attractionSearchService.searchStay(
                numOfRows,
                pageNo,
                arrange,
                areaCode,
                sigunguCode,
                modifiedTime
        );
        return ResponseEntity.ok(result);
    }

}
