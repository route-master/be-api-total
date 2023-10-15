package org.routemaster.api.total.endpoints.plan.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.routemaster.api.total.domain.plan.data.PlanActivity.ActivityType;
import org.routemaster.api.total.domain.plan.data.PlanActivity.ReferenceType;
import org.routemaster.api.total.domain.plan.data.subdata.PlanMapInfo;
import org.routemaster.api.total.domain.plan.data.subdata.PlanPaymentInfo;
import org.routemaster.api.total.domain.plan.data.subdata.PlanPaymentLog;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class PlanActivitySaveRequest {

    @Schema(description = "플랜 액티비티 ID, 액티비티 생성시 null로 설정, 기존 생성된 액티비티를 수정하는 경우만 값을 넣음", requiredMode = RequiredMode.AUTO)
    private String id;
    @Schema(description = "플랜 그룹 ID", requiredMode = RequiredMode.REQUIRED)
    @NotNull
    private String planGroupId;
    @Schema(description = "플랜 액티비티 아이디", requiredMode = RequiredMode.REQUIRED)
    @NotNull
    @Length(min = 1)
    private String name;
    @Schema(description = "플랜 액티비티 설명", requiredMode = RequiredMode.AUTO)
    @Length(min = 1)
    private String description;

    @Schema(description = "플랜 액티비티 썸네일 이미지 url", requiredMode = RequiredMode.AUTO)
    @Length(min = 1)
    private String thumbnailImageUrl;
    @Schema(description = "플랜 액티비티 시작 시간", requiredMode = RequiredMode.REQUIRED)
    @NotNull
    private LocalDateTime beginDate;
    @Schema(description = "플랜 액티비티 종료 시간", requiredMode = RequiredMode.REQUIRED)
    @NotNull
    private LocalDateTime endDate;
    @Schema(description = "플랜 액티비티 맵 정보", requiredMode = RequiredMode.AUTO)
    private PlanMapInfo mapInfo;
    @Schema(description = "플랜 액티비티 결제 정보", requiredMode = RequiredMode.AUTO)
    private PlanPaymentInfo paymentInfo;
    @Schema(description = "플랜 액티비티 타입", requiredMode = RequiredMode.REQUIRED)
    @NotNull
    private ActivityType activityType;
    @Schema(description = "플랜 액티비티 참고 타입(Kakao 지도에서 받아온건지, 아니면 Tour API에서 받아온건지)", requiredMode = RequiredMode.REQUIRED)
    @NotNull
    private ReferenceType referenceType;
    @Schema(description = "플랜 액티비티 참고 ID", requiredMode = RequiredMode.REQUIRED)
    @NotNull
    private String referenceId;
}
