package com.ftlllc.dmosEliteApi.service;

import com.ftlllc.dmosEliteApi.DmosEliteApiApplicationTests;
import com.ftlllc.dmosEliteApi.dto.RentalBoothDTO;
import com.ftlllc.dmosEliteApi.dto.payload.FeesPaidReportPayloadDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RentalBoothServiceTest extends DmosEliteApiApplicationTests
{
    @Autowired
    private RentalBoothService rentalBoothService;

    protected Page<RentalBoothDTO> results = null;
    protected List<RentalBoothDTO> resultList = null;

    @Test
    public void getAllRentalBooths() {
        results = rentalBoothService.getAllRentalBooths(null, null, 1, 100, "createDate", Sort.Direction.ASC);
        assertEquals(199, results.getTotalElements());
        assertEquals(2, results.getTotalPages());
        resultList = results.getContent();
        assertNotNull(resultList.get(1).getRentalBoothId());
        assertNotNull(resultList.get(1).getRental());
        assertNotNull(resultList.get(1).getFee());
        assertNotNull(resultList.get(1).getPaid());
        assertNotNull(resultList.get(1).getOrderId());
        assertNotNull(resultList.get(1).getGroupId());
        assertNotNull(resultList.get(1).getCreateDate());
    }

    @Test
    public void getAllRentalBoothsBetweenDates() {
        results = rentalBoothService.getAllRentalBooths(startDate, endDate, 1, 200, "createDate", Sort.Direction.ASC);
        assertEquals(199, results.getTotalElements());
        assertEquals(1, results.getTotalPages());
    }

    @Test
    public void getAllRentalBoothsMissingStartDate() {
        results = rentalBoothService.getAllRentalBooths(null, endDate, 1, 25, "createDate", Sort.Direction.ASC);
        assertEquals(199, results.getTotalElements());
        assertEquals(8, results.getTotalPages());
    }

    @Test
    public void getAllRentalBoothsMissingEndDate() {
        results = rentalBoothService.getAllRentalBooths(startDate, null, 1, 150, "createDate", Sort.Direction.ASC);
        assertEquals(199, results.getTotalElements());
        assertEquals(2, results.getTotalPages());
    }

    @Test
    public void getAllRentalBoothsOutOfBoundsPageNumber() {
        results = rentalBoothService.getAllRentalBooths(startDate, null, 5, 150, "createDate", Sort.Direction.ASC);
        assertEquals(0, results.getNumberOfElements());
    }

    @Test
    public void getFeedPaidAmountsReport() {
        List<FeesPaidReportPayloadDTO> response = rentalBoothService.getFeedPaidAmountsReport();
        assertEquals(18, response.size());
    }
}
