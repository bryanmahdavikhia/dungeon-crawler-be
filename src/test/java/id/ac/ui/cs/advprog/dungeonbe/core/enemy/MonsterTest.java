package id.ac.ui.cs.advprog.dungeonbe.core.enemy;

import id.ac.ui.cs.advprog.dungeonbe.core.hero.Hero;
import id.ac.ui.cs.advprog.dungeonbe.model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MonsterTest {
    private Class<?> monsterClass;
    private Class<?> accountClass;
    private Hero hero;

    @Mock
    Monster monsterMock;

    @Mock
    Account mockAccount;

    @BeforeEach
    void setUp() throws Exception{
        monsterClass = Class.forName("id.ac.ui.cs.advprog.dungeonbe.core.enemy.Monster");
        accountClass = Class.forName("id.ac.ui.cs.advprog.dungeonbe.model.Account");
        monsterMock = (Monster) mock(Class.forName("id.ac.ui.cs.advprog.dungeonbe.core.enemy.Monster"));
        mockAccount = (Account) mock(accountClass);

        hero = new Hero(mockAccount);
    }

    @Test
    void testMonsterIsConcreteClass(){
        assertFalse(Modifier.isAbstract(monsterClass.getModifiers()));
        assertFalse(Modifier.isInterface(monsterClass.getModifiers()));
    }

    @Test
    void testMonsterCanBeInitiatedAndHaveRightAttribute(){
        int dungeonLevel = 0;
        Monster monster = new Monster(dungeonLevel);

        assertEquals(120, monster.getHealthStat());
        assertEquals(20, monster.getAttackStat());
        assertEquals(10, monster.getExp());
        assertEquals(10, monster.getGold());
    }

    @Test
    void testSetHealthMonster(){
        Monster monster = new Monster(2);
        monster.setHealthStat(10);
        assertEquals(10, monster.getHealthStat());
    }

    @Test
    void testSetAttackMonster(){
        Monster monster = new Monster(2);
        monster.setAttackStat(10);
        assertEquals(10, monster.getAttackStat());
    }

    @Test
    void testMonsterAttack(){
        when(monsterMock.attack(hero)).thenCallRealMethod();
        when(monsterMock.getAttackStat()).thenReturn(20);
        when(monsterMock.toString()).thenCallRealMethod();

        List<String> expectedLogs = new ArrayList<>();
        expectedLogs.add("Monster is attacking");
        expectedLogs.add("Hero's damage received: 0");
        assertEquals(expectedLogs, monsterMock.attack(hero));
    }

    @Test
    void testMonsterDefense(){
        Monster monster = new Monster(0);

        assertTrue(monster.isAlive());
        assertEquals("Monster Current Health: 50", monster.defend(70));
        assertEquals(50, monster.getHealthStat());
        assertEquals("Monster Current Health: 0", monster.defend(150));
        assertEquals(0, monster.getHealthStat());
        assertFalse(monster.isAlive());
    }
}
