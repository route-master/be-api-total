package org.routemaster.api.total.domain.air.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.domain.air.service.AirUtilitiesService;
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

    private final AirUtilitiesService airUtilitiesService;

    @GetMapping("/locations")
    public ResponseEntity<List<LocationVO>> locations(
            @RequestParam String subType,
            @RequestParam String keyword,
            @RequestParam(required = false) String countryCode,
            @RequestParam(required = false) Integer pageLimit,
            @RequestParam(required = false) Integer pageOffset,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String view
            ) {
        List<LocationVO> locations = airUtilitiesService.airportAndCitySearch(
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
