package org.routemaster.api.total.domain.attraction.service;

import org.routemaster.api.total.infra.tourapi.vo.AreaBasedAttractionVO;
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

    Mono<AttractionSearchVO> searchAreaBasedAttraction(
            Integer numOfRows,
            Integer pageNo,
            String MobileOS,
            String MobileApp,
            String _type,
            String listYN,
            String arrange,
            Integer contentTypeId,
            Integer areaCode,
            Integer sigunguCode,
            String cat1,
            String cat2,
            String cat3,
            String modifiedtime
    );

}
