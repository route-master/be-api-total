package org.routemaster.api.total.domain.plan.data.subdata;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class PlanMapInfo {

    @Field(name = "Lat")
    private Double Lat;

    @Field(name = "Lng")
    private Double Lng;
}
