package org.routemaster.api.total.domain.kakao.message.data.template;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.routemaster.api.total.domain.kakao.message.data.base.Button;
import org.routemaster.api.total.domain.kakao.message.data.base.Link;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class TextTemplate {

    private final String objectType = "text";
    private String text;
    private Link link;
    private String buttonTitle;
    private List<Button> buttons;
}
