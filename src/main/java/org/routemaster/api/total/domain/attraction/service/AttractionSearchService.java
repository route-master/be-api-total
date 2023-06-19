package org.routemaster.api.total.domain.attraction.service;

import org.routemaster.api.total.infra.tourapi.vo.AttractionSearchVO;
import reactor.core.publisher.Mono;

public interface AttractionSearchService {

    Mono<AttractionSearchVO> searchLocationBasedAttraction(
            Integer numOfRows,
            Integer pageNo,
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
            String arrange,
            Integer contentTypeId,
            Integer areaCode,
            Integer sigunguCode,
            String cat1,
            String cat2,
            String cat3,
            String modifiedTime
    );

    Mono<AttractionSearchVO> searchKeywordBasedAttraction(
            Integer numOfRows,
            Integer pageNo,
            String arrange,
            String keyword,
            Integer contentTypeId,
            Integer areaCode,
            Integer sigunguCode,
            String cat1,
            String cat2,
            String cat3
    );

    Mono<AttractionSearchVO> searchFestival(
            Integer numOfRows,
            Integer pageNo,
            String arrange,
            String eventStartDate,
            String eventEndDate,
            Integer areaCode,
            Integer sigunguCode,
            String modifiedTime
    );

}
