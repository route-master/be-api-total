package org.routemaster.api.total.infra.tourapi.vo;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class FestivalAttractionVO extends AttractionVO {

    private @Getter Date eventStartDate;
    private @Getter Date eventEndDate;

    public FestivalAttractionVO(JsonNode item) throws ParseException {
        super(item);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        this.eventStartDate = item.get("eventstartdate").asText().isEmpty() ? null : formatter.parse(item.get("eventstartdate").asText());;
        this.eventEndDate = item.get("eventenddate").asText().isEmpty() ? null : formatter.parse(item.get("eventenddate").asText());;
    }

}
