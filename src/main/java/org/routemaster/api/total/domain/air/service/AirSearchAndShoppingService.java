package org.routemaster.api.total.domain.air.service;

import org.routemaster.api.total.infra.amadeus.vo.DestinationVO;

import java.util.List;

public interface AirSearchAndShoppingService {

    List<DestinationVO> airportRoutes(String departureAirportCode, Long max);

    List<DestinationVO> airlineRoutes(String airlineCode, Long max);
}
