package org.routemaster.api.total.infra.amadeus.vo;

import com.amadeus.resources.Airline;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@ToString
public class AirlineVO {

    private String type;
    private String iataCode;
    private String icaoCode;
    private String businessName;
    private String commonName;

    public static final class AirlineVOBuilder {
        public AirlineVOBuilder airline(Airline airline) {
            this.type = airline.getType();
            this.iataCode = airline.getIataCode();
            this.icaoCode = airline.getIcaoCode();
            this.businessName = airline.getBusinessName();
            this.commonName = airline.getCommonName();
            return this;
        }
    }

}
