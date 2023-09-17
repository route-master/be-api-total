package org.routemaster.api.total.domain.attraction.data.search;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.text.ParseException;

public class StayAttractionVO extends AttractionVO {

    @Schema(
            description = "베니키아 여부"
    ) private @Getter Boolean benikia;
    @Schema(
            description = "굿스테이 여부"
    ) private @Getter Boolean goodStay;
    @Schema(
            description = "한옥 여부"
    ) private @Getter Boolean hanok;

    public StayAttractionVO(JsonNode item) throws ParseException {
        super(item);
        this.benikia = !item.get("benikia").asText().isEmpty();
        this.goodStay = !item.get("goodstay").asText().isEmpty();
        this.hanok = !item.get("hanok").asText().isEmpty();
    }

}
