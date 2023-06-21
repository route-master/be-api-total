package org.routemaster.api.total.infra.tourapi.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @Schema(
            description = "행사 시작일",
            example = "2023-07-01"
    ) private @Getter Date eventStartDate;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @Schema(
            description = "행사 종료일",
            example = "2021-07-10"
    ) private @Getter Date eventEndDate;

    public FestivalAttractionVO(JsonNode item) throws ParseException {
        super(item);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        this.eventStartDate = item.get("eventstartdate").asText().isEmpty() ? null : formatter.parse(item.get("eventstartdate").asText());;
        this.eventEndDate = item.get("eventenddate").asText().isEmpty() ? null : formatter.parse(item.get("eventenddate").asText());;
    }

}
