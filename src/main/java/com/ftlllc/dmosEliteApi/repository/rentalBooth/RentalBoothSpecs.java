package com.ftlllc.dmosEliteApi.repository.rentalBooth;

import com.ftlllc.dmosEliteApi.domain.*;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class RentalBoothSpecs
{

    public static Specification<RentalBooth> findAllByCreateDateBetween(LocalDate startDate, LocalDate endDate) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if(startDate == null && endDate == null) {
                return criteriaBuilder.equal(criteriaBuilder.literal(true), criteriaBuilder.literal(true));
            }
            if (startDate == null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get(RentalBooth_.createDate), endDate);
            }

            if (endDate == null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get(RentalBooth_.createDate), startDate);
            }

            return criteriaBuilder.between(root.get(RentalBooth_.createDate), startDate, endDate);
        };
    }

}
