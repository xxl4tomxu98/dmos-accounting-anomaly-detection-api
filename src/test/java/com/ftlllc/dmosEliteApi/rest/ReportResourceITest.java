package com.ftlllc.dmosEliteApi.rest;

import org.junit.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ReportResourceITest extends RestControllerFixture
{

    @Test
    public void reportApiCheck()
    {
        assertStatus(get(FEE_V_PAID_AMOUNTS), status().isOk());
        assertStatus(get(FEE_V_PAID_AMOUNTS), status().isOk());
    }

}
