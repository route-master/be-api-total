package org.routemaster.api.total.domain.air.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.domain.air.service.AirBookingService;
import org.routemaster.api.total.infra.amadeus.vo.FlightOfferSearchVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/shopping")
@RequiredArgsConstructor
public class ShoppingController {

    private final AirBookingService airBookingService;

    @GetMapping("/flight-offers")
    public ResponseEntity<List<FlightOfferSearchVO>> flightOfferSearch(
            @RequestParam String originLocationCode,
            @RequestParam String destinationLocationCode,
            @RequestParam String departureDate,
            @RequestParam(required = false) String returnDate,
            @RequestParam Integer adults,
            @RequestParam(required = false) Integer children,
            @RequestParam(required = false) Integer infants,
            @RequestParam(required = false) String travelClass,
            @RequestParam(required = false) String includedAirlineCodes,
            @RequestParam(required = false) String excludeAirlineCodes,
            @RequestParam(required = false) boolean nonStop,
            @RequestParam(required = false) String currencyCode,
            @RequestParam(required = false) Integer maxPrice,
            @RequestParam(required = false) Integer max

    ) {
        List<FlightOfferSearchVO> flightOfferSearchVOs = airBookingService.flightOfferSearch(
                originLocationCode,
                destinationLocationCode,
                departureDate,
                returnDate,
                adults,
                children,
                infants,
                travelClass,
                includedAirlineCodes,
                excludeAirlineCodes,
                nonStop,
                currencyCode,
                maxPrice,
                max);
        return new ResponseEntity<>(flightOfferSearchVOs, HttpStatus.OK);
    }

}
