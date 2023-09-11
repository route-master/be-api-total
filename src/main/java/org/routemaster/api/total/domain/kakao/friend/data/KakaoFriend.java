package org.routemaster.api.total.domain.kakao.friend.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class KakaoFriend {

    private Long id;
    private String uuid;
    private Boolean favorite;
    private String profileNickname;
    private String profileThumbnailImage;
}
