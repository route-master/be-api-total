package org.routemaster.api.total.domain.weather.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Schema(
        name = "VeryShortLiveWeather",
        description = "단기예보조회(초단기실황)"
)
public class VeryShortLiveWeather {

    @Schema(
            description = "응답 메타데이터"
    ) private WeatherResponseHeader header;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Schema(
            description = "발표 일시",
            example = "2023-07-01 05:00"
    ) private LocalDateTime baseDateTime;
    @Schema(
            description = "예보지점 X 좌표",
            example = "55"
    ) private Integer nx;
    @Schema(
            description = "예보지점 Y 좌표",
            example = "127"
    ) private Integer ny;
    @Schema(
            description = "예보 항목 리스트"
    ) private List<ObservedItem> observedItems;

    public static final class VeryShortLiveWeatherBuilder {

        public VeryShortLiveWeatherBuilder buildFromJsonNode(JsonNode jsonNode) throws ParseException {
            this.header = WeatherResponseHeader.builder()
                    .resultCode(jsonNode.get("response").get("header").get("resultCode").asText())
                    .resultMessage(jsonNode.get("response").get("header").get("resultMsg").asText())
                    .pageNo(jsonNode.get("response").get("body").get("pageNo").asInt())
                    .numOfRows(jsonNode.get("response").get("body").get("numOfRows").asInt())
                    .totalCount(jsonNode.get("response").get("body").get("totalCount").asInt())
                    .build();
            String baseDatestr = jsonNode.get("response").get("body").get("items").get("item").get(0).get("baseDate").asText();
            String baseTimestr = jsonNode.get("response").get("body").get("items").get("item").get(0).get("baseTime").asText();
            this.baseDateTime = LocalDateTime.parse(baseDatestr + baseTimestr, DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
            this.nx = jsonNode.get("response").get("body").get("items").get("item").get(0).get("nx").asInt();
            this.ny = jsonNode.get("response").get("body").get("items").get("item").get(0).get("ny").asInt();
            this.observedItems = new ArrayList<>();
            jsonNode.get("response").get("body").get("items").get("item").forEach(item -> {
                try {
                    ObservedItem observedItem = ObservedItem.builder()
                            .category(item.get("category").asText())
                            .observedValue(item.get("obsrValue").asDouble())
                            .build();
                    this.observedItems.add(observedItem);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });

            return this;
        }
    }

}
