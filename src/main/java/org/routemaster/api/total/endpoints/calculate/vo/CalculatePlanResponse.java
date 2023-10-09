package org.routemaster.api.total.endpoints.calculate.vo;

import java.util.List;
import java.util.Locale;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Flux;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class CalculatePlanResponse {

    private List<CalculationUnit> calculated;
}
