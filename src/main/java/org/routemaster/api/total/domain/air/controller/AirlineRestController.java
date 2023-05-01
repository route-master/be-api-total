package org.routemaster.api.total.domain.air.controller;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.domain.air.service.AirSearchAndShoppingService;
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

    private final AirSearchAndShoppingService airSearchAndShoppingService;

    @GetMapping("/destinations")
    public ResponseEntity<List<DestinationVO>> directDestinations(
            @Parameter(
                    description = "IATA 표준에 따른 노선 코드",
                    required = true,
                    example = "BA"
            ) @RequestParam String airlineCode,
            @Parameter(
                    description = "응답의 최대 개수"
            ) @RequestParam(required = false) Long max
    ) {
        List<DestinationVO> destinations = airSearchAndShoppingService.airlineRoutes(airlineCode, max);
        return new ResponseEntity<>(destinations, HttpStatus.OK);
    }
}
