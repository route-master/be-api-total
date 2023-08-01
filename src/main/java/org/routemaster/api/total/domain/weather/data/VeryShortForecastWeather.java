package org.routemaster.api.total.domain.weather.data;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class VeryShortForecastWeather {

    private WeatherResponseHeader header;
    private String baseDate;
    private String baseTime;
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
            this.baseDate = jsonNode.get("response").get("body").get("items").get("item").get(0).get("baseDate").asText();
            this.baseTime = jsonNode.get("response").get("body").get("items").get("item").get(0).get("baseTime").asText();
            this.nx = jsonNode.get("response").get("body").get("items").get("item").get(0).get("nx").asInt();
            this.ny = jsonNode.get("response").get("body").get("items").get("item").get(0).get("ny").asInt();
            this.forecastItems = new ArrayList<>();
            jsonNode.get("response").get("body").get("items").get("item").forEach(item -> {
                try {
                    ForecastItem observedItem = ForecastItem.builder()
                            .category(item.get("category").asText())
                            .forecastDate(item.get("fcstDate").asDouble())
                            .forecastTime(item.get("fcstTime").asDouble())
                            .forecastValue(item.get("fcstValue").asDouble())
                            .build();
                    this.forecastItems.add(observedItem);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            return this;
        }
    }

}
