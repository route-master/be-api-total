package org.routemaster.api.total.endpoints.plan.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.domain.plan.data.PlanActivity;
import org.routemaster.api.total.domain.plan.data.PlanActivityComment;
import org.routemaster.api.total.domain.plan.data.PlanGroup;
import org.routemaster.api.total.endpoints.plan.service.PlanEndpointService;
import org.routemaster.api.total.endpoints.plan.vo.PlanActivityCommentSaveRequest;
import org.routemaster.api.total.endpoints.plan.vo.PlanActivityPaymentSaveRequest;
import org.routemaster.api.total.endpoints.plan.vo.PlanActivitySaveRequest;
import org.routemaster.api.total.endpoints.plan.vo.PlanGroupSaveRequest;
import org.routemaster.api.total.infra.auth.SecurityContextRepository;
import org.routemaster.api.total.infra.auth.data.BaseUser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/plan")
@RequiredArgsConstructor
public class PlanRestController {

    private final PlanEndpointService service;

    @Operation(summary = "여행 계획 상세 조회")
    @GetMapping("/group/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Mono<PlanGroup> planGroup(@PathVariable String id,
        @RequestAttribute(SecurityContextRepository.BASE_USER_KEY) BaseUser baseUser) {
        return service.planGroup(id, baseUser.payload().baseUserId());
    }

    @Operation(summary = "여행 계획 목록 조회")
    @GetMapping("/group")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Flux<PlanGroup> planGroupList(@RequestAttribute(SecurityContextRepository.BASE_USER_KEY) BaseUser baseUser) {
        return service.planGroupList(baseUser.payload().baseUserId());
    }

    @Operation(summary = "여행 계획 생성")
    @PostMapping("/group")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Mono<PlanGroup> savePlanGroup(@RequestBody @Validated PlanGroupSaveRequest request,
        @RequestAttribute(SecurityContextRepository.BASE_USER_KEY) BaseUser baseUser
    ) {
        return service.savePlanGroup(request, baseUser.payload().baseUserId());
    }

    @Operation(summary = "여행 계획 참가")
    @PostMapping("/group/{id}/invite")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Mono<PlanGroup> inviteGroup(
        @PathVariable
        @Parameter(description = "여행 계획 그룹 ID")
        String id,
        @RequestParam
        @Parameter(description = "초대할 사용자의 ID")
        String invite,
        @RequestAttribute(SecurityContextRepository.BASE_USER_KEY) BaseUser baseUser) {
        return service.inviteGroup(id, invite, baseUser.payload().baseUserId());
    }

    @Operation(summary = "여행 멤버 탈퇴")
    @PostMapping("/group/{id}/exit")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Mono<PlanGroup> exitGroup(
        @PathVariable
        @Parameter(description = "여행 계획 그룹 ID")
        String id,
        @Parameter(description = "탈퇴할 사용자의 ID")
        @RequestParam String exit,
        @RequestAttribute(SecurityContextRepository.BASE_USER_KEY) BaseUser baseUser) {
        return service.exitGroup(id, exit, baseUser.payload().baseUserId());
    }

    @Operation(summary = "여행 삭제")
    @DeleteMapping("/group/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Mono<Void> deletePlanGroup(@PathVariable String id,
        @RequestAttribute(SecurityContextRepository.BASE_USER_KEY) BaseUser baseUser) {
        return service.deletePlanGroup(id, baseUser.payload().baseUserId());
    }


    @Operation(summary = "여행 액티비티 목록 조회")
    @GetMapping("/activity/list")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Flux<PlanActivity> planActivityList(@RequestParam String planGroupId,
        @RequestAttribute(SecurityContextRepository.BASE_USER_KEY) BaseUser baseUser) {
        return service.planActivityList(planGroupId, baseUser.payload().baseUserId());
    }

    @Operation(summary = "여행 액티비티 생성")
    @PostMapping("/activity")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Mono<PlanActivity> savePlanActivity(
        @RequestBody @Validated PlanActivitySaveRequest request,
        @RequestAttribute(SecurityContextRepository.BASE_USER_KEY) BaseUser baseUser) {
        return service.savePlanActivity(request, baseUser.payload().baseUserId());
    }

    @Operation(summary = "여행 액티비티 결제 내역 수정")
    @PostMapping("/activity/payment")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Mono<PlanActivity> savePlanActivity(
        @RequestBody @Validated PlanActivityPaymentSaveRequest request,
        @RequestAttribute(SecurityContextRepository.BASE_USER_KEY) BaseUser baseUser) {
        return service.savePlanActivityPayment(request, baseUser.payload().baseUserId());
    }

    @Operation(summary = "여행 액티비티 삭제")
    @DeleteMapping("/activity/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Mono<Void> deletePlanActivity(@PathVariable String id,
        @RequestAttribute(SecurityContextRepository.BASE_USER_KEY) BaseUser baseUser) {
        return service.deletePlanActivity(id, baseUser.payload().baseUserId());
    }

    @Operation(summary = "여행 액티비티의 댓글 목록 조회")
    @GetMapping("/activity/{planActivityId}/comment")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Flux<PlanActivityComment> planCommentList(
        @PathVariable String planActivityId,
        @RequestAttribute(SecurityContextRepository.BASE_USER_KEY) BaseUser baseUser) {
        return service.planCommentList(planActivityId, baseUser.payload().baseUserId());
    }

    @Operation(summary = "여행 액티비티의 댓글 생성")
    @PostMapping("/activity/comment")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Mono<PlanActivityComment> savePlanComment(
        @RequestBody @Validated PlanActivityCommentSaveRequest request,
        @RequestAttribute(SecurityContextRepository.BASE_USER_KEY) BaseUser baseUser) {
        return service.savePlanComment(request, baseUser.payload().baseUserId());
    }

    @Operation(summary = "여행 액티비티의 댓글 삭제")
    @DeleteMapping("/activity/comment/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Mono<Void> deletePlanComment(
        @PathVariable String id,
        @RequestAttribute(SecurityContextRepository.BASE_USER_KEY) BaseUser baseUser) {
        return service.deletePlanComment(id, baseUser.payload().baseUserId());
    }
}
