package id.ac.ui.cs.advprog.dungeonbe.core.enemy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MonsterFactoryTest {

    private MonsterFactory monsterFactory;
    private final int ENEMY_BEFORE_BOSS = 9;

    @BeforeEach
    void setUp() {
        monsterFactory = new MonsterFactory(ENEMY_BEFORE_BOSS);
    }

    @Test
    void testWhenEnemyShouldBeMonster() {
        Enemy enemy = monsterFactory.generateEnemy(ENEMY_BEFORE_BOSS-1, 1);
        assertTrue(enemy instanceof Monster);
    }

    @Test
    void testWhenEnemyShouldBeBoss() {
        Enemy enemy = monsterFactory.generateEnemy(ENEMY_BEFORE_BOSS, 1);
        assertTrue(enemy instanceof BossMonster);
    }
}
