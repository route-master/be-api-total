package org.routemaster.api.total.infra.amadeus.value;

import com.amadeus.Amadeus;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Getter
public class AmadeusValue {

    private final AmadeusConfigValue amadeusConfigValue;
    private final Amadeus amadeus;

    @Autowired
    public AmadeusValue(AmadeusConfigValue amadeusConfigValue) {
        this.amadeusConfigValue = amadeusConfigValue;
        this.amadeus = Amadeus.builder(amadeusConfigValue.getClientId(), amadeusConfigValue.getClientSecret())
                .setHostname(amadeusConfigValue.getHostname())
                .build();
    }
}
