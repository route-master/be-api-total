package org.routemaster.api.total.domain.air.service.impl;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.Destination;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.infra.amadeus.vo.DestinationVO;
import org.routemaster.api.total.domain.air.service.AirSearchAndShoppingService;
import org.routemaster.api.total.infra.amadeus.value.AmadeusValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class DefaultAirSearchAndShoppingService implements AirSearchAndShoppingService {

    private final Amadeus amadeus;

    @Autowired
    public DefaultAirSearchAndShoppingService(AmadeusValue amadeusValue) {
        this.amadeus = amadeusValue.getAmadeus();
    }

    @Override
    public List<DestinationVO> airportRoutes(String departureAirportCode, Long max) {
        Params params = Params.with("departureAirportCode", departureAirportCode);

        if (max != null) {
            params.and("max", max);
        }

        return airportRoutes(params);
    }

    private List<DestinationVO> airportRoutes(Params params) {
        try {
            Destination[] destinations = amadeus.airport.directDestinations.get(params);
            List<DestinationVO> destinationVOs = new ArrayList<>();
            for (Destination destination : destinations) {
                destinationVOs.add(
                        DestinationVO.builder()
                                .destination(destination)
                                .build()
                );
            }
            return destinationVOs;
        } catch (ResponseException e) {
            throw new RuntimeException(e); // Required Custom Exception Handling
        }
    }
}
