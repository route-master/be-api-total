package org.routemaster.api.total.domain.air.service;

import org.routemaster.api.total.infra.amadeus.vo.DestinationVO;

import java.util.List;

public interface AirlinesService {
    List<DestinationVO> airlineRoutes(String airlineCode, Long max);
}
