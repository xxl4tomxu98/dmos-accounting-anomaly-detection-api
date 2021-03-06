package com.ftlllc.dmosEliteApi.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.Map;

@RequestMapping("/api/reports")
public interface IReportResource
{

//    @GetMapping("/feeVersusPaidAmount")
//    List<FeesPaidReportPayloadDTO> getFeedPaidAmountsReport();

    @GetMapping("/anomalyScoreCountByMonth")
    Map<String, Long> getAnomalyScoreByMonthReport(
            @RequestParam(value="anomalyScore", defaultValue = "0.5")
            BigDecimal anomalyScore
    );
}
