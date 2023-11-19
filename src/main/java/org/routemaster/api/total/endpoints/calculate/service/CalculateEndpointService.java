package org.routemaster.api.total.endpoints.calculate.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.routemaster.api.total.domain.kakao.message.KakaoMessageService;
import org.routemaster.api.total.domain.kakao.message.data.base.Link;
import org.routemaster.api.total.domain.kakao.message.data.basic.BasicTemplateMessageResponse;
import org.routemaster.api.total.domain.kakao.message.data.template.TextTemplate;
import org.routemaster.api.total.domain.plan.data.PlanActivity;
import org.routemaster.api.total.domain.plan.data.subdata.PlanPaymentInfo;
import org.routemaster.api.total.domain.plan.service.PlanActivityService;
import org.routemaster.api.total.endpoints.calculate.vo.CalculatePlanResponse;
import org.routemaster.api.total.endpoints.calculate.vo.CalculationUnit;
import org.routemaster.api.total.endpoints.calculate.vo.SendKakaoCalculateRequest;
import org.routemaster.api.total.endpoints.calculate.vo.SendKakaoCalculateResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class CalculateEndpointService {

    private final PlanActivityService planActivityService;
    private final KakaoMessageService kakaoMessageService;

    public Mono<CalculatePlanResponse> calculatePlan(String planGroupId) {
        Flux<PlanActivity> activities = getActivities(planGroupId);
        return getCalculationUnits(activities);
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


    private Flux<PlanActivity> getActivities(String planGroupId) {
        return planActivityService.listByPlanGroupId(planGroupId);
    }

    public Mono<CalculatePlanResponse> getCalculationUnits(Flux<PlanActivity> activities) {
        return activities.map(activity -> {
            PlanPaymentInfo paymentInfo = activity.getPaymentInfo();

            Map<String, Map<String, Double>> calcMap = new HashMap<>();
            paymentInfo.getPaymentLogs().forEach(paymentLog -> {
                Set<String> participants = paymentLog.getParticipants();
                String paid = paymentLog.getPaid();
                Double payment = paymentLog.getPayment();

                participants.forEach(participant -> {
                    if (!participant.equals(paid)) {
                        Double unit = payment / participants.size();
                        calcMap.computeIfAbsent(participant, k -> new HashMap<>())
                            .compute(paid, (k, v) -> v == null ? unit : v + unit);
                    }
                });
            });
            return calcMap;
        }).collect(Collectors.reducing((a, b) -> {
            Map<String, Map<String, Double>> calcMap = new HashMap<>();
            updateCalcMap(a, calcMap);
            updateCalcMap(b, calcMap);
            reduceCalcMap(calcMap);
            return calcMap;
        })).map(calcMap -> calcMap.orElse(new HashMap<>()))
        .map(calcMap -> {
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
            return CalculatePlanResponse.builder()
                .calculated(calculationUnits)
                .build();
        });
    }

    private void updateCalcMap(Map<String, Map<String, Double>> b,
        Map<String, Map<String, Double>> calcMap) {
        for (Map.Entry<String, Map<String, Double>> entry1 : b.entrySet()) {
            String sender = entry1.getKey();

            for (Map.Entry<String, Double> entry2 : entry1.getValue().entrySet()) {
                String receiver = entry2.getKey();
                Double amount = entry2.getValue();
                calcMap.computeIfAbsent(sender, k -> new HashMap<>())
                    .compute(receiver, (k, v) -> v == null ? amount : v + amount);
            }
        }
    }

    private void reduceCalcMap(Map<String, Map<String, Double>> a) {
        Set<String> all = new HashSet<>();
        all.addAll(a.keySet());
        a.values().forEach(map -> all.addAll(map.keySet()));
        for (String p1 : all) {
            for (String p2 : all) {
                if (p1.equals(p2)) continue;
                if (a.containsKey(p1) && a.get(p1).containsKey(p2)) {
                    if (a.containsKey(p2) && a.get(p2).containsKey(p1)) {
                        Double amount = a.get(p1).get(p2);
                        Double reversed = a.get(p2).get(p1);

                        if (reversed < amount) {
                            a.get(p2).remove(p1);
                            a.get(p1).compute(p2, (k, v) -> v - reversed);
                        } else if (reversed > amount) {
                            a.get(p1).remove(p2);
                            a.get(p2).compute(p1, (k, v) -> v - amount);
                        }
                    }
                }
            }
        }
    }
}
