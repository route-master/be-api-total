package org.routemaster.api.total.domain.navigation.service;

import org.routemaster.api.total.domain.navigation.data.CarNavigationRoutes;
import reactor.core.publisher.Mono;

public interface CarNavigationService {

    Mono<CarNavigationRoutes> navigate(
            Double originLatitude,
            Double originLongitude,
            Double destinationLatitude,
            Double destinationLogitude,
            String priority,
            String avoid,
            Boolean alternatives,
            Boolean roadDetails,
            Integer carType,
            String carFuel,
            Boolean carHipass,
            Boolean summary
    );

}
