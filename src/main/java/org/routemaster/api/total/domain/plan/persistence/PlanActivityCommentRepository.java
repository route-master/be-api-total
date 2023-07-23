package org.routemaster.api.total.domain.plan.persistence;

import org.routemaster.api.total.domain.plan.data.PlanActivityComment;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface PlanActivityCommentRepository extends ReactiveMongoRepository<PlanActivityComment, String> {
    Flux<PlanActivityComment> findAllByPlanActivityId(String planActivityId);
}
