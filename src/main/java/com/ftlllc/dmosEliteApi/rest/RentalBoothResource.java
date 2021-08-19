package com.ftlllc.dmosEliteApi.rest;

import com.ftlllc.dmosEliteApi.dto.RentalBoothDTO;
import com.ftlllc.dmosEliteApi.service.RentalBoothService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.Map;

@RestController
public class RentalBoothResource implements IRentalBoothResource {
    @Autowired
    private RentalBoothService rentalBoothService;

    @Override
    public Page<RentalBoothDTO> getAllRentalBooths(
                    @RequestParam(value="startDate", required = false)
                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                    LocalDate startDate,
                    @RequestParam(value="endDate", required = false)
                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                    LocalDate endDate,
                    @ApiParam(value = "Page number")
                    @Min(value = 1, message = "pageNumber has to be at least 1")
                    @RequestParam(value="pageNumber", required = false, defaultValue = "1")
                    Integer pageNumber,
                    @ApiParam(value = "Number of rows to return")
                    @Min(value = 1, message = "pageSize has to be at least 1")
                    @Max(value = 500, message = "pageSize can be a maximum of 500")
                    @RequestParam(value="pageSize", required = false, defaultValue = "25")
                    Integer pageSize,
                    @RequestParam(value="sortBy", required = false, defaultValue = "createDate")
                    String sortBy,
                    @RequestParam(value="sortOrder", required = false, defaultValue = "ASC")
                    Sort.Direction sortOrder) {
        return rentalBoothService.getAllRentalBooths(startDate, endDate, pageNumber, pageSize, sortBy, sortOrder);
    }


    @Override
    public Map<String, Long> getTotalRentalBoothCountsByDate(
                    @RequestParam(required = false)
                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                    LocalDate startDate,
                    @RequestParam(required = false)
                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                    LocalDate endDate
                    ) {
        return rentalBoothService.getTotalRentalBoothFrequencyByMonth(startDate, endDate);
    }
}
