package com.ftlllc.dmosEliteApi.rest;

import com.ftlllc.dmosEliteApi.dto.AccountEntryDTO;
import com.ftlllc.dmosEliteApi.service.AccountEntryService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

@RestController
public class AccountEntryResource implements IAccountEntryResource
{

    @Inject
    private AccountEntryService accountEntryService;

    @Override
    //@RolesAllowed("dmos_read")
    public List<AccountEntryDTO> getAllAccountEntries(
                    @RequestParam(required = false)
                    @JsonFormat(pattern="dd/MM/yyyy")
                    LocalDate startDate,
                    @RequestParam(required = false)
                    @JsonFormat(pattern="dd/MM/yyyy")
                    LocalDate endDate) throws IllegalAccessException
    {
        return accountEntryService.getAllAccountEntries(startDate, endDate);
    }

}