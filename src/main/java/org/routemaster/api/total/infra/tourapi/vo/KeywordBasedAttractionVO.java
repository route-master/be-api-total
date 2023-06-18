package org.routemaster.api.total.infra.tourapi.vo;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class KeywordBasedAttractionVO extends AttractionVO {

    public KeywordBasedAttractionVO(JsonNode item) throws ParseException {
        super(item);
    }

}
