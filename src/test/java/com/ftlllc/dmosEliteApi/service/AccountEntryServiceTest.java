package com.ftlllc.dmosEliteApi.service;

import com.ftlllc.dmosEliteApi.DmosEliteApiApplicationTests;
import com.ftlllc.dmosEliteApi.dto.AccountEntryDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class AccountEntryServiceTest extends DmosEliteApiApplicationTests
{

    @Autowired
    private AccountEntryService accountEntryService;

    @Test
    public void getAllAccountEntries() throws IllegalAccessException {
        Page<AccountEntryDTO> results = accountEntryService.getAllAccountEntries(null, null, 1, 25, "", "ASC");
        assertEquals(198, results.getNumberOfElements());
    }

    @Test
    public void getAllAccountEntriesBetweenDates() throws IllegalAccessException
    {
        LocalDate startDate = LocalDate.ofYearDay(2021, 1);
        // aug 1, 2021
        LocalDate endDate = LocalDate.ofYearDay(2021, 213);
        Page<AccountEntryDTO> results = accountEntryService.getAllAccountEntries(startDate, endDate, 1, 25, "", "ASC");
        assertEquals(79, results.getNumberOfElements());
    }

    @Test(expected = IllegalAccessException.class)
    public void getAllAccountEntriesMissingStartDate() throws IllegalAccessException
    {
        LocalDate endDate = LocalDate.ofYearDay(2021, 213);
        accountEntryService.getAllAccountEntries(null, endDate, 1, 25, "", "ASC");
    }

    @Test(expected = IllegalAccessException.class)
    public void getAllAccountEntriesMissingEndDate() throws IllegalAccessException
    {
        LocalDate startDate = LocalDate.ofYearDay(2021, 1);
        accountEntryService.getAllAccountEntries(startDate, null, 1, 25, "", "ASC");
    }
}
