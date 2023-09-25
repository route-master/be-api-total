package org.routemaster.api.total.domain.kakao.friend.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class KakaoFriend {

    @Schema(description = "회원번호")
    private Long id;
    @Schema(description = "친구마다 고유한 값을 가지는 참고용 코드(Code)\n"
        + "카카오톡 메시지 전송 시 사용")
    private String uuid;
    @Schema(description = "해당 친구 즐겨찾기 여부")
    private Boolean favorite;
    @Schema(description = "프로필 닉네임")
    private String profileNickname;
    @Schema(description = "프로필 썸네일(Thumbnail) 이미지\n"
        + "HTTPS만 지원")
    private String profileThumbnailImage;
}
