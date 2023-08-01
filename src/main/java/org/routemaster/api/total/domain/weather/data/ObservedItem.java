package org.routemaster.api.total.domain.weather.data;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ObservedItem {

    private String category;
    private Double observedValue;
    private String unit;

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
