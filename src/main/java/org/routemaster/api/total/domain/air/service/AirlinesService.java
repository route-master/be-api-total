package org.routemaster.api.total.domain.air.service;

import com.amadeus.referencedata.urls.CheckinLinks;
import com.amadeus.resources.CheckinLink;
import org.routemaster.api.total.infra.amadeus.vo.AirlineVO;
import org.routemaster.api.total.infra.amadeus.vo.CheckinLinkVO;
import org.routemaster.api.total.infra.amadeus.vo.DestinationVO;

import java.util.List;

public interface AirlinesService {
    List<CheckinLinkVO> flightCheckInLinks(String airlineCode, String language);
    List<AirlineVO> airlineCodeLookup(String airlineCode);
    List<DestinationVO> airlineRoutes(String airlineCode, Long max);
}
