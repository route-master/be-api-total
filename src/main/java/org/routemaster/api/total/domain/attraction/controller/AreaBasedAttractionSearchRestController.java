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
public class AreaBasedAttractionSearchRestController {

    private final AttractionSearchService attractionSearchService;

    @Operation(
            summary = "지역 기반 관광 정보 조회",
            description = "지역 및 시군구를 기반으로 관광정보 목록을 조회",
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
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = AttractionSearchVO.class)
                    )
            )
    })
    @GetMapping("/area-based")
    public ResponseEntity<Mono<AttractionSearchVO>> areaBased(
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
                    description = "관광지 타입",
                    example = "12"
            ) @RequestParam(required = false) Integer contentTypeId,
            @Parameter(
                    description = "지역코드",
                    example = "1"
            ) @RequestParam Integer areaCode,
            @Parameter(
                    description = "시군구코드",
                    example = "1"
            ) @RequestParam(required = false) Integer sigunguCode,
            @Parameter(
                    description = "대분류",
                    example = "A01"
            ) @RequestParam(required = false) String cat1,
            @Parameter(
                    description = "중분류",
                    example = "A0101"
            ) @RequestParam(required = false) String cat2,
            @Parameter(
                    description = "소분류",
                    example = "A01010100"
            ) @RequestParam(required = false) String cat3,
            @Parameter(
                    description = "수정일자",
                    example = "20191001"
            ) @RequestParam(required = false) String modifiedTime
    ) {
        Mono<AttractionSearchVO> result = attractionSearchService.searchAreaBasedAttraction(
            numOfRows,
            pageNo,
            arrange,
            contentTypeId,
            areaCode,
            sigunguCode,
            cat1,
            cat2,
            cat3,
            modifiedTime
        );
        return ResponseEntity.ok(result);
    }

}
