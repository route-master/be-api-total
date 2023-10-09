package org.routemaster.api.total.domain.attraction.service;

import org.routemaster.api.total.domain.attraction.data.utils.AreaCodeSearchResponse;
import org.routemaster.api.total.domain.attraction.data.utils.CategorySearchResponse;
import reactor.core.publisher.Mono;

public interface AttractionUtilService {

    Mono<CategorySearchResponse> searchCategory(
            Integer numOfRows,
            Integer pageNo,
            Integer contentTypeId,
            String largeCategory,
            String mediumCategory,
            String smallCategory
    );

    Mono<AreaCodeSearchResponse> searchAreaCode(
            Integer numOfRows,
            Integer pageNo,
            Integer areaCode
    );

}
