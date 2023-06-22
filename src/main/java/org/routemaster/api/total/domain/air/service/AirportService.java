package org.routemaster.api.total.domain.air.service;

import com.amadeus.resources.Destination;
import com.amadeus.resources.Location;
import org.routemaster.api.total.infra.amadeus.vo.DestinationVO;
import org.routemaster.api.total.infra.amadeus.vo.LocationVO;

import java.util.List;

public interface AirportService {
    List<DestinationVO> airportRoutes(String depatureAirportCode, Long max);
    List<LocationVO> airportAndCitySearch(String subType,
                                          String keyword,
                                          String countryCode,
                                          Integer pageLimit,
                                          Integer pageOffset,
                                          String sort,
                                          String view);
    LocationVO airportAndCitySearch(String locationId);
    List<LocationVO> airportNearestRelevant(Double latitude,
                                            Double longitude,
                                            Integer radius,
                                            Integer pageLimit,
                                            Integer pageOffset,
                                            String sort);
}