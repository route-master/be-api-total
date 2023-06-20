package org.routemaster.api.total.domain.attraction.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.domain.attraction.service.AttractionInformationSearchService;
import org.routemaster.api.total.infra.tourapi.vo.AttractionSearchVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/attraction-detail")
@RequiredArgsConstructor
public class CommonDetailInformationSearchRestController {

    private final AttractionInformationSearchService service;

    @GetMapping("/common")
    public ResponseEntity<Mono<AttractionSearchVO>> detailCommon(
            @RequestParam Integer contentId
    ) {
        Mono<AttractionSearchVO> result = service.searchAttractionDetail(
                contentId
        );
        return ResponseEntity.ok(result);
    }
}
