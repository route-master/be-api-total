package org.routemaster.api.total.endpoints.plan.vo;

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

    private String id;
    @Length(min = 1)
    private String name;
    @Length(min = 1)
    private String description;
    @Length(min = 1)
    private String thumbnailImageUrl;
    private LocalDateTime beginDate;
    private LocalDateTime endDate;

}
