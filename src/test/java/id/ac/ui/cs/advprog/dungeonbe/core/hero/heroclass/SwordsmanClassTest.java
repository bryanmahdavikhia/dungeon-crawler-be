package id.ac.ui.cs.advprog.dungeonbe.core.hero.heroclass;

import id.ac.ui.cs.advprog.dungeonbe.core.enemy.BossMonster;
import id.ac.ui.cs.advprog.dungeonbe.core.enemy.Enemy;
import id.ac.ui.cs.advprog.dungeonbe.core.hero.Hero;
import id.ac.ui.cs.advprog.dungeonbe.model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SwordsmanClassTest {

    private Class<?> swordsmanClassClass;
    private Class<?> accountClass;

    @Mock
    Account mockAccount;

    @BeforeEach
    void setUp() throws Exception{
        swordsmanClassClass = Class.forName("id.ac.ui.cs.advprog.dungeonbe.core.hero.heroclass.SwordsmanClass");
        accountClass = Class.forName("id.ac.ui.cs.advprog.dungeonbe.model.Account");
        mockAccount = (Account) mock(accountClass);
    }

    @Test
    void testSwordsmanClassIsConcreteClass(){
        assertFalse(Modifier.isAbstract(swordsmanClassClass.getModifiers()));
        assertFalse(Modifier.isInterface(swordsmanClassClass.getModifiers()));
    }

    @Test
    void testSwordsmanClassIsAHeroClass(){
        Collection<Type> interfaces = Arrays.asList(swordsmanClassClass.getInterfaces());

        assertTrue(interfaces.stream()
                .anyMatch(type -> type.getTypeName()
                        .equals("id.ac.ui.cs.advprog.dungeonbe.core.hero.heroclass.HeroClass")));
    }

    @Test
    void testMageHeroHasUseSkillMethod () throws Exception{
        Class<?>[] countItemArgs = new Class[2];
        countItemArgs[0] = Hero.class;
        countItemArgs[1] = Enemy.class;
        Method useSkill = swordsmanClassClass.getDeclaredMethod("useSkill", countItemArgs);

        assertTrue(Modifier.isPublic(useSkill.getModifiers()));
        assertEquals(2, useSkill.getParameterCount());

        when(mockAccount.getMaxMana()).thenReturn(100);
        Hero hero = new Hero(mockAccount);
        HeroClass swordsman = new SwordsmanClass();
        BossMonster bossMonster = new BossMonster(0);

        String usingSkill = swordsman.useSkill(hero, bossMonster);
        assertEquals("Hero's defense added by 30%", usingSkill);
        assertEquals(80, hero.getMana());

        hero.setMana(10);
        usingSkill = swordsman.useSkill(hero, bossMonster);
        assertEquals("Not enough mana", usingSkill);
        assertEquals(10, hero.getMana());

    }

    @Test
    void testMageHeroHasGiveBonusFromEquipmentMethod () throws Exception{
        Class<?>[] countItemArgs = new Class[1];
        countItemArgs[0] = Hero.class;
        Method giveBonusFromEquipment = swordsmanClassClass.getDeclaredMethod("giveBonusFromEquipment", countItemArgs);

        assertTrue(Modifier.isPublic(giveBonusFromEquipment.getModifiers()));
        assertEquals(1, giveBonusFromEquipment.getParameterCount());

        HeroClass swordsman = new SwordsmanClass();
        when(mockAccount.getArmorLevel()).thenReturn(1);
        when(mockAccount.getWeaponLevel()).thenReturn(1);

        Hero hero = new Hero(mockAccount);
        hero.setAttack(80);
        hero.setDefense(50);
        assertEquals(80, hero.getAttack());
        assertEquals(50, hero.getDefense());
        swordsman.giveBonusFromEquipment(hero);
        assertEquals(105, hero.getAttack());
        assertEquals(80, hero.getDefense());
    }
}
