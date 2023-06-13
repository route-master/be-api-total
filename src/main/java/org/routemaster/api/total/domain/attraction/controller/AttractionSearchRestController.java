package org.routemaster.api.total.domain.attraction.controller;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.domain.attraction.service.AttractionSearchService;
import org.springframework.http.HttpStatus;
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
public class AttractionSearchRestController {

    private final AttractionSearchService attractionSearchService;

    @GetMapping("/location-based")
    public ResponseEntity<Mono<JsonNode>> locationBase(
            @RequestParam(required = false) Integer numOfRows,
            @RequestParam(required = false) Integer pageNo,
            @RequestParam String MobileOS,
            @RequestParam String MobileApp,
            @RequestParam(required = false) String _type,
            @RequestParam(required = false) String listYN,
            @RequestParam(required = false) String arrange,
            @RequestParam Double mapX,
            @RequestParam Double mapY,
            @RequestParam Integer radius,
            @RequestParam(required = false) Integer contentTypeId,
            @RequestParam(required = false) String modifiedTime
    ) {
        Mono<JsonNode> result = attractionSearchService.searchLocationBasedAttraction(
                numOfRows,
                pageNo,
                MobileOS,
                MobileApp,
                _type,
                listYN,
                arrange,
                mapX,
                mapY,
                radius,
                contentTypeId,
                modifiedTime
        );
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
