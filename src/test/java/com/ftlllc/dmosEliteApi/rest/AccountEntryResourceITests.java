package com.ftlllc.dmosEliteApi.rest;

import org.junit.Test;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AccountEntryResourceITests extends RestControllerFixture
{

    @Test
    public void accountEntriesApiCheck()
    {
        assertStatus(get(ACCOUNT_ENTRIES_URL), status().isOk());
        assertStatus(get(ACCOUNT_ENTRIES_URL + "?startDate=2021-01-01"), status().isOk());
        assertStatus(get(ACCOUNT_ENTRIES_URL + "?endDate=2021-01-01"), status().isOk());
        assertStatus(get(ACCOUNT_ENTRIES_URL + "?startDate=2021-01-01&endDate=2021-08-01"), status().isOk());
        assertStatus(get(ACCOUNT_FREQUENCY_URL), status().isOk());
        assertStatus(get(ACCOUNT_FREQUENCY_URL + "?startDate=2021-01-01"), status().isOk());
        assertStatus(get(ACCOUNT_FREQUENCY_URL + "?endDate=2021-08-01"), status().isOk());
        assertStatus(get(ACCOUNT_FREQUENCY_URL + "?startDate=2021-01-01&endDate=2021-08-01"), status().isOk());
    }

}
