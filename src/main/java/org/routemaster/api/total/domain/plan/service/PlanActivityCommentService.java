package org.routemaster.api.total.domain.plan.service;

import java.util.List;
import org.routemaster.api.total.domain.plan.data.PlanActivityComment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlanActivityCommentService {

    Flux<PlanActivityComment> listByPlanActivityId(String planActivityId);
    Mono<PlanActivityComment> save(PlanActivityComment entity);
    Mono<Void> delete(String id);

    Mono<PlanActivityComment> details(String id);
}
