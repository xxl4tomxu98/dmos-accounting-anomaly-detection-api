package com.ftlllc.dmosEliteApi.repository.accountEntry;

import com.ftlllc.dmosEliteApi.domain.AccountEntry;
import com.ftlllc.dmosEliteApi.domain.AccountEntry_;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.time.LocalDate;

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
                Expression<?> createDateSelection = root.get(AccountEntry_.createDate);
                Expression<?> accountEntryIdSelection = root.get(AccountEntry_.accountEntryId);

                // handles aggregation
                cq.multiselect(
                        createDateSelection,
                        cb.count(accountEntryIdSelection).alias("dateCount")
                ).groupBy(createDateSelection);

                // uses existing spec to generate where conditions
                Specification<AccountEntry> predicates = Specification.where(AccountEntrySpecs.findAllByCreateDateBetween(startDate, endDate));
                cq.where(predicates.toPredicate(root, cq, cb));

                return em.createQuery(cq);
        }
}
