package com.ftlllc.dmosEliteApi.repository.rentalBooth;

import com.ftlllc.dmosEliteApi.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        Expression<?> createDateMonthSelection = cb.function(
                "month", Integer.class, root.get(RentalBooth_.createDate));
        Expression<?> createDateYearSelection = cb.function(
                "year", Integer.class, root.get(RentalBooth_.createDate));
        Expression<?> rentalBoothIdSelection = root.get(RentalBooth_.rentalBoothId);

        List<Order> orderList = new ArrayList();

        orderList.add(cb.asc(createDateYearSelection));
        orderList.add(cb.asc(createDateMonthSelection));

        // handles aggregation
        cq.multiselect(
                createDateMonthSelection,
                createDateYearSelection,
                cb.count(rentalBoothIdSelection)
        ).groupBy(createDateMonthSelection, createDateYearSelection);

        // uses existing spec to generate where conditions
        Specification<RentalBooth> predicates = Specification.where(RentalBoothSpecs.findAllByCreateDateBetween(startDate, endDate));
        cq.where(predicates.toPredicate(root, cq, cb))
                .orderBy(orderList);

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

    public Query getAnomalyScoreMonthly(BigDecimal anomalyScoreMin) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<?> cq = cb.createQuery();
        Root<RentalBooth> root = cq.from(RentalBooth.class);
        Expression<?> createDateMonthSelection = cb.function(
                "month", Integer.class, root.get(RentalBooth_.createDate));
        Expression<?> createDateYearSelection = cb.function(
                "year", Integer.class, root.get(RentalBooth_.createDate));
        Expression<?> rentalBoothIdSelection = root.get(RentalBooth_.rentalBoothId);

        Join<RentalBooth, AnomalyMap> itemJoin = root.join(RentalBooth_.anomalyMap);

        cq.where(
                cb.greaterThanOrEqualTo(
                        itemJoin.get(AnomalyMap_.score),
                        cb.literal(anomalyScoreMin.floatValue())
                )
        );

        List<Order> orderList = new ArrayList();

        orderList.add(cb.asc(createDateYearSelection));
        orderList.add(cb.asc(createDateMonthSelection));

        // handles aggregation
        cq.multiselect(
                createDateMonthSelection,
                createDateYearSelection,
                cb.count(rentalBoothIdSelection)
        ).groupBy(createDateMonthSelection, createDateYearSelection)
                .orderBy(orderList);

        return em.createQuery(cq);
    }
}
