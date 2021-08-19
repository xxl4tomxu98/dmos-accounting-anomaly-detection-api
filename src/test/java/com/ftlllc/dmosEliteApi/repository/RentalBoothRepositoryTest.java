package com.ftlllc.dmosEliteApi.repository;

import com.ftlllc.dmosEliteApi.DmosEliteApiApplicationTests;
import com.ftlllc.dmosEliteApi.domain.RentalBooth;
import com.ftlllc.dmosEliteApi.repository.rentalBooth.RentalBoothCustomRepository;
import com.ftlllc.dmosEliteApi.repository.rentalBooth.RentalBoothRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Query;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RentalBoothRepositoryTest extends DmosEliteApiApplicationTests
{

    @Autowired
    private RentalBoothRepository rbr;

    @Autowired
    private RentalBoothCustomRepository rbcr;

    protected List<RentalBooth> results = null;

    @Test
    public void validateDataReturnedFromFindAll() {
        results = rbr.findAll();
        assertEquals(199, results.size());
        assertNotNull(results.get(1).getRentalBoothId());
        assertNotNull(results.get(1).getRental());
        assertNotNull(results.get(1).getFee());
        assertNotNull(results.get(1).getPaid());
        assertNotNull(results.get(1).getOrderId());
        assertNotNull(results.get(1).getGroupId());
        assertNotNull(results.get(1).getCreateDate());
    }

    @Test
    public void getRentalBoothFrequencyAllRecords() {
        Query q = rbcr.getFrequencyCountBetweenDates(null, null);

        List<Object[]> queryResult = q.getResultList();
        assertEquals(3, queryResult.size());
    }

    @Test
    public void getRentalBoothFrequencyCustomDates() {
        Query q = rbcr.getFrequencyCountBetweenDates(startDate, endDate);

        List<Object[]> queryResult = q.getResultList();
        assertEquals(3, queryResult.size());
    }

}
