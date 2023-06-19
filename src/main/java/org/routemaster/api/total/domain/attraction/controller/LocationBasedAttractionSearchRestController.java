package org.routemaster.api.total.domain.attraction.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.domain.attraction.service.AttractionSearchService;
import org.routemaster.api.total.infra.tourapi.vo.AttractionSearchVO;
import org.springframework.http.HttpStatus;
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
public class LocationBasedAttractionSearchRestController {

    private final AttractionSearchService attractionSearchService;

    @Operation(
            summary = "위치 기반 관광 정보 조회",
            description = "위치 기반 관광 정보 파라미터 타입에 따라서 정렬(제목순, 수정일순 등) 검색목록을 조회",
            tags = {
                    "attraction-search"
            }
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
    public ResponseEntity<Mono<AttractionSearchVO>> locationBased(
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
                    description = "관광타입(관광지, 숙박 등) ID",
                    example = "12"
            ) @RequestParam(required = false) Integer contentTypeId,
            @Parameter(
                    description = "콘텐츠 수정일",
                    example = "2022-10-20 10:00:00.0"
            ) @RequestParam(required = false) String modifiedTime
    ) {
        Mono<AttractionSearchVO> result = attractionSearchService.searchLocationBasedAttraction(
                numOfRows,
                pageNo,
                arrange,
                mapX,
                mapY,
                radius,
                contentTypeId,
                modifiedTime
        );
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
