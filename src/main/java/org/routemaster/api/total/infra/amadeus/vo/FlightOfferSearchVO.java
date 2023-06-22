package org.routemaster.api.total.infra.amadeus.vo;

import com.amadeus.resources.FlightOfferSearch;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@ToString
public class FlightOfferSearchVO {

    private String type;
    private String id;
    private String source;
    private boolean instantTicketingRequired;
    private boolean disablePricing;
    private boolean nonHomogeneous;
    private boolean oneWay;
    private boolean paymentCardRequired;
    private String lastTicketingDate;
    private int numberOfBookableSeats;
    private FlightOfferSearch.Itinerary[] itineraries;
    private FlightOfferSearch.SearchPrice price;
    private FlightOfferSearch.PricingOptions pricingOptions;
    private String[] validatingAirlineCodes;
    private FlightOfferSearch.TravelerPricing[] travelerPricings;
    private String choiceProbability;
    private FlightOfferSearch.FareRules fareRules;

    public static final class FlightOfferSearchVOBuilder {

        public FlightOfferSearchVOBuilder flightOfferSearch(FlightOfferSearch flightOfferSearch) {
            this.type = flightOfferSearch.getType();
            this.id = flightOfferSearch.getId();
            this.source = flightOfferSearch.getSource();
            this.instantTicketingRequired = flightOfferSearch.isInstantTicketingRequired();
            this.disablePricing = flightOfferSearch.isDisablePricing();
            this.nonHomogeneous = flightOfferSearch.isNonHomogeneous();
            this.oneWay = flightOfferSearch.isOneWay();
            this.paymentCardRequired = flightOfferSearch.isPaymentCardRequired();
            this.lastTicketingDate = flightOfferSearch.getLastTicketingDate();
            this.numberOfBookableSeats = flightOfferSearch.getNumberOfBookableSeats();
            this.itineraries = flightOfferSearch.getItineraries();
            this.price = flightOfferSearch.getPrice();
            this.pricingOptions = flightOfferSearch.getPricingOptions();
            this.validatingAirlineCodes = flightOfferSearch.getValidatingAirlineCodes();
            this.travelerPricings = flightOfferSearch.getTravelerPricings();
            this.choiceProbability = flightOfferSearch.getChoiceProbability();
            this.fareRules = flightOfferSearch.getFareRules();
            return this;
        }

    }

}
