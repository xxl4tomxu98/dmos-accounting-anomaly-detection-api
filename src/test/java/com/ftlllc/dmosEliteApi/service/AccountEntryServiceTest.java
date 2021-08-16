package com.ftlllc.dmosEliteApi.service;

import com.ftlllc.dmosEliteApi.DmosEliteApiApplicationTests;
import com.ftlllc.dmosEliteApi.dto.AccountEntryDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class AccountEntryServiceTest extends DmosEliteApiApplicationTests
{

    @Autowired
    private AccountEntryService accountEntryService;

    @Test
    public void getAllAccountEntries() {
        Page<AccountEntryDTO> results = accountEntryService.getAllAccountEntries(null, null, 1, 100, "createDate", Sort.Direction.ASC);
        assertEquals(198, results.getTotalElements());
        assertEquals(2, results.getTotalPages());
    }

    @Test
    public void getAllAccountEntriesBetweenDates() {
        LocalDate startDate = LocalDate.ofYearDay(2021, 1);
        // aug 1, 2021
        LocalDate endDate = LocalDate.ofYearDay(2021, 213);
        Page<AccountEntryDTO> results = accountEntryService.getAllAccountEntries(startDate, endDate, 1, 200, "createDate", Sort.Direction.ASC);
        assertEquals(79, results.getTotalElements());
        assertEquals(1, results.getTotalPages());
    }

    @Test
    public void getAllAccountEntriesMissingStartDate() {
        LocalDate endDate = LocalDate.ofYearDay(2021, 213);
        Page<AccountEntryDTO> results = accountEntryService.getAllAccountEntries(null, endDate, 1, 25, "createDate", Sort.Direction.ASC);
        assertEquals(79, results.getTotalElements());
        assertEquals(4, results.getTotalPages());
    }

    @Test
    public void getAllAccountEntriesMissingEndDate() {
        LocalDate startDate = LocalDate.ofYearDay(2021, 1);
        Page<AccountEntryDTO> results = accountEntryService.getAllAccountEntries(startDate, null, 1, 150, "createDate", Sort.Direction.ASC);
        assertEquals(198, results.getTotalElements());
        assertEquals(2, results.getTotalPages());
    }

    @Test
    public void getAllAccountEntriesOutOfBoundsPageNumber() {
        LocalDate startDate = LocalDate.ofYearDay(2021, 1);
        Page<AccountEntryDTO> results = accountEntryService.getAllAccountEntries(startDate, null, 5, 150, "createDate", Sort.Direction.ASC);
        assertEquals(0, results.getNumberOfElements());
    }
}
