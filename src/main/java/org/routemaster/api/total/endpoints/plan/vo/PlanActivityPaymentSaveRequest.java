package org.routemaster.api.total.endpoints.plan.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import org.routemaster.api.total.domain.plan.data.subdata.PlanPaymentInfo;

public record PlanActivityPaymentSaveRequest(
    String id,
    @Schema(description = "플랜 액티비티 결제 정보", requiredMode = RequiredMode.REQUIRED)
    @NotNull
    PlanPaymentInfo paymentInfo
) {

}
