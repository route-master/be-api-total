package org.routemaster.api.total.domain.air.service.impl;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.domain.air.service.AirBookingService;
import org.routemaster.api.total.infra.amadeus.value.AmadeusValue;
import org.routemaster.api.total.infra.amadeus.vo.FlightOfferSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class DefaultAirBookingService implements AirBookingService {

    private final Amadeus amadeus;

    @Autowired
    public DefaultAirBookingService(AmadeusValue amadeusValue) {
        this.amadeus = amadeusValue.getAmadeus();
    }

    @Override
    public List<FlightOfferSearchVO> flightOfferSearch(
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
            Integer max) {
        Params params = Params.with("originLocationCode", originLocationCode)
                .and("destinationLocationCode", destinationLocationCode)
                .and("departureDate", departureDate)
                .and("adults", adults)
                .and("nonStop", nonStop);
        if (returnDate != null) { params.and("returnDate", returnDate); }
        if (children != null) { params.and("children", children); }
        if (infants != null) { params.and("infants", infants); }
        if (travelClass != null) { params.and("travelClass", travelClass); }
        if (includedAirlineCodes != null) { params.and("includedAirlineCodes", includedAirlineCodes); }
        if (excludeAirlineCodes != null) { params.and("excludeAirlineCodes", excludeAirlineCodes); }
        if (currencyCode != null) { params.and("currencyCode", currencyCode); }
        if (maxPrice != null) { params.and("maxPrice", maxPrice); }
        if (max != null) { params.and("max", max); }

        return flightOfferSearch(params);
    }

    private List<FlightOfferSearchVO> flightOfferSearch(Params params) {
        try {
            FlightOfferSearch[] flightOfferSearches = amadeus.shopping.flightOffersSearch.get(params);
            List<FlightOfferSearchVO> flightOfferSearchVOs = new ArrayList<>();
            for (FlightOfferSearch flightOfferSearch: flightOfferSearches) {
                flightOfferSearchVOs.add(
                        FlightOfferSearchVO.builder()
                                .flightOfferSearch(flightOfferSearch)
                                .build()
                );
            }
            return flightOfferSearchVOs;
        } catch (ResponseException e) {
            throw new RuntimeException(e);
        }
    }
}
