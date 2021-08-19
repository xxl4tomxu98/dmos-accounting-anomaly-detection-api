package com.ftlllc.dmosEliteApi.service;

import com.ftlllc.dmosEliteApi.domain.RentalBooth;
import com.ftlllc.dmosEliteApi.dto.RentalBoothDTO;
import com.ftlllc.dmosEliteApi.dto.payload.AnomalyScoreMonthlyPayloadDTO;
import com.ftlllc.dmosEliteApi.dto.payload.FeesPaidReportPayloadDTO;
import com.ftlllc.dmosEliteApi.repository.rentalBooth.RentalBoothCustomRepository;
import com.ftlllc.dmosEliteApi.repository.rentalBooth.RentalBoothRepository;
import com.ftlllc.dmosEliteApi.repository.rentalBooth.RentalBoothSpecs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class RentalBoothService
{

    // TODO: Add error checking, fix exception throwing.
    @Autowired
    private RentalBoothRepository rentalBoothRepository;

    @Autowired
    private RentalBoothCustomRepository rentalBoothCustomRepository;

    public Page<RentalBoothDTO> getAllRentalBooths(
            LocalDate startDate, LocalDate endDate, Integer pageNumber, Integer pageSize, String sortBy, Sort.Direction sortOrder
    ) {
        Specification<RentalBooth> predicates = Specification.where(RentalBoothSpecs.findAllByCreateDateBetween(startDate, endDate));

        return rentalBoothRepository
                .findAll(predicates,
                        PageRequest.of(pageNumber, pageSize,
                                Sort.by(sortOrder, sortBy)))
                .map(RentalBoothDTO::new);
    }

    public Map<String, Long> getTotalRentalBoothFrequencyByMonth(
            LocalDate startDate, LocalDate endDate
    ) {
        Query q = rentalBoothCustomRepository.getFrequencyCountBetweenDates(startDate, endDate);

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

    public List<FeesPaidReportPayloadDTO> getFeedPaidAmountsReport() {
        Query q = rentalBoothCustomRepository.getFeePaidAmounts();

        List<Object[]> queryResult = q.getResultList();
        List<FeesPaidReportPayloadDTO> result = new ArrayList<>();

        for (Object[] record : queryResult) {
            FeesPaidReportPayloadDTO resultDTO = new FeesPaidReportPayloadDTO((BigInteger) record[0], (BigDecimal) record[1], (BigDecimal) record[2], (BigDecimal) record[3]);
            result.add(resultDTO);
        }

        return result;
    }

    public Map<String, Long> getAnomalyScoreMonthlyReport(BigDecimal anomalyScoreMin) {
        Query q = rentalBoothCustomRepository.getAnomalyScoreMonthly(anomalyScoreMin);

        List<Object[]> queryResult = q.getResultList();
        Map<String, Long> resultMap = new HashMap<>();

        for (Object[] objects : queryResult) {
            String date = objects[0] + "-" + objects[1];
            resultMap.put(date, (Long) objects[2]);
        }

        return resultMap;
    }
}
