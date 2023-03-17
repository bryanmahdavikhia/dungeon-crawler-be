package id.ac.ui.cs.advprog.dungeonbe.model.dto;

import id.ac.ui.cs.advprog.dungeonbe.core.dungeon.DungeonState;
import id.ac.ui.cs.advprog.dungeonbe.core.enemy.Enemy;
import id.ac.ui.cs.advprog.dungeonbe.core.hero.Hero;
import lombok.Data;

import java.util.List;

@Data
public class DungeonDTO {
    int heroHealth;
    int heroMana;
    int heroAttack;
    int heroDefense;
    int enemyHealth;
    int enemyAttack;
    String enemyType;
    DungeonState dungeonState;
    List<String> logs;

    public DungeonDTO(Hero hero, Enemy enemy, DungeonState dungeonState, List<String> logs) {
        this.heroHealth = hero.getHealth();
        this.heroMana = hero.getMana();
        this.heroAttack = hero.getAttack();
        this.heroDefense = hero.getDefense();
        this.enemyHealth = enemy.getHealthStat();
        this.enemyAttack = enemy.getAttackStat();
        this.enemyType = enemy.toString();
        this.dungeonState = dungeonState;
        this.logs = logs;
    }

}
