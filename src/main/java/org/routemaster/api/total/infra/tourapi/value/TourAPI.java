package org.routemaster.api.total.infra.tourapi.value;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class TourAPI{

    public static String baseUrl;
    public static String encodingKey;
    public static String decodingKey;


    @Value("${tour-api.end-point}")
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Value("${tour-api.encoding-key}")
    public void setEncodingKey(String encodingKey) {
        this.encodingKey = encodingKey;
    }

    @Value("${tour-api.decoding-key}")
    public void setDecodingKey(String decodingKey) {
        this.decodingKey = decodingKey;
    }

}