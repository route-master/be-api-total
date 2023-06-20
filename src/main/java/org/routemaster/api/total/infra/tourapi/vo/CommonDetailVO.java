package org.routemaster.api.total.infra.tourapi.vo;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;

import java.text.ParseException;
import java.util.Objects;

public class CommonDetailVO extends AttractionVO {

    private @Getter String homepage;
    private @Getter String zipcode;
    private @Getter String overview;

    public CommonDetailVO(JsonNode item) throws ParseException {
        super(item);
        this.homepage = item.get("homepage").asText().isEmpty() ? null : item.get("homepage").asText();
        this.zipcode = item.get("zipcode").asText().isEmpty() ? null : item.get("zipcode").asText();
        this.overview = item.get("overview").asText().isEmpty() ? null : item.get("overview").asText();
        if (Objects.equals(this.overview, "-")) {
            this.overview = null;
        }
    }

}
