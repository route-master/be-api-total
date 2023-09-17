package org.routemaster.api.total.domain.kakao.message.data.basic;

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

    private List<String> successfulReceiverUuids;
    private List<FailureInfo> failureInfo;
}
