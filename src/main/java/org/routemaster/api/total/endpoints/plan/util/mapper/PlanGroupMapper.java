package org.routemaster.api.total.endpoints.plan.util.mapper;

import java.util.Arrays;
import java.util.HashSet;
import org.routemaster.api.total.domain.plan.data.PlanGroup;
import org.routemaster.api.total.endpoints.plan.vo.PlanGroupSaveRequest;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

@Component
public class PlanGroupMapper {

    public PlanGroup insert(PlanGroupSaveRequest request, String username) {
        return PlanGroup.builder()
            .id(null)
            .writer(username)
            .name(request.getName())
            .description(request.getDescription())
            .thumbnailImageUrl(request.getThumbnailImageUrl())
            .participants(new HashSet<>(Arrays.asList(username)))
            .beginDate(request.getBeginDate())
            .endDate(request.getEndDate())
            .build();
    }

    public PlanGroup update(PlanGroup planGroup, PlanGroupSaveRequest request) {
        return PlanGroup.builder()
            .id(planGroup.getId())
            .writer(planGroup.getWriter())
            .name(request.getName())
            .description(request.getDescription())
            .thumbnailImageUrl(request.getThumbnailImageUrl())
            .participants(planGroup.getParticipants())
            .beginDate(request.getBeginDate())
            .endDate(request.getEndDate())
            .build();
    }
}
