package com.ftlllc.dmosEliteApi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ftlllc.dmosEliteApi.domain.AnomalyMap;
import com.ftlllc.dmosEliteApi.domain.RentalBooth;
import io.swagger.annotations.ApiModel;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "RentalBooth", description = "Rental Booth")
public class RentalBoothDTO
{

    private Integer rentalBoothId;
    private String rental;
    private BigDecimal fee;
    private Boolean paid;
    private Integer orderId;
    private BigInteger groupId;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate createDate;
    private BigDecimal anomalyScore;
    @JsonIgnore
    private List<AccountEntryDTO> accountEntryList;
    @JsonIgnore
    private AnomalyMap anomalyMap;

    public RentalBoothDTO(RentalBooth rentalBooth)
    {
        BeanUtils.copyProperties(rentalBooth, this);
        // if we do this in here it creates a circular dependency it seems like
        // gotta figure that out
        // this.accountEntryList = rentalBooth.getAccountEntries() != null ?
        //          rentalBooth.getAccountEntries().stream().map(AccountEntryDTO::new).collect(Collectors.toList())
        //          : null;
    }
}
