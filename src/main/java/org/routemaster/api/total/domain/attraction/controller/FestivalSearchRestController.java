package org.routemaster.api.total.domain.attraction.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.domain.attraction.service.AttractionSearchService;
import org.routemaster.api.total.infra.tourapi.vo.AttractionSearchVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/attraction-search")
@RequiredArgsConstructor
public class FestivalSearchRestController {

    private final AttractionSearchService attractionSearchService;

    @GetMapping("event")
    public ResponseEntity<Mono<AttractionSearchVO>> event(
            @Parameter(
                    description = "한 페이지 결과 수",
                    example = "10"
            ) @RequestParam(required = false) Integer numOfRows,
            @Parameter(
                    description = "페이지 번호",
                    example = "1"
            ) @RequestParam(required = false) Integer pageNo,
            @Parameter(
                    description = "정렬 구분(A=제목순, C=수정일순, D=생성일순)",
                    example = "A",
                    schema = @Schema(
                            allowableValues = {
                                    "A", "B", "C", "D"
                            }
                    )
            ) @RequestParam(required = false) String arrange,
            @Parameter(
                    description = "행사 시작일 (형식: YYYYMMDD)",
                    example = "20230731"
            ) @RequestParam String eventStartDate,
            @Parameter(
                    description = "행사 종료일 (형식: YYYYMMDD)",
                    example = "20230831"
            ) @RequestParam(required = false) String eventEndDate,
            @Parameter(
                    description = "지역 코드(1: 서울, 2: 인천, 3: 대전, 4: 대구, 5: 광주, 6: 부산, 7: 울산, 8: 세종특별자치시, 31: 경기도, 32: 강원도, 33: 충청북도, 34: 충청남도, 35: 경상북도, 36: 경상남도, 37: 전라북도, 38: 전라남도, 39: 제주도))",
                    example = "1",
                    schema = @Schema(
                            allowableValues = {
                                    "1", "2", "3", "4", "5", "6", "7", "8", "31", "32", "33", "34", "35", "36", "37", "38", "39"
                            }
                    )
            ) @RequestParam(required = false) Integer areaCode,
            @Parameter(
                    description = "시군구코드",
                    example = "1"
            ) @RequestParam(required = false) Integer sigunguCode,
            @Parameter(
                    description = "콘텐츠 수정일(형식: YYYYMMDD)",
                    example = "20221020"
            ) @RequestParam(required = false) String modifiedTime
    ) {
        Mono<AttractionSearchVO> result = attractionSearchService.searchFestival(
                numOfRows,
                pageNo,
                arrange,
                eventStartDate,
                eventEndDate,
                areaCode,
                sigunguCode,
                modifiedTime
        );
        return ResponseEntity.ok(result);
    }

}
