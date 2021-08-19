package com.ftlllc.dmosEliteApi.rest;

import org.junit.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ReportResourceITest extends RestControllerFixture
{

    @Test
    public void reportApiCheck()
    {
        assertStatus(get(ANOMALY_MONTHLY_COUNTS), status().isOk());
        assertStatus(get(ANOMALY_MONTHLY_COUNTS), status().isOk());
    }

}
