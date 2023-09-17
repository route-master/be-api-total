package org.routemaster.api.total.global.config;

import org.routemaster.api.total.TotalApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackageClasses = TotalApplication.class)
public class FeignConfig {

}
