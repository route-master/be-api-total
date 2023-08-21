package org.routemaster.api.total.domain.attraction.service;

import org.routemaster.api.total.domain.attraction.data.review.AttractionReview;
import org.routemaster.api.total.domain.attraction.data.review.AttractionReviewSaveRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface AttractionReviewService {

    Flux<AttractionReview> listByContentId(String contentId);
    Mono<AttractionReview> save(AttractionReviewSaveRequest request);
    Mono<Void> delete(String contentId, String userId);
