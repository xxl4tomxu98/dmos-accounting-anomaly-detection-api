package com.ftlllc.dmosEliteApi.repository.rentalBooth;

import com.ftlllc.dmosEliteApi.domain.AccountEntry;
import com.ftlllc.dmosEliteApi.domain.AccountEntry_;
import com.ftlllc.dmosEliteApi.domain.RentalBooth;
import com.ftlllc.dmosEliteApi.domain.RentalBooth_;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.math.BigInteger;
import java.time.LocalDate;

public class RentalBoothRepositoryImpl implements RentalBoothCustomRepository
{

    @Autowired
    final EntityManager em;

    public RentalBoothRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Query getFrequencyCountBetweenDates(LocalDate startDate, LocalDate endDate) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<?> cq = cb.createQuery();
        Root<RentalBooth> root = cq.from(RentalBooth.class);
        Expression<?> createDateSelection = root.get(RentalBooth_.createDate);
        Expression<?> rentalBoothIdSelection = root.get(RentalBooth_.rentalBoothId);

        // handles aggregation
        cq.multiselect(
                createDateSelection,
                cb.count(rentalBoothIdSelection).alias("dateCount")
        ).groupBy(createDateSelection);

        // uses existing spec to generate where conditions
        Specification<RentalBooth> predicates = Specification.where(RentalBoothSpecs.findAllByCreateDateBetween(startDate, endDate));
        cq.where(predicates.toPredicate(root, cq, cb));

        return em.createQuery(cq);
    }

    @Override
    public Query getFeePaidAmounts() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<?> cq = cb.createQuery();
        Root<RentalBooth> root = cq.from(RentalBooth.class);

        Join<RentalBooth, AccountEntry> itemJoin = root.join(RentalBooth_.accountEntries);

        cq.multiselect(
            root.get(RentalBooth_.orderId),
            root.get(RentalBooth_.fee),
            cb.sum(itemJoin.get(AccountEntry_.amount)),
            cb.diff(root.get(RentalBooth_.fee), cb.sum(itemJoin.get(AccountEntry_.amount))).alias("diff")
        ).groupBy(root.get(RentalBooth_.orderId), root.get(RentalBooth_.fee))
            .having(cb.notEqual(root.get(RentalBooth_.fee), cb.sum(itemJoin.get(AccountEntry_.amount))));

        return em.createQuery(cq);
    }

}
