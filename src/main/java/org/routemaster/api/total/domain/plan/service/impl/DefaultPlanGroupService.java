package org.routemaster.api.total.domain.plan.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.domain.plan.data.PlanGroup;
import org.routemaster.api.total.domain.plan.persistence.PlanGroupRepository;
import org.routemaster.api.total.domain.plan.service.PlanGroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class DefaultPlanGroupService implements PlanGroupService {

    private final PlanGroupRepository repository;

    @Override
    public Mono<PlanGroup> details(String id) {
        return repository.findById(id);
    }

    @Override
    public Flux<PlanGroup> list(String username) {
        return repository.findAllByWriterOrParticipantsInOrderByBeginDate(username, List.of(username));
    }

    @Override
    @Transactional
    public Mono<PlanGroup> save(PlanGroup entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional
    public void delete(String id) {
        repository.deleteById(id);
    }
}
