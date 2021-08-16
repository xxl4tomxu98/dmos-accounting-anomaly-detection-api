package com.ftlllc.dmosEliteApi.rest;

import com.ftlllc.dmosEliteApi.dto.AccountEntryDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("/api/accountEntries")
public interface IAccountEntryResource
{
    @GetMapping
    List<AccountEntryDTO> getAllAccountEntries(
                    @RequestParam(required = false)
                    @JsonFormat(pattern="dd/MM/yyyy")
                    LocalDate startDate,
                    @RequestParam(required = false)
                    @JsonFormat(pattern="dd/MM/yyyy")
                    LocalDate endDate) throws IllegalAccessException;


}
