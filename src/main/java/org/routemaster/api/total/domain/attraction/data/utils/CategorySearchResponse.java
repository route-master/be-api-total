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
public class CategorySearchResponse {

    private TourApiResponseHeader header;
    private List<CategoryItem> categoryItems;

    public static final class CategorySearchResponseBuilder {
        public CategorySearchResponseBuilder buildFromJsonNode(JsonNode jsonNode) {
            this.header = TourApiResponseHeader.builder()
                    .buildFromJsonNode(jsonNode)
                    .build();
            this.categoryItems = new ArrayList<>();
            jsonNode.get("response").get("body").get("items").get("item").forEach(item -> {
                try {
                    CategoryItem categoryItem = CategoryItem.builder()
                            .buildFromJsonNode(item)
                            .build();
                    this.categoryItems.add(categoryItem);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            return this;
        }
    }

}
