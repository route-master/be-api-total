package org.routemaster.api.total.domain.navigation.data;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.domain.navigation.data.subdata.Route;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class CarNavigationRoutes {

    private String transId;
    private List<Route> routes;

    public static class CarNavigationRoutesBuilder {
        public CarNavigationRoutesBuilder buildRoutes(JsonNode jsonNode) {
            this.routes = new ArrayList<>();
            JsonNode jsonNodeSummary = jsonNode.get(0).get("summary");
                Route route = Route.builder()
                        .originName(jsonNodeSummary.get("origin").get("name").asText())
                        .originX(jsonNodeSummary.get("origin").get("x").asDouble())
                        .originY(jsonNodeSummary.get("origin").get("y").asDouble())
                        .destinationName(jsonNodeSummary.get("destination").get("name").asText())
                        .destinationX(jsonNodeSummary.get("destination").get("x").asDouble())
                        .destinationY(jsonNodeSummary.get("destination").get("y").asDouble())
                        .priority(jsonNodeSummary.get("priority").asText())
                        .buildBoundingBox(jsonNodeSummary.get("bound"))
                        .taxiFare(jsonNodeSummary.get("fare").get("taxi").asInt())
                        .tollFare(jsonNodeSummary.get("fare").get("toll").asInt())
                        .distance(jsonNodeSummary.get("distance").asInt())
                        .duration(jsonNodeSummary.get("duration").asInt())
                        .buildRouteSections(jsonNode.get(0).get("sections"))
                        .build();
                this.routes.add(route);
            return this;
        }
    }
}
