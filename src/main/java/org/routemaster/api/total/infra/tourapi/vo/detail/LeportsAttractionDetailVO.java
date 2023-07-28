package org.routemaster.api.total.infra.tourapi.vo.detail;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@ToString
public class LeportsAttractionDetailVO {

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
            description = "레포츠 상세 정보"
    )
    private @Getter LeportsAttractionDetail detail;

    private static class LeportsAttractionDetail {
        @Schema(
                description = "수용 인원"
        ) private @Getter String accomodationCount;
        @Schema(
                description = "유모차 대여 정보",
                example = "없음"
        ) private @Getter String babyCarriageInfo;
        @Schema(
                description = "신용카드 가능 정보",
                example = "없음"
        ) private @Getter String creditcardInfo;
        @Schema(
                description = "애완동물 동반 가능 정보",
                example = "불가"
        ) private @Getter String allowPetInfo;
        @Schema(
                description = "체험 가능 연령"
        ) private @Getter String experienceAgeRange;
        @Schema(
                description = "문의 및 안내",
                example = "02-544-9725"
        ) private @Getter String infoCenter;
        @Schema(
                description = "개장 기간"
        ) private @Getter String openPeriod;
        @Schema(
                description = "주차 시설",
                example = "주차 가"
        ) private @Getter String parking;
        @Schema(
                description = "주차 요금"
        ) private @Getter String parkingFee;
        @Schema(
                description = "예약 안내",
                example = "전화 문의 02-544-9725"
        ) private @Getter String reservation;
        @Schema(
                description = "쉬는 날",
                example = "매주 월요일"
        ) private @Getter String restDate;
        @Schema(
                description = "규모"
        ) private @Getter String scale;
        @Schema(
                description = "이용 요금",
                example = "무료"
        ) private @Getter String usageFee;
        @Schema(
                description = "이용 시간",
                example = "10:00~19:00 평일, 주말, 공휴일<br />\\n10:00~21:00 금요일, 토요일"
        ) private @Getter String hoursOfOperation;
    }

    public static final class LeportsAttractionDetailVOBuilder {
        public LeportsAttractionDetailVOBuilder builder(JsonNode jsonNode) {
            this.detail = new LeportsAttractionDetail();
            jsonNode.get("items").get("item").forEach(item -> {
                this.detail.accomodationCount = item.get("accomcountleports").asText().isEmpty() ? null : item.get("accomcountleports").asText();
                this.detail.babyCarriageInfo = item.get("chkbabycarriageleports").asText().isEmpty() ? null : item.get("chkbabycarriageleports").asText();
                this.detail.creditcardInfo = item.get("chkcreditcardleports").asText().isEmpty() ? null : item.get("chkcreditcardleports").asText();
                this.detail.allowPetInfo = item.get("chkpetleports").asText().isEmpty() ? null : item.get("chkpetleports").asText();
                this.detail.experienceAgeRange = item.get("expagerangeleports").asText().isEmpty() ? null : item.get("expagerangeleports").asText();
                this.detail.infoCenter = item.get("infocenterleports").asText().isEmpty() ? null : item.get("infocenterleports").asText();
                this.detail.openPeriod = item.get("openperiod").asText().isEmpty() ? null : item.get("openperiod").asText();
                this.detail.parking = item.get("parkingleports").asText().isEmpty() ? null : item.get("parkingleports").asText();
                this.detail.parkingFee = item.get("parkingfeeleports").asText().isEmpty() ? null : item.get("parkingfeeleports").asText();
                this.detail.reservation = item.get("reservation").asText().isEmpty() ? null : item.get("reservation").asText();
                this.detail.restDate = item.get("restdateleports").asText().isEmpty() ? null : item.get("restdateleports").asText();
                this.detail.scale = item.get("scaleleports").asText().isEmpty() ? null : item.get("scaleleports").asText();
                this.detail.usageFee = item.get("usefeeleports").asText().isEmpty() ? null : item.get("usefeeleports").asText();
                this.detail.hoursOfOperation = item.get("usetimeleports").asText().isEmpty() ? null : item.get("usetimeleports").asText();
            });
            return this;
        }
    }

}
