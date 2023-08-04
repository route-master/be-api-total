package org.routemaster.api.total.domain.attraction.data.utils;

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
public class AreaCodeItem {

    @Schema(
            description = "지역 코드 또는 시군구 코드",
            example = "1"
    ) private String areaCode;
    @Schema(
            description = "지역명 또는 시군구명",
            example = "서울"
    ) private String areaName;
    @Schema(
            description = "일련번호",
            example = "1"
    ) private Integer rowNumber;

    public static final class AreaCodeItemBuilder {
        public AreaCodeItemBuilder buildFromJsonNode(JsonNode jsonNode) {
            this.areaCode = jsonNode.get("code").asText();
            this.areaName = jsonNode.get("name").asText();
            this.rowNumber = jsonNode.get("rnum").asInt();
            return this;
        }
    }

}
