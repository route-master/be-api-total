package org.routemaster.api.total.endpoints.plan.vo;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.routemaster.api.total.domain.plan.data.subdata.PlanMapInfo;
import org.routemaster.api.total.domain.plan.data.subdata.PlanPaymentLog;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class PlanActivitySaveRequest {

    private String id;
    private String planGroupId;
    @Length(min = 1)
    private String name;
    @Length(min = 1)
    private String description;
    @Length(min = 1)
    private String thumbnailImageUrl;
    private LocalDateTime beginDate;
    private LocalDateTime endDate;
    private PlanMapInfo planMapInfo;
    private List<PlanPaymentLog> planPaymentLog;
}
