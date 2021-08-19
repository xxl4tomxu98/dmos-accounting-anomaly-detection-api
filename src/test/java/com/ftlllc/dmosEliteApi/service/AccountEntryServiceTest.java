package com.ftlllc.dmosEliteApi.service;

import com.ftlllc.dmosEliteApi.DmosEliteApiApplicationTests;
import com.ftlllc.dmosEliteApi.dto.AccountEntryDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AccountEntryServiceTest extends DmosEliteApiApplicationTests
{

    @Autowired
    private AccountEntryService accountEntryService;

    protected Page<AccountEntryDTO> results = null;
    protected List<AccountEntryDTO> resultList = null;

    @Test
    public void getAllAccountEntries() {
        results = accountEntryService.getAllAccountEntries(null, null, 1, 100, "createDate", Sort.Direction.ASC);
        assertEquals(198, results.getTotalElements());
        assertEquals(2, results.getTotalPages());
        resultList = results.getContent();
        assertNotNull(resultList.get(1).getAccountEntryId());
        assertNotNull(resultList.get(1).getAmount());
        assertNotNull(resultList.get(1).getStatus());
        assertNotNull(resultList.get(1).getOrderId());
        assertNotNull(resultList.get(1).getGroupId());
        assertNotNull(resultList.get(1).getCreateDate());
    }

    @Test
    public void getAllAccountEntriesBetweenDates() {
        results = accountEntryService.getAllAccountEntries(startDate, endDate, 1, 200, "createDate", Sort.Direction.ASC);
        assertEquals(79, results.getTotalElements());
        assertEquals(1, results.getTotalPages());
    }

    @Test
    public void getAllAccountEntriesMissingStartDate() {
        results = accountEntryService.getAllAccountEntries(null, endDate, 1, 25, "createDate", Sort.Direction.ASC);
        assertEquals(79, results.getTotalElements());
        assertEquals(4, results.getTotalPages());
    }

    @Test
    public void getAllAccountEntriesMissingEndDate() {
        results = accountEntryService.getAllAccountEntries(startDate, null, 1, 150, "createDate", Sort.Direction.ASC);
        assertEquals(198, results.getTotalElements());
        assertEquals(2, results.getTotalPages());
    }

    @Test
    public void getAllAccountEntriesOutOfBoundsPageNumber() {
        results = accountEntryService.getAllAccountEntries(startDate, null, 5, 150, "createDate", Sort.Direction.ASC);
        assertEquals(0, results.getNumberOfElements());
    }
}
