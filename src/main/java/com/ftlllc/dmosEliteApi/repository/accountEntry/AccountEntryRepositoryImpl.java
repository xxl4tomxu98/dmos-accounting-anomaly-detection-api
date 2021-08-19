package com.ftlllc.dmosEliteApi.repository.accountEntry;

import com.ftlllc.dmosEliteApi.domain.AccountEntry;
import com.ftlllc.dmosEliteApi.domain.AccountEntry_;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AccountEntryRepositoryImpl implements AccountEntryCustomRepository {
        @Autowired
        final EntityManager em;

        public AccountEntryRepositoryImpl(EntityManager em) {
                this.em = em;
        }

        @Override
        public Query getFrequencyCountBetweenDates(LocalDate startDate, LocalDate endDate) {
                CriteriaBuilder cb = em.getCriteriaBuilder();
                CriteriaQuery<?> cq = cb.createQuery();
                Root<AccountEntry> root = cq.from(AccountEntry.class);
                Expression<?> createDateMonthSelection = cb.function(
                        "month", Integer.class, root.get(AccountEntry_.createDate));
                Expression<?> createDateYearSelection = cb.function(
                        "year", Integer.class, root.get(AccountEntry_.createDate));
                Expression<?> accountEntryIdSelection = root.get(AccountEntry_.accountEntryId);
                List<Order> orderList = new ArrayList();

                orderList.add(cb.asc(createDateYearSelection));
                orderList.add(cb.asc(createDateMonthSelection));


                // handles aggregation
                cq.multiselect(
                        createDateMonthSelection,
                        createDateYearSelection,
                        cb.count(accountEntryIdSelection)
                ).groupBy(createDateMonthSelection, createDateYearSelection);

                // uses existing spec to generate where conditions
                Specification<AccountEntry> predicates = Specification.where(AccountEntrySpecs.findAllByCreateDateBetween(startDate, endDate));
                cq.where(predicates.toPredicate(root, cq, cb))
                        .orderBy(orderList);

                return em.createQuery(cq);
        }
}
