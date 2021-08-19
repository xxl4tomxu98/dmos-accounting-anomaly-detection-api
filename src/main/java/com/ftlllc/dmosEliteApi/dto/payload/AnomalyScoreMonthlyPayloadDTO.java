package com.ftlllc.dmosEliteApi.dto.payload;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AnomalyScoreMonthlyPayloadDTO {

    private String createDate;
    private Long anomalyCount;

}
