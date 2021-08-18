package com.ftlllc.dmosEliteApi.rest;

import com.ftlllc.dmosEliteApi.dto.payload.FeesPaidReportPayloadDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api/reports")
public interface IReportResource {
    @GetMapping("/FEE_V_PAID_AMOUNTS")
    List<FeesPaidReportPayloadDTO> getFeedPaidAmountsReport();
}
