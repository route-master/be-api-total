package org.routemaster.api.total.domain.weather.data;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class VeryShortLiveWeather {

    private WeatherResponseHeader header;
    private String baseDate;
    private String baseTime;
    private Integer nx;
    private Integer ny;
    private List<ObservedItem> observedItems;

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
