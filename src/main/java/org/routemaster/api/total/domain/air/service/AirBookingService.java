package org.routemaster.api.total.domain.air.service;

import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.FlightPrice;
import org.routemaster.api.total.infra.amadeus.vo.FlightOfferSearchVO;
import org.routemaster.api.total.infra.amadeus.vo.FlightOrderVO;
import org.routemaster.api.total.infra.amadeus.vo.FlightPriceVO;

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
    FlightPriceVO postFlightOffersPrice(String priceFlightOffersBody, String include, Boolean forceClass);
    FlightOrderVO postFlightOrder(String flightOfferBody);
}
