package com.ftlllc.dmosEliteApi.dto;

import com.ftlllc.dmosEliteApi.domain.AccountEntry;
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
@ApiModel(value = "AccountEntry", description = "Account Entry")
public class AccountEntryDTO
{

    private Integer accountEntryId;
    private BigDecimal amount;
    // ^^ todo do i want bigDecimal
    private String status;
    // ^^ todo I don't like this... i want status to be an enum
    private BigInteger orderId;
    private BigInteger groupId;
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate createDate;

    public AccountEntryDTO(AccountEntry accountEntry)
    {
        BeanUtils.copyProperties(accountEntry, this);
    }

}
