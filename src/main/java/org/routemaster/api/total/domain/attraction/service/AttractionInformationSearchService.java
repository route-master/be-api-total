package org.routemaster.api.total.domain.attraction.service;

import org.routemaster.api.total.infra.tourapi.vo.AttractionSearchVO;
import reactor.core.publisher.Mono;

public interface AttractionInformationSearchService {

    Mono<AttractionSearchVO> searchAttractionDetail(
            Integer contentId
    );

}
