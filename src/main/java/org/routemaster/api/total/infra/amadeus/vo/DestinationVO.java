package org.routemaster.api.total.infra.amadeus.vo;


import com.amadeus.resources.Destination;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(
        name = "destination",
        description = "물리적 공간의 특정 지점이나 장소에 대한 정보"
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@ToString
public class DestinationVO {

    @Schema(
            description = "장소 유형"
    )
    private String type;

    @Schema(
            description = "장소 서브 유형 (e.g. airport, port, rail-station, restaurant, atm...)"
    )
    private String subType;

    @Schema(
            description = "장소와 관련된 이름",
            example = "Eiffel Tower"
    )
    private String name;

    @Schema(
            description = "IATA 위치 코드",
            example = "PAR"
    )
    private String iataCode;

    public static final class DestinationVOBuilder {
        public DestinationVOBuilder destination(Destination destination) {
            this.type = destination.getType();
            this.subType = destination.getSubtype();
            this.name = destination.getName();
            this.iataCode = destination.getIataCode();
            return this;
        }
    }
}
