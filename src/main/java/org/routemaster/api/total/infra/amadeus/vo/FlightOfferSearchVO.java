package org.routemaster.api.total.infra.amadeus.vo;

import com.amadeus.resources.FlightOfferSearch;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(
        name = "FlightOfferSearch",
        description = "항공권 정보"
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@ToString
public class FlightOfferSearchVO {

    @Schema(
            description = "type",
            example = "flight-offer"
    ) private String type;

    @Schema(
            description = "검색 결과 인덱스",
            example = "1"
    ) private String id;

    @Schema(
            description = "source of the flight offer",
            example = "GDS",
            allowableValues = "GDS"
    ) private String source;

    @Schema(
            description = "예약 시, 항공권 발권 필요 여부",
            example = "false"
    ) private boolean instantTicketingRequired;

    @Schema(
            description = "결제 없이 PNR로 예약 가능 여부 (source가 GDS인 경우에만 해당)",
            example = "false"
    ) private boolean disablePricing;

    @Schema(
            description = "예약 완료 시, PNR을 포함하는 레코드가 여러 개 생성되는지 여부(승객 수와 좌석 수가 불일치할 때 생성됨)",
            example = "false"
    ) private boolean nonHomogeneous;

    @Schema(
            description = "편도 항공권 여부",
            example = "false"
    ) private boolean oneWay;
    private boolean paymentCardRequired;
    private String lastTicketingDate;

    @Schema(
            description = "한 번에 예약 가능한 좌석 개수",
            minimum = "1",
            maximum = "9"
    ) private int numberOfBookableSeats;

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
