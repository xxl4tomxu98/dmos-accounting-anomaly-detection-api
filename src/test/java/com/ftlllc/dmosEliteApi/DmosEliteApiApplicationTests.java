package com.ftlllc.dmosEliteApi;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.transaction.Transactional;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("unitTest")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@Transactional
public class DmosEliteApiApplicationTests {

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    protected TransactionTemplate transactionTemplate;


    @Before
    public void setupDmosBackendTemplateApplicationTests(){
        transactionTemplate = new TransactionTemplate(this.platformTransactionManager);
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void whenSpringContextIsBootstrapped_thenNoExceptions() {
    }

}
