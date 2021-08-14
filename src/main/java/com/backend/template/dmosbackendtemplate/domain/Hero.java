package com.backend.template.dmosbackendtemplate.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "heroes")
@NamedQuery(name = "Hero.findAll", query="select h from Hero h")
public class Hero
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hero_id")
    private Integer heroId;

    @NotNull
    @Column(name = "hero_name", nullable = false, unique = true)
    private String heroName;
}
