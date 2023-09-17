package org.routemaster.api.total.endpoints.plan.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
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

@RestController
@RequestMapping("/plan")
@RequiredArgsConstructor
public class PlanRestController {

    private final PlanEndpointService service;

    @GetMapping("/group")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Flux<PlanGroup>> planGroupList(@RequestParam Sort sort,
        @RequestAttribute(SecurityContextRepository.BASE_USER_KEY) BaseUser baseUser) {
        return new ResponseEntity<>(
            service.planGroupList(baseUser.payload().baseUserId(), sort), HttpStatus.OK);
    }

    @PostMapping("/group")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Mono<PlanGroup>> savePlanGroup(@RequestBody PlanGroupSaveRequest request,
        @RequestAttribute(SecurityContextRepository.BASE_USER_KEY) BaseUser baseUser) {
        return new ResponseEntity<>(
            service.savePlanGroup(request, baseUser.payload().baseUserId()), HttpStatus.OK);
    }

    @PostMapping("/group/{id}/invite")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Mono<PlanGroup>> inviteGroup(@PathVariable String id,
        @RequestParam String invite,
        @RequestAttribute(SecurityContextRepository.BASE_USER_KEY) BaseUser baseUser) {
        return new ResponseEntity<>(
            service.inviteGroup(id, invite, baseUser.payload().baseUserId()), HttpStatus.OK);
    }

    @PostMapping("/group/{id}/exit")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Mono<PlanGroup>> exitGroup(@PathVariable String id,
        @RequestParam String exit,
        @RequestAttribute(SecurityContextRepository.BASE_USER_KEY) BaseUser baseUser) {
        return new ResponseEntity<>(
            service.exitGroup(id, exit, baseUser.payload().baseUserId()), HttpStatus.OK);
    }

    @DeleteMapping("/group/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Void> deletePlanGroup(@PathVariable String id,
        @RequestAttribute(SecurityContextRepository.BASE_USER_KEY) BaseUser baseUser) {
        service.deletePlanGroup(id, baseUser.payload().baseUserId());
        return new ResponseEntity<>(null, HttpStatus.OK);
    }


    @GetMapping("/activity/list")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Flux<PlanActivity>> planActivityList(@RequestParam String planGroupId,
        @RequestAttribute(SecurityContextRepository.BASE_USER_KEY) BaseUser baseUser) {
        return new ResponseEntity<>(
            service.planActivityList(planGroupId, baseUser.payload().baseUserId()), HttpStatus.OK);
    }

    @PostMapping("/activity")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Mono<PlanActivity>> savePlanActivity(
        @RequestBody PlanActivitySaveRequest request,
        @RequestAttribute(SecurityContextRepository.BASE_USER_KEY) BaseUser baseUser) {
        return new ResponseEntity<>(
            service.savePlanActivity(request, baseUser.payload().baseUserId()), HttpStatus.OK);
    }

    @DeleteMapping("/activity/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Void> deletePlanActivity(@PathVariable String id,
        @RequestAttribute(SecurityContextRepository.BASE_USER_KEY) BaseUser baseUser) {
        service.deletePlanActivity(id, baseUser.payload().baseUserId());
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/activity/{planActivityId}/comment")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Flux<PlanActivityComment>> planCommentList(
        @PathVariable String planActivityId,
        @RequestAttribute(SecurityContextRepository.BASE_USER_KEY) BaseUser baseUser) {
        return new ResponseEntity<>(
            service.planCommentList(planActivityId, baseUser.payload().baseUserId()), HttpStatus.OK);
    }

    @PostMapping("/activity/comment")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Mono<PlanActivityComment>> savePlanComment(
        @RequestBody PlanActivityCommentSaveRequest request,
        @RequestAttribute(SecurityContextRepository.BASE_USER_KEY) BaseUser baseUser) {
        return new ResponseEntity<>(
            service.savePlanComment(request, baseUser.payload().baseUserId()), HttpStatus.OK);
    }

    @DeleteMapping("/activity/comment/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Void> deletePlanComment(
        @PathVariable String id,
        @RequestAttribute(SecurityContextRepository.BASE_USER_KEY) BaseUser baseUser) {
        service.deletePlanComment(id, baseUser.payload().baseUserId());
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
