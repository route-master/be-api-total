package org.routemaster.api.total.endpoints.plan.service.impl;

import java.util.Arrays;
import java.util.HashSet;
import lombok.RequiredArgsConstructor;
import org.routemaster.api.total.domain.plan.data.PlanActivity;
import org.routemaster.api.total.domain.plan.data.PlanActivityComment;
import org.routemaster.api.total.domain.plan.data.PlanGroup;
import org.routemaster.api.total.domain.plan.service.PlanActivityCommentService;
import org.routemaster.api.total.domain.plan.service.PlanActivityService;
import org.routemaster.api.total.domain.plan.service.PlanGroupService;
import org.routemaster.api.total.endpoints.plan.service.PlanEndpointService;
import org.routemaster.api.total.endpoints.plan.util.mapper.PlanActivityCommentMapper;
import org.routemaster.api.total.endpoints.plan.util.mapper.PlanActivityMapper;
import org.routemaster.api.total.endpoints.plan.util.mapper.PlanGroupMapper;
import org.routemaster.api.total.endpoints.plan.vo.PlanActivityCommentSaveRequest;
import org.routemaster.api.total.endpoints.plan.vo.PlanActivityPaymentSaveRequest;
import org.routemaster.api.total.endpoints.plan.vo.PlanActivitySaveRequest;
import org.routemaster.api.total.endpoints.plan.vo.PlanGroupSaveRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DefaultPlanEndpointService implements PlanEndpointService {

    private final PlanActivityCommentService commentService;
    private final PlanActivityService activityService;
    private final PlanGroupService groupService;

    private final PlanGroupMapper groupMapper;
    private final PlanActivityMapper activityMapper;
    private final PlanActivityCommentMapper commentMapper;

    @Override
    public Mono<PlanGroup> planGroup(String id, String username) {
        Mono<PlanGroup> details = groupService.details(id);
        return details.filter(group -> group.getParticipants().contains(username));
    }

    @Override
    public Flux<PlanGroup> planGroupList(String username) {
        return groupService.list(username);
    }

    @Override
    @Transactional
    public Mono<PlanGroup> savePlanGroup(PlanGroupSaveRequest request, String username) {
        if (request.getId() != null) {
            PlanGroup planGroup = groupService.details(request.getId()).block();
            return groupService.save(groupMapper.update(planGroup, request));
        }
        return groupService.save(groupMapper.insert(request, username));
    }

    @Override
    @Transactional
    public Mono<PlanGroup> inviteGroup(String id, String invite, String username) {
        Mono<PlanGroup> planGroupMono = groupService.details(id);
        return planGroupMono.map(planGroup -> {
            planGroup.invite(invite);
            return planGroup;
        }).flatMap(groupService::save);
    }

    @Override
    @Transactional
    public Mono<PlanGroup> exitGroup(String id, String exit, String username) {
        Mono<PlanGroup> planGroupMono = groupService.details(id);
        return planGroupMono.map(planGroup -> {
            planGroup.exit(exit);
            return planGroup;
        }).flatMap(groupService::save);
    }

    @Override
    @Transactional
    public Mono<Void> deletePlanGroup(String id, String username) {
        return groupService.delete(id);
    }

    @Override
    public Flux<PlanActivity> planActivityList(String planGroupId, String username) {
        return activityService.listByPlanGroupId(planGroupId);
    }

    @Override
    @Transactional
    public Mono<PlanActivity> savePlanActivity(PlanActivitySaveRequest request, String username) {
        if (request.getId() != null) {
            PlanActivity prev = activityService.details(request.getId()).block();
            return activityService.save(activityMapper.update(prev, request));
        }
        return activityService.save(activityMapper.insert(request, username));
    }

    @Override
    public Mono<PlanActivity> savePlanActivityPayment(
        PlanActivityPaymentSaveRequest request, String username) {
        return activityService.save(request.id(), request.paymentInfo());
    }

    @Override
    @Transactional
    public Mono<Void> deletePlanActivity(String id, String username) {
        return activityService.delete(id);
    }

    @Override
    public Flux<PlanActivityComment> planCommentList(String planActivityId, String username) {
        return commentService.listByPlanActivityId(planActivityId);
    }

    @Override
    @Transactional
    public Mono<PlanActivityComment> savePlanComment(PlanActivityCommentSaveRequest request,
        String username) {
        if (request.getId() != null) {
            PlanActivityComment prev =  commentService.details(request.getId()).block();
            return commentService.save(commentMapper.update(prev, request));
        }
        return commentService.save(commentMapper.insert(request, username));
    }

    @Override
    @Transactional
    public Mono<Void> deletePlanComment(String id, String username) {
        return commentService.delete(id);
    }
}
