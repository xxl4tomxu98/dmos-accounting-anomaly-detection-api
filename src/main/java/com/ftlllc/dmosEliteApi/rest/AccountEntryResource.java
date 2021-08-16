package com.ftlllc.dmosEliteApi.rest;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ftlllc.dmosEliteApi.dto.AccountEntryDTO;
import com.ftlllc.dmosEliteApi.service.AccountEntryService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Map<Date, Integer> getTotalAccountEntriesByDate(
                    @RequestParam(required = false)
                    @JsonFormat(pattern="dd/MM/yyyy")
                    LocalDate startDate,
                    @RequestParam(required = false)
                    @JsonFormat(pattern="dd/MM/yyyy")
                    LocalDate endDate)
    {
        return new HashMap<>();
    }

}