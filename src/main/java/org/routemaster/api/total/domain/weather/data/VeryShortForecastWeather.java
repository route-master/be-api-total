package org.routemaster.api.total.domain.weather.data;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class VeryShortForecastWeather {

    private WeatherResponseHeader header;
    @Schema(
            description = "발표 일시",
            example = "2023-07-01 05:00"
    ) private LocalDateTime baseDateTime;
    private Integer nx;
    private Integer ny;
    private List<ForecastItem> forecastItems;

    public static final class VeryShortForecastWeatherBuilder {
        public VeryShortForecastWeatherBuilder buildFromJsonNode(JsonNode jsonNode) {
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
            this.forecastItems = new ArrayList<>();
            jsonNode.get("response").get("body").get("items").get("item").forEach(item -> {
                try {
                    ForecastItem forecastItem = ForecastItem.builder()
                            .category(item.get("category").asText())
                            .forecastDateTime(LocalDateTime.parse(item.get("fcstDate").asText() + item.get("fcstTime").asText(), DateTimeFormatter.ofPattern("yyyyMMddHHmm")))
                            .forecastValue(item.get("fcstValue").asDouble())
                            .build();
                    this.forecastItems.add(forecastItem);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            return this;
        }
    }

}
