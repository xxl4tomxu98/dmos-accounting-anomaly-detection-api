package com.ftlllc.dmosEliteApi.repository.rentalBooth;

import com.ftlllc.dmosEliteApi.domain.RentalBooth;
import com.ftlllc.dmosEliteApi.domain.RentalBooth_;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
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

}
