package org.routemaster.api.total.endpoints.calculate.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.routemaster.api.total.domain.kakao.message.data.basic.BasicTemplateMessageResponse;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class SendKakaoCalculateResponse {

    private BasicTemplateMessageResponse kakaoResponse;
}
