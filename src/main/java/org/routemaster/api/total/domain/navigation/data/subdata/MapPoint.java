package org.routemaster.api.total.domain.navigation.data.subdata;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class MapPoint {

    private Double x;
    private Double y;

}
