package org.routemaster.api.total.endpoints.plan.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.domain.plan.data.PlanActivity;
import org.routemaster.api.total.domain.plan.data.PlanActivityComment;
import org.routemaster.api.total.domain.plan.data.PlanGroup;
import org.routemaster.api.total.endpoints.plan.service.PlanEndpointService;
import org.routemaster.api.total.endpoints.plan.vo.PlanActivityCommentSaveRequest;
import org.routemaster.api.total.endpoints.plan.vo.PlanActivitySaveRequest;
import org.routemaster.api.total.endpoints.plan.vo.PlanGroupSaveRequest;
import org.routemaster.api.total.infra.auth.SecurityContextRepository;
import org.routemaster.api.total.infra.auth.data.BaseUser;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("/group")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Flux<PlanGroup> planGroupList(@RequestAttribute(SecurityContextRepository.BASE_USER_KEY) BaseUser baseUser) {
        log.info("baseUser: {}", baseUser);
        return service.planGroupList(baseUser.payload().baseUserId());
    }

    @PostMapping("/group")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Mono<PlanGroup> savePlanGroup(@RequestBody PlanGroupSaveRequest request,
        @RequestAttribute(SecurityContextRepository.BASE_USER_KEY) BaseUser baseUser
    ) {
        return service.savePlanGroup(request, baseUser.payload().baseUserId());
    }

    @PostMapping("/group/{id}/invite")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Mono<PlanGroup> inviteGroup(@PathVariable String id,
        @RequestParam String invite,
        @RequestAttribute(SecurityContextRepository.BASE_USER_KEY) BaseUser baseUser) {
        return service.inviteGroup(id, invite, baseUser.payload().baseUserId());
    }

    @PostMapping("/group/{id}/exit")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Mono<PlanGroup> exitGroup(@PathVariable String id,
        @RequestParam String exit,
        @RequestAttribute(SecurityContextRepository.BASE_USER_KEY) BaseUser baseUser) {
        return service.exitGroup(id, exit, baseUser.payload().baseUserId());
    }

    @DeleteMapping("/group/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Mono<Void> deletePlanGroup(@PathVariable String id,
        @RequestAttribute(SecurityContextRepository.BASE_USER_KEY) BaseUser baseUser) {
        service.deletePlanGroup(id, baseUser.payload().baseUserId());
        return Mono.empty();
    }


    @GetMapping("/activity/list")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Flux<PlanActivity> planActivityList(@RequestParam String planGroupId,
        @RequestAttribute(SecurityContextRepository.BASE_USER_KEY) BaseUser baseUser) {
        return service.planActivityList(planGroupId, baseUser.payload().baseUserId());
    }

    @PostMapping("/activity")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Mono<PlanActivity> savePlanActivity(
        @RequestBody PlanActivitySaveRequest request,
        @RequestAttribute(SecurityContextRepository.BASE_USER_KEY) BaseUser baseUser) {
        return service.savePlanActivity(request, baseUser.payload().baseUserId());
    }

    @DeleteMapping("/activity/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Mono<Void> deletePlanActivity(@PathVariable String id,
        @RequestAttribute(SecurityContextRepository.BASE_USER_KEY) BaseUser baseUser) {
        service.deletePlanActivity(id, baseUser.payload().baseUserId());
        return Mono.empty();
    }

    @GetMapping("/activity/{planActivityId}/comment")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Flux<PlanActivityComment> planCommentList(
        @PathVariable String planActivityId,
        @RequestAttribute(SecurityContextRepository.BASE_USER_KEY) BaseUser baseUser) {
        return service.planCommentList(planActivityId, baseUser.payload().baseUserId());
    }

    @PostMapping("/activity/comment")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Mono<PlanActivityComment> savePlanComment(
        @RequestBody PlanActivityCommentSaveRequest request,
        @RequestAttribute(SecurityContextRepository.BASE_USER_KEY) BaseUser baseUser) {
        return service.savePlanComment(request, baseUser.payload().baseUserId());
    }

    @DeleteMapping("/activity/comment/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Mono<Void> deletePlanComment(
        @PathVariable String id,
        @RequestAttribute(SecurityContextRepository.BASE_USER_KEY) BaseUser baseUser) {
        service.deletePlanComment(id, baseUser.payload().baseUserId());
        return Mono.empty();
    }
}
