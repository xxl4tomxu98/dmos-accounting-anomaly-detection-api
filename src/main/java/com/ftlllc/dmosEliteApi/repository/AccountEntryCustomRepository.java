package com.ftlllc.dmosEliteApi.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.time.LocalDate;

@Repository
public interface AccountEntryCustomRepository {
        Query getFrequencyCountBetweenDates(LocalDate startDate, LocalDate endDate);
}