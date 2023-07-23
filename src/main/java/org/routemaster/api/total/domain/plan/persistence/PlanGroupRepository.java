package org.routemaster.api.total.domain.plan.persistence;

import java.time.LocalDateTime;
import java.util.List;
import org.routemaster.api.total.domain.plan.data.PlanGroup;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
@Repository
public interface PlanGroupRepository extends ReactiveMongoRepository<PlanGroup, String> {

    Flux<PlanGroup> findAllByWriterOrParticipantsIn(String writer, List<String> participants, Sort sort);
}
