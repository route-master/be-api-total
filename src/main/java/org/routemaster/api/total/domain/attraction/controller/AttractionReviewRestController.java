package org.routemaster.api.total.domain.attraction.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.routemaster.api.total.domain.attraction.data.review.AttractionReview;
import org.routemaster.api.total.domain.attraction.data.review.AttractionReviewSaveRequest;
import org.routemaster.api.total.domain.attraction.data.search.AttractionSearchVO;
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

    @Operation(
            summary = "관광지 리뷰 조회",
            description = "관광지 리뷰 조회",
            tags = {
                    "attraction-review"
            }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description= "Success Response",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = AttractionReview.class)
                    )
            )
    })
    @GetMapping("/list/{attractionContentId}")
    public ResponseEntity<Flux<AttractionReview>> attractionReviewList(@PathVariable(name = "attractionContentId") String attractionContentId) {
        return new ResponseEntity<>(service.listByContentId(attractionContentId), HttpStatus.OK);
    }

    @Operation(
            summary = "관광지 리뷰 저장",
            description = "관광지 리뷰 저장",
            tags = {
                    "attraction-review"
            }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description= "Success Response",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = AttractionReview.class)
                    )
            )
    })
    @PostMapping("/save")
    public ResponseEntity<Mono<AttractionReview>> saveAttractionReview(@RequestBody AttractionReviewSaveRequest request) {
        if (request.getId() == null) {
            return new ResponseEntity<>(service.save(request), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(service.update(request), HttpStatus.OK);
        }
    }

    @Operation(
            summary = "관광지 리뷰 삭제",
            description = "Attraction content id와 user id로 관광지 리뷰를 삭제",
            tags = {
                    "attraction-review"
            }
    )
    @DeleteMapping("/delete/{attractionContentId}")
    public ResponseEntity<Mono<Void>> deleteAttractionReview(
            @RequestParam(name = "attractionContentId") String attractionContentId,
            @RequestParam(name = "userId") String userId
    ) {
        return new ResponseEntity<>(service.delete(attractionContentId, userId), HttpStatus.OK);
    }

    @Operation(
            summary = "관광지 리뷰 이미지 조회",
            description = "Attraction content id에 따른 관광지 리뷰 이미지 URL 조회",
            tags = {
                    "attraction-review"
            }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description= "Success Response"
            )
    })
    @GetMapping("/list/reviewImages/{attractionContentId}")
    public ResponseEntity<Mono<List<String>>> attractionReviewImagesList(@PathVariable(name = "attractionContentId") String attractionContentId) {
        return new ResponseEntity<>(service.listReviewImagesByContentId(attractionContentId), HttpStatus.OK);
    }
}
