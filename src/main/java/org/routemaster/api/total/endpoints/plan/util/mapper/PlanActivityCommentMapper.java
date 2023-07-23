package org.routemaster.api.total.endpoints.plan.util.mapper;

import org.routemaster.api.total.domain.plan.data.PlanActivityComment;
import org.routemaster.api.total.endpoints.plan.vo.PlanActivityCommentSaveRequest;
import org.springframework.stereotype.Component;

@Component
public class PlanActivityCommentMapper {

    public PlanActivityComment insert(PlanActivityCommentSaveRequest request, String username) {
        return PlanActivityComment.builder()
            .id(null)
            .planActivityId(request.getPlanActivityId())
            .writer(username)
            .description(request.getDescription())
            .build();
    }

    public PlanActivityComment update(PlanActivityComment planActivityComment, PlanActivityCommentSaveRequest request) {
        return PlanActivityComment.builder()
            .id(planActivityComment.getId())
            .planActivityId(planActivityComment.getPlanActivityId())
            .writer(planActivityComment.getWriter())
            .description(request.getDescription())
            .build();
    }
}
