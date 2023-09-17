package org.routemaster.api.total.domain.attraction.data.detail;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@ToString
public class RestaurantAttractionDetailVO {

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
            description = "음식점 상세 정보"
    ) private @Getter RestaurantAttractionDetail detail;

    private static class RestaurantAttractionDetail {
        @Schema(
                description = "신용카드 가능 정보",
                example = "없음"
        ) public @Getter String creditcardInfo;
        @Schema(
                description = "할인 정보"
        ) public @Getter String discountInfo;
        @Schema(
                description = "대표 메뉴",
                example = "게살해물요리, 고추탕수육"
        ) public @Getter String signatureMenu;
        @Schema(
                description = "문의 및 안내",
                example = "02-545-5163"
        ) public @Getter String infoCenter;
        @Schema(
                description = "어린이 놀이방 여부",
                example = "0"
        ) public @Getter String kidsFacility;
        @Schema(
                description = "개업일"
        ) public @Getter String openDate;
        @Schema(
                description = "포장 가능 여부",
                example = "가능"
        ) public @Getter String packing;
        @Schema(
                description = "주차 시설",
                example = "주차 가능"
        ) public @Getter String parking;
        @Schema(
                description = "예약 안내",
                example = "가능"
        ) public @Getter String reservation;
        @Schema(
                description = "쉬는 날",
                example = "연중 무휴"
        ) public @Getter String restDate;
        @Schema(
                description = "규모"
        ) public @Getter String scale;
        @Schema(
                description = "금연/흡연 여부"
        ) public @Getter String smoking;
        @Schema(
                description = "취급 메뉴",
                example = "난자완스, 가담정식, 특색냉채, 오향장육, 깐풍기, 라조기"
        ) public @Getter String menu;
        @Schema(
                description = "인허가 번호",
                example = "19990106403"
        ) public @Getter String licenseNumber;
    }

    public static final class RestaurantAttractionDetailVOBuilder {
        public RestaurantAttractionDetailVOBuilder builder(JsonNode jsonNode) {
            this.detail = new RestaurantAttractionDetail();
            jsonNode.get("items").get("item").forEach(item -> {
                this.detail.creditcardInfo = item.get("chkcreditcardfood").asText().isEmpty() ? null : item.get("chkcreditcardfood").asText();
                this.detail.discountInfo = item.get("discountinfofood").asText().isEmpty() ? null : item.get("discountinfofood").asText();
                this.detail.signatureMenu = item.get("firstmenu").asText().isEmpty() ? null : item.get("firstmenu").asText();
                this.detail.infoCenter = item.get("infocenterfood").asText().isEmpty() ? null : item.get("infocenterfood").asText();
                this.detail.kidsFacility = item.get("kidsfacility").asText().isEmpty() ? null : item.get("kidsfacility").asText();
                this.detail.openDate = item.get("opendatefood").asText().isEmpty() ? null : item.get("opendatefood").asText();
                this.detail.packing = item.get("packing").asText().isEmpty() ? null : item.get("packing").asText();
                this.detail.parking = item.get("parkingfood").asText().isEmpty() ? null : item.get("parkingfood").asText();
                this.detail.reservation = item.get("reservationfood").asText().isEmpty() ? null : item.get("reservationfood").asText();
                this.detail.restDate = item.get("restdatefood").asText().isEmpty() ? null : item.get("restdatefood").asText();
                this.detail.scale = item.get("scalefood").asText().isEmpty() ? null : item.get("scalefood").asText();
                this.detail.smoking = item.get("smoking").asText().isEmpty() ? null : item.get("smoking").asText();
                this.detail.menu = item.get("treatmenu").asText().isEmpty() ? null : item.get("treatmenu").asText();
                this.detail.licenseNumber = item.get("lcnsno").asText().isEmpty() ? null : item.get("lcnsno").asText();
            });
            return this;
        }
    }

}
