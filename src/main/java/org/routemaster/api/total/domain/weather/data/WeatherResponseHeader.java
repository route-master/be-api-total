package org.routemaster.api.total.domain.weather.data;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Setter
public class WeatherResponseHeader {

    private String resultCode;
    private String resultMessage;
    private Integer numOfRows;
    private Integer pageNo;
    private Integer totalCount;

}
