package org.routemaster.api.total.endpoints.kakao.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.routemaster.api.total.domain.kakao.friend.data.KakaoFriendResponse;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class KakaoFriendEndpointResponse {

    private KakaoFriendResponse data;
}
