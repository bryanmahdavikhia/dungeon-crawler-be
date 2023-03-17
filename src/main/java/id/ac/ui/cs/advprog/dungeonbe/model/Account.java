package id.ac.ui.cs.advprog.dungeonbe.model;

import id.ac.ui.cs.advprog.dungeonbe.core.hero.heroclass.HeroClassType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "Account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "max_health", nullable = false)
    private int maxHealth;

    @Column(name = "max_mana", nullable = false)
    private int maxMana;

    @Column(name = "attack", nullable = false)
    private int attack;

    @Column(name = "defense", nullable = false)
    private int defense;

    @Column(name = "exp", nullable = false)
    private int exp;

    @Column(name = "gold", nullable = false)
    private int gold;

    @Column(name = "level", nullable = false)
    private int level;

    @Column(name = "dungeon_level", nullable = false)
    private int dungeonLevel;

    @Column(name = "weapon_level", nullable = false)
    private int weaponLevel;

    @Column(name = "armor_level", nullable = false)
    private int armorLevel;

    @Column(name = "items", nullable = false)
    private String items;

    @Enumerated(EnumType.STRING)
    @Column(name = "hero_class_type", nullable = false)
    private HeroClassType heroClassType;
}
