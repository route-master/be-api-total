package org.routemaster.api.total.domain.attraction.data;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Setter
@ToString
public class TourApiResponseHeader {

    @Schema(
            description = "API 호출 결과의 상태 코드",
            example = "0000"
    ) private String resultCode;
    @Schema(
            description = "API 호출 결과의 상태 메시지",
            example = "OK"
    ) private String resultMessage;
    @Schema(
            description = "한 페이지 당 결과 수",
            example = "10"
    ) private Integer numOfRows;
    @Schema(
            description = "페이지 번호",
            example = "1"
    ) private Integer pageNo;
    @Schema(
            description = "전체 결과 수",
            example = "100"
    ) private Integer totalCount;

    public static final class TourApiResponseHeaderBuilder {
        public TourApiResponseHeaderBuilder buildFromJsonNode(JsonNode jsonNode) {
            this.resultCode = jsonNode.get("response").get("header").get("resultCode").asText();
            this.resultMessage = jsonNode.get("response").get("header").get("resultMsg").asText();
            this.numOfRows = jsonNode.get("response").get("body").get("numOfRows").asInt();
            this.pageNo = jsonNode.get("response").get("body").get("pageNo").asInt();
            this.totalCount = jsonNode.get("response").get("body").get("totalCount").asInt();
            return this;
        }
    }

}