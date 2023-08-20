package org.routemaster.api.total.domain.navigation.data.subdata;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class RouteSection {

    private Integer distance;
    private Integer duration;
    private BoundingBox boundingBox;
    private List<Road> roads;

    public static class RouteSectionBuilder {
        public RouteSectionBuilder buildBoundingBox(JsonNode jsonNode) {
            BoundingBox boudingBox = BoundingBox.builder().buildFromJsonNode(jsonNode).build();
            return this;
        }
        public RouteSectionBuilder buildRoads(JsonNode jsonNode) {
            this.roads = new ArrayList<>();
            for (JsonNode roadNode : jsonNode) {
                Road road = Road.builder()
                        .name(roadNode.get("name").asText())
                        .distance(roadNode.get("distance").asInt())
                        .duration(roadNode.get("duration").asInt())
                        .trafficSpeed(roadNode.get("traffic_speed").asDouble())
                        .buildTrafficState(roadNode.get("traffic_state").asInt())
                        .buildVertexes(roadNode.get("vertexes"))
                        .build();
                this.roads.add(road);
            }
            return this;
        }
    }

}
