package com.ftlllc.dmosEliteApi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ftlllc.dmosEliteApi.domain.RentalBooth;
import io.swagger.annotations.ApiModel;
import lombok.*;
import java.math.BigInteger;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "AnomalyMap", description = "Anomaly Map")
public class AnomalyMapDTO
{

    private Integer orderId;
    private Float score;
    @JsonIgnore
    private RentalBooth rentalBooth;

}
