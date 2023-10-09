package org.routemaster.api.total.domain.kakao.message.data.basic;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class BasicTemplateMessageResponse {

    @Schema(description = "메시지 전송 성공한 친구 코드 리스트")
    private List<String> successfulReceiverUuids;
    @Schema(description = "메시지 전송 실패한 친구 로그")
    private List<FailureInfo> failureInfo;
}
