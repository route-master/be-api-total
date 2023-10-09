package org.routemaster.api.total.domain.kakao.message.data.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Button {

    private String title;
    private Link link;
}
