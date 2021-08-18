package com.ftlllc.dmosEliteApi.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.LinkedMultiValueMap;

import javax.inject.Inject;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("unitTest")
public class RestControllerFixture
{

    @Autowired
    private MockMvc mvc;

    @Inject
    protected ObjectMapper objectMapper;

    @Inject
    private PlatformTransactionManager platformTransactionManager;

    protected TransactionTemplate transactionTemplate;

    protected final String BASE_URL = "/api";
    protected final String ACCOUNT_ENTRIES_URL = BASE_URL + "/accountEntries";
    protected final String ACCOUNT_FREQUENCY_URL = ACCOUNT_ENTRIES_URL + "/frequency";
    protected final String RENTAL_BOOTH_URL = BASE_URL + "/rentalBooth";
    protected final String RENTAL_BOOTH_FREQUENCY_URL = RENTAL_BOOTH_URL + "/frequency";
    protected final String RENTAL_BOOTH_FEE_PAID_URL = RENTAL_BOOTH_URL + "/feePaidAmounts";

    @Before
    public void setupFixture()
    {
        transactionTemplate = new TransactionTemplate(this.platformTransactionManager);
    }

    protected ResultActions get(String url)
    {
        try
        {
            return mvc.perform(MockMvcRequestBuilders.get(url, true, MediaType.APPLICATION_JSON));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail(e.getMessage());
            return null;
        }
    }

    protected ResultActions get(String url, String parameterName, String value)
    {
        try
        {
            return mvc.perform(MockMvcRequestBuilders.get(url, true, MediaType.APPLICATION_JSON)
                    .queryParam(parameterName, value)).andDo(print());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail(e.getMessage());
            return null;
        }
    }

    protected ResultActions get(String url, LinkedMultiValueMap<String, String> requestParameters)
    {
        try
        {
            return mvc.perform(MockMvcRequestBuilders.get(url, true, MediaType.APPLICATION_JSON)
                    .params(requestParameters)).andDo(print());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail(e.getMessage());
            return null;
        }
    }

    protected ResultActions post(String url)
    {
        try
        {
            return mvc.perform(MockMvcRequestBuilders.post(url));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail(e.getMessage());
            return null;
        }
    }

    protected ResultActions put(String url, Object content)
    {
        try
        {
            return mvc.perform(MockMvcRequestBuilders.put(url)
                    .content(asJsonString(content))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail(e.getMessage());
            return null;
        }
    }

    protected ResultActions post(String url, Object content)
    {
        try
        {
            return mvc.perform(MockMvcRequestBuilders.post(url)
                    .content(asJsonString(content))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail(e.getMessage());
            return null;
        }
    }

    protected ResultActions put(String url, String queryParameterName, String[] values)
    {
        try
        {
            return mvc.perform(MockMvcRequestBuilders.put(url)
                    .param(queryParameterName, values)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail(e.getMessage());
            return null;
        }
    }

    protected ResultActions assertStatus(ResultActions endpoint, ResultMatcher matcher)
    {
        try
        {
            return endpoint.andExpect(matcher);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail("Unexpected error calling " + endpoint.toString() + "->" + e.getMessage());
            return null;
        }
    }

    public String asJsonString(final Object obj)
    {
        try
        {
            return objectMapper.writeValueAsString(obj);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

}
