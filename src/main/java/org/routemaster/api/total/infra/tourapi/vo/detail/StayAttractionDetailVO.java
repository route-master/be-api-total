package org.routemaster.api.total.infra.tourapi.vo.detail;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@ToString
public class StayAttractionDetailVO {

    private @Getter String resultCode;
    private @Getter String resultMessage;
    private @Getter Integer numOfRows;
    private @Getter Integer pageNo;
    private @Getter Integer totalCount;
    private @Getter Detail detail;

    private static class Detail {
        private @Getter String accomodationCount;
        private @Getter boolean benikia;
        private @Getter String checkInTime;
        private @Getter String checkOutTime;
        private @Getter String cookingInfo;
        private @Getter String foodPlace;
        private @Getter boolean goodStay;
        private @Getter boolean hanok;
        private @Getter String infoCenter;
        private @Getter String parking;
        private @Getter String pickup;
        private @Getter String roomCount;
        private @Getter String reservation;
        private @Getter String reservationUrl;
        private @Getter String roomType;
        private @Getter String scale;
        private @Getter String subFacility;
        private @Getter boolean barbecue;
        private @Getter boolean beautyFacility;
        private @Getter boolean beverage;
        private @Getter boolean bicycleRent;
        private @Getter boolean campfire;
        private @Getter boolean fitness;
        private @Getter boolean karaoke;
        private @Getter boolean publicBath;
        private @Getter boolean publicPC;
        private @Getter boolean sauna;
        private @Getter boolean seminar;
        private @Getter boolean sports;
        private @Getter String refundPolicy;
    }

    public static final class StayAttractionDetailVOBuilder {
        public StayAttractionDetailVOBuilder builder(JsonNode jsonNode) {
            this.detail = new Detail();
            jsonNode.get("items").get("item").forEach(item -> {
                this.detail.accomodationCount = item.get("accomcountlodging").asText().isEmpty() ? null : item.get("accomcountlodging").asText();
                this.detail.benikia = item.get("benikia").asText().equals("1");
                this.detail.checkInTime = item.get("checkintime").asText().isEmpty() ? null : item.get("checkintime").asText();
                this.detail.checkOutTime = item.get("checkouttime").asText().isEmpty() ? null : item.get("checkouttime").asText();
                this.detail.cookingInfo = item.get("chkcooking").asText().isEmpty() ? null : item.get("chkcooking").asText();
                this.detail.foodPlace = item.get("foodplace").asText().isEmpty() ? null : item.get("foodplace").asText();
                this.detail.goodStay = item.get("goodstay").asText().equals("1");
                this.detail.hanok = item.get("hanok").asText().equals("1");
                this.detail.infoCenter = item.get("infocenterlodging").asText().isEmpty() ? null : item.get("infocenterlodging").asText();
                this.detail.parking = item.get("parkinglodging").asText().isEmpty() ? null : item.get("parkinglodging").asText();
                this.detail.pickup = item.get("pickup").asText().isEmpty() ? null : item.get("pickup").asText();
                this.detail.roomCount = item.get("roomcount").asText().isEmpty() ? null : item.get("roomcount").asText();
                this.detail.reservation = item.get("reservationlodging").asText().isEmpty() ? null : item.get("reservationlodging").asText();
                this.detail.reservationUrl = item.get("reservationurl").asText().isEmpty() ? null : item.get("reservationurl").asText();
                this.detail.roomType = item.get("roomtype").asText().isEmpty() ? null : item.get("roomtype").asText();
                this.detail.scale = item.get("scalelodging").asText().isEmpty() ? null : item.get("scalelodging").asText();
                this.detail.subFacility = item.get("subfacility").asText().isEmpty() ? null : item.get("subfacility").asText();
                this.detail.barbecue = item.get("barbecue").asText().equals("1");
                this.detail.beautyFacility = item.get("beauty").asText().equals("1");
                this.detail.beverage = item.get("beverage").asText().equals("1");
                this.detail.bicycleRent = item.get("bicycle").asText().equals("1");
                this.detail.campfire = item.get("campfire").asText().equals("1");
                this.detail.fitness = item.get("fitness").asText().equals("1");
                this.detail.karaoke = item.get("karaoke").asText().equals("1");
                this.detail.publicBath = item.get("publicbath").asText().equals("1");
                this.detail.publicPC = item.get("publicpc").asText().equals("1");
                this.detail.sauna = item.get("sauna").asText().equals("1");
                this.detail.seminar = item.get("seminar").asText().equals("1");
                this.detail.sports = item.get("sports").asText().equals("1");
                this.detail.refundPolicy = item.get("refundregulation").asText().isEmpty() ? null : item.get("refundregulation").asText();
            });
            return this;
        }
    }

}
