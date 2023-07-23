package org.routemaster.api.total.domain.plan.persistence;


import org.routemaster.api.total.domain.plan.data.PlanActivity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface PlanActivityRepository extends ReactiveMongoRepository<PlanActivity, String> {

    Flux<PlanActivity> findAllByPlanGroupId(String planGroupId);
}
