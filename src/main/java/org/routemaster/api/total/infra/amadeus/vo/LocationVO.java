package org.routemaster.api.total.infra.amadeus.vo;

import com.amadeus.resources.Location;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@ToString
public class LocationVO {

    private String type;
    private String subtype;
    private String name;
    private String detailedName;
    private String timeZoneOffset;
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