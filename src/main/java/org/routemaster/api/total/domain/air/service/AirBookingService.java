package org.routemaster.api.total.domain.air.service;

import org.routemaster.api.total.infra.amadeus.vo.FlightOfferSearchVO;

import java.util.List;

public interface AirBookingService {
    List<FlightOfferSearchVO> getFlightOfferSearch(
            String originLocationCode,
            String destinationLocationCode,
            String departureDate,
            String returnDate,
            Integer adults,
            Integer children,
            Integer infants,
            String travelClass,
            String includedAirlineCodes,
            String excludeAirlineCodes,
            boolean nonStop,
            String currencyCode,
            Integer maxPrice,
            Integer max
    );

    List<FlightOfferSearchVO> postFlightOfferSearch(String getFlightOffersBody);

}
