package org.routemaster.api.total.domain.kakao.message.data.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Link {

    private String webUrl;
    private String mobileWebUrl;
    private String androidExecutionParams;
    private String iosExecutionParams;
}
