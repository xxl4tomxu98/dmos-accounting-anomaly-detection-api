package com.backend.template.dmosbackendtemplate.dto;

import com.backend.template.dmosbackendtemplate.domain.Hero;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@ToString
@NoArgsConstructor
@ApiModel(value = "Hero", description = "Hero")
public class HeroDTO
{
    private Integer heroId;
    private String heroName;

    public HeroDTO(Hero hero)
    {
        BeanUtils.copyProperties(hero, this);
    }
}
