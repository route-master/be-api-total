package org.routemaster.api.total.domain.weather.service;

import org.routemaster.api.total.domain.weather.data.ShortForecastWeather;
import org.routemaster.api.total.domain.weather.data.VeryShortForecastWeather;
import org.routemaster.api.total.domain.weather.data.VeryShortLiveWeather;
import reactor.core.publisher.Mono;

public interface WeatherService {

    Mono<VeryShortLiveWeather> getVeryShortLiveWeather(String baseDate, String baseTime, Double latitude, Double longitude);
    Mono<VeryShortForecastWeather> getVeryShortForecastWeather(String baseDate, String baseTime, Double latitude, Double longitude);
    Mono<ShortForecastWeather> getShortForecastWeather(String baseDate, String baseTime, Double latitude, Double longitude);

}
