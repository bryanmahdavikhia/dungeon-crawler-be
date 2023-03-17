package id.ac.ui.cs.advprog.dungeonbe.core.hero.heroclass;

import id.ac.ui.cs.advprog.dungeonbe.core.enemy.Enemy;
import id.ac.ui.cs.advprog.dungeonbe.core.hero.Hero;

public interface HeroClass {
    String useSkill(Hero hero, Enemy enemy);
    void giveBonusFromEquipment(Hero hero);
}
