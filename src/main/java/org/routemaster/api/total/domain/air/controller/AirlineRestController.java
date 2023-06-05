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
import org.routemaster.api.total.domain.air.service.AirlinesService;
import org.routemaster.api.total.infra.amadeus.vo.DestinationVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/airline")
@RequiredArgsConstructor
public class AirlineRestController {

    private final AirlinesService airlinesService;

    @Operation(
            summary = "항공사 노선 도착지 조회",
            tags = {
                    "destinations"
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
                                            implementation = DestinationVO.class
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
    @GetMapping("/destinations")
    public ResponseEntity<List<DestinationVO>> directDestinations(
            @Parameter(
                    description = "IATA 표준에 따른 항공사 코드",
                    required = true,
                    example = "BA"
            ) @RequestParam String airlineCode,
            @Parameter(
                    description = "응답의 최대 개수"
            ) @RequestParam(required = false) Long max
    ) {
        List<DestinationVO> destinations = airlinesService.airlineRoutes(airlineCode, max);
        return new ResponseEntity<>(destinations, HttpStatus.OK);
    }
}
