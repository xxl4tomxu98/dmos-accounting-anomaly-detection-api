package com.ftlllc.dmosEliteApi.dto;

import com.ftlllc.dmosEliteApi.domain.RentalBooth;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@ApiModel(value = "RentalBooth", description = "Rental Booth")
public class RentalBoothDTO
{

    private Integer id;
    private String rental;
    private BigDecimal fee;
    // ^^ todo do i want bigDecimal
    private String paid;
    private BigInteger orderId;
    private BigInteger groupId;
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate createDate;

    public RentalBoothDTO(RentalBooth rentalBooth)
    {
        BeanUtils.copyProperties(rentalBooth, this);
    }
}
