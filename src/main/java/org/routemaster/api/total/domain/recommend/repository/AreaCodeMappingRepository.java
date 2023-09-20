package org.routemaster.api.total.domain.recommend.repository;

import org.routemaster.api.total.domain.recommend.data.AreaCodeMapping;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AreaCodeMappingRepository extends ReactiveMongoRepository<AreaCodeMapping, String> {

    Flux<AreaCodeMapping> findAllByKmaRegionCode(String kmaRegionCode);

    Mono<Integer> findKntoAreaCodeByKmaRegionCode(String kmaRegionCode);

    Mono<Integer> findKntoSigunguCodeByKmaRegionCode(String kmaRegionCode);

}
