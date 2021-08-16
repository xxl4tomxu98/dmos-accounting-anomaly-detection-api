package com.ftlllc.dmosEliteApi.service;

import com.ftlllc.dmosEliteApi.domain.AccountEntry;
import com.ftlllc.dmosEliteApi.dto.AccountEntryDTO;
import com.ftlllc.dmosEliteApi.repository.AccountEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AccountEntryService
{
    // TODO: Add error checking, fix exception throwing.
    @Autowired
    private AccountEntryRepository accountEntryRepository;


    public List<AccountEntryDTO> getAllAccountEntries(LocalDate startDate, LocalDate endDate) throws IllegalAccessException {
        return getAllAccountEntriesFromDb(startDate, endDate).stream().map(AccountEntryDTO::new).collect(Collectors.toList());
    }

    //  Helpers

    private List<AccountEntry> getAllAccountEntriesFromDb(LocalDate startDate, LocalDate endDate) throws IllegalAccessException {
        List<AccountEntry> accountEntries;
        if(startDate == null && endDate == null)
        {
            accountEntries = accountEntryRepository.findAll();
        }
        else
        {
            if(startDate == null || endDate == null)
            { throw new IllegalAccessException("Either startDate or endDate is null. Please provide both."); }
            // not throwing an error cause its just a null result set if nothing comes back
            accountEntries = accountEntryRepository.getAllBetweenDates(startDate, endDate);//.orElse(null);
        }

        return accountEntries;
    }

//    private void updateAccountEntry(AccountEntry accountEntry, AccountEntryDTO accountEntryDTO) {
//        BeanUtils.copyProperties(accountEntryDTO, accountEntry);
//    }
}
