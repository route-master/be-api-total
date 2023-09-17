package org.routemaster.api.total.domain.plan.data.subdata;

import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
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
public class PlanPaymentInfo {

    @Field(name = "participants")
    private Set<String> participants;

    @Field(name = "payment_logs")
    private List<PlanPaymentLog> paymentLogs;
}
