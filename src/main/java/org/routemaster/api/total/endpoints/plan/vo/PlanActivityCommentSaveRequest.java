package org.routemaster.api.total.endpoints.plan.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class PlanActivityCommentSaveRequest {

    @Schema(description = "플랜 액티비티 댓글 ID, 댓글 생성시 null로 설정", requiredMode = RequiredMode.AUTO)
    private String id;
    @Schema(description = "플랜 액티비티 ID", requiredMode = RequiredMode.REQUIRED)
    @NotNull
    private String planActivityId;


    @Schema(description = "댓글 내용", requiredMode = RequiredMode.REQUIRED)
    @Length(min = 1)
    @NotNull
    private String description;
}
