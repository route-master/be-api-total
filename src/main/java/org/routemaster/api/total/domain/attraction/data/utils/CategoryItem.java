package org.routemaster.api.total.domain.attraction.data.utils;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@ToString
public class CategoryItem {

    private String categoryCode;
    private String categoryName;
    private Integer rowNumber;

    public static final class CategoryItemBuilder {
        public CategoryItemBuilder buildFromJsonNode(JsonNode jsonNode) {
            this.categoryCode = jsonNode.get("code").asText();
            this.categoryName = jsonNode.get("name").asText();
            this.rowNumber = jsonNode.get("rnum").asInt();
            return this;
        }
    }

}
