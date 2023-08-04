package org.routemaster.api.total.domain.attraction.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(
            summary = "서비스 분류 코드 조회",
            description = "각 관광타입에 해당하느 서비스 분류코드를 대, 중, 소분류로 조회하는 기능",
            tags = {
                    "attraction-utils"
            }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description= "Success Response",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CategorySearchResponse.class)
                    )
            )
    })
    @GetMapping
    public ResponseEntity<Mono<CategorySearchResponse>> searchCategory(
            @Parameter(
                    description = "한 페이지 결과 수",
                    example = "10",
                    schema = @Schema(
                            defaultValue = "10"
                    )
            ) @RequestParam(required = false) Integer numOfRows,
            @Parameter(
                    description = "페이지 번호",
                    example = "1",
                    schema = @Schema(
                            defaultValue = "1"
                    )
            ) @RequestParam(required = false) Integer pageNo,
            @Parameter(
                    description = "관광지 타입(12: 관광지, 14: 문화시설, 15: 행사/공연/축제, 25: 여행코스, 28: 레포츠, 32: 숙박, 38: 쇼핑, 39: 음식점)",
                    example = "12",
                    schema = @Schema(
                            allowableValues = {
                                    "12", "14", "15", "25", "28", "32", "38", "39"
                            }
                    )
            ) @RequestParam(required = false) Integer contentTypeId,
            @Parameter(
                    description = "대분류 코드",
                    example = "A01"
            ) @RequestParam(required = false) String largeCategory,
            @Parameter(
                    description = "중분류 코드(대분류 코드 입력 필수)",
                    example = "A0101"
            ) @RequestParam(required = false) String mediumCategory,
            @Parameter(
                    description = "소분류 코드(대분류, 중분류 코드 입력 필수)",
                    example = "A01010100"
            ) @RequestParam(required = false) String smallCategory
    ) {
        Mono<CategorySearchResponse> response = service.searchCategory(numOfRows, pageNo, contentTypeId, largeCategory, mediumCategory, smallCategory);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
