package org.routemaster.api.total.domain.kakao.message.data.basic;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class FailureInfo {

    private Integer code;
    private String msg;
    private List<String> receiverUuids;
}
