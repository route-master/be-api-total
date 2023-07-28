package org.routemaster.api.total.domain.attraction.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.domain.attraction.service.AttractionDetailSearchService;
import org.routemaster.api.total.infra.tourapi.vo.detail.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/attraction/detail")
@RequiredArgsConstructor
public class DetailSearchRestController {

    private final AttractionDetailSearchService service;

    @GetMapping("/tour")
    public ResponseEntity<Mono<TourAttractionDetailVO>> tourAttractionDetail(
            @RequestParam Integer contentId
    ) {
        return ResponseEntity.ok().body(service.searchTourAttractionDetail(contentId));
    }

    @GetMapping("/culture")
    public ResponseEntity<Mono<CultureAttractionDetailVO>> cultureAttractionDetail(
            @RequestParam Integer contentId
    ) {
        return ResponseEntity.ok().body(service.searchCultureAttractionDetail(contentId));
    }

    @GetMapping("/stay")
    public ResponseEntity<Mono<StayAttractionDetailVO>> stayAttractionDetail(
            @RequestParam Integer contentId
    ) {
        return ResponseEntity.ok().body(service.searchStayAttractionDetail(contentId));
    }

    @GetMapping("/festival")
    public ResponseEntity<Mono<FestivalAttractionDetailVO>> festivalAttractionDetail(
            @RequestParam Integer contentId
    ) {
        return ResponseEntity.ok().body(service.searchFestivalAttractionDetail(contentId));
    }

    @GetMapping("/leports")
    public ResponseEntity<Mono<LeportsAttractionDetailVO>> leportsAttractionDetail(
            @RequestParam Integer contentId
    ) {
        return ResponseEntity.ok().body(service.searchLeportsAttractionDetail(contentId));
    }

    @GetMapping("/restaurant")
    public ResponseEntity<Mono<RestaurantAttractionDetailVO>> restaurantAttractionDetail(
            @RequestParam Integer contentId
    ) {
        return ResponseEntity.ok().body(service.searchRestaurantAttractionDetail(contentId));
    }

    @GetMapping("/shopping")
    public ResponseEntity<Mono<ShoppingAttractionDetailVO>> shoppingAttractionDetail(
            @RequestParam Integer contentId
    ) {
        return ResponseEntity.ok().body(service.searchShoppingAttractionDetail(contentId));
    }

    @GetMapping("/course")
    public ResponseEntity<Mono<CourseAttractionDetailVO>> courseAttractionDetail(
            @RequestParam Integer contentId
    ) {
        return ResponseEntity.ok().body(service.searchCourseAttractionDetail(contentId));
    }

}
