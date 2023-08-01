package org.routemaster.api.total.infra.weatherapi.value;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class WeatherAPI {

    public static String baseUrl;
    public static String encodingKey;
    public static String decodingKey;

    @Value("${weather-api.end-point}")
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Value("${weather-api.encoding-key}")
    public void setEncodingKey(String encodingKey) {
        this.encodingKey = encodingKey;
    }

    @Value("${weather-api.decoding-key}")
    public void setDecodingKey(String decodingKey) {
        this.decodingKey = decodingKey;
    }

}
