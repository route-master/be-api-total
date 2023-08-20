package org.routemaster.api.total.domain.navigation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.domain.navigation.data.CarNavigationRoutes;
import org.routemaster.api.total.domain.navigation.service.CarNavigationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/navigation")
@RequiredArgsConstructor
public class CarNavigationRestController {

    private final CarNavigationService carNavigationService;

    @GetMapping("/car")
    public ResponseEntity<Mono<CarNavigationRoutes>> navigate(
            @RequestParam Double originLatitude,
            @RequestParam Double originLongitude,
            @RequestParam Double destinationLatitude,
            @RequestParam Double destinationLogitude,
            @RequestParam(required = false) String priority,
            @RequestParam(required = false) String avoid,
            @RequestParam(required = false) Boolean alternatives,
            @RequestParam(required = false) Boolean roadDetails,
            @RequestParam(required = false) Integer carType,
            @RequestParam(required = false) String carFuel,
            @RequestParam(required = false) Boolean carHipass,
            @RequestParam(required = false) Boolean summary
            ) {
        return ResponseEntity.ok(
                carNavigationService.navigate(
                        originLatitude,
                        originLongitude,
                        destinationLatitude,
                        destinationLogitude,
                        priority,
                        avoid,
                        alternatives,
                        roadDetails,
                        carType,
                        carFuel,
                        carHipass,
                        summary
                        ));
    }
}
