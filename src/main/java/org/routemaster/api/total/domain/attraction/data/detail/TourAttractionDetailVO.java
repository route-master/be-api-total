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
        name = "TourAttractionDetailVO",
        description = "관광지 상세 정보"
)
public class TourAttractionDetailVO {

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
            description = "관광지 상세 정보"
    ) private @Getter TourAttractionDetail detail;

    private static class TourAttractionDetail {
        @Schema(
                description = "수용 인원"
        ) private @Getter Integer accomodationCount;
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
                description = "체험 안내"
        ) private @Getter String experienceGuide;
        @Schema(
                description = "세계문화유산 유무"
        ) private @Getter boolean cultureHeritage;
        @Schema(
                description = "세계자연유산 유무"
        ) private @Getter boolean natureHeritage;
        @Schema(
                description = "세계기록유산 유무"
        ) private @Getter boolean recordHeritage;
        @Schema(
                description = "문의 및 안내",
                example = "02-777-6090"
        ) private @Getter String infoCenter;
        @Schema(
                description = "개장일"
        ) private @Getter String openDate;
        @Schema(
                description = "주차 시설",
                example = "없음"
        ) private @Getter String parking;
        @Schema(
                description = "쉬는 날",
                example = "매주 월요일"
        ) private @Getter String restDate;
        @Schema(
                description = "이용 시기"
        ) private @Getter String seasonsOfOperation;
        @Schema(
                description = "이용 시간"
        ) private @Getter String hoursOfOperation;
    }

    public final static class TourAttractionDetailVOBuilder {

        public TourAttractionDetailVOBuilder builder(JsonNode jsonNode) {
            this.detail = new TourAttractionDetail();
            jsonNode.get("items").get("item").forEach(item -> {
                    this.detail.accomodationCount = item.get("accomcount").asText().isEmpty() ? null : item.get("accomCount").asInt();
                    this.detail.babyCarriageInfo = item.get("chkbabycarriage").asText().isEmpty() ? null : item.get("chkbabycarriage").asText();
                    this.detail.creditcardInfo = item.get("chkcreditcard").asText().isEmpty() ? null : item.get("chkcreditcard").asText();
                    this.detail.allowPetInfo = item.get("chkpet").asText().isEmpty() ? null : item.get("chkpet").asText();
                    this.detail.experienceAgeRange = item.get("expguide").asText().isEmpty() ? null : item.get("expguide").asText();
                    this.detail.experienceGuide = item.get("expguide").asText().isEmpty() ? null : item.get("expguide").asText();
                    this.detail.cultureHeritage = item.get("heritage1").asText().equals("1");
                    this.detail.natureHeritage = item.get("heritage2").asText().equals("1");
                    this.detail.recordHeritage = item.get("heritage3").asText().equals("1");
                    this.detail.infoCenter = item.get("infocenter").asText().isEmpty() ? null : item.get("infocenter").asText();
                    this.detail.openDate = item.get("opendate").asText().isEmpty() ? null : item.get("opendate").asText();
                    this.detail.parking = item.get("parking").asText().isEmpty() ? null : item.get("parking").asText();
                    this.detail.restDate = item.get("restdate").asText().isEmpty() ? null : item.get("restdate").asText();
                    this.detail.seasonsOfOperation = item.get("useseason").asText().isEmpty() ? null : item.get("useseason").asText();
                    this.detail.hoursOfOperation = item.get("usetime").asText().isEmpty() ? null : item.get("usetime").asText();
            });
            return this;
        }
    }

}
