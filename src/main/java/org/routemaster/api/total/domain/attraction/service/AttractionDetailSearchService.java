package org.routemaster.api.total.domain.attraction.service;

import org.routemaster.api.total.domain.attraction.data.search.AttractionSearchVO;
import org.routemaster.api.total.domain.attraction.data.detail.*;
import reactor.core.publisher.Mono;

public interface AttractionDetailSearchService {

    Mono<AttractionSearchVO> searchAttractionCommonDetail(Integer contentId);
    Mono<TourAttractionDetailVO> searchTourAttractionDetail(Integer contentId);
    Mono<CultureAttractionDetailVO> searchCultureAttractionDetail(Integer contentId);
    Mono<LeportsAttractionDetailVO> searchLeportsAttractionDetail(Integer contentId);
    Mono<FestivalAttractionDetailVO> searchFestivalAttractionDetail(Integer contentId);
    Mono<RestaurantAttractionDetailVO> searchRestaurantAttractionDetail(Integer contentId);
    Mono<ShoppingAttractionDetailVO> searchShoppingAttractionDetail(Integer contentId);
    Mono<StayAttractionDetailVO> searchStayAttractionDetail(Integer contentId);
    Mono<CourseAttractionDetailVO> searchCourseAttractionDetail(Integer contentId);


}
