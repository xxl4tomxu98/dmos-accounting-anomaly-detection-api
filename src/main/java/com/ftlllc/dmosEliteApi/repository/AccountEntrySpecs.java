package com.ftlllc.dmosEliteApi.repository;

import com.ftlllc.dmosEliteApi.domain.AccountEntry;
import com.ftlllc.dmosEliteApi.domain.AccountEntry_;
import lombok.SneakyThrows;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;

public class AccountEntrySpecs {

    public static Specification<AccountEntry> findAllByCreateDateBetween(LocalDate startDate, LocalDate endDate) {
        return new Specification<AccountEntry>() {
            @SneakyThrows
            @Override
            public Predicate toPredicate(Root<AccountEntry> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                if(startDate == null && endDate == null)
                {
                    return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
                }
                if (startDate == null) {
                    return criteriaBuilder.lessThanOrEqualTo(root.get(AccountEntry_.createDate), endDate);
                }

                if (endDate == null) {
                    return criteriaBuilder.greaterThanOrEqualTo(root.get(AccountEntry_.createDate), startDate);
                }

                return criteriaBuilder.between(root.get(AccountEntry_.createDate), startDate, endDate);
            }
        };
    }
}
