package org.routemaster.api.total.domain.kakao.message.data.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Link {

    private String webUrl;
    private String mobileWebUrl;
    private String androidExecutionParams;
    private String iosExecutionParams;
}
