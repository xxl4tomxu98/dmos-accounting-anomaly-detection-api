package com.ftlllc.dmosEliteApi.rest;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ftlllc.dmosEliteApi.dto.AccountEntryDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

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


    @GetMapping("/frequency")
    Map<Date, Integer> getTotalAccountEntriesByDate(
                    @RequestParam(required = false)
                    @JsonFormat(pattern="dd/MM/yyyy")
                    LocalDate startDate,
                    @RequestParam(required = false)
                    @JsonFormat(pattern="dd/MM/yyyy")
                    LocalDate endDate) throws IllegalAccessException;
}
