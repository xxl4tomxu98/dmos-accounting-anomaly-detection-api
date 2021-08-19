package com.ftlllc.dmosEliteApi.rest;

import com.ftlllc.dmosEliteApi.dto.payload.AnomalyScoreMonthlyPayloadDTO;
import com.ftlllc.dmosEliteApi.dto.payload.FeesPaidReportPayloadDTO;
import com.ftlllc.dmosEliteApi.service.RentalBoothService;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Map;

@RestController
public class ReportResource implements IReportResource {

    @Inject
    private RentalBoothService rentalBoothService;

//    @Override
//    public List<FeesPaidReportPayloadDTO> getFeedPaidAmountsReport() {
//        return rentalBoothService.getFeedPaidAmountsReport();
//    }

    @Override
    public Map<String, Long> getAnomalyScoreByMonthReport(BigDecimal anomalyScore) {
        return rentalBoothService.getAnomalyScoreMonthlyReport(anomalyScore);
    }
}