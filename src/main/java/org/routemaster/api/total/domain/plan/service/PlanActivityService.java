package org.routemaster.api.total.domain.plan.service;

import java.util.List;
import org.routemaster.api.total.domain.plan.data.PlanActivity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlanActivityService {

    Flux<PlanActivity> listByPlanGroupId(String planGroupId);
    Mono<PlanActivity> save(PlanActivity entity);
    void delete(String id);

    Mono<PlanActivity>details(String id);
}
