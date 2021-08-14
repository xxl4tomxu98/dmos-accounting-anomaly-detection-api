package com.backend.template.dmosbackendtemplate.rest;

import com.backend.template.dmosbackendtemplate.dto.HeroDTO;
import com.backend.template.dmosbackendtemplate.service.HeroService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
public class HeroResource implements IHeroResource
{

    @Inject
    private HeroService heroService;

    @Override
    public List<HeroDTO> getAllHeroes() {
        return heroService.getAllHeroes();
    }

    @Override
    public HeroDTO getHero(@PathVariable("heroId") Integer heroId) {
        return heroService.getHero(heroId, null);
    }

    @Override
    public HeroDTO createHero(@Valid @NotNull @RequestBody HeroDTO dto) {
        return new HeroDTO(heroService.createHero(dto));
    }

    @Override
    public HeroDTO updateHero(@Valid @NotNull @PathVariable(value = "id") Integer id,
            @Valid @NotNull @RequestBody HeroDTO heroDTO) {
        return new HeroDTO(heroService.updateHero(id, heroDTO));
    }

    @Override
    public void deleteComment(@Valid @NotNull @PathVariable(value = "id") Integer id) {
        heroService.deleteHero(id);
    }
}