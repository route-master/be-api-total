package org.routemaster.api.total.domain.weather.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Schema(
        description = "예보 항목"
)
public class ForecastItem {

    @Schema(
            description = "예보 구분",
            example = "기온"
    ) private String category;
    @Schema(
            description = "예보 일시",
            example = "20230801 10:00"
    ) private LocalDateTime forecastDateTime;
    @Schema(
            description = "예보 항목에 해당하는 예보 값",
            example = "21"
    ) private Double forecastValue;
    @Schema(
            description = "예보 항목 단위",
            example = "ºC"
    ) private String unit;

    public final static class ForecastItemBuilder {
        public ForecastItemBuilder category(String category) {
            if (category.equals("T1H")) {
                this.category = "기온";
                this.unit = "ºC";
            } else if (category.equals("TMP")) {
                this.category = "1시간기온";
                this.unit = "ºC";
            } else if (category.equals("TMN")) {
                this.category = "일최저기온";
                this.unit = "ºC";
            } else if (category.equals("TMX")) {
                this.category = "일최고기온";
                this.unit = "ºC";
            } else if (category.equals("RN1")) {
                this.category = "1시간강수량";
                this.unit = "mm";
            } else if (category.equals("PCP")) {
                this.category = "1시간강수량";
                this.unit = "범주(mm)";
            } else if (category.equals("SNO")) {
                this.category = "1시간신적설";
                this.unit = "범주(mm)";
            } else if (category.equals("SKY")) {
                this.category = "하늘상태";
                this.unit = "코드값";
            } else if (category.equals("UUU")) {
                this.category = "동서바람성분";
                this.unit = "m/s";
            } else if (category.equals("VVV")) {
                this.category = "남북바람성분";
                this.unit = "m/s";
            } else if (category.equals("REH")) {
                this.category = "습도";
                this.unit = "%";
            } else if (category.equals("PTY")) {
                this.category = "강수형태";
                this.unit = "코드값";
            } else if (category.equals("POP")) {
                this.category = "강수확률";
                this.unit = "%";
            } else if (category.equals("LGT")) {
                this.category = "낙뢰";
                this.unit = "kA";
            } else if (category.equals("VEC")) {
                this.category = "풍향";
                this.unit = "deg";
            } else if (category.equals("WSD")) {
                this.category = "풍속";
                this.unit = "m/s";
            } else if (category.equals("WAV")) {
                this.category = "파고";
                this.unit = "M";
            } else {
                this.category = "unknown";
                this.unit = "unknown";
            }
            return this;
        }
    }


}
