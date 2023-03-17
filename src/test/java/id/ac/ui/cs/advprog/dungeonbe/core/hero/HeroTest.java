package id.ac.ui.cs.advprog.dungeonbe.core.hero;

import id.ac.ui.cs.advprog.dungeonbe.core.enemy.Enemy;
import id.ac.ui.cs.advprog.dungeonbe.core.hero.heroclass.HeroClass;
import id.ac.ui.cs.advprog.dungeonbe.core.hero.heroclass.HeroClassType;
import id.ac.ui.cs.advprog.dungeonbe.core.item.Item;
import id.ac.ui.cs.advprog.dungeonbe.model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class HeroTest {

    private Class<?> heroClass;
    private Hero hero;
    private Account account;
    private Class<?> accountClass;

    @Mock
    Enemy enemyMock;

    @Mock
    Account mockAccount;

    @BeforeEach
    void setUp() throws Exception{
        heroClass = Class.forName("id.ac.ui.cs.advprog.dungeonbe.core.hero.Hero");
        account = new Account("1", 100, 100, 10, 10, 0, 0, 1, 0, 0, 0, "0,1", HeroClassType.SWORDSMAN);
        hero = new Hero(account);
        enemyMock = (Enemy) mock(Class.forName("id.ac.ui.cs.advprog.dungeonbe.core.enemy.Enemy"));
        accountClass = Class.forName("id.ac.ui.cs.advprog.dungeonbe.model.Account");
        mockAccount = (Account) mock(accountClass);
    }

    @Test
    void testHeroIsConcreteClass(){
        assertFalse(Modifier.isAbstract(heroClass.getModifiers()));
        assertFalse(Modifier.isInterface(heroClass.getModifiers()));
    }

    @Test
    void testHeroHasGetHeroClassFromEnumAndCreateRightHero() throws Exception{
        Class<?>[] countItemArgs = new Class[1];
        countItemArgs[0] = HeroClassType.class;
        Method getHeroClassFromEnum = heroClass.getDeclaredMethod("getHeroClassFromEnum", countItemArgs);

        assertFalse(Modifier.isPublic(getHeroClassFromEnum.getModifiers()));
        assertEquals(1, getHeroClassFromEnum.getParameterCount());

        Class<?> classType = Class.forName("id.ac.ui.cs.advprog.dungeonbe.core.hero.heroclass.SwordsmanClass");
        when(mockAccount.getHeroClassType()).thenReturn(HeroClassType.SWORDSMAN);
        Hero hero = new Hero(mockAccount);
        HeroClass createdHeroClass = hero.getHeroClass();
        assertEquals(createdHeroClass.getClass(), classType);

        classType = Class.forName("id.ac.ui.cs.advprog.dungeonbe.core.hero.heroclass.ArcherClass");
        when(mockAccount.getHeroClassType()).thenReturn(HeroClassType.ARCHER);
        hero = new Hero(mockAccount);
        createdHeroClass = hero.getHeroClass();
        assertEquals(createdHeroClass.getClass(), classType);

        classType = Class.forName("id.ac.ui.cs.advprog.dungeonbe.core.hero.heroclass.MageClass");
        when(mockAccount.getHeroClassType()).thenReturn(HeroClassType.MAGE);
        hero = new Hero(mockAccount);
        createdHeroClass = hero.getHeroClass();
        assertEquals(createdHeroClass.getClass(), classType);
    }

    @Test
    void testHeroHasGetItemsFromItemStringAndCreateRightItem() throws Exception{
        Class<?>[] countItemArgs = new Class[1];
        countItemArgs[0] = String.class;
        Method getHeroClassFromEnum = heroClass.getDeclaredMethod("getItemsFromItemString", countItemArgs);

        assertFalse(Modifier.isPublic(getHeroClassFromEnum.getModifiers()));
        assertEquals(1, getHeroClassFromEnum.getParameterCount());

        when(mockAccount.getItems()).thenReturn("");
        Hero hero = new Hero(mockAccount);
        List<Item> createdList = hero.getItems();
        assertEquals(createdList.getClass(), ArrayList.class);
        assertEquals(0, createdList.size());

        when(mockAccount.getItems()).thenReturn("0,1");
        hero = new Hero(mockAccount);
        createdList = hero.getItems();
        assertEquals(createdList.getClass(), ArrayList.class);

        Item item = createdList.get(0);
        Class<?> classType = Class.forName("id.ac.ui.cs.advprog.dungeonbe.core.item.HealthPotion");
        assertEquals(item.getClass(), classType);

        item = createdList.get(1);
        classType = Class.forName("id.ac.ui.cs.advprog.dungeonbe.core.item.ManaPotion");
        assertEquals(item.getClass(), classType);
    }

    @Test
    void testHeroHasAttackMethod () throws Exception{
        Class<?>[] countItemArgs = new Class[1];
        countItemArgs[0] = Enemy.class;
        Method attack = heroClass.getDeclaredMethod("attack", countItemArgs);

        assertTrue(Modifier.isPublic(attack.getModifiers()));
        assertEquals(1, attack.getParameterCount());
    }

    @Test
    void testHeroHasDefendMethod () throws Exception{
        Class<?>[] countItemArgs = new Class[1];
        countItemArgs[0] = int.class;
        Method defend = heroClass.getDeclaredMethod("defend", countItemArgs);

        assertTrue(Modifier.isPublic(defend.getModifiers()));
        assertEquals(1, defend.getParameterCount());

        Hero hero = new Hero(mockAccount);
        String defending = hero.defend(0);
        assertEquals("Hero's damage received: 0", defending);
        defending = hero.defend(1000);
        assertEquals("Hero's damage received: 1000", defending);
    }

    @Test
    void testHeroHasUseSkillMethod () throws Exception{
        Class<?>[] countItemArgs = new Class[1];
        countItemArgs[0] = Enemy.class;
        Method useSkill = heroClass.getDeclaredMethod("useSkill", countItemArgs);

        assertTrue(Modifier.isPublic(useSkill.getModifiers()));
        assertEquals(1, useSkill.getParameterCount());
    }

    @Test
    void testHeroAttack() {
        when(enemyMock.defend(10)).thenReturn("dummy defend");
        when(enemyMock.toString()).thenReturn("Enemy");

        List<String> expectedLogs = new ArrayList<>();
        expectedLogs.add("Attacking Enemy");
        expectedLogs.add("dummy defend");

        List<String> actualLogs = hero.attack(enemyMock);
        assertEquals(expectedLogs, actualLogs);
    }

    @Test
    void testHeroDefend() {
        String log = hero.defend(11);
        assertEquals("Hero's damage received: 1", log);
    }

    @Test
    void testHeroUseSkill() {
        List<String> expectedLogs = new ArrayList<>();
        expectedLogs.add("Using skill");
        expectedLogs.add("Hero's defense added by 30%");

        List<String> actualLogs = hero.useSkill(enemyMock);
        assertEquals(expectedLogs, actualLogs);
    }

    @Test
    void testHeroUseItem() {
        hero.setHealth(50);

        List<String> expectedLogs = new ArrayList<>();
        expectedLogs.add("Using Health potion");
        expectedLogs.add("Replenished health by 100");
        List<Integer> expectedItem = new ArrayList<>();
        expectedItem.add(1);

        List<String> actualLogs = hero.useItem(1);
        assertEquals(expectedLogs, actualLogs);
        assertEquals(100, hero.getHealth());
        assertEquals(expectedItem, hero.getListIdItems());
    }

    @Test
    void testHeroGetReward() {
        hero.setGoldCollected(50);
        hero.setExpCollected(200);

        List<String> expectedLogs = new ArrayList<>();
        expectedLogs.add("Hero get 200 exp and 50 gold");
        expectedLogs.add("Hero leveled up");

        List<String> actualLogs = hero.destructor();
        assertEquals(expectedLogs, actualLogs);
        assertEquals(2, account.getLevel());
        assertEquals(100, account.getExp());
        assertEquals(50, account.getGold());
    }

    @Test
    void testHeroExpNeeded() {
        assertEquals(100, hero.expNeeded());
    }

    @Test
    void testHeroGetListIdItems() {
        List<Integer> expectedList = new ArrayList<>();
        expectedList.add(0);
        expectedList.add(1);

        List<Integer> actualList = hero.getListIdItems();
        assertEquals(expectedList, actualList);
    }

    @Test
    void testHeroGetListNameItems() {
        List<String> expectedList = new ArrayList<>();
        expectedList.add("Health potion");
        expectedList.add("Mana potion");

        List<String> actualList = hero.getListNameItems();
        assertEquals(expectedList, actualList);
    }

    @Test
    void testHeroIsAlive() {
        assertTrue(hero.isAlive());
        hero.setHealth(0);
        assertFalse(hero.isAlive());
    }

    @Test
    void testHeroSetHealth() {
        hero.setHealth(50);
        assertEquals(50, hero.getHealth());
        hero.setHealth(-50);
        assertEquals(0, hero.getHealth());
        hero.setHealth(200);
        assertEquals(100, hero.getHealth());
    }

    @Test
    void testHeroSetMana() {
        hero.setMana(50);
        assertEquals(50, hero.getMana());
        hero.setMana(-50);
        assertEquals(0, hero.getMana());
        hero.setMana(200);
        assertEquals(100, hero.getMana());
    }

    @Test
    void testHeroHasUseItemMethod () throws Exception{
        Class<?>[] countItemArgs = new Class[1];
        countItemArgs[0] = int.class;
        Method useItem = heroClass.getDeclaredMethod("useItem", countItemArgs);

        assertTrue(Modifier.isPublic(useItem.getModifiers()));
        assertEquals(1, useItem.getParameterCount());
    }

    @Test
    void testHeroHasDestructorMethod () throws Exception{
        Method destructor = heroClass.getDeclaredMethod("destructor");

        assertTrue(Modifier.isPublic(destructor.getModifiers()));
        assertEquals(0, destructor.getParameterCount());

        when(mockAccount.getItems()).thenReturn("0,1");
        Hero hero = new Hero(mockAccount);
        hero.destructor();
    }
}
