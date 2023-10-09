package org.routemaster.api.total.endpoints.plan.service;

import org.routemaster.api.total.domain.plan.data.PlanActivity;
import org.routemaster.api.total.domain.plan.data.PlanActivityComment;
import org.routemaster.api.total.domain.plan.data.PlanGroup;
import org.routemaster.api.total.endpoints.plan.vo.PlanActivityCommentSaveRequest;
import org.routemaster.api.total.endpoints.plan.vo.PlanActivityPaymentSaveRequest;
import org.routemaster.api.total.endpoints.plan.vo.PlanActivitySaveRequest;
import org.routemaster.api.total.endpoints.plan.vo.PlanGroupSaveRequest;
import org.springframework.data.domain.Sort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlanEndpointService {

    Mono<PlanGroup> planGroup(String id, String username);
    Flux<PlanGroup> planGroupList(String username);
    Mono<PlanGroup> savePlanGroup(PlanGroupSaveRequest request, String username);
    Mono<PlanGroup> inviteGroup(String id, String invite, String username);
    Mono<PlanGroup> exitGroup(String id, String exit, String username);
    void deletePlanGroup(String id, String username);
    Flux<PlanActivity> planActivityList(String planGroupId, String username);
    Mono<PlanActivity> savePlanActivity(PlanActivitySaveRequest request, String username);
    Mono<PlanActivity> savePlanActivityPayment(PlanActivityPaymentSaveRequest request, String username);
    void deletePlanActivity(String id, String username);
    Flux<PlanActivityComment> planCommentList(String planActivityId, String username);
    Mono<PlanActivityComment> savePlanComment(PlanActivityCommentSaveRequest request, String username);
    void deletePlanComment(String id, String username);
}
