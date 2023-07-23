package org.routemaster.api.total.domain.plan.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.domain.plan.data.PlanActivityComment;
import org.routemaster.api.total.domain.plan.persistence.PlanActivityCommentRepository;
import org.routemaster.api.total.domain.plan.service.PlanActivityCommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultPlanActivityCommentService implements PlanActivityCommentService {

    private final PlanActivityCommentRepository repository;

    @Override
    public Flux<PlanActivityComment> listByPlanActivityId(String planActivityId) {
        return repository.findAllByPlanActivityId(planActivityId);
    }

    @Override
    @Transactional
    public Mono<PlanActivityComment> save(PlanActivityComment entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public Mono<PlanActivityComment> details(String id) {
        return repository.findById(id);
    }
}
