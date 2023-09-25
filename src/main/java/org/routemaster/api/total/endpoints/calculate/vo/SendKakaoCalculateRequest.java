package org.routemaster.api.total.endpoints.calculate.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class SendKakaoCalculateRequest {

    @Schema(description = "카카오 액세스 토큰", requiredMode = RequiredMode.REQUIRED)
    private String accessToken;
    @Schema(description = "친구마다 고유한 값을 가지는 참고용 코드(Code)", requiredMode = RequiredMode.REQUIRED)
    private List<String> receiverUuids;
}
