package org.routemaster.api.total.domain.attraction.data.detail;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@ToString
@Schema(
        name = "CultureAttractionDetailVO",
        description = "문화 시설 상세 정보(수용 인원, 유모차 대여 정보 등)"
)
public class CultureAttractionDetailVO {

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
            description = "문화 시설 상세 정보"
    ) private @Getter CultureAttractionDetail detail;

    private static class CultureAttractionDetail {
        @Schema(
                description = "수용 인원"
        )
        private @Getter Integer accomodationCount;
        @Schema(
                description = "유모차 대여 정보",
                example = "없음"

        )
        private @Getter String babyCarriageInfo;
        @Schema(
                description = "신용카드 가능 정보",
                example = "없음"
        )
        private @Getter String creditcardInfo;
        @Schema(
                description = "애완동물 동반 가능 정보",
                example = "불가"
        )
        private @Getter String allowPetInfo;
        @Schema(
                description = "할인 정보"
        )
        private @Getter String discountInfo;
        @Schema(
                description = "문의 및 안내",
                example = "02-762-0442<br/>\\n간송미술문화재단 070-7774-2524"
        )
        private @Getter String infoCenter;
        @Schema(
                description = "주차 시설",
                example = "주차 가능"
        )
        private @Getter String parking;
        @Schema(
                description = "주차 요금",
                example = "주차 가능"
        )
        private @Getter String parkingFee;
        @Schema(
                description = "쉬는 날",
                example = "매주 월요일"
        )
        private @Getter String restDate;
        @Schema(
                description = "이용 요금",
                example = "무료"
        )
        private @Getter String usageFee;
        @Schema(
                description = "이용 시간",
                example = "10:00~19:00 평일, 주말, 공휴일<br />\\n10:00~21:00 금요일, 토요일"
        )
        private @Getter String hoursOfOperation;
        @Schema(
                description = "규모"
        )
        private @Getter String scale;
        @Schema(
                description = "소요 시간",
                example = "1시간"
        )
        private @Getter String spendTime;
    }

    public final static class CultureAttractionDetailVOBuilder {

        public CultureAttractionDetailVOBuilder builder(JsonNode jsonNode) {
            this.detail = new CultureAttractionDetail();
            jsonNode.get("items").get("item").forEach(item -> {
                this.detail.accomodationCount = item.get("accomcountculture").asText().isEmpty() ? null : item.get("accomcountculture").asInt();
                this.detail.babyCarriageInfo = item.get("chkbabycarriageculture").asText().isEmpty() ? null : item.get("chkbabycarriageculture").asText();
                this.detail.creditcardInfo = item.get("chkcreditcardculture").asText().isEmpty() ? null : item.get("chkcreditcardculture").asText();
                this.detail.allowPetInfo = item.get("chkpetculture").asText().isEmpty() ? null : item.get("chkpetculture").asText();
                this.detail.discountInfo = item.get("discountinfo").asText().isEmpty() ? null : item.get("discountinfo").asText();
                this.detail.infoCenter = item.get("infocenterculture").asText().isEmpty() ? null : item.get("infocenterculture").asText();
                this.detail.parking = item.get("parkingculture").asText().isEmpty() ? null : item.get("parkingculture").asText();
                this.detail.parkingFee = item.get("parkingfee").asText().isEmpty() ? null : item.get("parkingfee").asText();
                this.detail.restDate = item.get("restdateculture").asText().isEmpty() ? null : item.get("restdateculture").asText();
                this.detail.usageFee = item.get("usefee").asText().isEmpty() ? null : item.get("usefee").asText();
                this.detail.hoursOfOperation = item.get("usetimeculture").asText().isEmpty() ? null : item.get("usetimeculture").asText();
                this.detail.scale = item.get("scale").asText().isEmpty() ? null : item.get("scale").asText();
                this.detail.spendTime = item.get("spendtime").asText().isEmpty() ? null : item.get("spendtime").asText();
            });
            return this;
        }

    }
}

