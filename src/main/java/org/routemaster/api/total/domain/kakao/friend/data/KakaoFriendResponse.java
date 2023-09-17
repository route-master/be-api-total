package org.routemaster.api.total.domain.kakao.friend.data;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class KakaoFriendResponse {

    private List<KakaoFriend> elements;
    private Integer totalCount;
    private String beforeUrl;
    private String afterUrl;
    private Integer favoriteCount;
}
