package org.routemaster.api.total.domain.user.jwt;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.domain.plan.data.PlanActivityComment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/temp")
public class TempController {

    @GetMapping
    public ResponseEntity<String> temp() {
        return new ResponseEntity<>("temp", HttpStatus.OK);
    }

    @GetMapping("/auth")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Mono<String> auth() {
        return Mono.just("temp-auth");
    }
}
