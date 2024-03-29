package org.routemaster.api.total.domain.attraction.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.domain.attraction.data.review.AttractionReview;
import org.routemaster.api.total.domain.attraction.data.review.AttractionReviewSaveRequest;
import org.routemaster.api.total.domain.attraction.repository.AttractionReviewRepository;
import org.routemaster.api.total.domain.attraction.service.AttractionReviewService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultAttractionReviewService implements AttractionReviewService {

    private final AttractionReviewRepository repository;

    @Override
    public Flux<AttractionReview> listByContentId(String contentId) {
        return repository.findAllByContentId(contentId);
    }

    @Override
    public Mono<AttractionReview> save(AttractionReviewSaveRequest request) {
        return repository.save(AttractionReview.builder()
                .userId(request.getUserId())
                .contentId(request.getContentId())
                .reviewComment(request.getReviewComment())
                .imageUrl(request.getImageUrl())
                .rating(request.getRating())
                .build());
    }

    @Override
    public Mono<AttractionReview> update(AttractionReviewSaveRequest request) {
        return repository.findById(request.getId())
                .flatMap(review -> {
                    review.setReviewComment(request.getReviewComment());
                    review.setImageUrl(request.getImageUrl());
                    review.setRating(request.getRating());
                    return repository.save(review);
                });
    }

    @Override
    public Mono<Void> delete(String contentId, String userId) {
        return repository.deleteAttractionReviewByContentIdAndUserId(contentId, userId);
    }

    @Override
    public Mono<List<String>> listReviewImagesByContentId(String contentId) {
        return repository.findAllByContentId(contentId)
                .flatMap(review -> {
                    Optional<String> imageUrl = Optional.ofNullable(review.getImageUrl());
                    return Mono.justOrEmpty(imageUrl);
                })
                .collectList();
    }

}
