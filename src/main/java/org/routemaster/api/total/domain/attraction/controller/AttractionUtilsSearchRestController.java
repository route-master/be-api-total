package org.routemaster.api.total.domain.attraction.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.domain.attraction.data.utils.CategorySearchResponse;
import org.routemaster.api.total.domain.attraction.service.AttractionUtilService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/attraction/search/category")
@RequiredArgsConstructor
public class AttractionUtilsSearchRestController {

    private final AttractionUtilService service;

    @GetMapping
    public ResponseEntity<Mono<CategorySearchResponse>> searchCategory(
            @RequestParam(required = false) Integer numOfRows,
            @RequestParam(required = false) Integer pageNo,
            @RequestParam(required = false) Integer contentTypeId,
            @RequestParam(required = false) String largeCategory,
            @RequestParam(required = false) String mediumCategory,
            @RequestParam(required = false) String smallCategory
    ) {
        Mono<CategorySearchResponse> response = service.searchCategory(numOfRows, pageNo, contentTypeId, largeCategory, mediumCategory, smallCategory);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
