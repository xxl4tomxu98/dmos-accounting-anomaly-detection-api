package com.ftlllc.dmosEliteApi.service;

import com.ftlllc.dmosEliteApi.domain.RentalBooth;
import com.ftlllc.dmosEliteApi.dto.RentalBoothDTO;
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

    public Map<LocalDate, Long> getTotalRentalBoothsByDate(
            LocalDate startDate, LocalDate endDate
    ) {
        Query q = rentalBoothCustomRepository.getFrequencyCountBetweenDates(startDate, endDate);

        List<Object[]> queryResult = q.getResultList();

        // convert result into a list of hashmaps
        Map<LocalDate, Long> resultMap = new HashMap<>();
        for (Object[] record : queryResult) {
            LocalDate key = (LocalDate) record[0];
            Long value = (Long) record[1];
            resultMap.put(key, value);
        }

        return resultMap;
    }

    public List<Map<String, Object>> getFeedPaidAmountsReport() {
        Query q = rentalBoothCustomRepository.getFeePaidAmounts();

        List<Object[]> queryResult = q.getResultList();
        List<Map<String, Object>> result = new ArrayList<>();

        for (Object[] record : queryResult) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("orderId", record[0]);
            resultMap.put("fee", record[1]);
            resultMap.put("totalAmount", record[2]);
            resultMap.put("diff", record[3]);
            result.add(resultMap);
        }

        return result;
    }

}
