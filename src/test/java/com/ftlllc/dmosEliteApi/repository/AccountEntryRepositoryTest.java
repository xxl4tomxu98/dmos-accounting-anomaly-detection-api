package com.ftlllc.dmosEliteApi.repository;

import com.ftlllc.dmosEliteApi.DmosEliteApiApplicationTests;
import com.ftlllc.dmosEliteApi.domain.AccountEntry;
import com.ftlllc.dmosEliteApi.repository.accountEntry.AccountEntryCustomRepository;
import com.ftlllc.dmosEliteApi.repository.accountEntry.AccountEntryRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AccountEntryRepositoryTest extends DmosEliteApiApplicationTests
{

    @Autowired
    private AccountEntryRepository aer;

    @Autowired
    private AccountEntryCustomRepository aecr;

    private LocalDate startDate = LocalDate.ofYearDay(2021, 1);
    // aug 1, 2021
    private LocalDate endDate = LocalDate.ofYearDay(2021, 213);
    private List<AccountEntry> results = null;

    @Test
    public void validateDataReturnedFromFindAll() {
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
    public void getAccountEntryFrequencyAllRecords() {
        Query q = aecr.getFrequencyCountBetweenDates(null, null);

        List<Object[]> queryResult = q.getResultList();
        assertEquals(5, queryResult.size());
    }

    @Test
    public void getAccountEntryFrequencyCustomDates() {
        Query q = aecr.getFrequencyCountBetweenDates(startDate, endDate);

        List<Object[]> queryResult = q.getResultList();
        assertEquals(2, queryResult.size());
    }
}
