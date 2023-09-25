package org.routemaster.api.total.domain.kakao.friend.data;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class KakaoFriendResponse {

    @Schema(description = "친구 목록")
    private List<KakaoFriend> elements;
    @Schema(description = "친구 목록 총 개수")
    private Integer totalCount;
    @Schema(description = "친구 목록 이전 페이지")
    private String beforeUrl;
    @Schema(description = "친구 목록 다음 페이지")
    private String afterUrl;
    @Schema(description = "친구 목록 즐겨찾기 친구 수")
    private Integer favoriteCount;
}
