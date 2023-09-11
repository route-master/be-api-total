package org.routemaster.api.total.endpoints.calculate.vo;

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

    private String accessToken;
    private List<String> receiverUuids;
}
