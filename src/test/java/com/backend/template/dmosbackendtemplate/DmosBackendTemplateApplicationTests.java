package com.backend.template.dmosbackendtemplate;


import com.backend.template.dmosbackendtemplate.domain.Hero;
import com.backend.template.dmosbackendtemplate.dto.HeroDTO;
import com.backend.template.dmosbackendtemplate.service.HeroService;
import org.junit.After;
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
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("unitTest")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@Transactional
public class DmosBackendTemplateApplicationTests {

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Autowired
    private HeroService heroService;

    protected final List<Integer> createdHeroIds = new ArrayList<>();
    protected TransactionTemplate transactionTemplate;


    @Before
    public void setupDmosBackendTemplateApplicationTests(){
        transactionTemplate = new TransactionTemplate(this.platformTransactionManager);
    }

    @After
    public void tearDown() {
        new ArrayList<>(createdHeroIds).forEach(this::deleteAHero);
    }

//----------------------------------------------------------------------------------------------------------------------

    @Test
    public void contextLoads() {
    }

    @Test
    public void whenSpringContextIsBootstrapped_thenNoExceptions() {
    }

        /*
    Helper Bois
     */

    public Hero createAHero(String heroName) {
        HeroDTO dto = new HeroDTO();
        dto.setHeroName(heroName);
        Hero returned = heroService.createHero(dto);
        createdHeroIds.add(returned.getHeroId());
        return returned;
    }

    public void deleteAHero(Integer id) {
        heroService.deleteHero(id);
        createdHeroIds.remove(id);
    }

}
