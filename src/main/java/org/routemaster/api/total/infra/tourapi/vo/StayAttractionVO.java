package org.routemaster.api.total.infra.tourapi.vo;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;

import java.text.ParseException;

public class StayAttractionVO extends AttractionVO {

    private @Getter Boolean benikia;
    private @Getter Boolean goodStay;
    private @Getter Boolean hanok;

    public StayAttractionVO(JsonNode item) throws ParseException {
        super(item);
        this.benikia = !item.get("benikia").asText().isEmpty();
        this.goodStay = !item.get("goodstay").asText().isEmpty();
        this.hanok = !item.get("hanok").asText().isEmpty();
    }

}
