package org.routemaster.api.total.endpoints.kakao.vo;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class KakaoFriendEndpointRequest {

    @NotNull
    private String accessToken;

    private Integer offset;
    private Integer limit;
    private String order;
    private String friendOrder;
}
