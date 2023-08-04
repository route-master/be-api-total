package org.routemaster.api.total.domain.attraction.data.utils;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.domain.attraction.data.TourApiResponseHeader;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@ToString
public class AreaCodeSearchResponse {

    @Schema(
            description = "API 호출 결과 헤더"
    ) private TourApiResponseHeader header;
    @Schema(
            description = "지역 코드 검색 결과"
    ) private List<AreaCodeItem> areaCodeItems;

    public static class AreaCodeSearchResponseBuilder {
        public AreaCodeSearchResponseBuilder buildFromJsonNode(JsonNode jsonNode) {
            this.header = TourApiResponseHeader.builder()
                    .buildFromJsonNode(jsonNode)
                    .build();
            this.areaCodeItems = new ArrayList<>();
            jsonNode.get("response").get("body").get("items").get("item").forEach(item -> {
                try {
                    AreaCodeItem areaCodeItem = AreaCodeItem.builder()
                            .buildFromJsonNode(item)
                            .build();
                    this.areaCodeItems.add(areaCodeItem);
                } catch (Exception e) {
                    e.printStackTrace();
                }
        });
            return this;
        }
    }

}
