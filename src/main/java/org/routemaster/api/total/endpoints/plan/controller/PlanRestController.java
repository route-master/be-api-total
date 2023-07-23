package org.routemaster.api.total.endpoints.plan.controller;

import lombok.RequiredArgsConstructor;
import org.routemaster.api.total.domain.plan.data.PlanActivity;
import org.routemaster.api.total.domain.plan.data.PlanActivityComment;
import org.routemaster.api.total.domain.plan.data.PlanGroup;
import org.routemaster.api.total.endpoints.plan.service.PlanEndpointService;
import org.routemaster.api.total.endpoints.plan.vo.PlanActivityCommentSaveRequest;
import org.routemaster.api.total.endpoints.plan.vo.PlanActivitySaveRequest;
import org.routemaster.api.total.endpoints.plan.vo.PlanGroupSaveRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<Flux<PlanGroup>> planGroupList(@RequestParam Sort sort, @RequestParam String username) {
        return new ResponseEntity<>(service.planGroupList(username, sort), HttpStatus.OK);
    }

    @PostMapping("/group")
    public ResponseEntity<Mono<PlanGroup>> savePlanGroup(@RequestBody PlanGroupSaveRequest request, @RequestParam String username) {
        return new ResponseEntity<>(service.savePlanGroup(request, username), HttpStatus.OK);
    }

    @PostMapping("/group/{id}/invite")
    public ResponseEntity<Mono<PlanGroup>> inviteGroup(@PathVariable String id, @RequestParam String invite, @RequestParam String username) {
        return new ResponseEntity<>(service.inviteGroup(id, invite, username), HttpStatus.OK);
    }

    @PostMapping("/group/{id}/exit")
    public ResponseEntity<Mono<PlanGroup>> exitGroup(@PathVariable String id,  @RequestParam String exit, @RequestParam String username) {
        return new ResponseEntity<>(service.exitGroup(id, exit, username), HttpStatus.OK);
    }

    @DeleteMapping("/group/{id}")
    public ResponseEntity<Void> deletePlanGroup(@PathVariable String id, @RequestParam String username) {
        service.deletePlanGroup(id, username);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }


    @GetMapping("/activity/list")
    public ResponseEntity<Flux<PlanActivity>> planActivityList(@RequestParam String planGroupId, @RequestParam String username) {
        return new ResponseEntity<>(service.planActivityList(planGroupId, username), HttpStatus.OK);
    }

    @PostMapping("/activity")
    public ResponseEntity<Mono<PlanActivity>> savePlanActivity(@RequestBody PlanActivitySaveRequest request, @RequestParam String username) {
        return new ResponseEntity<>(service.savePlanActivity(request, username), HttpStatus.OK);
    }

    @DeleteMapping("/activity/{id}")
    public ResponseEntity<Void> deletePlanActivity(@PathVariable String id, @RequestParam String username) {
        service.deletePlanActivity(id, username);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/activity/{planActivityId}/comment")
    public ResponseEntity<Flux<PlanActivityComment>> planCommentList(@PathVariable String planActivityId, @RequestParam String username) {
        return new ResponseEntity<>(service.planCommentList(planActivityId, username), HttpStatus.OK);
    }

    @PostMapping("/activity/comment")
    public ResponseEntity<Mono<PlanActivityComment>> savePlanComment(@RequestBody
        PlanActivityCommentSaveRequest request, @RequestParam String username) {
        return new ResponseEntity<>(service.savePlanComment(request, username), HttpStatus.OK);
    }

    @DeleteMapping("/activity/comment/{id}")
    public ResponseEntity<Void> deletePlanComment(@PathVariable String id, @RequestParam String username) {
        service.deletePlanComment(id, username);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
