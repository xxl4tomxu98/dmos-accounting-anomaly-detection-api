package com.ftlllc.dmosEliteApi.rest;

import com.ftlllc.dmosEliteApi.dto.AccountEntryDTO;
import com.ftlllc.dmosEliteApi.service.AccountEntryService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.domain.Page;
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
    public Page<AccountEntryDTO> getAllAccountEntries(
                    @RequestParam(required = false)
                    @JsonFormat(pattern="dd/MM/yyyy")
                    LocalDate startDate,
                    @RequestParam(required = false)
                    @JsonFormat(pattern="dd/MM/yyyy")
                    LocalDate endDate,
                    @RequestParam(required = false) Integer pageNumber,
                    @RequestParam(required = false) Integer pageSize,
                    @RequestParam(required = false) String sortBy,
                    @RequestParam(required = false) String sortOrder
    ) throws IllegalAccessException
    {
        // set sensible default values for pagination
        if (pageNumber == null) {
            pageNumber = 1;
        }

        if (pageSize == null) {
            pageSize = 25;
        }

        if (sortBy == null) {
            sortBy = "";
        }

        if (sortOrder == null) {
            sortOrder = "ASC";
        }
        return accountEntryService.getAllAccountEntries(startDate, endDate, pageNumber, pageSize, sortBy, sortOrder);
    }

}