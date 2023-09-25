package org.routemaster.api.total.endpoints.calculate.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.domain.kakao.message.KakaoMessageService;
import org.routemaster.api.total.domain.kakao.message.data.base.Link;
import org.routemaster.api.total.domain.kakao.message.data.basic.BasicTemplateMessageResponse;
import org.routemaster.api.total.domain.kakao.message.data.template.TextTemplate;
import org.routemaster.api.total.domain.plan.data.PlanActivity;
import org.routemaster.api.total.domain.plan.data.subdata.PlanPaymentInfo;
import org.routemaster.api.total.domain.plan.data.subdata.PlanPaymentLog;
import org.routemaster.api.total.domain.plan.service.PlanActivityService;
import org.routemaster.api.total.endpoints.calculate.vo.CalculatePlanResponse;
import org.routemaster.api.total.endpoints.calculate.vo.CalculationUnit;
import org.routemaster.api.total.endpoints.calculate.vo.SendKakaoCalculateRequest;
import org.routemaster.api.total.endpoints.calculate.vo.SendKakaoCalculateResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class CalculateEndpointService {

    private final PlanActivityService planActivityService;
    private final KakaoMessageService kakaoMessageService;

    public Mono<CalculatePlanResponse> calculatePlan(String planGroupId) {
        List<PlanActivity> activities = getActivities(planGroupId);
        List<CalculationUnit> calculated = getCalculationUnits(activities);
        return Mono.just(CalculatePlanResponse.builder()
                .calculated(calculated)
            .build());
    }

    public Mono<SendKakaoCalculateResponse> sendKakao(String planGroupId, SendKakaoCalculateRequest request) {
        TextTemplate template = TextTemplate.builder()
            .text("Trip Marker에서 정산 결과가 도착했습니다.")
            .link(Link.builder()
                .webUrl("http://api.route-master.org/calculate/" + planGroupId)
                .build())
            .build();
        Mono<BasicTemplateMessageResponse> responseMono = kakaoMessageService.sendTextTemplate(request.getAccessToken(), request.getReceiverUuids(), template);
        return responseMono.map(response -> SendKakaoCalculateResponse.builder()
            .kakaoResponse(response)
            .build());
    }


    private List<PlanActivity> getActivities(String planGroupId) {
        return planActivityService.listByPlanGroupId(planGroupId)
            .collectList().block();
    }

    public List<CalculationUnit> getCalculationUnits(List<PlanActivity> activities) {
        Map<String, Map<String, Double>> calcMap = new HashMap<>();
        for (PlanActivity activity : activities) {
            PlanPaymentInfo paymentInfo = activity.getPaymentInfo();

            for (PlanPaymentLog paymentLog : paymentInfo.getPaymentLogs()) {
                Set<String> participants = paymentLog.getParticipants();
                String paid = paymentLog.getPaid();
                Double payment = paymentLog.getPayment();

                for (String participant : participants) {
                    if (!participant.equals(paid)) {
                        Double unit = payment / participants.size();
                        calcMap.computeIfAbsent(participant, k -> new HashMap<>())
                            .compute(paid, (k, v) -> v == null ? unit : v + unit);
                    }
                }
            }
        }

        for (Map.Entry<String, Map<String, Double>> entry1 : calcMap.entrySet()) {
            String sender = entry1.getKey();

            for (Map.Entry<String, Double> entry2 : entry1.getValue().entrySet()) {
                String receiver = entry2.getKey();
                Double amount = entry2.getValue();

                if (calcMap.containsKey(receiver) && calcMap.get(receiver).containsKey(sender)) {
                    Double reversedAmount = calcMap.get(receiver).get(sender);

                    Double sub = Math.min(amount, reversedAmount);

                    calcMap.get(sender).compute(receiver, (k, v) -> v - sub);
                    calcMap.get(receiver).compute(sender, (k, v) -> v - sub);
                }
            }
        }

        List<CalculationUnit> calculationUnits = new ArrayList<>();
        for (Map.Entry<String, Map<String, Double>> entry1 : calcMap.entrySet()) {
            String sender = entry1.getKey();

            for (Map.Entry<String, Double> entry2 : entry1.getValue().entrySet()) {
                String receiver = entry2.getKey();
                Double amount = entry2.getValue();
                calculationUnits.add(CalculationUnit.builder()
                        .sender(sender)
                        .receiver(receiver)
                        .amount(amount)
                    .build());
            }
        }

        return calculationUnits;
    }
}
