package org.routemaster.api.total.infra.amadeus.vo;

import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.FlightOrder;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@ToString
public class FlightOrderVO {

    private String type;
    private String id;
    private String queuingOfficeId;
    private FlightOrder.AssociatedRecord[] associatedRecords;
    private FlightOrder.Traveler[] travelers;
    private FlightOfferSearch[] flightOffers;

    public static final class FlightOrderVOBuilder {
        public FlightOrderVOBuilder flightOrder(FlightOrder flightOrder) {
            this.type = flightOrder.getType();
            this.id = flightOrder.getId();
            this.queuingOfficeId = flightOrder.getQueuingOfficeId();
            this.travelers = flightOrder.getTravelers();
            this.flightOffers = flightOrder.getFlightOffers();
            return this;
        }
    }

}
