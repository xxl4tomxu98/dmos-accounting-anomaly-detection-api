package com.ftlllc.dmosEliteApi.service;

import com.ftlllc.dmosEliteApi.DmosEliteApiApplicationTests;
import com.ftlllc.dmosEliteApi.dto.AccountEntryDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AccountEntryServiceTest extends DmosEliteApiApplicationTests
{

    @Autowired
    private AccountEntryService accountEntryService;

    @Test
    public void getAllAccountEntries() throws IllegalAccessException {
        List<AccountEntryDTO> results = accountEntryService.getAllAccountEntries(null, null);
        assertEquals(198, results.size());
    }

    @Test
    public void getAllAccountEntriesBetweenDates() throws IllegalAccessException
    {
        LocalDate startDate = LocalDate.ofYearDay(2021, 1);
        // aug 1, 2021
        LocalDate endDate = LocalDate.ofYearDay(2021, 213);
        List<AccountEntryDTO> results = accountEntryService.getAllAccountEntries(startDate, endDate);
        assertEquals(79, results.size());
    }

    @Test(expected = IllegalAccessException.class)
    public void getAllAccountEntriesMissingStartDate() throws IllegalAccessException
    {
        LocalDate endDate = LocalDate.ofYearDay(2021, 213);
        accountEntryService.getAllAccountEntries(null, endDate);
    }

    @Test(expected = IllegalAccessException.class)
    public void getAllAccountEntriesMissingEndDate() throws IllegalAccessException
    {
        LocalDate startDate = LocalDate.ofYearDay(2021, 1);
        accountEntryService.getAllAccountEntries(startDate, null);
    }
}
