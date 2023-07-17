package org.routemaster.api.total.domain.air.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.domain.air.service.AirBookingService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {

    private final AirBookingService airBookingService;

//    @PostMapping("/flight-orders")
//    public ResponseEntity<FlightOrderVO> postFlightOrders(
//            @RequestBody String flightOfferBody
//    ) {
//
//    }

}
