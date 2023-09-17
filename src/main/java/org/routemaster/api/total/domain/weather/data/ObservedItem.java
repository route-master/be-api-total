package org.routemaster.api.total.domain.weather.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Schema(
        description = "관측 항목"
)
public class ObservedItem {

    @Schema(
            description = "관측 구분",
            example = "기온"
    ) private String category;
    @Schema(
            description = "관측 일자",
            example = "2023-08-01"
    ) @DateTimeFormat(pattern = "yyyy-MM-dd") private Date observedDate;
    @Schema(
            description = "관측 시각",
            example = "6"
    ) private Integer observedTime;
    @Schema(
            description = "관측 항목에 해당하는 관측 값",
            example = "21"
    ) private Double observedValue;
    @Schema(
            description = "관측 항목 단위",
            example = "ºC"
    ) private String unit;

    public static final class ObservedItemBuilder {
        public ObservedItemBuilder category(String category) {
            if (category.equals("T1H")) {
                this.category = "기온";
                this.unit = "ºC";
            } else if (category.equals("RN1")) {
                this.category = "1시간강수량";
                this.unit = "mm";
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
            } else if (category.equals("VEC")) {
                this.category = "풍향";
                this.unit = "deg";
            } else if (category.equals("WSD")) {
                this.category = "풍속";
                this.unit = "m/s";
            } else {
                this.category = "unknown";
                this.unit = "unknown";
            }
            return this;
        }
    }

}
