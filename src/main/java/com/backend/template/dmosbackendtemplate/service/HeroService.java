package com.backend.template.dmosbackendtemplate.service;

import com.backend.template.dmosbackendtemplate.domain.Hero;
import com.backend.template.dmosbackendtemplate.dto.HeroDTO;
import com.backend.template.dmosbackendtemplate.repository.HeroRepository;
import liquibase.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class HeroService {
    // TODO: Add error checking, fix exception throwing.
    @Autowired
    private HeroRepository heroRepository;


    public List<HeroDTO> getAllHeroes()
    {
        return getHeroes().stream().map(HeroDTO::new).collect(Collectors.toList());
    }

    public HeroDTO getHero(Integer heroId, String heroName)
    {
        if(heroId != null && heroId > 0)
        {
            return new HeroDTO(getHeroById(heroId));
        }
        else if (StringUtil.isNotEmpty(heroName))
        {
            return new HeroDTO(getHeroByName(heroName));
        }
        else
        {
            throw new DataIntegrityViolationException("Please provide either a heroId or heroName.");
        }

    }

    private Hero getHeroById(Integer heroId)
    {
        return heroRepository.findOneByHeroId(heroId)
                .orElseThrow(() -> new DataIntegrityViolationException("Hero not found with id: " + heroId));
    }

    private Hero getHeroByName(String heroName)
    {
        if(heroName == null || StringUtil.isEmpty(heroName))
        {
            throw new DataIntegrityViolationException("Hero name is empty.");
        }
        return heroRepository.findOneByHeroNameIgnoringCase(heroName)
                .orElseThrow(() -> new DataIntegrityViolationException("Hero not found with id: " + heroName));
    }

    private List<Hero> getHeroes()
    {
        return heroRepository.findAll();
    }

    public Hero createHero(HeroDTO dto) {
        Hero hero = new Hero();
        updateHero(hero, dto);
        return heroRepository.save(hero);
    }

    public Hero updateHero(Integer id, HeroDTO heroDTO) {
        Hero hero = getHeroById(id);
        updateHero(hero, heroDTO);
        return heroRepository.saveAndFlush(hero);
    }

    public void deleteHero(Integer id) {
        Hero hero = getHeroById(id);
        heroRepository.delete(hero);
    }

//  Helpers

    private void updateHero(Hero hero, HeroDTO heroDTO) {
        BeanUtils.copyProperties(heroDTO, hero);
    }
}
