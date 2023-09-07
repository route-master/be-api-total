package org.routemaster.api.total.domain.recommend.service;

import org.routemaster.api.total.domain.recommend.data.TourismClimateIndexItem;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface WeatherBasedRecommendService {
    Mono<Integer> getKntoAreaCode(String kmaRegionCode);
    Mono<Integer> getKntoSigunguCode(String kmaRegionCode);
    Mono<List<TourismClimateIndexItem>> getTourismClimateIndex(String date, String day, String cityAreaId);
}
