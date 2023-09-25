package org.routemaster.api.total.endpoints.calculate.vo;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "송금자")
    private String sender;
    @Schema(description = "수취자")
    private String receiver;
    @Schema(description = "금액")
    private Double amount;
}
