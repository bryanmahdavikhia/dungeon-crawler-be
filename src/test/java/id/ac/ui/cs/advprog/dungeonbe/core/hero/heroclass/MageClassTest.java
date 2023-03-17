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

class MageClassTest {

    private Class<?> mageClassClass;
    private Class<?> accountClass;

    @Mock
    Account mockAccount;

    @BeforeEach
    void setUp() throws Exception{
        mageClassClass = Class.forName("id.ac.ui.cs.advprog.dungeonbe.core.hero.heroclass.MageClass");
        accountClass = Class.forName("id.ac.ui.cs.advprog.dungeonbe.model.Account");
        mockAccount = (Account) mock(accountClass);
    }

    @Test
    void testMageClassIsConcreteClass(){
        assertFalse(Modifier.isAbstract(mageClassClass.getModifiers()));
        assertFalse(Modifier.isInterface(mageClassClass.getModifiers()));
    }

    @Test
    void testMageClassIsAHeroClass(){
        Collection<Type> interfaces = Arrays.asList(mageClassClass.getInterfaces());

        assertTrue(interfaces.stream()
                .anyMatch(type -> type.getTypeName()
                        .equals("id.ac.ui.cs.advprog.dungeonbe.core.hero.heroclass.HeroClass")));
    }

    @Test
    void testMageHeroHasUseSkillMethod () throws Exception{
        Class<?>[] countItemArgs = new Class[2];
        countItemArgs[0] = Hero.class;
        countItemArgs[1] = Enemy.class;
        Method useSkill = mageClassClass.getDeclaredMethod("useSkill", countItemArgs);

        assertTrue(Modifier.isPublic(useSkill.getModifiers()));
        assertEquals(2, useSkill.getParameterCount());

        when(mockAccount.getMaxMana()).thenReturn(100);
        Hero hero = new Hero(mockAccount);
        HeroClass mage = new MageClass();
        BossMonster bossMonster = new BossMonster(0);

        String usingSkill = mage.useSkill(hero, bossMonster);
        assertEquals("Attacking using skill", usingSkill);
        assertEquals(85, hero.getMana());

        hero.setMana(10);
        usingSkill = mage.useSkill(hero, bossMonster);
        assertEquals("Not enough mana", usingSkill);
        assertEquals(10, hero.getMana());

    }

    @Test
    void testMageHeroHasGiveBonusFromEquipmentMethod () throws Exception{
        Class<?>[] countItemArgs = new Class[1];
        countItemArgs[0] = Hero.class;
        Method giveBonusFromEquipment = mageClassClass.getDeclaredMethod("giveBonusFromEquipment", countItemArgs);

        assertTrue(Modifier.isPublic(giveBonusFromEquipment.getModifiers()));
        assertEquals(1, giveBonusFromEquipment.getParameterCount());

        HeroClass mage = new MageClass();
        when(mockAccount.getArmorLevel()).thenReturn(1);
        when(mockAccount.getWeaponLevel()).thenReturn(1);

        Hero hero = new Hero(mockAccount);
        hero.setAttack(80);
        hero.setDefense(50);
        assertEquals(80, hero.getAttack());
        assertEquals(50, hero.getDefense());
        mage.giveBonusFromEquipment(hero);
        assertEquals(110, hero.getAttack());
        assertEquals(75, hero.getDefense());
    }
}
