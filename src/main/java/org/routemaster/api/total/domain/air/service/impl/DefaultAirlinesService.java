package org.routemaster.api.total.domain.air.service.impl;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.Destination;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.domain.air.service.AirlinesService;
import org.routemaster.api.total.infra.amadeus.value.AmadeusValue;
import org.routemaster.api.total.infra.amadeus.vo.DestinationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class DefaultAirlinesService implements AirlinesService {

    private final Amadeus amadeus;

    @Autowired
    public DefaultAirlinesService(AmadeusValue amadeusValue) {
        this.amadeus = amadeusValue.getAmadeus();
    }

    @Override
    public List<DestinationVO> airlineRoutes(String airlineCode, Long max) {
        Params params = Params.with("airlineCode", airlineCode);

        if (max != null) {
            params.and("max", max);
        }

        return airlineRoutes(params);
    }

    private List<DestinationVO> airlineRoutes(Params params) {
        try {
            Destination[] destinations = amadeus.airline.destinations.get(params);
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
