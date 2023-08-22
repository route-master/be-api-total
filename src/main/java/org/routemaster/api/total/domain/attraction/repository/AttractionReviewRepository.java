package org.routemaster.api.total.domain.attraction.repository;

import org.routemaster.api.total.domain.attraction.data.review.AttractionReview;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AttractionReviewRepository extends ReactiveMongoRepository<AttractionReview, String> {

    Flux<AttractionReview> findAllByContentId(String contentId);
    Flux<String> findImageUrlsByContentId(String contentId);
    Mono<Void> deleteAttractionReviewByContentIdAndUserId(String contentId, String userId);

}
