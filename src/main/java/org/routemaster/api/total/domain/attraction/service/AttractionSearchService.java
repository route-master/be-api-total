package org.routemaster.api.total.domain.attraction.service;

import org.routemaster.api.total.infra.tourapi.vo.AttractionSearchVO;
import reactor.core.publisher.Mono;

public interface AttractionSearchService {

    Mono<AttractionSearchVO> searchLocationBasedAttraction(
            Integer numOfRows,
            Integer pageNo,
            String MobileOS,
            String MobileApp,
            String _type,
            String listYN,
            String arrange,
            Double mapX,
            Double mapY,
            Integer radius,
            Integer contentTypeId,
            String modifiedtime
    );

}
