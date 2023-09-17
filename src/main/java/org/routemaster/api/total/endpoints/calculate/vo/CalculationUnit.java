package org.routemaster.api.total.endpoints.calculate.vo;

import java.util.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class CalculationUnit {

    private String sender;
    private String receiver;
    private Double amount;
}
