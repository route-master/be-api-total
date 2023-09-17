package org.routemaster.api.total.domain.kakao.message.data.basic;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class BasicTemplateMessageRequest {

    private List<String> receiverUuids;
    private Object templateObject;
}
