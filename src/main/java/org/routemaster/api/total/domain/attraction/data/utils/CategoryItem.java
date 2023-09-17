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
public class CategoryItem {

    @Schema(
            description = "대/중/소분류 코드",
            example = "A01"
    ) private String categoryCode;
    @Schema(
            description = "대/중/소분류 코드명",
            example = "자연"
    ) private String categoryName;
    @Schema(
            description = "일련번호",
            example = "1"
    ) private Integer rowNumber;

    public static final class CategoryItemBuilder {
        public CategoryItemBuilder buildFromJsonNode(JsonNode jsonNode) {
            this.categoryCode = jsonNode.get("code").asText();
            this.categoryName = jsonNode.get("name").asText();
            this.rowNumber = jsonNode.get("rnum").asInt();
            return this;
        }
    }

}
