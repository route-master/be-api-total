package org.routemaster.api.total.domain.air.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.routemaster.api.total.domain.air.service.impl.DefaultAirSearchAndShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class AirSearchAndShoppingServiceTest {

    @Autowired
    private DefaultAirSearchAndShoppingService service;

    @Test
    public void testExist() {
        Assertions.assertNotNull(service);
    }

    @Test
    public void testAirportRoutes() {
            String departureAirportCode = "BLR";
            Long max = null;

            Assertions.assertNotNull(service.airportRoutes(departureAirportCode, max));
        }

}
