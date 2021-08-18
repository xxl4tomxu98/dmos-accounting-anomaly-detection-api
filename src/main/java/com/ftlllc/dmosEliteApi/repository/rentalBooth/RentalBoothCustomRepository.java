package com.ftlllc.dmosEliteApi.repository.rentalBooth;

import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.time.LocalDate;

@Repository
public interface RentalBoothCustomRepository
{
    Query getFrequencyCountBetweenDates(LocalDate startDate, LocalDate endDate);

    Query getFeePaidAmounts();
}
