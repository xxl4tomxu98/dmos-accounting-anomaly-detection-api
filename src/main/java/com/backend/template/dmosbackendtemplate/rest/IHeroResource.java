package com.backend.template.dmosbackendtemplate.rest;

import com.backend.template.dmosbackendtemplate.dto.HeroDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("/api/heroes")
public interface IHeroResource
{
    @GetMapping
    @RolesAllowed("dmos_read")
    List<HeroDTO> getAllHeroes();

    @GetMapping("/{heroId}")
    @RolesAllowed("dmos_read")
    HeroDTO getHero(@PathVariable("heroId") Integer heroId);

    @PostMapping
    @ApiOperation("Create Hero")
    // @RolesAllowed("dmos_write")
    HeroDTO createHero(@Valid @NotNull @RequestBody HeroDTO dto);

    @PutMapping("/{id}")
    @ApiOperation("Update Hero")
    // @RolesAllowed("dmos_write")
    HeroDTO updateHero(@Valid @NotNull @PathVariable(value = "id") Integer id,
                              @Valid @NotNull @RequestBody HeroDTO heroDTO);

    @DeleteMapping("/{id}")
    @ApiOperation("Delete Hero")
    // @RolesAllowed("dmos_write")
    void deleteComment(@Valid @NotNull @PathVariable(value = "id") Integer id);

}
