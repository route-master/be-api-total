package org.routemaster.api.total.domain.attraction.data.search;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class AreaBasedAttractionVO  extends AttractionVO {

    @Schema(
            description = "우편번호",
            example = "03072"
    ) private @Getter String zipCode;

    public AreaBasedAttractionVO(JsonNode item) throws ParseException {
        super(item);
        this.zipCode = item.get("zipcode").asText().isEmpty() ? null : item.get("zipcode").asText();
    }

}
