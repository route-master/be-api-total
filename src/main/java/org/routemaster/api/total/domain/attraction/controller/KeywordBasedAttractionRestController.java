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
public class KeywordBasedAttractionRestController {

    private final AttractionSearchService attractionSearchService;

    @GetMapping("/keyword-based")
    public ResponseEntity<Mono<AttractionSearchVO>> keywordBased(
            @RequestParam(required = false) Integer numOfRows,
            @RequestParam(required = false) Integer pageNo,
            @RequestParam(required = false) String arrange,
            @RequestParam String keyword,
            @RequestParam(required = false) Integer contentTypeId,
            @RequestParam(required = false) String areaCode,
            @RequestParam(required = false) String sigunguCode,
            @RequestParam(required = false) String cat1,
            @RequestParam(required = false) String cat2,
            @RequestParam(required = false) String cat3
    ) {
        Mono<AttractionSearchVO> result = attractionSearchService.searchKeywordBasedAttraction(
                numOfRows,
                pageNo,
                arrange,
                keyword,
                contentTypeId,
                areaCode,
                sigunguCode,
                cat1,
                cat2,
                cat3
        );
        return ResponseEntity.ok(result);
    }

}
