package id.ac.ui.cs.advprog.dungeonbe.core.dungeon;

import id.ac.ui.cs.advprog.dungeonbe.core.enemy.BossMonster;
import id.ac.ui.cs.advprog.dungeonbe.core.enemy.Enemy;
import id.ac.ui.cs.advprog.dungeonbe.core.enemy.Monster;
import id.ac.ui.cs.advprog.dungeonbe.core.enemy.MonsterFactory;
import id.ac.ui.cs.advprog.dungeonbe.core.hero.Hero;
import id.ac.ui.cs.advprog.dungeonbe.model.Account;
import id.ac.ui.cs.advprog.dungeonbe.model.dto.DungeonDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DungeonImplTest {

    private DungeonImpl dungeon;

    @Mock
    Hero heroMock;

    @Mock
    MonsterFactory monsterFactoryMock;

    @Mock
    Enemy enemyMock;

    @BeforeEach
    void setUp() throws Exception{
        heroMock = (Hero) mock(Class.forName("id.ac.ui.cs.advprog.dungeonbe.core.hero.Hero"));
        enemyMock = (Enemy) mock(Class.forName("id.ac.ui.cs.advprog.dungeonbe.core.enemy.Enemy"));
        monsterFactoryMock = (MonsterFactory) mock(Class.forName("id.ac.ui.cs.advprog.dungeonbe.core.enemy.MonsterFactory"));
        dungeon = new DungeonImpl(heroMock);
        dungeon.setMonsterFactory(monsterFactoryMock);
        dungeon.setEnemy(enemyMock);
    }

    @Test
    void testAttackMethodNormal() {
        List<String> heroAttackLog = new ArrayList<>();
        heroAttackLog.add("Dummy hero attack log");
        when(heroMock.attack(enemyMock)).thenReturn(heroAttackLog);
        when(heroMock.isAlive()).thenReturn(Boolean.TRUE);

        List<String> enemyAttackLog = new ArrayList<>();
        heroAttackLog.add("Dummy enemy attack log");
        when(enemyMock.attack(heroMock)).thenReturn(enemyAttackLog);
        when(enemyMock.isAlive()).thenReturn(Boolean.TRUE);

        List<String> expectedLog = new ArrayList<>();
        expectedLog.addAll(heroAttackLog);
        expectedLog.addAll(enemyAttackLog);
        DungeonDTO expectedDTO = new DungeonDTO(heroMock, enemyMock, DungeonState.NORMAL, expectedLog);

        assertEquals(expectedDTO, dungeon.attack());
    }

    @Test
    void testUseSkillNormal() {
        List<String> heroAttackLog = new ArrayList<>();
        heroAttackLog.add("Dummy hero use skill log");
        when(heroMock.useSkill(enemyMock)).thenReturn(heroAttackLog);
        when(heroMock.isAlive()).thenReturn(Boolean.TRUE);

        List<String> enemyAttackLog = new ArrayList<>();
        heroAttackLog.add("Dummy enemy attack log");
        when(enemyMock.attack(heroMock)).thenReturn(enemyAttackLog);
        when(enemyMock.isAlive()).thenReturn(Boolean.TRUE);

        List<String> expectedLog = new ArrayList<>();
        expectedLog.addAll(heroAttackLog);
        expectedLog.addAll(enemyAttackLog);
        DungeonDTO expectedDTO = new DungeonDTO(heroMock, enemyMock, DungeonState.NORMAL, expectedLog);

        assertEquals(expectedDTO, dungeon.useSkill());
    }

    @Test
    void testUseItemNormal() {
        List<String> heroAttackLog = new ArrayList<>();
        heroAttackLog.add("Dummy hero use item log");
        when(heroMock.useItem(0)).thenReturn(heroAttackLog);
        when(heroMock.isAlive()).thenReturn(Boolean.TRUE);

        List<String> enemyAttackLog = new ArrayList<>();
        heroAttackLog.add("Dummy enemy attack log");
        when(enemyMock.attack(heroMock)).thenReturn(enemyAttackLog);
        when(enemyMock.isAlive()).thenReturn(Boolean.TRUE);

        List<String> expectedLog = new ArrayList<>();
        expectedLog.addAll(heroAttackLog);
        expectedLog.addAll(enemyAttackLog);
        DungeonDTO expectedDTO = new DungeonDTO(heroMock, enemyMock, DungeonState.NORMAL, expectedLog);

        assertEquals(expectedDTO, dungeon.useItem(0));
    }

    @Test
    void testHeroDiesAfterMonsterAttack() {
        List<String> heroAttackLog = new ArrayList<>();
        heroAttackLog.add("Dummy hero attack log");
        when(heroMock.attack(enemyMock)).thenReturn(heroAttackLog);
        when(heroMock.isAlive()).thenReturn(Boolean.FALSE);

        List<String> enemyAttackLog = new ArrayList<>();
        heroAttackLog.add("Dummy enemy attack log");
        when(enemyMock.attack(heroMock)).thenReturn(enemyAttackLog);
        when(enemyMock.isAlive()).thenReturn(Boolean.TRUE);

        List<String> expectedLog = new ArrayList<>();
        expectedLog.addAll(heroAttackLog);
        expectedLog.addAll(enemyAttackLog);
        expectedLog.add("Hero fainted");
        expectedLog.add("Reward has been reduced");
        DungeonDTO expectedDTO = new DungeonDTO(heroMock, enemyMock, DungeonState.DEATH, expectedLog);

        assertEquals(expectedDTO, dungeon.attack());
    }

    @Test
    void testEnemyDefeatedAfterHeroAttackAndEnemyIsMonster() throws ClassNotFoundException {
        Monster monsterMock = (Monster) mock(Class.forName("id.ac.ui.cs.advprog.dungeonbe.core.enemy.Monster"));
        List<String> heroAttackLog = new ArrayList<>();
        heroAttackLog.add("Dummy hero attack log");
        when(heroMock.attack(monsterMock)).thenReturn(heroAttackLog);
        when(heroMock.isAlive()).thenReturn(Boolean.TRUE);

        List<String> enemyAttackLog = new ArrayList<>();
        heroAttackLog.add("Dummy monster attack log");
        when(monsterMock.attack(heroMock)).thenReturn(enemyAttackLog);
        when(monsterMock.isAlive()).thenReturn(Boolean.FALSE);
        when(monsterMock.toString()).thenCallRealMethod();
        dungeon.setEnemy(monsterMock);

        List<String> expectedLog = new ArrayList<>();
        expectedLog.addAll(heroAttackLog);
        expectedLog.add("Monster has been defeated");
        DungeonDTO expectedDTO = new DungeonDTO(heroMock, monsterMock, DungeonState.WIN, expectedLog);

        assertEquals(expectedDTO, dungeon.attack());
    }

    @Test
    void testEnemyDefeatedAfterHeroAttackAndEnemyIsBoss() throws ClassNotFoundException {
        BossMonster bossMock = (BossMonster) mock(Class.forName("id.ac.ui.cs.advprog.dungeonbe.core.enemy.BossMonster"));
        List<String> heroAttackLog = new ArrayList<>();
        heroAttackLog.add("Dummy hero attack log");
        when(heroMock.attack(bossMock)).thenReturn(heroAttackLog);
        List<String> heroGetReward = new ArrayList<>();
        heroGetReward.add("Dummy hero get reward log");
        when(heroMock.destructor()).thenReturn(heroGetReward);
        when(heroMock.isAlive()).thenReturn(Boolean.TRUE);

        List<String> enemyAttackLog = new ArrayList<>();
        heroAttackLog.add("Dummy boss attack log");
        when(bossMock.attack(heroMock)).thenReturn(enemyAttackLog);
        when(bossMock.isAlive()).thenReturn(Boolean.FALSE);
        when(bossMock.toString()).thenCallRealMethod();
        dungeon.setEnemy(bossMock);

        List<String> expectedLog = new ArrayList<>();
        expectedLog.addAll(heroAttackLog);
        expectedLog.add("Boss has been defeated");
        expectedLog.add("Dungeon has been finished");
        expectedLog.addAll(heroGetReward);
        DungeonDTO expectedDTO = new DungeonDTO(heroMock, bossMock, DungeonState.FINISH, expectedLog);

        assertEquals(expectedDTO, dungeon.attack());
    }

    @Test
    void testListItemFromHero() {
        List<String> heroItemListName = new ArrayList<>();
        heroItemListName.add("Dummy hero list name item 0");
        heroItemListName.add("Dummy hero list name item 1");
        when(heroMock.getListNameItems()).thenReturn(heroItemListName);

        List<String> expectedLog = new ArrayList<>(heroItemListName);
        DungeonDTO expectedDTO = new DungeonDTO(heroMock, enemyMock, DungeonState.ITEM, expectedLog);

        assertEquals(expectedDTO, dungeon.item());
    }

    @Test
    void testHeroContinue() throws ClassNotFoundException {
        Enemy enemyMockAnother = (Enemy) mock(Class.forName("id.ac.ui.cs.advprog.dungeonbe.core.enemy.Enemy"));
        when(enemyMockAnother.toString()).thenReturn("Monster");
        when(monsterFactoryMock.generateEnemy(0, 0)).thenReturn(enemyMockAnother);

        List<String> expectedLog = new ArrayList<>();
        expectedLog.add("An enemy Monster appeared!");
        DungeonDTO expectedDTO = new DungeonDTO(heroMock, enemyMockAnother, DungeonState.NORMAL, expectedLog);

        assertEquals(expectedDTO, dungeon.heroContinue());
    }

    @Test
    void testHeroLeave() {
        List<String> heroGetReward = new ArrayList<>();
        heroGetReward.add("Dummy hero get reward log");
        when(heroMock.destructor()).thenReturn(heroGetReward);

        List<String> expectedLog = new ArrayList<>();
        expectedLog.add("Hero leave the Dungeon");
        expectedLog.addAll(heroGetReward);
        DungeonDTO expectedDTO = new DungeonDTO(heroMock, enemyMock, DungeonState.LEAVE, expectedLog);

        assertEquals(expectedDTO, dungeon.heroLeave());
    }

    @Test
    void testHeroTimeOut() {
        List<String> heroGetReward = new ArrayList<>();
        heroGetReward.add("Dummy hero get reward log");
        when(heroMock.destructor()).thenReturn(heroGetReward);

        List<String> expectedLog = new ArrayList<>();
        expectedLog.add("Hero took too long to respond");
        expectedLog.add("Reward has been reduced");
        expectedLog.addAll(heroGetReward);
        DungeonDTO expectedDTO = new DungeonDTO(heroMock, enemyMock, DungeonState.TIMEOUT, expectedLog);

        assertEquals(expectedDTO, dungeon.heroTimeOut());
    }

    @Test
    void testToString() {
        assertEquals("Dungeon", dungeon.toString());
    }

    @Test
    void testGetAccount() throws ClassNotFoundException {
        Account accountMock = (Account) mock(Class.forName("id.ac.ui.cs.advprog.dungeonbe.model.Account"));
        when(heroMock.getAccount()).thenReturn(accountMock);
        assertEquals(accountMock, dungeon.getAccount());
    }
}
