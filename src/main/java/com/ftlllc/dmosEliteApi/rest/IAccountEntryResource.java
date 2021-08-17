package com.ftlllc.dmosEliteApi.rest;

import com.ftlllc.dmosEliteApi.dto.AccountEntryDTO;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/accountEntries")
public interface IAccountEntryResource
{
    @GetMapping
    Page<AccountEntryDTO> getAllAccountEntries(
        @RequestParam(value="startDate", required = false)
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate startDate,
        @RequestParam(value="endDate", required = false)
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate endDate,
        @ApiParam(value = "Page number")
        @Min(value = 1, message = "pageNumber has to be at least 1")
        @RequestParam(value="pageNumber", required = false, defaultValue = "1")
        Integer pageNumber,
        @ApiParam(value = "Number of rows to return")
        @Min(value = 1, message = "pageSize has to be at least 1")
        @Max(value = 500, message = "pageSize can be a maximum of 500")
        @RequestParam(value="pageSize", required = false, defaultValue = "25")
        Integer pageSize,
        @RequestParam(value="sortBy", required = false, defaultValue = "createDate")
        String sortBy,
        @RequestParam(value="sortOrder", required = false, defaultValue = "ASC")
        String sortOrder
    ) throws IllegalAccessException;


    @GetMapping("/frequency")
    List<Map<LocalDate, Long>> getTotalAccountEntriesByDate(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate startDate,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate endDate
    ) throws IllegalAccessException;
}
