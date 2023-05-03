package org.routemaster.api.total.domain.air.service.impl;

import com.amadeus.Amadeus;
import com.amadeus.resources.Location;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.domain.air.service.AirUtilitiesService;
import org.routemaster.api.total.infra.amadeus.value.AmadeusValue;
import org.routemaster.api.total.infra.amadeus.vo.LocationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class DefaultAirUtilitiesService implements AirUtilitiesService {

    private final Amadeus amadeus;

    @Autowired
    public DefaultAirUtilitiesService(AmadeusValue amadeusValue) {
        this.amadeus = amadeusValue.getAmadeus();
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
