package org.routemaster.api.total.domain.recommend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.domain.attraction.data.search.AttractionSearchVO;
import org.routemaster.api.total.domain.attraction.service.AttractionSearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/recommend/age-based")
@RequiredArgsConstructor
public class AgeBasedRecommendRestController {

    private final AttractionSearchService attractionSearchService;

    @GetMapping
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

        log.info("recommendAreaCode: {}", recommendAreaCode);
        log.info("recommendContentTypeId: {}", recommendContentTypeId);
        log.info("recommendLargeCategory: {}", recommendLargeCategory);
        log.info("recommendMediumCategory: {}", recommendMediumCategory);
        log.info("recommendSmallCategory: {}", recommendSmallCategory);

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
