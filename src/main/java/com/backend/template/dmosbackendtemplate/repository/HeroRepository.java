package com.backend.template.dmosbackendtemplate.repository;

import com.backend.template.dmosbackendtemplate.domain.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Integer>, PagingAndSortingRepository<Hero, Integer>
{
    Optional<Hero> findOneByHeroId(Integer heroId);

    Optional<Hero> findOneByHeroNameIgnoringCase(String heroName);
}
