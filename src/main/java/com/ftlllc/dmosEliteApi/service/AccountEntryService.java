package com.ftlllc.dmosEliteApi.service;

import com.ftlllc.dmosEliteApi.domain.AccountEntry;
import com.ftlllc.dmosEliteApi.dto.AccountEntryDTO;
import com.ftlllc.dmosEliteApi.repository.accountEntry.AccountEntryCustomRepository;
import com.ftlllc.dmosEliteApi.repository.accountEntry.AccountEntryRepository;
import com.ftlllc.dmosEliteApi.repository.accountEntry.AccountEntrySpecs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

        Page<AccountEntry> temp = accountEntryRepository
                .findAll(predicates,
                        PageRequest.of(pageNumber, pageSize,
                                Sort.by(sortOrder, sortBy)));

        return temp.map(AccountEntryDTO::new);
    }

    public Map<String, Long> getTotalAccountEntriesByDate(
            LocalDate startDate, LocalDate endDate
    ) {
//        LocalDate start = startDate;
//        LocalDate end = endDate;
//        // Hardcode date range to compensate for UI
//        if(start == null || end == null) {
//            log.info("*** Assigning defaults ***");
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
//            formatter = formatter.withLocale( Locale.US );
//            start = LocalDate.parse("2020-jan-01", formatter);
//            end = LocalDate.parse("2021-jul-01", formatter);
//        }
        Query q = accountEntryCustomRepository.getFrequencyCountBetweenDates(startDate, endDate);

        List<Object[]> queryResult = q.getResultList();

        // convert result into a list of hashmaps
        Map<String, Long> resultMap = new HashMap<>();
        for (Object[] record : queryResult) {
            String date = record[0] + "-" + record[1];
            Long value = (Long) record[2];
            resultMap.put(date, value);
        }

        return resultMap;
    }
}
