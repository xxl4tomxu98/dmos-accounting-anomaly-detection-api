package com.backend.template.dmosbackendtemplate.service;

import com.backend.template.dmosbackendtemplate.DmosBackendTemplateApplicationTests;
import com.backend.template.dmosbackendtemplate.domain.Hero;
import com.backend.template.dmosbackendtemplate.dto.HeroDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class HeroServiceTest extends DmosBackendTemplateApplicationTests {

    @Autowired
    private HeroService heroService;

//----------------------------------------------------------------------------------------------------------------------

    @Test
    public void getAllHeroes()
    {
        createAHero("Wade Wilson");
        createAHero("Anthony Masters");
        createAHero("Alex Hayden");
        createAHero("Robert Dobalina");
        List<HeroDTO> results = heroService.getAllHeroes();
        assertEquals(results.size(), 8);
    }

    @Test
    public void createHero()
    {
        Hero results = createAHero("Wade Wilson");
        assertEquals(results.getHeroName(), "Wade Wilson");
    }

    @Test
    public void createHero_badId()
    {
        HeroDTO dto = new HeroDTO();
        dto.setHeroName("Jane Foster");
        dto.setHeroId(10000);
        createdHeroIds.add(10);
        Hero results = heroService.createHero(dto);
        // TODO: Figure out why this is ending up with an ID of 6. I assume my teardown is doing something improperly.
        // However, it's not showing anything in the list of heroes. Generation issue?
        // Test currently passes as it's technically succeeding minus this bug.
        // Goal is, like successful manual tests, to ignore inputted ID's so it'll use the next in sequence.
        assertEquals(java.util.Optional.of(10), java.util.Optional.of(results.getHeroId()));
    }

    @Test
    public void updateHero() {
        Hero hero = createAHero("Wade Wilson");
        hero.setHeroName("Slade Wilson");
        HeroDTO updatedHero = new HeroDTO(hero);
        Hero returned = heroService.updateHero(hero.getHeroId(), updatedHero);
        assertEquals(returned.getHeroName(), "Slade Wilson");
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void updateHero_badId() {
        Hero hero = createAHero("Wade Wilson");
        hero.setHeroName("Slade Wilson");
        HeroDTO updatedHero = new HeroDTO(hero);
        heroService.updateHero(10000, updatedHero);
    }
}
