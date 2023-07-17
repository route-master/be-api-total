package org.routemaster.api.total.domain.air.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.domain.air.service.AirBookingService;
import org.routemaster.api.total.infra.amadeus.vo.FlightOfferSearchVO;
//import org.routemaster.api.total.infra.amadeus.vo.FlightPriceVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        List<FlightOfferSearchVO> flightOfferSearchVOs = airBookingService.getFlightOfferSearch(
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

    @PostMapping("/flight-offers")
    public ResponseEntity<List<FlightOfferSearchVO>> postFlightOffers(@RequestBody String getFlightOffersBody) {
        List<FlightOfferSearchVO> flightOfferSearchVOs = airBookingService.postFlightOfferSearch(getFlightOffersBody);
        return new ResponseEntity<>(flightOfferSearchVOs, HttpStatus.OK);
    }

//    @PostMapping("/flight-offers/pricing")
//    public ResponseEntity<FlightPriceVO> postFlightOffersPrice(
//            @RequestBody String priceFlightOffersBody,
//            @RequestParam(required = false) String include,
//            @RequestParam(required = false) Boolean forceClass
//    ) {
//        FlightPriceVO flightPriceVO = airBookingService.postFlightOffersPrice(priceFlightOffersBody, include, forceClass);
//        return new ResponseEntity<>(flightPriceVO, HttpStatus.OK);
//    }

}
