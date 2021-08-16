package com.ftlllc.dmosEliteApi.service;

import com.ftlllc.dmosEliteApi.domain.AccountEntry;
import com.ftlllc.dmosEliteApi.dto.AccountEntryDTO;
import com.ftlllc.dmosEliteApi.repository.AccountEntryCustomRepository;
import com.ftlllc.dmosEliteApi.repository.AccountEntryRepository;
import com.ftlllc.dmosEliteApi.repository.AccountEntrySpecs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.time.LocalDate;
import java.util.*;

@Service
@Slf4j
public class AccountEntryService
{
    // TODO: Add error checking, fix exception throwing.
    @Autowired
    private AccountEntryRepository accountEntryRepository;

    @Autowired
    private AccountEntryCustomRepository accountEntryCustomRepository;

    public Page<AccountEntryDTO> getAllAccountEntries(
        LocalDate startDate, LocalDate endDate, Integer pageNumber, Integer pageSize, String sortBy, Sort.Direction sortOrder
    ) {
        Specification<AccountEntry> predicates = Specification.where(AccountEntrySpecs.findAllByCreateDateBetween(startDate, endDate));

        return accountEntryRepository
                .findAll(predicates,
                        PageRequest.of(pageNumber, pageSize,
                                Sort.by(sortOrder, sortBy)))
                .map(AccountEntryDTO::new);
    }

    public Page<Map<LocalDate, Long>> getTotalAccountEntriesByDate(
            LocalDate startDate, LocalDate endDate, Integer pageNumber, Integer pageSize
    ) {
        Integer totalCount = 0;
        Query q = accountEntryCustomRepository.getFrequencyCountBetweenDates(startDate, endDate);

        List<Object[]> queryResult = q.getResultList();
        if (queryResult != null && !queryResult.isEmpty()) {
            totalCount = queryResult.size();
        }

        // convert result into a list of hashmaps
        List<Map<LocalDate, Long>> resultList = new ArrayList<>();
        for (Object[] record : queryResult) {
            LocalDate key = (LocalDate) record[0];
            Long value = (Long) record[1];
            Map<LocalDate, Long> newRecord = new HashMap<>();
            newRecord.put(key, value);
            resultList.add(newRecord);
        }

        return new PageImpl<>(
                resultList,
                PageRequest.of(pageNumber, pageSize),
                totalCount
        );
    }
}
