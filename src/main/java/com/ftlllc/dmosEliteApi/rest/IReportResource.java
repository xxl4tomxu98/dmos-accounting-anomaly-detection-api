package com.ftlllc.dmosEliteApi.rest;

import com.ftlllc.dmosEliteApi.dto.payload.AnomalyScoreMonthlyPayloadDTO;
import com.ftlllc.dmosEliteApi.dto.payload.FeesPaidReportPayloadDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@RequestMapping("/api/reports")
public interface IReportResource
{

//    @GetMapping("/feeVersusPaidAmount")
//    List<FeesPaidReportPayloadDTO> getFeedPaidAmountsReport();

    @GetMapping("/anomalyScoreCountByMonth")
    List<AnomalyScoreMonthlyPayloadDTO> getAnomalyScoreByMonthReport(
            @RequestParam(value="anomalyScore", defaultValue = "0.5")
            BigDecimal anomalyScore
    );
}
