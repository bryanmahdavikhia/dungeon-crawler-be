package id.ac.ui.cs.advprog.dungeonbe.core.enemy;

import lombok.Data;

@Data
public class MonsterFactory {
    private final int enemyNeededBeforeBoss;

    public MonsterFactory(int enemyNeededBeforeBoss){
        this.enemyNeededBeforeBoss = enemyNeededBeforeBoss;
    }

    public Enemy generateEnemy(int enemyDefeated, int dungeonLevel) {
        if (enemyDefeated < enemyNeededBeforeBoss) {
            return new Monster(dungeonLevel);
        } else {
            return new BossMonster(dungeonLevel);
        }
    }
}
