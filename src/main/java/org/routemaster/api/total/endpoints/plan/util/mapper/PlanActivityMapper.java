package org.routemaster.api.total.endpoints.plan.util.mapper;

import java.util.ArrayList;
import org.routemaster.api.total.domain.plan.data.PlanActivity;
import org.routemaster.api.total.endpoints.plan.vo.PlanActivitySaveRequest;
import org.springframework.stereotype.Component;

@Component
public class PlanActivityMapper {

    public PlanActivity insert(PlanActivitySaveRequest request, String username) {
        return PlanActivity.builder()
            .id(null)
            .planGroupId(request.getPlanGroupId())
            .writer(username)
            .name(request.getName())
            .description(request.getDescription())
            .thumbnailImageUrl(request.getThumbnailImageUrl())
            .beginDate(request.getBeginDate())
            .mapInfo(request.getMapInfo())
            .paymentInfo(request.getPaymentInfo())
            .activityType(request.getActivityType())
            .referenceType(request.getReferenceType())
            .referenceId(request.getReferenceId())
            .build();
    }

    public PlanActivity update(PlanActivity planActivity, PlanActivitySaveRequest request) {
        return PlanActivity.builder()
            .id(planActivity.getId())
            .planGroupId(request.getPlanGroupId())
            .writer(planActivity.getWriter())
            .name(request.getName())
            .description(request.getDescription())
            .thumbnailImageUrl(request.getThumbnailImageUrl())
            .beginDate(request.getBeginDate())
            .mapInfo(request.getMapInfo())
            .paymentInfo(request.getPaymentInfo())
            .activityType(request.getActivityType())
            .referenceType(request.getReferenceType())
            .referenceId(request.getReferenceId())
            .build();
    }
}
