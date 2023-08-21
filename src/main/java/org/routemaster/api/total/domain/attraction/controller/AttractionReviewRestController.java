package org.routemaster.api.total.domain.attraction.controller;

import lombok.RequiredArgsConstructor;
import org.routemaster.api.total.domain.attraction.data.review.AttractionReview;
import org.routemaster.api.total.domain.attraction.data.review.AttractionReviewSaveRequest;
import org.routemaster.api.total.domain.attraction.service.AttractionReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/attraction/review")
@RequiredArgsConstructor
public class AttractionReviewRestController {

    private final AttractionReviewService service;

    @GetMapping("/list/{attractionContentId}")
    public ResponseEntity<Flux<AttractionReview>> attractionReviewList(@PathVariable(name = "attractionContentId") String attractionContentId) {
        return new ResponseEntity<>(service.listByContentId(attractionContentId), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Mono<AttractionReview>> saveAttractionReview(@RequestBody AttractionReviewSaveRequest request) {
        if (request.getId() == null) {
            return new ResponseEntity<>(service.save(request), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(service.update(request), HttpStatus.OK);
        }
    }
}
