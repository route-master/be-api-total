package org.routemaster.api.total.domain.navigation.data.subdata;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class Route {

    private String originName;
    private Double originX;
    private Double originY;
    private String destinationName;
    private Double destinationX;
    private Double destinationY;
    private String priority;
    private BoundingBox boundingBox;
    private Integer taxiFare;
    private Integer tollFare;
    private Integer distance;
    private Integer duration;
    private List<RouteSection> routeSections;

    public static class RouteBuilder {
        public RouteBuilder buildBoundingBox(JsonNode jsonNode) {
            BoundingBox boudingBox = BoundingBox.builder().buildFromJsonNode(jsonNode).build();
            return this;
        }
        public RouteBuilder buildRouteSections(JsonNode jsonNode) {
            this.routeSections = new ArrayList<>();
            for (JsonNode routeSectionNode : jsonNode) {
                RouteSection routeSection = RouteSection.builder()
                        .distance(routeSectionNode.get("distance").asInt())
                        .duration(routeSectionNode.get("duration").asInt())
                        .buildBoundingBox(routeSectionNode.get("bound"))
                        .buildRoads(routeSectionNode.get("roads"))
                        .build();
                this.routeSections.add(routeSection);
            }
            return this;
        }
    }

}
