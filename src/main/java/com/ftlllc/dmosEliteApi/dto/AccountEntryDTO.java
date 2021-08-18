package com.ftlllc.dmosEliteApi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ftlllc.dmosEliteApi.domain.AccountEntry;
import io.swagger.annotations.ApiModel;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "AccountEntry", description = "Account Entry")
public class AccountEntryDTO
{

    private Integer accountEntryId;
    private BigDecimal amount;
    private String status;
    // ^^ todo I don't like this... i want status to be an enum
    private BigInteger orderId;
    private BigInteger groupId;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate createDate;
    private RentalBoothDTO rentalBoothDTO;
    public AccountEntryDTO(AccountEntry accountEntry)
    {
        BeanUtils.copyProperties(accountEntry, this);
        this.rentalBoothDTO = accountEntry.getRentalBooth() != null ? new RentalBoothDTO(accountEntry.getRentalBooth()) : null;
    }

}
