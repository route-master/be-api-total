package org.routemaster.api.total.domain.plan.service;

import java.util.List;
import org.routemaster.api.total.domain.plan.data.PlanActivity;
import org.routemaster.api.total.domain.plan.data.PlanGroup;
import org.springframework.data.domain.Sort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlanGroupService {

    Mono<PlanGroup> details(String id);
    Flux<PlanGroup> list(String username, Sort sort);
    Mono<PlanGroup> save(PlanGroup entity);
    void delete(String id);
}
