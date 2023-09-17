package org.routemaster.api.total.domain.attraction.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.domain.attraction.service.AttractionDetailSearchService;
import org.routemaster.api.total.domain.attraction.data.search.AttractionSearchVO;
import org.routemaster.api.total.domain.attraction.data.detail.*;
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

    @Operation(
            summary = "공통 상세 정보 조회",
            description = "contentID에 해당하는 attraction의 공통 상세 정보(제목, 연락처, 주소, 좌표, 개요 정보 등) 조회",
            tags = {"attraction-detail"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description= "Success Response",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AttractionSearchVO.class)
                    )
            )
    })
    @GetMapping("/common")
    public ResponseEntity<Mono<AttractionSearchVO>> detailCommon(
            @Parameter(
                    name = "contentId",
                    description = "조회할 관광지의 contentId",
                    required = true,
                    example = "126508"
            ) @RequestParam Integer contentId
    ) {
        Mono<AttractionSearchVO> result = service.searchAttractionCommonDetail(
                contentId
        );
        return ResponseEntity.ok(result);
    }

    @Operation(
            summary = "관광지 상세 정보 조회",
            description = "content id에 해당하는 관광지의 상세 정보 조회",
            tags = {"attraction-detail"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description= "Success Response",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TourAttractionDetailVO.class)
                    )
            )
    })
    @GetMapping("/tour")
    public ResponseEntity<Mono<TourAttractionDetailVO>> tourAttractionDetail(
            @Parameter(
                    name = "contentId",
                    description = "관광지의 content ID",
                    required = true,
                    example = "1949905"
            ) @RequestParam Integer contentId
    ) {
        return ResponseEntity.ok().body(service.searchTourAttractionDetail(contentId));
    }

    @Operation(
            summary = "문화 시설 상세 정보 조회",
            description = "content id에 해당하는 문화 시설의 상세 정보 조회",
            tags = {"attraction-detail"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description= "Success Response",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CultureAttractionDetailVO.class)
                    )
            )
    })
    @GetMapping("/culture")
    public ResponseEntity<Mono<CultureAttractionDetailVO>> cultureAttractionDetail(
            @Parameter(
                    name = "contentId",
                    description = "문화시설의 content ID",
                    required = true,
                    example = "2714751"
            ) @RequestParam Integer contentId
    ) {
        return ResponseEntity.ok().body(service.searchCultureAttractionDetail(contentId));
    }

    @Operation(
            summary = "숙박 객실 상세 정보 조회",
            description = "content id에 해당하는 숙박 객실의 상세 정보 조회",
            tags = {"attraction-detail"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description= "Success Response",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = StayAttractionDetailVO.class)
                    )
            )
    })
    @GetMapping("/stay")
    public ResponseEntity<Mono<StayAttractionDetailVO>> stayAttractionDetail(
            @Parameter(
                    name = "contentId",
                    description = "숙박 시설의 content id",
                    required = true,
                    example = "142785"
            ) @RequestParam Integer contentId
    ) {
        return ResponseEntity.ok().body(service.searchStayAttractionDetail(contentId));
    }

    @Operation(
            summary = "행사/공연/축제 상세 정보 조회",
            description = "content id에 해당하는 행사의 상세 정보 조회",
            tags = {"attraction-detail"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description= "Success Response",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = FestivalAttractionDetailVO.class)
                    )
            )
    })
    @GetMapping("/festival")
    public ResponseEntity<Mono<FestivalAttractionDetailVO>> festivalAttractionDetail(
            @Parameter(
                    name = "contentId",
                    description = "행사/공연/축제의 content id",
                    required = true,
                    example = "737479"
            ) @RequestParam Integer contentId
    ) {
        return ResponseEntity.ok().body(service.searchFestivalAttractionDetail(contentId));
    }

    @Operation(
            summary = "레포츠 상세 정보 조회",
            description = "content id에 해당하는 레포츠의 상세 정보 조회",
            tags = {"attraction-detail"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description= "Success Response",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LeportsAttractionDetailVO.class)
                    )
            )
    })
    @GetMapping("/leports")
    public ResponseEntity<Mono<LeportsAttractionDetailVO>> leportsAttractionDetail(
            @Parameter(
                    name = "contentId",
                    description = "행사/공연/축제의 content id",
                    required = true,
                    example = "131139"
            ) @RequestParam Integer contentId
    ) {
        return ResponseEntity.ok().body(service.searchLeportsAttractionDetail(contentId));
    }

    @Operation(
            summary = "음식점 상세 정보 조회",
            description = "content id에 해당하는 음식점의 상세 정보 조회",
            tags = {"attraction-detail"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description= "Success Response",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RestaurantAttractionDetailVO.class)
                    )
            )
    })
    @GetMapping("/restaurant")
    public ResponseEntity<Mono<RestaurantAttractionDetailVO>> restaurantAttractionDetail(
            @Parameter(
                    name = "contentId",
                    description = "음식점의 content id",
                    required = true,
                    example = "2869760"
            ) @RequestParam Integer contentId
    ) {
        return ResponseEntity.ok().body(service.searchRestaurantAttractionDetail(contentId));
    }

    @Operation(
            summary = "쇼핑 시설 상세 정보 조회",
            description = "content id에 해당하는 쇼핑 시설의 상세 정보 조회",
            tags = {"attraction-detail"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description= "Success Response",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ShoppingAttractionDetailVO.class)
                    )
            )
    })
    @GetMapping("/shopping")
    public ResponseEntity<Mono<ShoppingAttractionDetailVO>> shoppingAttractionDetail(
            @Parameter(
                    name = "contentId",
                    description = "쇼핑 시설의 content id",
                    required = true,
                    example = "2924134"
            ) @RequestParam Integer contentId
    ) {
        return ResponseEntity.ok().body(service.searchShoppingAttractionDetail(contentId));
    }

    @Operation(
            summary = "여행 코스 상세 정보 조회",
            description = "content id에 해당하는 여행 코스의 상세 정보 조회",
            tags = {"attraction-detail"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description= "Success Response",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CourseAttractionDetailVO.class)
                    )
            )
    })
    @GetMapping("/course")
    public ResponseEntity<Mono<CourseAttractionDetailVO>> courseAttractionDetail(
            @Parameter(
                    name = "contentId",
                    description = "여행 코스의 content id",
                    required = true,
                    example = "1952962"
            ) @RequestParam Integer contentId
    ) {
        return ResponseEntity.ok().body(service.searchCourseAttractionDetail(contentId));
    }

}
