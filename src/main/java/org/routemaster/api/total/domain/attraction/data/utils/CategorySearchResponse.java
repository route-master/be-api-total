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
public class CategorySearchResponse {

    @Schema(
            description = "API 호출 결과 헤더"
    ) private TourApiResponseHeader header;
    @Schema(
            description = "카테고리 검색 결과"
    ) private List<CategoryItem> categoryItems;

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
