package com.ftlllc.dmosEliteApi.dto.payload;

import lombok.*;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FeesPaidReportPayloadDTO {

    private BigInteger orderId;
    private BigDecimal fee;
    private BigDecimal totalAmount;
    private BigDecimal diff;

}
