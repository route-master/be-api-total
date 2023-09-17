package org.routemaster.api.total.domain.navigation.data.subdata;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class Road {

    private String name;
    private Integer distance;
    private Integer duration;
    private Double trafficSpeed;
    private String trafficState;
    private List<MapPoint> vertexes;

    public static class RoadBuilder {
        public RoadBuilder buildVertexes(JsonNode jsonNode) {
            vertexes = new ArrayList<>();
            List<Double> tmp = new ArrayList<>();
            for (var points : jsonNode) {
                tmp.add(points.asDouble());
            }
            for (var i = 0; i < (tmp.size() / 2); i++) {
                MapPoint mapPoint = MapPoint.builder()
                        .x(tmp.get(i * 2))
                        .y(tmp.get(i * 2 + 1))
                        .build();
                this.vertexes.add(mapPoint);
            }
            return this;
        }

        public RoadBuilder buildTrafficState(Integer stateCode) {
            switch (stateCode) {
                case 0:
                    trafficState = "정보 없음";
                    break;
                case 1:
                    trafficState = "정체";
                    break;
                case 2:
                    trafficState = "지체";
                    break;
                case 3:
                    trafficState = "서행";
                    break;
                case 4:
                    trafficState = "원활";
                    break;
                case 6:
                    trafficState = "교통사고(통행 불가)";
                    break;
            }
            return this;
        }
    }

}
