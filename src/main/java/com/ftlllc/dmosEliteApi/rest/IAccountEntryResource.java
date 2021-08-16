package com.ftlllc.dmosEliteApi.rest;

import com.ftlllc.dmosEliteApi.dto.AccountEntryDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("/api/accountEntries")
public interface IAccountEntryResource
{
    @GetMapping
    Page<AccountEntryDTO> getAllAccountEntries(
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
    ) throws IllegalAccessException;


}
