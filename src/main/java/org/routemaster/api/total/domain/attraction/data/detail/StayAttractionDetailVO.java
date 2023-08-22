package org.routemaster.api.total.domain.attraction.data.detail;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@ToString
@Schema(
        name = "StayAttractionDetailVO",
        description = "숙박 시설 상세 정보(수용 인원, 체크인/체크아웃 시간 등)"
)
public class StayAttractionDetailVO {

    @Schema(
            description = "API 호출 결과의 상태 코드",
            example = "0000"
    ) private @Getter String resultCode;
    @Schema(
            description = "API 호출 결과의 상태 메시지",
            example = "OK"
    ) private @Getter String resultMessage;
    @Schema(
            description = "한 페이지 당 결과 수",
            example = "10"
    ) private @Getter Integer numOfRows;
    @Schema(
            description = "페이지 번호",
            example = "1"
    ) private @Getter Integer pageNo;
    @Schema(
            description = "전체 결과 수",
            example = "100"
    ) private @Getter Integer totalCount;
    @Schema(
            description = "숙박 시설 상세 정보"
    ) private @Getter StayAttractionDetail detail;

    private static class StayAttractionDetail {
        @Schema(
                description = "수용 인원"
        ) private @Getter String accomodationCount;
        @Schema(
                description = "베네키아(한국형 비지니스 호텔급 체인브랜드) 유무",
                example = "N"
        ) private @Getter boolean benikia;
        @Schema(
                description = "입실 시간",
                example = "15:00"
        ) private @Getter String checkInTime;
        @Schema(
                description = "퇴실 시간",
                example = "11:00"
        ) private @Getter String checkOutTime;
        @Schema(
                description = "객실 내 취사 여부",
                example = "불가"
        ) private @Getter String cookingInfo;
        @Schema(
                description = "식음료장",
                example = "Cafe, Restaurant"
        ) private @Getter String foodPlace;
        @Schema(
                description = "굿스테이 여부"
        ) private @Getter boolean goodStay;
        @Schema(
                description = "한옥 여부"
        ) private @Getter boolean hanok;
        @Schema(
                description = "문의 및 안내",
                example = "02-400-6641~3"
        ) private @Getter String infoCenter;
        @Schema(
                description = "주차 시설",
                example = "주차 가능(발렛파킹 무료)"
        ) private @Getter String parking;
        @Schema(
                description = "픽업 서비스"
        ) private @Getter String pickup;
        @Schema(
                description = "객실 수",
                example = "71실"
        ) private @Getter String roomCount;
        @Schema(
                description = "예약 안내",
                example = "02-400-6641~3"
        ) private @Getter String reservation;
        @Schema(
                description = "예약 안내 홈페이지",
                example = "<a href=\\\"http://www.hotelkarak.com\\\" target=\\\"_blank\\\" title=\\\"새창 : 가락관광호텔 홈페이지로 이동\\\">http://www.hotelkarak.com</a>"
        ) private @Getter String reservationUrl;
        @Schema(
                description = "객실 유형",
                example = "스탠다드 더블/트윈, 디럭스 더블, 주니어 스위트, 로얄 스위트"
        ) private @Getter String roomType;
        @Schema(
                description = "규모",
                example = "지상 8층/지하 3층"
        ) private @Getter String scale;
        @Schema(
                description = "부대 시설(기타)"
        ) private @Getter String subFacility;
        @Schema(
                description = "바베큐장 여부"
        ) private @Getter boolean barbecue;
        @Schema(
                description = "뷰티 시설 정보"
        ) private @Getter boolean beautyFacility;
        @Schema(
                description = "식음료장 여부"
        ) private @Getter boolean beverage;
        @Schema(
                description = "자전거 대여 여부"
        ) private @Getter boolean bicycleRent;
        @Schema(
                description = "캠프파이어 여부"
        ) private @Getter boolean campfire;
        @Schema(
                description = "피트니스 센터 여부"
        ) private @Getter boolean fitness;
        @Schema(
                description = "노래방 여부"
        ) private @Getter boolean karaoke;
        @Schema(
                description = "공용 샤워실 여부"
        ) private @Getter boolean publicBath;
        @Schema(
                description = "공용 PC실 여부"
        ) private @Getter boolean publicPC;
        @Schema(
                description = "사우나실 여부"
        ) private @Getter boolean sauna;
        @Schema(
                description = "세미나실 여부"
        ) private @Getter boolean seminar;
        @Schema(
                description = "스포츠 시설 여부"
        ) private @Getter boolean sports;
        @Schema(
                description = "환불 규정"
        ) private @Getter String refundPolicy;
    }

    public static final class StayAttractionDetailVOBuilder {
        public StayAttractionDetailVOBuilder builder(JsonNode jsonNode) {
            this.detail = new StayAttractionDetail();
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
                this.detail.barbecue = item.get("barbecue").asText().equals("1") || this.detail.subFacility.contains("바베큐");
                this.detail.beautyFacility = item.get("beauty").asText().equals("1") || this.detail.subFacility.contains("뷰티");
                this.detail.beverage = item.get("beverage").asText().equals("1") || this.detail.subFacility.contains("음료");
                this.detail.bicycleRent = item.get("bicycle").asText().equals("1");
                this.detail.campfire = item.get("campfire").asText().equals("1") || this.detail.subFacility.contains("캠프파이어");
                this.detail.fitness = item.get("fitness").asText().equals("1") || this.detail.subFacility.contains("피트니스") || this.detail.subFacility.contains("헬스");
                this.detail.karaoke = item.get("karaoke").asText().equals("1") || this.detail.subFacility.contains("노래방");
                this.detail.publicBath = item.get("publicbath").asText().equals("1") || this.detail.subFacility.contains("공용샤워");
                this.detail.publicPC = item.get("publicpc").asText().equals("1") || this.detail.subFacility.contains("pc");
                this.detail.sauna = item.get("sauna").asText().equals("1") || this.detail.subFacility.contains("사우나");
                this.detail.seminar = item.get("seminar").asText().equals("1") || this.detail.subFacility.contains("세미나");
                this.detail.sports = item.get("sports").asText().equals("1") || this.detail.subFacility.contains("스포츠");
                this.detail.refundPolicy = item.get("refundregulation").asText().isEmpty() ? null : item.get("refundregulation").asText();
            });
            return this;
        }
    }

}
