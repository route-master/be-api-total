package org.routemaster.api.total.endpoints.calculate.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.routemaster.api.total.endpoints.calculate.service.CalculateEndpointService;
import org.routemaster.api.total.endpoints.calculate.vo.CalculatePlanResponse;
import org.routemaster.api.total.endpoints.calculate.vo.SendKakaoCalculateRequest;
import org.routemaster.api.total.endpoints.calculate.vo.SendKakaoCalculateResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/calculate")
@RequiredArgsConstructor
public class CalculateController {

    private final CalculateEndpointService calculateEndpointService;

    @Operation(summary = "여행 계획 그룹별 예산 계산")
    @GetMapping
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Mono<CalculatePlanResponse> calculatePlan(@RequestParam String planGroupId) {
        return calculateEndpointService.calculatePlan(planGroupId);
    }

    @Operation(summary = "카카오 메시지 전송")
    @PostMapping("/kakao/send")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Mono<SendKakaoCalculateResponse> sendCalculated(@RequestParam String planGroupId, @RequestBody @Validated SendKakaoCalculateRequest request) {
        return calculateEndpointService.sendKakao(planGroupId, request);
    }
}
