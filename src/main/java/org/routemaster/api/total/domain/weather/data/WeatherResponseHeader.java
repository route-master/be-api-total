package org.routemaster.api.total.domain.weather.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Setter
@Schema(
        description = "기상청 단기예보조회 API 응답 데이터 헤더"
)
public class WeatherResponseHeader {

    @Schema(
            description = "API 호출 결과의 상태 코드",
            example = "00"
    ) private String resultCode;
    @Schema(
            description = "API 호출 결과의 상태 메시지",
            example = "NORMAL_SERVICE"
    ) private String resultMessage;
    @Schema(
            description = "한 페이지 당 결과 수",
            example = "10"
    ) private Integer numOfRows;
    @Schema(
            description = "페이지 번호",
            example = "1"
    ) private Integer pageNo;
    @Schema(
            description = "전체 결과 수",
            example = "100"
    ) private Integer totalCount;

}
