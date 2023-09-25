package org.routemaster.api.total.endpoints.kakao.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class KakaoFriendEndpointRequest {

    @Schema(description = "카카오 액세스 토큰", requiredMode = RequiredMode.REQUIRED)
    @NotNull
    private String accessToken;

    @Schema(description = "친구 목록 시작 지점(기본값: 0)", requiredMode = RequiredMode.AUTO)
    private Integer offset;
    @Schema(description = "한 페이지에 가져올 친구 최대 수(기본값: 10, 최대: 100)", requiredMode = RequiredMode.AUTO)
    private Integer limit;
    @Schema(description = "친구 목록 정렬 순서\n"
        + "오름차순(asc) 또는 내림차순(desc)(기본값 asc)", requiredMode = RequiredMode.AUTO)
    private String order;
    @Schema(description = "친구 목록 정렬 시 기준 설정\n"
        + "favorite: 즐겨찾기 친구 우선 정렬\n"
        + "nickname: 닉네임 순서 정렬로 기준 설정\n"
        + "(기본값 favorite)", requiredMode = RequiredMode.AUTO)
    private String friendOrder;
}
