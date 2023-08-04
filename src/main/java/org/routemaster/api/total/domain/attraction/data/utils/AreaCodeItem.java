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
public class AreaCodeItem {

    private String areaCode;
    private String areaName;
    private Integer rowNumber;

    public static final class AreaCodeItemBuilder {
        public AreaCodeItemBuilder buildFromJsonNode(JsonNode jsonNode) {
            this.areaCode = jsonNode.get("code").asText();
            this.areaName = jsonNode.get("name").asText();
            this.rowNumber = jsonNode.get("rnum").asInt();
            return this;
        }
    }

}
