package org.routemaster.api.total.domain.attraction.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.domain.attraction.DTO.ReviewDTO;
import org.routemaster.api.total.domain.attraction.service.impl.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/review")
@RequiredArgsConstructor
@ComponentScan(basePackages = "org.routemaster.api.total.domain.attraction.service.impl")
public class ReviewController {

    @Autowired
    private final ReviewService reviewService;

    @GetMapping(value = "/find")
    public ResponseEntity<List<ReviewDTO>> findReview(@RequestParam String contentId) {
        return ResponseEntity.ok(reviewService.selectReview(contentId));
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<List<ReviewDTO>> findAllReview() {
        return ResponseEntity.ok(reviewService.selectAllReview());
    }

}
