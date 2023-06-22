package org.routemaster.api.total.infra.tourapi.vo.detail;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@ToString
public class CourseAttractionDetailVO {

    private @Getter String resultCode;
    private @Getter String resultMessage;
    private @Getter Integer numOfRows;
    private @Getter Integer pageNo;
    private @Getter Integer totalCount;
    private @Getter Detail detail;

    private static class Detail {
        private @Getter String distance;
        private @Getter String spendTime;
        private @Getter String theme;
        private @Getter String infoCenter;
        private @Getter String schedule;
    }

    public static final class CourseAttractionDetailVOBuilder {
        public CourseAttractionDetailVOBuilder builder(JsonNode jsonNode) {
            this.detail = new Detail();
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
