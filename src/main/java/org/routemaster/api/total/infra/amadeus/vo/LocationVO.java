package org.routemaster.api.total.infra.amadeus.vo;

import com.amadeus.resources.Location;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(
        name = "location",
        description = "공항이나 도시에 대한 정보"
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@ToString
public class LocationVO {

    @Schema(
            description = "장소 유형",
            example = "location"
    )
    private String type;

    @Schema(
            description = "장소 서브 유형",
            example = "AIRPORT"
    )
    private String subtype;

    @Schema(
            description = "장소의 간략한 이름",
            example = "Paris CDG"
    )
    private String name;

    @Schema(
            description = "장소의 세부적인 이름",
            example = "Paris/FR: Charles de Gaulle"
    )
    private String detailedName;

    @Schema(
            description = "API 호출 날짜의 location의 timezone offset",
            example = "+01:00"
    )
    private String timeZoneOffset;

    @Schema(
            description = "IATA 위치 코드",
            example = "CDG"
    )
    private String iataCode;

    private Location.GeoCode geoCode;
    private Location.Address address;
    private Location.Analytics analytics;

    public static final class LocationVOBuilder {

        public LocationVOBuilder location(Location location) {
            this.type = location.getType();
            this.subtype = location.getSubType();
            this.name = location.getName();
            this.detailedName = location.getDetailedName();
            this.timeZoneOffset = location.getTimeZoneOffset();
            this.iataCode = location.getIataCode();
            this.geoCode = location.getGeoCode();
            this.address = location.getAddress();
            this.analytics = location.getAnalytics();
            return this;
        }
    }

}