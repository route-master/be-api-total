package org.routemaster.api.total.domain.attraction.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@RequestMapping("/attraction/search/restaurant")
@RequiredArgsConstructor
public class RestaurantSearchRestController {

    private final AttractionSearchService attractionSearchService;
    private final Integer RESTAURANT_CONTENT_TYPE_ID = 39;

    @Operation(
            summary = "음식점 정보 조회(위치 기반)",
            description = "위치를 기반으로 음식점 정보를 조회하는 기능",
            tags = { "attraction-search" }
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
    @GetMapping("/location-based")
    public ResponseEntity<Mono<AttractionSearchVO>> getLocationBasedRestuarant(
            @Parameter(
                    description = "한 페이지 결과 수",
                    example = "10"
            ) @RequestParam(required = false) Integer numOfRows,
            @Parameter(
                    description = "페이지 번호",
                    example = "1"
            ) @RequestParam(required = false) Integer pageNo,
            @Parameter(
                    description = "정렬 구분(A=제목순, C=수정일순, D=생성일순)",
                    example = "A",
                    schema = @Schema(
                            allowableValues = {
                                    "A", "B", "C", "D"
                            }
                    )
            ) @RequestParam(required = false) String arrange,
            @Parameter(
                    description = "GPS X좌표(WGS84 경도 좌표)",
                    example = "126.981611"
            ) @RequestParam Double mapX,
            @Parameter(
                    description = "GPS Y좌표(WGS84 위도 좌표)",
                    example = "37.568477"
            ) @RequestParam Double mapY,
            @Parameter(
                    description = "거리 반경(단위: m)",
                    example = "20000",
                    schema = @Schema(
                            minimum = "1",
                            maximum = "20000"
                    )
            ) @RequestParam Integer radius,
            @Parameter(
                    description = "콘텐츠 수정일(형식: YYYYMMDD)",
                    example = "20221030"
            ) @RequestParam(required = false) String modifiedTime
    ) {
        return ResponseEntity.ok(attractionSearchService.searchLocationBasedAttraction(
                numOfRows,
                pageNo,
                arrange,
                mapX,
                mapY,
                radius,
                RESTAURANT_CONTENT_TYPE_ID,
                modifiedTime)
        );
    }

    @Operation(
            summary = "음식점 정보 조회(키워드 기반)",
            description = "키워드를 기반으로 음식점 정보를 조회하는 기능",
            tags = { "attraction-search" }
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
    @GetMapping("/keyword-based")
    public ResponseEntity<Mono<AttractionSearchVO>> getKeywordBasedRestuarant(
            @Parameter(
                    description = "한 페이지 결과 수",
                    example = "10"
            ) @RequestParam(required = false) Integer numOfRows,
            @Parameter(
                    description = "페이지 번호",
                    example = "1"
            ) @RequestParam(required = false) Integer pageNo,
            @Parameter(
                    description = "정렬 구분(A=제목순, C=수정일순, D=생성일순)",
                    example = "A",
                    schema = @Schema(
                            allowableValues = {
                                    "A", "B", "C", "D"
                            }
                    )
            ) @RequestParam(required = false) String arrange,
            @Parameter(
                    description = "검색 키워드",
                    example = "강남"
            ) @RequestParam String keyword,
            @Parameter(
                    description = "지역 코드(1: 서울, 2: 인천, 3: 대전, 4: 대구, 5: 광주, 6: 부산, 7: 울산, 8: 세종특별자치시, 31: 경기도, 32: 강원도, 33: 충청북도, 34: 충청남도, 35: 경상북도, 36: 경상남도, 37: 전라북도, 38: 전라남도, 39: 제주도))",
                    example = "1",
                    schema = @Schema(
                            allowableValues = {
                                    "1", "2", "3", "4", "5", "6", "7", "8", "31", "32", "33", "34", "35", "36", "37", "38", "39"
                            }
                    )
            ) @RequestParam(required = false) Integer areaCode,
            @Parameter(
                    description = "시군구코드",
                    example = "1"
            ) @RequestParam(required = false) Integer sigunguCode
    ) {
        Mono<AttractionSearchVO> result = attractionSearchService.searchKeywordBasedAttraction(
                numOfRows,
                pageNo,
                arrange,
                keyword,
                RESTAURANT_CONTENT_TYPE_ID,
                areaCode,
                sigunguCode,
                null,
                null,
                null
        );
        return ResponseEntity.ok(result);
    }

    @Operation(
            summary = "음식점 정보 조회(지역 기반)",
            description = "지역을 기반으로 음식점 정보를 조회하는 기능",
            tags = { "attraction-search" }
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
    @GetMapping("/area-based")
    public ResponseEntity<Mono<AttractionSearchVO>> getAreaBasedRestuarant(
            @Parameter(
                    description = "한 페이지 결과 수",
                    example = "10"
            ) @RequestParam(required = false) Integer numOfRows,
            @Parameter(
                    description = "페이지 번호",
                    example = "1"
            ) @RequestParam(required = false) Integer pageNo,
            @Parameter(
                    description = "정렬 구분(A=제목순, C=수정일순, D=생성일순)",
                    example = "A",
                    schema = @Schema(
                            allowableValues = {
                                    "A", "B", "C", "D"
                            }
                    )
            ) @RequestParam(required = false) String arrange,
            @Parameter(
                    description = "지역 코드(1: 서울, 2: 인천, 3: 대전, 4: 대구, 5: 광주, 6: 부산, 7: 울산, 8: 세종특별자치시, 31: 경기도, 32: 강원도, 33: 충청북도, 34: 충청남도, 35: 경상북도, 36: 경상남도, 37: 전라북도, 38: 전라남도, 39: 제주도))",
                    example = "1",
                    schema = @Schema(
                            allowableValues = {
                                    "1", "2", "3", "4", "5", "6", "7", "8", "31", "32", "33", "34", "35", "36", "37", "38", "39"
                            }
                    )
            ) @RequestParam Integer areaCode,
            @Parameter(
                    description = "시군구코드",
                    example = "1"
            ) @RequestParam(required = false) Integer sigunguCode,
            @Parameter(
                    description = "콘텐츠 수정일(형식: YYYYMMDD)",
                    example = "20191001"
            ) @RequestParam(required = false) String modifiedTime
    ) {
        Mono<AttractionSearchVO> result = attractionSearchService.searchAreaBasedAttraction(
                numOfRows,
                pageNo,
                arrange,
                RESTAURANT_CONTENT_TYPE_ID,
                areaCode,
                sigunguCode,
                null,
                null,
                null,
                modifiedTime
        );
        return ResponseEntity.ok(result);
    }

}
