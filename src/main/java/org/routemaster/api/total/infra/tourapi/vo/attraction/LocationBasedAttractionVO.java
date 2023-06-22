package org.routemaster.api.total.infra.tourapi.vo.attraction;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.text.ParseException;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class LocationBasedAttractionVO extends AttractionVO {

    @Schema(
            description = "중심 좌표로부터의 거리(단위: m)",
            example = "616.0375045908319"
    ) private @Getter Double dist;

    public LocationBasedAttractionVO(JsonNode item) throws ParseException {
        super(item);
        this.dist = item.get("dist").asDouble();
    }


}
