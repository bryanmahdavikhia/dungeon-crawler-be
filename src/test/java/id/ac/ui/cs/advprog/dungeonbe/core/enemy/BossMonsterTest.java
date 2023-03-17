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

class BossMonsterTest {
    private Class<?> bossMonsterClass;
    private Class<?> accountClass;
    private Hero hero;

    @Mock
    BossMonster mockBoss;

    @Mock
    Account mockAccount;

    @BeforeEach
    void setUp() throws Exception{
        bossMonsterClass = Class.forName("id.ac.ui.cs.advprog.dungeonbe.core.enemy.BossMonster");
        accountClass = Class.forName("id.ac.ui.cs.advprog.dungeonbe.model.Account");
        mockBoss = (BossMonster) mock(bossMonsterClass);
        mockAccount = (Account) mock(accountClass);

        hero = new Hero(mockAccount);
        mockAccount.setItems("");
        when(mockBoss.attack(hero)).thenCallRealMethod();
        when(mockBoss.specialAttack(hero)).thenCallRealMethod();
        when(mockBoss.normalAttack(hero)).thenCallRealMethod();
    }

    @Test
    void testBossMonsterIsConcreteClass(){
        assertFalse(Modifier.isAbstract(bossMonsterClass.getModifiers()));
        assertFalse(Modifier.isInterface(bossMonsterClass.getModifiers()));
    }

    @Test
    void testBossMonsterCanBeInitiatedAndHaveRightAttribute(){
        int dungeonLevel = 0;
        BossMonster bossMonster = new BossMonster(dungeonLevel);

        assertEquals(250, bossMonster.getHealthStat());
        assertEquals(30, bossMonster.getAttackStat());
        assertEquals(25, bossMonster.getExp());
        assertEquals(30, bossMonster.getGold());
    }

    @Test
    void testAttackShouldBeSpecialAttackWhenRngLessEqual20(){
        when(mockBoss.rngSpecialAttack()).thenReturn(20);
        when(mockBoss.toString()).thenCallRealMethod();

        List<String> expectedLogs = new ArrayList<>();
        expectedLogs.add("Boss is attacking");
        expectedLogs.add("Boss doing special attack");

        assertEquals(expectedLogs, mockBoss.attack(hero));
    }

    @Test
    void testAttackShouldBeNormalAttackWhenRngGreaterThan20(){
        when(mockBoss.rngSpecialAttack()).thenReturn(21);
        when(mockBoss.toString()).thenCallRealMethod();

        List<String> expectedLogs = new ArrayList<>();
        expectedLogs.add("Boss is attacking");
        expectedLogs.add("Boss doing normal attack");

        assertEquals(expectedLogs, mockBoss.attack(hero));
    }

    @Test
    void testRngSpecialAttackShouldBeReturnIntBetween1And100(){
        BossMonster bossMonster = new BossMonster(1);

        for (int i = 0; i < 100; i++) {
            int rngSpecialAttack = bossMonster.rngSpecialAttack();
            assertTrue((1 <= rngSpecialAttack) && (rngSpecialAttack <= 100));
        }
    }

    @Test
    void testSetHealthBoss(){
        BossMonster bossMonster = new BossMonster(1);
        bossMonster.setHealthStat(10);
        assertEquals(10, bossMonster.getHealthStat());
    }

    @Test
    void testSetAttackBoss(){
        BossMonster bossMonster = new BossMonster(1);
        bossMonster.setAttackStat(10);
        assertEquals(10, bossMonster.getAttackStat());
    }

    @Test
    void testBossDefense(){
        int dungeonLevel = 0;
        BossMonster bossMonster = new BossMonster(dungeonLevel);

        assertTrue(bossMonster.isAlive());
        assertEquals("Boss Current Health: 80", bossMonster.defend(170));
        assertEquals(80, bossMonster.getHealthStat());
        assertEquals("Boss Current Health: 0", bossMonster.defend(170));
        assertEquals(0, bossMonster.getHealthStat());
        assertFalse(bossMonster.isAlive());
    }
}
