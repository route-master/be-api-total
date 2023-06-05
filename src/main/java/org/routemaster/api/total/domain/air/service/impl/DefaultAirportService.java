package org.routemaster.api.total.domain.air.service.impl;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.Destination;
import com.amadeus.resources.Location;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.domain.air.service.AirportService;
import org.routemaster.api.total.infra.amadeus.value.AmadeusValue;
import org.routemaster.api.total.infra.amadeus.vo.DestinationVO;
import org.routemaster.api.total.infra.amadeus.vo.LocationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class DefaultAirportService implements AirportService {

    private final Amadeus amadeus;

    @Autowired
    public DefaultAirportService(AmadeusValue amadeusValue) {
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

    @Override
    public List<LocationVO> airportAndCitySearch(String subType,
                                                 String keyword,
                                                 String countryCode,
                                                 Integer pageLimit,
                                                 Integer pageOffset,
                                                 String sort,
                                                 String view) {
        Params params = Params.with("subType", subType).and("keyword", keyword);

        if (countryCode != null)
            params.and("countryCode", countryCode);
        if (pageLimit != null)
            params.and("page[limit]", pageLimit);
        if (pageOffset != null)
            params.and("page[offset]", pageOffset);
        if (sort != null)
            params.and("sort", sort);
        if (view != null)
            params.and("view", view);
        return airportAndCitySearch(params);
    }

    private List<LocationVO> airportAndCitySearch(Params params) {
        try{
            Location[] locations = amadeus.referenceData.locations.get(params);
            List<LocationVO> locationVOs = new ArrayList<>();
            for (Location location : locations) {
                locationVOs.add(
                        LocationVO.builder()
                                .location(location)
                                .build()
                );
            }
            return locationVOs;
        } catch (ResponseException e) {
            throw new RuntimeException(e);
        }

    }

}
