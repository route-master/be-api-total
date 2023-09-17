package org.routemaster.api.total.domain.recommend.data;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;
import reactor.core.publisher.Mono;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TourismClimateIndexItem {

    private String date;
    private String totalCityName;
    private String doName;
    private String cityName;
    private String cityAreaId;
    private Integer tourApiAreaCode; // TODO mapping
    private Integer tourApiSigunguCode; // TODO mapping
    private double kmaTci;
    private String TCI_GRADE;

    public static final class TourismClimateIndexItemBuilder {

        public TourismClimateIndexItemBuilder buildFromJsonNode(JsonNode jsonNode) {
            this.date = jsonNode.get("tm").asText();
            this.totalCityName = jsonNode.get("totalCityName").asText();
            this.doName = jsonNode.get("doName").asText();
            this.cityName = jsonNode.get("cityName").asText();
            this.cityAreaId = jsonNode.get("cityAreaId").asText();
            this.kmaTci = jsonNode.get("kmaTci").asDouble();
            this.TCI_GRADE = jsonNode.get("TCI_GRADE").asText();
            return this;
        }
    }

}
