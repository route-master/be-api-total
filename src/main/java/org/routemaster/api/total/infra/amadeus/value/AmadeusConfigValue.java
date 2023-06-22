package org.routemaster.api.total.infra.amadeus.value;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Getter
public class AmadeusConfigValue {

    @Value("${amadeus.client-id}")
    private String clientId;
    @Value("${amadeus.client-secret}")
    private String clientSecret;
    @Value("${amadeus.hostname}")
    private String hostname;
}
