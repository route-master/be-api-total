package org.routemaster.api.total.domain.plan.data.subdata;

import java.util.Currency;
import java.util.Locale;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class PlanPaymentLog {

    @Field(name = "paid")
    private String paid;

    @Field(name = "payment")
    private Double payment;
}
