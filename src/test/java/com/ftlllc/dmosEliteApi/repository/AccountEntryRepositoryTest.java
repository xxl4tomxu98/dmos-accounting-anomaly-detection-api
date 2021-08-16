package com.ftlllc.dmosEliteApi.repository;

import com.ftlllc.dmosEliteApi.DmosEliteApiApplicationTests;
import com.ftlllc.dmosEliteApi.domain.AccountEntry;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AccountEntryRepositoryTest extends DmosEliteApiApplicationTests
{

    @Autowired
    private AccountEntryRepository aer;

    private LocalDate startDate = LocalDate.ofYearDay(2021, 1);
    // aug 1, 2021
    private LocalDate endDate = LocalDate.ofYearDay(2021, 213);
    private List<AccountEntry> results = null;

    @Test
    public void validateDataReturnedFromFindAll()
    {
        results = aer.findAll();
        assertEquals(198, results.size());
        assertNotNull(results.get(1).getAccountEntryId());
        assertNotNull(results.get(1).getAmount());
        assertNotNull(results.get(1).getStatus());
        assertNotNull(results.get(1).getOrderId());
        assertNotNull(results.get(1).getGroupId());
        assertNotNull(results.get(1).getCreateDate());
    }

    @Test
    public void getAllAccountEntriesBetweenNullDates(){
        results = aer.getAllBetweenDates(null, null);
        assertEquals(0, results.size());
    }

    @Test
    public void getAllAccountEntriesBetweenDates()
    {
        results = aer.getAllBetweenDates(startDate, endDate);
        assertEquals(79, results.size());
    }

    @Test
    public void getAllAccountEntriesMissingStartDate()
    {
        results = aer.getAllBetweenDates(null, endDate);
        assertEquals(0, results.size());
    }

    @Test
    public void getAllAccountEntriesMissingEndDate()
    {
        results = aer.getAllBetweenDates(startDate, null);
        assertEquals(0, results.size());
    }

//    @Test
//    public void getAccountEntryFrequency()
//    {
//
//        Dictionary<LocalDate, Integer> frequencyResults = aer.getFrequencyCountBetweenDates(startDate, endDate);
//        assertEquals(79, frequencyResults.size());
//
//    }

}
