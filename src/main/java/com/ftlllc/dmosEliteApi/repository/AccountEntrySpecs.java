package com.ftlllc.dmosEliteApi.repository;

import com.ftlllc.dmosEliteApi.domain.AccountEntry;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Date;

public class AccountEntrySpecs {

    public static Specification<AccountEntry> findAllByCreateDateBetween(Date startDate, Date endDate) {
        return new Specification<AccountEntry>() {
            @Override
            public Predicate toPredicate(Root<AccountEntry> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.between(root.get("create_date"), startDate, endDate);
            }
        };
    }
}
