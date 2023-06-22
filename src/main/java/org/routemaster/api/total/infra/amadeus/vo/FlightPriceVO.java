package org.routemaster.api.total.infra.amadeus.vo;

import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.FlightPrice;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@ToString
public class FlightPriceVO {

    private String type;
    private FlightOfferSearch[] flightOffers;
    private FlightPrice.BookingRequirements bookingRequirements;

    public static final class FlightPriceVOBuilder {
        public FlightPriceVOBuilder flightPrice(FlightPrice flightPrice) {
            this.type = flightPrice.getType();
            this.flightOffers = flightPrice.getFlightOffers();
            this.bookingRequirements = flightPrice.getBookingRequirements();
            return this;
        }
    }

}
