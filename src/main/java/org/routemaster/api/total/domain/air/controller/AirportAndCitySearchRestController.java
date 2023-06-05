package org.routemaster.api.total.domain.air.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.domain.air.service.AirportService;
import org.routemaster.api.total.infra.amadeus.vo.LocationVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/reference-data")
@RequiredArgsConstructor
public class AirportAndCitySearchRestController {

    private final AirportService airportService;

    @Operation(
            summary = "키워드에 매칭되는 공항과 도시 조회",
            tags = {
                    "locations",
                    "locations/{locationId}"
            }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success Response",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(
                                            implementation = LocationVO.class
                                    )
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error"
            ),
    })
    @GetMapping("/locations")
    public ResponseEntity<List<LocationVO>> locations(
            @Parameter(
                    description = "location 종류 (AIRPROT and/or CITY)",
                    required = true,
                    example = "AIRPORT"
            ) @RequestParam String subType,
            @Parameter(
                    description = "도시나 공항 이름의 시작 키워드",
                    required = true,
                    example = "MUC"
            ) @RequestParam String keyword,
            @Parameter(
                    description = "ISO 3166-1 alpha-2에 따른 국가 코드",
                    example = "US"
            ) @RequestParam(required = false) String countryCode,
            @Parameter(
                    description = "페이지 당 최대 응답 개수"
            ) @RequestParam(required = false) Integer pageLimit,
            @Parameter(
                    description = "페이지 시작 인덱스"
            ) @RequestParam(required = false) Integer pageOffset,
            @Parameter(
                    description = "정렬 방법",
                    example = "analytics.travelers.score"
            ) @RequestParam(required = false) String sort,
            @Parameter(
                    description = "응답의 정보 범위",
                    example = "FULL"
            ) @RequestParam(required = false) String view
        ) {
        List<LocationVO> locations = airportService.airportAndCitySearch(
                subType,
                keyword,
                countryCode,
                pageLimit,
                pageOffset,
                sort,
                view);
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }

}
