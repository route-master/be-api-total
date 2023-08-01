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
        name = "CourseAttractionDetailVO",
        description = "여행 코스 상세 정보(거리, 소요시간, 테마, 문의 및 안내, 코스 일정 등)"
)
public class CourseAttractionDetailVO {

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
    private @Getter CourseAttractionDetail detail;

    private static class CourseAttractionDetail {
        @Schema(
                description = "거리",
                example = "3.43km"
        )
        private @Getter String distance;
        @Schema(
                description = "소요시간",
                example = "4시간"
        )
        private @Getter String spendTime;
        @Schema(
                description = "코스 테마",
                example = "지자체"
        )
        private @Getter String theme;
        @Schema(
                description = "문의 및 안내",
                nullable = true
        )
        private @Getter String infoCenter;
        @Schema(
                description = "코스 일정",
                example = "기타"
        )
        private @Getter String schedule;
    }

    public static final class CourseAttractionDetailVOBuilder {
        public CourseAttractionDetailVOBuilder builder(JsonNode jsonNode) {
            this.detail = new CourseAttractionDetail();
            jsonNode.get("items").get("item").forEach(item -> {
                this.detail.distance = item.get("distance").asText().isEmpty() ? null : item.get("distance").asText();
                this.detail.spendTime = item.get("taketime").asText().isEmpty() ? null : item.get("taketime").asText();
                this.detail.theme = item.get("theme").asText().isEmpty() ? null : item.get("theme").asText();
                this.detail.infoCenter = item.get("infocentertourcourse").asText().isEmpty() ? null : item.get("infocentertourcourse").asText();
                this.detail.schedule = item.get("schedule").asText().isEmpty() ? null : item.get("schedule").asText();
            });
            return this;
        }
    }

}
