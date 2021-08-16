package com.ftlllc.dmosEliteApi.service;

import com.ftlllc.dmosEliteApi.domain.AccountEntry;
import com.ftlllc.dmosEliteApi.dto.AccountEntryDTO;
import com.ftlllc.dmosEliteApi.repository.AccountEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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


    public Page<AccountEntryDTO> getAllAccountEntries(
        LocalDate startDate, LocalDate endDate, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder
    ) throws IllegalAccessException {
        return getAllAccountEntriesFromDb(startDate, endDate, pageNumber, pageSize, sortBy, sortOrder);
    }

    //  Helpers

    private Page<AccountEntryDTO> getAllAccountEntriesFromDb(LocalDate startDate, LocalDate endDate, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) throws IllegalAccessException {
        List<AccountEntry> accountEntries;
        Specification<AccountEntry> predicates = null;
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

        return accountEntryRepository
                .findAll(predicates,
                        PageRequest.of(pageNumber, pageSize,
                                Sort.by(sortBy)))
                .map(AccountEntryDTO::new);
    }

//    private void updateAccountEntry(AccountEntry accountEntry, AccountEntryDTO accountEntryDTO) {
//        BeanUtils.copyProperties(accountEntryDTO, accountEntry);
//    }
}
