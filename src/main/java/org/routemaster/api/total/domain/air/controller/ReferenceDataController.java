package org.routemaster.api.total.domain.air.controller;

import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.CheckinLink;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.domain.air.service.AirlinesService;
import org.routemaster.api.total.domain.air.service.AirportService;
import org.routemaster.api.total.infra.amadeus.vo.AirlineVO;
import org.routemaster.api.total.infra.amadeus.vo.CheckinLinkVO;
import org.routemaster.api.total.infra.amadeus.vo.LocationVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/reference-data")
@RequiredArgsConstructor
public class ReferenceDataController {

    private final AirportService airportService;
    private final AirlinesService airlinesService;

    @Operation(
            summary = "키워드에 매칭되는 공항과 도시 조회",
            tags = {
                    "airport",
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
                    description = "location의 서브 타입 (AIRPROT and/or CITY)",
                    required = true,
                    example = "AIRPORT",
                    schema = @Schema(
                            allowableValues = {"AIRPORT", "CITY"}
                    )
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
                    description = "페이지 당 최대 응답 개수",
                    schema = @Schema(
                            defaultValue = "10"
                    )
            ) @RequestParam(required = false) Integer pageLimit,
            @Parameter(
                    description = "페이지 시작 인덱스",
                    schema = @Schema(
                            defaultValue = "0"
                    )
            ) @RequestParam(required = false) Integer pageOffset,
            @Parameter(
                    description = "정렬 방법",
                    schema = @Schema(
                            allowableValues = "analytics.travelers.score",
                            defaultValue = "analytics.travelers.score"
                    )
            ) @RequestParam(required = false) String sort,
            @Parameter(
                    description = """
                            응답의 정보 범위:
                            
                            LIGHT: IATA코드, 이름, 세부 이름, 도시 이름, 국가 이름을 포함
                            
                            FULL: IATA코드, 이름, 세부 이름, 도시 이름, 국가 이름, timezone offset, geocode, 세부 주소, travelers.score를 포함""",
                    schema = @Schema(
                            allowableValues = {"LIGHT", "FULL"},
                            defaultValue = "FULL"
                    )
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

    @Operation(
            summary = "location ID에 따른 특정 공항 또는 도시 조회",
            tags = {
                    "airport"
            }
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success Response",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LocationVO.class)
                    )
            )
    })
    @GetMapping("/locations/{locationId}")
    public ResponseEntity<LocationVO> locations(
            @Parameter(
                    description = "location의 ID",
                    example = "CMUC"
            )@PathVariable("locationId") String locationId
    ) throws ResponseException {
        LocationVO location = airportService.airportAndCitySearch(locationId);
        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    @Operation(
            summary = "location의 근처 공항 조회",
            tags = "airport"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Success Response",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LocationVO.class)
                    )
            )
    })
    @GetMapping("/locations/airports")
    public ResponseEntity<List<LocationVO>> locations(
            @Parameter(
                    description = "위도",
                    example = "51.57285"
            ) @RequestParam Double latitude,
            @Parameter(
                    description = "경도",
                    example = "-0.44161"
            ) @RequestParam Double longitude,
            @Parameter(
                    description = "조회 할 반경 (킬로미터 단위)",
                    schema = @Schema(
                            defaultValue = "500",
                            maximum = "500",
                            minimum = "0"
                    )
            ) @RequestParam(required = false) Integer radius,
            @Parameter(
                    description = "페이지 당 최대 응답 개수",
                    schema = @Schema(
                            defaultValue = "10"
                    )
            ) @RequestParam(required = false) Integer pageLimit,
            @Parameter(
                    description = "페이지 시작 인덱스",
                    schema = @Schema(
                            defaultValue = "0"
                    )
            ) @RequestParam(required = false) Integer pageOffset,
            @Parameter(
                    description = "정렬 방법",
                    schema = @Schema(
                            allowableValues = {"relevance", "distance", "analytics.flight.score", "analytics.travelers.score"},
                            defaultValue = "relevance"
                    )
            ) @RequestParam(required = false) String sort
    ) {
        List<LocationVO> locations = airportService.airportNearestRelevant(
                latitude,
                longitude,
                radius,
                pageLimit,
                pageOffset,
                sort);
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }

    @GetMapping("/urls/checkin-links")
    public ResponseEntity<List<CheckinLinkVO>> checkinLinks(
            @RequestParam String airlineCode,
            @RequestParam(required = false) String language
    ) {
        List<CheckinLinkVO> checkinLinkVOS = airlinesService.flightCheckInLinks(airlineCode, language);
        return new ResponseEntity<>(checkinLinkVOS, HttpStatus.OK);
    }

    @GetMapping("/airlines")
    public ResponseEntity<List<AirlineVO>> airlines(
            @RequestParam String airlineCodes
    ) {
        List<AirlineVO> airlineVOs = airlinesService.airlineCodeLookup(airlineCodes);
        return new ResponseEntity<>(airlineVOs, HttpStatus.OK);
    }

}
