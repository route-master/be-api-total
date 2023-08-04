package org.routemaster.api.total.domain.attraction.data.utils;

import com.fasterxml.jackson.databind.JsonNode;
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

    private TourApiResponseHeader header;
    private List<AreaCodeItem> areaCodeItems;

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
