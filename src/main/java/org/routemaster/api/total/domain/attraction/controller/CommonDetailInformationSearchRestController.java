package org.routemaster.api.total.domain.attraction.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.domain.attraction.service.AttractionInformationSearchService;
import org.routemaster.api.total.infra.tourapi.vo.AttractionSearchVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/attraction-detail")
@RequiredArgsConstructor
public class CommonDetailInformationSearchRestController {

    private final AttractionInformationSearchService service;

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
        Mono<AttractionSearchVO> result = service.searchAttractionDetail(
                contentId
        );
        return ResponseEntity.ok(result);
    }
}
