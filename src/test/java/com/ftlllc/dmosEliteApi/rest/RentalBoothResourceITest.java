package com.ftlllc.dmosEliteApi.rest;

import org.junit.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RentalBoothResourceITest extends RestControllerFixture
{

    @Test
    public void rentalBoothApiCheck()
    {
        assertStatus(get(RENTAL_BOOTH_URL), status().isOk());
        assertStatus(get(RENTAL_BOOTH_URL + "?startDate=2021-01-01"), status().isOk());
        assertStatus(get(RENTAL_BOOTH_URL + "?endDate=2021-01-01"), status().isOk());
        assertStatus(get(RENTAL_BOOTH_URL + "?startDate=2021-01-01&endDate=2021-08-01"), status().isOk());
        assertStatus(get(RENTAL_BOOTH_FREQUENCY_URL), status().isOk());
        assertStatus(get(RENTAL_BOOTH_FREQUENCY_URL + "?startDate=2021-01-01"), status().isOk());
        assertStatus(get(RENTAL_BOOTH_FREQUENCY_URL + "?endDate=2021-08-01"), status().isOk());
        assertStatus(get(RENTAL_BOOTH_FREQUENCY_URL + "?startDate=2021-01-01&endDate=2021-08-01"), status().isOk());
        assertStatus(get(RENTAL_BOOTH_FEE_PAID_URL), status().isOk());
        assertStatus(get(RENTAL_BOOTH_FEE_PAID_URL), status().isOk());
    }

}
