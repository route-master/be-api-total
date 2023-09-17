package org.routemaster.api.total.domain.navigation.data.subdata;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class BoundingBox {
    private Double leftTopX;
    private Double leftTopY;
    private Double rightBottomX;
    private Double rightBottomY;

    public static class BoundingBoxBuilder {
        public BoundingBoxBuilder buildFromJsonNode(JsonNode jsonNode) {
            this.leftTopX = jsonNode.get("min_x").asDouble();
            this.leftTopY = jsonNode.get("min_y").asDouble();
            this.rightBottomX = jsonNode.get("max_x").asDouble();
            this.rightBottomY = jsonNode.get("max_y").asDouble();
            return this;
        }
    }
    
}
