package org.routemaster.api.total.endpoints.plan.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class PlanGroupSaveRequest {

    @Schema(description = "플랜 그룹 ID, 그룹 생성시 null로 설정, 기존 생성된 그룹을 수정하는 경우만 값을 넣음")
    private String id;
    @Schema(description = "플랜 그룹 이름")
    @Length(min = 1)
    @NotNull
    private String name;
    @Schema(description = "플랜 그룹 설명")
    @Length(min = 1)
    @NotNull
    private String description;
    @Length(min = 1)
    private String thumbnailImageUrl;
    @NotNull
    private LocalDateTime beginDate;
    @NotNull
    private LocalDateTime endDate;

}
