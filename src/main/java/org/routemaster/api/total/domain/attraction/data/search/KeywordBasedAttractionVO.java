package org.routemaster.api.total.domain.attraction.data.search;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
