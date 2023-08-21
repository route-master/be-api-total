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
}
