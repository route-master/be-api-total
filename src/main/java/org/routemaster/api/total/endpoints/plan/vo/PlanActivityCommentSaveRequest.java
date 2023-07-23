package org.routemaster.api.total.endpoints.plan.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class PlanActivityCommentSaveRequest {

    private String id;
    private String planActivityId;

    @Length(min = 1)
    private String description;
}
