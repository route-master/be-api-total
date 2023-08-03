package org.routemaster.api.total.domain.weather.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.routemaster.api.total.domain.weather.data.ShortForecastWeather;
import org.routemaster.api.total.domain.weather.data.VeryShortForecastWeather;
import org.routemaster.api.total.domain.weather.data.VeryShortLiveWeather;
import org.routemaster.api.total.domain.weather.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
public class WeatherRestController {

    private final WeatherService weatherService;

    @Operation(
            summary = "초단기 실황 조회",
            description = "실황 정보를 조회하기 위해 발표일자, 발표시각, 예보지점 x좌표, 예보지점 y좌표의 조회 조건으로 " +
                    "자료구분코드, 실황값, 발표일자, 발표시각, 예보지점 x좌표, 예보지점 y좌표의 정보를 조회하는 기능",
            tags = {
                    "weather"
            }
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "Success Response",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = VeryShortLiveWeather.class)
                            )
                    }
            )
    )
    @GetMapping("/very-short-live")
    public ResponseEntity<Mono<VeryShortLiveWeather>> getVeryShortLiveWeather(
            @Parameter(
                    required = true,
                    description = "발표 일자(최근 1일 이내, yyyyMMdd)",
                    example = "20230801"
            ) @RequestParam String baseDate,
            @Parameter(
                    required = true,
                    description = "발표 시간(정시 단위, 0~23)",
                    example = "6"
            ) @RequestParam Integer baseTime,
            @Parameter(
                    required = true,
                    description = "위도",
                    example = "37.63"
            ) @RequestParam Double latitude,
            @Parameter(
                    required = true,
                    description = "경도",
                    example = "127.07"
            ) @RequestParam Double longitude
    ) {
        Mono<VeryShortLiveWeather> result = weatherService.getVeryShortLiveWeather(
                baseDate, baseTime, latitude, longitude);
        return ResponseEntity.ok(result);
    }

    @Operation(
            summary = "초단기 예보 조회",
            description = "초단기 예보 정보를 조회하기 위해 발표일자, 발표시각, 예보지점 x좌표, 예보지점 y좌표의 조회 조건으로 " +
                    "자료구분코드, 실황값, 발표일자, 발표시각, 예보지점 x좌표, 예보지점 y좌표의 정보를 조회하는 기능",
            tags = {
                    "weather"
            }
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "Success Response",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = VeryShortForecastWeather.class)
                            )
                    }
            )
    )
    @GetMapping("/very-short-forecast")
    public ResponseEntity<Mono<VeryShortForecastWeather>> getVeryShortForecastWeather(
            @Parameter(
                    required = true,
                    description = "발표 일자(최근 1일 이내, yyyyMMdd)",
                    example = "20230801"
            ) @RequestParam String baseDate,
            @Parameter(
                    required = true,
                    description = "발표 시간(정시 단위, 0~23)",
                    example = "6"
            ) @RequestParam Integer baseTime,
            @Parameter(
                    required = true,
                    description = "위도",
                    example = "37.63"
            ) @RequestParam Double latitude,
            @Parameter(
                    required = true,
                    description = "경도",
                    example = "127.07"
            ) @RequestParam Double longitude
    ) {
        Mono<VeryShortForecastWeather> result = weatherService.getVeryShortForecastWeather(
                baseDate, baseTime, latitude, longitude);
        return ResponseEntity.ok(result);
    }

    @Operation(
            summary = "단기 예보 조회",
            description = "단기 예보 정보를 조회하기 위해 발표일자, 발표시각, 예보지점 x좌표, 예보지점 y좌표의 조회 조건으로 " +
                    "자료구분코드, 실황값, 발표일자, 발표시각, 예보지점 x좌표, 예보지점 y좌표의 정보를 조회하는 기능",
            tags = {
                    "weather"
            }
    )
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    description = "Success Response",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ShortForecastWeather.class)
                            )
                    }
            )
    )
    @GetMapping("/short-forecast")
    public ResponseEntity<Mono<ShortForecastWeather>> getShortForecastWeather(
            @Parameter(
                    required = true,
                    description = "발표 일자(최근 1일 이내, yyyyMMdd)",
                    example = "20230801"
            ) @RequestParam String baseDate,
            @Parameter(
                    required = true,
                    description = "발표 시간",
                    example = "5",
                    schema = @Schema(
                            allowableValues = {
                                    "2", "5", "8", "11", "14", "17", "20", "23"
                            }
                    )
            ) @RequestParam Integer baseTime,
            @Parameter(
                    required = true,
                    description = "위도",
                    example = "37.63"
            ) @RequestParam Double latitude,
            @Parameter(
                    required = true,
                    description = "경도",
                    example = "127.07"
            ) @RequestParam Double longitude
    ) {
        Mono<ShortForecastWeather> result = weatherService.getShortForecastWeather(
                baseDate, baseTime, latitude, longitude);
        return ResponseEntity.ok(result);
    }

}
