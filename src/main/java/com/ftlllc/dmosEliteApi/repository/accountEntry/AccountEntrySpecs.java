package com.ftlllc.dmosEliteApi.repository.accountEntry;

import com.ftlllc.dmosEliteApi.domain.AccountEntry;
import com.ftlllc.dmosEliteApi.domain.AccountEntry_;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class AccountEntrySpecs {

    public static Specification<AccountEntry> findAllByCreateDateBetween(LocalDate startDate, LocalDate endDate) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if(startDate == null && endDate == null)
            {
                return criteriaBuilder.conjunction();
            }
            if (startDate == null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get(AccountEntry_.createDate), endDate);
            }

            if (endDate == null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get(AccountEntry_.createDate), startDate);
            }

            return criteriaBuilder.between(root.get(AccountEntry_.createDate), startDate, endDate);
        };
    }
}
